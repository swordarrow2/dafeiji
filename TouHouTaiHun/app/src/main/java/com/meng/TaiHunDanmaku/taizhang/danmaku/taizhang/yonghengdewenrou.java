package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseSpellCard;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.badlogic.gdx.math.*;

public class yonghengdewenrou extends BaseSpellCard {

    private float ro = 0;

    public void init(BaseBossPlane b) {
        spellName = "台符「永恒的温柔」";
        boss = b;
        taskManagerEnemyPlane = new TaskManagerEnemyPlane(b, TaskRepeatMode.repeatLast);
        waitFrameSpell = 120;
        shooters = new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.diandan)
                        .setBulletWays(15)
                        .setBulletWaysDegree(24)
                        .setBulletVelocity(new Vector2(0, -10))
                ,
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.blue)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(10)
                        .setBulletWaysDegree(36)
                        .setBulletVelocity(new Vector2(0, -7))

        };
        taskManagerEnemyPlane.addTask(new TaskRunnable(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < shooters.length; ++i) {
                    shooters[i].shoot();
                    shooters[i].bulletVelocity.setAngle(ro);
                    ro = ObjectPools.randomPool.nextFloat() * 360;
                }
            }
        }));
    }

    @Override
    public void update() {
        boss.moveTo(193, 350);
        if (waitFrameSpell > 0) {
            waitFrameSpell--;
            return;
        }
        frame++;
        taskManagerEnemyPlane.update();
    }

}

