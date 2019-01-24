package com.meng.stg.bullets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.math.*;

import com.meng.stg.MainScreen;
import com.meng.stg.player.MyPlane;

public abstract class Projectile extends BaseBullet {
    public static HashSet<Projectile> Instances = new HashSet<Projectile>();
    public static LinkedBlockingQueue<Projectile> ToDelete = new LinkedBlockingQueue<Projectile>();
    public static LinkedBlockingQueue<Projectile> ToAdd = new LinkedBlockingQueue<Projectile>();
    //建立一个对所有Projectile的引用
    Circle judgeCircle;
    public boolean isEnemyBullet = true;
    protected Rectangle drawBox = new Rectangle();

    @Override
    public void Init() {
        ToAdd.add(this);
        super.Init();
    }

    @Override
    public void Update() {
        super.Update();
        drawBox.set(Drawer.getX(), Drawer.getY(), Drawer.getWidth(), Drawer.getHeight());
        judgeCircle.setPosition(Center);
        if (!drawBox.overlaps(MainScreen.FightArea)) {
            Kill();
        } else {
            Judge();
        }
    }

    protected abstract Shape2D getCollisionArea();

    public static void killAllBullet() {
        Iterator i = Instances.iterator();
        while(i.hasNext()){
        	((Projectile)i.next()).Kill();
        }

}

    public void Judge() {
        if (getCollisionArea().contains(MyPlane.Instance.Center)) {
            Kill();
            MyPlane.Instance.Kill();
        }
    }

    @Override
    public void Kill() {
        ToDelete.add(this);
        //在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
        super.Kill();
    }

    public static void UpdateAll() {
        while (!ToDelete.isEmpty()) {
            Instances.remove(ToDelete.poll());
            bullet.bulletCount--;
        }
        while (!ToAdd.isEmpty()) {
            Instances.add(ToAdd.poll());
            bullet.bulletCount++;
        }


        for (Projectile projectile : Instances) {
            projectile.Update();
        }
    }
}
