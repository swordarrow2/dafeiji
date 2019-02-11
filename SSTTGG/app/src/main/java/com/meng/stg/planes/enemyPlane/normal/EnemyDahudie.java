package com.meng.stg.planes.enemyPlane.normal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.ui.MainScreen;

public class EnemyDahudie extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private float vx=-2;
    private float vy=-2;

    @Override
    public void update(){
        super.update();
        //    move();
        if(existTime%60==1){
            //      bulletShooter.shoot();
        }
    }

    private final int[][] dahudieAnim=new int[][]{
            {96,100},
            {101,107}
    };

    public void init(Vector2 center,int everyAnimFrameTime,EnemyColor enemyColor,int hp){
        super.init(center,everyAnimFrameTime,hp);
        this.enemyColor=enemyColor;
        animNum=dahudieAnim;
        bulletShooter=new BulletShooter().init()
                .setBaseEnemyPlane(this)
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
                EnemyBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,0);
                vel.rotate(30);
            }
        }
    }

    @Override
    public void kill(){
        MainScreen.onBoss=false;
        int randVal=MathUtils.random(0,360);
        Vector2 vel=new Vector2(15,0);
        vel.rotate(randVal);
        for(int i=0;i<24;i++){
            EnemyBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,0);
            vel.rotate(15);
        }
        //   BaseBullet.killAllBullet();
        super.kill();
    }

}
