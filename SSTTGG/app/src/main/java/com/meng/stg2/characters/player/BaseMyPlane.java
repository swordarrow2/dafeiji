package com.meng.stg2.characters.player;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.characters.*;
import com.meng.stg2.ui.*;

public abstract class BaseMyPlane extends BaseGameObject {

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched = false;
    public int bombTime;
    public boolean onBomb = false;

    public int power = 300;
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
        MainScreen.instence.groupNormal.addActor(image);
		lastPosition.x = position.x = 193;
    }

    public void kill() {
        super.kill();
		image.remove();
    }

    public void update() {
        super.update();
        animFlag++;
        position.set(MathUtils.clamp(position.x, 10, 376), MathUtils.clamp(position.y, 10, 440));
		if (MainActivity.status == 1) {
			instance.position.x += 5;
		}
		if (MainActivity.status == 2) {
			position.y += 5;
		}
        image.setPosition(position.x, position.y, Align.center);
        shoot();
		//   judge();
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

//    public void judge() {
//        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
//            if (baseBullet.getCollisionArea().contains(position)) {
//                baseBullet.kill();
//                kill();
//            }
//        }
//    }

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
		private Image judgeImage = new Image();

		public void init() {
			judgeImage.setDrawable(ResourcesManager.textures.get("effect23"));
			judgeImage.setSize(48, 48);
			judgeImage.setOrigin(judgeImage.getWidth() / 2, judgeImage.getHeight() / 2);
			MainScreen.instence.groupNormal.addActor(judgeImage);
		}

		public void update() {
			judgeImage.setRotation(stat);
			if (slow) {
				judgeImage.setSize(48, 48);
			} else {
				judgeImage.setSize(0, 0);
			}
			stat += 2;
			judgeImage.setPosition(position.x, position.y, Align.center);
			judgeImage.toFront();
		}
	}


	public abstract class BaseSubPlane extends BaseGameObject {

		public Vector2 nowPosition=Vector2.Zero.cpy();
		public int bianHao=1;

		private SubInfo.Info subInfo;

		public void init() {
			super.init();
			//subInfo=info;
			size = getSize();
			BaseSubPlane.this.position = BaseMyPlane.this.position.cpy();
			image.setDrawable(getDrawable());
			image.setSize(size.x, size.y);
			image.setRotation(getRotationDegree());
			image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
			MainScreen.instence.groupNormal.addActor(image);
		}

		public void kill() {
			super.kill();
			image.remove();
		}

		public void setSubInfo(SubInfo.Info subInfo) {
			this.subInfo = subInfo;
		}

		public void update() {
			super.update();
			if (slow) {
				nowPosition.set(BaseMyPlane.this.position.x + subInfo.focusX, BaseMyPlane.this.position.y + subInfo.focusY);
			} else {
				nowPosition.set(BaseMyPlane.this.position.x + subInfo.normalX, BaseMyPlane.this.position.y + subInfo.normalY);
			}
			position.add(nowPosition.sub(position).scl(0.2f));
			// image.setDrawable(getDrawable());
			image.setRotation(getRotationDegree());
			image.setPosition(position.x, position.y, Align.center);
			//image.setOrigin(image.getWidth()/2,image.getHeight()/2);
			shoot();
		}

		public abstract Drawable getDrawable();

		public abstract float getRotationDegree();

		public abstract Vector2 getSize();

		public abstract void shoot();

	}

}
