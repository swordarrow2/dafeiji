package com.meng.TaiHunDanmaku.taizhang.danmaku;

import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.BulletShooter;
import com.meng.TaiHunDanmaku.task.TaskManagerBullet;
import com.meng.TaiHunDanmaku.task.TaskManagerEnemyPlane;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public String spellName="";
    public BulletShooter[] shooters;
    public int frame=0;
    public TaskManagerEnemyPlane taskManagerEnemyPlane;

    public int waitFrameSpell=60;

    public abstract void init(BaseBossPlane b);

    public void update(){
        if(waitFrameSpell-->0)
            return;
        frame++;
    }
}
