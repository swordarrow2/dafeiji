package com.meng.TaiHunDanmaku.taizhang.body;

import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItem;
import com.meng.TaiHunDanmaku.baseObjects.dropItems.DropItemType;
import com.meng.TaiHunDanmaku.taizhang.BaseBossPlane;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.normal1;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.normal2;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.spell1;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.spell2;
import com.meng.TaiHunDanmaku.taizhang.partAgent;
import com.meng.TaiHunDanmaku.task.Task;
import com.meng.TaiHunDanmaku.task.TaskMoveTo;
import com.meng.TaiHunDanmaku.ui.FightScreen;

public class BossTaiZhang2 extends BaseBossPlane{

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
			FightScreen.instence.normalMode();
			normalDanmaku.update();
		  }else{
			FightScreen.instence.spellMode();
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
        normalDanmaku=new normal2();
        normalDanmaku.init(this);
		spellCard=new spell2();
		spellCard.init(this);
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(4000));
	  }

	@Override
	public void kill(){

		//	FightScreen.sleep=90;
		FightScreen.instence.normalMode();
		DropItem.create(objectCenter.cpy(),DropItemType.power);
		new BossTaiZhang3().init(objectCenter.cpy(),10,2600,new Task[]{
									   new TaskMoveTo(193,250)
									 });
		super.kill();
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }

  }
