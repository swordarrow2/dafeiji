package com.meng.stg.boss;

import com.meng.stg.bullets.enemy.BulletShooter;

public abstract class BaseSpellCard{

    public BaseBossPlane boss;
    public int spellTime=1000;
    public BulletShooter[] shooters;
    public float partHp[];
    public int frame=0;
    public int partFrame[];

    public abstract void init(BaseBossPlane boss);

    public void update(){
        for(int i=0;i<shooters.length;++i){
            if(boss.hp>partHp[i]&&frame<partFrame[i]){
                continue;
            }
            shooters[i].shoot();
        }
        frame++;
    }
}
