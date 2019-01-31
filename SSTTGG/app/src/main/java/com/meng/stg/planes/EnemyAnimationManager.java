package com.meng.stg.planes;

import com.meng.stg.*;
import com.meng.stg.planes.enemyPlane.*;

public class EnemyAnimationManager{

	private BaseEnemyPlane bep;
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;
    private String objectName="";
    private boolean flip=false;
    private int[][] animNum;
    private final int[][] bossAnim=new int[][]{
		  {96,100},
		  {101,107}
	  };

    public EnemyAnimationManager(BaseEnemyPlane obj,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;	
		bep=obj;
		objectName="zayu"; 
		animNum=bossAnim;
	  }

    public void setStatus(MoveStatus mov){
        if(mov==status){
            return;
		  }
        status=mov;     
		switch(status){
			case stay:
			  flip=false;
			  animFrom=animNum[0][0];
			  animTo=animNum[0][1];
			  curFrameNumber=animFrom;
			  break;
			case leftMove:
			  flip=true;
			  animFrom=animNum[1][0];
			  animTo=animNum[1][1];
			  curFrameNumber=animFrom;
			  break;
			case rightMove:		  
			  flip=false;
			  animFrom=animNum[1][0];
			  animTo=animNum[1][1];
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
            curFrameNumber=animFrom+4;
		  }
        if(flip){
            bep.image.setDrawable(ResourcesManager.flipedTextures.get(objectName+curFrameNumber));
		  }else{
			bep.image.setDrawable(ResourcesManager.textures.get(objectName+curFrameNumber));
		  }
	  }
  }
