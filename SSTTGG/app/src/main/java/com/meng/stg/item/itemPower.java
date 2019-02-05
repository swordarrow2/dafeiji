package com.meng.stg.item;
import com.badlogic.gdx.math.*;
import com.meng.stg.helpers.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class itemPower extends baseItem{

	@Override
	public Vector2 getSize(){	
		return new Vector2(16,16);
	  }

	@Override
	public Drawable getDrawable(){
		return ResourcesManager.textures.get("item518");
	  }

	public itemPower init(Vector2 center,int i){
		// TODO: Implement this method
		super.init(center);
		return this;
	  }


	

  }
