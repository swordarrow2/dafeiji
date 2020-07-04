package com.meng.stg2.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.bullets.myPlane.*;
import com.meng.stg2.bullets.subPlane.*;
import com.meng.stg2.dropItems.*;
import com.meng.stg2.effects.*;

public final class ObjectPools {
//    private static Pool<Image> imagePool=new Pool<Image>(512){
//        @Override
//        protected Image newObject(){
//            return new Image();
//        }
//    };

    private static Pool<EnemyBullet> enemyBulletPool=new Pool<EnemyBullet>(512){
        @Override
        protected EnemyBullet newObject() {
            return new EnemyBullet();
        }
    };

    private static Pool<DropItem> itemPool=new Pool<DropItem>(512){
        @Override
        protected DropItem newObject() {
            return new DropItem();
        }
    };

    private static Pool<ReimuSpell> reimuBombPool=new Pool<ReimuSpell>(64){
        @Override
        protected ReimuSpell newObject() {
            return new ReimuSpell();
        }
    };

    private static Pool<ReimuShoot> reimuShootPool=new Pool<ReimuShoot>(64){
        @Override
        protected ReimuShoot newObject() {
            return new ReimuShoot();
        }
    };

    private static Pool<ReimuSubPlaneBulletStraight> reimuSubPlaneBulletStraightPool=new Pool<ReimuSubPlaneBulletStraight>(64){
        @Override
        protected ReimuSubPlaneBulletStraight newObject() {
            return new ReimuSubPlaneBulletStraight();
        }
    };

    private static Pool<ReimuSubPlaneBulletInduce> reimuSubPlaneBulletInducePool=new Pool<ReimuSubPlaneBulletInduce>(64){
        @Override
        protected ReimuSubPlaneBulletInduce newObject() {
            return new ReimuSubPlaneBulletInduce();
        }
    };

    private static Pool<Effect> effectPool=new Pool<Effect>(512){
        @Override
        protected Effect newObject() {
            return new Effect();
        }
    };

	public static EnemyBullet getEnemyBullet() {
		return enemyBulletPool.obtain();
	}

	public static DropItem getDropItem() {
		return itemPool.obtain();
	}

	public static ReimuSpell getReimuSpell() {
		return reimuBombPool.obtain();
	}

	public static ReimuShoot getReimuShoot() {
		return reimuShootPool.obtain();
	}

	public static ReimuSubPlaneBulletStraight getReimuSubPlaneBulletStraight() {
		return reimuSubPlaneBulletStraightPool.obtain();
	}

	public static ReimuSubPlaneBulletInduce getReimuSubPlaneBulletInduce() {
		return reimuSubPlaneBulletInducePool.obtain();
	}

	public static Effect getEffect() {
		return effectPool.obtain();
	}

	public static void recycle(EnemyBullet eb) {
		enemyBulletPool.free(eb);
	}

	public static void recycle(DropItem di) {
		itemPool.free(di);
	}
	
	public static void recycle(ReimuSpell rs) {
		reimuBombPool.free(rs);
	}

	public static void recycle(ReimuShoot rs) {
		reimuShootPool.free(rs);
	}

	public static void recycle(ReimuSubPlaneBulletStraight rsbs) {
		reimuSubPlaneBulletStraightPool.free(rsbs);
	}

	public static void recycle(ReimuSubPlaneBulletInduce rsbi) {
		reimuSubPlaneBulletInducePool.free(rsbi);
	}

}
