package com.meng.stg.bullets;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.helpers.Resources;

public class AliceShoot extends BaseMyPlaneBullet{
    public static Pool<AliceShoot> Pool=new Pool<AliceShoot>(){
        @Override
        protected AliceShoot newObject(){
            return new AliceShoot();
        }
    };

    // @Override
    //  public void Update(){
    //     super.Update();
    //     Drawer.toBack();
    // }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get("AliceShoot");
        }
        return drawable;
    }
}
