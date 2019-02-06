package com.meng.stg.planes.enemyPlane.boss.danmaku;
import com.meng.stg.planes.enemyPlane.boss.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;
import java.util.*;

public class Junko_1_danmaku extends BaseSpellCard{

	private float ro=0;
	private int moveFlag=30;
	private Random ran=new Random();
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
			.setInterval(120)
			.setBulletSpeed(3)
		  };
		spellShooters=new BulletShooter[]{
			new BulletShooter().init()
			.setBaseEnemyPlane(boss)
			.setBulletCenter(boss.objectCenter)
			.setBulletColor(BulletColor.yellow_light)
			.setBulletForm(BulletForm.xiaoyu)
			.setWays(5)
			.setWaysDegree(72)
			.setInterval(2)
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
		if(MainScreen.onSpellCard){
			if(waitFrameSpell -->0){
				return;
			  }
			for(int i=0;i<spellShooters.length;++i){
				if(boss.hp>partHp[i]&&frame<partFrame[i]){
					continue;
				  }
				spellShooters[i].shoot();
				spellShooters[i].bulletVelocity.rotate(ro);
				ro+=0.1f;
			  }
			frame++;
		  }else{	
			if(waitFrameNormal -->0){
				return;
			  }
			moveFlag++;
			if(moveFlag%120==1){
				boss.moveTo(ran.nextInt(250)+136,ran.nextInt(250)+150);
			  }
			for(int i=0;i<normalShooters.length;++i){
				if(boss.hp>partHp[i]&&frame<partFrame[i]){
					continue;
				  }
				normalShooters[i].shoot();
			  }
			frame++;
		  }
		if(boss.hp<4000){
		  boss.moveTo(193,250);
			MainScreen.spellMode();
		  }
	  }

  }
