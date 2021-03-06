package com.meng.TaiHunDanmaku.taizhang.body;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.taizhang.*;
import com.meng.TaiHunDanmaku.taizhang.danmaku.taizhang.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.badlogic.gdx.graphics.*;
import com.meng.TaiHunDanmaku.task.*;

public class BossTaiZhang5 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
		  {10,14},
		  {5,9}
	  };
	private int wf=200;

    @Override
    public void update(){     
        super.update();
		if(wf-->0) return;
        FightScreen.instence.spellMode();
        spellCard.update();
	  }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        targetPosition=center.cpy();
        spellCard=new spell5();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        FightScreen.instence.layoutManager.nextPart.add(new partAgent(7200));
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(5500,Color.YELLOW));
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(3500,Color.ORANGE));
		FightScreen.instence.layoutManager.nextPart.add(new partAgent(1200,Color.RED));
	  }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
	  }

    @Override
    public void kill(){
        super.kill();
        BaseEnemyBullet.killAllBullet(BulletKillMode.KillOnBossLastDeath);
        FightScreen.instence.onSpellCard=false;
        FightScreen.instence.sleep=75;
        FightScreen.instence.onBoss=false;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }
