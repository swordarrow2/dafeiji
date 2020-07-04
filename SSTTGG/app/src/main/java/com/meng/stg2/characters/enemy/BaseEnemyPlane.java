package com.meng.stg2.characters.enemy;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.dropItems.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.characters.*;
import com.meng.stg2.characters.enemy.normal.*;
import com.meng.stg2.characters.player.*;
import com.meng.stg2.task.*;
import com.meng.stg2.ui.*;

import static com.meng.stg2.ui.MainScreen.enemys;

public abstract class BaseEnemyPlane extends BaseGameObject {

    public int time = 0;
	public float hp = 10;
    public EnemyColor enemyColor;
    public boolean isKilled = false;

    public int animFrom = 0;
    public int animTo = 7;
    public int everyAnimFrameTime = 0;
    public int curFrameNumber = 0;
    public MoveStatus status = MoveStatus.stay;
    public String objectName = "";
    public boolean flip = false;
    public int[][] animNum;

    public Danmaku bulletShooter;
	public Vector2 targetPosition = new Vector2();
	public Circle judge = new Circle();
	public TaskManager taskManager;

    public void init(Vector2 center, int everyAnimFrameTime, int hp, Task[] task) {
        super.init();
		taskManager = new TaskManager(this, TaskRepeatMode.noRepeat);
		for (Task t:task) {
			taskManager.addTask(t);
		}
        this.everyAnimFrameTime = everyAnimFrameTime;
        objectName = "zayu";
        isKilled = false;
        position = center;
        this.hp = hp;
        size = getSize();
        image.setRotation(0);
        image.setSize(size.x, size.y);
        judge.set(position, image.getWidth() / 4);
        MainScreen.instence.groupNormal.addActor(image);
        for (int i=0;i < 32;i++) {
            if (enemys[i] == null) {
                enemys[i] = this;
                break;
            }
        }
    }

    public abstract Vector2 getSize();

    public float getHp() {
        return hp;
    }

    public void hit(float bulletDamage) {
        if (hp < 1) {
            kill();
        } else {
            if (MainScreen.onSpellCard) {
				hp = hp - bulletDamage / 7;
			} else {
				hp -= bulletDamage;
			}
        }
    }

    public Vector2 getLocation() {
        return position;
    }

	public void moveTo(float x, float y) {
        targetPosition.x = x;
        targetPosition.y = y;
	}

    public void setStatus(MoveStatus mov) {
        if (mov == status) return;
        status = mov;
        switch (status) {
            case stay:
                flip = false;
                animFrom = animNum[0][0];
                animTo = animNum[0][1];
                curFrameNumber = animFrom;
                break;
            case moveLeft:
                flip = true;
                animFrom = animNum[1][0];
                animTo = animNum[1][1];
                curFrameNumber = animFrom;
                break;
            case moveRight:
                flip = false;
                animFrom = animNum[1][0];
                animTo = animNum[1][1];
                curFrameNumber = animFrom;
                break;
        }
    }

    public void kill() {
		if (isKilled) {
			return;
		}
        super.kill();
        image.remove();
        isKilled = true;
		DropItem.create(this, DropItemType.power);
    }

    public void update() {
        super.update();
        time++;
        animFlag++;
        taskManager.update();

        anim();
        shoot();

        image.setPosition(position.x, position.y, Align.center);
        judge.setPosition(position.x, position.y);
        if (judge.x < -5 || judge.x > 390
			|| judge.y < -5 || judge.y > 460) {
            kill();
        } else {
            judge();
        }

		if (position.cpy().sub(targetPosition).len2() > 10) {
            position.add(targetPosition.cpy().sub(position).nor().scl(3f));
		}
    }

    private void anim() {
        if (position.x > lastPosition.x) {
            lastPosition.x = position.x;
            setStatus(MoveStatus.moveRight);
        } else if (position.x < lastPosition.x) {
            lastPosition.x = position.x;
            setStatus(MoveStatus.moveLeft);
        } else {
            setStatus(MoveStatus.stay);
        }
        if (time >= everyAnimFrameTime) {
            ++curFrameNumber;
            time = 0;
        }
        if (curFrameNumber > animTo) {
            curFrameNumber = animFrom + 2;
        }
        if (flip) {
            image.setDrawable(ResourcesManager.flipedTextures.get(objectName + curFrameNumber));
        } else {
            image.setDrawable(ResourcesManager.textures.get(objectName + curFrameNumber));
        }
    }

    public abstract void shoot();

    public Circle getCollisionArea() {
        return judge;
    }

    public void judge() {
		if (judge.contains(BaseMyPlane.instance.position)) {
			BaseMyPlane.instance.kill();
		}
    }

	public void createDrop(DropItemType dip) {
		DropItem.create(this, dip);
	}
}
