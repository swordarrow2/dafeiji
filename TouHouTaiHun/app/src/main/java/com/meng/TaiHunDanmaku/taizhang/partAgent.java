package com.meng.TaiHunDanmaku.taizhang;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.*;
import com.meng.TaiHunDanmaku.ui.*;

public class partAgent {
    private float flagHp = 1;
    private Image ima;

    public partAgent(float flagHp, Color c) {
        this.flagHp = flagHp;
        Pixmap p = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
        p.setColor(c);
        p.fill();
        ima = new Image(new Texture(p));
    }

    public partAgent(float flagHp) {
        this.flagHp = flagHp;
        Pixmap p = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
        p.setColor(Color.PURPLE);
        p.fill();
        ima = new Image(new Texture(p));
    }

    public void update() {
        if (BaseBossPlane.instence.hp > flagHp) {
            ima.setPosition(0, 450);
            ima.setWidth(flagHp / FightScreen.instence.layoutManager.bossMaxHp * 386);
            ima.draw(GameMain.spriteBatch, 1);
        } else {
            ima.setPosition(0, 450);
            ima.setWidth(BaseBossPlane.instence.hp / FightScreen.instence.layoutManager.bossMaxHp * 386);
            ima.draw(GameMain.spriteBatch, 1);
        }
    }

}
