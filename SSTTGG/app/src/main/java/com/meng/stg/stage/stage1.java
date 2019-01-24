package com.meng.stg.stage;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.MainScreen;
import com.meng.stg.boss.boss1;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.enemy.enemy;
import com.meng.stg.player.MyPlane;

/**
 * Created by Administrator on 2017/11/1.
 */

public class stage1 {
    public static void addEnemy(){
        enemy e=new enemy();
        boss1 b=new boss1();
        switch (MainScreen.gameTime) {
            case 30:
                Vector2 vel = new Vector2(0, -50);
                SimpleRedBullet sb = new SimpleRedBullet();
                sb.createBullet(new Vector2(MyPlane.Instance.x,720), vel);
                break;
            case 60:
                Vector2 vel2 = new Vector2(0, 30);
                SimpleRedBullet sb2 = new SimpleRedBullet();
                sb2.createBullet(new Vector2(MyPlane.Instance.x,0), vel2);
                break;
            case 120:
                MainScreen.newEnemy(e.createEnemy(270, 719, 0, -1));
                break;
            case 180:
                MainScreen.newEnemy(e.createEnemy(150, 719, -1, -2));

                break;
            case 240:
                MainScreen.newEnemy(e.createEnemy(350, 719, -3, -3));
                break;
            case 260:
                MainScreen.newEnemy(e.createEnemy(50, 719, -2, -4));
                break;
            case 360:
                MainScreen.newEnemy(b.createEnemy(250, 450, -2, -1));
                MainScreen.onBoss = true;
                break;
            case 380:
                MainScreen.newEnemy(e.createEnemy(220, 719, -2, -5));
                break;
            case 400:
                MainScreen.newEnemy(e.createEnemy(450, 719, -1, -6));
                break;
            case 420:
                MainScreen.newEnemy(e.createEnemy(540, 650, -5, -3));
                break;
            case 440:
                MainScreen.newEnemy(e.createEnemy(50, 719, -9, 2));
                break;
            case 500:MainScreen.newEnemy(b.createEnemy(250, 650, -2, -1,2000));
                MainScreen.onBoss = true;
                break;
            case 620 :
                Vector2 vel3 = new Vector2(0, 30);
                SimpleRedBullet sb3 = new SimpleRedBullet();
                sb3.createBullet(new Vector2(MyPlane.Instance.x,0), vel3);
                break;
            case 670:
                Vector2 vel4 = new Vector2(0, -50);
                SimpleRedBullet sb4 = new SimpleRedBullet();
                sb4.createBullet(new Vector2(MyPlane.Instance.x,720), vel4);
                break;

        }


    }
}
