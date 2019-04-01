package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.*;
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

        BulletShooter bulletShooter1=new BulletShooter();
        bulletShooter1.baseEnemyPlane=boss;
        bulletShooter1.bulletShooterCenter=boss.objectCenter;
		bulletShooter1.bulletColor=BulletColor.red;
		bulletShooter1.bulletForm=BulletForm.lindan;
		bulletShooter1.bulletWays=6;
		bulletShooter1.bulletWaysDegree=60;
		bulletShooter1.bulletVelocity=new Vector2(0,-2);
		bulletShooter1.randomDegreeRange=360;
		bulletShooter1.shooterCenterRandomRange=new Vector2(48,48);


		BulletShooter bulletShooter2=new BulletShooter();
		bulletShooter2.baseEnemyPlane=boss;
		bulletShooter2.bulletShooterCenter=boss.objectCenter;
		bulletShooter2.shootCenterOffset=new Vector2(-120,-30);
		bulletShooter2.bulletColor=BulletColor.purple;
		bulletShooter2.bulletForm=BulletForm.lindan;
		bulletShooter2.bulletWays=6;
		bulletShooter2.bulletWaysDegree=60;
		bulletShooter2.bulletVelocity=new Vector2(0,-0.7f);
		bulletShooter2.randomDegreeRange=360;
		bulletShooter2.shooterCenterRandomRange=new Vector2(64,64);
		bulletShooter2.highLight=true;

		BulletShooter bulletShooter3=new BulletShooter();
		bulletShooter3.baseEnemyPlane=boss;
		bulletShooter3.bulletShooterCenter=boss.objectCenter;
		bulletShooter3.shootCenterOffset=new Vector2(120,-30);
		bulletShooter3.bulletColor=BulletColor.purple;
		bulletShooter3.bulletForm=BulletForm.lindan;
		bulletShooter3.bulletWays=6;
		bulletShooter3.bulletWaysDegree=60;
		bulletShooter3.bulletVelocity=new Vector2(0,-0.7f);
		bulletShooter3.randomDegreeRange=360;
		bulletShooter3.shooterCenterRandomRange=new Vector2(64,64);
		bulletShooter3.highLight=true;

		BulletShooter bulletShooter4=new BulletShooter();
		bulletShooter4.baseEnemyPlane=boss;
		bulletShooter4.bulletShooterCenter=boss.objectCenter;
		bulletShooter4.shootCenterOffset=new Vector2(120,-30);
		bulletShooter4.bulletColor=BulletColor.purple;
		bulletShooter4.bulletForm=BulletForm.lindan;
		bulletShooter4.bulletWays=6;
		bulletShooter4.bulletWaysDegree=60;
		bulletShooter4.bulletVelocity=new Vector2(0,-0.7f);
		bulletShooter4.randomDegreeRange=360;
		bulletShooter4.shooterCenterRandomRange=new Vector2(64,64);
		bulletShooter4.highLight=true;


		BulletShooter bulletShooter5=new BulletShooter();
		bulletShooter5.baseEnemyPlane=boss;
		bulletShooter5.bulletShooterCenter=boss.objectCenter;
		bulletShooter5.shootCenterOffset=new Vector2(120,-30);
		bulletShooter5.bulletColor=BulletColor.purple;
		bulletShooter5.bulletForm=BulletForm.lindan;
		bulletShooter5.bulletWays=6;
		bulletShooter5.bulletWaysDegree=60;
		bulletShooter5.bulletVelocity=new Vector2(0,-0.7f);
		bulletShooter5.randomDegreeRange=360;
		bulletShooter5.shooterCenterRandomRange=new Vector2(64,64);
		bulletShooter5.highLight=true;


		BulletShooter bulletShooter6=new BulletShooter();
		bulletShooter6.baseEnemyPlane=boss;
		bulletShooter6.bulletShooterCenter=boss.objectCenter;
		bulletShooter6.shootCenterOffset=new Vector2(120,-30);
		bulletShooter6.bulletColor=BulletColor.purple;
		bulletShooter6.bulletForm=BulletForm.lindan;
		bulletShooter6.bulletWays=6;
		bulletShooter6.bulletWaysDegree=60;
		bulletShooter6.bulletVelocity=new Vector2(0,-0.7f);
		bulletShooter6.randomDegreeRange=360;
		bulletShooter6.shooterCenterRandomRange=new Vector2(64,64);
		bulletShooter6.highLight=true;

        shooters=new BulletShooter[]{
				bulletShooter1,
				bulletShooter2,
				bulletShooter3,
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletShooterCenter(boss.objectCenter)
			.setShootCenterOffset(new Vector2(-160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.lindan)
			.setBulletWays(6)
			.setBulletWaysDegree(60)
			.setRandomDegreeRange(360)
			.setBulletVelocity(new Vector2(0,-2.5f))
			.setShooterCenterRandomRange(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletShooterCenter(boss.objectCenter)
			.setShootCenterOffset(new Vector2(160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.lindan)
			.setBulletWays(6)
			.setBulletWaysDegree(60)
			.setRandomDegreeRange(360)
			.setBulletVelocity(new Vector2(0,-2.5f))
			.setHighLight(true)
			.setShooterCenterRandomRange(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletShooterCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_dark)
			.setBulletForm(BulletForm.lindan)
			.setBulletWays(12)
			.setBulletWaysDegree(30)
			.setRandomDegreeRange(360)
			.setBulletVelocity(new Vector2(0,-4))
		  };
	  }

    @Override
    public void update(){
        super.update();
        boss.moveTo(193,350);
		if(boss.existTime%6==0)
		for(BulletShooter bs:shooters){
			bs.shootCenterOffset.x+=offset;
			if(bs.shootCenterOffset.x>150){
				bs.shootCenterOffset.x=-150;
			  }
			bs.shoot();
		  }
	  }
  }

