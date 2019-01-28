package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.JudgeCircleAnimation;

public abstract class BaseMyPlane extends BaseGameObject{

    public static BaseMyPlane Instance;
    public int ExistTime=0;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public static JudgeCircleAnimation animation=null;
    public static Image judgeAnim=null;

    private float playerLastX=270;

    public void Init(){
        Instance=this;
        if(animation==null){
            animation=new JudgeCircleAnimation();
        }
        if(judgeAnim==null){
            judgeAnim=Pools.ImagePool.obtain();
            judgeAnim.setDrawable(animation);
            judgeAnim.setSize(32,32);
        }
        Drawer=Pools.ImagePool.obtain();
        //Drawable drawable=getDrawableJavaBean();
        //Drawer.setDrawable(drawable);
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        MainScreen.MainGroup.addActor(Drawer);
        MainScreen.MainGroup.addActor(judgeAnim);
        ExistTime=0;
        objectCenter.set(MainScreen.Width/2,80);
        Drawer.setSize(30,46);
        unmatchedTime=1;
        onUnmatched=true;
    }

    public void Kill(){
    }

    public void Update(){
        ExistTime++;
        animFlag++;
        objectCenter=new Vector2(MathUtils.clamp(objectCenter.x,0,MainScreen.Width),MathUtils.clamp(objectCenter.y,0,MainScreen.Height));
        judgeAnim.setPosition(objectCenter.x,objectCenter.y,Align.center);
        Drawer.setPosition(objectCenter.x,objectCenter.y,Align.center);
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
            Drawer.setDrawable(getRightMoveAnim());
        }else if(objectCenter.x<playerLastX){
            playerLastX=objectCenter.x;
            Drawer.setDrawable(getLeftMoveAnim());
        }else{
            playerLastX=objectCenter.x;
            Drawer.setDrawable(getStayAnim());
        }

        Drawer.toBack();
        judgeAnim.toFront();
        animation.Update();
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
