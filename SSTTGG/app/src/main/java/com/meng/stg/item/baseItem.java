package com.meng.stg.item;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;

public abstract class BaseItem extends BaseGameObject{

	public int time=0;


	public void init(Vector2 center){
        super.init();

        objectCenter.set(center);
        size=getSize();
        image.setRotation(0);
		image.setDrawable(getDrawable());
		moveManager=new MoveManager(this,new MoveMethodStraight(1,1,new Vector2(0,-1)));
        image.setSize(size.x,size.y);
        judgeCircle=new Circle(objectCenter,image.getWidth()/4);
		MainScreen.mainGroup.addActor(image);   
	  }

	public abstract Vector2 getSize();

	public Shape2D getCollisionArea(){
        return judgeCircle;
	  }
	  
	public abstract Drawable getDrawable();
	
    public Shape2D getJudgeCircle(){
        return judgeCircle;
	  }  

	public void kill(){
        super.kill();
        image.remove();
        judgeCircle=null;
	  }

	public void update(){
        super.update();
        time++;
        animFlag++;
        moveManager.update();
        objectCenter.add(velocity);
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle.setPosition(objectCenter.x,objectCenter.y);
        if(judgeCircle.x<-5||judgeCircle.x>390
		   ||judgeCircle.y<-5||judgeCircle.y>460){
            kill();
		  }else{
            judge();
		  }
	  }
    public void judge(){
		//     if(getCollisionArea().contains(BaseMyPlane.instance.objectCenter)){
		//        hit(10.5f);
		//         BaseMyPlane.instance.kill();
		//	  }
	  }

  }
