package com.meng.stg.planes;

import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.stage.*;
import java.util.*;

public class BossPlane extends BaseEnemyPlane{

	Random ran=new Random();

    private final int[][] xiaozayuAnimLan=new int[][]{
		  {10,14},
		  {5,9}
	  };

	BulletShooter p2a,p2b,p3a,p3b,p4;

    @Override
    public void update(){
        super.update();
		//  moveManager.update();
        //  am.update();
		objectCenter.set(193,350);
	  }

    @Override
    public void init(Vector2 center,EnemyColor enemyColor,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,enemyColor,everyAnimFrameTime,hp,bmm);
		BaseBullet.killAllBullet();
		objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;	
		animNum=xiaozayuAnimLan;
        bulletShooter=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.red)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(24)
		  .setReflex(0)
		  .setWaysDegree(15)
		  .setCengShu(1)
		  .setInterval(30)
		  .setRandomCenter(64,64)
		  .setInFrame(1);


		p2a=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(-120,0))
		  .setBulletColor(BulletColor.purple)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(12)
		  .setReflex(0)
		  .setWaysDegree(30)
		  .setCengShu(1)
		  .setInterval(60)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p2b=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(120,0))
		  .setBulletColor(BulletColor.purple)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(12)
		  .setReflex(0)
		  .setWaysDegree(30)
		  .setCengShu(1)
		  .setInterval(60)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p3a=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(-160,0))
		  .setBulletColor(BulletColor.blue)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(40)
		  .setReflex(0)
		  .setWaysDegree(9)
		  .setInterval(40)
		  .setCengShu(1)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p3b=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(160,0))
		  .setBulletColor(BulletColor.blue)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(40)
		  .setReflex(0)
		  .setWaysDegree(9)
		  .setInterval(40)
		  .setCengShu(1)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p4=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.yellow_dark)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(36)
		  .setReflex(0)
		  .setWaysDegree(10)
		  .setCengShu(1)
		  .setInterval(8)
		  .setInFrame(1);

	  }

	@Override
	public void kill(){
		super.kill();
		BaseBullet.killAllBullet();
		//	new BossPlane2().init(new Vector2(50,419),enemyColor,5,hp,new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)));
		//	stage1.epc.setEnemyCenter(50,419).setEnemyType(EnemyType.Boss).setColor(EnemyColor.blue).setMoveMethods(new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)) ).createEnemy();
	  }


    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }

    @Override
    public void shoot(){
        
			bulletShooter
			
			  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
			  .setStraightMove(true)
			  .shoot();
		
			p2a
		//	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
			  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(0.7f))
			  .setStraightMove(true)
			  .shoot();


			p2b
		//	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
			  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(0.7f))
			  .setStraightMove(true)
			  .shoot();
		  
		if(hp<3020&&hp>3000){
			BaseBullet.killAllBullet();
		  }

		
			p3a
		//	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
			  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
			  .setStraightMove(true)
			  .shoot();

			p3b
		//	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
			  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
			  .setStraightMove(true)
			  .shoot();
		
			p4
			  .setBulletVelocity(new Vector2(0,-1).nor().scl(6))
			  .setStraightMove(true)
			  .shoot();
		  
	  }
  }
