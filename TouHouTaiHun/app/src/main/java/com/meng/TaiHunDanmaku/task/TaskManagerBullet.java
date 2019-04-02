package com.meng.TaiHunDanmaku.task;

import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.bullets.BaseEnemyBullet;
import com.meng.TaiHunDanmaku.planes.myPlane.BaseMyPlane;

import java.util.HashMap;

public class TaskManagerBullet {
    private TaskRepeatMode repeatMode;
    private BaseEnemyBullet bullet = null;
    private int holdingTime = 0;
    private int addTaskFlag = 0;
    private int getTaskFlag = 0;
    private HashMap<Integer, Task> tasks;

    public TaskManagerBullet(BaseEnemyBullet bullet, TaskRepeatMode repeatMode) {
        this.repeatMode = repeatMode;
        this.bullet = bullet;
        tasks = new HashMap<Integer, Task>();
    }

    public void addTask(Task t) {
        tasks.put(addTaskFlag, t);
        addTaskFlag++;
    }

    public void update() {
        switch (repeatMode) {
            case repeatAll:
                if (getTaskFlag < addTaskFlag) {
                    if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
                        doTask(tasks.get(getTaskFlag));
                    } else {
                        holdingTime = 0;
                        getTaskFlag++;
                    }
                } else {
                    holdingTime = 0;
                    getTaskFlag = 0;
                }
                break;
            case repeatLast:
                if (getTaskFlag < addTaskFlag) {
                    if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
                        doTask(tasks.get(getTaskFlag));
                    } else {
                        holdingTime = 0;
                        getTaskFlag++;
                    }
                } else {
                    holdingTime = 0;
                    getTaskFlag = addTaskFlag - 1;
                }
                break;
            case noRepeat:
                if (getTaskFlag < addTaskFlag) {
                    if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
                        doTask(tasks.get(getTaskFlag));
                    } else {
                        holdingTime = 0;
                        getTaskFlag++;
                    }
                }
                break;
        }


    }

    public void doTask(Task task) {
        if (task instanceof TaskMoveTo) {
            if (task.tmpVector2.x == 10000f && task.tmpVector2.y == 10000f) {
                Vector2 vector2 = new Vector2(BaseMyPlane.instance.objectCenter.x - bullet.objectCenter.x, BaseMyPlane.instance.objectCenter.y - bullet.objectCenter.y).nor();
                vector2.scl(bullet.velocity.len());
                bullet.velocity.set(vector2);
            } else {
                Vector2 vector2 = new Vector2(task.tmpVector2.x - bullet.objectCenter.x, task.tmpVector2.y - bullet.objectCenter.y).nor();
                vector2.scl(bullet.velocity.len());
                bullet.velocity.set(vector2);
            }
        } else if (task instanceof TaskChangeAcceleration) {
            bullet.acceleration.set(task.tmpVector2);
        } else if (task instanceof TaskMoveBy) {
            bullet.velocity.set(task.tmpVector2);
        }
        holdingTime++;
    }
}
