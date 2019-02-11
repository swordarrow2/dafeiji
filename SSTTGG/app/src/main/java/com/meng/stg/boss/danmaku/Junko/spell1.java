package com.meng.stg.boss.danmaku.Junko;

import com.meng.stg.boss.*;
import com.meng.stg.boss.danmaku.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.task.TaskManager;
import com.meng.stg.task.TaskRepeatMode;
import com.meng.stg.task.TaskRunnable;

public class spell1 extends BaseSpellCard{

	private float ro=0;

	public void init(BaseBossPlane b){
		spellName="「掌上的纯光」";
        boss=b;
		boss.taskManager=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_light)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(5)
			.setWaysDegree(72)
			.setBulletSpeed(3)
		  };
		boss.taskManager.addTask(new TaskRunnable(new Runnable(){
						 @Override
						 public void run(){
							 for(BulletShooter shooter : shooters){
								 shooter.shoot();
								 shooter.bulletVelocity.rotate(ro);
								 ro+=0.15f;
							 }
						   }
					   }));
	  }

	@Override
	public void update(){
		boss.moveTo(193,250);
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		boss.taskManager.update();
	  }

  }
