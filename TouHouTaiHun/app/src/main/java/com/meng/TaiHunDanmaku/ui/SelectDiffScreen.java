package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.helpers.*;


public class SelectDiffScreen extends ScreenAdapter{
    private FitViewport fitViewport;
    private GameMain gameMain;
    private Stage stage;
    private Image background;
    private int time = 0;
    private int tmp = 1;

    public SelectDiffScreen(final GameMain gameMain){
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

		/*     Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGBA8888);
		 pixmap.setColor(Color.GRAY);
		 pixmap.fill();
		 Image background = new Image(new Texture(pixmap));

		 background.setBounds(0,0,386,450);
		 stage.addActor(background);*/
        Group buttons = new Group();

        int buttonX = 120;
        int buttonY = 80;
        Button buttonE = new Button(ResourcesManager.textures.get("easy"),ResourcesManager.textures.get("easy_c"));
        buttonE.setPosition(buttonX,buttonY+200);
        Button buttonN = new Button(ResourcesManager.textures.get("normal"),ResourcesManager.textures.get("normal_c"));
        buttonN.setPosition(buttonX,buttonY+150);
        Button buttonH = new Button(ResourcesManager.textures.get("hard"),ResourcesManager.textures.get("hard_c"));
        buttonH.setPosition(buttonX,buttonY+100);
        Button buttonL = new Button(ResourcesManager.textures.get("lunatic"),ResourcesManager.textures.get("lunatic_c"));
        buttonL.setPosition(buttonX,buttonY+50);
        buttonE.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.difficultFlag="Easy";
				  gameMain.setSelectCharScreen();
				}
			});
        buttonN.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.difficultFlag="Normal";
				  gameMain.setSelectCharScreen();
				}
			});
        buttonH.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){        
				  gameMain.difficultFlag="Hard";
				  gameMain.setSelectCharScreen();
				}
			});
        buttonL.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){               
				  gameMain.difficultFlag="Lunatic";
				  gameMain.setSelectCharScreen();
				}
			});
        buttons.addActor(buttonE);
        buttons.addActor(buttonN);
        buttons.addActor(buttonH);
        buttons.addActor(buttonL);
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
