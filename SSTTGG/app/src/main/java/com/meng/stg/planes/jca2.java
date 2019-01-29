package com.meng.stg.planes;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class jca2 {

    private TextureRegion[] regions=new TextureRegion[8];
    private int stat=0;
	Image i;
    public jca2(){
	 
		i=new Image(ResourcesManager.textures.get("slow23"));
        for(int i=0;i<regions.length;i++){
            Pixmap pixmap=new Pixmap(64,64,Pixmap.Format.RGBA8888);
            pixmap.setColor(0,0,1,1);
            pixmap.fillCircle(32,32,10);
            pixmap.setColor(1,1,1,1);
            pixmap.fillCircle(32,32,5);
            pixmap.setColor(1,1,1,0.5f);
            pixmap.drawCircle(32,32,(int)(i*3f)+7);
            pixmap.setColor(1,1,1,1f);
            pixmap.drawCircle(32,32,(int)(i*3f)+8);
            pixmap.setColor(1,1,1,0.5f);
            pixmap.drawCircle(32,32,(int)(i*3f)+9);
            regions[i]=new TextureRegion(new Texture(pixmap));
            pixmap.dispose();
        }
        
    }
	
public Image getImage(){
  return i;
}
    public void update(){
	  i.setRotation(stat);
        stat+=2;
    }

  
}
