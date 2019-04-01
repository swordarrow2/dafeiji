package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;

public class spell1 extends BaseSpellCard{

	private float ro=0;

	public void init(final BaseBossPlane b){
		spellName="「FPS test」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatAll);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletShooterCenter(boss.objectCenter)
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.midan)
			.setBulletWays(30)
			.setShooterCenterRandomRange(256,32)
			.setBulletWaysDegree(12)
			.setReflexCount(1)
			.setBulletVelocity(new Vector2(0,-2))

		  };

		tm.addTask(new TaskRunnable(new Runnable(){

						 @Override
						 public void run(){
							 b.moveTo(193,350);
						   }
					   }));
		tm.addTask(new TaskWait(5));
		tm.addTask(new TaskRunnable(new Runnable(){
						 @Override
						 public void run(){
							 for(int i=0;i<shooters.length;++i){
								 shooters[i].shoot();
								 shooters[i].bulletVelocity.rotate(ro);
								 ro+=0.15f;
							   }
						   }
					   }));
					   tm.addTask(new TaskWait(30));
					   tm.addTask(new TaskChangeAcceleration(0,0.5f));
	  }

	@Override
	public void update(){
		if(Gdx.graphics.getFramesPerSecond()<58)return;

		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		tm.update();
	  }

  }
