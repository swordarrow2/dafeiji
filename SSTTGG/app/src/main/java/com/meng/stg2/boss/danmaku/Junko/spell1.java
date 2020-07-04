package com.meng.stg2.boss.danmaku.Junko;

import com.meng.stg2.boss.*;
import com.meng.stg2.task.*;
import com.meng.stg2.boss.danmaku.*;
import com.meng.stg2.bullets.enemy.*;

public class spell1 extends BaseSpellCard{

	private float ro=0;

	public void init(BaseBossPlane b){
		spellName="「掌上的纯光」";
        boss=b;
		tm=new TaskManager(b,TaskRepeatMode.repeatLast);
		waitFrameSpell=120;
		shooters=new Danmaku[]{
			new Danmaku().init()
			.setEnemy(boss)
			.setBulletCenter(boss.position)
			.setBulletColor(BulletColor.yellow_light)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(5)
			.setWaysDegree(72)
			.setBulletSpeed(5)
			.setHighLight(true)
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
		boss.moveTo(193,250);
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
		tm.update();
	  }

  }
