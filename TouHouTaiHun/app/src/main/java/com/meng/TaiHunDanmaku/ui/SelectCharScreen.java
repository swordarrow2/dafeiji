package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.control.ReplayManager;
import com.meng.TaiHunDanmaku.helpers.*;


public class SelectCharScreen extends ScreenAdapter {
    private FitViewport fitViewport;
    public GameMain gameMain;
    public Stage stage;
    private int width = 386;
    private int height = 600;
    private boolean A = false;

    public SelectCharScreen(final GameMain gameMain) {
        this.gameMain = gameMain;
    }

    @Override
    public void show() {
        super.show();
        fitViewport = new FitViewport(width, height);
        stage = new Stage(fitViewport, gameMain.spriteBatch);

        Image background = new Image(ResourcesManager.textures.get("spellbackground"));
        background.setBounds(0, 0, 386, 450);
        stage.addActor(background);
        Group buttons = new Group();

        Button buttonA = new Button(ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce), ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce));
        buttonA.setPosition(120, 200);
        Button buttonB = new Button(ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight), ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletStraight));
        buttonB.setPosition(120, 150);
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //         gameMain.equipmentFlag = "A";
                //        ReplayManager.init(gameMain, System.currentTimeMillis());
                //        gameMain.setFightScreen();
                A=true;
            }
        });
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.equipmentFlag = "B";
                ReplayManager.init(gameMain, System.currentTimeMillis());
                gameMain.setFightScreen();
            }
        });
        buttons.addActor(buttonA);
        buttons.addActor(buttonB);
        stage.addActor(buttons);

        Gdx.input.setInputProcessor(stage);
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
        gameMain.bitmapFont.draw(gameMain.spriteBatch, "选择装备：", 50, 250);
        if (A) {
            gameMain.bitmapFont.draw(gameMain.spriteBatch, "售罄", 165, 215);
        }
        gameMain.spriteBatch.end();
        super.render(delta);
    }
}
