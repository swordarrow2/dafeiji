package com.meng.stg.planes.enemyPlane.normal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.task.*;
import com.meng.stg.bullets.enemy.*;

public class EnemyDahudie extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private float vx=-2;
    private float vy=-2;

    @Override
    public void update(){
        super.update();
        //    move();
        taskManager.update();
        if(existTime%60==1){
            //      bulletShooter.shoot();
		  }
	  }

    private final int[][] dahudieAnim=new int[][]{
		  {96,100},
		  {101,107}
	  };

    public void init(Vector2 center,int everyAnimFrameTime,EnemyColor enemyColor,int hp,Task[] t){
        super.init(center,everyAnimFrameTime ,hp,t);
        this.enemyColor=enemyColor;
        animNum=dahudieAnim;
        bulletShooter=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.red)
		  .setBulletForm(BulletForm.liandan)
		  .setStraightMove(true)
		  .setWays(6) 
		  .setWaysDegree(60)
		  .setCengShu(10)
		  .setInFrame(10)
		  .setBulletStyle(BulletStyle.snipe)
		  ;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(64,64);
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
    public void shoot(){
        if(time%30==1){
            bulletShooter.shoot();
		  }
	  }

    @Override
    public void kill(){
        bulletShooter.setWays(30).setBulletColor(BulletColor.red).setBulletForm(BulletForm.zadan).setBulletStyle(BulletStyle.round).shoot();
        super.kill();
	  }

  }
