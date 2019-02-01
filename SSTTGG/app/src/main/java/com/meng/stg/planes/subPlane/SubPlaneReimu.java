package com.meng.stg.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletInduce;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBulletStraight;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.helpers.TextureNameManager;

/*
 subplane
 */
public class SubPlaneReimu extends BaseSubPlane{
    private int degree=0;
    private Vector2 vel;
    private Vector2 tmpv;

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
                -16,20,16,20,
                -32,0,32,0,
                -16,20,0,30,16,20,
                -32,0,0,32,32,0,
                -16,20,-8,30,8,30,16,20,
                -32,0,-16,32,16,32,32,0,
        };
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

    @Override
    public void shoot(){
        if(existTime%7==1){
            if(myPlane.slow){
                vel.set(0,37);
                ReimuSubPlaneBulletStraight.Pool.obtain().init(new Vector2(objectCenter.x+4,objectCenter.y+16),vel);
                ReimuSubPlaneBulletStraight.Pool.obtain().init(new Vector2(objectCenter.x-4,objectCenter.y+16),vel);
            }else{
                switch(subPlaneNumber){
                    case 1:
                        tmpv.set(0,8).rotate(60);
                        break;
                    case 2:
                        tmpv.set(0,8).rotate(30);
                        break;
                    case 3:
                        tmpv.set(0,8).rotate(-30);
                        break;
                    case 4:
                        tmpv.set(0,8).rotate(-60);
                        break;
                }
                ReimuSubPlaneBulletInduce.Pool.obtain().init(new Vector2(objectCenter.x,objectCenter.y),tmpv);
            }
        }
    }
}
