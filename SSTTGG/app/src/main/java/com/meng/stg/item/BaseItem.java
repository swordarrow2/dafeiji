package com.meng.stg.item;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.move.*;
import com.meng.stg.planes.myPlane.*;
import java.util.*;
import java.util.concurrent.*;
import com.meng.stg.bullets.enemy.*;

public abstract class BaseItem extends BaseGameObject{

    public static HashSet<BaseItem> instances=new HashSet<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toDelete=new LinkedBlockingQueue<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toAdd=new LinkedBlockingQueue<BaseItem>();

	public BulletKillMode bulletKillMode;
	
    public void init(){
		super.init();
		bulletKillMode=BulletKillMode.killWithNothing;
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
		//   objectCenter.add(velocity);
		switch(bulletKillMode){
			case killWithScorePoint:
			  moveManager.update();
			  objectCenter.add(velocity);
			  break;
			case killWithScorePointAndCollect:
			  objectCenter.add(BaseMyPlane.instance.objectCenter.cpy().sub(objectCenter).nor().scl(4f));
			  break;
		  }
        image.setRotation(0);
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

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
		  }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
		  }
        for(BaseItem item : instances){
            item.update();
		  }
	  }

    public Shape2D getCollisionArea(){
        return judgeCircle;
	  }

    public abstract Drawable getDrawable();

    public void judge(){
		Vector2 v=BaseMyPlane.instance.objectCenter.cpy();
		if(Math.abs(judgeCircle.x-v.x)<5&&Math.abs(judgeCircle.y-v.y)<5){
            kill();
			//	BaseMyPlane.instance.incPower(1);
		  }
	  }

    public abstract Vector2 getSize();
  }
