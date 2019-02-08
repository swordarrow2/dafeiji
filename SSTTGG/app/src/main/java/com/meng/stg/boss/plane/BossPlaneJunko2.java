package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.boss.danmaku.Junko.spell7;
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.bullets.enemy.BulletKillMode;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.ui.MainScreen;

public class BossPlaneJunko2 extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
            {10,14},
            {5,9}
    };
    int wf=200;

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
        //    objectCenter.set(193,350);
        if(wf-->0) return;
        MainScreen.spellMode();
        spellCard.update();
    }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        //     BaseRemover.killAllBullet();
        targetPosition=center.cpy();
        spellCard=new spell7();
        spellCard.init(this);
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;

    }

    @Override
    public void hit(float bulletDamage){
        if(wf>0) return;
        super.hit(bulletDamage);
    }


    @Override
    public void kill(){
        super.kill();
        BaseEnemyBullet.killAllBullet(BulletKillMode.killWithScorePointAndCollect);
        MainScreen.onSpellCard=false;
        MainScreen.sleep=90;
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
    }
}
