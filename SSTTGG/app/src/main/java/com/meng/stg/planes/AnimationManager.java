package com.meng.stg.planes;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.stg.*;
import com.meng.stg.planes.myPlane.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class AnimationManager{
	private BaseGameObject gameObject=null;
	private int animFrom=0;
	private int animTo=7;
	private int everyAnimFrameTime=0;
	private int time=0;
	private Drawable currentFrame=null;
	private int curFrameNumber=0;
	private MoveStatus status=MoveStatus.stay;

	public AnimationManager(BaseGameObject obj,int everyAnimFrameTime){
		gameObject=obj;
		this.everyAnimFrameTime=everyAnimFrameTime;
	  }

	public void setStatus(MoveStatus mov){
	  if(mov==status)return;
		status=mov;
		if(status==MoveStatus.stay){
			animFrom=0;
			animTo=7;
			curFrameNumber=animFrom;
		  }else if(status==MoveStatus.leftMove){
			animFrom=8;
			animTo=15;
			  curFrameNumber=animFrom;
		  }else{
			animFrom=16;
			animTo=23;
			  curFrameNumber=animFrom;
		  }
	  }

	public void update(){
		++time;
		if(time>=everyAnimFrameTime){
			++curFrameNumber;
			time=0;
		  }
		if(curFrameNumber>animTo){
			curFrameNumber=animFrom+4;
		  }
		currentFrame=ResourcesManager.textures.get("reimu"+curFrameNumber);
	  }

	public Drawable getImage(){
		return currentFrame;
	  }
  }
