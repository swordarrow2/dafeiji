package com.meng.stg2.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.bullets.myPlane.*;
import com.meng.stg2.bullets.subPlane.*;
import com.meng.stg2.dropItems.*;
import com.meng.stg2.effects.*;

public final class ObjectPools{
//    public static Pool<Image> imagePool=new Pool<Image>(512){
//        @Override
//        protected Image newObject(){
//            return new Image();
//        }
//    };
    public static Pool<EnemyBullet> enemyBulletPool=new Pool<EnemyBullet>(512){
        @Override
        protected EnemyBullet newObject(){
            return new EnemyBullet();
        }
    };

    public static Pool<DropItem> itemPool=new Pool<DropItem>(512){
        @Override
        protected DropItem newObject(){
            return new DropItem();
        }
    };

    public static Pool<ReimuSpell> reimuBombPool=new Pool<ReimuSpell>(64){
        @Override
        protected ReimuSpell newObject(){
            return new ReimuSpell();
        }
    };
    public static Pool<ReimuShoot> reimuShootPool=new Pool<ReimuShoot>(64){
        @Override
        protected ReimuShoot newObject(){
            return new ReimuShoot();
        }
    };

    public static Pool<ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<ReimuSubPlaneBulletStraight>(64){
        @Override
        protected ReimuSubPlaneBulletStraight newObject(){
            return new ReimuSubPlaneBulletStraight();
        }
    };

    public static Pool<ReimuSubPlaneBulletInduce> reimuSubPlaneBulletInducePool=new Pool<ReimuSubPlaneBulletInduce>(64){
        @Override
        protected ReimuSubPlaneBulletInduce newObject(){
            return new ReimuSubPlaneBulletInduce();
        }
    };

    public static Pool<Effect> effectPool=new Pool<Effect>(512){
        @Override
        protected Effect newObject(){
            return new Effect();
        }
    };
}
