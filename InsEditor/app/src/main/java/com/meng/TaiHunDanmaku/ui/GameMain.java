package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.meng.TaiHunDanmaku.helpers.*;

public class GameMain extends Game {
    public AndroidApplication androidApplication;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;
    
    public int width = 386;//540;//386;
    public int height = 600;

	public int miss;//720;//450;

    public GameMain(AndroidApplication androidApplication) {
        this.androidApplication = androidApplication;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        ResourcesManager.Load();
        bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        bitmapFont.setColor(Color.RED);
        setScreen(new FightScreen(this));
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
