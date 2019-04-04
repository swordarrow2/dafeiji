package com.meng.TaiHunDanmaku.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.meng.TaiHunDanmaku.planes.myPlane.*;

public class MainActivity extends AndroidApplication{
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        instance=this;

        AndroidApplicationConfiguration cfg=new AndroidApplicationConfiguration();
		cfg.touchSleepTime=0;
        Game game=new GameMain();
		
        initialize(game,cfg); 
       
    }
}
