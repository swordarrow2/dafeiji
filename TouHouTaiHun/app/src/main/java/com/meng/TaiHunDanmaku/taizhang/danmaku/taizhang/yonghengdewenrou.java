package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseSpellCard;
import com.meng.TaiHunDanmaku.task.*;
import java.util.*;
import com.meng.TaiHunDanmaku.helpers.*;

public class yonghengdewenrou extends BaseSpellCard {

	private float ro=0;
	public void init(BaseBossPlane b){
		spellName="台符「永恒的温柔」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.diandan)
			.setWays(15)
			.setWaysDegree(24)
			.setBulletSpeed(10)
			,
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(10)
			.setWaysDegree(36)
			.setBulletSpeed(7)
			
		  };
		tm.addTask(new TaskRunnable(new Runnable(){
						 @Override
						 public void run(){
							 for(int i=0;i<shooters.length;++i){
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.setAngle(ro);
								 ro=ObjectPools.randomPool.nextFloat()*360;
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

