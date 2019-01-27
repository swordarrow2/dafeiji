package com.meng.stg.player;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;

public abstract class BaseMyPlane{

    public static BaseMyPlane Instance;
    public Vector2 Center=new Vector2();
    public Vector2 Velocity=new Vector2();
    public Image Drawer=null;
    public int ExistTime=0;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int animTime=0;

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
        Drawable drawable=getDrawableJavaBean();
        Drawer.setDrawable(drawable);
        Drawer.setRotation(getRotationDegree());
        MainScreen.MainGroup.addActor(Drawer);
        MainScreen.MainGroup.addActor(judgeAnim);
        ExistTime=0;
        Center.set(MainScreen.Width/2,80);
        Drawer.setSize(30,46);
        unmatchedTime=1;
        onUnmatched=true;
    }

    public void Kill(){
    }

    public float getRotationDegree(){
        return 0;
    }

    public void Update(){
        ExistTime++;
        animTime++;
        Center.add(Velocity);
        Center=new Vector2(MathUtils.clamp(Center.x,0,MainScreen.Width),MathUtils.clamp(Center.y,0,MainScreen.Height));
        judgeAnim.setPosition(Center.x,Center.y,Align.center);
        Drawer.setPosition(Center.x,Center.y,Align.center);
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
        if(Center.x>playerLastX){
            playerLastX=Center.x;
            Drawer.setDrawable(getRightMoveAnim());
        }else if(Center.x<playerLastX){
            playerLastX=Center.x;
            Drawer.setDrawable(getLeftMoveAnim());
        }else{
            playerLastX=Center.x;
            Drawer.setDrawable(getStayAnim());
        }

        Drawer.toBack();
        judgeAnim.toFront();
        animation.Update();
    }

    public float[] getPosition(){
        return new float[]{Center.x,Center.y};
    }

    public abstract void bomb();

    public abstract Drawable getDrawableJavaBean();

    public abstract Drawable getStayAnim();

    public abstract Drawable getRightMoveAnim();

    public abstract Drawable getLeftMoveAnim();

    public abstract void shoot();
}
