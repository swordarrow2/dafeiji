package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.task.*;

import java.util.*;
import java.util.concurrent.*;

public class BulletShooter {

    public BaseEnemyPlane enemyPlane;

    public static HashSet<BulletShooter> instances = new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toDelete = new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toAdd = new LinkedBlockingQueue<BulletShooter>();
    public int shooterExistTime = 0;
    public Vector2 shooterCenter = new Vector2(200, 200);
    public Vector2 bulletVelocity = new Vector2(1, 0);
    public Vector2 bulletAcceleration = new Vector2(0, 0);
    public Vector2 shootCenterOffset = new Vector2(0, 0);
    public Vector2 shooterCenterRandomRange = new Vector2(0, 0);
    public BulletForm bulletForm = BulletForm.lindan;
    public BulletColor bulletColor = BulletColor.white;
    public BulletStyle bulletStyle = BulletStyle.normal;
    public int bulletWays = 1;
    public float bulletWaysDegree = 0;
    public int bulletCengShu = 1;
    public float bulletCengDanSuCha = 0.1f;

    public int reflexCount = 0;
    public int reflexTopCount = 0;
    public int reflexBottomCount = 0;
    public int reflexLeftCount = 0;
    public int reflexRightCount = 0;
    public int throughCount = 0;
    public int throughTopCount = 0;
    public int throughBottomCount = 0;
    public int throughLeftCount = 0;
    public int throughRightCount = 0;

    public int shooterShootAfterFrames = 0;
    public float bulletRandomDegreeRange = 0;
    public boolean bulletHighLight = false;
    public int bulletLife = 7200;
    public int bulletLiveOutOfScreen = 0;
    public ArrayList<Task> bulletTasks = new ArrayList<Task>();


    private float nowCenterX = 0;
    private float nowCenterY = 0;
    private float angle = 0;
    private float cengJianBeiLv = 1;
    private Vector2 tmpv = new Vector2(0, 0);

    public BulletShooter init() {
        return this;
    }

    public BulletShooter setEnemyPlane(BaseEnemyPlane enemyPlane) {
        this.enemyPlane = enemyPlane;
        return this;
    }

    public BulletShooter setShooterCenter(Vector2 bulletShooterCenter) {
        this.shooterCenter = bulletShooterCenter;
        return this;
    }

    public BulletShooter setBulletVelocity(Vector2 bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
        return this;
    }

    public BulletShooter setBulletAcceleration(Vector2 bulletAcceleration) {
        this.bulletAcceleration = bulletAcceleration;
        return this;
    }

    public BulletShooter setShootCenterOffset(Vector2 shootCenterOffset) {
        this.shootCenterOffset = shootCenterOffset;
        return this;
    }

    public BulletShooter setShooterCenterRandomRange(Vector2 shooterCenterRandomRange) {
        this.shooterCenterRandomRange = shooterCenterRandomRange;
        return this;
    }

    public BulletShooter setShooterCenterRandomRange(float x, float y) {
        shooterCenterRandomRange.x = x;
        shooterCenterRandomRange.y = y;
        return this;
    }

    public BulletShooter setBulletForm(BulletForm bulletForm) {
        this.bulletForm = bulletForm;
        return this;
    }

    public BulletShooter setBulletColor(BulletColor bulletColor) {
        this.bulletColor = bulletColor;
        return this;
    }

    public BulletShooter setBulletStyle(BulletStyle bulletStyle) {
        this.bulletStyle = bulletStyle;
        return this;
    }

    public BulletShooter setReflexCount(int all, int t, int b, int l, int r) {
        this.reflexCount = all;
        this.reflexTopCount = t;
        this.reflexBottomCount = b;
        this.reflexLeftCount = l;
        this.reflexRightCount = r;
        return this;
    }

    public BulletShooter setThoughCount(int all, int t, int b, int l, int r) {
        this.throughCount = all;
        this.throughTopCount = t;
        this.throughBottomCount = b;
        this.throughLeftCount = l;
        this.throughRightCount = r;
        return this;
    }

    public BulletShooter setBulletWays(int bulletWays) {
        this.bulletWays = bulletWays;
        return this;
    }

    public BulletShooter setBulletWaysDegree(float bulletWaysDegree) {
        this.bulletWaysDegree = bulletWaysDegree;
        return this;
    }

    public BulletShooter setBulletCengShu(int bulletCengShu) {
        this.bulletCengShu = bulletCengShu;
        return this;
    }

    public BulletShooter setBulletCengDanSuCha(float bulletCengDanSuCha) {
        this.bulletCengDanSuCha = bulletCengDanSuCha;
        return this;
    }

    public BulletShooter setCengJianBeiLv(float cengJianBeiLv) {
        this.cengJianBeiLv = cengJianBeiLv;
        return this;
    }

    public BulletShooter setShooterShootAfterFrames(int shooterShootAfterFrames) {
        this.shooterShootAfterFrames = shooterShootAfterFrames;
        return this;
    }

    public BulletShooter setBulletRandomDegreeRange(float bulletRandomDegreeRange) {
        this.bulletRandomDegreeRange = bulletRandomDegreeRange;
        return this;
    }

    public BulletShooter setBulletHighLight(boolean bulletHighLight) {
        this.bulletHighLight = bulletHighLight;
        return this;
    }

    public BulletShooter setBulletTasks(ArrayList<Task> bulletTasks) {
        this.bulletTasks = bulletTasks;
        return this;
    }

    public BulletShooter setBulletLiveOutOfScreen(int bulletLiveOutOfScreen) {
        this.bulletLiveOutOfScreen = bulletLiveOutOfScreen;
        return this;
    }

    public BulletShooter setBulletLife(int bulletLife) {
        this.bulletLife = bulletLife;
        return this;
    }

    public void update() {
        ++shooterExistTime;
        if (enemyPlane.judgeCircle == null) {
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
        if (shooterShootAfterFrames > 0) {
            --shooterShootAfterFrames;
            return;
        }
        cengJianBeiLv = 1;
        if (bulletRandomDegreeRange > 0) {
            bulletVelocity.rotate(bulletVelocity.angle() + ObjectPools.randomPool.nextFloat() * bulletRandomDegreeRange * (ObjectPools.randomPool.nextBoolean() ? 1 : -1));
        }
        switch (bulletStyle) {
            case normal:
                break;
            case snipe:
                bulletVelocity = BaseMyPlane.instance.objectCenter.cpy().sub(shooterCenter).nor().scl(bulletVelocity.len());
                break;
            case round:
                bulletWaysDegree = 360f / bulletWays;
                break;
        }

        nowCenterX = shooterCenter.x;
        nowCenterY = shooterCenter.y;

        nowCenterX += shootCenterOffset.x;
        nowCenterY += shootCenterOffset.y;

        if (shooterCenterRandomRange.x > 0) {
            nowCenterX += shooterCenterRandomRange.x * ObjectPools.randomPool.nextFloat() * (ObjectPools.randomPool.nextBoolean() ? 1 : -1);
        }
        if (shooterCenterRandomRange.y > 0) {
            nowCenterY += shooterCenterRandomRange.y * ObjectPools.randomPool.nextFloat() * (ObjectPools.randomPool.nextBoolean() ? 1 : -1);
        }

        if (bulletCengShu > 1 && bulletWays > 1) {
            angle = (bulletWays - 1) * bulletWaysDegree;
            for (int ceng = 0; ceng < bulletCengShu; ++ceng) {
                tmpv = bulletVelocity.cpy().scl(cengJianBeiLv);
                float tmpangle = -angle / 2;
                tmpv.rotate(tmpangle);
                for (int i = 0; i < bulletWays; i++) {
                    EnemyBullet.create(new Vector2(nowCenterX, nowCenterY), tmpv, bulletAcceleration.cpy().setAngle(tmpv.angle()).setAngle(tmpv.angle()), bulletForm, bulletColor, bulletLife, bulletLiveOutOfScreen, bulletHighLight, reflexCount, reflexTopCount, reflexBottomCount, reflexLeftCount, reflexRightCount, throughCount, throughTopCount, throughBottomCount, throughLeftCount, throughRightCount, bulletTasks);
                    tmpv.rotate(bulletWaysDegree);
                }
                cengJianBeiLv += bulletCengDanSuCha;
            }
        } else if (bulletCengShu > 1) {
            for (int ceng = 0; ceng < bulletCengShu; ++ceng) {
                tmpv = bulletVelocity.cpy().scl(cengJianBeiLv);
                EnemyBullet.create(new Vector2(nowCenterX, nowCenterY), tmpv, bulletAcceleration.cpy().setAngle(tmpv.angle()).setAngle(tmpv.angle()), bulletForm, bulletColor, bulletLife, bulletLiveOutOfScreen, bulletHighLight, reflexCount, reflexTopCount, reflexBottomCount, reflexLeftCount, reflexRightCount, throughCount, throughTopCount, throughBottomCount, throughLeftCount, throughRightCount, bulletTasks);
                cengJianBeiLv += bulletCengDanSuCha;
            }
        } else if (bulletWays > 1) {
            angle = (bulletWays - 1) * bulletWaysDegree;
            tmpv = bulletVelocity.cpy();
            float tmpangle = -angle / 2;
            tmpv.rotate(tmpangle);
            for (int i = 0; i < bulletWays; i++) {
                EnemyBullet.create(new Vector2(nowCenterX, nowCenterY), tmpv, bulletAcceleration.cpy().setAngle(tmpv.angle()), bulletForm, bulletColor, bulletLife, bulletLiveOutOfScreen, bulletHighLight, reflexCount, reflexTopCount, reflexBottomCount, reflexLeftCount, reflexRightCount, throughCount, throughTopCount, throughBottomCount, throughLeftCount, throughRightCount, bulletTasks);
                tmpv.rotate(bulletWaysDegree);
            }
        } else {
            EnemyBullet.create(new Vector2(nowCenterX, nowCenterY), bulletVelocity, bulletAcceleration, bulletForm, bulletColor, bulletLife, bulletLiveOutOfScreen, bulletHighLight, reflexCount, reflexTopCount, reflexBottomCount, reflexLeftCount, reflexRightCount, throughCount, throughTopCount, throughBottomCount, throughLeftCount, throughRightCount, bulletTasks);
        }
    }
}
