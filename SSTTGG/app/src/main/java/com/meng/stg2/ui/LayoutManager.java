package com.meng.stg2.ui;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.stg2.bigFace.*;
import com.meng.stg2.boss.*;
import com.meng.stg2.bullets.*;
import com.meng.stg2.bullets.enemy.*;
import com.meng.stg2.characters.player.*;
import com.meng.stg2.dropItems.*;
import com.meng.stg2.effects.*;
import java.util.*;

public class LayoutManager {

	public ArrayList<partAgent> nextPart=new ArrayList<partAgent>();
	public Image bossLifeImage;

	public LayoutManager() {
		Pixmap bossLife=new Pixmap(386, 5, Pixmap.Format.RGBA8888);
		bossLife.setColor(Color.WHITE);
		bossLife.fill();
		bossLifeImage = new Image(new Texture(bossLife));
		bossLifeImage.setPosition(0, 450);
		
	}

	public void update() {
		Danmaku.updateAll();
        DropItem.updateAll();
        BaseMyBullet.updateAll();
        BaseEnemyBullet.updateAll();
        Effect.updateAll();
        BulletRemover.updateAll();
        BaseBigFace.updateAll();
        BaseMyPlane.instance.update();

		if (BaseBossPlane.instence != null && BaseBossPlane.instence.hp > 0) {
			bossLifeImage.setWidth(BaseBossPlane.instence.hp / MainScreen.instence.bossMaxHp * 386);	
			MainScreen.instence.toNormal.draw(GameMain.spriteBatch, 1);
			bossLifeImage.draw(GameMain.spriteBatch, 1);
			for (partAgent p:nextPart) {
				p.update();
			}
		}
	}
}
