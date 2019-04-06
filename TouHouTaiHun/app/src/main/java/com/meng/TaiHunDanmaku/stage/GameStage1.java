package com.meng.TaiHunDanmaku.stage;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.control.ReplayManager;
import com.meng.TaiHunDanmaku.taizhang.body.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;


public class GameStage1 extends GameStage {

    public GameStage1(GameMain gameMain) {
        super(gameMain);
    }

    @Override
    public void addEnemy(int enemyTimeFlag) {
        switch (enemyTimeFlag) {
            case 30:
                new BossTaiZhang4().init(new Vector2(193, 350), 10, 2000, new Task[]{new TaskMoveTo(193, 250)});
                FightScreen.instence.onBoss = true;
                break;
        }
        if (enemyTimeFlag > 100) {
            gameMain.spriteBatch.begin();
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(gameMain.bitmapFont, "stage Clear!!");
            gameMain.bitmapFont.draw(gameMain.spriteBatch, glyphLayout, (gameMain.width - glyphLayout.width) / 2, gameMain.height / 2);
            gameMain.spriteBatch.end();
        }
        if (enemyTimeFlag > 300) {
            ReplayManager.saveRepaly();
            gameMain.setMainMenuScreen();
        }
    }
}
  
