package com.meng.stg2.move;

import com.badlogic.gdx.math.Vector2;

public class MoveMethodStraight extends BaseMoveMethod{

    public MoveMethodStraight(int inFrame,int afterFrames,Vector2 velo){
        time=inFrame;
		this.afterFrames=afterFrames;
        velocity=velo;
    }

	public MoveMethodStraight(){
	  time=1;
	  afterFrames=1;
	  velocity=new Vector2(0,-1);
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
        time--;
    }
}
