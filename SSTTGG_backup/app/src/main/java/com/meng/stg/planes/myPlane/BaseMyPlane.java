package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.AnimationManager;
import com.meng.stg.planes.JudgeCircleAnimation;
import com.meng.stg.planes.JudgeCircleAnimation2;
import com.meng.stg.planes.MoveStatus;
import com.meng.stg.planes.subPlane.BaseSubPlane;
import com.meng.stg.ui.MainScreen;

/*
 base class of my plane
 */
public abstract class BaseMyPlane extends BaseGameObject{

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int power=4;

    public static JudgeCircleAnimation animation=null;
    public static JudgeCircleAnimation2 animation2=null;
    public static Image judgeAnim=null;
    public static Image judgeAnim2=null;
    private float playerLastX=270;
    public boolean slow=false;

    public AnimationManager animationManager;
    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public void Init(){
        instance=this;
        if(animation==null){
            animation=new JudgeCircleAnimation();
        }
        if(judgeAnim==null){
            judgeAnim=new Image();// Pools.imagePool.obtain();
            judgeAnim=animation.getImage();
            judgeAnim.setSize(48,48);
        }
        if(animation2==null){
            animation2=new JudgeCircleAnimation2();
        }
        if(judgeAnim2==null){
            judgeAnim2=Pools.imagePool.obtain();
            judgeAnim2=animation2.getImage();
            judgeAnim2.setSize(48,48);
        }
        image= Pools.imagePool.obtain();
        existTime=0;
        objectCenter.set(MainScreen.width/2,80);
        image.setSize(30,46);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        judgeAnim.setOrigin(judgeAnim.getWidth()/2,judgeAnim.getHeight()/2);
        judgeAnim2.setOrigin(judgeAnim2.getWidth()/2,judgeAnim2.getHeight()/2);
        unmatchedTime=1;
        onUnmatched=true;
        MainScreen.mainGroup.addActor(image);
        MainScreen.mainGroup.addActor(judgeAnim);
        MainScreen.mainGroup.addActor(judgeAnim2);
    }

    public void Kill(){
    }

    public void update(){
        super.update();
        animFlag++;
        objectCenter=new Vector2(MathUtils.clamp(objectCenter.x,0,MainScreen.width),MathUtils.clamp(objectCenter.y,0,MainScreen.height));
        if(slow){
            judgeAnim.setSize(48,48);
            judgeAnim2.setSize(48,48);
        }else{
            judgeAnim.setSize(0,0);
            judgeAnim2.setSize(0,0);
        }
        judgeAnim.setOrigin(judgeAnim.getWidth()/2,judgeAnim.getHeight()/2);
        judgeAnim2.setOrigin(judgeAnim2.getWidth()/2,judgeAnim2.getHeight()/2);

        judgeAnim.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeAnim2.setPosition(objectCenter.x,objectCenter.y,Align.center);
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
            animationManager.setStatus(MoveStatus.moveRight);
        }else if(objectCenter.x<playerLastX){
            playerLastX=objectCenter.x;
            animationManager.setStatus(MoveStatus.moveLeft);
        }else{
            animationManager.setStatus(MoveStatus.stay);
        }

        animationManager.update();
        image.toBack();
        judgeAnim2.toFront();
        judgeAnim.toFront();

        animation.update();
        animation2.update();
    }

    public abstract void bomb();

    public abstract void shoot();
}