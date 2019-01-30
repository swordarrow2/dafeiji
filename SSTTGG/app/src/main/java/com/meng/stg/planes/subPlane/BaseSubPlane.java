package com.meng.stg.planes.subPlane;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

public abstract class BaseSubPlane extends BaseGameObject{
    public Vector2 center=new Vector2();
    public Vector2 nowPosition=new Vector2();
    public Vector2 size=new Vector2();
    public Image image=null;
    protected Rectangle drawBox=new Rectangle();
    public BaseMyPlane myPlane;

    public Drawable drawable=null;
    public int subPlaneNumber=0;

    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        this.subPlaneNumber=subPlaneNumber;
        this.myPlane=myPlane;
        size=getSize();
        image=Pools.imagePool.obtain();
        image.setDrawable(getDrawable());
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        MainScreen.mainGroup.addActor(image);
    }

    public void kill(){
        image.setRotation(0);
        image.remove();
        Pools.imagePool.free(image);
    }

    public void update(){
        super.update();
        center.add(nowPosition.sub(center).scl(0.2f));
        image.setDrawable(getDrawable());
        image.setRotation(getRotationDegree());
        image.setPosition(center.x,center.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        drawBox.set(image.getX(),image.getY(),image.getWidth(),image.getHeight());
        shoot();
    }


    public abstract Drawable getDrawable();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();

    public abstract void shoot();

}
