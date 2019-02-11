package com.meng.stg.effects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class Effect extends BaseGameObject{

    public static HashSet<Effect> instances=new HashSet<Effect>();
    public static LinkedBlockingQueue<Effect> toDelete=new LinkedBlockingQueue<Effect>();
    public static LinkedBlockingQueue<Effect> toAdd=new LinkedBlockingQueue<Effect>();

    public EffectType effectType;
    private int drawableNumber=0;

    public static void create(Vector2 center,EffectType type){
        ObjectPools.effectPool.obtain().init(center,type);
    }

    public void init(Vector2 center,EffectType type){
        super.init();
        toAdd.add(this);
        existTime=0;
        judgeCircle=new Circle(objectCenter,16);
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        effectType=type;
        image.setSize(16,16);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
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
        drawableNumber=existTime/2;
        image.setDrawable(ResourcesManager.textures.get("effect"+(drawableNumber+540)));
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(Effect item : instances){
            item.update();
        }
    }

    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("effect540");
        }
        return drawable;
    }
}
