package com.meng.stg.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.stg.bullets.*;
import com.meng.stg.helpers.*;
import com.meng.stg.planes.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.planes.myPlane.*;
import com.meng.stg.stage.*;
/*
 main layout
 */
public class MainScreen extends ScreenAdapter{
    public static int playerFlag;//角色
    public static int stageFlag;
    public static int gameTime=0;
    public static int width, height;
    public static Stage stage;
    public static Group mainGroup;
    public static Rectangle fightArea;
    public static InputMultiplexer inputManager;
    public static BaseEnemyPlane[] enemys=new BaseEnemyPlane[32];
    public static BitmapFont mainBitmapFont;
    public static boolean onBoss=false;
	public FitViewport sv;
    // enemyPlane e;

    @Override
    public void show(){
        width=386;//540;//386;
        height=600;//720;//450;
		sv=new FitViewport(width,height);
        stage=new Stage(sv,GameMain.spriteBatch);
        Pixmap pixmap=new Pixmap(1,1,Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        mainBitmapFont=new BitmapFont(Gdx.files.internal("font/fo.fnt"));// new BitmapFont(Gdx.files.internal("font/bitmapfont.fnt"));
        mainBitmapFont.setColor(Color.GREEN);

        Image background=new Image(new Texture(pixmap));

        background.setBounds(0,0,386,450);
        stage.addActor(background);
        mainGroup=new Group();
        stage.addActor(mainGroup);
        fightArea=new Rectangle(0,0,386,450);

        playerFlag=Data.playerFlagReimu;
        stageFlag=Data.stageFlagStage1;
        switch(playerFlag){
            case Data.playerFlagReimu:
			  new MyPlaneReimu().Init();
			  break;
            case Data.playerFlagAlice:
			  //     new MyPlaneAlice().init();
			  break;
		  }
        // e= new enemyPlane().createEnemy();
        inputManager=new InputMultiplexer();
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
        super.show();
	  }

	@Override
	public void resize(int width,int height){
		super.resize(width,height);
		sv.update(width,height);
	  }

    @Override
    public void render(float delta){
        for(int i=0;i<32;i++){
            if(!(enemys[i]==null)){
                if(!(enemys[i].isKilled())){
                    enemys[i].Update();
				  }else{
                    enemys[i]=null;
				  }
			  }
		  }

        stage.draw();
        BulletShooter.updateAll();
        BaseBullet.updateAll();
        BaseMyPlane.instance.update();
        GameMain.spriteBatch.begin();
        mainBitmapFont.draw(GameMain.spriteBatch,"FPS:"+Gdx.graphics.getFramesPerSecond()
							//	+"\ntouch:"+PlayerInputProcessor.touchX+","+PlayerInputProcessor.touchY+"\n"
							+isKilled()
							,10,590);
        switch(stageFlag){
            case Data.stageFlagStage1:
			  if(gameTime==700){
				  GlyphLayout glyphLayout=new GlyphLayout();
				  glyphLayout.setText(mainBitmapFont,"stage Clear!!");
				  mainBitmapFont.draw(GameMain.spriteBatch,glyphLayout,(width-glyphLayout.width)/2,height/2);
				  //      mainBitmapFont.draw(GameMain.spriteBatch,"stage Clear!!",height/2,width/2);
                }
			  break;
		  }
        GameMain.spriteBatch.end();
        if(!onBoss){
            gameTime++;
            switch(stageFlag){
                case Data.stageFlagStage1:
				  stage1.addEnemy();
				  break;
			  }
		  }
        super.render(delta);
	  }

    private String isKilled(){
        String s="";
        for(int i=0;i<32;i++){
            if(!(enemys[i]==null)){
                s+="\nHp:"+enemys[i].getHp();
			  }
		  }
        return s;
	  }

    @Override
    public void hide(){
        super.hide();
	  }

  }
