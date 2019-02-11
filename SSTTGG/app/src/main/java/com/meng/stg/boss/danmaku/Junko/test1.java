package com.meng.stg.boss.danmaku.Junko;

import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.task.TaskManager;
import com.meng.stg.task.TaskMode;
import com.meng.stg.task.TaskMove;
import com.meng.stg.task.TaskShoot;
import com.meng.stg.task.TaskWait;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.BulletStyle;

public class test1 extends BaseNormalDanmaku{
    TaskManager tm;

    public void init(BaseBossPlane b){
        boss=b;
        tm=new TaskManager(b,TaskMode.repeatAll);
        shooters=new BulletShooter[]{
                new BulletShooter().init()
                        .setBaseEnemyPlane(boss)
                        .setBulletCenter(boss.objectCenter)
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
