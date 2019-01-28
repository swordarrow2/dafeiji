package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.MainScreen;
import com.meng.stg.planes.enemyPlane.MoveCtrl;
import com.meng.stg.planes.enemyPlane.MoveMethod;
import com.meng.stg.planes.myPlane.BaseMyPlane;

public abstract class BaseEnemyBullet extends BaseBullet{

    public int refCount=0;
    public int thoughCount=0;

    public abstract Drawable getDrawable() ;
    public static int bulletCount=0;
    @Override
    public void Init(){
        super.Init();
        mvc=new MoveCtrl(this,
                new MoveMethod(60,new Vector2(0,-5)),
                new MoveMethod(20,new Vector2(1,9)),
                new MoveMethod(20,new Vector2(-5,-9)),
                new MoveMethod(20,new Vector2(-1,3)),
                new MoveMethod(10,new Vector2(-1,-21))
        );
        bulletCount++;
    }

    @Override
    public void Kill(){
        super.Kill();
        bulletCount--;
    }

    public void setRefAndThough(int ref,int tho){
        refCount=ref;
        thoughCount=tho;
    }

    @Override
    public void Update(){
        super.Update();
        mvc.update();
        if(refCount>0){
            if(objectCenter.x<=0){
                velocity.x=-velocity.x;
                objectCenter.x=1;
                refCount--;
            }
            if(objectCenter.x>=MainScreen.FightArea.width){
                velocity.x=-velocity.x;
                objectCenter.x=MainScreen.FightArea.width-1;
                refCount--;
            }
            if(objectCenter.y<=0){
                velocity.y=-velocity.y;
                objectCenter.y=1;
                refCount--;
            }
            if(objectCenter.y>=MainScreen.FightArea.height){
                velocity.y=-velocity.y;
                objectCenter.y=MainScreen.FightArea.height-1;
                refCount--;
            }
        }else if(thoughCount>0){
            if(objectCenter.x<=0){
                objectCenter.x=MainScreen.FightArea.width-1;
                thoughCount--;
            }
            if(objectCenter.x>=MainScreen.FightArea.width){
                objectCenter.x=1;
                thoughCount--;
            }
            if(objectCenter.y<=0){
                objectCenter.y=MainScreen.FightArea.height-1;
                thoughCount--;
            }
            if(objectCenter.y>=MainScreen.FightArea.height){
                objectCenter.y=1;
                thoughCount--;
            }
        }
    }

    @Override
    public void Judge(){
        if(judgeCircle.contains(BaseMyPlane.Instance.objectCenter)){
            Kill();
            BaseMyPlane.Instance.Kill();
        }
    }

    @Override
    public float getRotationDegree(){
        return velocity.angle()+270;
    }
}
