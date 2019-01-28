package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
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

    public static HashSet<BulletShooter> Instances=new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> ToDelete=new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> ToAdd=new LinkedBlockingQueue<BulletShooter>();

    public BulletShooter(BaseEnemyPlane baseEnemyPlane,Vector2 speed ){
        this.baseEnemyPlane=baseEnemyPlane;
        this.speed=speed;
        ToAdd.add(this);
    }

    public void Update(){
        time++;
        shoot();
        if(baseEnemyPlane.judgeCircle==null){
            Kill();
        }
    }

    public void Kill(){
        ToDelete.add(this);
    }

    public static void UpdateAll(){
        while(!ToDelete.isEmpty()){
            Instances.remove(ToDelete.poll());
        }
        while(!ToAdd.isEmpty()){
            Instances.add(ToAdd.poll());
        }
        for(BulletShooter shooter : Instances){
            shooter.Update();
        }
    }

    void shoot(){
        if(time%25==0){
            SimpleGreenBullet.Pool.obtain().Init(baseEnemyPlane.objectCenter.cpy(),speed);
        }
    }


}
