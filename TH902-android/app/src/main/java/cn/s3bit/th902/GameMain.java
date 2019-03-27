package cn.s3bit.th902;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.meng.stg.ui.PlayerInputProcessor;

import cn.s3bit.th902.gamecontents.Entity;

/**
 * @author Obsidianss
 * <p>
 * Game class.
 * </p>
 */
public class GameMain extends Game {
    public static final String GAME_TITLE = "TH902";
    public static GameMain instance = null;
    public InputMultiplexer inputMultiplexer;
    public Stage activeStage = null;

    @Override
    public void create() {
        instance = this;
        Gdx.graphics.setVSync(false);
        //Gdx.graphics.setContinuousRendering(false);
        ResourceManager.Load();
        // setScreen(new MainMenuScreen());
        setScreen(new FightScreen());
        //setScreen(new DifficultySelectScreen());
        //setScreen(new CharacterSelectScreen());
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void setScreen(Screen screen) {
        if (getScreen() != screen) {
            if (activeStage != null) {
                activeStage.dispose();
            }
            activeStage = new Stage();
            Entity.Reset();
        }
        super.setScreen(screen);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        activeStage.draw();
        activeStage.getBatch().begin();
        FightScreen.bf.draw(activeStage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 850, 30);
        activeStage.getBatch().end();
    }

}
