package com.meng.stg.planes.subPlane;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
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
		if(myPlane.slow){
			switch(subPlaneNumber){
				case 0:
					nowPosition=myPlane.objectCenter.cpy().add(-30,-20);
					break;
				case 1:
					nowPosition=myPlane.objectCenter.cpy().add(30,-20);
					break;
				case 2:
					nowPosition=myPlane.objectCenter.cpy().add(-10,-30);
					break;
				case 3:
					nowPosition=myPlane.objectCenter.cpy().add(10,-30);
					break;
			}
		}else{
			switch(subPlaneNumber){
				case 0:
					nowPosition=myPlane.objectCenter.cpy().add(-38,16);
					break;
				case 1:
					nowPosition=myPlane.objectCenter.cpy().add(38,16);
					break;
				case 2:
					nowPosition=myPlane.objectCenter.cpy().add(-16,32);
					break;
				case 3:
					nowPosition=myPlane.objectCenter.cpy().add(16,32);
					break;
			}
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
            ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(center.x+4,center.y+16),vel);
			ReimuSubPlaneBullet.Pool.obtain().Init(new Vector2(center.x-4,center.y+16),vel);
		  }
	  }
  }
