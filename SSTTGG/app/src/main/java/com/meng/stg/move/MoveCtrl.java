package com.meng.stg.move;

import com.meng.stg.BaseGameObject;
import com.badlogic.gdx.math.*;

public class MoveCtrl{
    private BaseGameObject baseGameObject;
	private BaseMoveMethod[] moveMethodList;
	private int nowMoveMethod=0;
	private Vector2 tmpVec=new Vector2(1,0);

	public MoveCtrl(BaseGameObject baseGameObject,BaseMoveMethod... r){
		this.baseGameObject=baseGameObject;
		moveMethodList=r;
	  }

	public void update(){
		if(nowMoveMethod<moveMethodList.length){
			if(moveMethodList[nowMoveMethod].getTime()>0){
				tmpVec=moveMethodList[nowMoveMethod].getVelocity();
				moveMethodList[nowMoveMethod].update();
			  }else{
				nowMoveMethod++;
			  }	  
		  }
            baseGameObject.velocity.set(tmpVec);
	  }
  }
