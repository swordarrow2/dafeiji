package com.meng.stg2.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.task.*;
import com.meng.stg2.ui.*;

public class EnemyBullet extends BaseEnemyBullet {

    private int colorNum=0;
    private int formNum=0;

    public static void create(Vector2 center, BulletForm bulletForm, BulletColor bulletColor, float speed, int ref, int through, boolean highLight, Task[] mm) {
        ObjectPool.getEnemyBullet().init(center, bulletForm, bulletColor, speed, ref, through, highLight, mm);
    }

    public void init(Vector2 center, BulletForm bulletForm, BulletColor bulletColor, float speed, int ref, int though, boolean highLight, Task[] tasks) {
        super.init();
        for (Task task : tasks) {
            taskManager.addTask(task);
        }
		bulletSpeed = speed;
        refCount = ref;
        thoughCount = though;
        position.set(center);
        image.setPosition(center.x, center.y, Align.center);
        judgeCircle.set(position, image.getWidth() / 2);
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
			case tuo:
				formNum = 9961;
        }
        image.setDrawable(getDrawable());
		if (highLight) {
			MainScreen.instence.groupHighLight.addActor(image);
		} else {
			MainScreen.instence.groupNormal.addActor(image);
		}
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(16, 16);
    }

	@Override
	public void kill() {
		// TODO: Implement this method
		super.kill();
		drawable = null;
		ObjectPool.recycle(this);
	}

    @Override
    public Drawable getDrawable() {
		if (formNum == 13 || formNum == 14) {
			image.setSize(14, 16);
		}
        if (drawable == null) {
			if (formNum == 9961) {
				drawable = ResourcesManager.textures.get("bullet398");
			} else
				drawable = ResourcesManager.textures.get("bullet" + (formNum * 16 + colorNum));
        }
        return drawable;
    }
}
