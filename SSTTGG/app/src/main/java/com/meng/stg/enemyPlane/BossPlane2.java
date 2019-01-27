package com.meng.stg.enemyPlane;

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
import com.meng.stg.helpers.*;

public class BossPlane2 extends BaseEnemyPlane{

	@Override
	protected Drawable getDrawable(){
		return getStayAnim();
	  }


    private boolean xx=false;
    private boolean yy=false;
    private HashMap<String,Drawable > bossAnim=new HashMap<String,Drawable >();
    private Drawable  d=null;

	String reimu="zayu";
	@Override
    public Drawable getStayAnim(){
        switch(animTime%32){
            case 1:
			  d=Resources.Textures.get(reimu+96);
			  break;
            case 5:
			  d=Resources.Textures.get(reimu+97);
			  break;
            case 9:
			  d=Resources.Textures.get(reimu+98);
			  break;
            case 13:
			  d=Resources.Textures.get(reimu+99);
			  break;
            case 17:
			  d=Resources.Textures.get(reimu+100);
			  
		  }
        return d;
	  }

    public Drawable getLeftMoveAnim(){
        switch(animTime%32){
            case 1:
			  d=Resources.Textures.get(reimu+101);
			  break;
            case 5:
			  d=Resources.Textures.get(reimu+102);
			  break;
            case 9:
			  d=Resources.Textures.get(reimu+103);
			  break;
            case 13:
			  d=Resources.Textures.get(reimu+104);
			  break;
            case 17:
			  d=Resources.Textures.get(reimu+105);
			  break;
            case 21:
			  d=Resources.Textures.get(reimu+106);
			  break;
            case 25:
			  d=Resources.Textures.get(reimu+107);
			  break;
            
		  }
        return d;
	  }

    public Drawable getRightMoveAnim(){
        
        return getLeftMoveAnim();
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
