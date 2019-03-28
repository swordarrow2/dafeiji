package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;

public class liukai extends BaseSpellCard{
	int offset=5;
    public liukai(){
	  }

    public void init(BaseBossPlane b){
        boss=b;
        waitFrameSpell=120;
        spellName="台符「东方妖妖梦六开」";
        shooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.lindan)
			.setWays(6)
			.setWaysDegree(60)
			.setBulletSpeed(2)
			.setRandomDegree(360)
			.setRandomCenter(48,48),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(-120,-30))
			.setBulletColor(BulletColor.purple)
			.setBulletForm(BulletForm.lindan)
			.setWays(6)
			.setWaysDegree(60)
			.setRandomDegree(360)
			.setBulletSpeed(0.7f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(120,-30))
			.setBulletColor(BulletColor.purple)
			.setBulletForm(BulletForm.lindan)
			.setWays(6)
			.setWaysDegree(60)
			.setRandomDegree(360)
			.setBulletSpeed(0.7f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(-160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.lindan)
			.setWays(6)
			.setWaysDegree(60)
			.setRandomDegree(360)
			.setBulletSpeed(2.5f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.lindan)
			.setWays(6)
			.setWaysDegree(60)
			.setRandomDegree(360)
			.setBulletSpeed(2.5f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_dark)
			.setBulletForm(BulletForm.lindan)
			.setWays(12)
			.setWaysDegree(30)
			.setRandomDegree(360)
			.setBulletSpeed(4)
		  };
	  }

    @Override
    public void update(){
        super.update();
        boss.moveTo(193,350);
		if(boss.existTime%6==0)
		for(BulletShooter bs:shooters){
			bs.offset.x+=offset;
			if(bs.offset.x>150){
				bs.offset.x=-150;
			  }
			bs.shoot();
		  }
	  }
  }

