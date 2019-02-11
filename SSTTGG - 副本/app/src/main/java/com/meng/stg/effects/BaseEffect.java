package com.meng.stg.effects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.effects.enemy.EffectType;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseEffect extends BaseGameObject{

    public static HashSet<BaseEffect> instances=new HashSet<BaseEffect>();
    public static LinkedBlockingQueue<BaseEffect> toDelete=new LinkedBlockingQueue<BaseEffect>();
    public static LinkedBlockingQueue<BaseEffect> toAdd=new LinkedBlockingQueue<BaseEffect>();

    public EffectType effectType;

    public void init(){
        super.init();
        toAdd.add(this);
        existTime=0;
        judgeCircle=new Circle(objectCenter,16);
        moveManager=new MoveManager(this,new MoveMethodStraight(90,1,new Vector2(0,-1)));
    }

    public void kill(){
        toDelete.add(this);
        image.remove();
        //	super.kill();
    }

    public void update(){
        super.update();
        if(existTime>40||judgeCircle.x<-5||judgeCircle.x>390||judgeCircle.y<-5||judgeCircle.y>460){
            kill();
        }
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseEffect item : instances){
            item.update();
        }
    }

    public abstract Drawable getDrawable();

    public abstract Vector2 getSize();
}
