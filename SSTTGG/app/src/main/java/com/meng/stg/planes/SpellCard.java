package com.meng.stg.planes;
import com.meng.stg.bullets.enemy.*;

public class SpellCard{
  BossPlane boss;
  public int spellTime=1000;
  private BulletShooter[] shooters;
  private float hp[];
  private int frame[];
  private int partFrame[];
	public SpellCard(){
		
	  }
	  
	public void init(BossPlane b){
		boss=b;
	  }
	  
	  public void update(){
		for(int i=0;i<shooters.length;++i){
		  if(boss.hp>hp[i]&&frame[i]>partFrame[i]){
			continue;
		  }
		  shooters[i].shoot();
		}
	  }
	  
  }
