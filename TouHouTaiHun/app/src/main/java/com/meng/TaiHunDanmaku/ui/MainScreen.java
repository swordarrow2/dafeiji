package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.bigFace.item.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.planes.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.stage.*;
import com.meng.TaiHunDanmaku.taizhang.*;

import java.util.HashSet;

public class MainScreen extends ScreenAdapter {
    public static int playerFlag;//角色
    public static int stageFlag;
    public static int gameTime = 0;
    public static int width, height;
    public static Stage stage;
    public static Group groupNormal;
    public static Group groupHighLight;
    public static HashSet<Laser> lasers;
    public static HashSet<ReflexAndThrough> reflexAndThroughs;
    public static Rectangle fightArea;
    public static InputMultiplexer inputManager;
    public static BaseEnemyPlane[] enemys = new BaseEnemyPlane[32];
    public static BitmapFont bitmapFont;
    public static boolean onBoss = false;
    public FitViewport fitViewport;
    public static MainScreen instence;
    public static int sleep = 0;
    public static boolean onSpellCard = false;
    static int spellHeight = 450;
    public float bossMaxHp = 1;

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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width, height);
    }

    @Override
    public void render(float delta) {
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


        //	stage.act();
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
                        //	+"\npos:"+BulletRemover.instance.objectCenter.x+" "+BulletRemover.instance.objectCenter.y+"\n"
                        "MaxPoint:" + BaseMyPlane.instance.maxPoint
                        + "\nmiss:" + BaseMyPlane.instance.miss + "\n"
                        + "\nbullet:" + BaseEnemyBullet.instances.size() + "\n"
                        + "\nmemory:" + (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024))
                        + isKilled()
                , 10, 590);
        switch (stageFlag) {
            case Data.stageFlagStage1:
                if (gameTime == 700) {
                    GlyphLayout glyphLayout = new GlyphLayout();
                    glyphLayout.setText(bitmapFont, "stage Clear!!");
                    bitmapFont.draw(GameMain.spriteBatch, glyphLayout, (width - glyphLayout.width) / 2, height / 2);
                    //      bitmapFont.draw(GameMain.spriteBatch,"stage Clear!!",height/2,width/2);
                }
                break;
        }
        GameMain.spriteBatch.end();
        if (!onBoss) {
            gameTime++;
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
        width = 386;//540;//386;
        height = 600;//720;//450;
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, GameMain.spriteBatch);
        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        bitmapFont.setColor(Color.GREEN);
        Image background = new Image(new Texture(pixmap));

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

		/*	Button.ButtonStyle style = new Button.ButtonStyle();
		 style.up=ResourcesManager.textures.get(TextureNameManager.ReimuBullet);
		 style.down=ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce);
		 Button button = new Button(style);
		 button.setPosition(10,10);
		 button.addListener(new ClickListener() {
		 @Override
		 public void clicked(InputEvent event,float x,float y){
		 BaseMyPlane.instance.onBomb=true;
		 }
		 });
		 groupNormal.addActor(button);
		 */
        inputManager = new InputMultiplexer();
        //	inputManager.addProcessor(stage);
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
    }

    public void restart() {
        gameTime = 0;
        stage.clear();
        groupNormal.clear();
        onBoss = false;
        enemys = new BaseEnemyPlane[32];
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
        //	MainScreen.sleep=0;
    }

}
