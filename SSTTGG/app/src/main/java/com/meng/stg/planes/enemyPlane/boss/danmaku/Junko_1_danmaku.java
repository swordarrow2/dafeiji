package com.meng.stg.planes.enemyPlane.boss.danmaku;
import com.meng.stg.planes.enemyPlane.boss.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;
import java.util.*;
import com.meng.stg.planes.enemyPlane.bossTask.*;

public class Junko_1_danmaku extends BaseNormalDanmaku{
  	TaskManager tm;
	public void init(BaseBossPlane b){
        boss=b;
		tm=new TaskManager(b);
        normalShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(96)
			.setWaysDegree(3.75f)
			//	.setInterval(120)
			.setBulletSpeed(3)
		  };
		tm.addTask(new TaskShoot(normalShooters));
		tm.addTask(new TaskWait(60));
		tm.addTask(new TaskMove(10000,10000));
		tm.addTask(new TaskWait(60));
		
	  }
	@Override
	public void update(){
		super.update();
		tm.update();
		frame++; 
		/*
		 if(MainScreen.onSpellCard){
		 if(waitFrameSpell -->0){
		 return;
		 }
		 for(int i=0;i<spellShooters.length;++i){
		 if(boss.hp>partHp[i]&&frame<partFrame[i]){
		 continue;
		 }
		 spellShooters[i].shoot();
		 spellShooters[i].bulletVelocity.rotate(ro);
		 ro+=0.1f;
		 }
		 frame++;
		 }else{	
		 if(waitFrameNormal -->0){
		 return;
		 }
		 moveFlag++;
		 if(moveFlag%120==1){
		 boss.moveTo(ran.nextInt(250)+136,ran.nextInt(250)+150);
		 }
		 for(int i=0;i<normalShooters.length;++i){
		 if(boss.hp>partHp[i]&&frame<partFrame[i]){
		 continue;
		 }
		 normalShooters[i].shoot();
		 }
		 frame++;
		 }

		 if(boss.hp<4000){
		 boss.moveTo(193,250);
		 MainScreen.spellMode();
		 }
		 */
	  }

  }
