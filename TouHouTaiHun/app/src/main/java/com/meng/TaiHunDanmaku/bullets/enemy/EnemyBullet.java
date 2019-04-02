package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

import java.util.ArrayList;

public class EnemyBullet extends BaseEnemyBullet {

    public static void create(Vector2 center, Vector2 velocity, Vector2 acceleration, BulletForm bulletForm, BulletColor bulletColor, int life, int liveOutScreen, boolean highLight, int reflex, int reflexT, int reflexB, int reflexL, int reflexR, int through, int throughT, int throughB, int throughL, int throughR, ArrayList<Task> tasks) {
        ObjectPools.enemyBulletPool.obtain().init(center, velocity, acceleration, bulletForm, bulletColor, life, liveOutScreen, highLight, reflex, reflexT, reflexB, reflexL, reflexR, through, throughT, throughB, throughL, throughR, tasks);
    }

    public void init(Vector2 center, Vector2 velocity, Vector2 acceleration, BulletForm bulletForm, BulletColor bulletColor, int life, int lifeOutScreen, boolean highLight, int reflex, int reflexT, int reflexB, int reflexL, int reflexR, int through, int throughT, int throughB, int throughL, int throughR, ArrayList<Task> tasks) {
        super.init();
        for (Task t : tasks) {
            taskManager.addTask(t);
        }
        reflexCount = reflex;
        reflexTopCount = reflexT;
        reflexBottomCount = reflexB;
        reflexLeftCount = reflexL;
        reflexRightCount = reflexR;
        throughCount = through;
        throughTopCount = throughT;
        throughBottomCount = throughB;
        throughLeftCount = throughL;
        throughRightCount = throughR;
        objectCenter.set(center);
        this.velocity.set(velocity);
        this.acceleration.set(acceleration);
        this.liveOutOfScreen = lifeOutScreen;
        bulletLife = life;
        image.setPosition(center.x, center.y, Align.center);
        judgeCircle = new Circle(objectCenter, Math.min(image.getWidth(), image.getHeight()) / 3);
        switch (bulletColor) {
            case gray:
                colorNum = 0;
                break;
            case grayAndRed:
                colorNum = 1;
                break;
            case red:
                colorNum = 2;
                break;
            case grayAndPurple:
                colorNum = 3;
                break;
            case purple:
                colorNum = 4;
                break;
            case grayAndBlue:
                colorNum = 5;
                break;
            case blue:
                colorNum = 6;
                break;
            case grayAndLightBlue:
                colorNum = 7;
                break;
            case lightBlue:
                colorNum = 8;
                break;
            case grayAndGreen:
                colorNum = 9;
                break;
            case green:
                colorNum = 10;
                break;
            case grayAndYellow:
                colorNum = 11;
                break;
            case yellow_dark:
                colorNum = 12;
                break;
            case yellow_light:
                colorNum = 13;
                break;
            case orange:
                colorNum = 14;
                break;
            case white:
                colorNum = 15;
                break;
        }
        switch (bulletForm) {
            case jiguangkuai:
                formNum = 22;
                break;
            case lindan:
                formNum = 14;
                break;
            case huanyu:
                formNum = 6;
                break;
            case xiaoyu:
                formNum = 4;
                break;
            case midan:
                formNum = 8;
                break;
            case liandan:
                formNum = 10;
                break;
            case zhendan:
                formNum = 11;
                break;
            case zadan:
                formNum = 13;
                break;
            case chongdan:
                formNum = 15;
                break;
            case ganjundan:
                formNum = 17;
                break;
            case xingdan:
                formNum = 18;
                break;
            case xiaodan:
                formNum = 16;
                break;
            case jundan:
                formNum = 3;
                break;
            case lidan:
                formNum = 4;
                break;
            case diandan:
                formNum = 0;
                break;
        }
        image.setDrawable(getDrawable());
        if (highLight) {
            MainScreen.groupHighLight.addActor(image);
        } else {
            MainScreen.groupNormal.addActor(image);
        }
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(16, 16);
    }

    @Override
    public void killByOutOfScreen() {
        if (liveOutOfScreen > 0) {
            --liveOutOfScreen;
            return;
        }
        super.killByOutOfScreen();
        toDelete.add(this);
        image.remove();
        drawable = null;
        ObjectPools.enemyBulletPool.free(this);
    }

    @Override
    public void update() {
        super.update();
        --bulletLife;
        if (bulletLife < 0) {
            killByJudge();
        }
    }

    public void killByJudge() {
        super.killByOutOfScreen();
        toDelete.add(this);
        image.remove();
        drawable = null;
        ObjectPools.enemyBulletPool.free(this);
    }

    @Override
    public Drawable getDrawable() {
        if (formNum == 13 || formNum == 14) {
            image.setSize(14, 16);
        }
        if (formNum == 0) {
            image.setSize(8, 8);
        }
        if (formNum == 8) {
            image.setSize(8, 10);
        }
        if (drawable == null) {
            drawable = ResourcesManager.textures.get("bullet" + (formNum * 16 + colorNum));
        }
        return drawable;
    }
}
