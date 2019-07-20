package com.meng.TaiHunDanmaku.helpers;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.math.*;

public final class ObjectPools{
  
  public static RandomXS128 randomPool=new RandomXS128(9961);
  
    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet> enemyBulletPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet>(8192){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet();
        }
    };
    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuSpell> reimuBombPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuSpell>(64){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuSpell newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuSpell();
        }
    };
    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuShoot> reimuShootPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuShoot>(64){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuShoot newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.myPlane.ReimuShoot();
        }
    };

    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletStraight>(64){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletStraight newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletStraight();
        }
    };

    
}
