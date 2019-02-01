package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.ui.*;
import java.util.*;
import com.meng.stg.move.*;

public class BossPlane2 extends BaseEnemyPlane{

	

    private boolean xx=false;
    private boolean yy=false;
    private HashMap<String,Drawable > bossAnim=new HashMap<String,Drawable >();
    private Drawable  d=null;

	String reimu="zayu";
	
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
        image.setSize(128,128);
    }

    
    protected void move(){
        if(objectCenter.x>500){
            xx=true;
        }else if(objectCenter.x<50){
            xx=false;
        }
        if(objectCenter.y>700){
            yy=true;
        }else if(objectCenter.y<400){
            yy=false;
        }
        objectCenter.x=xx?objectCenter.x+vx:objectCenter.x-vx;
        objectCenter.y=yy?objectCenter.y+vy:objectCenter.y-vy;
    }

    @Override
    protected void shoot(){
        if(time%30==1){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight(1,new Vector2(0,-1)));
                vel.rotate(30);
            }
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
            SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,0,new MoveMethodStraight(1,new Vector2(0,-1)));
            vel.rotate(15);
        }
        super.Kill();
    }

}
