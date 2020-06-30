package com.meng.stg2.planes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg2.BaseGameObject;
import com.meng.stg2.helpers.ResourcesManager;
import com.meng.stg2.planes.myPlane.*;
import com.meng.stg2.ui.*;
import com.badlogic.gdx.utils.*;

public class JudgeCircleAnimation extends BaseGameObject{

    private int stat=0;

    public JudgeCircleAnimation(){
	  }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("effect23"));
		MainScreen.mainGroup.addActor(image);
		image.setSize(48,48);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
	  }

	@Override
	public void kill(){
		super.kill();
	  }

    public void update(){
		position=BaseMyPlane.instance.position;
        image.setRotation(stat);
		if(BaseMyPlane.instance.slow){
		  image.setSize(48,48);
		}else{
		  image.setSize(0,0);
		}
        stat+=2;
		image.setPosition(position.x,position.y,Align.center);
		image.toFront();
	  }
  }
