package com.meng.stg.planes;

import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.stage.*;

public class BossPlane extends BaseEnemyPlane{


    private final int[][] xiaozayuAnimLan=new int[][]{
		  {0,5},
		  {5,11}
	  };
    private final int[][] xiaozayuAnimHong=new int[][]{
		  {12,17},
		  {17,23}
	  };
    private final int[][] xiaozayuAnimLv=new int[][]{
		  {24,29},
		  {29,35}
	  };
    private final int[][] xiaozayuAnimHuang=new int[][]{
		  {36,41},
		  {41,47}
	  };
    @Override
    public void update(){
        super.update();
        moveManager.update();
        //  am.update();  
	  }

    @Override
    public void init(Vector2 center,EnemyColor enemyColor,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,enemyColor,everyAnimFrameTime,hp,bmm);
		BaseBullet.killAllBullet();
        this.everyAnimFrameTime=everyAnimFrameTime;
		switch(enemyColor){
			case red:
			  animNum=xiaozayuAnimHong;
			  break;
			case blue:
			  animNum=xiaozayuAnimLan;
			  break;
			case green:
			  animNum=xiaozayuAnimLv;
			  break;
			case yellow:
			  animNum=xiaozayuAnimHuang;
			  break;
		  }
        bulletShooter=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.red)
		  .setBulletForm(BulletForm.liandan)
		  .setStraightMove(true)
		  .setWays(6)
		  .setReflex(0)
		  .setWaysDegree(60)
		  .setCengShu(10)
		  .setInFrame(10);
	  }

	@Override
	public void kill(){
		super.kill();
		new BossPlane2().init(new Vector2(50,419),enemyColor,5,hp,new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)) );
	//	stage1.epc.setEnemyCenter(50,419).setEnemyType(EnemyType.Boss).setColor(EnemyColor.blue).setMoveMethods(new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)) ).createEnemy();
	  }


    @Override
    public Vector2 getSize(){
        return new Vector2(32,32);
	  }

    @Override
    public void shoot(){
        if(existTime%60==1){
			bulletShooter.shoot();
		  }
	  }
  }
