package com.meng.TaiHunDanmaku.bullets.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.ui.*;


public class ReimuSpell extends BaseMyBullet {

    @Override
    public void init(Vector2 center, Vector2 velocity) {
        super.init(center, velocity);
        judgeCircle.setRadius(20);
	  }


    @Override
    public Drawable getDrawable() {
        if (drawable == null) {
            drawable = ResourcesManager.textures.get("reimu55");
		  }
        return drawable;
	  }

    @Override
    public Vector2 getSize() {
        return new Vector2(64, 16);
	  }

    public void judge() {
		if (FightScreen.instence.boss != null) {
			if (((Circle) getCollisionArea()).overlaps(((Circle) FightScreen.instence.boss.getJudgeCircle()))) {
				FightScreen.instence.boss.hit(70.5f);
			  }
		  }

		for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
			if (judgeCircle.contains(baseBullet.objectCenter)) {

				baseBullet.killByJudge(BulletKillMode.KillOnBossLastDeath);
			  }
		  }
	  }

    @Override
    public float getRotationDegree() {
        return 90;
	  }
  }
