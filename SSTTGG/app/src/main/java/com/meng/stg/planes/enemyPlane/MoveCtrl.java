package com.meng.stg.planes.enemyPlane;
import com.meng.stg.bullets.*;
import com.badlogic.gdx.math.*;


public class MoveCtrl{
	private BaseEnemyPlane enemy;
	private BaseEnemyBullet enemyBullet;
	private MoveMethod[] m;
	private int now=0;
	private int flag=0;
	private Vector2 tmpVec=new Vector2(1,0);

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
			if(m[now].getTime()>0){	
				tmpVec=m[now].getVelocity();
				m[now].update();
			  }else{
				now++;
			  }	  
		  }
		if(flag==0){
			enemy.velocity.set(tmpVec);
		  }else{
			enemyBullet.velocity.set(tmpVec);
		  }
	  }
  }
