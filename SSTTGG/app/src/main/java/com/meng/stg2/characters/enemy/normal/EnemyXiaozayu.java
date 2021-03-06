package com.meng.stg2.characters.enemy.normal;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg2.bullets.enemy.BulletColor;
import com.meng.stg2.bullets.enemy.BulletForm;
import com.meng.stg2.bullets.enemy.Danmaku;
import com.meng.stg2.bullets.enemy.EnemyBullet;
import com.meng.stg2.move.MoveMethodStraight;
import com.meng.stg2.characters.enemy.BaseEnemyPlane;
import com.meng.stg2.task.*;

public class EnemyXiaozayu extends BaseEnemyPlane{


    private final int[][] xiaozayuAnimLan=new int[][]{
            {0,4},
            {5,11}
    };
    private final int[][] xiaozayuAnimHong=new int[][]{
            {12,16},
            {17,23}
    };
    private final int[][] xiaozayuAnimLv=new int[][]{
            {24,28},
            {29,35}
    };
    private final int[][] xiaozayuAnimHuang=new int[][]{
            {36,40},
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
        bulletShooter=new Danmaku().init()
                .setEnemy(this)
                .setBulletCenter(position)
                .setBulletColor(BulletColor.red)
                .setBulletForm(BulletForm.liandan)
                .setStraightMove(true)
                .setWays(6)
                .setReflex(0)
                .setWaysDegree(60)
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
                EnemyBullet.create(position,BulletForm.ganjundan,BulletColor.purple,3,0,0,false,new Task[]{
									   new TaskMove(193,250)
									 });
                vel.rotate(30);
            }
        }
    }
}
