package com.meng.stg2.boss.danmaku;

import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.boss.BaseBossPlane;
import com.meng.stg2.ui.*;

public abstract class BaseNormalDanmaku{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public Danmaku[] shooters;
    public int frame=0;

	public int waitFrameNormal=60;

    public abstract void init(BaseBossPlane boss);

    public void update(){
		if(waitFrameNormal -->0){
			return;
		  }	
	  }
  }
