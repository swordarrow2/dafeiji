package com.meng.stg.planes.enemyPlane.boss;

import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.ui.*;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public BulletShooter[] normalShooters;
	public BulletShooter[] spellShooters;
    public float partHp[];
    public int frame=0;
    public int partFrame[];
	
	public int waitFrameNormal=60;
	public int waitFrameSpell=60;

    public abstract void init(BaseBossPlane boss);

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
			  }
			frame++;
		}else{
			if(waitFrameNormal -->0){
				return;
			  }
			for(int i=0;i<normalShooters.length;++i){
				if(boss.hp>partHp[i]&&frame<partFrame[i]){
					continue;
				  }
				normalShooters[i].shoot();
			  }
			frame++;
		}
    }
}
