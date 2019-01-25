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

    public float x=0;
    public float y=0;
    public int missTime=0;
    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int animTime=0;

    public JudgeCircleAnimation animation=null;
    public Image judgeAnim=null;
    public boolean missed=false;
    public boolean b=false;

    private float playerLastX=270;

    public void Init(){
        judgeAnim=Pools.ImagePool.obtain();
        judgeAnim.setDrawable(getJudgeAnim());
        Drawer=Pools.ImagePool.obtain();
        Drawable drawable=getDrawable();
        Drawer.setDrawable(drawable);
        MainScreen.MainGroup.addActor(Drawer);
        MainScreen.MainGroup.addActor(judgeAnim);
        ExistTime=0;
        Instance=this;
        judgeAnim.setSize(32,32);
        Center.set(MainScreen.Width/2,80);
        Drawer.setSize(32,48);
        unmatchedTime=1;
        onUnmatched=true;
    }

    private Drawable getJudgeAnim(){
        if(animation==null){
            animation=new JudgeCircleAnimation();
        }
        return animation;
    }

    public void Kill(){
        if(!onUnmatched){
            missTime++;
            missed=true;
            b=false;
            Pools.ImagePool.free(judgeAnim);
            switch(MainScreen.playerFlag){

                case Data.playerFlagAlice:
                    //   Drawer.remove();
                    //    MyPlaneAlice.Alice.Drawer.remove();
                    //     Pools.ImagePool.free(Drawer);
                    //      Pools.ImagePool.free(MyPlaneAlice.Alice.Drawer);
                    //      new MyPlaneAlice().Init();
                    break;
            }
            //Drawer.remove();
            //将Drawer从舞台上撤下并且将其回归到Pool中
            //Pools.ImagePool.free(Drawer);
        }
    }

    public void Update(){
        Center.x=x=MathUtils.clamp(Center.x,0,MainScreen.Width);
        Center.y=y=MathUtils.clamp(Center.y,0,MainScreen.Height);
        judgeAnim.setPosition(Center.x,Center.y,Align.center);
        judgeAnim.setDrawable(getJudgeAnim());
        Center.add(Velocity);
        Drawer.setPosition(Center.x,Center.y,Align.center);
        ExistTime++;
        if(onBomb){
            onUnmatched=true;
            bomb();
            bombTime--;
        }
        if(onUnmatched){
            unmatchedTime--;
        }
        onUnmatched=false;
        judgeAnim.toFront();
        animation.Update();

        Drawer.toBack();
        animTime++;
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
        ExistTime++;
        shoot();
        if(bombTime==0){
            onBomb=false;
            bombTime=Data.ReimuBombTime;
        }
        if(unmatchedTime==0){
            onUnmatched=false;
            unmatchedTime=Data.ReimuUnmatchedTime;
        }

    }

    public float[] getPosition(){
        return new float[]{Center.x,Center.y};
    }

    public abstract void bomb();

    public abstract Drawable getDrawable();

    public abstract Drawable getStayAnim();

    public abstract Drawable getRightMoveAnim();

    public abstract Drawable getLeftMoveAnim();

    public abstract void shoot();
}
