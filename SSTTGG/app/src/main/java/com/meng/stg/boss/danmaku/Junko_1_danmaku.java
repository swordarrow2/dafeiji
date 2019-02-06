package com.meng.stg.boss.danmaku;
import com.meng.stg.boss.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;

public class Junko_1_danmaku extends BaseSpellCard{

	public void init(BaseBossPlane b){
        boss=b;
        normalShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.red)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(96)
			.setWaysDegree(3.75f)
			.setInterval(60)
			.setBulletSpeed(3)
		  };
		spellShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_light)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(112)
			.setWaysDegree(3.21428571f)
			.setInterval(120)
			.setBulletSpeed(3)
			.setStraightMove(false)
			
		  };
        partFrame=new int[]{
			0,1800
		  };
        partHp=new float[]{
			21000,4000
		  };
	  }

	@Override
	public void update(){
		super.update();
		if(boss.hp<4000){
		  MainScreen.spellMode();
		}
	  }

  }
