package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public class MoveAcceleration extends BaseMoveMethod{
    private float toSpeed=0;

    public MoveAcceleration(int inFrame,Vector2 vector2,float toSpeed){
        time=inFrame;
        velocity=vector2;
        this.toSpeed=toSpeed;
    }

    @Override
    public Vector2 getVelocity(){
        return velocity.cpy().scl(time/toSpeed);
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
