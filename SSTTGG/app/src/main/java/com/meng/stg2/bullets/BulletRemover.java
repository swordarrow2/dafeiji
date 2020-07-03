package com.meng.stg2.bullets;

import com.badlogic.gdx.math.*;
import com.meng.stg2.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.effects.*;
import java.util.*;
import java.util.concurrent.*;

public  class BulletRemover extends BaseGameObject {

	public static HashSet<BulletRemover> instances=new HashSet<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toDelete=new LinkedBlockingQueue<BulletRemover>();
    public static LinkedBlockingQueue<BulletRemover> toAdd=new LinkedBlockingQueue<BulletRemover>();
	private BulletKillMode bkm;
	public Circle judgeCircle = new Circle();
	private int flag=0;
	public void init(Vector2 center) {
        existTime = 0;
		flag = 0;
        toAdd.add(this);
        position.set(center);
        velocity.set(0, 0);
        judgeCircle.set(position, 1);
		bkm = BulletKillMode.KillOnBossLastDeath;
	}

    public void kill() {
		toDelete.add(this);
	}

	public void init() {
		flag = 1;
		toAdd.add(this);
        position.set(193, 225);
        velocity.set(0, 0);
        judgeCircle = new Circle(position, 500);
		bkm = BulletKillMode.killWithNothing;
	}

    public void update() {
        super.update();
		if (flag == 0) {
			judgeCircle.setRadius(existTime * 7);
			if (existTime > 90) {
				kill();
			}
		} else {
			if (existTime > 3) {
				kill();
			}
		}
		judge();
	}

	public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
		}
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
		}
        for (BulletRemover baseBullet : instances) {
            baseBullet.update();
		}
	}

    public void judge() {
        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
            if (judgeCircle.contains(baseBullet.position)) {
				Effect.create(baseBullet.position.cpy(), EffectType.explore);
                baseBullet.kill(bkm);
			}
		}
	}
}
