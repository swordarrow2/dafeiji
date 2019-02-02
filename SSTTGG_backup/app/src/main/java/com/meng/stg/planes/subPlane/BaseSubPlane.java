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
import com.meng.stg.bullets.subPlane.*;

public abstract class BaseSubPlane extends BaseGameObject{

    public Vector2 nowPosition=Vector2.Zero;
    public Vector2 size=Vector2.Zero;
    public Image image=null;
    public BaseMyPlane myPlane;

    public Drawable drawable=null;
    public int bianHao=1;

    private int[] subPlanePosition;

    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        this.bianHao=subPlaneNumber;
        subPlanePosition=getSubPlanePosition();
        this.myPlane=myPlane;
        size=getSize();
        objectCenter=myPlane.objectCenter.cpy();
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
		switch(myPlane.power){
            case 1:
			  if(myPlane.slow){
				  nowPosition.set(myPlane.objectCenter.x+subPlanePosition[0],myPlane.objectCenter.y+subPlanePosition[1]);
                }else{
				  nowPosition.set(myPlane.objectCenter.x+subPlanePosition[2],myPlane.objectCenter.y+subPlanePosition[3]);
                }
			  break;
            case 2:
			  if(myPlane.slow){
				  switch(bianHao){
					  case 1:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[4],myPlane.objectCenter.y+subPlanePosition[5]);
						break;
					  case 2:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[6],myPlane.objectCenter.y+subPlanePosition[7]);
						break;
                    }
                }else{
				  switch(bianHao){
					  case 1:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[8],myPlane.objectCenter.y+subPlanePosition[9]);
						break;
					  case 2:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[10],myPlane.objectCenter.y+subPlanePosition[11]);
						break;
                    }
                }
			  break;
            case 3:
			  if(myPlane.slow){
				  switch(bianHao){
					  case 1:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[12],myPlane.objectCenter.y+subPlanePosition[13]);
						break;
					  case 2:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[14],myPlane.objectCenter.y+subPlanePosition[15]);
						break;
					  case 3:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[16],myPlane.objectCenter.y+subPlanePosition[17]);
						break;
                    }
                }else{
				  switch(bianHao){
					  case 1:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[18],myPlane.objectCenter.y+subPlanePosition[19]);
						break;
					  case 2:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[20],myPlane.objectCenter.y+subPlanePosition[21]);
						break;
					  case 3:
						nowPosition.set(myPlane.objectCenter.x+subPlanePosition[22],myPlane.objectCenter.y+subPlanePosition[23]);
						break;
                    }
                }
			  break;
            case 4:
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
			  break;
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