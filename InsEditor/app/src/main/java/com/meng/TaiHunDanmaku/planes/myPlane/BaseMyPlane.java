package com.meng.TaiHunDanmaku.planes.myPlane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.BaseGameObject;
import com.meng.TaiHunDanmaku.bullets.BaseEnemyBullet;
import com.meng.TaiHunDanmaku.helpers.Data;
import com.meng.TaiHunDanmaku.planes.AnimationManager;
import com.meng.TaiHunDanmaku.planes.JudgeCircleAnimation;
import com.meng.TaiHunDanmaku.planes.JudgeCircleAnimation2;
import com.meng.TaiHunDanmaku.planes.MoveStatus;
import com.meng.TaiHunDanmaku.planes.subPlane.BaseSubPlane;
import com.meng.TaiHunDanmaku.ui.FightScreen;
import com.meng.TaiHunDanmaku.ui.GameMain;

public abstract class BaseMyPlane extends BaseGameObject {

    public static BaseMyPlane instance;

    public JudgeCircleAnimation animation = null;
    public JudgeCircleAnimation2 animation2 = null;

    private float playerLastX = 270;
    public boolean slow = false;
    public AnimationManager animationManager;
    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public GameMain gameMain;

    public void init(GameMain gameMain) {
        super.init();
        instance = this;
        this.gameMain = gameMain;
        animation = new JudgeCircleAnimation();
        animation.init();
        animation2 = new JudgeCircleAnimation2();
        animation2.init();
        existTime = 0;
        objectCenter.set(gameMain.width / 2, 80);
        image.setSize(30, 46);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);      
        FightScreen.instence.groupNormal.addActor(image);
        image.setZIndex(Data.zIndexMyPlane);
    }

    public void kill() {
        super.kill();
    }

    public void update() {
        super.update();
        animFlag++;
        objectCenter = new Vector2(MathUtils.clamp(objectCenter.x, 10, 376), MathUtils.clamp(objectCenter.y, 10, 440));
        if (image.getRotation() != 0) {
            image.setRotation(0);
        }
        image.setPosition(objectCenter.x, objectCenter.y, Align.center);
        shoot();
        judge();

        if (objectCenter.x > playerLastX) {
            playerLastX = objectCenter.x;
            animationManager.setStatus(MoveStatus.moveRight);
        } else if (objectCenter.x < playerLastX) {
            playerLastX = objectCenter.x;
            animationManager.setStatus(MoveStatus.moveLeft);
        } else {
            animationManager.setStatus(MoveStatus.stay);
        }
        animationManager.update();
        image.toBack();
        animation2.update();
        animation.update();
    }

    public void judge() {
        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
            if (baseBullet.getCollisionArea().contains(objectCenter)) {
                baseBullet.killByJudge();
                kill();
            }
        }
    }


    public abstract void shoot();

}
