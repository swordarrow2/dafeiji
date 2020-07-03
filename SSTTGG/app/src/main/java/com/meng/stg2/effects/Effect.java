package com.meng.stg2.effects;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.ui.*;
import java.util.*;
import java.util.concurrent.*;

public class Effect extends BaseGameObject {

    public static HashSet<Effect> instances=new HashSet<Effect>();
    public static LinkedBlockingQueue<Effect> toDelete=new LinkedBlockingQueue<Effect>();
    public static LinkedBlockingQueue<Effect> toAdd=new LinkedBlockingQueue<Effect>();

    public EffectType effectType;
    private int drawableNumber=0;
	public Circle judgeCircle = new Circle();

    public static void create(Vector2 center, EffectType type) {
        ObjectPools.effectPool.obtain().init(center, type);
    }

    public void init(Vector2 center, EffectType type) {
        super.init();
        toAdd.add(this);
        existTime = 0;
        judgeCircle.set(position, 16);
        position.set(center);
        image.setPosition(center.x, center.y, Align.center);
        effectType = type;
        image.setSize(16, 16);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    public void kill() {
        toDelete.add(this);
        image.remove();
    }

    public void update() {
        super.update();
        if (existTime > 40 || judgeCircle.x < -5 || judgeCircle.x > 390 || judgeCircle.y < -5 || judgeCircle.y > 460) {
            kill();
        }
        drawableNumber = existTime / 2;
        image.setDrawable(ResourcesManager.textures.get("effect" + (drawableNumber + 540)));
    }

    public static void updateAll() {
        while (!toDelete.isEmpty()) {
            instances.remove(toDelete.poll());
        }
        while (!toAdd.isEmpty()) {
            instances.add(toAdd.poll());
        }
        for (Effect item : instances) {
            item.update();
        }
    }

    public Drawable getDrawable() {
        if (drawable == null) {
            drawable = ResourcesManager.textures.get("effect540");
        }
        return drawable;
    }
}
