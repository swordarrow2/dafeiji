package com.meng.stg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.bullets.BulletShooter;
import com.meng.stg.helpers.Data;
import com.meng.stg.planes.PlayerInputProcessor;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.planes.myPlane.MyPlaneReimu;
import com.meng.stg.stage.stage1;
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
    // enemyPlane e;

    @Override
    public void show(){
        width=540;//386;
        height=720;//450;
        FitViewport sv=new FitViewport(width,height);
        stage=new Stage(sv,GameMain.spriteBatch);
        Pixmap pixmap=new Pixmap(1,1,Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        mainBitmapFont=new BitmapFont(Gdx.files.internal("font/fo.fnt"));// new BitmapFont(Gdx.files.internal("font/bitmapfont.fnt"));
        mainBitmapFont.setColor(Color.WHITE);
        Image background=new Image(new Texture(pixmap));

        background.setBounds(0,0,width,height);
        stage.addActor(background);
        mainGroup=new Group();
        stage.addActor(mainGroup);
        fightArea=new Rectangle(0,0,width,height);
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
                        +"\ntouch:"+PlayerInputProcessor.touchX+","+PlayerInputProcessor.touchY+"\n"
                        +isKilled()
                ,10,710);
        switch(stageFlag){
            case Data.stageFlagStage1:
                if(gameTime>700){
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