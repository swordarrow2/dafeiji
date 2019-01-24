package com.meng.stg.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.MainScreen;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.helpers.Resources;

public class EnemyPlane extends BaseEnemyPlane{

    @Override
    protected Drawable getDrawable(){
        return Resources.Textures.get("EnemyPlane");
    }

    @Override
    protected void shoot(){
        if(time%21==1){
            Vector2 vel=new Vector2(0,-5);
            SimpleRedBullet sb=new SimpleRedBullet();
            sb.createBullet(Center,vel);
        }
    }

    @Override
    protected void move(){
      //  if(!MainScreen.gameOver){
            Center.x+=vx;
            Center.y+=vy;
      //  }
    }

    @Override
    protected void anim(){

    }
}
