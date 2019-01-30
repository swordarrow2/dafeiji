package com.meng.stg.planes;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class jca2{

    private int stat=0;
	Image judgeAnim;
	
    public jca2(){ 
		judgeAnim=new Image(ResourcesManager.textures.get("slow23")); 
	  }

	public Image getImage(){
		return judgeAnim;
	  }

    public void update(){
		judgeAnim.setRotation(stat);
        stat+=2;
	  }
  }
