package com.meng.TaiHunDanmaku.boss.plane;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.boss.*;
import com.meng.TaiHunDanmaku.boss.danmaku.Junko.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.dropItems.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.meng.TaiHunDanmaku.boss.danmaku.*;

public class BossTaiZhang2 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };
	private int wf=200;

    @Override
    public void update(){
        super.update();
        if(wf-->0) return;
        MainScreen.spellMode();
        spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        targetPosition=center.cpy();
        spellCard=new yonghengdewenrou();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(7000,Color.YELLOW));
	  }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
	  }

    @Override
    public void kill(){
        BaseEnemyBullet.killAllBullet(BulletKillMode.KillOnBossLastDeath);
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
