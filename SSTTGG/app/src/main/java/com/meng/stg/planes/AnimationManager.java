package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.BaseGameObject;
import com.meng.stg.ResourcesManager;
import com.meng.stg.planes.myPlane.BaseMyPlane;

public class AnimationManager{
	private BaseMyPlane bmp;
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;
    
    public AnimationManager(BaseMyPlane obj,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;   
		bmp=obj;
	  }

    public void setStatus(MoveStatus mov){
        if(mov==status){
            return;
		  }
        status=mov;      
            switch(status){
                case stay:				 
				  animFrom=0;
				  animTo=7;
				  curFrameNumber=animFrom;
				  break;
                case leftMove:				 
				  animFrom=8;
				  animTo=15;
				  curFrameNumber=animFrom;
				  break;
                case rightMove:				
				  animFrom=16;
				  animTo=23;
				  curFrameNumber=animFrom;
				  break;
			  }
	  }

    public void update(){
        ++time;
        if(time>=everyAnimFrameTime){
            ++curFrameNumber;
            time=0;
		  }
        if(curFrameNumber>animTo){
            curFrameNumber=animFrom+5;
		  }        			
		bmp.image.setDrawable(ResourcesManager.textures.get("reimu"+curFrameNumber));		  
	  }
  }
