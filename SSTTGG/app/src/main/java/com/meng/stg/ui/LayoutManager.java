package com.meng.stg.ui;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.meng.stg.bigFace.*;
import com.meng.stg.boss.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.dropItems.*;
import com.meng.stg.effects.Effect;
import com.meng.stg.planes.myPlane.*;
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
        BaseEnemyBullet.updateAll();
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
