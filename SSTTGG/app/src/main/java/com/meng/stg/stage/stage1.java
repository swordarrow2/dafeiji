package com.meng.stg.stage;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.MainScreen;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.enemyPlane.BossPlane1;
import com.meng.stg.enemyPlane.BossPlane2;
import com.meng.stg.enemyPlane.EnemyPlane;
import com.meng.stg.myPlane.BaseMyPlane;
import com.meng.stg.bullets.*;

/**
 * Created by Administrator on 2017/11/1.
 */

public class stage1{
    public static void addEnemy(){
        switch(MainScreen.gameTime){
            case 30:
			  Vector2 vel=new Vector2(0,0);
			  MainScreen.gameTime=1;
			  SimpleRedBullet.Pool.obtain().Init(new Vector2(270,720),vel);
			//  Vector2 vele2=new Vector2(-1,-1.7f);
			  MainScreen.gameTime=25;
			//  SimpleGreenBullet.Pool.obtain().Init(new Vector2(270,720),vele2);
			  
                break;
            case 60:
                Vector2 vel2=new Vector2(0,30);
                SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.Instance.Center.x,0),vel2);
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
                Vector2 vel3=new Vector2(0,30);
                SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.Instance.Center.x,0),vel3);
                break;
            case 670:
                Vector2 vel4=new Vector2(0,-50);
                SimpleRedBullet.Pool.obtain().Init(new Vector2(BaseMyPlane.Instance.Center.x,720),vel4);
                break;
        }
    }
}
