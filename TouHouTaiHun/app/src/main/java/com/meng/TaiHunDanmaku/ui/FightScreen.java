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
import com.meng.TaiHunDanmaku.taizhang.*;

import java.util.*;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;

public class FightScreen extends ScreenAdapter {
    public GameMain gameMain;
    public static int playerFlag;
    public static int difficultFlag;
    public static int stageFlag;
    public static int enemyTimeFlag = 0;
    public static int gameTimeFlag = 0;
    public static int width, height;
    public static Stage stage;
    public static Group groupNormal;
    public static Group groupHighLight;
    public static HashSet<Laser> lasers;
    public static HashSet<ReflexAndThrough> reflexAndThroughs;
    public static Rectangle fightArea;
    public InputMultiplexer inputManager;
    public static BaseEnemyPlane[] enemys;
    public static boolean onBoss = false;
    public FitViewport fitViewport;
    public static FightScreen instence;
    public static int sleep = 0;
    public static boolean onSpellCard = false;
    static int spellHeight = 450;
    public float bossMaxHp = 1;
    public static String pl;
    private Float[] data;

    private Actor changeBlend1 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            GameMain.spriteBatch.end();
            GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            GameMain.spriteBatch.begin();
        }
    };

    private Actor changeBlend2 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            GameMain.spriteBatch.end();
            GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            GameMain.spriteBatch.begin();
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
        ++gameTimeFlag;
        if (ReplayManager.onReplay) {
            data = ReplayManager.getData(gameTimeFlag);
            BaseMyPlane.instance.objectCenter.x = data[0];
            BaseMyPlane.instance.objectCenter.y = data[1];
            BaseMyPlane.instance.slow = data[2] == 1f;
            BaseMyPlane.instance.onBomb = data[3] == 1f;
        } else {
            ReplayManager.appendData(
                    BaseMyPlane.instance.objectCenter.x +
                            " " + BaseMyPlane.instance.objectCenter.y +
                            " " + (BaseMyPlane.instance.slow ? 1 : 0) +
                            " " + (BaseMyPlane.instance.onBomb ? 1 : 0) +
                            "\n");
        }
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
		/*	ShapeRenderer shapeRenderer = new ShapeRenderer();
		 shapeRenderer.setAutoShapeType(true);
		 shapeRenderer.begin();
		 shapeRenderer.rectLine(10, 10, 300, 400, 80);
		 shapeRenderer.end();*/
        for (ReflexAndThrough reflexAndThrough : reflexAndThroughs) {
            reflexAndThrough.update();
        }

        GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        GameMain.spriteBatch.begin();
        for (Laser b : lasers) {
            b.render();
        }
        GameMain.spriteBatch.end();
        GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        GameMain.spriteBatch.begin();
        layoutManager.update();
        if (onSpellCard) {
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(bitmapFont, BaseBossPlane.instence.spellCard.spellName);
            spellHeight += 3;
            if (spellHeight > 450) {
                spellHeight = 450;
            }
            bitmapFont.draw(GameMain.spriteBatch, glyphLayout, width - glyphLayout.width, spellHeight);
        }
        bitmapFont.draw(GameMain.spriteBatch, "FPS:" + Gdx.graphics.getFramesPerSecond() + "\n" +
                        "\npos:" + BaseMyPlane.instance.objectCenter.x + " " + BaseMyPlane.instance.objectCenter.y + "\n" +
                        "MaxPoint:" + BaseMyPlane.instance.maxPoint
                        + "\nmiss:" + BaseMyPlane.instance.miss + "\n"
                        + "\nbullet:" + BaseEnemyBullet.instances.size() + "\n"
                        + "\nmemory:" + (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024))
                        + isKilled()
                , 10, 590);
        switch (stageFlag) {
            case Data.stageFlagStage1:
                if (enemyTimeFlag > 100) {
                    GlyphLayout glyphLayout = new GlyphLayout();
                    glyphLayout.setText(bitmapFont, "stage Clear!!");
                    bitmapFont.draw(GameMain.spriteBatch, glyphLayout, (width - glyphLayout.width) / 2, height / 2);
                }
                if (enemyTimeFlag > 300) {
                    ReplayManager.saveRepaly();
                    gameMain.setSelectDiffScreen();
                }
                break;
        }
        GameMain.spriteBatch.end();
        if (!onBoss) {
            enemyTimeFlag++;
            switch (stageFlag) {
                case Data.stageFlagStage1:
                    stage1.addEnemy();
                    break;
            }
        }

        super.render(delta);
    }


    private String isKilled() {
        String s = "";
        for (int i = 0; i < 32; i++) {
            if (enemys[i] != null) {
                s += "\nHp:" + enemys[i].getHp();
            }
        }
        return s;
    }

    private void init() {
        instence = this;
        BaseEnemyBullet.instances.clear();
        BaseEnemyBullet.toAdd.clear();
        BaseEnemyBullet.toDelete.clear();
        BaseMyBullet.instances.clear();
        BaseMyBullet.toAdd.clear();
        BaseMyBullet.toDelete.clear();
        lasers = new HashSet<Laser>();
        reflexAndThroughs = new HashSet<ReflexAndThrough>();
        layoutManager = new LayoutManager();
        enemys = new BaseEnemyPlane[32];
        width = 386;//540;//386;
        height = 600;//720;//450;
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, GameMain.spriteBatch);
        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Image background = new Image(new Texture(pixmap));
        long seed = System.currentTimeMillis();
        ObjectPools.randomPool = new RandomXS128(seed);
        ReplayManager.appendData(pl + " " + difficultFlag + " " + playerFlag + " " + stageFlag + " " + seed + "\n");
        background.setBounds(0, 0, 386, 450);
        stage.addActor(background);
        groupNormal = new Group();
        groupHighLight = new Group();
        stage.addActor(groupNormal);
        stage.addActor(changeBlend1);
        stage.addActor(groupHighLight);
        stage.addActor(changeBlend2);
        fightArea = new Rectangle(0, 0, 386, 450);
        playerFlag = Data.playerFlagReimu;
        stageFlag = Data.stageFlagStage1;
        switch (playerFlag) {
            case Data.playerFlagReimu:
                new MyPlaneReimu().init();
                break;
            case Data.playerFlagAlice:
                //     new MyPlaneAlice().init();
                break;
        }
        inputManager = new InputMultiplexer();
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

    public static void normalMode() {
        if (!onSpellCard) return;
        onSpellCard = false;
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
    }

    public static void spellMode() {
        if (onSpellCard) return;
        onSpellCard = true;
        spellHeight = 200;
        new BigFace().init(new Vector2(300, 200), FaceCharacter.Junko);
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithNothing);
        //	FightScreen.sleep=0;
    }

}
