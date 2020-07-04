package com.meng.stg2.bullets.subPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.characters.player.*;
import com.meng.stg2.helpers.*;

import static com.meng.stg2.ui.MainScreen.enemys;

public class PersuasionNeedle extends BaseMyBullet {

    @Override
	public void init(Vector2 center, Vector2 velocity) {
		super.init(center, velocity);
		drawable = ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight);
	}

    @Override
    public Drawable getDrawable() {
		return drawable;
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(64, 16);
	}

    public void judge() {
		for (int i=0;i < 32;i++) {
			if (enemys[i] != null) {
				if (getCollisionArea().overlaps(enemys[i].getCollisionArea())) {
					enemys[i].hit(BaseMyPlane.instance.slow ?17.5f: 10.5f);
					kill();
				}
			}
		}
	}

	@Override
	public void kill() {
		super.kill();
		ObjectPool.recycle(this);
	}

    @Override
    public float getRotationDegree() {
        return 90;
	}
}
