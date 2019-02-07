package com.meng.stg.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import java.util.*;
import java.util.concurrent.*;

import com.meng.stg.effects.enemy.Effect;
import com.meng.stg.effects.enemy.EffectType;
import com.meng.stg.item.item.*;

public abstract class BaseBullet extends BaseGameObject{

    public static HashSet<BaseBullet> instances=new HashSet<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> toDelete=new LinkedBlockingQueue<BaseBullet>();
    public static LinkedBlockingQueue<BaseBullet> toAdd=new LinkedBlockingQueue<BaseBullet>();

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
        if(judgeCircle.x<-5||judgeCircle.x>390||judgeCircle.y<-5||judgeCircle.y>460){
			kill();
		  }else{
			judge();
		  }
	  }

    public static void killAllBullet(BulletKillMode bkm){
        Iterator i=instances.iterator();
        while(i.hasNext()){
            BaseBullet bullet=(BaseBullet)i.next();
            if(bullet instanceof BaseEnemyBullet){
				EnemyBullet.bulletCount--;
				if(bkm!=BulletKillMode.killWithNothing){
					EnemyItem.create(bullet.objectCenter.cpy(),ItemType.highScoreMediam,bkm);
				}
                Effect.create(bullet.objectCenter.cpy(),EffectType.explore);
				bullet.kill();
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
