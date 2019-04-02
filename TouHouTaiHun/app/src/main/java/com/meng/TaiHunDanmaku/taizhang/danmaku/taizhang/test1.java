package com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang;

import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;
import com.meng.TaiHunDanmaku.task.TaskManagerBullet;
import com.meng.TaiHunDanmaku.task.TaskManagerEnemyPlane;
import com.meng.TaiHunDanmaku.task.TaskRepeatMode;
import com.meng.TaiHunDanmaku.task.TaskMoveTo;
import com.meng.TaiHunDanmaku.task.TaskShoot;
import com.meng.TaiHunDanmaku.task.TaskWait;
import com.meng.TaiHunDanmaku.taizhang.danmaku.BaseNormalDanmaku;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletColor;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletForm;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletShooter;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletStyle;
import com.badlogic.gdx.math.*;

public class test1 extends BaseNormalDanmaku{
    TaskManagerEnemyPlane tm;

    public void init(BaseBossPlane b){
        boss=b;
        tm=new TaskManagerEnemyPlane(b,TaskRepeatMode.repeatAll) ;
        shooters=new BulletShooter[]{
                new BulletShooter().init()
                        .setEnemyPlane(boss)
                        .setShooterCenter(boss.objectCenter)
                        .setBulletColor(BulletColor.red)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setBulletWays(1)
                        .setBulletStyle(BulletStyle.snipe)
                        .setBulletWaysDegree(3.75f)
                        .setBulletVelocity(new Vector2(0,-5))
        };
        tm.addTask(new TaskShoot(shooters));
        tm.addTask(new TaskWait(60));
        tm.addTask(new TaskMoveTo(10000,10000));
        tm.addTask(new TaskWait(60));
    }

    @Override
    public void update(){
        super.update();
        tm.update();
        frame++;
    }

}
