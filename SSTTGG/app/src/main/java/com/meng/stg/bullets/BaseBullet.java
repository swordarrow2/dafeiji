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
import com.meng.stg.player.BaseMyPlane;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseBullet{
    public Vector2 Center=new Vector2();
    public Vector2 Velocity=new Vector2();
    public Image Drawer=null;
    public int ExistTime;
    public static HashSet<BaseBullet> Instances=new HashSet<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> ToDelete=new LinkedBlockingQueue<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> ToAdd=new LinkedBlockingQueue<BaseBullet>();
    public Circle judgeCircle;
    protected Rectangle drawBox=new Rectangle();

    public Drawable drawable;
    public Vector2 Size=new Vector2();

    public void Init(){
        ToAdd.add(this);
        Drawer=Pools.ImagePool.obtain();
        Drawer.setDrawable(getDrawable());
        ExistTime=0;
    }

    public void Kill(){
        ToDelete.add(this);
        Drawer.remove();
        Pools.ImagePool.free(Drawer);
    }

    public void Update(){
        Center.add(Velocity);
        Drawer.setPosition(Center.x,Center.y,Align.center);
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
            bullet.bulletCount--;
        }
        while(!ToAdd.isEmpty()){
            Instances.add(ToAdd.poll());
            bullet.bulletCount++;
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
}
