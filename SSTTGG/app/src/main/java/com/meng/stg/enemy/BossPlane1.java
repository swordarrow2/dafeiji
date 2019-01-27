package com.meng.stg.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meng.stg.MainScreen;
import com.meng.stg.bullets.SimpleRedBullet;

import java.util.HashMap;

public class BossPlane1 extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private HashMap<String,Drawable> bossAnim=new HashMap<String,Drawable>();
    private Drawable d=null;

    @Override
    protected Drawable getDrawable(){
        switch(animTime%48){
            case 15:
                d=bossAnim.get("anim1");
                break;
            case 39:
                d=bossAnim.get("anim3");
                break;
        }
        return d;
    }

    private Drawable getRightMoveAnim(){
        switch(animTime%48){
            case 15:
                d=bossAnim.get("anim6");
                break;
            case 39:
                d=bossAnim.get("anim9");
                break;
        }
        return d;
    }

    private Drawable getLeftMoveAnim(){
        switch(animTime%48){
            case 15:
                d=bossAnim.get("anim14");
                break;
            case 39:
                d=bossAnim.get("anim11");
                break;
        }
        return d;
    }

    @Override
    public void Init(float x,float y,float vx,float vy,int hp){
        super.Init(x,y,vx,vy,hp);
        Texture walkSheet=new Texture(Gdx.files.internal("textures/enemy/boss1.png"));
        int FRAME_COLS=5;
        int FRAME_ROWS=3;
        TextureRegion[][] tmp=TextureRegion.split(walkSheet,walkSheet.getWidth()/FRAME_COLS,walkSheet.getHeight()/FRAME_ROWS);
        int index=0;
        for(int i=0;i<FRAME_ROWS;i++){
            for(int j=0;j<FRAME_COLS;j++){
                TextureRegionDrawable drawable=new TextureRegionDrawable(tmp[i][j]);
                bossAnim.put("anim"+index,drawable);
                index++;
            }
        }
        Drawer.setSize(128,128);
    }

    @Override
    protected void move(){
        if(Center.x>500){
            xx=true;
        }else if(Center.x<50){
            xx=false;
        }
        if(Center.y>700){
            yy=true;
        }else if(Center.y<400){
            yy=false;
        }
        Center.x=xx?Center.x+vx:Center.x-vx;
        Center.y=yy?Center.y+vy:Center.y-vy;
    }

    @Override
    protected void shoot(){
        if(time%30==1){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                SimpleRedBullet.Pool.obtain().Init(Center,vel);
                vel.rotate(30);
            }
        }
    }

    @Override
    protected void anim(){
        if(Center.x>enemyLastX){
            enemyLastX=Center.x;
            Drawer.setDrawable(getRightMoveAnim() );
        }else if(Center.x<enemyLastX){
            enemyLastX=Center.x;
            Drawer.setDrawable(getLeftMoveAnim() );
        }else{
            enemyLastX=Center.x;
            Drawer.setDrawable(getDrawable() );
        }
    }

    @Override
    public void Kill(){
        Killed=true;
        MainScreen.onBoss=false;
        int randVal=MathUtils.random(0,360);
        Vector2 vel=new Vector2(15,0);
        vel.rotate(randVal);
        for(int i=0;i<24;i++){
            SimpleRedBullet.Pool.obtain().Init(Center,vel);
            vel.rotate(15);
        }
        super.Kill();
    }

}
