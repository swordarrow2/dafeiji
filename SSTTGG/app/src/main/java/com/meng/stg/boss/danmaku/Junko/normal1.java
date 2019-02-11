package com.meng.stg.boss.danmaku.Junko;
import com.meng.stg.boss.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.task.TaskManager;
import com.meng.stg.task.TaskMode;
import com.meng.stg.task.TaskMove;
import com.meng.stg.task.TaskShoot;
import com.meng.stg.task.TaskWait;

public class normal1 extends BaseNormalDanmaku{
	public void init(BaseBossPlane b){
        boss=b;
		boss.taskManager=new TaskManager(b,TaskMode.repeatAll);
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(48)
			.setBulletSpeed(2)
			.setBulletStyle(BulletStyle.round)
		  };
		boss.taskManager.addTask(new TaskShoot(shooters));
		boss.taskManager.addTask(new TaskWait(60));
		boss.taskManager.addTask(new TaskMove(10000,10000));
		boss.taskManager.addTask(new TaskWait(60));
	  }

	@Override
	public void update(){
		super.update();
		boss.taskManager.update();
		frame++;
	  }

  }
