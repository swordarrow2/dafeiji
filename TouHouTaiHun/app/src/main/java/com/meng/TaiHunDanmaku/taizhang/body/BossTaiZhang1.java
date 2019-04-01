package com.meng.TaiHunDanmaku.taizhang.body;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class BossTaiZhang1 extends BaseBossPlane{

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
			MainScreen.bl=null;
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
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(4000,Color.GREEN));
	  }

	@Override
	public void kill(){
		
		//	MainScreen.sleep=90;
		MainScreen.normalMode();
		DropItem.create(objectCenter.cpy(),DropItemType.power);
		new BossTaiZhang2().init(objectCenter.cpy(),10,7000,new Task[]{
									   new TaskMoveTo(193,250)
									 });
		super.kill();							 
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }

  }
