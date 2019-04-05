package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.meng.TaiHunDanmaku.helpers.*;

public class GameMain extends Game {
    public static SpriteBatch spriteBatch;
    public static BitmapFont bitmapFont;
    public String replayFileName="replay/myfilerep115235.txt";
    public boolean onReplay=true;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        ResourcesManager.Load();
        bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        bitmapFont.setColor(Color.GREEN);
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
	  FightScreen.gameTime=0;
	  FightScreen.enemyFlag=0;
	  FightScreen.onBoss=false;
        setScreen(new FightScreen(this));
    }

    public void setSelectCharScreen() {
        setScreen(new SelectCharScreen(this));
    }

    public void setSelectDiffScreen() {
        setScreen(new SelectDiffScreen(this));
    }

}
