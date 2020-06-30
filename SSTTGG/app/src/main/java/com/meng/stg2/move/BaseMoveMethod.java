package com.meng.stg2.move;

import com.badlogic.gdx.math.Vector2;

public abstract class BaseMoveMethod{

    public Vector2 velocity;
    public int time=0;
	public int afterFrames=0;

    public abstract Vector2 getVelocity();

    public abstract int getTime();

    public abstract void update();

}
