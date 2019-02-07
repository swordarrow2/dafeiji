package com.meng.stg.planes.enemyPlane.boss;

import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.ui.*;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
	public BulletShooter[] spellShooters;
    public int frame=0;

	public int waitFrameNormal=60;
	public int waitFrameSpell=60;

    public abstract void init(BaseBossPlane boss);

    public void update(){
		if(waitFrameSpell -->0){
			return;
		  }
		for(int i=0;i<spellShooters.length;++i){
			spellShooters[i].shoot();
		  }
		frame++;
	  }
  }
