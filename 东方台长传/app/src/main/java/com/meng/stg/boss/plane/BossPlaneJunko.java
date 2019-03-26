package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.boss.*;
import com.meng.stg.boss.danmaku.Junko.normal1;
import com.meng.stg.boss.danmaku.Junko.spell1;
import com.meng.stg.dropItems.DropItemType;
import com.meng.stg.ui.*;
import com.meng.stg.task.*;
import com.meng.stg.dropItems.*;

public class BossPlaneJunko extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,8}
	  };

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
		//    objectCenter.set(193,350);
		if(hp>4000){
			MainScreen.normalMode();
			normalDanmaku.update();
		  }else{
			MainScreen.spellMode();
			spellCard.update();
		  }
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
		//   BulletRemover.killAllBullet();
        objectName="chunhu";
		targetPosition=center.cpy();
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        normalDanmaku=new normal1();
        normalDanmaku.init(this);
		spellCard=new spell1();
		spellCard.init(this);
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(4000));
	  }

	@Override
	public void kill(){
		
		//	MainScreen.sleep=90;
		MainScreen.normalMode();
		DropItem.create(objectCenter.cpy(),DropItemType.power);
		new BossPlaneJunko2().init(objectCenter.cpy(),10,7000,new Task[]{
									   new TaskMove(193,250)
									 });
		super.kill();							 
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }

  }
