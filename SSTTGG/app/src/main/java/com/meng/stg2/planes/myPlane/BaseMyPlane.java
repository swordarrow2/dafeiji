package com.meng.stg2.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.meng.stg2.BaseGameObject;
import com.meng.stg2.bullets.BaseBullet;
import com.meng.stg2.bullets.BaseEnemyBullet;
import com.meng.stg2.helpers.Data;
import com.meng.stg2.planes.AnimationManager;
import com.meng.stg2.planes.JudgeCircleAnimation;
import com.meng.stg2.planes.JudgeCircleAnimation2;
import com.meng.stg2.planes.MoveStatus;
import com.meng.stg2.planes.subPlane.BaseSubPlane;
import com.meng.stg2.ui.MainScreen;
import com.meng.stg2.ui.*;

public abstract class BaseMyPlane extends BaseGameObject {

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched=false;
    public int bombTime;
    public boolean onBomb=false;

    public int power=3;
    public int maxPoint=10000;
    public int miss=0;

    public JudgeCircleAnimation animation=null;
    public JudgeCircleAnimation2 animation2=null;

	public boolean slow=false;
    public AnimationManager animationManager;
    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public void init() {
        super.init();
        instance = this;
        animation = new JudgeCircleAnimation();
        animation.init();
        animation2 = new JudgeCircleAnimation2();
        animation2.init();
        existTime = 0;
        position.set(MainScreen.width / 2, 80);
        image.setSize(30, 46);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        unmatchedTime = 1;
        onUnmatched = true;
        MainScreen.mainGroup.addActor(image);
		lastPosition.x = position.x = 270;
    }

    public void kill() {
        super.kill();
    }

    public void update() {
        super.update();
        animFlag++;
        position.set(MathUtils.clamp(position.x, 10, 376), MathUtils.clamp(position.y, 10, 440));
		if (MainActivity.status == 1) {
			BaseMyPlane.instance.position.x += 5;
		}
		if (MainActivity.status == 2) {
			BaseMyPlane.instance.position.y += 5;
		}
        image.setPosition(position.x, position.y, Align.center);
        shoot();
        judge();
        if (onBomb) {
            onUnmatched = true;
            bomb();
            bombTime--;
        }
        if (onUnmatched) {
            unmatchedTime--;
        }
        if (bombTime == 0) {
            onBomb = false;
            bombTime = Data.ReimuBombTime;
        }
        if (unmatchedTime == 0) {
            onUnmatched = false;
            unmatchedTime = Data.ReimuUnmatchedTime;
        }
		animationManager.updatePosition(position.x, position.y);
		
        animationManager.update();
        image.toBack();
        animation2.update();
        animation.update();

    }

    public void incPower(int p) {
        power += p;
        onPowerInc();
    }

    public void decPower(int p) {
        power -= p;
        onPowerDec();
    }

    public void judge() {
        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
            if (baseBullet.getCollisionArea().contains(position)) {
                baseBullet.kill();
                kill();
            }
        }
    }

    public abstract void bomb();

    public abstract void shoot();

    public abstract void onPowerInc();

    public abstract void onPowerDec();
}
