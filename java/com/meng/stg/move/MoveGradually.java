package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveGradually extends BaseMoveMethod{

    private float speedXPerFrame=1;
    private float speedYPerFrame=1;

    public MoveGradually(int inFrame,int afterFrames,Vector2 fromSpeed,Vector2 toSpeed){
        time=inFrame;
		this.afterFrames=afterFrames;
        velocity=fromSpeed;
        speedXPerFrame=(toSpeed.x-fromSpeed.x)/time;
        speedYPerFrame=(toSpeed.y-fromSpeed.y)/time;
    }

    @Override
    public Vector2 getVelocity(){
        return velocity;
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public void update(){
	  if(afterFrames>0){
		--afterFrames;
		return;
	  }
        velocity.add(speedXPerFrame,speedYPerFrame);
        time--;
    }
}
