package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.ui.*;

/**
 * Laser Class
 * @author Trenton Shaffer
 */
public class bulletLaser{

	public Vector2 position = new Vector2() ;
	public float distance;
	public Color color = new Color(Color.RED);
	public Color rayColor = new Color(Color.WHITE);
	public float degrees;
	public Sprite begin1,begin2,mid1,mid2,end1,end2;
	public Vector2 p1,p2,p3,p4;
	
	public bulletLaser(Sprite s1,Sprite s2,Sprite m1,Sprite m2,Sprite e1,Sprite e2,Vector2 p1,Vector2 p2,Vector2 p3,Vector2 p4){
		begin1=s1;
		begin2=s2;
		mid1=m1;
		mid2=m2;
		end1=e1;
		end2=e2;
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		this.p4=p4;
	  }

	public void render(){
		begin1.setColor(color);
		begin2.setColor(rayColor);
		mid1.setColor(color);
		mid2.setColor(rayColor);
		end1.setColor(color);
		end2.setColor(rayColor);
		mid1.setSize(mid1.getWidth(),distance);
		mid2.setSize(mid1.getWidth(),distance);
		begin1.setPosition(position.x,position.y);
		begin2.setPosition(position.x,position.y);
		mid1.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight());
		mid2.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight());
		end1.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight()+mid1.getHeight());
		end2.setPosition(begin1.getX(),begin1.getY()+begin1.getHeight()+mid1.getHeight());
		begin1.setOrigin(begin1.getWidth()/2,0);
		begin2.setOrigin(begin1.getWidth()/2,0);
		mid1.setOrigin(mid1.getWidth()/2,-begin1.getHeight());
		mid2.setOrigin(mid2.getWidth()/2,-begin1.getHeight());
		end1.setOrigin(mid1.getWidth()/2,-begin1.getHeight()-mid1.getHeight());
		end2.setOrigin(mid2.getWidth()/2,-begin1.getHeight()-mid2.getHeight());
		begin1.setRotation(degrees);
		begin2.setRotation(degrees);
		mid1.setRotation(degrees);
		mid2.setRotation(degrees);
		end1.setRotation(degrees);
		end2.setRotation(degrees);

		GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE);
		GameMain.spriteBatch.begin();
		begin1.draw(GameMain.spriteBatch);
		begin2.draw(GameMain.spriteBatch);
		mid1.draw(GameMain.spriteBatch);
		mid2.draw(GameMain.spriteBatch);
		end1.draw(GameMain.spriteBatch);
		end2.draw(GameMain.spriteBatch);
		GameMain.spriteBatch.end();
		GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);


	  }
  }
