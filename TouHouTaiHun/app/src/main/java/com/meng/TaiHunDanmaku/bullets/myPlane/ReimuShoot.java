package com.meng.TaiHunDanmaku.bullets.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.bullets.BaseMyBullet;
import com.meng.TaiHunDanmaku.helpers.TextureNameManager;
import com.meng.TaiHunDanmaku.helpers.*;

public class ReimuShoot extends BaseMyBullet{

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
