package com.meng.stg.planes.enemyPlane.boss.danmaku;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.planes.enemyPlane.boss.BaseBossPlane;
import com.meng.stg.planes.enemyPlane.boss.BaseSpellCard;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.ui.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;

public class Junko_7_danmaku extends BaseSpellCard{

    public Junko_7_danmaku(){
	  }

    public void init(BaseBossPlane b){
        boss=b;
		MainScreen.spellMode();
        spellShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(24)
			.setWaysDegree(15)
			.setInterval(30)
			.setBulletSpeed(2)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(-120,0))
			.setBulletColor(BulletColor.purple)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(12)
			.setWaysDegree(30)
			.setInterval(60)
			.setRandomDegree(360)
			.setBulletSpeed(0.7f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(120,0))
			.setBulletColor(BulletColor.purple)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(12)
			.setWaysDegree(30)
			.setInterval(60)
			.setRandomDegree(360)
			.setBulletSpeed(0.7f)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(-160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(40)
			.setWaysDegree(9)
			.setInterval(40)
			.setRandomDegree(360)
			.setBulletSpeed(2)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setOffset(new Vector2(160,0))
			.setBulletColor(BulletColor.blue)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(40)
			.setWaysDegree(9)
			.setInterval(40)
			.setRandomDegree(360)
			.setBulletSpeed(2)
			.setRandomCenter(64,64),
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_dark)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(36)
			.setWaysDegree(10)
			.setBulletSpeed(6)
			.setInterval(8)
		  };
        partFrame=new int[]{
			0,1800,1800,3600,3600,5400
		  };
        partHp=new float[]{
			7000,5500,5500,3500,3500,1200
		  };
	  }

	@Override
	public void update(){
	  
		super.update();
		if(boss.hp<3540&&boss.hp>3500){
		  BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
		}
	  }
	  
  }
