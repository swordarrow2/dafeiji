package com.meng.stg2.bullets.subPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg2.bullets.BaseMyBullet;
import com.meng.stg2.helpers.ResourcesManager;
import com.meng.stg2.helpers.TextureNameManager;

import static com.meng.stg2.ui.MainScreen.enemys;
import com.meng.stg2.helpers.*;
import com.meng.stg2.planes.myPlane.*;

public class ReimuSubPlaneBulletStraight extends BaseMyBullet{

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight);
		  }
        return drawable;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(64,16);
	  }

    public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(enemys[i]!=null){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        enemys[i].hit(BaseMyPlane.instance.slow?17.5f:10.5f);
                        kill();
					  }
				  }
			  }
		  }catch(Exception e){
            e.printStackTrace();
		  }
	  }

    @Override
    public float getRotationDegree(){
        return 90;
	  }
  }
