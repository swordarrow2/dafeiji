package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveManager;
import com.meng.stg.planes.EnemyAnimationManager;
import com.meng.stg.planes.MoveStatus;
import com.meng.stg.planes.myPlane.BaseMyPlane;
import com.meng.stg.ui.MainScreen;

import static com.meng.stg.ui.MainScreen.enemys;
import com.meng.stg.helpers.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/*
 base class of enemy
 */
public abstract class BaseEnemyPlane extends BaseGameObject{

    public int time=0;
    public float enemyLastX;
    public Rectangle drawBox=new Rectangle();
    public int hp=10;
    public EnemyColor enemyColor;
    public boolean isKilled=false;

    public EnemyAnimationManager enemyAnimationManager;

    public BulletShooter bulletShooter;

    public void init(EnemyColor c,Vector2 center,int hp,BaseMoveMethod... bmm){
	  image=new Image();
        enemyColor=c;
        isKilled=false;
        enemyAnimationManager=new EnemyAnimationManager(this,c,5);
		enemyAnimationManager.init();
        objectCenter.set(center);
        this.hp=hp;
        size=getSize();
		image.setRotation(0);
		image.setSize(size.x,size.y);
        judgeCircle=new Circle(objectCenter,image.getWidth()/4); //中心、半径
        moveManager=new MoveManager(this,bmm);
        MainScreen.mainGroup.addActor(image);
        for(int i=0;i<32;i++){
            if(enemys[i]==null){
                enemys[i]=this;
                break;
            }
        }
    }

    public abstract Vector2 getSize();

    public int getHp(){
        return hp;
    }

    public void hit(){
        if(--hp<1){
            kill();
        }
    }

    public Vector2 getLocation(){
        return objectCenter;
    }

    public void kill(){
	//  super.kill();
        image.remove();
        isKilled=true;
        judgeCircle=null;
		enemyAnimationManager.kill();
    }

    public void update(){
        super.update();
        time++;
        animFlag++;
        moveManager.update();
        enemyAnimationManager.update();
        objectCenter.add(velocity);
        anim();
        shoot();

        
        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle.setPosition(objectCenter.x,objectCenter.y);
        drawBox.set(image.getX(),image.getY(),image.getWidth(),image.getHeight());
        if(drawBox.overlaps(MainScreen.fightArea)){
            Judge();
        }else{
            kill();
        }
    }

    private void anim(){
        if(objectCenter.x>enemyLastX){
            enemyLastX=objectCenter.x;
           enemyAnimationManager.setStatus(MoveStatus.moveRight);
        }else if(objectCenter.x<enemyLastX){
            enemyLastX=objectCenter.x;
            enemyAnimationManager.setStatus(MoveStatus.moveLeft);
        }else{
           enemyAnimationManager.setStatus(MoveStatus.stay);
        }
    }

    public abstract void shoot();

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
    }

    public void Judge(){
        if(getCollisionArea().contains(BaseMyPlane.instance.objectCenter)){
            hit();
            BaseMyPlane.instance.kill();
        }
    }

}
