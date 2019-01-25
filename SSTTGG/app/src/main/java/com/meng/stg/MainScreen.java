package com.meng.stg;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.bullets.bullet;
import com.meng.stg.enemy.BaseEnemyPlane;
import com.meng.stg.helpers.Data;
import com.meng.stg.player.BaseMyPlane;
import com.meng.stg.player.MyPlaneReimu;
import com.meng.stg.player.PlayerInputProcessor;
import com.meng.stg.stage.stage1;

public class MainScreen extends ScreenAdapter {
    public static int playerFlag;//角色
    public static int stageFlag;
    public static int gameTime = 0;
    public static int Width, Height;
    public static Stage Stage;
    public static Group MainGroup;
    public static Rectangle FightArea;
    public static InputMultiplexer InputMgr;
    public static BaseEnemyPlane[] enemys = new BaseEnemyPlane[32];
    public static bullet[] bullets = new bullet[1024];
  public static  BitmapFont f;
    public static boolean onBoss = false;
    // EnemyPlane e;

    @Override
    public void show() {
        Stage = new Stage(new ScalingViewport(Scaling.fit, 540f, 720f), GameMain.SBatch);
        Width = 540;
        Height = 720;
        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        f = new BitmapFont(Gdx.files.internal("font/fo.fnt"));// new BitmapFont(Gdx.files.internal("font/bitmapfont.fnt"));
        f.setColor(Color.WHITE);
        Image bg = new Image(new Texture(pixmap));

        bg.setBounds(0, 0, 540, 720);
        Stage.addActor(bg);
        MainGroup = new Group();
        Stage.addActor(MainGroup);
        FightArea = new Rectangle(0, 0, 540, 720);

        playerFlag=Data.playerFlagReimu;
        stageFlag=Data.stageFlagStage1;
        switch (playerFlag) {
            case Data.playerFlagReimu:
                new MyPlaneReimu().Init();
                break;
            case Data.playerFlagAlice:
           //     new MyPlaneAlice().Init();
                break;
        }
        // e= new EnemyPlane().createEnemy();
        InputMgr = new InputMultiplexer();
        InputMgr.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(InputMgr);
        super.show();
    }

    @Override
    public void render(float delta) {
        for (int i = 0; i < 32; i++) {
            if (!(enemys[i] == null)) {
                if (!(enemys[i].isKilled())) {
                    enemys[i].Update();
                } else {
                    enemys[i] = null;
                }
            }
        }
        Stage.draw();
        BaseBullet.UpdateAll();
        BaseMyPlane.Instance.Update();
        //    for(EnemyPlane e:enemys){
        //        if(e!=null){
        //            if (!e.isKilled()){
        //                e.Update();
        //            }else {
        //                e=null;
        //            }
        //        }
        //   }

        GameMain.SBatch.begin();
        f.draw(GameMain.SBatch, "FPS:" + Gdx.graphics.getFramesPerSecond()
                //        + "\nPlayerLocation:" + (int) playerBaseEntity.x + "," + (int) playerBaseEntity.y
                //        + "\nmissTime:" + playerBaseEntity.missTime
                //        + "\nbullets:" + bullet.bulletCount
                //        + "\ngameTime:" + gameTime
                //        + "\nonBoss:" + onBoss
                //        + "\ntouch:" + Player.PlayerInputProcessor.touchX + "," + Player.PlayerInputProcessor.touchY
                        + isKilled()
                //+ "\nisKilled:" + enemys[0].isKilled()
                // + "\nisKilled2:" + enemys[1].isKilled()
                , 10, 710);
        //绘制
        f.draw(GameMain.SBatch, "Bomb", 10, 30);
        switch (stageFlag) {
            case Data.stageFlagStage1:
                if (gameTime > 700) {
                    f.draw(GameMain.SBatch, "Stage Clear!!", 120, 350);
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
            if (!onBoss) {
                gameTime++;
                switch (stageFlag) {
                    case Data.stageFlagStage1:
                        stage1.addEnemy();
                        break;
                }
     //       }
        }
        super.render(delta);
    }

    private String isKilled() {
        String s = "";
        for (int i = 0; i < 32; i++) {
            if (!(enemys[i] == null)) {
                //  s += "\nenemy" + i;// + "Killed:" + enemys[i].isKilled();
                s += "\nHp:" + enemys[i].getHp();
             //   s += "\nlocation:" + enemys[i].getLocation().x + "," + enemys[i].getLocation().y;
            }
        }
        return s;
    }

    @Override
    public void hide() {
        super.hide();
    }

}
