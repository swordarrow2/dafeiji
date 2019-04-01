package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.BaseGameObject;

public abstract class BaseBullet extends BaseGameObject{


    public void init(){
        super.init();
        size=getSize();
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        existTime=0;
	  }

    public void kill(){
		super.kill();
	  }

    public void update(){
        super.update();
		//    
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        judgeCircle.setPosition(objectCenter);
        if(judgeCircle.x<-5||judgeCircle.x>390||judgeCircle.y<-5||judgeCircle.y>460){
            kill();
		  }else{
            judge();
		  }
	  }


    public Shape2D getCollisionArea(){
        return judgeCircle;
	  }

    public abstract Drawable getDrawable();

    public abstract void judge();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();
  }
