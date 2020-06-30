package com.meng.stg2.planes;

import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.planes.myPlane.*;
import com.meng.stg2.ui.*;

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
		position=BaseMyPlane.instance.position;
        image.setRotation(stat);
		if(BaseMyPlane.instance.slow){
			image.setSize(48,48);
		  }else{
			image.setSize(0,0);
		  }
        stat-=2;
		image.setPosition(position.x,position.y,Align.center);
    }

    @Override
    public void kill(){
        super.kill();
    }
}
