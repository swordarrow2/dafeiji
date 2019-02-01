package com.meng.stg.bullets.myPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.bullets.BaseMyBullet;
import com.meng.stg.helpers.ResourcesManager;

import static com.meng.stg.ui.MainScreen.enemys;

public class ReimuBomb extends BaseMyBullet{
    public static Pool<ReimuBomb> Pool=new Pool<ReimuBomb>(){
        @Override
        protected ReimuBomb newObject(){
            return new ReimuBomb();
        }
    };

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("reimu55");
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
