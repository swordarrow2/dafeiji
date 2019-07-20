package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;

public class spell3 extends BaseSpellCard{

    private float ro = 0;

    public void init(BaseBossPlane b){
        spellName="台符「无双风神」";
        boss=b;
        taskManagerEnemyPlane=new TaskManagerEnemyPlane(b,TaskRepeatMode.repeatLast);
        waitFrameSpell=120;
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setEnemyPlane(boss)
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.midan)
			.setBulletWays(1)
			.setShooterCenter(new Vector2(195,450))
			.setShooterCenterRandomRange(385,0)
			.setBulletWaysDegree(2)
			.setBulletVelocity(new Vector2(0,-3))

		  };
		taskManagerEnemyPlane.addTask(new TaskMoveTo(193,350));
        taskManagerEnemyPlane.addTask(new TaskRunnable(new Runnable() {
											@Override
											public void run(){
												if(boss.existTime%3==0)
												  for(int i = 0; i<shooters.length; ++i){
													  for(int ii=1;ii<9;++ii){
														  shooters[i].shoot();
														  shooters[i].bulletVelocity.setAngle(ro);
														  ro=-ObjectPools.randomPool.nextFloat()*60-60;	   
														} 
													}              
											  }
										  }));
	  }

    @Override
    public void update(){
        if(waitFrameSpell>0){
            waitFrameSpell--;
            return;
		  }
        frame++;
        taskManagerEnemyPlane.update();
	  }

  }

