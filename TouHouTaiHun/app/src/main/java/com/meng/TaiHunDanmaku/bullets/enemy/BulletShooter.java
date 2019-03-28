package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.task.*;

import java.util.*;
import java.util.concurrent.*;

public class BulletShooter {

    private BaseEnemyPlane baseEnemyPlane;

    public static HashSet<BulletShooter> instances = new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toDelete = new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toAdd = new LinkedBlockingQueue<BulletShooter>();
    private int time = 0;
    private Vector2 bulletCenter = new Vector2(200, 200);
    public Vector2 bulletVelocity = new Vector2(1, 0);
    private float bulletSpeed = 1;
    private float bulletRotation = 0;
    private BulletForm bf = BulletForm.lindan;
    private BulletColor bc = BulletColor.white;
    public Vector2 offset = new Vector2(0, 0);
    private int inFrame = 1;
    private int ways = 1;
    private float waysDegree = 0;
    private int cengShu = 1;
    private float cengDanSuCha = 0.1f;
    private boolean straightMove = true;
    private int reflex = 0;
    private int through = 0;
    private float beilv = 1;
    private int afterFrames = 0;
    private float randomX = 0;
    private float randomY = 0;
    private boolean useRandomCenter = false;
    private boolean useRandomDegree = false;
    private float randomDegree = 0;
    private Random ran = new Random();
    private BulletStyle bulletStyle = BulletStyle.normal;


    public BulletShooter() {
    }

    public BulletShooter setBulletStyle(BulletStyle bulletStyle) {
        this.bulletStyle = bulletStyle;
        return this;
    }

    public BulletShooter setThrough(int through) {
        this.through = through;
        return this;
    }

    public BulletShooter setUseRandomDegree(boolean useRandomDegree) {
        this.useRandomDegree = useRandomDegree;
        return this;
    }

    public BulletShooter setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
        return this;
    }

    public BulletShooter setBulletRotation(float bulletRotation) {
        this.bulletRotation = bulletRotation;
        return this;
    }

    public BulletShooter setRandomDegree(float randomDegree) {
        setUseRandomDegree(true);
        this.randomDegree = randomDegree;
        return this;
    }

    public BulletShooter setUseRandomCenter(boolean useRandomCenter) {
        this.useRandomCenter = useRandomCenter;
        return this;
    }

    public BulletShooter setRandomCenter(float x, float y) {
        setUseRandomCenter(true);
        randomX = x;
        randomY = y;
        return this;
    }

    public BulletShooter init() {
        toAdd.add(this);
        return this;
    }

    public BulletShooter setOffset(Vector2 v) {
        offset = v;
        return this;
    }

    public BulletShooter setBaseEnemyPlane(BaseEnemyPlane baseEnemyPlane) {
        this.baseEnemyPlane = baseEnemyPlane;
        return this;
    }

    public BulletShooter setReflex(int reflex) {
        this.reflex = reflex;
        return this;
    }

    public BulletShooter setCengDanSuCha(float cengDanSuCha) {
        this.cengDanSuCha = cengDanSuCha;
        return this;
    }

    public BulletShooter setStraightMove(boolean straightMove) {
        this.straightMove = straightMove;
        return this;
    }

    public BulletShooter setWays(int ways) {
        this.ways = ways;
        return this;
    }

    public BulletShooter setCengShu(int cengShu) {
        this.cengShu = cengShu;
        return this;
    }

    public BulletShooter setWaysDegree(float waysDegree) {
        this.waysDegree = waysDegree;
        return this;
    }

    public BulletShooter setBulletCenter(Vector2 bulletCenter) {
        this.bulletCenter = bulletCenter;
        return this;
    }

    public BulletShooter setBulletColor(BulletColor bc) {
        this.bc = bc;
        return this;
    }

    public BulletShooter setBulletForm(BulletForm bf) {
        this.bf = bf;
        return this;
    }

    public BulletShooter setInFrame(int inFrame) {
        this.inFrame = inFrame;
        return this;
    }

    public void update() {
        ++time;
        if (baseEnemyPlane.judgeCircle == null) {
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
        for (BulletShooter shooter : instances) {
            shooter.update();
        }
    }

    public void shoot() {
        if (afterFrames > 0) {
            --afterFrames;
            return;
        }
        beilv = 1;
        if (useRandomDegree) {
            bulletVelocity.rotate(bulletVelocity.angle() + ran.nextFloat() * randomDegree);
        }
        if (bulletStyle == BulletStyle.snipe) {
            bulletVelocity = BaseMyPlane.instance.objectCenter.cpy().sub(bulletCenter).nor().scl(bulletSpeed);
        } else if (bulletStyle == bulletStyle.round) {
            setWaysDegree(360f / ways);
            bulletVelocity.nor().scl(bulletSpeed);
            //      moveMethods=straightMove?new BaseMoveMethod[]{new MoveMethodStraight(inFrame,15,bulletVelocity)}:moveMethods;
        } else {
            bulletVelocity.nor().scl(bulletSpeed);
        }
        float nowCenterX = -randomX / 2 + ran.nextFloat() * randomX;
        float nowCenterY = -randomY / 2 + ran.nextFloat() * randomY;
        for (int ceng = 0; ceng < cengShu; ++ceng) {
            float allAngle = (ways - 1) * waysDegree;
            Vector2 tmpv = bulletVelocity.cpy().scl(beilv);
            tmpv.rotate(-allAngle / 2);
            for (int i = 0; i < ways; i++) {
                if (useRandomCenter) {
                    float finalX = bulletCenter.x + offset.x + nowCenterX;
                    float finalY = bulletCenter.y + offset.y + nowCenterY;
                    Vector2 tmp = tmpv.cpy().scl(1000);
                    EnemyBullet.create(new Vector2(finalX, finalY), bf, bc, bulletSpeed, reflex, through, new Task[]{
                            new TaskMove(tmp.x + finalX, tmp.y + finalY)
                    });
                } else {
                    float finalX = bulletCenter.x + offset.x;
                    float finalY = bulletCenter.y + offset.y;
                    Vector2 tmp = tmpv.cpy().scl(1000);
                    EnemyBullet.create(new Vector2(finalX, finalY), bf, bc, bulletSpeed, reflex, through, new Task[]{
                            new TaskMove(tmp.x + finalX, tmp.y + finalY)
                    });
                }
                tmpv.rotate(waysDegree);
            }
            beilv += cengDanSuCha;
        }
    }
}
