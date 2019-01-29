package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.ResourcesManager;

import static com.meng.stg.ui.MainScreen.enemys;

/**
 * Created by Administrator on 2019/1/29.
 */

public class ReimuSubPlaneBullet extends BaseMyPlaneBullet{
    public static com.badlogic.gdx.utils.Pool<ReimuSubPlaneBullet> Pool=new Pool<ReimuSubPlaneBullet>(){
        @Override
        protected ReimuSubPlaneBullet newObject(){
            return new ReimuSubPlaneBullet();
        }
    };

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("reimu29");
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
                if(!(enemys[i]==null)){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        enemys[i].hit();
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
