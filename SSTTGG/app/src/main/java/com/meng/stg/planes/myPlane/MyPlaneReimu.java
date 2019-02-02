package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.planes.AnimationManager;
import com.meng.stg.planes.subPlane.SubPlaneReimu;

/*
 my plane
 */
public class MyPlaneReimu extends BaseMyPlane{

    @Override
    public void shoot(){
        if(existTime%3==1){
            Vector2 vel=new Vector2(0,47);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(objectCenter.x+8,objectCenter.y+32),vel);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(objectCenter.x-8,objectCenter.y+32),vel);
        }
    }

    @Override
    public void Init(){
        super.Init();
        bombTime=Data.ReimuBombTime;
        animationManager=new AnimationManager(this,5);
        switch(power){
            case 4:
                subPlane4=new SubPlaneReimu();
                subPlane4.init(this,4);
            case 3:
                subPlane3=new SubPlaneReimu();
                subPlane3.init(this,3);
            case 2:
                subPlane2=new SubPlaneReimu();
                subPlane2.init(this,2);
            case 1:
                subPlane1=new SubPlaneReimu();
                subPlane1.init(this,1);
        }
    }

    @Override
    public void Kill(){
        if(true){
            return;
        }
        super.Kill();
        switch(power){
            case 4:
                subPlane4.kill();
            case 3:
                subPlane3.kill();
            case 2:
                subPlane2.kill();
            case 1:
                subPlane1.kill();
        }
        ObjectPools.imagePool.free(image);
        new MyPlaneReimu().Init();
    }

    @Override
    public void update(){
        super.update();
        switch(power){
            case 4:
                subPlane4.update();
            case 3:
                subPlane3.update();
            case 2:
                subPlane2.update();
            case 1:
                subPlane1.update();
        }
    }

    public void onPowerInc(){
        switch(power){
            case 4:
                subPlane4=new SubPlaneReimu();
                subPlane4.init(this,4);
                break;
            case 3:
                subPlane3=new SubPlaneReimu();
                subPlane3.init(this,3);
                break;
            case 2:
                subPlane2=new SubPlaneReimu();
                subPlane2.init(this,2);
                break;
            case 1:
                subPlane1=new SubPlaneReimu();
                subPlane1.init(this,1);
                break;
        }
    }

    @Override
    public void bomb(){
        Vector2 vel=new Vector2(0,30);
        if(bombTime%16==0){
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x,0),vel);
        }
        if(bombTime%16==4){
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x-20,0),vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x+20,0),vel);
        }
        if(bombTime%16==8){
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x-40,0),vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x+40,0),vel);
        }
        if(bombTime%16==12){
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x-20,0),vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(objectCenter.x+20,0),vel);
        }
    }
}
