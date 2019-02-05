package com.meng.stg.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.bullets.myPlane.*;
import com.meng.stg.bullets.subPlane.*;

public final class ObjectPools{
    public static Pool<Image> imagePool=new Pool<Image>(500){
        @Override
        protected Image newObject(){
            return new Image();
		  }
	  };
    public static Pool<com.meng.stg.bullets.enemy.EnemyBullet> enemyBulletPool=new Pool<com.meng.stg.bullets.enemy.EnemyBullet>(){
        @Override
        protected com.meng.stg.bullets.enemy.EnemyBullet newObject(){
            return new com.meng.stg.bullets.enemy.EnemyBullet();
		  }
	  };

	public static Pool<com.meng.stg.item.enemy.EnemyItem> itemPool=new Pool<com.meng.stg.item.enemy.EnemyItem>(){
        @Override
        protected com.meng.stg.item.enemy.EnemyItem newObject(){
            return new com.meng.stg.item.enemy.EnemyItem();
		  }
	  };

    public static Pool<ReimuSpell> reimuBombPool=new Pool<ReimuSpell>(){
        @Override
        protected ReimuSpell newObject(){
            return new ReimuSpell();
		  }
	  };
    public static Pool<ReimuShoot> reimuShootPool=new Pool<ReimuShoot>(){
        @Override
        protected ReimuShoot newObject(){
            return new ReimuShoot();
		  }
	  };

    public static Pool<ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<ReimuSubPlaneBulletStraight>(){
        @Override
        protected ReimuSubPlaneBulletStraight newObject(){
            return new ReimuSubPlaneBulletStraight();
		  }
	  };

    public static Pool<ReimuSubPlaneBulletInduce> reimuSubPlaneBulletInducePool=new Pool<ReimuSubPlaneBulletInduce>(){
        @Override
        protected ReimuSubPlaneBulletInduce newObject(){
            return new ReimuSubPlaneBulletInduce();
		  }
	  };

  }
