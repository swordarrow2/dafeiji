package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.JudgeCircleAnimation;
import com.meng.stg.planes.*;
/*
base class of my plane
 */
public abstract class BaseMyPlane extends BaseGameObject{

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public static jca2 animation=null;
    public static Image judgeAnim=null;

    private float playerLastX=270;

    public void Init(){
        instance=this;
        if(animation==null){
            animation=new jca2();
        }
        if(judgeAnim==null){
            judgeAnim=Pools.imagePool.obtain();
            judgeAnim=animation.getImage();
            judgeAnim.setSize(32,32);
        }
        image=Pools.imagePool.obtain();
        //Drawable drawable=getDrawableJavaBean();
        //image.setDrawable(drawable);
		
        MainScreen.mainGroup.addActor(image);
        MainScreen.mainGroup.addActor(judgeAnim);
        existTime=0;
        objectCenter.set(MainScreen.width/2,80);
        image.setSize(30,46);
		image.setOrigin(image.getWidth()/2,image.getHeight()/2);
		judgeAnim.setOrigin(judgeAnim.getWidth()/2,judgeAnim.getHeight()/2);
        unmatchedTime=1;
        onUnmatched=true;
    }

    public void Kill(){
    }

    public void update(){
        existTime++;
        animFlag++;
        objectCenter=new Vector2(MathUtils.clamp(objectCenter.x,0,MainScreen.width),MathUtils.clamp(objectCenter.y,0,MainScreen.height));
        judgeAnim.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        shoot();
        if(onBomb){
            onUnmatched=true;
            bomb();
            bombTime--;
        }
        if(onUnmatched){
            unmatchedTime--;
        }
        if(bombTime==0){
            onBomb=false;
            bombTime=Data.ReimuBombTime;
        }
        if(unmatchedTime==0){
            onUnmatched=false;
            unmatchedTime=Data.ReimuUnmatchedTime;
        }
        if(objectCenter.x>playerLastX){
            playerLastX=objectCenter.x;
            image.setDrawable(getRightMoveAnim());
        }else if(objectCenter.x<playerLastX){
            playerLastX=objectCenter.x;
            image.setDrawable(getLeftMoveAnim());
        }else{
            playerLastX=objectCenter.x;
            image.setDrawable(getStayAnim());
        }

        image.toBack();
        judgeAnim.toFront();
        animation.update();
    }

    public float[] getPosition(){
        return new float[]{objectCenter.x,objectCenter.y};
    }

    public abstract void bomb();

    public abstract Drawable getDrawableJavaBean();

    public abstract Drawable getStayAnim();

    public abstract Drawable getRightMoveAnim();

    public abstract Drawable getLeftMoveAnim();

    public abstract void shoot();
}
