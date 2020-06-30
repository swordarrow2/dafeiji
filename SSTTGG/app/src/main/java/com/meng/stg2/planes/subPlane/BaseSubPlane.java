package com.meng.stg2.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg2.BaseGameObject;
import com.meng.stg2.planes.myPlane.BaseMyPlane;
import com.meng.stg2.ui.MainScreen;

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
        position=myPlane.position.cpy();
        image.setDrawable(getDrawable());
        image.setSize(size.x,size.y);
        image.setRotation(getRotationDegree());
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        MainScreen.mainGroup.addActor(image);
    }

    public void kill(){
        super.kill();
        image.remove();
    }

    public void update(){
        super.update();
        switch(myPlane.power){
            case 1:
                if(myPlane.slow){
                    nowPosition.set(myPlane.position.x+subPlanePosition[0],myPlane.position.y+subPlanePosition[1]);
                }else{
                    nowPosition.set(myPlane.position.x+subPlanePosition[2],myPlane.position.y+subPlanePosition[3]);
                }
                break;
            case 2:
                if(myPlane.slow){
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[4],myPlane.position.y+subPlanePosition[5]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[6],myPlane.position.y+subPlanePosition[7]);
                            break;
                    }
                }else{
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[8],myPlane.position.y+subPlanePosition[9]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[10],myPlane.position.y+subPlanePosition[11]);
                            break;
                    }
                }
                break;
            case 3:
                if(myPlane.slow){
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[12],myPlane.position.y+subPlanePosition[13]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[14],myPlane.position.y+subPlanePosition[15]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.position.x+subPlanePosition[16],myPlane.position.y+subPlanePosition[17]);
                            break;
                    }
                }else{
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[18],myPlane.position.y+subPlanePosition[19]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[20],myPlane.position.y+subPlanePosition[21]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.position.x+subPlanePosition[22],myPlane.position.y+subPlanePosition[23]);
                            break;
                    }
                }
                break;
            case 4:
                if(myPlane.slow){
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[24],myPlane.position.y+subPlanePosition[25]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[26],myPlane.position.y+subPlanePosition[27]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.position.x+subPlanePosition[28],myPlane.position.y+subPlanePosition[29]);
                            break;
                        case 4:
                            nowPosition.set(myPlane.position.x+subPlanePosition[30],myPlane.position.y+subPlanePosition[31]);
                            break;
                    }
                }else{
                    switch(bianHao){
                        case 1:
                            nowPosition.set(myPlane.position.x+subPlanePosition[32],myPlane.position.y+subPlanePosition[33]);
                            break;
                        case 2:
                            nowPosition.set(myPlane.position.x+subPlanePosition[34],myPlane.position.y+subPlanePosition[35]);
                            break;
                        case 3:
                            nowPosition.set(myPlane.position.x+subPlanePosition[36],myPlane.position.y+subPlanePosition[37]);
                            break;
                        case 4:
                            nowPosition.set(myPlane.position.x+subPlanePosition[38],myPlane.position.y+subPlanePosition[39]);
                            break;
                    }
                }
                break;
        }
        position.add(nowPosition.sub(position).scl(0.2f));
        image.setDrawable(getDrawable());
        image.setRotation(getRotationDegree());
        image.setPosition(position.x,position.y,Align.center);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        shoot();
    }


    public abstract Drawable getDrawable();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();

    public abstract void shoot();

    public abstract int[] getSubPlanePosition();
}
