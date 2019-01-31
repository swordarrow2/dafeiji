package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.BaseGameObject;
import com.meng.stg.ResourcesManager;
import com.meng.stg.planes.myPlane.BaseMyPlane;

public class AnimationManager{
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private Drawable currentFrame=null;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;
    private String objectName="";
    private boolean flip=false;
    private boolean isMyPlane=true;
    private int[][] animNum=new int[][]{
            {0,7},
            {16,23}
    };
    private final int[][] bossAnim=new int[][]{
            {96,100},
            {101,107}
    };

    public AnimationManager(BaseGameObject obj,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;
        if(obj instanceof BaseMyPlane){
            objectName="reimu";
            isMyPlane=true;
        }else{
            objectName="zayu";
            animNum=bossAnim;
            isMyPlane=false;
        }
    }

    public void setStatus(MoveStatus mov){
        if(mov==status){
            return;
        }
        status=mov;
        if(isMyPlane){
            switch(status){
                case stay:
                    flip=false;
                    animFrom=0;
                    animTo=7;
                    curFrameNumber=animFrom;
                    break;
                case leftMove:
                    flip=false;
                    animFrom=8;
                    animTo=15;
                    curFrameNumber=animFrom;
                    break;
                case rightMove:
                    flip=false;
                    animFrom=16;
                    animTo=23;
                    curFrameNumber=animFrom;
                    break;
            }
        }else{
            switch(status){
                case stay:
                    flip=false;
                    animFrom=animNum[0][0];
                    animTo=animNum[0][1];
                    curFrameNumber=animFrom;
                    break;
                case leftMove:
                    flip=false;
                    animFrom=animNum[1][0];
                    animTo=animNum[1][1];
                    curFrameNumber=animFrom;
                    break;
                case rightMove:
                    flip=true;
                    animFrom=animNum[1][0];
                    animTo=animNum[1][1];
                    curFrameNumber=animFrom;
                    break;
            }
        }
    }

    public void update(){
        ++time;
        if(time>=everyAnimFrameTime){
            ++curFrameNumber;
            time=0;
        }
        if(curFrameNumber>animTo){
            curFrameNumber=animFrom+5;
        }
        if(flip){
            currentFrame=ResourcesManager.flipedTextures.get(objectName+curFrameNumber);
        }else{
            currentFrame=ResourcesManager.textures.get(objectName+curFrameNumber);
        }
    }

    public Drawable getImage(){
        return currentFrame;
    }
}
