package com.meng.stg.move;

import com.badlogic.gdx.math.Vector2;

public abstract class BaseMoveMethod{

    public Vector2 velocity;
    public int time;

    public abstract Vector2 getVelocity();

    public abstract int getTime();

    public abstract void update();

}
