package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.graphics.GL20;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.Laser;

import java.util.HashSet;

public class LaserManager {
    public HashSet<Laser> lasers=new HashSet<Laser>();

    public LaserManager() {

    }
    public void addLaser(Laser l){
        lasers.add(l);
    }
    public void draw(){
        GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        GameMain.spriteBatch.begin();
        for (Laser b : lasers) {
            b.render();
        }
        GameMain.spriteBatch.end();
        GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }
    public void clear(){
        lasers.clear();
    }
}
