package com.meng.stg;

import android.view.WindowManager;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.meng.stg.helpers.Resources;

public class Main extends Game {
	public static SpriteBatch SBatch;

	@Override
	public void create() {
		SBatch = new SpriteBatch();
		Resources.Load();
		setScreen(new MainScreen());
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//将屏幕清空为黑色，随后绘制当前活跃Screen上的内容。
		//颜色可以修改，glClearColor前三个参数分别为红绿蓝，为0~1之间的float型
		super.render();
	}


}
