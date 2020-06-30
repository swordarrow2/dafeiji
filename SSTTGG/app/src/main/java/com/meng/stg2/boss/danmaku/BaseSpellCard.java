package com.meng.stg2.boss.danmaku;

import com.meng.stg2.boss.BaseBossPlane;
import com.meng.stg2.task.TaskManager;
import com.meng.stg2.bullets.enemy.BulletShooter;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public String spellName="";
    public BulletShooter[] shooters;
    public int frame=0;
    public TaskManager tm;

    public int waitFrameSpell=60;

    public abstract void init(BaseBossPlane b);

    public void update(){
        if(waitFrameSpell-->0)
            return;
        frame++;
    }
}
