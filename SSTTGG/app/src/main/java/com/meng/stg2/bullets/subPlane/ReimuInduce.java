package com.meng.stg2.bullets.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg2.bullets.BaseMyBullet;
import com.meng.stg2.helpers.ResourcesManager;
import com.meng.stg2.helpers.TextureNameManager;
import com.meng.stg2.characters.enemy.BaseEnemyPlane;
import com.meng.stg2.ui.MainScreen;
import com.meng.stg2.helpers.*;

public class ReimuInduce extends BaseMyBullet {

    private Vector2 nearestEnemyPosition=new Vector2(0, 1);
    private final Vector2 targetOnEnemy=new Vector2(0, 2);
    private final Vector2 noEnemy=new Vector2(1000000000, 1000000000);

	@Override
	public void init(Vector2 center, Vector2 velocity) {
		super.init(center, velocity);
		drawable = ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce);
	}

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

    @Override
    public void update() {
        image.setRotation(getRotationDegree());
        image.setPosition(position.x, position.y, Align.center);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);    
        judgeCircle.setPosition(position);
		if (judgeCircle.x < -5 || judgeCircle.x > 390 || judgeCircle.y < -5 || judgeCircle.y > 460) {         
			kill();
		} else {		
			judge();
		}
        nearestEnemyPosition.set(noEnemy);
        for (BaseEnemyPlane bep : MainScreen.enemys) {
            if (bep == null) {
                continue;
            }
            if (bep.getLocation().cpy().sub(position).len2() < nearestEnemyPosition.cpy().sub(position).len2()) {
                nearestEnemyPosition.set(bep.getLocation());
            }
        }
        if (nearestEnemyPosition.x == noEnemy.x && nearestEnemyPosition.y == noEnemy.y) {
            position.add(velocity);
        } else {
            position.add(targetOnEnemy).add(nearestEnemyPosition.sub(position).nor().scl(8));
        }
    }

    @Override
    public float getRotationDegree() {
        return nearestEnemyPosition.x == noEnemy.x && nearestEnemyPosition.y == noEnemy.y ?velocity.angle(): nearestEnemyPosition.angle();
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(16, 16);
	}

	@Override
	public void kill() {
		super.kill();
		ObjectPool.recycle(this);
	}
}
