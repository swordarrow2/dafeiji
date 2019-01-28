package com.meng.stg.planes.subPlane;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.GameTextureManager;
import com.meng.stg.bullets.*;

public class subPlaneReimu extends BaseSubPlane{
	int i=0;
	@Override
	public Drawable getDrawable(){
		return GameTextureManager.Textures.get("reimu53");
	  }

	@Override
	public float getRotationDegree(){
		i+=5;
		return i++;
	  }

	@Override
	public Vector2 getSize(){
		return new Vector2(16,16);
	  }

	@Override
	public void shoot(){
		if(ExistTime%5==1){
            Vector2 vel=new Vector2(0,37);
            ReimuBomb.Pool.obtain().Init(new Vector2(Center.x+4,Center.y+16),vel);
			ReimuBomb.Pool.obtain().Init(new Vector2(Center.x-4,Center.y+16),vel);
		  }
	  }
  }
