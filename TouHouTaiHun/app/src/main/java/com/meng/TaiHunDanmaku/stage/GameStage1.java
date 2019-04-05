package com.meng.TaiHunDanmaku.stage;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.control.ReplayManager;
import com.meng.TaiHunDanmaku.taizhang.body.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;

public class GameStage1 extends GameStage {

    public GameStage1(GameMain gameMain) {
        super(gameMain);
    }

    @Override
    public void addEnemy(int enemyTimeFlag) {
        switch (enemyTimeFlag) {
            case 30:
                new BossTaiZhang3().init(new Vector2(193, 350), 10, 2700, new Task[]{new TaskMoveTo(193, 250)});
                FightScreen.instence.onBoss = true;
                break;
        }
        if (enemyTimeFlag > 100) {
            GameMain.spriteBatch.begin();
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(bitmapFont, "stage Clear!!");
            bitmapFont.draw(GameMain.spriteBatch, glyphLayout, (GameMain.width - glyphLayout.width) / 2, GameMain.height / 2);
            GameMain.spriteBatch.end();
        }
        if (enemyTimeFlag > 300) {
            ReplayManager.saveRepaly();
            gameMain.setSelectDiffScreen();
        }
    }
}
  
