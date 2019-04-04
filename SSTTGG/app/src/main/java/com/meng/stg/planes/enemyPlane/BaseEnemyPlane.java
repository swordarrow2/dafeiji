package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.bullets.enemy.BulletShooter;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.planes.MoveStatus;
import com.meng.stg.planes.enemyPlane.normal.EnemyColor;
import com.meng.stg.ui.MainScreen;

import static com.meng.stg.ui.MainScreen.enemys;
import com.meng.stg.task.*;

public abstract class BaseEnemyPlane extends BaseGameObject{

    public int time=0;
    public float enemyLastX;
    public float hp=10;
    public EnemyColor enemyColor;
    public boolean isKilled=false;

    public int animFrom=0;
    public int animTo=7;
    public int everyAnimFrameTime=0;
    public int curFrameNumber=0;
    public MoveStatus status=MoveStatus.stay;
    public String objectName="";
    public boolean flip=false;
    public int[][] animNum;

    public BulletShooter bulletShooter;
	public Vector2 targetPosition=new Vector2();
	
	public TaskManager taskManager;
	
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] task){
        super.init();
		taskManager=new TaskManager(this,TaskRepeatMode.noRepeat);
		for(Task t:task){
		  taskManager.addTask(t);
		}
        this.everyAnimFrameTime=everyAnimFrameTime;
        objectName="zayu";
        isKilled=false;
        objectCenter=center;
        this.hp=hp;
        size=getSize();
        image.setRotation(0);
        image.setSize(size.x,size.y);
        judgeCircle=new Circle(objectCenter,image.getWidth()/4);
        MainScreen.mainGroup.addActor(image);
        for(int i=0;i<32;i++){
            if(enemys[i]==null){
                enemys[i]=this;
                break;
            }
        }
    }

    public abstract Vector2 getSize();

    public float getHp(){
        return hp;
    }

    public void hit(float bulletDamage){
        if(hp<1){
            kill();
        }else{
            if(MainScreen.onSpellCard){
				hp=hp-bulletDamage/7;
			}else{
				hp-=bulletDamage;
			}
        }
    }

    public Vector2 getLocation(){
        return objectCenter;
    }
	
	public void moveTo(float x,float y){
        targetPosition.x=x;
        targetPosition.y=y;
	  }

    public void setStatus(MoveStatus mov){
        if(mov==status) return;
        status=mov;
        switch(status){
            case stay:
                flip=false;
                animFrom=animNum[0][0];
                animTo=animNum[0][1];
                curFrameNumber=animFrom;
                break;
            case moveLeft:
                flip=true;
                animFrom=animNum[1][0];
                animTo=animNum[1][1];
                curFrameNumber=animFrom;
                break;
            case moveRight:
                flip=false;
                animFrom=animNum[1][0];
                animTo=animNum[1][1];
                curFrameNumber=animFrom;
                break;
        }
    }

    public void kill(){
        super.kill();
        image.remove();
        isKilled=true;
        judgeCircle=null;
    }

    public void update(){
        super.update();
        time++;
        animFlag++;
        taskManager.update();

        anim();
        shoot();

        image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        judgeCircle.setPosition(objectCenter.x,objectCenter.y);
        if(judgeCircle.x<-5||judgeCircle.x>390
                ||judgeCircle.y<-5||judgeCircle.y>460){
            kill();
        }else{
            judge();
        }
		
		if(objectCenter.cpy().sub(targetPosition).len2()>10){
            objectCenter.add(targetPosition.cpy().sub(objectCenter).nor().scl(3f));
		  }
    }

    private void anim(){
        if(objectCenter.x>enemyLastX){
            enemyLastX=objectCenter.x;
            setStatus(MoveStatus.moveRight);
        }else if(objectCenter.x<enemyLastX){
            enemyLastX=objectCenter.x;
            setStatus(MoveStatus.moveLeft);
        }else{
            setStatus(MoveStatus.stay);
        }
        if(time>=everyAnimFrameTime){
            ++curFrameNumber;
            time=0;
        }
        if(curFrameNumber>animTo){
            curFrameNumber=animFrom+2;
        }
        if(flip){
            image.setDrawable(ResourcesManager.flipedTextures.get(objectName+curFrameNumber));
        }else{
            image.setDrawable(ResourcesManager.textures.get(objectName+curFrameNumber));
        }
    }

    public abstract void shoot();

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
    }

    public void judge(){

    }

}
