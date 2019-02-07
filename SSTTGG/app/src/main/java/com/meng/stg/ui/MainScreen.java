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
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.helpers.Data;
import com.meng.stg.planes.PlayerInputProcessor;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.planes.enemyPlane.boss.BaseBossPlane;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.planes.myPlane.MyPlaneReimu;
import com.meng.stg.stage.stage1;

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
    public static BitmapFont bitmapFont;
    public static boolean onBoss=false;
    public FitViewport fitViewport;
    // enemyPlane e;
    public static MainScreen instence;
    public static int sleep=0;
    public static boolean onSpellCard=false;

    @Override
    public void show(){
        init();
        super.show();
    }

    @Override
    public void resize(int width,int height){
        super.resize(width,height);
        fitViewport.update(width,height);
    }

    @Override
    public void render(float delta){
        if(sleep>0){
            try{
                Thread.sleep(sleep--);
            }catch(InterruptedException e){
            }
        }
        for(int i=0;i<32;i++){
            if(!(enemys[i]==null)){
                if(!(enemys[i].isKilled)){
                    enemys[i].update();
                }else{
                    enemys[i]=null;
                }
            }
        }

        stage.draw();
        BulletShooter.updateAll();
        com.meng.stg.item.BaseItem.updateAll();
        com.meng.stg.bullets.BaseBullet.updateAll();
        com.meng.stg.effects.BaseEffect.updateAll();
        BaseMyPlane.instance.update();
        GameMain.spriteBatch.begin();
        if(onSpellCard){
            bitmapFont.draw(GameMain.spriteBatch,"spell card attack\n"+BaseBossPlane.instence.spellCard.spellName,10,500);
            GlyphLayout glyphLayout=new GlyphLayout();
            glyphLayout.setText(bitmapFont,"spell card attack\n"+BaseBossPlane.instence.spellCard.spellName);
            bitmapFont.draw(GameMain.spriteBatch,glyphLayout,width-glyphLayout.width,440);
        }

        bitmapFont.draw(GameMain.spriteBatch,//"FPS:"+Gdx.graphics.getFramesPerSecond()
                //	+"\npos:"+BaseMyPlane.instance.objectCenter.x+" "+BaseMyPlane.instance.objectCenter.y+"\n"
                "MaxPoint:"+BaseMyPlane.instance.maxPoint
                        +"\nmiss:"+BaseMyPlane.instance.miss+"\n"
                        //            +"\nmemory:"+(Runtime.getRuntime().totalMemory()*1.0/(1024*1024))
                        +isKilled()
                //	+"\nAcce"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)+"\nCom;"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.Compass)+"\nhard:"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.HardwareKeyboard)+"\nmul:"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen)+"\non"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.OnscreenKeyboard)+"\nVib"
                //	+Gdx.input.isPeripheralAvailable(Peripheral.Vibrator)

                ,10,590);
        switch(stageFlag){
            case Data.stageFlagStage1:
                if(gameTime==700){
                    GlyphLayout glyphLayout=new GlyphLayout();
                    glyphLayout.setText(bitmapFont,"stage Clear!!");
                    bitmapFont.draw(GameMain.spriteBatch,glyphLayout,(width-glyphLayout.width)/2,height/2);
                    //      bitmapFont.draw(GameMain.spriteBatch,"stage Clear!!",height/2,width/2);
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

    private void init(){
        instence=this;
        BaseEnemyBullet.bulletCount=0;
        width=386;//540;//386;
        height=600;//720;//450;
        fitViewport=new FitViewport(width,height);
        stage=new Stage(fitViewport,GameMain.spriteBatch);
        Pixmap pixmap=new Pixmap(1,1,Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        bitmapFont=new BitmapFont(Gdx.files.internal("font/font.fnt"));
        bitmapFont.setColor(Color.GREEN);
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
                new MyPlaneReimu().init();
                break;
            case Data.playerFlagAlice:
                //     new MyPlaneAlice().init();
                break;
        }
        inputManager=new InputMultiplexer();
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
    }

    public void restart(){
        gameTime=0;
        stage.clear();
        mainGroup.clear();
        onBoss=false;
        enemys=new BaseEnemyPlane[32];
        init();
    }

    @Override
    public void hide(){
        super.hide();
    }

    public static void normalMode(){
        if(!onSpellCard) return;
        onSpellCard=false;
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithScorePoint);
    }

    public static void spellMode(){
        if(onSpellCard) return;
        onSpellCard=true;
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithScorePoint);
        //	MainScreen.sleep=0;
    }

}
