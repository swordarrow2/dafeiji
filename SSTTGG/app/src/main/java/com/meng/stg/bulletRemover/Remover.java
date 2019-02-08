package com.meng.stg.bulletRemover;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.helpers.TextureNameManager;

public class Remover extends BaseMyRemover{

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get(TextureNameManager.ReimuBullet);
        }
        return drawable;
    }

    @Override
    public void update(){
        super.update();
        image.toBack();
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(64,16);
    }

    @Override
    public float getRotationDegree(){
        return 90;
    }
}
