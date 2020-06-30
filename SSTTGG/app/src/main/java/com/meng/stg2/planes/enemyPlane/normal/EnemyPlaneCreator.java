package com.meng.stg2.planes.enemyPlane.normal;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg2.boss.plane.BossPlaneJunko;
import com.meng.stg2.task.*;

public class EnemyPlaneCreator{
    private EnemyType enemyType=EnemyType.xiaozayu;
    private Vector2 enemyCenter=Vector2.Zero;
    private int hp=10;
    private EnemyColor enemyColor=EnemyColor.red;

    public EnemyPlaneCreator(){
    }

    public EnemyPlaneCreator setEnemyType(EnemyType enemyType){
        this.enemyType=enemyType;
        return this;
    }

    public EnemyPlaneCreator setHp(int hp){
        this.hp=hp;
        return this;
    }

    public EnemyPlaneCreator setEnemyCenter(float x,float y){
        enemyCenter=new Vector2(x,y);
        return this;
    }

    public EnemyPlaneCreator setEnemyCenter(Vector2 enemyCenter){
        this.enemyCenter=enemyCenter;
        return this;
    }

    public EnemyPlaneCreator setColor(EnemyColor color){
        enemyColor=color;
        return this;
    }

    public void createEnemy(){
        switch(enemyType){
            case xiaozayu:
			  new EnemyXiaozayu().init(enemyCenter.cpy(),enemyColor,5,hp,new Task[]{
										   new TaskMove(193,250),
										   new TaskWait(120),
										   new TaskMove(-100,70)
										 });
                break;
            case dahudie:
			  new EnemyDahudie().init(enemyCenter.cpy(),5,enemyColor,hp,new Task[]{
				new TaskMove(193,250)
			  });
                break;
            case Boss:
			  new BossPlaneJunko().init(enemyCenter.cpy(),10,hp,new Task[]{
											new TaskMove(193,250)
										  });
                break;
        }
    }
}
