package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.effects.BaseEffect;
import com.meng.stg.effects.EffectType;
import com.meng.stg.item.BaseItem;
import com.meng.stg.item.ItemType;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.task.TaskManager;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseEnemyBullet extends BaseBullet{

    public static HashSet<BaseEnemyBullet> instances=new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete=new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd=new LinkedBlockingQueue<BaseEnemyBullet>();

    public int refCount=0;
    public int thoughCount=0;

    public TaskManager taskManager;

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

    public void kill(BulletKillMode bkm){
        super.kill();
        toDelete.add(this);
        image.remove();
        BaseEffect.create(objectCenter.cpy(),EffectType.explore);
        switch(bkm){
            case killWithNothing:
                break;
            case killWithScorePoint:
                BaseItem.create(objectCenter.cpy(),ItemType.highScoreSmall,bkm);
                break;
            case killWithScorePointAndCollect:
                BaseItem.create(objectCenter.cpy(),ItemType.highScoreSmall,bkm);
                break;
            case KillOnBossLastDeath:
                BaseItem.create(objectCenter.cpy(),ItemType.highScoreMediam,bkm);
                break;
            case killOnPlayerDeath:
                break;
        }
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
		taskManager.update();
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
        switch(bkm){
            case KillOnBossLastDeath:
                new BulletRemover().init(BaseBossPlane.instence.objectCenter.cpy());
                break;
            default:
                new BulletRemover().init();
                break;
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
