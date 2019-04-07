package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.baseObjects.bigFace.item.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.control.PlayerInputProcessor;
import com.meng.TaiHunDanmaku.control.ReplayManager;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.*;
import com.meng.TaiHunDanmaku.stage.*;

import java.util.*;

public class FightScreen extends ScreenAdapter {
    public static FightScreen instence;
    public GameMain gameMain;
    public int enemyTimeFlag = 0;
    public int gameTimeFlag = 0;
    public Stage stage;
    public Group groupNormal;
    public Group groupHighLight;
    public HashSet<ReflexAndThrough> reflexAndThroughs;
    public BaseEnemyPlane[] enemys;
    public boolean onBoss = false;
    public int sleep = 0;
    public boolean onSpellCard = false;
    static int spellHeight = 450;
    private FitViewport fitViewport;
    private GameStage gameStage;
    public LaserManager laserManager;
    private Image spellBackground;
    private Image normalBackground;
    private Actor changeBlend1 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            gameMain.spriteBatch.end();
            gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            gameMain.spriteBatch.begin();
        }
    };

    public int nowFps = 0;
    public int replayFPS = 0;
    private Actor changeBlend2 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            gameMain.spriteBatch.end();
            gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            gameMain.spriteBatch.begin();
        }
    };


    public LayoutManager layoutManager;

    @Override
    public void show() {
        init();
        super.show();
    }

    public FightScreen(GameMain game) {
        gameMain = game;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        nowFps = Gdx.graphics.getFramesPerSecond();
        ReplayManager.update(gameTimeFlag);
        ++gameTimeFlag;
        if (sleep > 0) {
            try {
                Thread.sleep(sleep--);
            } catch (InterruptedException e) {
            }
        }

        for (int i = 0; i < 32; i++) {
            if (enemys[i] != null) {
                if ((enemys[i].isKilled)) {
                    enemys[i] = null;
                } else {
                    enemys[i].update();
                }
            }
        }
        stage.draw();
        gameMain.spriteBatch.begin();
        layoutManager.update();
     /*      gameMain.bitmapFont.draw(gameMain.spriteBatch, "FPS:" + nowFps + "\n" +
                    (ReplayManager.onReplay ? "replay FPS:" + replayFPS + "\n" : "") +
         "pos:"+BaseMyPlane.instance.objectCenter.x+" "+BaseMyPlane.instance.objectCenter.y+"\n"+
                "MaxPoint:" + gameMain.maxPoint
                   + "\nmiss:" + gameMain.miss + "\n"
           + "\nbullet:" + BaseEnemyBullet.instances.size() + "\n"
               + "memory:" + (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024))
             + isKilled()
               , 10, 590);*/
        gameMain.bitmapFont.draw(gameMain.spriteBatch,
                "FPS:" + nowFps +
                        (ReplayManager.onReplay ? "\nreplay FPS:" + replayFPS : "") +
                        "\nmiss:" + gameMain.miss
                , 10, 590);
        gameMain.bitmapFont.draw(gameMain.spriteBatch,
                "Player:未实装\nBomb:未实装\nGraze:未实装\n最大得点:未实装\nScore:未实装\nHiScore:未实装", 190, 590);
        gameMain.spriteBatch.end();
        for (ReflexAndThrough reflexAndThrough : reflexAndThroughs) {
            reflexAndThrough.update();
        }
        laserManager.draw();
        if (!onBoss) {
            gameStage.addEnemy(++enemyTimeFlag);
        }
        super.render(delta);
    }

   /* private String isKilled() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (enemys[i] != null) {
                s.append("\nHp:").append(enemys[i].getHp());
            }
        }
        return s.toString();
    }*/

    private void init() {
        instence = this;
        BaseEnemyBullet.instances.clear();
        BaseEnemyBullet.toAdd.clear();
        BaseEnemyBullet.toDelete.clear();
        BaseMyBullet.instances.clear();
        BaseMyBullet.toAdd.clear();
        BaseMyBullet.toDelete.clear();
        reflexAndThroughs = new HashSet<ReflexAndThrough>();
        layoutManager = new LayoutManager(gameMain);
        laserManager = new LaserManager(gameMain);
        enemys = new BaseEnemyPlane[32];
        switch (gameMain.stageFlag) {
            case "Stage1":
                gameStage = new GameStage1(gameMain);
                break;
        }

        fitViewport = new FitViewport(gameMain.width, gameMain.height);
        stage = new Stage(fitViewport, gameMain.spriteBatch);

        spellBackground = new Image(ResourcesManager.textures.get("spellbackground"));
        spellBackground.setBounds(0, 0, 0, 0);

        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        normalBackground = new Image(new Texture(pixmap));
        normalBackground.setBounds(0, 0, 386, 450);

        Pixmap pixmap2 = new Pixmap(1, 1, Format.RGBA8888);
        pixmap2.setColor(Color.GREEN);
        pixmap2.fill();
        Image UI = new Image(new Texture(pixmap2));
        UI.setBounds(0, 450, 386, 150);

        stage.addActor(spellBackground);
        stage.addActor(normalBackground);
        spellBackground.setZIndex(Data.zIndexBackground);
        normalBackground.setZIndex(Data.zIndexBackground);
        groupNormal = new Group();
        groupHighLight = new Group();
        stage.addActor(groupNormal);
        stage.addActor(changeBlend1);
        stage.addActor(groupHighLight);
        stage.addActor(changeBlend2);
        stage.addActor(UI);
        switch (gameMain.charaFlag) {
            case "Reimu":
                new MyPlaneReimu().init(gameMain);
                break;
            case "Alice":
                //     new MyPlaneAlice().init();
                break;
        }
        InputMultiplexer inputManager = new InputMultiplexer();
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
    }

    public void restart() {
        enemyTimeFlag = 0;
        onBoss = false;
        init();
    }

    @Override
    public void hide() {
        super.hide();
    }

    public void normalMode() {
        if (!onSpellCard) return;
        spellBackground.setSize(0, 0);
        normalBackground.setSize(386, 450);
        laserManager.clear();
        onSpellCard = false;
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
    }

    public void spellMode() {
        if (onSpellCard) return;
        spellBackground.setSize(386, 450);
        normalBackground.setSize(0, 0);
        laserManager.clear();
        onSpellCard = true;
        spellHeight = 200;
        new BigFace().init(new Vector2(300, 200), FaceCharacter.Junko);
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
        //	FightScreen.sleep=0;
    }

}
