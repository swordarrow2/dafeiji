package com.meng.stg.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.SimpleGreenBullet;
import com.meng.stg.bullets.SimpleRedBullet;
import com.meng.stg.helpers.Resources;

public class EnemyPlane extends BaseEnemyPlane{

    @Override
    protected Drawable getDrawable(){
        return Resources.Textures.get("EnemyPlane");
    }

    @Override
    protected void shoot(){
        if(time%42==1){
            Vector2 vel=new Vector2(0,-5);
            SimpleRedBullet.Pool.obtain().Init(Center,vel);
        }

        if(time%42==21){
            Vector2 vel=new Vector2(0,-5);
            SimpleGreenBullet.Pool.obtain().Init(Center,vel);
        }
    }

    @Override
    protected void move(){
        Center.x+=vx;
        Center.y+=vy;
    }

    @Override
    protected void anim(){

    }
}
