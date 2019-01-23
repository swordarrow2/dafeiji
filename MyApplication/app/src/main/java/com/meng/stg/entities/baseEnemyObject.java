package com.meng.stg.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.meng.stg.MainScreen;
import com.meng.stg.entities.enemyBullet.BaseBullet;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseEnemyObject extends BaseObject{
    public static HashSet<BaseEnemyObject> Instances=new HashSet<BaseEnemyObject>();
    public static LinkedBlockingQueue<BaseEnemyObject> ToDelete=new LinkedBlockingQueue<BaseEnemyObject>();
    public static LinkedBlockingQueue<BaseEnemyObject> ToAdd=new LinkedBlockingQueue<BaseEnemyObject>();
    //建立一个对所有Projectile的引用

    protected Rectangle drawBox=new Rectangle();

    @Override
    public void Init(){
        super.Init();
        ToAdd.add(this);
    }

    @Override
    public void Update(){
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
        if(!drawBox.overlaps(MainScreen.FightArea))
            Kill();
        super.Update();
        Judge();
    }

    protected abstract Shape2D getCollisionArea();

    public void Judge(){
        if(getCollisionArea().contains(BaseMyPlane.Instance.Center)){
            BaseMyPlane.Instance.Kill();
        }
    }

    @Override
    public void Kill(){
        ToDelete.add(this);
        //在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
        if(this instanceof BaseBullet){
            BaseBullet.bulletCount--;
        }
        super.Kill();
    }

    public static void UpdateAll(){
        while(!ToDelete.isEmpty())
            Instances.remove(ToDelete.poll());
        while(!ToAdd.isEmpty())
            Instances.add(ToAdd.poll());
        for(BaseEnemyObject projectile : Instances){
            projectile.Update();
        }
    }
}
