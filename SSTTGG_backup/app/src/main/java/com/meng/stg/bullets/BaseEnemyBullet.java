package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

public abstract class BaseEnemyBullet extends BaseBullet{

    public int refCount=0;
    public int thoughCount=0;

    public abstract Drawable getDrawable();

    public static int bulletCount=0;

    public void Init(){
        super.Init();
        bulletCount++;
    }

    @Override
    public void kill(){
        super.kill();
        bulletCount--;
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
            if(objectCenter.x>=MainScreen.fightArea.width){
                velocity.x=-velocity.x;
                objectCenter.x=MainScreen.fightArea.width-1;
                refCount--;
            }
            if(objectCenter.y<=0){
                velocity.y=-velocity.y;
                objectCenter.y=1;
                refCount--;
            }
            if(objectCenter.y>=MainScreen.fightArea.height){
                velocity.y=-velocity.y;
                objectCenter.y=MainScreen.fightArea.height-1;
                refCount--;
            }
        }else if(thoughCount>0){
            if(objectCenter.x<=0){
                objectCenter.x=MainScreen.fightArea.width-1;
                thoughCount--;
            }
            if(objectCenter.x>=MainScreen.fightArea.width){
                objectCenter.x=1;
                thoughCount--;
            }
            if(objectCenter.y<=0){
                objectCenter.y=MainScreen.fightArea.height-1;
                thoughCount--;
            }
            if(objectCenter.y>=MainScreen.fightArea.height){
                objectCenter.y=1;
                thoughCount--;
            }
        }
    }

    @Override
    public void judge(){
        if(judgeCircle.contains(BaseMyPlane.instance.objectCenter)){
            kill();
            BaseMyPlane.instance.Kill();
        }
    }

    @Override
    public float getRotationDegree(){
        return velocity.angle()+270;
    }
}