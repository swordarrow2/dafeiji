package com.meng.TaiHunDanmaku.baseObjects.bullets.subPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.TaiHunDanmaku.baseObjects.bullets.BaseMyBullet;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.helpers.TextureNameManager;


import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.FightScreen;

public class ReimuSubPlaneBulletStraight extends BaseMyBullet {

    @Override
    public Drawable getDrawable() {
        if (drawable == null) {
            drawable = ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight);
        }
        return drawable;
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(64, 16);
	  }
	  
    public void judge() {
        try {
            for (int i = 0; i < 32; i++) {
                if (FightScreen.instence.boss!= null) {
                    if (((Circle) getCollisionArea()).overlaps(((Circle) FightScreen.instence.boss.getJudgeCircle()))) {
                        FightScreen.instence.boss.hit(BaseMyPlane.instance.slow ? 13.5f : 10.5f);
                        killByOutOfScreen();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void killByOutOfScreen() {
        super.killByOutOfScreen();
        //   ObjectPools.reimuSubPlaneBulletStraightPool.free(this);
    }

    @Override
    public float getRotationDegree() {
        return 90;
    }
}
