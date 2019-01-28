package com.meng.stg.enemyPlane;
import com.meng.stg.bullets.*;
import com.badlogic.gdx.math.*;


public class MoveCtrl{
	private BaseEnemyPlane enemy;
	private BaseEnemyBullet enemyBullet;
	private MoveMethod[] m;
	private int now=0;
	private int flag=0;
	private Vector2 tmpVec;

	public MoveCtrl(BaseEnemyPlane enemy,MoveMethod... r){
		this.enemy=enemy;
		m=r;
		flag=0;
	  }
	public MoveCtrl(BaseEnemyBullet enemyBullet,MoveMethod... r){
		this.enemyBullet=enemyBullet;
		m=r;
		flag=1;
	  }
	public void update(){
		if(now<m.length){
			if(m[now].time-->0){	
				tmpVec=m[now].velocity;
			  }else{
				now++;
			  }	  
		  }
		if(flag==0){
			enemy.Center.add(tmpVec);
		  }else{
			enemyBullet.Center.add(tmpVec);
		  }
	  }
  }
