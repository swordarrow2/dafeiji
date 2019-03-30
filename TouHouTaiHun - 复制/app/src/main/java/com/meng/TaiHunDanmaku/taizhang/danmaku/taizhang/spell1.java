package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.badlogic.gdx.*;

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
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.midan)
			.setWays(30)
			.setRandomCenter(256,32)
			.setWaysDegree(12)
			.setReflex(1)
			.setBulletSpeed(2f)

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
					   tm.addTask(new TaskChangeAcce(0,0.5f));
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
