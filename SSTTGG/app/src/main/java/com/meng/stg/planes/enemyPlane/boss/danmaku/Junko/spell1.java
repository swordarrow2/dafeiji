package com.meng.stg.planes.enemyPlane.boss.danmaku.Junko;

import com.meng.stg.bullets.enemy.*;
import com.meng.stg.planes.enemyPlane.boss.*;
import com.meng.stg.planes.enemyPlane.boss.danmaku.BaseSpellCard;
import com.meng.stg.planes.enemyPlane.bossTask.*;

public class spell1 extends BaseSpellCard{

	public TaskManager tm;
	private float ro=0;
	public void init(BaseBossPlane b){
		spellName="掌上的纯光";
        boss=b;
		tm=new TaskManager(b,TaskMode.repeatLast);
		waitFrameSpell=120;
		spellShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_light)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(5)
			.setWaysDegree(72)

			.setBulletSpeed(3)
			.setStraightMove(false)
		  };
		//	  boss.moveTo(193,250);
		tm.addTask(new TaskRunnable(new Runnable(){
			@Override
			public void run(){
				for(int i=0;i<spellShooters.length;++i){
					spellShooters[i].shoot();
					spellShooters[i].bulletVelocity.rotate(ro);
					ro+=0.15f;
				}
			}
		}));
	  }

	@Override
	public void update(){

		boss.moveTo(193,250);
		//super.update();
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		tm.update();

		/*
		 if(MainScreen.onSpellCard){
		 if(waitFrameSpell -->0){
		 return;
		 }

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