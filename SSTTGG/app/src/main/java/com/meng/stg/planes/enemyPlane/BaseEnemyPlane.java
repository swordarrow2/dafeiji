package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.move.MoveManager;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.myPlane.BaseMyPlane;

import static com.meng.stg.ui.MainScreen.enemys;

import com.meng.stg.move.*;
import com.meng.stg.planes.*;
/*
 base class of enemy
 */
public abstract class BaseEnemyPlane extends BaseGameObject{

    public boolean Killed=false;
    public int time=0;
    public float vx=0;
    public float vy=0;
    public float enemyLastX;
    public Rectangle drawBox=new Rectangle();
    public int hp=10;

	public EnemyAnimationManager enemyAnimationManager;
	private float playerLastX=270;

    public void Init(float x,float y,float vx,float vy){
        Init(x,y,vx,vy,10);
	  }

    public void Init(float x,float y,float vx,float vy,int hp){
        image=Pools.imagePool.obtain();
		enemyAnimationManager=new EnemyAnimationManager(this,5);
        Killed=false;
        objectCenter.set(x,y);
        this.vx=vx;
        this.vy=vy;
        this.hp=hp;
        judgeCircle=new Circle(objectCenter,image.getWidth()/4); //中心、半径
        MainScreen.mainGroup.addActor(image);
        for(int i=0;i<32;i++){
            if(enemys[i]==null){
                enemys[i]=this;
                break;
			  }
		  }
        moveManager=new MoveManager(this,new MoveGradually(90,new Vector2(0,-7f),new Vector2(1,-0.1f)));
	  }

    public int getHp(){
        return hp;
	  }

    public void hit(){
        if(--hp<1){
            Kill();
		  }
	  }

    public Vector2 getLocation(){
        return objectCenter;
	  }

    public void Kill(){
        Killed=true;
        image.remove();
        judgeCircle=null;
        Pools.imagePool.free(image);
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
        if(!drawBox.overlaps(MainScreen.fightArea)){
            Kill();
		  }else{
            Judge();
		  }
	  }

    protected void anim(){
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

    protected abstract void shoot();

    public Shape2D getCollisionArea(){
        return judgeCircle;
	  }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
	  }

    public void Judge(){
        if(getCollisionArea().contains(BaseMyPlane.instance.objectCenter)){
            hit();
            BaseMyPlane.instance.Kill();
		  }
	  }

    public boolean isKilled(){
        return Killed;
	  }

  }
