package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.*;
import com.meng.stg.boss.*;
import com.meng.stg.boss.danmaku.Junko.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.graphics.*;
import com.meng.stg.task.*;

public class BossPlaneJunko2 extends BaseBossPlane{

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
        spellCard=new spell7();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(5500,Color.YELLOW));
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(3500,Color.ORANGE));
		MainScreen.instence.layoutManager.nextPart.add(new partAgent(1200,Color.RED));
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
        MainScreen.onSpellCard=false;
        MainScreen.sleep=75;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
	  }
  }
