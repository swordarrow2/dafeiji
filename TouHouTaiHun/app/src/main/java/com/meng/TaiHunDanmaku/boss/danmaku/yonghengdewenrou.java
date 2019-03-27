package com.meng.TaiHunDanmaku.boss.danmaku;

import com.meng.TaiHunDanmaku.boss.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.task.*;
import java.util.*;

public class yonghengdewenrou extends BaseSpellCard{

	private float ro=0;
	Random r=new Random();
	public void init(BaseBossPlane b){
		spellName="「yhdwr」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(30)
			.setWaysDegree(72)
			.setBulletSpeed(10)
			,
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(20)
			.setWaysDegree(72)
			.setBulletSpeed(8)
			,
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(17)
			.setWaysDegree(72)
			.setBulletSpeed(9)
		  };
		tm.addTask(new TaskRunnable(new Runnable(){
						 @Override
						 public void run(){
							 for(int i=0;i<shooters.length;++i){
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=r.nextFloat()*360;
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

