package com.meng.stg2.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg2.ui.MainScreen;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

import static com.meng.stg2.ui.MainScreen.enemys;


public abstract class BaseMyBullet extends BaseBullet {

    public static HashSet<BaseMyBullet> instances=new HashSet<BaseMyBullet>();
    public static LinkedBlockingQueue<BaseMyBullet> toDelete=new LinkedBlockingQueue<BaseMyBullet>();
    public static LinkedBlockingQueue<BaseMyBullet> toAdd=new LinkedBlockingQueue<BaseMyBullet>();

    public abstract Drawable getDrawable();

    public void init(Vector2 center, Vector2 velocity) {
        super.init();
        toAdd.add(this);
        position.set(center);
        this.velocity.set(velocity);
        image.setPosition(position.x, position.y, Align.center);
        judgeCircle.set(position, image.getHeight() / 2 * 3); //中心、半径
        image.setDrawable(getDrawable());
        MainScreen.instence.groupNormal.addActor(image);
    }

    @Override
    public void kill() {
        super.kill();
        toDelete.add(this);
        image.remove();
	}

	@Override
	public void update() {
		position.add(velocity);
		super.update();
	}

    public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
        }
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
        }
        for (BaseMyBullet baseBullet : instances) {
            baseBullet.update();
        }
    }

    public void judge() {
		for (int i=0;i < 32;i++) {
			if (enemys[i] != null) {
				if (getCollisionArea().overlaps(enemys[i].getCollisionArea())) {
					enemys[i].hit(10.5f);
					kill();
				}
			}
		}
    }
}
