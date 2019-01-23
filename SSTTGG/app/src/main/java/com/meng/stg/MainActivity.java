package com.meng.stg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

public class MainActivity extends AndroidApplication {
	public static MainActivity Instance;
	public static int screenHeight;
	public static int screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Instance = this;
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenHeight=size.y;
		screenWidth=size.x;
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		Game game = new Main();
		initialize(game, cfg); //全屏的游戏
		//如果你需要显示安卓原生标题栏，将initialize那行删去后，改为
		//setContentView(initializeForView(game, cfg));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		System.exit(0);super.onPause();
	}
}
