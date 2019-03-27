package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseNormalDanmaku;
import com.meng.TaiHunDanmaku.task.*;

public class normal1 extends BaseNormalDanmaku{
  	private TaskManager taskManager;
	public void init(BaseBossPlane baseBossPlane){
        boss=baseBossPlane;
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(48)
			.setBulletSpeed(2)
			.setBulletStyle(BulletStyle.round)
		  };
		taskManager =new TaskManager(baseBossPlane,TaskRepeatMode.repeatAll);
		taskManager.addTask(new TaskShoot(shooters));
		taskManager.addTask(new TaskWait(60));
		taskManager.addTask(new TaskMove(10000,10000));
		taskManager.addTask(new TaskWait(60));
	  }

	@Override
	public void update(){
		super.update();
		taskManager.update();
		frame++;
	  }

  }
