package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveGradually;
import android.text.method.*;

public class EnemyPlaneCreator{
    private EnemyType enemyType=EnemyType.xiaozayu;
    private Vector2 enemyCenter=Vector2.Zero;
    private int hp=10;
    private Color color=Color.RED;
    private BaseMoveMethod[] moveMethods=new BaseMoveMethod[]{new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f))};

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

    public EnemyPlaneCreator setMoveMethods(BaseMoveMethod... moveMethods){
        this.moveMethods=moveMethods;
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

    public EnemyPlaneCreator setColor(Color color){
        this.color=color;
        return this;
    }

    public void createEnemy(){
        switch(enemyType){
            case xiaozayu:
                new EnemyXiaozayu().Init(color,enemyCenter.cpy(),hp,moveMethods);
                break;
            case dahudie:
                new EnemyDahudie().Init(color,enemyCenter.cpy(),hp,moveMethods);
                break;
            case Boss:
                break;
        }
    }
}
