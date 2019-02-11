package com.meng.stg.stage;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.planes.enemyPlane.normal.EnemyColor;
import com.meng.stg.planes.enemyPlane.normal.EnemyPlaneCreator;
import com.meng.stg.planes.enemyPlane.normal.EnemyType;
import com.meng.stg.ui.MainScreen;

public class stage1{
    public static EnemyPlaneCreator epc=new EnemyPlaneCreator();

    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
                // MainScreen.gameTime=1;
                EnemyBullet.create(new Vector2(270,400),BulletForm.lindan,BulletColor.blue,0,0);
                break;
            case 60:
                EnemyBullet.create(new Vector2(70,400),BulletForm.huanyu,BulletColor.grayAndRed,0,0);
                break;
            case 120:
                epc.setEnemyCenter(270,419).setColor(EnemyColor.red).createEnemy();
                break;
            case 140:
                epc.setEnemyCenter(150,419).setColor(EnemyColor.green).createEnemy();
                break;
            case 160:
                epc.setEnemyCenter(350,419).setColor(EnemyColor.yellow).createEnemy();
                break;
            case 180:
                epc.setEnemyCenter(50,419).setColor(EnemyColor.blue).createEnemy();
                //	  MainScreen.gameTime=119;
                break;
            case 20:
                //i.update();
                epc.setEnemyCenter(193,350).setHp(20000).setEnemyType(EnemyType.Boss).setColor(EnemyColor.blue).createEnemy();
                //      epc.setEnemyCenter(50,419).setHp(1000).setEnemyType(EnemyType.dahudie).createEnemy();
                MainScreen.onBoss=true;
                //	  MainScreen.gameTime=2;
                break;
            case 380:
                epc.setEnemyCenter(220,419).setHp(10).setEnemyType(EnemyType.xiaozayu).createEnemy();
                break;
            case 400:
                epc.setEnemyCenter(450,419).createEnemy();
                break;
            case 420:
                epc.setEnemyCenter(540,419).createEnemy();
                break;
            case 440:
                epc.setEnemyCenter(50,419).createEnemy();
                break;
            case 500:
                //      new BossPlane2().init(250,450,-2,-1,2000);
                MainScreen.onBoss=true;
                break;
            case 620:
                //     BaseBigFace.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(BulletRemover.instance.objectCenter.x,0),null);
                break;
            case 670:
                //         BaseBigFace.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(BulletRemover.instance.objectCenter.x,720),null);
                break;
        }
    }
}
