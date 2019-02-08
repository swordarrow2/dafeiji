package com.meng.stg.bulletRemover;


import com.badlogic.gdx.math.*;
import com.meng.stg.*;
import com.meng.stg.bullets.*;
import com.meng.stg.effects.enemy.*;
import java.util.*;
import java.util.concurrent.*;

import static com.meng.stg.ui.MainScreen.enemys;

public  class BaseRemover extends BaseGameObject{

	public static HashSet<BaseRemover> instances=new HashSet<BaseRemover>();
    public static LinkedBlockingQueue<BaseRemover> toDelete=new LinkedBlockingQueue<BaseRemover>();
    public static LinkedBlockingQueue<BaseRemover> toAdd=new LinkedBlockingQueue<BaseRemover>();

	public void init(Vector2 center){
        existTime=0;
        toAdd.add(this);
        objectCenter.set(center);
        velocity.set(0,0);
        judgeCircle=new Circle(objectCenter,1); //中心、半径
	  }

    public void kill(){
		toDelete.add(this);
	  }

    public void update(){
        super.update();
		judgeCircle.setRadius(existTime*7);
		if(existTime>90){
		  kill();
		}
		judge();
	  }

	public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
		  }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
		  }
        for(BaseRemover baseBullet : instances){
            baseBullet.update();
		  }
	  }

    public void judge(){
        for(BaseEnemyBullet baseBullet : BaseEnemyBullet.instances){
            if(judgeCircle.contains(baseBullet.objectCenter)){
				Effect.create(baseBullet.objectCenter.cpy(),EffectType.explore);	
                baseBullet.kill();
			  }
		  }
	  }

    public Shape2D getCollisionArea(){
        return judgeCircle;
	  }



  }
