package com.meng.stg2.bullets.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.helpers.*;

public class ReimuShoot extends BaseMyBullet {

    @Override
	public void init(Vector2 center, Vector2 velocity) {
		super.init(center, velocity);
		drawable = ResourcesManager.textures.get(TextureNameManager.ReimuBullet);
	}

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

	@Override
	public void kill() {
		super.kill();
		ObjectPool.recycle(this);
	}

    @Override
    public void update() {
        super.update();
	}

    @Override
    public Vector2 getSize() {
        return new Vector2(64, 16);
	}

    @Override
    public float getRotationDegree() {
        return 90;
	}
}
