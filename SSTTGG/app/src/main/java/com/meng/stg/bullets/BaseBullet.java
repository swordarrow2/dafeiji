package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Pools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public abstract class BaseBullet{
    public Vector2 Center=new Vector2();
    public Vector2 Velocity=new Vector2();
    public Vector2 size=new Vector2();
    public Image Drawer=null;
    public int ExistTime;
    public static HashSet<BaseBullet> Instances=new HashSet<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> ToDelete=new LinkedBlockingQueue<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> ToAdd=new LinkedBlockingQueue<BaseBullet>();
    public Circle judgeCircle;
    protected Rectangle drawBox=new Rectangle();

    public Drawable drawable=null;

    public void Init(){
        ToAdd.add(this);
        size=getSize();
        Drawer=Pools.ImagePool.obtain();
        Drawable da=getDrawable();
		
        Drawer.setDrawable(da);
        Drawer.setSize(size.x,size.y);
		Drawer.setRotation(getRotationDegree());
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        ExistTime=0;
    }

    public void Kill(){
        ToDelete.add(this);
        Drawer.setRotation(0);
        Drawer.remove();
        Pools.ImagePool.free(Drawer);
    }

    public void Update(){
        Center.add(Velocity);
		Drawer.setRotation(getRotationDegree());
        Drawer.setPosition(Center.x,Center.y);
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
		
        ExistTime++;
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
        judgeCircle.setPosition(Center);
        if(!drawBox.overlaps(MainScreen.FightArea)){
            Kill();
        }else{
            Judge();
        }
    }

    public static void killAllBullet(){
        Iterator i=Instances.iterator();
        while(i.hasNext()){
            ((BaseBullet)i.next()).Kill();
        }
    }


    public static void UpdateAll(){
        while(!ToDelete.isEmpty()){
            Instances.remove(ToDelete.poll());
        }
        while(!ToAdd.isEmpty()){
            Instances.add(ToAdd.poll());
        }
        for(BaseBullet baseBullet : Instances){
            baseBullet.Update();
        }
    }

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public abstract Drawable getDrawable();

    public abstract void Judge();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();
}
