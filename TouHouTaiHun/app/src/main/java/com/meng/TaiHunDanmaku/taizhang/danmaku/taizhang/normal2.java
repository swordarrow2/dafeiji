package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.BulletColor;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.BulletForm;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.BulletShooter;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.BulletStyle;
import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseNormalDanmaku;
import com.meng.TaiHunDanmaku.task.ChangeMode;
import com.meng.TaiHunDanmaku.task.Task;
import com.meng.TaiHunDanmaku.task.TaskChangeAcceleration;
import com.meng.TaiHunDanmaku.task.TaskManagerEnemyPlane;
import com.meng.TaiHunDanmaku.task.TaskMoveTo;
import com.meng.TaiHunDanmaku.task.TaskRepeatMode;
import com.meng.TaiHunDanmaku.task.TaskShoot;
import com.meng.TaiHunDanmaku.task.TaskWait;

import java.util.ArrayList;

public class normal2 extends BaseNormalDanmaku {
    private TaskManagerEnemyPlane taskManager;

    public void init(BaseBossPlane baseBossPlane) {
        boss = baseBossPlane;
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(new TaskWait(100));
        //  arrayList.add(new TaskMoveTo(10001, 10001));
        arrayList.add(new TaskChangeAcceleration(-4f, 0, ChangeMode.scl));
        arrayList.add(new TaskWait(30));
        arrayList.add(new TaskChangeAcceleration(0.3f, 0, ChangeMode.scl));
        shooters = new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.huanyu)
                        .setBulletWays(112)
                        .setBulletVelocity(new Vector2(0, -1))
                        .setBulletStyle(BulletStyle.round)
                        .setBulletHighLight(true)
                        .setBulletLiveOutOfScreen(500)
                        .setBulletAcceleration(new Vector2(0, -0.05f))
                        .setBulletTasks(arrayList)
        };
        taskManager = new TaskManagerEnemyPlane(baseBossPlane, TaskRepeatMode.repeatAll);
        taskManager.addTask(new TaskShoot(shooters));
        taskManager.addTask(new TaskWait(60));
        taskManager.addTask(new TaskMoveTo(10000, 10000));
        taskManager.addTask(new TaskWait(60));
    }

    @Override
    public void update() {
        super.update();
        taskManager.update();
        frame++;
        //   laser.degrees = frame * 0.3f;
    }

}
