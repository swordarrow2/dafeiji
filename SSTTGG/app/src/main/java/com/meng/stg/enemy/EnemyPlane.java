package com.meng.stg.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BulletShooter;
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
    //    if(time%42==1){
     //       Vector2 vel=new Vector2(0,-5);
     //       SimpleRedBullet.Pool.obtain().Init(Center,vel);
    //    }

     //   if(time%42==21){
     //       Vector2 vel=new Vector2(0,-5);
     //       SimpleGreenBullet.Pool.obtain().Init(Center,vel);
     //   }
    }

    @Override
    public void Init(float x,float y,float vx,float vy){
        super.Init(x,y,vx,vy);
        BulletShooter bs=new BulletShooter(this,new Vector2(0,-3));
    }

    public EnemyPlane(String EnemyName,Vector2 OriginalPosition,float Velocity,double Direction,byte ColorType){

    }
    public EnemyPlane(){

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
