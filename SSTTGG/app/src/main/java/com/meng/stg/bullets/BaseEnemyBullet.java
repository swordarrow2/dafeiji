package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.effects.enemy.Effect;
import com.meng.stg.effects.enemy.EffectType;
import com.meng.stg.item.item.EnemyItem;
import com.meng.stg.item.item.ItemType;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseEnemyBullet extends BaseBullet{

    public static HashSet<BaseEnemyBullet> instances=new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete=new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd=new LinkedBlockingQueue<BaseEnemyBullet>();

    public int refCount=0;
    public int thoughCount=0;

    public abstract Drawable getDrawable();

    public void init(){
        super.init();
        toAdd.add(this);
    }

    @Override
    public void kill(){
        super.kill();
        toDelete.add(this);
        image.remove();
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseEnemyBullet baseBullet : instances){
            baseBullet.update();
        }
    }

    @Override
    public void update(){
        super.update();
        moveManager.update();
        if(refCount>0){
            if(objectCenter.x<=0){
                velocity.x=-velocity.x;
                objectCenter.x=1;
                refCount--;
            }
            if(objectCenter.x >= MainScreen.fightArea.width){
                velocity.x=-velocity.x;
                objectCenter.x=MainScreen.fightArea.width-1;
                refCount--;
            }
            if(objectCenter.y<=0){
                velocity.y=-velocity.y;
                objectCenter.y=1;
                refCount--;
            }
            if(objectCenter.y >= MainScreen.fightArea.height){
                velocity.y=-velocity.y;
                objectCenter.y=MainScreen.fightArea.height-1;
                refCount--;
            }
        }else if(thoughCount>0){
            if(objectCenter.x<=0){
                objectCenter.x=MainScreen.fightArea.width-1;
                thoughCount--;
            }
            if(objectCenter.x >= MainScreen.fightArea.width){
                objectCenter.x=1;
                thoughCount--;
            }
            if(objectCenter.y<=0){
                objectCenter.y=MainScreen.fightArea.height-1;
                thoughCount--;
            }
            if(objectCenter.y >= MainScreen.fightArea.height){
                objectCenter.y=1;
                thoughCount--;
            }
        }
    }

    public static void killAllBullet(BulletKillMode bkm){
        Iterator i=instances.iterator();
        while(i.hasNext()){
            BaseEnemyBullet bullet=(BaseEnemyBullet)i.next();
            if(bkm!=BulletKillMode.killWithNothing){
                EnemyItem.create(bullet.objectCenter.cpy(),ItemType.highScoreMediam,bkm);
            }
            Effect.create(bullet.objectCenter.cpy(),EffectType.explore);
            bullet.kill();
        }
    }

    @Override
    public void judge(){
        if(judgeCircle.contains(BaseMyPlane.instance.objectCenter)){
            kill();
            BaseMyPlane.instance.kill();
        }
    }

    @Override
    public float getRotationDegree(){
        return velocity.angle()+270;
    }
}
