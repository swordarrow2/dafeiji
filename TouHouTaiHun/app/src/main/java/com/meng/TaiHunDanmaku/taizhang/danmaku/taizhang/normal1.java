package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.badlogic.gdx.math.*;

import java.util.ArrayList;

public class normal1 extends BaseNormalDanmaku {
    private TaskManagerEnemyPlane taskManager;
    public Laser laser;

    public void init(BaseBossPlane baseBossPlane) {
        boss = baseBossPlane;
        laser = new Laser(new Sprite(new Texture(Gdx.files.internal("textures/beamstart1.png"))),
                new Sprite(new Texture(Gdx.files.internal("textures/beamstart2.png"))),
                new Sprite(new Texture(Gdx.files.internal("textures/beammid1.png"))),
                new Sprite(new Texture(Gdx.files.internal("textures/beammid2.png"))),
                new Sprite(new Texture(Gdx.files.internal("textures/beamend1.png"))),
                new Sprite(new Texture(Gdx.files.internal("textures/beamend2.png"))));
        laser.color = Color.GREEN;
        laser.position.set(boss.objectCenter);
        laser.degrees = 180;
        laser.distance = 190;
        MainScreen.lasers.add(laser);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(new TaskWait(30));
        arrayList.add(new TaskMoveTo(10000, 10000));
        shooters = new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.huanyu)
                        .setBulletWays(112)
                        .setBulletVelocity(new Vector2(0, -2))
                        .setBulletStyle(BulletStyle.round)
                        .setBulletHighLight(true)
                        .setBulletLiveOutOfScreen(60)
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
        laser.degrees = frame * 0.3f;
    }

}
