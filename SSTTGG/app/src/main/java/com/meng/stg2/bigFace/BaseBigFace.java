package com.meng.stg2.bigFace;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg2.BaseGameObject;
import com.meng.stg2.bigFace.item.FaceCharacter;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseBigFace extends BaseGameObject{

    public static HashSet<BaseBigFace> instances=new HashSet<BaseBigFace>();
    public static LinkedBlockingQueue<BaseBigFace> toDelete=new LinkedBlockingQueue<BaseBigFace>();
    public static LinkedBlockingQueue<BaseBigFace> toAdd=new LinkedBlockingQueue<BaseBigFace>();

    public FaceCharacter faceCharacter;

    public void init(){
        super.init();
        toAdd.add(this);
        existTime=0;
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setSize(399,512);
    }

    public void kill(){
        toDelete.add(this);
        image.remove();
        //	super.kill();
    }

    public void update(){
        super.update();
        image.setRotation(0);
        image.setPosition(position.x,position.y,Align.center);
        image.toBack();
        if(existTime>120){
            kill();
        }else{
            position.y+=1;
        }
    }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
        }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
        }
        for(BaseBigFace item : instances){
            item.update();
        }
    }

    public abstract Drawable getDrawable();

    public abstract Vector2 getSize();
}
