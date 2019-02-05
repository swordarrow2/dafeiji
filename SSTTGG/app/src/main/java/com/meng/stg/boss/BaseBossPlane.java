package com.meng.stg.boss;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;

public abstract class BaseBossPlane extends BaseEnemyPlane{

    public BaseSpellCard spellCard;

    @Override
    public abstract Vector2 getSize();

    @Override
    public void shoot(){
        spellCard.update();
    }

    @Override
    public void kill(){
        super.kill();
    //    BaseBullet.killAllBullet();
        //	new BossPlaneJunko2().init(new Vector2(50,419),enemyColor,5,hp,new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)));
        //	stage1.epc.setEnemyCenter(50,419).setEnemyType(EnemyType.Boss).setColor(EnemyColor.blue).setMoveMethods(new MoveGradually(90,1,new Vector2(0,-7f),new Vector2(1,-0.1f)),new MoveMethodStraight(1,1,new Vector2(0,0)) ).createEnemy();
    }

}
