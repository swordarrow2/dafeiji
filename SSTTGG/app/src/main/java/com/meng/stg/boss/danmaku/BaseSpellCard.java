package com.meng.stg.boss.danmaku;

import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.bullets.enemy.BulletShooter;

public class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public String spellName="";
    public BulletShooter[] spellShooters;
    public int frame=0;

    public int waitFrameSpell=60;

    public void init(){

    }

    public void update(){
        if(waitFrameSpell-->0)
            return;
        frame++;
    }
}
