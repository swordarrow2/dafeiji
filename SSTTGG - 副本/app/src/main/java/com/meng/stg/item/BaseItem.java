package com.meng.stg.item;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.item.item.ItemType;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.myPlane.BaseMyPlane;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseItem extends BaseGameObject{

    public static HashSet<BaseItem> instances=new HashSet<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toDelete=new LinkedBlockingQueue<BaseItem>();
    public static LinkedBlockingQueue<BaseItem> toAdd=new LinkedBlockingQueue<BaseItem>();

    public BulletKillMode bulletKillMode;
    public ItemType itemType;

    public void init(){
        super.init();
        bulletKillMode=BulletKillMode.killWithNothing;
        toAdd.add(this);
        existTime=0;
        judgeCircle=new Circle(objectCenter,16);
        moveManager=new MoveManager(this,new MoveMethodStraight(90,1,new Vector2(0,-1)));
    }

    public void kill(){
        toDelete.add(this);
        image.remove();
        //	super.kill();
    }

    public void update(){
        super.update();
        if(BaseMyPlane.instance.objectCenter.y>380||objectCenter.cpy().sub(BaseMyPlane.instance.objectCenter).len2()<800){
            bulletKillMode=BulletKillMode.killWithScorePointAndCollect;
        }
        switch(bulletKillMode){
            case killWithScorePoint:
                moveManager.update();
                objectCenter.add(velocity);
                break;
            case killWithScorePointAndCollect:
            case KillOnBossLastDeath:
                objectCenter.add(BaseMyPlane.instance.objectCenter.cpy().sub(objectCenter).nor().scl(4f));
                break;
        }
        image.setRotation(0);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        judgeCircle.setPosition(objectCenter);
        if(judgeCircle.x<-5||judgeCircle.x>390
                ||judgeCircle.y<-5||judgeCircle.y>460){
            kill();
        }else{
            judge();
        }

    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseItem item : instances){
            item.update();
        }
    }

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public abstract Drawable getDrawable();

    public void judge(){
        Vector2 v=BaseMyPlane.instance.objectCenter.cpy();
        if(Math.abs(judgeCircle.x-v.x)<5&&Math.abs(judgeCircle.y-v.y)<5){
            kill();
            switch(itemType){
                case power:
                    BaseMyPlane.instance.incPower(1);
                    break;
                case powerBig:
                    BaseMyPlane.instance.incPower(1);
                    BaseMyPlane.instance.incPower(1);
                    BaseMyPlane.instance.incPower(1);
                    break;
                case point:
                    break;
                case player:
                    break;
                case playerFragment:
                    break;
                case bomb:
                    break;
                case bombFragment:
                    break;
                case powerFull:
                    break;
                case powerPointer:
                    break;
                case powerBigPointer:
                    break;
                case pointPointer:
                    break;
                case playerPointer:
                    break;
                case playerFragmentPointer:
                    break;
                case bombPointer:
                    break;
                case bombFragmentPointer:
                    break;
                case powerFullPointer:
                    break;
                case highScoreSmall:
                    BaseMyPlane.instance.maxPoint+=5;
                    break;
                case highScoreMediam:
                    BaseMyPlane.instance.maxPoint+=10;
                    break;
                case highScoreLarge:
                    BaseMyPlane.instance.maxPoint+=20;
                    break;
            }

        }
    }

    public abstract Vector2 getSize();
}
