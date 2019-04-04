package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.helpers.*;

import static com.meng.TaiHunDanmaku.ui.GameMain.bitmapFont;

public class SelectCharScreen extends ScreenAdapter{
    private FitViewport fitViewport;
    public GameMain gameMain;
    public Stage stage;
    private int width = 386;
    private int height = 600;
    private InputMultiplexer inputManager;

    public SelectCharScreen(final GameMain gameMain){
        this.gameMain=gameMain;
	  }

    @Override
    public void show(){
        super.show();
        fitViewport=new FitViewport(width,height);
        stage=new Stage(fitViewport,GameMain.spriteBatch);
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();
        Image background = new Image(new Texture(pixmap));
        background.setBounds(0,0,386,450);
        stage.addActor(background);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up=ResourcesManager.textures.get(TextureNameManager.ReimuBullet);
        style.down=ResourcesManager.textures.get(TextureNameManager.ReimuSubPlaneBulletInduce);
        Button button = new Button(style);
        button.setPosition(10,10);
        button.addListener(new ClickListener() {
			  @Override
			  public void clicked(InputEvent event,float x,float y){
				  gameMain.setFightScreen();
				}
			});
        stage.addActor(button);
        inputManager=new InputMultiplexer();
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
        stage.draw();
        GameMain.spriteBatch.begin();
        bitmapFont.draw(GameMain.spriteBatch,"FPS:"+Gdx.graphics.getFramesPerSecond(),10,590);
        GameMain.spriteBatch.end();
        super.render(delta);
	  }
  }
