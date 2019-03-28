package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.badlogic.gdx.*;

public class spell1 extends BaseSpellCard{

	private float ro=0;

	public void init(BaseBossPlane b){
		spellName="「FPS test」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.green)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(60)
			.setWaysDegree(6)
			.setBulletSpeed(0.4f)
			
		  };
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
	  }

	@Override
	public void update(){
	  if(Gdx.graphics.getFramesPerSecond()<58)return;
		boss.moveTo(193,250);
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		tm.update();
	  }

  }
