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
    public Vector2 Center=new Vector2();
    public Vector2 Velocity=new Vector2();
    public Image Drawer=null;
    public int ExistTime;

    public float x=0;
    public float y=0;
    public int missTime=0;
    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int animTime=0;

    public static BaseMyPlane Instance;
    public JudgeCircleAnimation animation=null;
    public Image judgeAnim=null;
    public boolean missed=false;
    public boolean b=false;

    public void Init(){

        //	if (!MainScreen.gameOver) {
        //获取一个Image
        judgeAnim=Pools.ImagePool.obtain();
        judgeAnim.setDrawable(getJudgeAnim());
        Drawer=Pools.ImagePool.obtain();
        Drawable drawable=getDrawable();
        //设置材质
        Drawer.setDrawable(drawable);
        //加入舞台中
        MainScreen.MainGroup.addActor(Drawer);
        MainScreen.MainGroup.addActor(judgeAnim);
        ExistTime=0;
        Instance=this;
        judgeAnim.setSize(32,32);
        Drawer.setSize(32,32);
        Center.set(270,80);
        //	}
    }

    private Drawable getJudgeAnim(){
        if(animation==null){
            animation=new JudgeCircleAnimation();
        }
        return animation;
    }

    public void Kill(){
        if(!onUnmatched){
            //		MainScreen.gameOver=true;
            missTime++;
            missed=true;
            b=false;
            Pools.ImagePool.free(judgeAnim);
            //Pools.ImagePool.free(Drawer);
            switch(MainScreen.playerFlag){
                case Data.playerFlagReimu:

                    break;
                case Data.playerFlagAlice:
                    Drawer.remove();
                    MyPlaneAlice.Alice.Drawer.remove();
                    Pools.ImagePool.free(Drawer);
                    Pools.ImagePool.free(MyPlaneAlice.Alice.Drawer);
                    //   if(!MainScreen.gameOver)
                    new MyPlaneAlice().Init();
                    break;
            }
            //Drawer.remove();
            //将Drawer从舞台上撤下并且将其回归到Pool中
            //Pools.ImagePool.free(Drawer);
        }
    }

    public void Update(){
        //	if (!MainScreen.gameOver) {
        Center.x=x=MathUtils.clamp(Center.x,0,MainScreen.Width);
        Center.y=y=MathUtils.clamp(Center.y,0,MainScreen.Height);
        Center.add(Velocity);
        Drawer.setPosition(Center.x,Center.y,Align.center);
        judgeAnim.setPosition(Center.x,Center.y,Align.center);
        judgeAnim.setDrawable(getJudgeAnim());
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
        Drawer.toFront();
        judgeAnim.toFront();
        animation.Update();
        //	}
    }

    public abstract void bomb();

    public float[] getPosition(){
        return new float[]{Center.x,Center.y};
    }

    ;

    protected abstract Drawable getDrawable();
}
