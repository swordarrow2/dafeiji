package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.ResourcesManager;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.helpers.TextureNameManager;

public class ReimuShoot extends BaseMyBullet{

    public static Pool<ReimuShoot> Pool=new Pool<ReimuShoot>(){
        @Override
        protected ReimuShoot newObject(){
            return new ReimuShoot();
        }
    };

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
