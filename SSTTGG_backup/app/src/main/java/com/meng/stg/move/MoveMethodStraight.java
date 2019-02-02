package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveMethodStraight extends BaseMoveMethod{

    public MoveMethodStraight(int inFrame,Vector2 velo){
        time=inFrame;
        velocity=velo;
    }

	public MoveMethodStraight(){
	  time=1;
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
        time--;
    }
}
