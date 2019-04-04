package com.meng.TaiHunDanmaku.planes;

import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.planes.myPlane.BaseMyPlane;

public class AnimationManager{
    private BaseMyPlane myPlane;
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;

    public AnimationManager(BaseMyPlane obj,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;
        myPlane=obj;
    }

    public void setStatus(MoveStatus mov){
        if(mov==status) return;
        status=mov;
        switch(status){
            case stay:
                animFrom=0;
                animTo=7;
                curFrameNumber=animFrom;
                break;
            case moveLeft:
                animFrom=8;
                animTo=15;
                curFrameNumber=animFrom;
                break;
            case moveRight:
                animFrom=16;
                animTo=23;
                curFrameNumber=animFrom;
                break;
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
        myPlane.image.setDrawable(ResourcesManager.textures.get("reimu"+curFrameNumber));
    }
}
