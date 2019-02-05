package com.meng.stg.backupItem;
import com.badlogic.gdx.math.*;
import com.meng.stg.helpers.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.bullets.enemy.*;

public class itemPower extends EnemyBullet{

	@Override
	public Vector2 getSize(){	
		return new Vector2(16,16);
	  }

	@Override
	public Drawable getDrawable(){
		return ResourcesManager.textures.get("item518");
	  }

	public itemPower init(Vector2 center){
		// TODO: Implement this method
		//super.init(center);
		return this;
	  }


	

  }
