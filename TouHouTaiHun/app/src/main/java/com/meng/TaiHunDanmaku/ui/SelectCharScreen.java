package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.helpers.TextureNameManager;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;

public class SelectCharScreen extends ScreenAdapter {
    private FitViewport fitViewport;
    public GameMain gameMain;
    public Stage stage;
    private int width = 386;
    private int height = 600;
    public InputMultiplexer inputManager;

    public SelectCharScreen(final GameMain gameMain) {
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
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = ResourcesManager.textures.get(TextureNameManager.ReimuBullet);
        style.down = ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce);
        Button button = new Button(style);
        button.setPosition(10, 10);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMain.setFightScreen();
            }
        });
        stage.addActor(button);
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
