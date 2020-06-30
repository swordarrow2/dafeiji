package com.meng.stg2.boss;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.*;
import com.meng.stg2.ui.*;

public class partAgent{
	private float hp=1;
	private float positionX=1;
	private Image ima;

	public partAgent(float hp,Color c){
		this.hp=hp;
		Pixmap p=new Pixmap(5,5,Pixmap.Format.RGBA8888);
		p.setColor(c);
		p.fill();
	  	ima=new Image(new Texture(p));
		positionX=hp/MainScreen.instence.bossMaxHp*386;
	  }

	public partAgent(float hp){
		this.hp=hp;
		Pixmap p=new Pixmap(5,5,Pixmap.Format.RGBA8888);
		p.setColor(Color.PURPLE);
		p.fill();
	  	ima=new Image(new Texture(p));
		positionX=hp/MainScreen.instence.bossMaxHp*386;
	  }
	  
	public void update(){
		if(BaseBossPlane.instence.hp>hp){	
		//	ima.setPosition(positionX,450);
		//	ima.draw(GameMain.spriteBatch,1);
			ima.setPosition(0,450);
			ima.setWidth(hp/MainScreen.instence.bossMaxHp*386);
			ima.draw(GameMain.spriteBatch,1); 
		  }else{
			ima.setPosition(0,450);
			ima.setWidth(BaseBossPlane.instence.hp/MainScreen.instence.bossMaxHp*386);	  
			ima.draw(GameMain.spriteBatch,1); 
		  }	
	  }

  }
