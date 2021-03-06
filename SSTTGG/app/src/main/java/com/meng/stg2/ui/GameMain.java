package com.meng.stg2.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.meng.stg2.helpers.ResourcesManager;

public class GameMain extends Game {
	public static SpriteBatch spriteBatch;

	@Override
	public void create() {
		spriteBatch= new SpriteBatch();
		ResourcesManager.Load();
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
