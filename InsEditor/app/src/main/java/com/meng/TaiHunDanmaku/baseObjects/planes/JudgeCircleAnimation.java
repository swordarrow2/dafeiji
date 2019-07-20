package com.meng.TaiHunDanmaku.baseObjects.planes;

import com.meng.TaiHunDanmaku.BaseGameObject;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.helpers.*;

public class JudgeCircleAnimation extends BaseGameObject{

    private int stat=0;

    public JudgeCircleAnimation(){
	  }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("effect23"));
		FightScreen.instence.groupNormal.addActor(image);
		image.setSize(48,48);
		image.setZIndex(Data.zIndexJudgePoint);
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
	  }
  }
