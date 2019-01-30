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

/*
stage data:when create enemy
 */
public class stage1{
    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
                SimpleRedBullet.Pool.obtain().Init(new Vector2(270,320),null);
                SimpleRedBullet.create(null,BulletForm.lindan,BulletColor.blue);
                break;
            case 60:
                SimpleRedBullet.create(null,BulletForm.huanyu,BulletColor.grayAndRed);
                break;
            case 120:
                new EnemyPlane().Init(270,719,0,-1);
                break;
            case 180:
                new EnemyPlane().Init(150,719,-1,-2);
                break;
            case 240:
                new EnemyPlane().Init(350,719,-3,-3);
                break;
            case 260:
                new EnemyPlane().Init(50,719,-2,-4);
                break;
            case 360:
                new BossPlane1().Init(250,450,-2,-1,200);
                MainScreen.onBoss=true;
                break;
            case 380:
                new EnemyPlane().Init(220,719,-2,-5);
                break;
            case 400:
                new EnemyPlane().Init(450,719,-1,-6);
                break;
            case 420:
                new EnemyPlane().Init(540,650,-5,-3);
                break;
            case 440:
                new EnemyPlane().Init(50,719,-9,2);
                break;
            case 500:
                new BossPlane2().Init(250,650,-2,-1,2000);
                MainScreen.onBoss=true;
                break;
            case 620:
                SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.instance.objectCenter.x,0),null);
                break;
            case 670:
                SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.instance.objectCenter.x,720),null);
                break;
        }
    }
}
