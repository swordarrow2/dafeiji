package com.meng.stg.stage;

import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.planes.enemyPlane.normal.EnemyColor;
import com.meng.stg.planes.enemyPlane.normal.EnemyPlaneCreator;
import com.meng.stg.planes.enemyPlane.normal.EnemyType;
import com.meng.stg.ui.*;

public class stage1{
    public static EnemyPlaneCreator epc=new EnemyPlaneCreator();
	//public static itemPower i=null;
    public static void addEnemy(){
	//  if(i==null){
	//	  i=new itemPower().init(new Vector2(200,200),1);
	//  }else{
	//	i.update();
	//	}
        switch(MainScreen.gameTime){
            case 30:
			  // MainScreen.gameTime=1;
			  EnemyBullet.create(new Vector2(270,400),BulletForm.lindan,BulletColor.blue,0,new MoveMethodStraight(1,10,new Vector2(0,-1)));
			  break;
            case 60:
			  EnemyBullet.create(new Vector2(70,400),BulletForm.huanyu,BulletColor.grayAndRed,0,new MoveMethodStraight(1,10,new Vector2(0,-1)));
			  break;
            case 120:
			  epc.setEnemyCenter(270,419).setColor(EnemyColor.red).setMoveMethods(new MoveGradually(90,30,new Vector2(0,-7f),new Vector2(1,-0.1f))).createEnemy();
			  break;
            case 140:
			  epc.setEnemyCenter(150,419).setColor(EnemyColor.green).setMoveMethods(new MoveGradually(90,20,new Vector2(0,-7f),new Vector2(1,-2f))).createEnemy();
			  break;
            case 160:
			  epc.setEnemyCenter(350,419).setColor(EnemyColor.yellow).setMoveMethods(new MoveGradually(90,10,new Vector2(0,-8f),new Vector2(-2,-0.2f))).createEnemy();
			  break;
            case 180:
			  epc.setEnemyCenter(50,419).setColor(EnemyColor.blue).setMoveMethods(new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f))).createEnemy();
			  //	  MainScreen.gameTime=119;
			  break;
            case 20:
			  //i.update();
			    epc.setEnemyCenter(193,350).setHp(20000).setEnemyType(EnemyType.Boss).setColor(EnemyColor.blue).setMoveMethods(new MoveMethodStraight(1,1,new Vector2(0,0.0001f)) ).createEnemy();
			  //      epc.setEnemyCenter(50,419).setHp(1000).setEnemyType(EnemyType.dahudie).createEnemy();
			       MainScreen.onBoss=true;
		//	  MainScreen.gameTime=2;
			  break;
            case 380:
			  epc.setEnemyCenter(220,419).setHp(10).setEnemyType(EnemyType.xiaozayu).createEnemy();
			  break;
            case 400:
			  epc.setEnemyCenter(450,419).setMoveMethods(new MoveGradually(90,20,new Vector2(0,-7f),new Vector2(1,-0.1f))).createEnemy();
			  break;
            case 420:
			  epc.setEnemyCenter(540,419).setMoveMethods(new MoveGradually(90,20,new Vector2(0,-7f),new Vector2(1,-0.1f))).createEnemy();
			  break;
            case 440:
			  epc.setEnemyCenter(50,419).setMoveMethods(new MoveGradually(90,20,new Vector2(0,-7f),new Vector2(1,-0.1f))).createEnemy();
			  break;
            case 500:
			  //      new BossPlane2().init(250,450,-2,-1,2000);
			  MainScreen.onBoss=true;
			  break;
            case 620:
			  //     BaseItem.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(BaseMyPlane.instance.objectCenter.x,0),null);
			  break;
            case 670:
			  //         BaseItem.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(BaseMyPlane.instance.objectCenter.x,720),null);
			  break;
		  }
	  }
  }
