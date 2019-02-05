package com.meng.stg.item;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.ObjectPools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import com.meng.stg.bullets.enemy.*;

public abstract class BaseItem extends BaseGameObject{

    public static HashSet<BaseItem> instances=new HashSet<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toDelete=new LinkedBlockingQueue<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toAdd=new LinkedBlockingQueue<BaseItem>();


    public void init(){
		super.init();
        toAdd.add(this);
        size=getSize();
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        existTime=0;
	  }

    public void kill(){	
        toDelete.add(this);
        image.remove();
	//	super.kill();
	  }

    public void update(){
        super.update();
        objectCenter.add(velocity);
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        judgeCircle.setPosition(objectCenter);
        if(judgeCircle.x<-5||judgeCircle.x>390
		   ||judgeCircle.y<-5||judgeCircle.y>460){
			kill();
		  }else{
			judge();
		  }
	  }

    public static void killAllBullet(){
        Iterator i=instances.iterator();
        while(i.hasNext()){
            BaseItem bb=(BaseItem)i.next();
            if(bb instanceof BaseEnemyItem){
                bb.kill();
				--EnemyBullet.bulletCount;
			  }
		  }
		  
	  }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
		  }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
		  }
        for(BaseItem baseBullet : instances){
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
