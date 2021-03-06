package com.meng.stg2.bullets.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.helpers.*;

import static com.meng.stg2.ui.MainScreen.enemys;

public class ReimuSpell extends BaseMyBullet {

    @Override
	public void init(Vector2 center, Vector2 velocity) {
		super.init(center, velocity);
		drawable = ResourcesManager.textures.get("reimu55");
	}

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(64, 16);
    }

	@Override
	public void kill() {
		super.kill();
		ObjectPool.recycle(this);
	}

    public void judge() {
		for (int i=0;i < 32;i++) {
			if (enemys[i] != null) {
				if (getCollisionArea().overlaps(enemys[i].getCollisionArea())) {
					enemys[i].hit(70.5f);
				}
			}
		}
		for (BaseEnemyBullet eb:BaseEnemyBullet.instances) {
			if (getCollisionArea().contains(eb.position)) {
				eb.kill(BulletKillMode.killWithScorePointAndCollect);
			}
		}
    }

    @Override
    public float getRotationDegree() {
        return 90;
    }
}
