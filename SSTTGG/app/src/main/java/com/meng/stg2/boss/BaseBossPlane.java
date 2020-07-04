package com.meng.stg2.boss;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg2.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg2.boss.danmaku.BaseSpellCard;
import com.meng.stg2.characters.enemy.BaseEnemyPlane;
import com.meng.stg2.ui.*;
import com.meng.stg2.task.*;

public abstract class BaseBossPlane extends BaseEnemyPlane{

    public static BaseBossPlane instence;
    public BaseNormalDanmaku normalDanmaku;
    public BaseSpellCard spellCard;
    
    @Override
    public abstract Vector2 getSize();

    @Override
    public void shoot(){
    }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
		MainScreen.instence.bossMaxHp=hp;
        instence=this;
    }

    @Override
    public void kill(){
        super.kill();
    }

}
