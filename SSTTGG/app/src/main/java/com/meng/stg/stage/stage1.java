package com.meng.stg.stage;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.SimpleRedBullet;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.enemyPlane.EnemyPlaneCreator;
import com.meng.stg.planes.enemyPlane.EnemyType;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.move.*;
import com.badlogic.gdx.graphics.*;

/*
stage data:when create enemy
 */
public class stage1{
    static EnemyPlaneCreator epc=new EnemyPlaneCreator();

    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
                SimpleRedBullet.create(new Vector2(270,400),BulletForm.lindan,BulletColor.blue,0,new MoveMethodStraight(1,new Vector2(0,-1)));
                break;
            case 60:
                SimpleRedBullet.create(new Vector2(70,400),BulletForm.huanyu,BulletColor.grayAndRed,0,new MoveMethodStraight(1,new Vector2(0,-1)));
                break;
            case 120:
			  epc.setEnemyCenter(270,419).setColor(Color.RED).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 180:
			  epc.setEnemyCenter(150,419).setColor(Color.GREEN).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 240:
			  epc.setEnemyCenter(350,419).setColor(Color.YELLOW).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 260:
			  epc.setEnemyCenter(50,419).setColor(Color.BLUE).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 360:
                epc.setEnemyCenter(50,419).setHp(1000).setEnemyType(EnemyType.dahudie).createEnemy();
                MainScreen.onBoss=true;
                break;
            case 380:
                epc.setEnemyCenter(220,419).setHp(10).setEnemyType(EnemyType.xiaozayu).createEnemy();
                break;
            case 400:
			  epc.setEnemyCenter(450,419).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 420:
			  epc.setEnemyCenter(540,419).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 440:
			  epc.setEnemyCenter(50,419).setMoveMethods(new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))}).createEnemy();
                break;
            case 500:
                //      new BossPlane2().Init(250,450,-2,-1,2000);
                MainScreen.onBoss=true;
                break;
            case 620:
                //     SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.instance.objectCenter.x,0),null);
                break;
            case 670:
                //         SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.instance.objectCenter.x,720),null);
                break;
        }
    }
}
