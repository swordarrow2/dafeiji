package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.planes.AnimationManager;
import com.meng.stg.planes.subPlane.SubPlaneReimuA;
import com.meng.stg.planes.subPlane.SubPlaneReimuB;
import com.meng.stg.ui.onStartActivity;

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
    public void init(){
        super.init();
        bombTime=Data.ReimuBombTime;
        animationManager=new AnimationManager(this,5);
        if(onStartActivity.pl.equals("A:诱导")){
            switch(power){
                case 4:
                    subPlane4=new SubPlaneReimuA();
                    subPlane4.init(this,4);
                case 3:
                    subPlane3=new SubPlaneReimuA();
                    subPlane3.init(this,3);
                case 2:
                    subPlane2=new SubPlaneReimuA();
                    subPlane2.init(this,2);
                case 1:
                    subPlane1=new SubPlaneReimuA();
                    subPlane1.init(this,1);
            }
        }else if(onStartActivity.pl.equals("B:针")){
            switch(power){
                case 4:
                    subPlane4=new SubPlaneReimuB();
                    subPlane4.init(this,4);
                case 3:
                    subPlane3=new SubPlaneReimuB();
                    subPlane3.init(this,3);
                case 2:
                    subPlane2=new SubPlaneReimuB();
                    subPlane2.init(this,2);
                case 1:
                    subPlane1=new SubPlaneReimuB();
                    subPlane1.init(this,1);
            }
        }else{
            power=0;
        }
    }


    @Override
    public void kill(){
        miss++;
        if(true){
            return;
        }
        super.kill();
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
        new MyPlaneReimu().init();
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

    @Override
    public void onPowerInc(){
        if(power >= 4){
            power=4;
        }
        if(onStartActivity.pl.equals("A:诱导")){
            switch(power){
                case 4:
                    if(subPlane4==null){
                        subPlane4=new SubPlaneReimuA();
                        subPlane4.init(this,4);
                    }
                case 3:
                    if(subPlane3==null){
                        subPlane3=new SubPlaneReimuA();
                        subPlane3.init(this,3);
                    }
                case 2:
                    if(subPlane2==null){
                        subPlane2=new SubPlaneReimuA();
                        subPlane2.init(this,2);
                    }
                case 1:
                    if(subPlane1==null){
                        subPlane1=new SubPlaneReimuA();
                        subPlane1.init(this,1);
                    }
            }
        }else if(onStartActivity.pl.equals("B:针")){
            switch(power){
                case 4:
                    if(subPlane4==null){
                        subPlane4=new SubPlaneReimuB();
                        subPlane4.init(this,4);
                    }
                case 3:
                    if(subPlane4==null){
                        subPlane3=new SubPlaneReimuB();
                        subPlane3.init(this,3);
                    }
                case 2:
                    if(subPlane4==null){
                        subPlane2=new SubPlaneReimuB();
                        subPlane2.init(this,2);
                    }
                case 1:
                    if(subPlane4==null){
                        subPlane1=new SubPlaneReimuB();
                        subPlane1.init(this,1);
                    }
            }
        }
    }

    @Override
    public void onPowerDec(){
        // TODO: Implement this method
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