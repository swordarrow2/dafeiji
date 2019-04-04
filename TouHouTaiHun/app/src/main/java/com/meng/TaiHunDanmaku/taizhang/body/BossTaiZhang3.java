package com.meng.TaiHunDanmaku.taizhang.body;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;

public class BossTaiZhang3 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };
	private int wf=200;

    @Override
    public void update(){
        super.update();
        if(wf-->0) return;
        FightScreen.spellMode();
        spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        targetPosition=center.cpy();
        spellCard=new liukai();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(7000,Color.GREEN));
	  }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
	  }

    @Override
    public void killByJudge(){
        BaseEnemyBullet.killAllBullet(BulletKillMode.KillOnBossLastDeath);
		DropItem.create(objectCenter.cpy(),DropItemType.power);
		new BossTaiZhang4().init(objectCenter.cpy(),10,7000,new Task[]{
									 new TaskMoveTo(193,250)
								   });
        super.killByJudge();
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }

