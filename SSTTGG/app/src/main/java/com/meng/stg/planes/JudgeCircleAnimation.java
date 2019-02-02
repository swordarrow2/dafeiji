package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg.BaseGameObject;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.planes.myPlane.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.utils.*;

public class JudgeCircleAnimation extends BaseGameObject{

    private int stat=0;

    public JudgeCircleAnimation(){
	  }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("slow23"));
		MainScreen.mainGroup.addActor(image);
		image.setSize(48,48);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
	  }

	@Override
	public void kill(){
		super.kill();
	  }

    public void update(){
		objectCenter=BaseMyPlane.instance.objectCenter;
        image.setRotation(stat);
        stat+=2;
		image.setPosition(objectCenter.x,objectCenter.y,Align.center);
	  }
  }
