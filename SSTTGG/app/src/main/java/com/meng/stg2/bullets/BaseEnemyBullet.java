package com.meng.stg2.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg2.boss.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.dropItems.*;
import com.meng.stg2.effects.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.task.*;
import com.meng.stg2.ui.*;
import java.util.*;
import java.util.concurrent.*;
import com.meng.stg2.characters.player.*;

public abstract class BaseEnemyBullet extends BaseBullet {

    public static HashSet<BaseEnemyBullet> instances=new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete=new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd=new LinkedBlockingQueue<BaseEnemyBullet>();

    public int refCount=0;
    public int thoughCount=0;

    public Vector2 targetPosition=new Vector2();

    public abstract Drawable getDrawable();

    public TaskManager taskManager;

	public float bulletSpeed=1;

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
        Effect.create(position.cpy(), EffectType.explore);
        switch (bkm) {
            case killWithNothing:
                break;
            case killWithScorePoint:
                DropItem.create(position.cpy(), DropItemType.highScoreSmall, bkm);
                break;
            case killWithScorePointAndCollect:
                DropItem.create(position.cpy(), DropItemType.highScoreSmall, bkm);
                break;
            case KillOnBossLastDeath:
                DropItem.create(position.cpy(), DropItemType.highScoreMediam, bkm);
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
		if (position.cpy().sub(targetPosition).len2() > 10) {
			velocity = targetPosition.cpy().sub(position).nor().scl(bulletSpeed);
            position.add(velocity);
		}
        if (refCount > 0) {
            if (position.x <= 0) {
                velocity.x = -velocity.x;
                position.x = 1;
                refCount--;
            }
            if (position.x >= MainScreen.fightArea.width) {
                velocity.x = -velocity.x;
                position.x = MainScreen.fightArea.width - 1;
                refCount--;
            }
            if (position.y <= 0) {
                velocity.y = -velocity.y;
                position.y = 1;
                refCount--;
            }
            if (position.y >= MainScreen.fightArea.height) {
                velocity.y = -velocity.y;
                position.y = MainScreen.fightArea.height - 1;
                refCount--;
            }
        } else if (thoughCount > 0) {
            if (position.x <= 0) {
                position.x = MainScreen.fightArea.width - 1;
                thoughCount--;
            }
            if (position.x >= MainScreen.fightArea.width) {
                position.x = 1;
                thoughCount--;
            }
            if (position.y <= 0) {
                position.y = MainScreen.fightArea.height - 1;
                thoughCount--;
            }
            if (position.y >= MainScreen.fightArea.height) {
                position.y = 1;
                thoughCount--;
            }
        }
    }

    public static void killAllBullet(BulletKillMode bkm) {
        switch (bkm) {
            case KillOnBossLastDeath:
                new BulletRemover().init(BaseBossPlane.instence.position.cpy());
                break;
            default:
                new BulletRemover().init();
                break;
        }

	}

    @Override
    public void judge() {
		if (judgeCircle.contains(BaseMyPlane.instance.position)) {
			BaseMyPlane.instance.kill();
			kill();
		}
    }

    @Override
    public float getRotationDegree() {
        return velocity.angle() + 270;
    }
}
