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
    public static com.badlogic.gdx.utils.Pool<ReimuBomb> Pool=new Pool<ReimuBomb>(){
        @Override
        protected ReimuBomb newObject(){
            return new ReimuBomb();
        }
    };

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("reimu29");
        }
        return drawable;
    }

    private boolean ifJudgeCircleMeet(Shape2D s1,Shape2D s2){
        Circle c1=(Circle)s1;
        Circle c2=(Circle)s2;
        float circle1x=c1.x;
        float circle1y=c1.y;
        float circle1r=c1.radius;
        float circle2x=c2.x;
        float circle2y=c2.y;
        float circle2r=c2.radius;
        if((Math.pow((circle2x-circle1x),2)+Math.pow((circle2y-circle1y),2))>(circle1r+circle2r)){
            return false;
        }else{
            return true;
        }
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
