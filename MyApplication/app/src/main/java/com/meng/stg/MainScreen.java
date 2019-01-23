package com.meng.stg;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.stg.entities.*;
import com.meng.stg.entities.enemyPlane.*;
import com.meng.stg.entities.myPlane.*;
import com.meng.stg.entities.bullet.*;

public class MainScreen extends ScreenAdapter {
	public static int Width, Height;
	public static Stage Stage;
	public static Group MainGroup;
	public static Rectangle FightArea;
	public static InputMultiplexer InputMgr;
	public static BitmapFont f;
	basePlayer bp=null;
	
	
	@Override
	public void show() {
		Width = Gdx.graphics.getWidth()/2;
		Height = Gdx.graphics.getHeight()/2;
	  
		Stage = new Stage(new ScalingViewport(Scaling.fit, Width, Height), Main.SBatch);
		
		f=new BitmapFont(Gdx.files.internal("font/fo.fnt"));
        f.setColor(Color.WHITE);
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fill();
		Image bg = new Image(new Texture(pixmap));
		bg.setBounds(0, 0, Width, Height);
		Stage.addActor(bg);
		
		MainGroup = new Group();
		Stage.addActor(MainGroup);
		
		FightArea = new Rectangle(0, 0, Width, Height);

		bp= new PlayerAlice();
		bp.Init();
		EnemyAndroid.Pool.obtain().Init(new Vector2(Width/2,Height/2));
		InputMgr = new InputMultiplexer();
		InputMgr.addProcessor(new basePlayer.PlayerInputProcessor());
		Gdx.input.setInputProcessor(InputMgr);
		
		super.show();
	}
	
	@Override
	public void render(float delta) {
		baseEnemyObject.UpdateAll();
		basePlayer.Instance.Update();
		Stage.draw();
		Main.SBatch.begin();
		f.draw(Main.SBatch, "FPS:" + Gdx.graphics.getFramesPerSecond()+
			   "\ncount:"+SimpleRedBullet.bulletCount//+
			//   "\ntime:"+enemy.cardTime
			   , 10, 210);

        //   f.draw(Main.SBatch, "Integer:" + enemy.i, 10, 50);
        Main.SBatch.end();
		
		super.render(delta);
	}
	
	@Override
	public void hide() {
		super.hide();
	}
}
