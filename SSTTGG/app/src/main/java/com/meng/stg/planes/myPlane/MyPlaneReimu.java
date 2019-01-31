package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.myPlane.ReimuBomb;
import com.meng.stg.bullets.myPlane.ReimuShoot;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.AnimationManager;
import com.meng.stg.planes.MoveStatus;
import com.meng.stg.planes.subPlane.subPlaneReimu;

/*
 my plane
 */
public class MyPlaneReimu extends BaseMyPlane{

    subPlaneReimu subPlane0, subPlane1, subPlane2, subPlane3;
    AnimationManager animationManager;

    @Override
    public Drawable getStayAnim(){
        animationManager.setStatus(MoveStatus.stay);
        return null;// animationManager.getImage();
    }

    public Drawable getLeftMoveAnim(){
        animationManager.setStatus(MoveStatus.leftMove);
        return null;//animationManager.getImage();
    }

    public Drawable getRightMoveAnim(){
        animationManager.setStatus(MoveStatus.rightMove);
        return null;//animationManager.getImage();
    }

    @Override
    public void shoot(){
        if(existTime%3==1){
            Vector2 vel=new Vector2(0,47);
            ReimuShoot.Pool.obtain().Init(new Vector2(objectCenter.x+8,objectCenter.y+32),vel);
            ReimuShoot.Pool.obtain().Init(new Vector2(objectCenter.x-8,objectCenter.y+32),vel);
        }
    }

    @Override
    public void Init(){
        super.Init();
        bombTime=Data.ReimuBombTime;
        animationManager=new AnimationManager(this,5);
        subPlane0=new subPlaneReimu();
        subPlane0.init(this,0);
        subPlane1=new subPlaneReimu();
        subPlane1.init(this,1);
        subPlane2=new subPlaneReimu();
        subPlane2.init(this,2);
        subPlane3=new subPlaneReimu();
        subPlane3.init(this,3);
    }

    @Override
    public void Kill(){
        super.Kill();
        subPlane0.kill();
        subPlane1.kill();
        subPlane2.kill();
        subPlane3.kill();
        Pools.imagePool.free(image);
        new MyPlaneReimu().Init();
    }

    @Override
    public void update(){
        super.update();
        animationManager.update();
        subPlane0.update();
        subPlane1.update();
        subPlane2.update();
        subPlane3.update();
    }


    @Override
    public void bomb(){
        Vector2 vel=new Vector2(0,30);
        if(bombTime%16==0){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x,0),vel);
        }
        if(bombTime%16==4){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+20,0),vel);
        }
        if(bombTime%16==8){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-40,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+40,0),vel);
        }
        if(bombTime%16==12){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+20,0),vel);
        }
    }
}
