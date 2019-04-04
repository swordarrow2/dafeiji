package com.meng.TaiHunDanmaku.taizhang.danmaku;

import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;

public abstract class BaseNormalDanmaku{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public BulletShooter[] shooters;
    public int frame=0;

	public int waitFrameNormal=60;

    public abstract void init(BaseBossPlane boss);

    public void update(){
		if(waitFrameNormal -->0){
			return;
		  }	
	  }
  }
