package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class spell1 extends BaseSpellCard {

    private float ro = 0;

    public void init(final BaseBossPlane b) {
        spellName = "台符「495次的真香」";
        boss = b;
        taskManagerEnemyPlane = new TaskManagerEnemyPlane(b, TaskRepeatMode.repeatAll);
        waitFrameSpell = 120;
        FightScreen.instence.reflexAndThroughs.add(new ReflexAndThrough(ReflexAndThrough.Position.Top));
        FightScreen.instence.reflexAndThroughs.add(new ReflexAndThrough(ReflexAndThrough.Position.Left));
        FightScreen.instence.reflexAndThroughs.add(new ReflexAndThrough(ReflexAndThrough.Position.Right));
        shooters = new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.green)
                        .setBulletForm(BulletForm.midan)
                        .setBulletWays(45)
						.setBulletStyle(BulletStyle.round)
                        .setShooterCenterRandomRange(128, 32)
                        .setReflexCount(495, 1000, 0, 1000, 1000)
                        .setBulletVelocity(new Vector2(0, -2))
        };

        taskManagerEnemyPlane.addTask(new TaskRunnable(new Runnable() {

            @Override
            public void run() {
                b.moveTo(193, 350);
            }
        }));
        taskManagerEnemyPlane.addTask(new TaskWait(5));
        taskManagerEnemyPlane.addTask(new TaskRunnable(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < shooters.length; ++i) {
                    shooters[i].shoot();
                    shooters[i].bulletVelocity.rotate(ro);
                    ro += 0.15f;
                }
            }
        }));
        taskManagerEnemyPlane.addTask(new TaskWait(30));
        taskManagerEnemyPlane.addTask(new TaskChangeAcceleration(0, -0.5f, ChangeMode.scl));
    }

    @Override
    public void update() {
        if (Gdx.graphics.getFramesPerSecond() < 58) return;

        if (waitFrameSpell > 0) {
            waitFrameSpell--;
            return;
        }
        frame++;
        taskManagerEnemyPlane.update();
    }

}
