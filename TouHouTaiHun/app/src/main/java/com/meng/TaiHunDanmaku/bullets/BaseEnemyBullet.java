package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.TaiHunDanmaku.boss.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.effects.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

import java.util.*;
import java.util.concurrent.*;

public abstract class BaseEnemyBullet extends BaseBullet {

    public static HashSet<BaseEnemyBullet> instances = new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete = new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd = new LinkedBlockingQueue<BaseEnemyBullet>();

    public int refCount = 0;
    public int thoughCount = 0;

    public Vector2 targetPosition = new Vector2();

    public abstract Drawable getDrawable();

    public TaskManager taskManager;

    public float bulletSpeed = 1;

    public void init() {
        super.init();
        toAdd.add(this);
        taskManager = new TaskManager(this, TaskRepeatMode.noRepeat);
    }

    public void moveTo(float x, float y) {
        targetPosition.x = x;
        targetPosition.y = y;
    }

    @Override
    public void kill() {
        super.kill();
        toDelete.add(this);
        image.remove();
        //	ObjectPools.imagePool.free(image);
    }

    public void kill(BulletKillMode bkm) {
        super.kill();
        toDelete.add(this);
        image.remove();
        Effect.create(objectCenter.cpy(), EffectType.explore);
        switch (bkm) {
            case killWithNothing:
                break;
            case killWithScorePoint:
                DropItem.create(objectCenter.cpy(), DropItemType.highScoreSmall, bkm);
                break;
            case killWithScorePointAndCollect:
                DropItem.create(objectCenter.cpy(), DropItemType.highScoreSmall, bkm);
                break;
            case KillOnBossLastDeath:
                DropItem.create(objectCenter.cpy(), DropItemType.highScoreMediam, bkm);
                break;
            case killOnPlayerDeath:
                break;
        }
    }

    public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
        }
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
        }
        for (BaseEnemyBullet baseBullet : instances) {
            baseBullet.update();
        }
    }

    @Override
    public void update() {
        super.update();
        taskManager.update();
        if (objectCenter.cpy().sub(targetPosition).len2() > 10) {
            velocity = targetPosition.cpy().sub(objectCenter).nor().scl(bulletSpeed);
            objectCenter.add(velocity);
        }
        if (refCount > 0) {
            if (objectCenter.x <= 0) {
                velocity.x = -velocity.x;
                objectCenter.x = 1;
                refCount--;
            }
            if (objectCenter.x >= MainScreen.fightArea.width) {
                velocity.x = -velocity.x;
                objectCenter.x = MainScreen.fightArea.width - 1;
                refCount--;
            }
            if (objectCenter.y <= 0) {
                velocity.y = -velocity.y;
                objectCenter.y = 1;
                refCount--;
            }
            if (objectCenter.y >= MainScreen.fightArea.height) {
                velocity.y = -velocity.y;
                objectCenter.y = MainScreen.fightArea.height - 1;
                refCount--;
            }
        } else if (thoughCount > 0) {
            if (objectCenter.x <= 0) {
                objectCenter.x = MainScreen.fightArea.width - 1;
                thoughCount--;
            }
            if (objectCenter.x >= MainScreen.fightArea.width) {
                objectCenter.x = 1;
                thoughCount--;
            }
            if (objectCenter.y <= 0) {
                objectCenter.y = MainScreen.fightArea.height - 1;
                thoughCount--;
            }
            if (objectCenter.y >= MainScreen.fightArea.height) {
                objectCenter.y = 1;
                thoughCount--;
            }
        }
    }

    public static void killAllBullet(BulletKillMode bkm) {
        switch (bkm) {
            case KillOnBossLastDeath:
                new BulletRemover().init(BaseBossPlane.instence.objectCenter.cpy());
                break;
            default:
                new BulletRemover().init();
                break;
        }

    }

    @Override
    public Shape2D getCollisionArea() {
        return judgeCircle;
		/*
		Rectangle r=new Rectangle();
		r.setSize(164,18);

		r.setPosition(objectCenter.x-164/2,objectCenter.y-18/2);
		return r ;
		*/
    }
 
    @Override
    public void judge() {

    }

    @Override
    public float getRotationDegree() {
        return velocity.angle() + 270;
    }
}
