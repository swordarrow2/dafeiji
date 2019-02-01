package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.SimpleRedBullet;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;

public class EnemyXiaozayu extends BaseEnemyPlane{

    @Override
    public void update(){
        super.update();
        moveManager.update();
        //  am.update();
        if(existTime%60==1){
            bulletShooter.shoot();
        }
    }

    public void Init(Color c,Vector2 center,int hp,BaseMoveMethod... bmm){
        super.Init(c,center,hp,bmm);
        bulletShooter=new BulletShooter(this)
                .setBulletCenter(objectCenter)
                .setBulletColor(BulletColor.red)
                .setBulletForm(BulletForm.liandan)
                .setStraightMove(true)
                .setWays(6)
                .setWaysDegree(60)
                .setCengShu(10)
                .setInFrame(10);
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(32,32);
    }

    @Override
    public void shoot(){
        if(time%30==31){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight());
                vel.rotate(30);
            }
        }
    }
}
