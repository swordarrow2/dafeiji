package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.*;

public class ReflexAndThrough {
    public Rectangle rectangle;

    public ReflexAndThrough(float x, float y, float w, float h) {
        rectangle = new Rectangle(x, y, w, h);
    }

    public void update() {
        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
            if (rectangle.contains(baseBullet.objectCenter)) {
                if (baseBullet.refCount > 0) {
                    --baseBullet.refCount;
                    baseBullet.objectCenter.x -= baseBullet.velocity.x;
                    baseBullet.velocity.x = -baseBullet.velocity.x;
                }
            }
        }
    }
}
