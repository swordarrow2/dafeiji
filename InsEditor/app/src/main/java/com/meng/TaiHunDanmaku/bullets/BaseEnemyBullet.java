package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.task.*;
import java.util.*;
import java.util.concurrent.*;

public abstract class BaseEnemyBullet extends BaseBullet {

    public static HashSet<BaseEnemyBullet> instances = new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete = new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd = new LinkedBlockingQueue<BaseEnemyBullet>();

    public int reflexCount = 0;
    public int reflexTopCount = 0;
    public int reflexBottomCount = 0;
    public int reflexLeftCount = 0;
    public int reflexRightCount = 0;
    public int throughCount = 0;
    public int throughTopCount = 0;
    public int throughBottomCount = 0;
    public int throughLeftCount = 0;
    public int throughRightCount = 0;
    public int colorNum = 0;
    public int formNum = 0;
    public int liveOutOfScreen = 0;
    public int bulletLife = 7200;

    public Vector2 acceleration = new Vector2();

    public abstract Drawable getDrawable();

    public TaskManagerBullet taskManager;

    public void init() {
        super.init();
        toAdd.add(this);
        taskManager = new TaskManagerBullet(this, TaskRepeatMode.noRepeat);
    }

    public abstract void killByJudge();

    public void killByJudge(BulletKillMode bkm) {
        super.killByOutOfScreen();
        toDelete.add(this);
        image.remove();        
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
        if (taskManager != null) {
            taskManager.update();
        }
        velocity.add(acceleration);
        objectCenter.add(velocity);
    }

    @Override
    public Shape2D getCollisionArea() {
        return judgeCircle;
    }

    @Override
    public void judge() {

    }

    @Override
    public float getRotationDegree() {
        return velocity.angle() + 270;
    }
}
