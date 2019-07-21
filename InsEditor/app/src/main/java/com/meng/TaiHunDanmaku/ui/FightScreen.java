package com.meng.TaiHunDanmaku.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.planes.myPlane.*;
import com.meng.TaiHunDanmaku.control.*;
import com.meng.TaiHunDanmaku.task.*;
import java.util.*;

public class FightScreen extends ScreenAdapter {
    public static FightScreen instence;
    public GameMain gameMain;
    public int gameTimeFlag = 0;
    public Stage stage;
    public Group groupNormal;
    public Group groupHighLight;
	public BaseBossPlane boss;
    public HashSet<ReflexAndThrough> reflexAndThroughs;
    private FitViewport fitViewport;
    public LaserManager laserManager;
    private Actor changeBlend1 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            gameMain.spriteBatch.end();
            gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
            gameMain.spriteBatch.begin();
		  }
	  };

    private Actor changeBlend2 = new Actor() {
        public void draw(Batch batch, float parentAlpha) {
            gameMain.spriteBatch.end();
            gameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            gameMain.spriteBatch.begin();
		  }
	  };

    @Override
    public void show() {
        init();
        super.show();
	  }

    public FightScreen(GameMain game) {
        gameMain = game;
	  }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        fitViewport.update(width, height);
	  }

    @Override
    public void render(float delta) {     
        ++gameTimeFlag;
        stage.draw();
        gameMain.spriteBatch.begin();
		if(boss==null){
			new BossTaiZhang1().init(new Vector2(275,450), 10, 21000, new Task[]{
										 new TaskMoveTo(193, 250)
									   });
		}else{
        boss.update();}
		BulletShooter.updateAll();

        BaseMyBullet.updateAll();

        BaseEnemyBullet.updateAll();

        BaseMyPlane.instance.update();


        gameMain.spriteBatch.end();
        for (ReflexAndThrough reflexAndThrough : reflexAndThroughs) {
            reflexAndThrough.update();
		  }
        laserManager.draw();
        super.render(delta);
	  }

    private void init() {
        instence = this;
        BaseEnemyBullet.instances.clear();
        BaseEnemyBullet.toAdd.clear();
        BaseEnemyBullet.toDelete.clear();
        BaseMyBullet.instances.clear();
        BaseMyBullet.toAdd.clear();
        BaseMyBullet.toDelete.clear();
        reflexAndThroughs = new HashSet<ReflexAndThrough>();
        laserManager = new LaserManager(gameMain);
        fitViewport = new FitViewport(gameMain.width, gameMain.height);
        stage = new Stage(fitViewport, gameMain.spriteBatch);
		groupNormal = new Group();
        groupHighLight = new Group();
        stage.addActor(groupNormal);
        stage.addActor(changeBlend1);
        stage.addActor(groupHighLight);
        stage.addActor(changeBlend2);

  //      boss = new BossTaiZhang1();
		new MyPlaneReimu().init(gameMain);
		InputMultiplexer inputManager = new InputMultiplexer();
        inputManager.addProcessor(new PlayerInputProcessor());
        Gdx.input.setInputProcessor(inputManager);
	  }

    public void restart() {
        init();
	  }

    @Override
    public void hide() {
        super.hide();
	  }
  }
