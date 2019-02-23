package com.meng.stg.helpers;

import com.badlogic.gdx.utils.Pool;

public final class ObjectPools{
    public static Pool<com.badlogic.gdx.scenes.scene2d.ui.Image> imagePool=new Pool<com.badlogic.gdx.scenes.scene2d.ui.Image>(500){
        @Override
        protected com.badlogic.gdx.scenes.scene2d.ui.Image newObject(){
            return new com.badlogic.gdx.scenes.scene2d.ui.Image();
        }
    };
    public static Pool<com.meng.stg.bullets.enemy.EnemyBullet> enemyBulletPool=new Pool<com.meng.stg.bullets.enemy.EnemyBullet>(){
        @Override
        protected com.meng.stg.bullets.enemy.EnemyBullet newObject(){
            return new com.meng.stg.bullets.enemy.EnemyBullet();
        }
    };

    public static Pool<com.meng.stg.dropItems.DropItem> itemPool=new Pool<com.meng.stg.dropItems.DropItem>(){
        @Override
        protected com.meng.stg.dropItems.DropItem newObject(){
            return new com.meng.stg.dropItems.DropItem();
        }
    };

    public static Pool<com.meng.stg.bullets.myPlane.ReimuSpell> reimuBombPool=new Pool<com.meng.stg.bullets.myPlane.ReimuSpell>(){
        @Override
        protected com.meng.stg.bullets.myPlane.ReimuSpell newObject(){
            return new com.meng.stg.bullets.myPlane.ReimuSpell();
        }
    };
    public static Pool<com.meng.stg.bullets.myPlane.ReimuShoot> reimuShootPool=new Pool<com.meng.stg.bullets.myPlane.ReimuShoot>(){
        @Override
        protected com.meng.stg.bullets.myPlane.ReimuShoot newObject(){
            return new com.meng.stg.bullets.myPlane.ReimuShoot();
        }
    };

    public static Pool<com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight>(){
        @Override
        protected com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight newObject(){
            return new com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight();
        }
    };

    public static Pool<com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce> reimuSubPlaneBulletInducePool=new Pool<com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce>(){
        @Override
        protected com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce newObject(){
            return new com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce();
        }
    };

    public static Pool<com.meng.stg.effects.Effect> effectPool=new Pool<com.meng.stg.effects.Effect>(){
        @Override
        protected com.meng.stg.effects.Effect newObject(){
            return new com.meng.stg.effects.Effect();
        }
    };
}