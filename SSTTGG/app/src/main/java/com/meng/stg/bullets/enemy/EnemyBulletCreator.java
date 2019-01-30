package com.meng.stg.bullets.enemy;
import com.badlogic.gdx.math.*;

public class EnemyBulletCreator
{
  public static BaseEnemyBullet create(BulletForm bf){
	switch(bf){
	  case jiguangkuai:
		break;
		case lindan:
		   SimpleGreenBullet.Pool.obtain().Init(new Vector2(160,440),new Vector2(1,1));
		   break;
		case huanyu:
		  SimpleRedBullet.Pool.obtain().Init(new Vector2(270,400),new Vector2(-1,1));
		  break; 
	}
	return new SimpleRedBullet();
  }
}
