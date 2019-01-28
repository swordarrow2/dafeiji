package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.GameTextureManager;

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
            drawable=GameTextureManager.Textures.get("reimu24");
        }
        return drawable;
	  }

	@Override
	public void Update(){
		super.Update();
		Drawer.toBack();
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
