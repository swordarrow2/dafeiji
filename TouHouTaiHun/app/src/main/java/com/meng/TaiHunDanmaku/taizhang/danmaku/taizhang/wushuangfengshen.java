package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;
import java.util.*;
import com.badlogic.gdx.math.*;

public class wushuangfengshen extends BaseSpellCard{

	private float ro=0;
	Random r=new Random();
	public void init(BaseBossPlane b){
		spellName="台符「无双风神」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setOffset(new Vector2(0,0))
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.midan)
			.setWays(1)
			.setBulletCenter(new Vector2(195,450))
			.setRandomCenter(385,0)
			.setWaysDegree(2)
			.setBulletSpeed(3)
			
		  };
		tm.addTask(new TaskRunnable(new Runnable(){
						 @Override
						 public void run(){
							 for(int i=0;i<shooters.length;++i){
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
								 
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;

								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=-r.nextFloat()*60-60;
							   }
						   }
					   }));
	  }

	@Override
	public void update(){
		boss.moveTo(193,350);
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		tm.update();
	  }

  }

