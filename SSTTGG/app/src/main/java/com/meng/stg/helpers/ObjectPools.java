package com.meng.stg.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.bullets.myPlane.ReimuBomb;
import com.meng.stg.bullets.myPlane.ReimuShoot;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight;

public final class ObjectPools{
    public static Pool<Image> imagePool=new Pool<Image>(500){
        @Override
        protected Image newObject(){
            return new Image();
        }
    };
    public static Pool<EnemyBullet> enemyBulletPool=new Pool<EnemyBullet>(){
        @Override
        protected EnemyBullet newObject(){
            return new EnemyBullet();
        }
    };

    public static Pool<ReimuBomb> reimuBombPool=new Pool<ReimuBomb>(){
        @Override
        protected ReimuBomb newObject(){
            return new ReimuBomb();
        }
    };
    public static Pool<ReimuShoot> reimuShootPool=new Pool<ReimuShoot>(){
        @Override
        protected ReimuShoot newObject(){
            return new ReimuShoot();
        }
    };

    public static com.badlogic.gdx.utils.Pool<ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<ReimuSubPlaneBulletStraight>(){
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
