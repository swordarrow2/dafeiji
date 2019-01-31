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

    public Vector2 nowPosition=new Vector2();
    public Vector2 size=new Vector2();
    public Image image=null;
    protected Rectangle drawBox=new Rectangle();
    public BaseMyPlane myPlane;

    public Drawable drawable=null;
    public int subPlaneNumber=0;

    private int[] subPlanePosition;

    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        this.subPlaneNumber=subPlaneNumber;
        subPlanePosition=getSubPlanePosition();
        this.myPlane=myPlane;
        size=getSize();
        objectCenter.set(myPlane.objectCenter);
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
        objectCenter.add(nowPosition.sub(objectCenter).scl(0.2f));
        image.setDrawable(getDrawable());
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        drawBox.set(image.getX(),image.getY(),image.getWidth(),image.getHeight());
        shoot();
        switch(myPlane.subPlaneCount){
            case 1:
                if(myPlane.slow){
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[0],subPlanePosition[1]);
                            break;
                    }
                }else{
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[2],subPlanePosition[3]);
                            break;
                    }
                }
                break;
            case 2:
                if(myPlane.slow){
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[4],subPlanePosition[5]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[6],subPlanePosition[7]);
                            break;
                    }
                }else{
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[8],subPlanePosition[9]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[10],subPlanePosition[11]);
                            break;
                    }
                }
                break;
            case 3:
                if(myPlane.slow){
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[12],subPlanePosition[13]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[14],subPlanePosition[15]);
                            break;
                        case 3:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[16],subPlanePosition[17]);
                            break;
                    }
                }else{
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[18],subPlanePosition[19]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[20],subPlanePosition[21]);
                            break;
                        case 3:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[22],subPlanePosition[23]);
                            break;
                    }
                }
                break;
            case 4:
                if(myPlane.slow){
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[24],subPlanePosition[25]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[26],subPlanePosition[27]);
                            break;
                        case 3:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[28],subPlanePosition[29]);
                            break;
                        case 4:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[30],subPlanePosition[31]);
                            break;
                    }
                }else{
                    switch(subPlaneNumber){
                        case 1:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[32],subPlanePosition[33]);
                            break;
                        case 2:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[34],subPlanePosition[35]);
                            break;
                        case 3:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[36],subPlanePosition[37]);
                            break;
                        case 4:
                            nowPosition=myPlane.objectCenter.cpy().add(subPlanePosition[38],subPlanePosition[39]);
                            break;
                    }
                }
                break;
        }
    }


    public abstract Drawable getDrawable();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();

    public abstract void shoot();

    public abstract int[] getSubPlanePosition();
}
