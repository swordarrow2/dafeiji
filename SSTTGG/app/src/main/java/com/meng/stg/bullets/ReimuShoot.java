package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.helpers.Resources;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class ReimuShoot extends BaseMyPlaneBullet{
 
    public static Pool<ReimuShoot> Pool=new Pool<ReimuShoot>(){
        @Override
        protected ReimuShoot newObject(){
            return new ReimuShoot();
        }
    };

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get("reimu24");
        }
        return drawable;
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
