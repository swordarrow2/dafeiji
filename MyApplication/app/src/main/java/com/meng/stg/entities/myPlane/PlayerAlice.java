package com.meng.stg.entities.myPlane;

import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.entities.*;
import com.meng.stg.helpers.*;

public class PlayerAlice extends basePlayer{
		@Override
		public Drawable getDrawable(){
			return Resources.Textures.get("Alice");
		  }
	@Override
	public void Init(){
		super.Init();
		Drawer.setSize(49,95);
	  }

	@Override
	public void Update(){
		super.Update();
		Drawer.toBack();
		this.Center.set(basePlayer.Instance.Center);
	  }

	/*@Override
	 public void Kill()
	 {
	 Alice.Kill();
	 super.Kill();
	 }*/

  }
