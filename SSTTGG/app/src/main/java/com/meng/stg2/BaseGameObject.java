package com.meng.stg2;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public abstract class BaseGameObject {

    public Vector2 position = new Vector2();
	public Vector2 lastPosition = new Vector2();
    public Image image = new Image();
    public int animFlag = 0;
    public Drawable drawable = null;
    public Vector2 velocity = new Vector2();
    public int existTime = 0;
    public Vector2 size = new Vector2();

    public void update() {
        ++existTime;
	}

	public void init() {
		//  image=new Image();//ObjectPools.imagePool.obtain();
	}

	public void kill() {

	}
}
