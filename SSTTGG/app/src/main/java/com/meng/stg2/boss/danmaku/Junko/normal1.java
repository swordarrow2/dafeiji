package com.meng.stg2.boss.danmaku.Junko;
import com.meng.stg2.boss.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg2.task.*;

public class normal1 extends BaseNormalDanmaku{
  	TaskManager tm;
	public void init(BaseBossPlane b){
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatAll);
        shooters=new Danmaku[]{
			new Danmaku().init()
			.setEnemy(boss)
			.setBulletCenter(boss.position)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(48)
			.setBulletSpeed(2)
			.setBulletStyle(BulletStyle.round)
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
