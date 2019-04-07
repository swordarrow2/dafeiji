package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;

public class MainMenuScreen extends ScreenAdapter{
    private FitViewport fitViewport;
    private GameMain gameMain;
    private Stage stage;
    private Image background;
    private int time = 0;
    private int tmp = 1;

    public MainMenuScreen(final GameMain gameMain){
        this.gameMain=gameMain;
	  }

    @Override
    public void show(){
        super.show();
        background=new Image(ResourcesManager.textures.get("spellbackground"));
        background.setBounds(0,0,386,450);

        int width = 386;
        int height = 600;
        fitViewport=new FitViewport(width,height);
        stage=new Stage(fitViewport,gameMain.spriteBatch);

        Group buttons = new Group();
        int buttonX = 80;
        int buttonY = 200;
        Button gameStart = new Button(ResourcesManager.textures.get("menu0"),ResourcesManager.textures.get("menu0"));
        gameStart.setPosition(buttonX,buttonY+128);
        Button extraStart = new Button(ResourcesManager.textures.get("menu2"),ResourcesManager.textures.get("menu2"));
        extraStart.setPosition(buttonX,buttonY+96);
        Button practice = new Button(ResourcesManager.textures.get("menu5"),ResourcesManager.textures.get("menu5"));
        practice.setPosition(buttonX,buttonY+64);
        Button spellPractice = new Button(ResourcesManager.textures.get("menu7"),ResourcesManager.textures.get("menu7"));
        spellPractice.setPosition(buttonX,buttonY+32);
        Button replay = new Button(ResourcesManager.textures.get("menu8"),ResourcesManager.textures.get("menu8"));
        replay.setPosition(buttonX,buttonY);
        Button playerData = new Button(ResourcesManager.textures.get("menu11"),ResourcesManager.textures.get("menu11"));
        playerData.setPosition(buttonX,buttonY-32);
        Button musicRoom = new Button(ResourcesManager.textures.get("menu13"),ResourcesManager.textures.get("menu13"));
        musicRoom.setPosition(buttonX,buttonY-64);
        Button option = new Button(ResourcesManager.textures.get("menu15"),ResourcesManager.textures.get("menu15"));
        option.setPosition(buttonX,buttonY-96);
        Button quit = new Button(ResourcesManager.textures.get("menu19"),ResourcesManager.textures.get("menu19"));
        quit.setPosition(buttonX,buttonY-128);

        gameStart.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.onReplay=false;
				  gameMain.setSelectDiffScreen();
				}
			});
        extraStart.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.difficultFlag="Extra";
				  gameMain.onReplay=false;
				  gameMain.setFightScreen();
				}
			});
        replay.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.onReplay=true;
				  gameMain.setFightScreen();
				}
			});

        buttons.addActor(gameStart);
        buttons.addActor(extraStart);
        buttons.addActor(practice);
        buttons.addActor(spellPractice);
        buttons.addActor(replay);
        buttons.addActor(playerData);
        buttons.addActor(musicRoom);
        buttons.addActor(option);
        buttons.addActor(quit);

        stage.addActor(buttons);

        InputMultiplexer inputManager = new InputMultiplexer();
        inputManager.addProcessor(stage);
        Gdx.input.setInputProcessor(inputManager);
	  }

    @Override
    public void resize(int width,int height){
        super.resize(width,height);
        fitViewport.update(width,height);
	  }

    @Override
    public void render(float delta){
        if(time==900)
		  tmp=-1;
        else if(time==0)
		  tmp=1;
        time+=tmp;
        gameMain.spriteBatch.begin();
        background.draw(gameMain.spriteBatch,1f);
        gameMain.bitmapFont.draw(gameMain.spriteBatch,"FPS:"+Gdx.graphics.getFramesPerSecond(),10,590);
        gameMain.spriteBatch.end();
        stage.draw();
        super.render(delta);
	  }
  }
