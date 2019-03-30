package com.meng.TaiHunDanmaku.planes;

import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.ui.*;

public class JudgeCircleAnimation2 extends BaseGameObject{
    private int stat=0;

    public JudgeCircleAnimation2(){
    }

    @Override
    public void init(){
        super.init();
        image.setDrawable(ResourcesManager.textures.get("effect24"));
		image.setSize(48,48);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
		MainScreen.mainGroup.addActor(image);
    }

    public void update(){
		objectCenter=BaseMyPlane.instance.objectCenter;
        image.setRotation(stat);
		if(BaseMyPlane.instance.slow){
			image.setSize(48,48);
		  }else{
			image.setSize(0,0);
		  }
        stat-=2;
		image.setPosition(objectCenter.x,objectCenter.y,Align.center);
    }

    @Override
    public void kill(){
        super.kill();
    }
}
