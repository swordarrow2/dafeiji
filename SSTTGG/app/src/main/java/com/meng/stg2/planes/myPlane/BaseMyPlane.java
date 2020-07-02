package com.meng.stg2.planes.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.planes.*;
import com.meng.stg2.planes.subPlane.*;
import com.meng.stg2.ui.*;

public abstract class BaseMyPlane extends BaseGameObject {

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched = false;
    public int bombTime;
    public boolean onBomb = false;

    public int power = 3;
    public int maxPoint = 10000;
    public int miss = 0;

    public JudgePointAnim judgeAnim = new JudgePointAnim();
	public PlayerAnim playerAnim = new PlayerAnim();

	public boolean slow = false;

    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public void init() {
        super.init();
        instance = this;
        judgeAnim.init();
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
		playerAnim.update(position.x, position.y);
        image.toBack();
		//  animation2.update();
        judgeAnim.update();

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

	public class PlayerAnim {
		private int animFrom = 0;
		private int animTo = 7;
		private int everyAnimFrameTime = 5;
		private int time = 0;
		private int curFrameNumber = 0;
		private String name = "reimu";
		private MoveStatus status = MoveStatus.stay;

		public void setName(String name) {
			this.name = name;
		}

		public void update(float x, float y) {
			++time;
			if (x > lastPosition.x) {
				lastPosition.x = x;
				if (status != MoveStatus.moveRight) {
					animFrom = 16;
					animTo = 23;
					curFrameNumber = 16;
					status = MoveStatus.moveRight;
				}
			} else if (x < lastPosition.x) {
				lastPosition.x = x;
				if (status != MoveStatus.moveLeft) {
					animFrom = 8;
					animTo = 15;
					curFrameNumber = 8;
					status = MoveStatus.moveLeft;
				}
			} else if (status != MoveStatus.stay) {
				animFrom = 0;
				animTo = 7;
				curFrameNumber = 0;
				status = MoveStatus.stay;
			}
			if (time >= everyAnimFrameTime) {
				++curFrameNumber;
				time = 0;
			}
			if (curFrameNumber > animTo) {
				curFrameNumber = animFrom + 5;
			}
			image.setDrawable(ResourcesManager.textures.get(name + curFrameNumber));
		}
	}
	public class JudgePointAnim {

		private int stat = 0;
		private Image jImage = new Image();

		public void init() {
			jImage.setDrawable(ResourcesManager.textures.get("effect23"));
			jImage.setSize(48, 48);
			jImage.setOrigin(jImage.getWidth() / 2, jImage.getHeight() / 2);
			MainScreen.mainGroup.addActor(jImage);
		}

		public void update() {
			jImage.setRotation(stat);
			if (slow) {
				jImage.setSize(48, 48);
			} else {
				jImage.setSize(0, 0);
			}
			stat += 2;
			jImage.setPosition(position.x, position.y, Align.center);
			jImage.toFront();
		}
	}
}
