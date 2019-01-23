package com.meng.stg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;

import cn.s3bit.th902.GameMain;

public class MainActivity extends AndroidApplication {
	public static MainActivity Instance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Instance = this;
		
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	//	Game game = new Main();
		Game game = new GameMain();
		initialize(game, cfg); //全屏的游戏
		//如果你需要显示安卓原生标题栏，将initialize那行删去后，改为
		//setContentView(initializeForView(game, cfg));
	}
}
