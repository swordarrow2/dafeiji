package com.meng.TaiHunDanmaku.helpers;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.math.*;

public final class ObjectPools{
  
  public static RandomXS128 randomPool=new RandomXS128(9961);
  
    public static Pool<com.badlogic.gdx.scenes.scene2d.ui.Image> imagePool=new Pool<com.badlogic.gdx.scenes.scene2d.ui.Image>(512){
        @Override
        protected com.badlogic.gdx.scenes.scene2d.ui.Image newObject(){
            return new com.badlogic.gdx.scenes.scene2d.ui.Image();
        }
    };
    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet> enemyBulletPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet>(8192){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.EnemyBullet();
        }
    };

    public static Pool<com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItem> itemPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItem>(512){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItem newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItem();
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

    public static Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletInduce> reimuSubPlaneBulletInducePool=new Pool<com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletInduce>(64){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletInduce newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane.ReimuSubPlaneBulletInduce();
        }
    };

    public static Pool<com.meng.TaiHunDanmaku.baseObjects.effects.Effect> effectPool=new Pool<com.meng.TaiHunDanmaku.baseObjects.effects.Effect>(4096){
        @Override
        protected com.meng.TaiHunDanmaku.baseObjects.effects.Effect newObject(){
            return new com.meng.TaiHunDanmaku.baseObjects.effects.Effect();
        }
    };
}
