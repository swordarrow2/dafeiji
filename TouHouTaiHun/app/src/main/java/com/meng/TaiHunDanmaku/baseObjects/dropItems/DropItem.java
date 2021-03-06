package com.meng.TaiHunDanmaku.baseObjects.dropItems;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;

import java.util.*;
import java.util.concurrent.*;

public class DropItem extends BaseGameObject {

    public static HashSet<DropItem> instances = new HashSet<DropItem>();
    public static LinkedBlockingQueue<DropItem> toDelete = new LinkedBlockingQueue<DropItem>();
    public static LinkedBlockingQueue<DropItem> toAdd = new LinkedBlockingQueue<DropItem>();

    public BulletKillMode bulletKillMode;
    public DropItemType itemType;
    public int DrawableNumber = 0;

    public static void create(Vector2 center, DropItemType type) {
        create(center, type, BulletKillMode.killWithScorePoint);
    }

    public static void create(Vector2 center, DropItemType bf, BulletKillMode bkm) {
        ObjectPools.itemPool.obtain().init(center, bf, bkm);
    }

    public void init(Vector2 center, DropItemType itemType, BulletKillMode bulletKillMode) {
        super.init();
        this.bulletKillMode = BulletKillMode.killWithNothing;
        toAdd.add(this);
        velocity = new Vector2(0, 3);

        existTime = 0;
        judgeCircle = new Circle(objectCenter, 16);
        objectCenter.set(center);
        this.bulletKillMode = bulletKillMode;
        image.setPosition(center.x, center.y, Align.center);
        judgeCircle = new Circle(objectCenter, image.getWidth() / 2);
        this.itemType = itemType;
        switch (itemType) {
            case power:
                DrawableNumber = 500;
                size = new Vector2(16, 16);
                break;
            case powerBig:
                DrawableNumber = 504;
                size = new Vector2(32, 32);
                break;
            case point:
                DrawableNumber = 502;
                size = new Vector2(16, 16);
                break;
            case player:
                DrawableNumber = 508;
                size = new Vector2(32, 32);
                break;
            case playerFragment:
                DrawableNumber = 506;
                size = new Vector2(32, 32);
                break;
            case bomb:
                DrawableNumber = 512;
                size = new Vector2(32, 32);
                break;
            case bombFragment:
                DrawableNumber = 510;
                size = new Vector2(32, 32);
                break;
            case powerFull:
                DrawableNumber = 514;
                size = new Vector2(32, 32);
                break;
            case powerPointer:
                DrawableNumber = 501;
                size = new Vector2(16, 16);
                break;
            case powerBigPointer:
                DrawableNumber = 505;
                size = new Vector2(32, 32);
                break;
            case pointPointer:
                DrawableNumber = 503;
                size = new Vector2(16, 16);
                break;
            case playerPointer:
                DrawableNumber = 509;
                size = new Vector2(32, 32);
                break;
            case playerFragmentPointer:
                DrawableNumber = 507;
                size = new Vector2(32, 32);
                break;
            case bombPointer:
                DrawableNumber = 513;
                size = new Vector2(32, 32);
                break;
            case bombFragmentPointer:
                DrawableNumber = 511;
                size = new Vector2(32, 32);
                break;
            case powerFullPointer:
                DrawableNumber = 515;
                size = new Vector2(32, 32);
                break;
            case highScoreSmall:
                DrawableNumber = 516;
                size = new Vector2(16, 16);
                break;
            case highScoreMediam:
                DrawableNumber = 517;
                size = new Vector2(16, 16);
                break;
            case highScoreLarge:
                DrawableNumber = 518;
                size = new Vector2(16, 16);
                break;
        }
        image.setSize(size.x, size.y);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        image.setDrawable(getDrawable());
        FightScreen.instence.groupNormal.addActor(image);
        image.setZIndex(Data.zIndexDropItem);
    }

    public void kill() {
        super.kill();
        toDelete.add(this);
        image.remove();
        drawable = null;
        ObjectPools.itemPool.free(this);
    }

    public void update() {
        super.update();
        if (BaseMyPlane.instance.objectCenter.y > 380 || objectCenter.cpy().sub(BaseMyPlane.instance.objectCenter).len2() < 800) {
            bulletKillMode = BulletKillMode.killWithScorePointAndCollect;
        }
        switch (bulletKillMode) {
            case killWithScorePoint:
                objectCenter.add(velocity);
                velocity.y -= 0.1f;
                break;
            case killWithScorePointAndCollect:
            case KillOnBossLastDeath:
                objectCenter.add(BaseMyPlane.instance.objectCenter.cpy().sub(objectCenter).nor().scl(7f));
                break;
        }
        image.setRotation(0);
        image.setPosition(objectCenter.x, objectCenter.y, Align.center);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        judgeCircle.setPosition(objectCenter);
        if (judgeCircle.x < -5 || judgeCircle.x > 390
                || judgeCircle.y < -5 || judgeCircle.y > 460) {
            kill();
        } else {
            judge();
        }

    }

    public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
        }
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
        }
        for (DropItem item : instances) {
            item.update();
        }
    }

    public Drawable getDrawable() {
        drawable = ResourcesManager.textures.get("item" + DrawableNumber);
        return drawable;
    }

    public void judge() {
        Vector2 v = BaseMyPlane.instance.objectCenter.cpy();
        if (Math.abs(judgeCircle.x - v.x) < 5 && Math.abs(judgeCircle.y - v.y) < 5) {
            kill();
            switch (itemType) {
                case power:
                    BaseMyPlane.instance.incPower(1);
                    break;
                case powerBig:
                    BaseMyPlane.instance.incPower(1);
                    BaseMyPlane.instance.incPower(1);
                    BaseMyPlane.instance.incPower(1);
                    break;
                case point:
                    break;
                case player:
                    break;
                case playerFragment:
                    break;
                case bomb:
                    break;
                case bombFragment:
                    break;
                case powerFull:
                    break;
                case powerPointer:
                    break;
                case powerBigPointer:
                    break;
                case pointPointer:
                    break;
                case playerPointer:
                    break;
                case playerFragmentPointer:
                    break;
                case bombPointer:
                    break;
                case bombFragmentPointer:
                    break;
                case powerFullPointer:
                    break;
                case highScoreSmall:
                    FightScreen.instence.gameMain.maxPoint += 5;
                    break;
                case highScoreMediam:
                    FightScreen.instence.gameMain.maxPoint += 10;
                    break;
                case highScoreLarge:
                    FightScreen.instence.gameMain.maxPoint += 20;
                    break;
            }

        }
    }
}
