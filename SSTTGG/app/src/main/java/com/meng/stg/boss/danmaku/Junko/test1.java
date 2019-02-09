package com.meng.stg.boss.danmaku.Junko;

import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.boss.bossTask.TaskManager;
import com.meng.stg.boss.bossTask.TaskMode;
import com.meng.stg.boss.bossTask.TaskMove;
import com.meng.stg.boss.bossTask.TaskShoot;
import com.meng.stg.boss.bossTask.TaskWait;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;

public class test1 extends BaseNormalDanmaku{
  	TaskManager tm;
	public void init(BaseBossPlane b){
        boss=b;
		tm=new TaskManager(b,TaskMode.repeatAll);
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(1)
			.setWaysDegree(3.75f)
			.setBulletSpeed(1)
		  };
		tm.addTask(new TaskShoot(shooters));
		tm.addTask(new TaskWait(60));
		tm.addTask(new TaskMove(10000,10000));
		tm.addTask(new TaskWait(60));
	  }

	@Override
	public void update(){
		super.update();
		tm.update();
		frame++;
	  }

  }
