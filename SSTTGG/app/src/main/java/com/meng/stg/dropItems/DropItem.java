package com.meng.stg.dropItems;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class DropItem extends BaseGameObject{

    public static HashSet<DropItem> instances=new HashSet<DropItem>();
    public static LinkedBlockingQueue<DropItem> toDelete=new LinkedBlockingQueue<DropItem>();
    public static LinkedBlockingQueue<DropItem> toAdd=new LinkedBlockingQueue<DropItem>();

    public BulletKillMode bulletKillMode;
    public DropItemType itemType;
    public int DrawableNumber=0;

    public static void create(Vector2 center,DropItemType type){
        create(center,type,BulletKillMode.killWithScorePoint);
    }

    public static void create(Vector2 center,DropItemType bf,BulletKillMode bkm){
        ObjectPools.itemPool.obtain().init(center,bf,bkm);
    }

    public void init(Vector2 center,DropItemType itemType,BulletKillMode bulletKillMode){
        super.init();
        this.bulletKillMode=BulletKillMode.killWithNothing;
        toAdd.add(this);
        existTime=0;
        judgeCircle=new Circle(objectCenter,16);
     //   moveManager=new MoveManager(this,new MoveMethodStraight(90,1,new Vector2(0,-1)));
        objectCenter.set(center);
        this.bulletKillMode=bulletKillMode;
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/2);
        this.itemType=itemType;
        switch(itemType){
            case power:
                DrawableNumber=500;
                size=new Vector2(16,16);
                break;
            case powerBig:
                DrawableNumber=504;
                size=new Vector2(32,32);
                break;
            case point:
                DrawableNumber=502;
                size=new Vector2(16,16);
                break;
            case player:
                DrawableNumber=508;
                size=new Vector2(32,32);
                break;
            case playerFragment:
                DrawableNumber=506;
                size=new Vector2(32,32);
                break;
            case bomb:
                DrawableNumber=512;
                size=new Vector2(32,32);
                break;
            case bombFragment:
                DrawableNumber=510;
                size=new Vector2(32,32);
                break;
            case powerFull:
                DrawableNumber=514;
                size=new Vector2(32,32);
                break;
            case powerPointer:
                DrawableNumber=501;
                size=new Vector2(16,16);
                break;
            case powerBigPointer:
                DrawableNumber=505;
                size=new Vector2(32,32);
                break;
            case pointPointer:
                DrawableNumber=503;
                size=new Vector2(16,16);
                break;
            case playerPointer:
                DrawableNumber=509;
                size=new Vector2(32,32);
                break;
            case playerFragmentPointer:
                DrawableNumber=507;
                size=new Vector2(32,32);
                break;
            case bombPointer:
                DrawableNumber=513;
                size=new Vector2(32,32);
                break;
            case bombFragmentPointer:
                DrawableNumber=511;
                size=new Vector2(32,32);
                break;
            case powerFullPointer:
                DrawableNumber=515;
                size=new Vector2(32,32);
                break;
            case highScoreSmall:
                DrawableNumber=516;
                size=new Vector2(16,16);
                break;
            case highScoreMediam:
                DrawableNumber=517;
                size=new Vector2(16,16);
                break;
            case highScoreLarge:
                DrawableNumber=518;
                size=new Vector2(16,16);
                break;
        }
        image.setSize(size.x,size.y);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
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
       //         moveManager.update();
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
        for(DropItem item : instances){
            item.update();
        }
    }

    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("item"+DrawableNumber);
        }
        return drawable;
    }

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
}
