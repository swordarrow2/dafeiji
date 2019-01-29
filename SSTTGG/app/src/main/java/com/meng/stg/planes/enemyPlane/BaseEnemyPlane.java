package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.BaseGameObject;
import com.meng.stg.move.MoveManager;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.ui.MainScreen;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.myPlane.BaseMyPlane;

import static com.meng.stg.ui.MainScreen.enemys;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class BaseEnemyPlane extends BaseGameObject{

    public boolean Killed=false;
    public int time=0;
    public float vx=0;
    public float vy=0;
    public float enemyLastX;
    public Rectangle drawBox=new Rectangle();
    public int hp=10;
	
	private float playerLastX=270;

    public void Init(float x,float y,float vx,float vy){
        Init(x,y,vx,vy,10);
    }

    public void Init(float x,float y,float vx,float vy,int hp){
        Drawer=Pools.ImagePool.obtain();
        Drawable drawable=getDrawable();
        Drawer.setDrawable(drawable );
		//Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        Killed=false;
        objectCenter.set(x,y);
        this.vx=vx;
        this.vy=vy;
        this.hp=hp;
        judgeCircle=new Circle(objectCenter,Drawer.getWidth()/2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
        for(int i=0;i<32;i++){
            if(enemys[i]==null){
                enemys[i]=this;
                break;
            }
        }
        mvc=new MoveManager(this,
                new MoveMethodStraight(60,new Vector2(0,-5)),
                new MoveMethodStraight(20,new Vector2(1,9)),
                new MoveMethodStraight(20,new Vector2(-5,-9)),
                new MoveMethodStraight(20,new Vector2(-1,3)),
                new MoveMethodStraight(10,new Vector2(-1,-21))
        );
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
        Drawer.remove();
        judgeCircle=null;
        Pools.ImagePool.free(Drawer);
    }

    public void Update(){
        time++;
        animFlag++;
        mvc.update();
        objectCenter.add(velocity);
        anim();
        shoot();
        Drawer.setPosition(objectCenter.x,objectCenter.y,Align.center);
		if(objectCenter.x>playerLastX){
            playerLastX=objectCenter.x;
            Drawer.setDrawable(getRightMoveAnim());
		  }else if(objectCenter.x<playerLastX){
            playerLastX=objectCenter.x;
			  TextureRegion t=((TextureRegionDrawable)getLeftMoveAnim()).getRegion();
			  t.flip(true,false);
            Drawer.setDrawable(new TextureRegionDrawable(t));
		  }else{
            playerLastX=objectCenter.x;
            Drawer.setDrawable(getStayAnim());
		  }
        judgeCircle.setPosition(objectCenter.x,objectCenter.y);
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
        if(!drawBox.overlaps(MainScreen.FightArea)){
            Kill();
        }else{
            Judge();
        }
    }

    protected abstract void anim();

    protected abstract void shoot();

    protected abstract Drawable getDrawable();
	
	public abstract Drawable getStayAnim();

    public abstract Drawable getRightMoveAnim();

    public abstract Drawable getLeftMoveAnim();
	

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
    }

    public void Judge(){
        if(getCollisionArea().contains(BaseMyPlane.Instance.objectCenter)){
            hit();
            BaseMyPlane.Instance.Kill();
        }
    }

    public boolean isKilled(){
        return Killed;
    }

}
