package com.meng.stg2.bullets.myPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg2.bullets.BaseMyBullet;
import com.meng.stg2.helpers.ResourcesManager;

import static com.meng.stg2.ui.MainScreen.enemys;
import com.meng.stg2.bullets.*;
import com.meng.stg2.bullets.enemy.*;

public class ReimuSpell extends BaseMyBullet {

    @Override
    public Drawable getDrawable() {
        if (drawable == null) {
            drawable = ResourcesManager.textures.get("reimu55");
        }
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
