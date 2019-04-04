package com.meng.TaiHunDanmaku.planes;

import com.meng.TaiHunDanmaku.BaseGameObject;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.badlogic.gdx.utils.*;

public class JudgeCircleAnimation extends BaseGameObject{

    private int stat=0;

    public JudgeCircleAnimation(){
	  }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("effect23"));
		FightScreen.groupNormal.addActor(image);
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
		if(BaseMyPlane.instance.slow){
		  image.setSize(48,48);
		}else{
		  image.setSize(0,0);
		}
        stat+=2;
		image.setPosition(objectCenter.x,objectCenter.y,Align.center);
		image.toFront();
	  }
  }
