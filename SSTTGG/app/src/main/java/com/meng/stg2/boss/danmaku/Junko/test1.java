package com.meng.stg2.boss.danmaku.Junko;

import com.meng.stg2.boss.BaseBossPlane;
import com.meng.stg2.task.TaskManager;
import com.meng.stg2.task.TaskRepeatMode;
import com.meng.stg2.task.TaskMove;
import com.meng.stg2.task.TaskShoot;
import com.meng.stg2.task.TaskWait;
import com.meng.stg2.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg2.bullets.enemy.BulletColor;
import com.meng.stg2.bullets.enemy.BulletForm;
import com.meng.stg2.bullets.enemy.BulletShooter;
import com.meng.stg2.bullets.enemy.BulletStyle;

public class test1 extends BaseNormalDanmaku{
    TaskManager tm;

    public void init(BaseBossPlane b){
        boss=b;
        tm=new TaskManager(b,TaskRepeatMode.repeatAll);
        shooters=new BulletShooter[]{
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.position)
                        .setBulletColor(BulletColor.red)
                        .setBulletForm(BulletForm.xiaoyu)
                        .setWays(1)
                        .setBulletStyle(BulletStyle.snipe)
                        .setWaysDegree(3.75f)
                        .setBulletSpeed(5)
        };
        tm.addTask(new TaskShoot(shooters));
        tm.addTask(new TaskWait(60));
        tm.addTask(new TaskMove(10000,10000));
        tm.addTask(new TaskWait(60));
    }

    @Override
    public void update(){
        super.update();
        tm.update();
        frame++;
    }

}
