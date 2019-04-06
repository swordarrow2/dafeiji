package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.helpers.*;


public class SelectCharScreen extends ScreenAdapter {
    private FitViewport fitViewport;
    public GameMain gameMain;
    public Stage stage;
    private int width = 386;
    private int height = 600;
    private InputMultiplexer inputManager;
    private Group buttons;

    public SelectCharScreen(final GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        super.show();
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, gameMain.spriteBatch);
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Image background = new Image(new Texture(pixmap));
        background.setBounds(0, 0, 386, 450);
        stage.addActor(background);
        buttons = new Group();

        Button buttonA = new Button(ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce), ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce));
        buttonA.setPosition(121, 32);
        Button buttonB = new Button(ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight), ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight));
        buttonB.setPosition(121, 112);
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.equipmentFlag = "A";
                gameMain.setFightScreen();
            }
        });
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.equipmentFlag = "B";
                gameMain.setFightScreen();
            }
        });
        buttons.addActor(buttonA);
        buttons.addActor(buttonB);
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
        gameMain.spriteBatch.begin();
        gameMain.bitmapFont.draw(gameMain.spriteBatch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 590);
        gameMain.spriteBatch.end();
        super.render(delta);
    }
}
