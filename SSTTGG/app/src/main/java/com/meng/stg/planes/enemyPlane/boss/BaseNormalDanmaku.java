package com.meng.stg.planes.enemyPlane.boss;

import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;

public abstract class BaseNormalDanmaku{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public BulletShooter[] normalShooters;
    public int frame=0;

	public int waitFrameNormal=60;

    public abstract void init(BaseBossPlane boss);

    public void update(){
		if(waitFrameNormal -->0){
			return;
		  }
		
	  }
  }
