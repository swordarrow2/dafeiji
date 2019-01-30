package com.meng.stg.planes;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.stg.*;

public class jca3
{
	private int stat=0;
	Image judgeAnim;

    public jca3(){ 
		judgeAnim=new Image(ResourcesManager.textures.get("slow24")); 
	  }

	public Image getImage(){
		return judgeAnim;
	  }

    public void update(){
		judgeAnim.setRotation(stat);
        stat-=2;
	  }
  }
