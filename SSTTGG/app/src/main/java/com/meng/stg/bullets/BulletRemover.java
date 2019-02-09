package com.meng.stg.bullets;


import com.badlogic.gdx.math.*;
import com.meng.stg.*;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.effects.enemy.*;
import java.util.*;
import java.util.concurrent.*;

public  class BulletRemover extends BaseGameObject{

	public static HashSet<BulletRemover> instances=new HashSet<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toDelete=new LinkedBlockingQueue<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toAdd=new LinkedBlockingQueue<BulletRemover>();

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
        for(BulletRemover baseBullet : instances){
            baseBullet.update();
		  }
	  }

    public void judge(){
        for(BaseEnemyBullet baseBullet : BaseEnemyBullet.instances){
            if(judgeCircle.contains(baseBullet.objectCenter)){
				Effect.create(baseBullet.objectCenter.cpy(),EffectType.explore);	
                baseBullet.kill(BulletKillMode.KillOnBossLastDeath);
			  }
		  }
	  }
  }
