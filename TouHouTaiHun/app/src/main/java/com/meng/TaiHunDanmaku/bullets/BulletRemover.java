package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.effects.EffectType;

import java.util.*;
import java.util.concurrent.*;
import com.meng.TaiHunDanmaku.effects.*;

public  class BulletRemover extends BaseGameObject{

	public static HashSet<BulletRemover> instances=new HashSet<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toDelete=new LinkedBlockingQueue<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toAdd=new LinkedBlockingQueue<BulletRemover>();
	private BulletKillMode bkm;

	private int flag=0;
	public void init(Vector2 center){
        existTime=0;
		flag=0;
        toAdd.add(this);
        objectCenter.set(center);
        velocity.set(0,0);
        judgeCircle=new Circle(objectCenter,1);
		bkm=BulletKillMode.KillOnBossLastDeath;
	  }

    public void killByJudge(){
		toDelete.add(this);
	  }

	public void init(){
		flag=1;
		toAdd.add(this);
        objectCenter.set(193,225);
        velocity.set(0,0);
        judgeCircle=new Circle(objectCenter,500);
		bkm=BulletKillMode.killWithNothing;
	  }

    public void update(){
        super.update();
		if(flag==0){
			judgeCircle.setRadius(existTime*7);
			if(existTime>90){
				killByJudge();
			  }
		  }else{
			if(existTime>3){
				killByJudge();
			  }
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
                baseBullet.killByJudge(bkm);
			  }
		  }
	  }
  }
