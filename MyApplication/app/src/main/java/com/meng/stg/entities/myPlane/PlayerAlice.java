package com.meng.stg.entities.myPlane;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.entities.*;
import com.meng.stg.helpers.*;

public class PlayerAlice extends BaseMyPlane{
		@Override
		public Drawable getDrawable(){
			return new TextureRegionDrawable(new TextureRegion(Resources.Textures.get("Alice"))) ;
		  }
	@Override
	public void Init(){
		super.Init();
		Drawer.setSize(49,95);

		Texture texture = Resources.Textures.get("Reimu");
		TextureRegion[][] splitted = TextureRegion.split(texture, texture.getWidth() / 8, texture.getHeight() / 3);
		animationStay = new Animation<TextureRegion>(5, splitted[0]);
		animationStay.setPlayMode(Animation.PlayMode.LOOP);
		animationLeft = new Animation<TextureRegion>(4, splitted[1]);
		animationLeft.setPlayMode(Animation.PlayMode.LOOP);
		animationRight = new Animation<TextureRegion>(4, splitted[2]);
		animationRight.setPlayMode(Animation.PlayMode.LOOP);

	  }

	@Override
	public void Update(){
		super.Update();
		Drawer.toBack();
		this.Center.set(BaseMyPlane.Instance.Center);
	  }

	/*@Override
	 public void Kill()
	 {
	 Alice.Kill();
	 super.Kill();
	 }*/

  }
