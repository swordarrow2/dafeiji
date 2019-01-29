package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveGradually extends BaseMoveMethod{
   private float originSpeedX=1;
   private float originSpeedY=1;
	private float speedXPerFrame=1;
	private float speedYPerFrame=1;
	
    public MoveGradually(int inFrame,Vector2 fromSpeed,Vector2 toSpeed){
        time=inFrame;
        velocity=fromSpeed;
        originSpeedX=velocity.x;
		originSpeedY=velocity.y;
		speedXPerFrame=(toSpeed.x- velocity.x)/time;
		speedYPerFrame=(toSpeed.y-velocity.y)/time;
		
    }

    @Override
    public Vector2 getVelocity(){
        return new Vector2(originSpeedX,originSpeedY);
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public void update(){
	  originSpeedX+=speedXPerFrame;
		originSpeedY+=speedYPerFrame;
        time--;
    }
}
