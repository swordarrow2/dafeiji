package com.meng.TaiHunDanmaku.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.BaseGameObject;
import com.meng.TaiHunDanmaku.planes.myPlane.BaseMyPlane;
import com.meng.TaiHunDanmaku.ui.FightScreen;
import com.meng.TaiHunDanmaku.helpers.*;

public abstract class BaseSubPlane extends BaseGameObject{

    public Vector2 nowPosition=Vector2.Zero;
    public BaseMyPlane myPlane;
    public int bianHao=1;

    private int[] subPlanePosition;

    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        super.init();
        this.bianHao=subPlaneNumber;
        subPlanePosition=getSubPlanePosition();
        this.myPlane=myPlane;
        size=getSize();
        objectCenter=myPlane.objectCenter.cpy();
        image.setDrawable(getDrawable());
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        FightScreen.instence.groupNormal.addActor(image);
		image.setZIndex(Data.zIndexMyPlane);
    }

    public void kill(){
        super.kill();
        image.remove();
    }

    public void update(){
        super.update();
                     if(myPlane.slow){
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[24],myPlane.objectCenter.y+subPlanePosition[25]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[26],myPlane.objectCenter.y+subPlanePosition[27]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[28],myPlane.objectCenter.y+subPlanePosition[29]);
                            break;
                        case 4:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[30],myPlane.objectCenter.y+subPlanePosition[31]);
                            break;
                    }
                }else{
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[32],myPlane.objectCenter.y+subPlanePosition[33]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[34],myPlane.objectCenter.y+subPlanePosition[35]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[36],myPlane.objectCenter.y+subPlanePosition[37]);
                            break;
                        case 4:
                            nowPosition.set(myPlane.objectCenter.x+subPlanePosition[38],myPlane.objectCenter.y+subPlanePosition[39]);
                            break;
                    }
                }      
        objectCenter.add(nowPosition.sub(objectCenter).scl(0.2f));
        image.setDrawable(getDrawable());
        image.setRotation(getRotationDegree());
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        shoot();
    }


    public abstract Drawable getDrawable();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();

    public abstract void shoot();

    public abstract int[] getSubPlanePosition();
}
