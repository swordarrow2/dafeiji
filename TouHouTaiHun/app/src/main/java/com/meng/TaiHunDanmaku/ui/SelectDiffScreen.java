package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.control.ReplayManager;
import com.meng.TaiHunDanmaku.helpers.*;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;
import static com.meng.TaiHunDanmaku.ui.GameMain.spriteBatch;

public class SelectDiffScreen extends ScreenAdapter {
    private FitViewport fitViewport;
    private GameMain gameMain;
    private Stage stage;
    private TextureRegion backgroundTexture;
    private int time = 0;
    private int tmp = 1;

    public SelectDiffScreen(final GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        super.show();
        Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
        backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

        int width = 386;
        int height = 600;
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, GameMain.spriteBatch);

		/*     Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGBA8888);
		 pixmap.setColor(Color.GRAY);
		 pixmap.fill();
		 Image background = new Image(new Texture(pixmap));

		 background.setBounds(0,0,386,450);
		 stage.addActor(background);*/
        Group buttons = new Group();

        Button buttonE = new Button(ResourcesManager.textures.get("easy"), ResourcesManager.textures.get("easy_c"));
        buttonE.setPosition(51, 352);
        Button buttonN = new Button(ResourcesManager.textures.get("normal"), ResourcesManager.textures.get("normal_c"));
        buttonN.setPosition(91, 272);
        Button buttonH = new Button(ResourcesManager.textures.get("hard"), ResourcesManager.textures.get("hard_c"));
        buttonH.setPosition(121, 192);
        Button buttonL = new Button(ResourcesManager.textures.get("lunatic"), ResourcesManager.textures.get("lunatic_c"));
        buttonL.setPosition(161, 112);
        Button buttonEx = new Button(ResourcesManager.textures.get("extra"), ResourcesManager.textures.get("extra_c"));
        buttonEx.setPosition(181, 32);


        buttonE.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.onReplay = false;
                gameMain.setSelectCharScreen();
            }
        });
        buttonN.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.onReplay = false;
                gameMain.setSelectCharScreen();
            }
        });
        buttonH.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.onReplay = false;
                gameMain.setSelectCharScreen();
            }
        });
        buttonL.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.onReplay = false;
                gameMain.setSelectCharScreen();
            }
        });
        buttonEx.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.onReplay = true;
                gameMain.setFightScreen();
            }
        });

        buttons.addActor(buttonE);
        buttons.addActor(buttonN);
        buttons.addActor(buttonH);
        buttons.addActor(buttonL);
        buttons.addActor(buttonEx);
        stage.addActor(buttons);

        InputMultiplexer inputManager = new InputMultiplexer();
        inputManager.addProcessor(stage);
        Gdx.input.setInputProcessor(inputManager);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        if (time == 900)
            tmp = -1;
        else if (time == 0)
            tmp = 1;
        time += tmp;
        GameMain.spriteBatch.begin();
        //for(int i = 0; i<30; i++){

        GameMain.spriteBatch.draw(backgroundTexture, -(time % 900), 0, 2900, 800);
        //	}
        bitmapFont.draw(GameMain.spriteBatch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 590);
        GameMain.spriteBatch.end();
        stage.draw();
        super.render(delta);
    }
}
