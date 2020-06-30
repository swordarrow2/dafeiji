package com.meng.stg2.move;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg2.BaseGameObject;

public class MoveManager{

    private BaseGameObject baseGameObject;
    private BaseMoveMethod[] moveMethodList;
    private int nowMoveMethod=0;
    private Vector2 tmpVec=new Vector2(1,0);
	
    public MoveManager(BaseGameObject baseGameObject,BaseMoveMethod... moveActions){
        this.baseGameObject=baseGameObject;
        moveMethodList=moveActions;
	  }

    public void update(){
        if(nowMoveMethod<moveMethodList.length){          
			if(moveMethodList[nowMoveMethod].getTime()>0){
				if(moveMethodList[nowMoveMethod].afterFrames==0){
					tmpVec=moveMethodList[nowMoveMethod].getVelocity();			
				  }
				moveMethodList[nowMoveMethod].update(); 
			  }else{
				nowMoveMethod++;
			  }		
			baseGameObject.velocity.set(tmpVec);
		  }   
	  }
  }
