package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.planes.AnimationManager;
import com.meng.stg.planes.JudgeCircleAnimation;
import com.meng.stg.planes.JudgeCircleAnimation2;
import com.meng.stg.planes.MoveStatus;
import com.meng.stg.planes.subPlane.BaseSubPlane;
import com.meng.stg.ui.MainScreen;

public abstract class BaseMyPlane extends BaseGameObject{

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int power=4;

    public JudgeCircleAnimation animation=null;
    public JudgeCircleAnimation2 animation2=null;

    private float playerLastX=270;
    public boolean slow=false;

    public AnimationManager animationManager;
    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public void init(){
		super.init();
        instance=this;
		animation=new JudgeCircleAnimation();
		animation.init();
		animation2=new JudgeCircleAnimation2();
		animation2.init();
        existTime=0;
        objectCenter.set(MainScreen.width/2,80);
        image.setSize(30,46);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
		unmatchedTime=1;
        onUnmatched=true;
        MainScreen.mainGroup.addActor(image);
	  }

    public void kill(){
		super.kill();

	  }

    public void update(){
        super.update();
        animFlag++;
        objectCenter=new Vector2(MathUtils.clamp(objectCenter.x,0,MainScreen.width),MathUtils.clamp(objectCenter.y,0,MainScreen.height));

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


        animation2.update();
        animation.update();
	  }

    public abstract void bomb();

    public abstract void shoot();
  }
