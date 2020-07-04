package com.meng.stg2.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.meng.stg2.move.*;
import com.meng.stg2.characters.enemy.*;
import com.meng.stg2.characters.player.*;
import com.meng.stg2.task.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/1/25.
 */

public class Danmaku {

    private BaseEnemyPlane baseEnemyPlane;

    public static HashSet<Danmaku> instances=new HashSet<Danmaku>();
    public static LinkedBlockingQueue<Danmaku> toDelete=new LinkedBlockingQueue<Danmaku>();
    public static LinkedBlockingQueue<Danmaku> toAdd=new LinkedBlockingQueue<Danmaku>();
    private int time=0;
    private Vector2 bulletCenter=new Vector2(200, 200);
    public Vector2 bulletVelocity=new Vector2(1, 0);
    private float bulletSpeed=1;
    private float bulletRotation=0;
    private BulletForm bf=BulletForm.lindan;
    private BulletColor bc=BulletColor.white;
    private Vector2 offset=new Vector2(0, 0);
    private int inFrame=1;
    private int ways=1;
    private float waysDegree=0;
    private boolean straightMove=true;
    private int reflex=0;
    private int through=0;
    private int afterFrames=0;
    private float randomX=0;
    private float randomY=0;
    private boolean useRandomCenter=false;
    private boolean useRandomDegree=false;
    private float randomDegree=0;
    private Random ran=new Random();
    private BulletStyle bulletStyle=BulletStyle.normal;
	private boolean highLight = false;

    private BaseMoveMethod[] moveMethods=new BaseMoveMethod[]{new MoveMethodStraight()};

    public Danmaku() {
	}

	public Danmaku setHighLight(boolean highLight) {
		this.highLight = highLight;
		return this;
	}

    public Danmaku setBulletStyle(BulletStyle bulletStyle) {
        this.bulletStyle = bulletStyle;
        return this;
	}

    public Danmaku setThrough(int through) {
        this.through = through;
        return this;
	}

    public Danmaku setUseRandomDegree(boolean useRandomDegree) {
        this.useRandomDegree = useRandomDegree;
        return this;
	}

    public Danmaku setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
        return this;
	}

    public Danmaku setBulletRotation(float bulletRotation) {
        this.bulletRotation = bulletRotation;
        return this;
	}

    public Danmaku setRandomDegree(float randomDegree) {
        setUseRandomDegree(true);
        this.randomDegree = randomDegree;
        return this;
	}

    public Danmaku setUseRandomCenter(boolean useRandomCenter) {
        this.useRandomCenter = useRandomCenter;
        return this;
	}

    public Danmaku setRandomCenter(float x, float y) {
        setUseRandomCenter(true);
        randomX = x;
        randomY = y;
        return this;
	}

    public Danmaku init() {
        toAdd.add(this);
        return this;
	}

    public Danmaku setOffset(Vector2 v) {
        offset = v;
        return this;
	}

    public Danmaku setEnemy(BaseEnemyPlane baseEnemyPlane) {
        this.baseEnemyPlane = baseEnemyPlane;
        return this;
	}

    public Danmaku setReflex(int reflex) {
        this.reflex = reflex;
        return this;
	}

    public Danmaku setStraightMove(boolean straightMove) {
        this.straightMove = straightMove;
        return this;
	}

    public Danmaku setMoveMethods(BaseMoveMethod... moveMethods) {
        this.moveMethods = moveMethods;
        return this;
	}

    public Danmaku setWays(int ways) {
        this.ways = ways;
        return this;
	}

    public Danmaku setWaysDegree(float waysDegree) {
        this.waysDegree = waysDegree;
        return this;
	}

    public Danmaku setBulletCenter(Vector2 bulletCenter) {
        this.bulletCenter = bulletCenter;
        return this;
	}

    public Danmaku setBulletColor(BulletColor bc) {
        this.bc = bc;
        return this;
	}

    public Danmaku setBulletForm(BulletForm bf) {
        this.bf = bf;
        return this;
	}

    public Danmaku setInFrame(int inFrame) {
        this.inFrame = inFrame;
        return this;
	}

    public void update() {
        ++time;
        if (baseEnemyPlane.judge == null) {
            kill();
		}
	}

    public void kill() {
        toDelete.add(this);
	}

    public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
		}
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
		}
        for (Danmaku shooter : instances) {
            shooter.update();
		}
	}

    public void shoot() {
        if (afterFrames > 0) {
            --afterFrames;
            return;
		}
        if (useRandomDegree) {
            bulletVelocity.rotate(bulletVelocity.angle() + ran.nextFloat() * randomDegree);
		}
        if (bulletStyle == BulletStyle.snipe) {
            bulletVelocity = BaseMyPlane.instance.position.cpy().sub(bulletCenter).nor().scl(bulletSpeed);
		} else if (bulletStyle == bulletStyle.round) {
			setWaysDegree(360f / ways);
			bulletVelocity.nor().scl(bulletSpeed);
			//      moveMethods=straightMove?new BaseMoveMethod[]{new MoveMethodStraight(inFrame,15,bulletVelocity)}:moveMethods;
		} else {
			bulletVelocity.nor().scl(bulletSpeed);
		}
        float nowCenterX=-randomX / 2 + ran.nextFloat() * randomX;
        float nowCenterY=-randomY / 2 + ran.nextFloat() * randomY;
		float allAngle=(ways - 1) * waysDegree;
		Vector2 tmpv=bulletVelocity.cpy();
		tmpv.rotate(-allAngle / 2);
		for (int i=0;i < ways;i++) {
			float finalX=0;
			float finalY=0;
			if (useRandomCenter) {
				finalX = bulletCenter.x + offset.x + nowCenterX;
				finalY = bulletCenter.y + offset.y + nowCenterY;
			} else {
				finalX = bulletCenter.x + offset.x;
				finalY = bulletCenter.y + offset.y;
			}
			Vector2 tmp=tmpv.cpy().scl(1000);
			EnemyBullet.create(new Vector2(finalX, finalY), bf, bc, bulletSpeed, reflex, through, highLight, new Task[]{
								   new TaskMove(tmp.x + finalX, tmp.y + finalY)
							   });
			tmpv.rotate(waysDegree);
		}
	}
}
