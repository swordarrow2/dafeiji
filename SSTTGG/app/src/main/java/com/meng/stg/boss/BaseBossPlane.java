package com.meng.stg.boss;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.danmaku.BaseNormalDanmaku;
import com.meng.stg.boss.danmaku.BaseSpellCard;
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;

public abstract class BaseBossPlane extends BaseEnemyPlane{

    public static BaseBossPlane instence;
    public BaseNormalDanmaku normalDanmaku;
    public BaseSpellCard spellCard;
    public Vector2 targetPosition=new Vector2();

    @Override
    public abstract Vector2 getSize();

    @Override
    public void shoot(){
    }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        instence=this;
    }

    @Override
    public void kill(){
        super.kill();
        BaseEnemyBullet.toAdd.clear();
    }

    public void moveTo(float x,float y){
        targetPosition.x=x;
        targetPosition.y=y;
    }

    @Override
    public void update(){
        super.update();
        if(objectCenter.cpy().sub(targetPosition).len2()>10){
            objectCenter.add(targetPosition.cpy().sub(objectCenter).nor().scl(3f));
        }
    }


}
