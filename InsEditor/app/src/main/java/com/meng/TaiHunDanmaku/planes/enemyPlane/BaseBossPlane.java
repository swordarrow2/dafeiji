package com.meng.TaiHunDanmaku.planes.enemyPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.meng.TaiHunDanmaku.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.planes.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.danmaku.*;
import com.meng.TaiHunDanmaku.task.*;
import com.meng.TaiHunDanmaku.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public abstract class BaseBossPlane extends BaseGameObject{

    public int time = 0;
    public float enemyLastX;
    public float hp = 10;
    public boolean isKilled = false;

    public int animFrom = 0;
    public int animTo = 7;
    public int everyAnimFrameTime = 0;
    public int curFrameNumber = 0;
    public MoveStatus status = MoveStatus.stay;
    public String objectName = "";
    public boolean flip = false;
    public int[][] animNum;

    public BulletShooter bulletShooter;
    public Vector2 targetPosition = new Vector2();

    public TaskManagerEnemyPlane taskManager;
	public BaseNormalDanmaku normalDanmaku;
    public BaseSpellCard spellCard;
    public void init(Vector2 center,int everyAnimFrameTime,int hp,Task[] task){
        super.init();
        taskManager=new TaskManagerEnemyPlane(this,TaskRepeatMode.noRepeat);
        for(Task t : task){
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
        FightScreen.instence.groupNormal.addActor(image);
		image.setZIndex(Data.zIndexEnemyPlane);
		FightScreen.instence.boss=this;
	  }

    public abstract Vector2 getSize();

    public void hit(float bulletDamage){
	  hp-=1;
      //  if(hp<1){
      //      kill();
		//  }else{          
      //          hp-=bulletDamage;		  
		//  }
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
		FightScreen.instence.reflexAndThroughs.clear();
	  }

    public void update(){
        super.update();
        time++;
        animFlag++;
        if(taskManager!=null){
			taskManager.update();
		}
        anim();
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
			  Drawable d=ResourcesManager.textures.get(objectName+curFrameNumber);
            image.setDrawable(d);
		  }
	  }

    public Shape2D getCollisionArea(){
        return judgeCircle;
	  }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
	  }

    public void judge(){

	  }


}
