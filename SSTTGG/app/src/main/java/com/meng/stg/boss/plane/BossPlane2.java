package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.ui.*;

public class BossPlane2 extends BaseEnemyPlane{

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

    public void init(Vector2 center,EnemyColor enemyColor,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
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
    public Vector2 getSize(){
        return new Vector2(32,32);
	  }

	@Override
	public void kill(){
		super.kill();
		MainScreen.onBoss=false;
	  }
	  

    @Override
    public void shoot(){
        if(existTime%60==1){
			bulletShooter.shoot();
		  }
	  }
  }
