package com.meng.stg.stage;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.SimpleRedBullet;
import com.meng.stg.planes.enemyPlane.BossPlane1;
import com.meng.stg.planes.enemyPlane.BossPlane2;
import com.meng.stg.planes.enemyPlane.EnemyPlane;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.move.*;

/*
stage data:when create enemy
 */
public class stage1{
    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
			  SimpleRedBullet.create(new Vector2(270,400),BulletForm.lindan,BulletColor.blue,0,new MoveMethodStraight(1,new Vector2(0,-1)));
                break;
            case 60:
			  SimpleRedBullet.create(new Vector2(70,400),BulletForm.huanyu,BulletColor.grayAndRed,0,new MoveMethodStraight(1,new Vector2(0,-1)) );
                break;
            case 120:
                new EnemyPlane().Init(270,419,0,-1);
                break;
            case 180:
                new EnemyPlane().Init(150,419,-1,-2);
                break;
            case 240:
                new EnemyPlane().Init(350,419,-3,-3);
                break;
            case 260:
                new EnemyPlane().Init(50,419,-2,-4);
                break;
            case 360:
                new BossPlane1().Init(250,250,-2,-1,1000);
                MainScreen.onBoss=true;
                break;
            case 380:
                new EnemyPlane().Init(220,419,-2,-5);
                break;
            case 400:
                new EnemyPlane().Init(450,419,-1,-6);
                break;
            case 420:
                new EnemyPlane().Init(540,440,-5,-3);
                break;
            case 440:
                new EnemyPlane().Init(50,419,-9,2);
                break;
            case 500:
                new BossPlane2().Init(250,450,-2,-1,2000);
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
