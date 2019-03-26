package com.meng.TaiHunDanmaku.boss.danmaku.Junko;

import com.meng.TaiHunDanmaku.boss.BaseBossPlane;
import com.meng.TaiHunDanmaku.task.TaskManager;
import com.meng.TaiHunDanmaku.task.TaskRepeatMode;
import com.meng.TaiHunDanmaku.task.TaskMove;
import com.meng.TaiHunDanmaku.task.TaskShoot;
import com.meng.TaiHunDanmaku.task.TaskWait;
import com.meng.TaiHunDanmaku.boss.danmaku.BaseNormalDanmaku;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletColor;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletForm;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletShooter;
import com.meng.TaiHunDanmaku.bullets.enemy.BulletStyle;

public class test1 extends BaseNormalDanmaku{
    TaskManager tm;

    public void init(BaseBossPlane b){
        boss=b;
        tm=new TaskManager(b,TaskRepeatMode.repeatAll);
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
