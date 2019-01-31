package com.meng.stg.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBullet;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.helpers.TextureNameManager;

/*
 subplane
 */
public class SubPlaneReimu extends BaseSubPlane{
    private int degree=0;

    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
    }

    @Override
    public float getRotationDegree(){
        degree+=5;
        return degree;
    }

    @Override
    public int[] getSubPlanePosition(){
        return new int[]{
                0,20,
                0,32,
                16,20,-16,20,
                32,0,-32,0,
                16,20,-16,20,0,30,
                32,0,-32,0,0,-32,
                16,20,-16,20,8,30,-8,30,
                32,0,-32,0,16,-32,-16,-32
        };
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

    @Override
    public void shoot(){
        if(existTime%7==1){
            Vector2 vel=new Vector2(0,37);
            ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(objectCenter.x+4,objectCenter.y+16),vel);
            ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(objectCenter.x-4,objectCenter.y+16),vel);
        }
    }
}
