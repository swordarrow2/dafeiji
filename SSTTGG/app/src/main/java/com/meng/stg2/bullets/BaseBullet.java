package com.meng.stg2.bullets;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;

public abstract class BaseBullet extends BaseGameObject {

	public Circle judgeCircle = new Circle();
	
    public void init() {
        super.init();
        size = getSize();
        image.setSize(size.x, size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        existTime = 0;
    }

    public void kill() {
		super.kill();
    }

    public void update() {
        super.update(); 
        image.setRotation(getRotationDegree());
        image.setPosition(position.x, position.y, Align.center);
        //image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        judgeCircle.setPosition(position);
        if (position.x < -5 || position.x > 390 || position.y < -5 || position.y > 460) {
            kill();
        } else {
            judge();
        }
    }


    public Shape2D getCollisionArea() {
        return judgeCircle;
    }

    public abstract Drawable getDrawable();

    public abstract void judge();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();
}
