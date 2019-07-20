package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.graphics.GL20;
import com.meng.TaiHunDanmaku.bullets.enemy.Laser;

import java.util.HashSet;

public class LaserManager {
    public HashSet<Laser> lasers = new HashSet<Laser>();
    public GameMain gameMain;

    public LaserManager(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    public void addLaser(Laser l) {
        lasers.add(l);
    }

    public void draw() {
        gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        gameMain.spriteBatch.begin();
        for (Laser b : lasers) {
            b.render(gameMain);
        }
        gameMain.spriteBatch.end();
        gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void clear() {
        lasers.clear();
    }
}
