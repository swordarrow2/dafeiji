package com.meng.stg.planes.enemyPlane.boss.danmaku;

import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.planes.enemyPlane.boss.BaseBossPlane;
import com.meng.stg.ui.*;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public String spellName="";
	public BulletShooter[] spellShooters;
    public int frame=0;

	public int waitFrameSpell=60;

    public abstract void init(BaseBossPlane boss);

    public void update(){
		if(waitFrameSpell>0){
			waitFrameSpell--;
			return;
		  }
		frame++;
	  }
  }
