package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.ui.MainScreen;

public class EnemyDahudie extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private float vx=-2;
    private float vy=-2;

    @Override
    public void update(){
        super.update();
        move();
        //  am.update();
        if(existTime%60==1){
            bulletShooter.shoot();
        }
    }

    @Override
    public void init(EnemyColor c,Vector2 center,int hp,BaseMoveMethod... bmm){
        super.init(c,center,hp,bmm);
        bulletShooter=new BulletShooter(this)
                .setBulletCenter(objectCenter)
                .setBulletColor(BulletColor.red)
                .setBulletForm(BulletForm.liandan)
                .setStraightMove(true)
                .setWays(6)
                .setWaysDegree(60)
                .setCengShu(10)
                .setInFrame(10);
        moveManager=new MoveManager(this,new MoveMethodStraight(1,new Vector2(0,0)));
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(64,64);
    }

    protected void move(){
        if(objectCenter.x>380){
            xx=true;
        }else if(objectCenter.x<10){
            xx=false;
        }
        if(objectCenter.y>430){
            yy=true;
        }else if(objectCenter.y<100){
            yy=false;
        }
        objectCenter.x=xx?objectCenter.x+vx:objectCenter.x-vx;
        objectCenter.y=yy?objectCenter.y+vy:objectCenter.y-vy;
    }

    @Override
    public void shoot(){
        if(time%30==31){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                EnemyBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight());
                vel.rotate(30);
            }
        }
    }

    @Override
    public void Kill(){
        MainScreen.onBoss=false;
        int randVal=MathUtils.random(0,360);
        Vector2 vel=new Vector2(15,0);
        vel.rotate(randVal);
        for(int i=0;i<24;i++){
            EnemyBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight());
            vel.rotate(15);
        }
        BaseBullet.killAllBullet();
        super.Kill();
    }

}
