package com.meng.stg.entities.enemyPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.entities.*;
import com.meng.stg.entities.bullet.*;
import com.meng.stg.helpers.*;
public class EnemyAndroid extends baseEnemy{

  
	Circle judgeCircle = new Circle();
    public static int cardTime = 0;
	public static int count=0;
	int angle=0;
	int flag=0;
	int delay=0;

	public static Pool<EnemyAndroid> Pool = new Pool<EnemyAndroid>()
	  {
		@Override
		protected EnemyAndroid newObject(){
			return new EnemyAndroid();
		  }
	  };

	public void Init(Vector2 center){
		super.Init();
		Center.set(center);
		Velocity.set(0,0);
		Drawer.setSize(100,121);
		judgeCircle.set(Center,40);
		Drawer.setPosition(center.x,center.y,Align.center);
	  }

	@Override
	public int GetMaxLife(){
		return 10000;
	  }


	float f_angleCurrent = 0;
	boolean b_angleWay = true;
	Vector2 vct_pos = new Vector2(), vct_spd = new Vector2(), vct_tmp1 = new Vector2();
	@Override
	public void Update(){
		super.Update();
		shoot();
		
	  }

	
	@Override
	protected Shape2D getCollisionArea(){
		return judgeCircle;
	  }

	@Override
	protected Drawable getDrawable(){
		return Resources.Textures.get("mirror");
	  }
	public void shoot(){	
		cardTime++;

		//	boyuli();
		normal1();

	  }
	private void boyuli(){
		float cacher=30f;
		if(cardTime==((int)cacher*180)){
			cardTime=0;
		  }
		Vector2 vel = new Vector2(5,0);
		float flagA=cardTime*cardTime/cacher;
		SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(flagA));
		SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(flagA+30));
		SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(flagA+60));
		//	SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(flagA+270));

		//	SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(-(flagA)));
		//	SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(-(flagA+90)));
		//	SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(-(flagA+180)));
		//	SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().rotate(-(flagA+270)));
	  }

	private void normal1(){
		Vector2 vel = new Vector2(0,-1);
		angle=40;
		delay=3;
		if(cardTime%delay==0){
			if(cardTime<=30){
				for(int k=0;k<=360/angle;k++){
					SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().add(0,-0.0003f*flag-0.0003f).rotate(flag*3+k*angle));
				  }
			  }else{
				for(int k=0;k<=360/angle;k++){
					SimpleRedBullet.Pool.obtain().Init(Center,vel.cpy().add(0,-0.0003f*flag-0.0003f).rotate(-flag*3-k*angle));
				  }
			  }
			flag++;
			if(flag==10){
				flag=0;
			  }
		  }
		if(cardTime>60){
			cardTime=0;
		  }

	  }



  }
