package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;


public class MoveMethodCircle extends BaseMoveMethod{

    private float degreePerFrame=0;
    private float degree=0;
    private boolean clock=false;

    public MoveMethodCircle(int inFrame,float degree,boolean clock,Vector2 velo){
        time=inFrame;
        this.clock=clock;
        this.degree=degree;
        velocity=velo;
        if(clock){
            degreePerFrame=-degree/inFrame;
        }else{
            degreePerFrame=degree/inFrame;
        }
    }

    @Override
    public Vector2 getVelocity(){
        return velocity.rotate(velocity.angle()+degreePerFrame);
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
