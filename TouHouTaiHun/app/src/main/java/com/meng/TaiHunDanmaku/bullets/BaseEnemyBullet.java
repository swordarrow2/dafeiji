package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.effects.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;
import java.util.*;
import java.util.concurrent.*;

public abstract class BaseEnemyBullet extends BaseBullet{

    public static HashSet<BaseEnemyBullet> instances = new HashSet<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toDelete = new LinkedBlockingQueue<BaseEnemyBullet>();
    public static LinkedBlockingQueue<BaseEnemyBullet> toAdd = new LinkedBlockingQueue<BaseEnemyBullet>();

    public int refCount = 0;
    public int thoughCount = 0;
	public int colorNum = 0;
    public int formNum = 0;

    public Vector2 acceleration = new Vector2();

    public abstract Drawable getDrawable();

    public TaskManager taskManager;

    public void init(){
        super.init();
        toAdd.add(this);
        taskManager=new TaskManager(this,TaskRepeatMode.noRepeat);
	  }

    @Override
    public void kill(){
        super.kill();
        toDelete.add(this);
        image.remove();
        //	ObjectPools.imagePool.free(image);
	  }

    public void kill(BulletKillMode bkm){
        super.kill();
        toDelete.add(this);
        image.remove();
        Effect.create(objectCenter.cpy(),EffectType.explore);
        switch(bkm){
            case killWithNothing:
			  break;
            case killWithScorePoint:
			  DropItem.create(objectCenter.cpy(),DropItemType.highScoreSmall,bkm);
			  break;
            case killWithScorePointAndCollect:
			  DropItem.create(objectCenter.cpy(),DropItemType.highScoreSmall,bkm);
			  break;
            case KillOnBossLastDeath:
			  DropItem.create(objectCenter.cpy(),DropItemType.highScoreMediam,bkm);
			  break;
            case killOnPlayerDeath:
			  break;
		  }
	  }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
		  }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
		  }
		//	GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        for(BaseEnemyBullet baseBullet : instances){
            baseBullet.update();
		  }
		//	GameMain.spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	  }

    @Override
    public void update(){
        super.update();
        taskManager.update();
		/*	if (refCount > 0) {
		 if (objectCenter.x < 5) {
		 targetPosition.x = -targetPosition.x;
		 objectCenter.x = 5;
		 refCount--;
		 }
		 if (objectCenter.x >= MainScreen.fightArea.width-5) {
		 targetPosition.x = -targetPosition.x;
		 objectCenter.x = MainScreen.fightArea.width - 5;
		 refCount--;
		 }
		 if (objectCenter.y < 5) {
		 targetPosition.y = -targetPosition.y;
		 objectCenter.y = 5;
		 refCount--;
		 }
		 if (objectCenter.y > MainScreen.fightArea.height-5) {
		 targetPosition.y = -targetPosition.y;
		 objectCenter.y = MainScreen.fightArea.height - 5;
		 refCount--;
		 }
		 } else if (thoughCount > 0) {
		 if (objectCenter.x < 5) {
		 objectCenter.x = MainScreen.fightArea.width - 4;
		 thoughCount--;
		 }
		 if (objectCenter.x > MainScreen.fightArea.width-5) {
		 objectCenter.x = 5;
		 thoughCount--;
		 }
		 if (objectCenter.y < 5) {
		 objectCenter.y = MainScreen.fightArea.height - 5;
		 thoughCount--;
		 }
		 if (objectCenter.y > MainScreen.fightArea.height-5) {
		 objectCenter.y = 5;
		 thoughCount--;
		 }
		 }
		 */
		objectCenter.add(velocity);
		velocity.add(acceleration);


	  }

    public static void killAllBullet(BulletKillMode bkm){
        switch(bkm){
            case KillOnBossLastDeath:
			  new BulletRemover().init(BaseBossPlane.instence.objectCenter.cpy());
			  break;
            default:
			  new BulletRemover().init();
			  break;
		  }

	  }

    @Override
    public Shape2D getCollisionArea(){
        return judgeCircle;
		/*
		 Rectangle r=new Rectangle();
		 r.setSize(164,18);

		 r.setPosition(objectCenter.x-164/2,objectCenter.y-18/2);
		 return r ;
		 */
	  }

    @Override
    public void judge(){

	  }

    @Override
    public float getRotationDegree(){
        return velocity.angle()+270;
	  }
  }
