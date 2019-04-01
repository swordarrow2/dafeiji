package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class normal1 extends BaseNormalDanmaku{
  	private TaskManager taskManager;
	public bulletLaser ls;
	public void init(BaseBossPlane baseBossPlane){
        boss=baseBossPlane;
		ls=new bulletLaser(new Sprite(new Texture(Gdx.files.internal("textures/beamstart1.png"))),
						   new Sprite(new Texture(Gdx.files.internal("textures/beamstart2.png"))),
						   new Sprite(new Texture(Gdx.files.internal("textures/beammid1.png"))),
						   new Sprite(new Texture(Gdx.files.internal("textures/beammid2.png"))),
						   new Sprite(new Texture(Gdx.files.internal("textures/beamend1.png"))),
						   new Sprite(new Texture(Gdx.files.internal("textures/beamend2.png"))));
		ls.color=Color.GREEN;
		ls.position.set(boss.objectCenter);	
		ls.degrees=180;
		ls.distance=190;
		MainScreen.bl=ls;
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.white)
			.setBulletForm(BulletForm.huanyu)
			.setWays(112)
			.setBulletSpeed(2)
			.setBulletStyle(BulletStyle.round)
		  };
		taskManager=new TaskManager(baseBossPlane,TaskRepeatMode.repeatAll);
		taskManager.addTask(new TaskShoot(shooters));
		taskManager.addTask(new TaskWait(60));
		taskManager.addTask(new TaskMoveTo(10000,10000));
		taskManager.addTask(new TaskWait(60));
	  }

	@Override
	public void update(){
		super.update();
		taskManager.update();
		frame++;
		ls.degrees=frame*0.3f;
	  }

  }
