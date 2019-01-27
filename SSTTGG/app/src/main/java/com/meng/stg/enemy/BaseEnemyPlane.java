package com.meng.stg.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Pools;
import com.meng.stg.player.BaseMyPlane;

import static com.meng.stg.MainScreen.enemys;

public abstract class BaseEnemyPlane{

    public boolean Killed=false;
    public int time=0;
    public float vx=0;
    public float vy=0;
    public float enemyLastX;
    public int animTime=0;
    public Image Drawer=null;
    public Rectangle drawBox=new Rectangle();
    public Circle judgeCircle;
    public Vector2 Center=new Vector2();
    public int hp=10;

    public void Init(float x,float y,float vx,float vy){
        Init(x,y,vx,vy,10);
    }

    public void Init(float x,float y,float vx,float vy,int hp){
        Drawer=Pools.ImagePool.obtain();
        Drawable drawable=getDrawable();
        Drawer.setDrawable(drawable );
        Killed=false;
        Center.set(x,y);
        this.vx=vx;
        this.vy=vy;
        this.hp=hp;
        judgeCircle=new Circle(Center,Drawer.getWidth()/2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
        for(int i=0;i<32;i++){
            if(enemys[i]==null){
                enemys[i]=this;
                break;
            }
        }
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
        return Center;
    }

    public void Kill(){
        Killed=true;
        Drawer.remove();
        judgeCircle=null;
        //将Drawer从舞台上撤下并且将其回归到Pool中
        Pools.ImagePool.free(Drawer);
    }

    public void Update(){
        time++;
        animTime++;
        move();
        anim();
        shoot();
        Drawer.setPosition(Center.x,Center.y,Align.center);
        Drawer.setRotation(getRotationDegree());
        judgeCircle.setPosition(Center.x,Center.y);
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
        if(!drawBox.overlaps(MainScreen.FightArea)){
            Kill();
        }else{
            Judge();
        }
    }

    protected abstract void anim();

    protected abstract void shoot();

    protected abstract void move();

    protected abstract Drawable getDrawable();

    public Shape2D getCollisionArea(){
        return judgeCircle;
    }

    public Shape2D getJudgeCircle(){
        return judgeCircle;
    }

    public void Judge(){
        if(getCollisionArea().contains(BaseMyPlane.Instance.Center)){
            hit();
            BaseMyPlane.Instance.Kill();
        }
    }

    public boolean isKilled(){
        return Killed;
    }

    public float getRotationDegree(){
        return 0;
    }
}
