package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.TaiHunDanmaku.baseObjects.bigFace.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.baseObjects.dropItems.*;
import com.meng.TaiHunDanmaku.baseObjects.effects.Effect;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;

import java.util.*;


public class LayoutManager {

    public ArrayList<partAgent> nextPart = new ArrayList<partAgent>();
    public Image bossLifeImage;
    public float bossMaxHp = 1;

    public LayoutManager() {
        Pixmap bossLife = new Pixmap(386, 5, Pixmap.Format.RGBA8888);
        bossLife.setColor(Color.WHITE);
        bossLife.fill();
        bossLifeImage = new Image(new Texture(bossLife));
        bossLifeImage.setPosition(0, 450);
    }

    public void update() {
        BulletShooter.updateAll();
        DropItem.updateAll();
        BaseMyBullet.updateAll();
        if (FightScreen.instence.onSpellCard) {
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(GameMain.bitmapFont, BaseBossPlane.instence.spellCard.spellName);
            FightScreen.spellHeight += 3;
            if (FightScreen.spellHeight > 450) {
                FightScreen.spellHeight = 450;
            }
            GameMain.bitmapFont.draw(GameMain.spriteBatch, glyphLayout, GameMain.width - glyphLayout.width, FightScreen.spellHeight);
        }
        BaseEnemyBullet.updateAll();
        Effect.updateAll();
        BulletRemover.updateAll();
        BaseBigFace.updateAll();
        BaseMyPlane.instance.update();
        if (BaseBossPlane.instence != null && BaseBossPlane.instence.hp > 0) {
            bossLifeImage.setWidth(BaseBossPlane.instence.hp / bossMaxHp * 386);
            bossLifeImage.draw(GameMain.spriteBatch, 1);
            for (partAgent p : nextPart) {
                p.update();
            }
        }
    }
}
