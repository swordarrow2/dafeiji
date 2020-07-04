package com.meng.stg2.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.meng.stg2.characters.player.*;

public class MainActivity extends AndroidApplication{
    public static MainActivity instance;

	public static int status=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        instance=this;

        AndroidApplicationConfiguration cfg=new AndroidApplicationConfiguration();
		cfg.touchSleepTime=0;
        Game game=new GameMain();
		
        initialize(game,cfg); 
       
    }

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
			status=0;
			return true;
        }
		if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
			status=0;
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
		  status=1;
		  return true;
        }
		if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
			status=2;
			return true;
		  }
		if(keyCode==KeyEvent.KEYCODE_MENU){
			BaseMyPlane.instance.slow=true;
			return true;
		  }
		if(keyCode==KeyEvent.KEYCODE_BACK){
			BaseMyPlane.instance.slow=false;
			return true;
		  }
        return super.onKeyDown(keyCode,event);
	  }

	
    @Override
    protected void onPause(){
        super.onPause();
    }
}
