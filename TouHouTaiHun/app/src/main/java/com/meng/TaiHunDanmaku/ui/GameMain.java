package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.meng.TaiHunDanmaku.helpers.*;

public class GameMain extends Game {
    public static SpriteBatch spriteBatch;
    public static BitmapFont bitmapFont;
    public String replayFileName = "replay/myfilerep115235.txt";
    public boolean onReplay = true;

    public static int width, height;
    public static int playerFlag;
    public static int difficultFlag;
    public static int stageFlag = 1;
    public static String equipment;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        ResourcesManager.Load();
        bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		bitmapFont.setColor(Color.RED);
		width=386;//540;//386;
        height=600;//720;//450;
        setScreen(new SelectDiffScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //将屏幕清空为黑色，随后绘制当前活跃Screen上的内容。
        //颜色可以修改，glClearColor前三个参数分别为红绿蓝，为0~1之间的float型
        super.render();
    }

    public void setFightScreen() {
        setScreen(new FightScreen(this));
    }

    public void setSelectCharScreen() {
        setScreen(new SelectCharScreen(this));
    }

    public void setSelectDiffScreen() {
        setScreen(new SelectDiffScreen(this));
    }

}
