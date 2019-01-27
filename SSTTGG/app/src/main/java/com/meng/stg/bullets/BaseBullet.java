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

	public int refCount=5;
	public int thoughCount=5;

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
        ExistTime++;
        Center.add(Velocity);
		Drawer.setRotation(getRotationDegree());
        Drawer.setPosition(Center.x,Center.y,Align.center);
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
        judgeCircle.setPosition(Center);

		if(refCount>0){
			if(Center.x<=0){
				Velocity.x=-Velocity.x;
				Center.x=1;
				refCount--;
			  }
			if(Center.x>=MainScreen.FightArea.width){
				Velocity.x=-Velocity.x;
				Center.x=MainScreen.FightArea.width-1;
				refCount--;
			  }
			if(Center.y<=0){
				Velocity.y=-Velocity.y;
				Center.y=1;
				refCount--;
			  }
			if(Center.y>=MainScreen.FightArea.height){
				Velocity.y=-Velocity.y;			
				Center.y=MainScreen.FightArea.height-1;
				refCount--;
			  }
		  }else if(thoughCount>0){		
			if(Center.x<=0){
				Center.x=MainScreen.FightArea.width-1;
				thoughCount--;
			  }
			if(Center.x>=MainScreen.FightArea.width){	  
				Center.x=1;
				thoughCount--;
			  }
			if(Center.y<=0){ 
				Center.y=MainScreen.FightArea.height-1;
				thoughCount--;
			  }
			if(Center.y>=MainScreen.FightArea.height){
				Center.y=1;
				thoughCount--;
			  }
		  }

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
