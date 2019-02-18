package com.meng.stg.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import cn.s3bit.th902.*;

public class MainActivity extends AndroidApplication{
    public static MainActivity instance;
    public static int screenHeight;
    public static int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        instance=this;
        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        screenHeight=size.y;
        screenWidth=size.x;
        AndroidApplicationConfiguration cfg=new AndroidApplicationConfiguration();
        Game game=new cn.s3bit.th902.GameMain();
        initialize(game,cfg); //全屏的游戏
        //如果你需要显示安卓原生标题栏，将initialize那行删去后，改为
        //setContentView(initializeForView(game, cfg));
    }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
