package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveMethodCircle extends BaseMoveMethod{

    private float degreePerFrame=0;

    public MoveMethodCircle(int inFrame,int afterFrames,float degree,boolean clock,Vector2 velo){
        time=inFrame;
		this.afterFrames=afterFrames;
        velocity=velo;
        if(clock){
            degreePerFrame=-degree/inFrame;
        }else{
            degreePerFrame=degree/inFrame;
        }
    }

    @Override
    public Vector2 getVelocity(){
        return velocity.rotate(degreePerFrame);
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
        time--;
    }
}
