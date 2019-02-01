package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveGradually extends BaseMoveMethod{

    private float speedXPerFrame=1;
    private float speedYPerFrame=1;

    public MoveGradually(int inFrame,Vector2 fromSpeed,Vector2 toSpeed){
        time=inFrame;
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
        velocity.add(speedXPerFrame,speedYPerFrame);
        time--;
    }
}
