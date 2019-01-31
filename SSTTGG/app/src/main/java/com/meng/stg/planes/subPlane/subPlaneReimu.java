package com.meng.stg.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.ResourcesManager;
import com.meng.stg.bullets.subPlane.ReimuSubPlaneBullet;

/*
 subplane
 */
public class subPlaneReimu extends BaseSubPlane{
    private int degree=0;

    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get("reimu53");
	  }

    @Override
    public float getRotationDegree(){
        degree+=5;
        return degree;
	  }

    @Override
    public void update(){
        super.update();
        switch(myPlane.subPlaneCount){
			case 0:
			case 1:
			case 2:
			case 3:
			  if(myPlane.slow){
				  switch(subPlaneNumber){
					  case 1:
						nowPosition=myPlane.objectCenter.cpy().add(30,20);
						break;
					  case 2:
						nowPosition=myPlane.objectCenter.cpy().add(-30,20);
						break;
					  case 3:
						nowPosition=myPlane.objectCenter.cpy().add(0,30);
						break;				  
					}
				}else{
				  switch(subPlaneNumber){
					  case 1:
						nowPosition=myPlane.objectCenter.cpy().add(38,-16);
						break;
					  case 2:
						nowPosition=myPlane.objectCenter.cpy().add(-38,-16);
						break;
					  case 3:
						nowPosition=myPlane.objectCenter.cpy().add(0,-32);
						break;
					}
				}
			  break;
			case 4:
			  if(myPlane.slow){
				  switch(subPlaneNumber){
					  case 1:
						nowPosition=myPlane.objectCenter.cpy().add(30,20);
						break;
					  case 2:
						nowPosition=myPlane.objectCenter.cpy().add(-30,20);
						break;
					  case 3:
						nowPosition=myPlane.objectCenter.cpy().add(10,30);
						break;
					  case 4:
						nowPosition=myPlane.objectCenter.cpy().add(-10,30);
						break;
					}
				}else{
				  switch(subPlaneNumber){
					  case 1:
						nowPosition=myPlane.objectCenter.cpy().add(38,-16);
						break;
					  case 2:
						nowPosition=myPlane.objectCenter.cpy().add(-38,-16);
						break;
					  case 3:
						nowPosition=myPlane.objectCenter.cpy().add(16,-32);
						break;
					  case 4:
						nowPosition=myPlane.objectCenter.cpy().add(-16,-32);
						break;
					}
				}
			  break;
		  }
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public void shoot(){
        if(existTime%7==1){
            Vector2 vel=new Vector2(0,37);
            ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(objectCenter.x+4,objectCenter.y+16),vel);
            ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(objectCenter.x-4,objectCenter.y+16),vel);
		  }
	  }
  }
