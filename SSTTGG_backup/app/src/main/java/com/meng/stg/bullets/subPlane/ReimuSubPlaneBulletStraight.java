package com.meng.stg.bullets.subPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.helpers.TextureNameManager;

import static com.meng.stg.ui.MainScreen.enemys;

public class ReimuSubPlaneBulletStraight extends BaseMyBullet{

    public static com.badlogic.gdx.utils.Pool<ReimuSubPlaneBulletStraight> Pool=new Pool<ReimuSubPlaneBulletStraight>(){
        @Override
        protected ReimuSubPlaneBulletStraight newObject(){
            return new ReimuSubPlaneBulletStraight();
        }
    };

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
