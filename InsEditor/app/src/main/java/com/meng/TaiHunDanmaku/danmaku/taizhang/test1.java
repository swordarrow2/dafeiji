package com.meng.TaiHunDanmaku.danmaku.taizhang;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;

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
