package cn.s3bit.th902;

import cn.s3bit.th902.gamecontents.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * @author Obsidianss
 * <p>
 * Game class.
 * </p>
 */
public class GameMain extends Game{
	public static final String GAME_TITLE = "TH902";
	public static GameMain instance = null;
	public InputMultiplexer inputManager;
	public static final boolean DEBUG = false;

	public Stage activeStage = null;

	public GameMain(){
		super();
	  }
	@Override
	public void create(){
		instance=this;
		Gdx.graphics.setVSync(false);
		//Gdx.graphics.setContinuousRendering(false);
		ResourceManager.Load();
		//	setScreen(new MainMenuScreen());
		setScreen(new FightScreen());
		//setScreen(new DifficultySelectScreen());
		//setScreen(new CharacterSelectScreen());
		inputManager=new InputMultiplexer();
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
	  }

	@Override
    public void resize(int width,int height){
        super.resize(width,height);
        activeStage.getViewport().update(width,height);
	  }

	@Override
	public void setScreen(Screen screen){
		if(getScreen()!=screen){
			if(activeStage!=null){
				activeStage.dispose();
			  }
			//activeStage = new Stage();
			activeStage=new Stage(new FitViewport(386,450+150),new SpriteBatch());
			Entity.Reset();
		  }
		super.setScreen(screen);
	  }

	@Override
	public void render(){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		activeStage.draw();
		activeStage.getBatch().begin();
		FightScreen.bf.draw(activeStage.getBatch(),"FPS: "+Gdx.graphics.getFramesPerSecond(),6,600);
		activeStage.getBatch().end();
	  }
  }
