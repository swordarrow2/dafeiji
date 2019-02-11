package com.meng.stg.planes.enemyPlane.normal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.bullets.enemy.EnemyBullet;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.task.*;

public class EnemyXiaozayu extends BaseEnemyPlane{


    private final int[][] xiaozayuAnimLan=new int[][]{
            {0,5},
            {5,11}
    };
    private final int[][] xiaozayuAnimHong=new int[][]{
            {12,17},
            {17,23}
    };
    private final int[][] xiaozayuAnimLv=new int[][]{
            {24,29},
            {29,35}
    };
    private final int[][] xiaozayuAnimHuang=new int[][]{
            {36,41},
            {41,47}
    };

    @Override
    public void update(){
        super.update();
        //  am.update();
        if(existTime%60==1){
            bulletShooter.shoot();
        }
    }

    public void init(Vector2 center,EnemyColor enemyColor,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        this.enemyColor=enemyColor;
        this.everyAnimFrameTime=everyAnimFrameTime;
        switch(enemyColor){
            case red:
                animNum=xiaozayuAnimHong;
                break;
            case blue:
                animNum=xiaozayuAnimLan;
                break;
            case green:
                animNum=xiaozayuAnimLv;
                break;
            case yellow:
                animNum=xiaozayuAnimHuang;
                break;
        }
        bulletShooter=new BulletShooter().init()
                .setBaseEnemyPlane(this)
                .setBulletCenter(objectCenter)
                .setBulletColor(BulletColor.red)
                .setBulletForm(BulletForm.liandan)
                .setStraightMove(true)
                .setWays(6)
                .setReflex(0)
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
                EnemyBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,0,new MoveMethodStraight());
                vel.rotate(30);
            }
        }
    }
}
