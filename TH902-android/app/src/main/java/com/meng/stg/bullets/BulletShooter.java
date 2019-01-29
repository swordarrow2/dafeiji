package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.SimpleGreenBullet;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2019/1/25.
 */

public class BulletShooter{

    private BaseEnemyPlane baseEnemyPlane;
    private Vector2 speed;
    private int time;

    public static HashSet<BulletShooter> instances=new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toDelete=new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toAdd=new LinkedBlockingQueue<BulletShooter>();

    public BulletShooter(BaseEnemyPlane baseEnemyPlane,Vector2 speed){
        this.baseEnemyPlane=baseEnemyPlane;
        this.speed=speed;
        toAdd.add(this);
    }

    public void update(){
        time++;
        shoot();
        if(baseEnemyPlane.judgeCircle==null){
            kill();
        }
    }

    public void kill(){
        toDelete.add(this);
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BulletShooter shooter : instances){
            shooter.update();
        }
    }

    void shoot(){
        if(time%25==0){
            SimpleGreenBullet.Pool.obtain().Init(baseEnemyPlane.objectCenter.cpy(),speed);
        }
    }


}
