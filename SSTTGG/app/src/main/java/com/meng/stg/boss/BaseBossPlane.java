package com.meng.stg.boss;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.boss.danmaku.BaseSpellCard;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.*;
import com.meng.stg.boss.bossTask.*;

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
