package com.meng.stg.boss.danmaku.Junko;
import com.meng.stg.boss.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.boss.bossTask.*;

public class normal1 extends BaseNormalDanmaku{
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
			.setWays(48)
			.setWaysDegree(7.5f)
			.setBulletSpeed(2)
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
