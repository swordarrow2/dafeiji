package com.meng.stg.bullets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.Pools;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseBullet extends BaseGameObject{

    public Vector2 size=new Vector2();
    public static HashSet<BaseBullet> instances=new HashSet<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> toDelete=new LinkedBlockingQueue<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> toAdd=new LinkedBlockingQueue<BaseBullet>();
    protected Rectangle drawBox=new Rectangle();


    public void Init(){
        toAdd.add(this);
        size=getSize();
        image=Pools.imagePool.obtain();
        image.setDrawable(getDrawable());
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        existTime=0;
    }

    public void kill(){
        toDelete.add(this);
        image.setRotation(0);
        image.remove();
        Pools.imagePool.free(image);
    }

    public void update(){
        super.update();
        objectCenter.add(velocity);
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        drawBox.set(image.getX(),image.getY(),image.getWidth(),image.getHeight());
        judgeCircle.setPosition(objectCenter);
        if(!drawBox.overlaps(MainScreen.fightArea)){
            kill();
        }else{
            judge();
        }
    }

    public static void killAllBullet(){
        Iterator i=instances.iterator();
        while(i.hasNext()){
            ((BaseBullet)i.next()).kill();
        }
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseBullet baseBullet : instances){
            baseBullet.update();
        }
    }

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public abstract Drawable getDrawable();

    public abstract void judge();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();
}
