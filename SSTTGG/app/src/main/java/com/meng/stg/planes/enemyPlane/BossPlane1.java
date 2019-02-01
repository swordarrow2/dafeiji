package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;

public class BossPlane1 extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private BulletShooter bs;

    @Override
    public void update(){
        super.update();
		move();
		//  am.update();
		if(existTime%60==1){
			bs.shoot();
		  }
	  }

    @Override
    public void Init(float x,float y,float vx,float vy,int hp){
        super.Init(x,y,vx,vy,hp);
        image.setSize(64,64);

        bs=new BulletShooter(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.red)
		  .setBulletForm(BulletForm.liandan)
		  .setStraightMove(true)
		  .setWays(6)
		  .setWaysDegree(60)
		  .setCengShu(10)
		  .setInFrame(10);
		bs.shoot();
        moveManager=new MoveManager(this,new MoveMethodStraight(1,new Vector2(0,0)));
	  }

    protected void move(){
        if(objectCenter.x>380){
            xx=true;
		  }else if(objectCenter.x<10){
            xx=false;
		  }
        if(objectCenter.y>430){
            yy=true;
		  }else if(objectCenter.y<100){
            yy=false;
		  }
        objectCenter.x=xx?objectCenter.x+vx:objectCenter.x-vx;
        objectCenter.y=yy?objectCenter.y+vy:objectCenter.y-vy;
	  }

    @Override
    protected void shoot(){
        if(time%30==31){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight());
                vel.rotate(30);
			  }
		  }
	  }

    @Override
    public void Kill(){
        Killed=true;
        MainScreen.onBoss=false;
        int randVal=MathUtils.random(0,360);
        Vector2 vel=new Vector2(15,0);
        vel.rotate(randVal);
        for(int i=0;i<24;i++){
            SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight());
            vel.rotate(15);
		  }
        super.Kill();
	  }

  }
