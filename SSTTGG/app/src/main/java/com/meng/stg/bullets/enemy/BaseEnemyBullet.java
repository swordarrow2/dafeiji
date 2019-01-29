package com.meng.stg.bullets.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodCircle;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

public abstract class BaseEnemyBullet extends BaseBullet{

    public int refCount=0;
    public int thoughCount=0;

    public abstract Drawable getDrawable();

    public static int bulletCount=0;

    @Override
    public void Init(){
        super.Init();
        mvc=new MoveManager(this,
                new MoveMethodStraight(60,new Vector2(0,-5)),
                //  new MoveMethodStraight(20,new Vector2(1,9)),
                //  new MoveMethodStraight(20,new Vector2(-5,-9)),
                //  new MoveMethodStraight(20,new Vector2(-1,3)),
                new MoveMethodCircle(10,90,false,new Vector2(-1,-21))
        );
        bulletCount++;
    }

    @Override
    public void kill(){
        super.kill();
        bulletCount--;
    }

    public void setRefAndThough(int ref,int tho){
        refCount=ref;
        thoughCount=tho;
    }

    @Override
    public void update(){
        super.update();
        mvc.update();
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
