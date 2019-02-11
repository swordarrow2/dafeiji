package com.meng.stg.effects.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.effects.BaseEffect;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.ui.MainScreen;

public class Effect extends BaseEffect{

    private int drawableNumber=0;

    public static void create(Vector2 center,EffectType type){
        ObjectPools.effectPool.obtain().init(center,type);
    }

    private void init(Vector2 center,EffectType type){
        super.init();
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        effectType=type;
        image.setSize(16,16);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public Vector2 getSize(){
        return size;
    }

    @Override
    public void update(){
        super.update();
        drawableNumber=existTime/2;
        image.setDrawable(ResourcesManager.textures.get("effect"+(drawableNumber+540)));
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("effect540");
        }
        return drawable;
    }
}
