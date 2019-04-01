package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.task.*;

import java.util.*;
import java.util.concurrent.*;

public class BulletShooter {

    public BaseEnemyPlane baseEnemyPlane;

    public static HashSet<BulletShooter> instances = new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toDelete = new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toAdd = new LinkedBlockingQueue<BulletShooter>();
    public int time = 0;
    public Vector2 bulletShooterCenter = new Vector2(200, 200);
    public Vector2 bulletVelocity = new Vector2(1, 0);
    public Vector2 bulletAcceleration = new Vector2(0, 0);
    public Vector2 shootCenterOffset = new Vector2(0, 0);
    public Vector2 shooterCenterRandomRange = new Vector2(0, 0);
    public BulletForm bulletForm = BulletForm.lindan;
    public BulletColor bulletColor = BulletColor.white;
    public BulletStyle bulletStyle = BulletStyle.normal;
    public int bulletWays = 1;
    public float bulletWaysDegree = 0;
    public int cengShu = 1;
    public float cengDanSuCha = 0.1f;
    public int reflexCount = 0;
    public int throughCount = 0;
    public float cengJianBeiLv = 1;
    public int shootAfterFrames = 0;
    public float randomDegreeRange = 0;
    public boolean highLight = false;

    private float nowCenterX = 0;
    private float nowCenterY = 0;

    public void setBaseEnemyPlane(BaseEnemyPlane baseEnemyPlane) {
        this.baseEnemyPlane = baseEnemyPlane;
    }

    public void setBulletShooterCenter(Vector2 bulletShooterCenter) {
        this.bulletShooterCenter = bulletShooterCenter;
    }

    public void setBulletVelocity(Vector2 bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public void setBulletAcceleration(Vector2 bulletAcceleration) {
        this.bulletAcceleration = bulletAcceleration;
    }

    public void setShootCenterOffset(Vector2 shootCenterOffset) {
        this.shootCenterOffset = shootCenterOffset;
    }

    public void setShooterCenterRandomRange(Vector2 shooterCenterRandomRange) {
        this.shooterCenterRandomRange = shooterCenterRandomRange;
    }

    public void setBulletForm(BulletForm bulletForm) {
        this.bulletForm = bulletForm;
    }

    public void setBulletColor(BulletColor bulletColor) {
        this.bulletColor = bulletColor;
    }

    public void setBulletStyle(BulletStyle bulletStyle) {
        this.bulletStyle = bulletStyle;
    }

    public void setReflexCount(int reflexCount) {
        this.reflexCount = reflexCount;
    }

    public void setThroughCount(int throughCount) {
        this.throughCount = throughCount;
    }

    public void setBulletWays(int bulletWays) {
        this.bulletWays = bulletWays;
    }

    public void setBulletWaysDegree(float bulletWaysDegree) {
        this.bulletWaysDegree = bulletWaysDegree;
    }

    public void setCengShu(int cengShu) {
        this.cengShu = cengShu;
    }

    public void setCengDanSuCha(float cengDanSuCha) {
        this.cengDanSuCha = cengDanSuCha;
    }

    public void setCengJianBeiLv(float cengJianBeiLv) {
        this.cengJianBeiLv = cengJianBeiLv;
    }

    public void setShootAfterFrames(int shootAfterFrames) {
        this.shootAfterFrames = shootAfterFrames;
    }

    public void setRandomDegreeRange(float randomDegreeRange) {
        this.randomDegreeRange = randomDegreeRange;
    }

    public void setHighLight(boolean highLight) {
        this.highLight = highLight;
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
        if (shootAfterFrames > 0) {
            --shootAfterFrames;
            return;
        }
        cengJianBeiLv = 1;
        if (randomDegreeRange > 0) {
            bulletVelocity.rotate(bulletVelocity.angle() + ObjectPools.randomPool.nextFloat() * randomDegreeRange * (ObjectPools.randomPool.nextBoolean() ? 1 : -1));
        }
        switch (bulletStyle) {
            case normal:
                break;
            case snipe:
                bulletVelocity = BaseMyPlane.instance.objectCenter.cpy().sub(bulletShooterCenter).nor();
                break;
            case round:
                bulletWaysDegree = 360f / bulletWays;
                break;
        }

        nowCenterX = bulletShooterCenter.x;
        nowCenterY = bulletShooterCenter.y;

        nowCenterX += shootCenterOffset.x;
        nowCenterY += shootCenterOffset.y;

        if (shooterCenterRandomRange.x > 0) {
            nowCenterX += shooterCenterRandomRange.x * ObjectPools.randomPool.nextFloat() * (ObjectPools.randomPool.nextBoolean() ? 1 : -1);
        }
        if (shooterCenterRandomRange.y > 0) {
            nowCenterY += shooterCenterRandomRange.y * ObjectPools.randomPool.nextFloat() * (ObjectPools.randomPool.nextBoolean() ? 1 : -1);
        }

        float angle = (bulletWays - 1) * bulletWaysDegree;
        Vector2 tmpv;
        for (int ceng = 0; ceng < cengShu; ++ceng) {
            tmpv = bulletVelocity.cpy().scl(cengJianBeiLv);
            tmpv.rotate(-angle / 2);
            for (int i = 0; i < bulletWays; i++) {
                EnemyBullet.create(new Vector2(nowCenterX, nowCenterY), bulletForm, bulletColor, highLight, reflexCount, throughCount, new Task[]{
                        new TaskMoveBy(bulletVelocity)
                });
                tmpv.rotate(bulletWaysDegree);
            }
            cengJianBeiLv += cengDanSuCha;
        }
    }
}
