package com.meng.stg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.meng.stg.GameTextureManager;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.bullets.BulletShooter;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.helpers.Data;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.planes.myPlane.MyPlaneReimu;
import com.meng.stg.planes.PlayerInputProcessor;
import com.meng.stg.stage.stage1;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.helpers.*;

public class MainScreen extends ScreenAdapter{
    public static int playerFlag;//角色
    public static int stageFlag;
    public static int gameTime=0;
    public static int Width, Height;
    public static Stage Stage;
    public static Group MainGroup;
    public static Rectangle FightArea;
    public static InputMultiplexer InputMgr;
    public static BaseEnemyPlane[] enemys=new BaseEnemyPlane[32];
    public static BitmapFont mainBitmapFont;
	Button imb;
    public static boolean onBoss=false;
	FitViewport sv;
    // enemyPlane e;

    @Override
    public void show(){
        Width=540;//386;
        Height=720;//450;
		imb=new Button(GameTextureManager.Textures.get("reimu2"));
		imb.setPosition(100,100);
		imb.setSize(100,100);
		imb.addListener(new ClickListener(){
			  @Override
			  public void clicked(InputEvent event, float x, float y) {
				
				  BaseMyPlane.Instance.onBomb=true;
				  super.clicked(event,x,y);
				}
			});
		sv=new FitViewport(Width,Height);
        Stage=new Stage(sv,GameMain.SBatch);
        Pixmap pixmap=new Pixmap(1,1,Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        mainBitmapFont=new BitmapFont(Gdx.files.internal("font/fo.fnt"));// new BitmapFont(Gdx.files.internal("font/bitmapfont.fnt"));
        mainBitmapFont.setColor(Color.WHITE);
        Image background=new Image(new Texture(pixmap));

        background.setBounds(0,0,Width,Height);
        Stage.addActor(background);
        MainGroup=new Group();
        Stage.addActor(MainGroup);
		MainGroup.addActor(imb);
		
        FightArea=new Rectangle(0,0,Width,Height);

        playerFlag=Data.playerFlagReimu;
        stageFlag=Data.stageFlagStage1;
        switch(playerFlag){
            case Data.playerFlagReimu:
                new MyPlaneReimu().Init();
                break;
            case Data.playerFlagAlice:
                //     new MyPlaneAlice().Init();
                break;
        }
        // e= new enemyPlane().createEnemy();
        InputMgr=new InputMultiplexer();
        InputMgr.addProcessor(new PlayerInputProcessor());
		
        Gdx.input.setInputProcessor(InputMgr);
        super.show();
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
		
        Stage.draw();
        BulletShooter.UpdateAll();
        BaseBullet.UpdateAll();
        BaseMyPlane.Instance.Update();
        //    for(enemyPlane e:enemys){
        //        if(e!=null){
        //            if (!e.isKilled()){
        //                e.Update();
        //            }else {
        //                e=null;
        //            }
        //        }
        //   }

        GameMain.SBatch.begin();
        mainBitmapFont.draw(GameMain.SBatch,"FPS:"+Gdx.graphics.getFramesPerSecond()
                        //        + "\nPlayerLocation:" + (int) playerBaseEntity.x + "," + (int) playerBaseEntity.y
                        //        + "\nmissTime:" + playerBaseEntity.missTime
                        //        + "\nbullets:" + bullet.bulletCount
                        //        + "\ngameTime:" + gameTime
                        //        + "\nonBoss:" + onBoss
                        //        + "\ntouch:" + Player.PlayerInputProcessor.touchX + "," + Player.PlayerInputProcessor.touchY
                        +isKilled()
                //+ "\nisKilled:" + enemys[0].isKilled()
                // + "\nisKilled2:" + enemys[1].isKilled()
                ,10,710);
        //绘制
        mainBitmapFont.draw(GameMain.SBatch,"B",10,30);
        switch(stageFlag){
            case Data.stageFlagStage1:
                if(gameTime>700){
            //        f.draw(GameMain.SBatch,"Stage Clear!!",120,350);
                }
                break;
        }
        //     if(gameOver){
        // InputMgr.clear();
        //       f.draw(GameMain.SBatch, "满身疮痍", 204, 350);
        //  Player.PlayerInputProcessor.touchX=0;
        //  Player.PlayerInputProcessor.touchY=0;
        //   onBoss=true;
        //  }
        GameMain.SBatch.end();
        //  if(!gameOver) {
        if(!onBoss){
            gameTime++;
            switch(stageFlag){
                case Data.stageFlagStage1:
                  stage1.addEnemy();
                    break;
            }
            //       }
        }
        super.render(delta);
    }

    private String isKilled(){
        String s="";
        for(int i=0;i<32;i++){
            if(!(enemys[i]==null)){
                //  s += "\nenemy" + i;// + "Killed:" + enemys[i].isKilled();
                s+="\nHp:"+enemys[i].getHp();
                //   s += "\nlocation:" + enemys[i].getLocation().x + "," + enemys[i].getLocation().y;
            }
        }
        return s;
	  }
	
    @Override
    public void hide(){
        super.hide();
    }

}
