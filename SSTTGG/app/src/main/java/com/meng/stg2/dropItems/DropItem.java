package com.meng.stg2.dropItems;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.characters.player.*;
import com.meng.stg2.ui.*;
import java.util.*;
import java.util.concurrent.*;
import com.meng.stg2.characters.enemy.*;

public class DropItem extends BaseGameObject {

    public static HashSet<DropItem> instances=new HashSet<DropItem>();
    public static LinkedBlockingQueue<DropItem> toDelete=new LinkedBlockingQueue<DropItem>();
    public static LinkedBlockingQueue<DropItem> toAdd=new LinkedBlockingQueue<DropItem>();

    public BulletKillMode bulletKillMode;
    public DropItemType itemType;
    public int DrawableNumber = 0;
	public Circle judgeCircle = new Circle();

	public static void create(Vector2 center, DropItemType type) {
        create(center, type, BulletKillMode.killWithScorePoint);
	}
	
    public static void create(BaseEnemyPlane bep, DropItemType type) {
        create(bep.position.cpy(), type, BulletKillMode.killWithScorePoint);
	}

    public static void create(Vector2 center, DropItemType bf, BulletKillMode bkm) {
        ObjectPools.itemPool.obtain().init(center, bf, bkm);
	}

    public void init(Vector2 center, DropItemType itemType, BulletKillMode bulletKillMode) {
        super.init();
        this.bulletKillMode = BulletKillMode.killWithNothing;
        toAdd.add(this);
		velocity = new Vector2(0, 3);

        position.set(center);
        this.bulletKillMode = bulletKillMode;
        image.setPosition(center.x, center.y, Align.center);
        judgeCircle.set(position, image.getWidth() / 2);
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
        MainScreen.instence.groupNormal.addActor(image);
	}

    public void kill() {
        toDelete.add(this);
        image.remove();
	}

    public void update() {
        super.update();
        if (BaseMyPlane.instance.position.y > 380 || position.cpy().sub(BaseMyPlane.instance.position).len2() < 800) {
            bulletKillMode = BulletKillMode.killWithScorePointAndCollect;
		}
        switch (bulletKillMode) {
            case killWithScorePoint:
				position.add(velocity);
				velocity.y -= 0.1f;
				break;
            case killWithScorePointAndCollect:
            case KillOnBossLastDeath:
				position.add(BaseMyPlane.instance.position.cpy().sub(position).nor().scl(7f));
				break;
		}
        image.setRotation(0);
        image.setPosition(position.x, position.y, Align.center);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        judgeCircle.setPosition(position);
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
        if (drawable == null) {
            drawable = ResourcesManager.textures.get("item" + DrawableNumber);
		}
        return drawable;
	}

    public void judge() {
        Vector2 v=BaseMyPlane.instance.position.cpy();
        if (Math.abs(judgeCircle.x - v.x) < 5 && Math.abs(judgeCircle.y - v.y) < 5) {
            kill();
            switch (itemType) {
                case power:
					BaseMyPlane.instance.incPower(30);
					break;
                case powerBig:
					BaseMyPlane.instance.incPower(90);
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
					BaseMyPlane.instance.maxPoint += 5;
					break;
                case highScoreMediam:
					BaseMyPlane.instance.maxPoint += 10;
					break;
                case highScoreLarge:
					BaseMyPlane.instance.maxPoint += 20;
					break;
			}

		}
	}
}
