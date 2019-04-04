package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.helpers.*;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;
import static com.meng.TaiHunDanmaku.ui.GameMain.spriteBatch;

public class SelectDiffScreen extends ScreenAdapter {
    private FitViewport fitViewport;
    public GameMain gameMain;
    public Stage stage;
    private int width = 386;
    private int height = 600;
    private Group buttons;
    private InputMultiplexer inputManager;

    public SelectDiffScreen(final GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        super.show();
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, GameMain.spriteBatch);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Image background = new Image(new Texture(pixmap));

        background.setBounds(0, 0, 386, 450);
        stage.addActor(background);
        buttons = new Group();

        Button buttonE = new Button(ResourcesManager.textures.get("easy"), ResourcesManager.textures.get("easy_c"));
        buttonE.setPosition(121, 32);
        Button buttonN = new Button(ResourcesManager.textures.get("normal"), ResourcesManager.textures.get("normal_c"));
        buttonN.setPosition(121, 112);
        Button buttonH = new Button(ResourcesManager.textures.get("hard"), ResourcesManager.textures.get("hard_c"));
        buttonH.setPosition(121, 192);
        Button buttonL = new Button(ResourcesManager.textures.get("lunatic"), ResourcesManager.textures.get("lunatic_c"));
        buttonL.setPosition(121, 272);
        Button buttonEx = new Button(ResourcesManager.textures.get("extra"), ResourcesManager.textures.get("extra_c"));
        buttonEx.setPosition(121, 352);


        buttonE.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (FightScreen.difficultFlag != 0) {
                    FightScreen.difficultFlag = 0;
                    return;
                }
                gameMain.setSelectCharScreen();
            }
        });
        buttonN.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (FightScreen.difficultFlag != 1) {
                    FightScreen.difficultFlag = 1;
                    return;
                }
                gameMain.setSelectCharScreen();
            }
        });
        buttonH.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (FightScreen.difficultFlag != 2) {
                    FightScreen.difficultFlag = 2;
                    return;
                }
                gameMain.setSelectCharScreen();
            }
        });
        buttonL.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (FightScreen.difficultFlag != 3) {
                    FightScreen.difficultFlag = 3;
                    return;
                }
                gameMain.setSelectCharScreen();
            }
        });
        buttonEx.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (FightScreen.difficultFlag != 4) {
                    FightScreen.difficultFlag = 4;
                    return;
                }
                gameMain.setSelectCharScreen();
            }
        });

        buttons.addActor(buttonE);
        buttons.addActor(buttonN);
        buttons.addActor(buttonH);
        buttons.addActor(buttonL);
        buttons.addActor(buttonEx);
        stage.addActor(buttons);

        inputManager = new InputMultiplexer();
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
        stage.draw();
        GameMain.spriteBatch.begin();
        bitmapFont.draw(GameMain.spriteBatch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 590);
        GameMain.spriteBatch.end();
        super.render(delta);
    }
}
