package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.TaiHunDanmaku.bigFace.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.effects.Effect;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import java.util.*;

public class LayoutManager{
	
	public ArrayList<partAgent> nextPart=new ArrayList<partAgent>();
	public Image bossLifeImage;
	
	public LayoutManager(){
		Pixmap bossLife=new Pixmap(386,5,Pixmap.Format.RGBA8888);
		bossLife.setColor(Color.WHITE);
		bossLife.fill();
		bossLifeImage=new Image(new Texture(bossLife));
		bossLifeImage.setPosition(0,450);
	  }
	  
	public void update(){
		BulletShooter.updateAll();
        DropItem.updateAll();
        BaseMyBullet.updateAll();
		GameMain.spriteBatch.end();
		GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		GameMain.spriteBatch.begin();
        BaseEnemyBullet.updateAll();
		GameMain.spriteBatch.end();
		GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		GameMain.spriteBatch.begin();
        Effect.updateAll();
        BulletRemover.updateAll();
        BaseBigFace.updateAll();
        BaseMyPlane.instance.update();
		if(BaseBossPlane.instence!=null&&BaseBossPlane.instence.hp>0){
			bossLifeImage.setWidth(BaseBossPlane.instence.hp/MainScreen.instence.bossMaxHp*386);	
			bossLifeImage.draw(GameMain.spriteBatch,1);
			for(partAgent p:nextPart){
				p.update();
			  }
		  }
	  }
  }
