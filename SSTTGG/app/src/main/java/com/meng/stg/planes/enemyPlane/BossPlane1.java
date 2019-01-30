package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg.*;
import com.meng.stg.bullets.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.move.*;
import com.meng.stg.ui.*;
import java.util.*;

public class BossPlane1 extends BaseEnemyPlane{

    private boolean xx=false;
    private boolean yy=false;
    private HashMap<String,Drawable> bossAnim=new HashMap<String,Drawable>();
    private Drawable d=null;

	private String reimu="reimu";
    @Override
    protected Drawable getDrawable(){
        switch(animFlag%48){
            case 15:
			  d=bossAnim.get("anim1");
			  break;
            case 39:
			  d=bossAnim.get("anim3");
			  break;
		  }
        return d;
	  }

	@Override
    public Drawable getStayAnim(){
        switch(animFlag%32){
            case 1:
			  d=ResourcesManager.textures.get(reimu+0);
			  break;
            case 5:
			  d=ResourcesManager.textures.get(reimu+1);
			  break;
            case 9:
			  d=ResourcesManager.textures.get(reimu+2);
			  break;
            case 13:
			  d=ResourcesManager.textures.get(reimu+3);
			  break;
            case 17:
			  d=ResourcesManager.textures.get(reimu+4);
			  break;
            case 21:
			  d=ResourcesManager.textures.get(reimu+5);
			  break;
            case 25:
			  d=ResourcesManager.textures.get(reimu+6);
			  break;
            case 29:
			  d=ResourcesManager.textures.get(reimu+7);
			  break;
		  }
        return d;
	  }

    public Drawable getLeftMoveAnim(){
        switch(animFlag%32){
            case 1:
			  d=ResourcesManager.textures.get(reimu+8);
			  break;
            case 5:
			  d=ResourcesManager.textures.get(reimu+9);
			  break;
            case 9:
			  d=ResourcesManager.textures.get(reimu+10);
			  break;
            case 13:
			  d=ResourcesManager.textures.get(reimu+11);
			  break;
            case 17:
			  d=ResourcesManager.textures.get(reimu+12);
			  break;
            case 21:
			  d=ResourcesManager.textures.get(reimu+13);
			  break;
            case 25:
			  d=ResourcesManager.textures.get(reimu+14);
			  break;
            case 29:
			  d=ResourcesManager.textures.get(reimu+15);
			  break;
		  }
        return d;
	  }

    public Drawable getRightMoveAnim(){
        switch(animFlag%32){
            case 1:
			  d=ResourcesManager.textures.get(reimu+16);
			  break;
            case 5:
			  d=ResourcesManager.textures.get(reimu+17);
			  break;
            case 9:
			  d=ResourcesManager.textures.get(reimu+18);
			  break;
            case 13:
			  d=ResourcesManager.textures.get(reimu+19);
			  break;
            case 17:
			  d=ResourcesManager.textures.get(reimu+20);
			  break;
            case 21:
			  d=ResourcesManager.textures.get(reimu+21);
			  break;
            case 25:
			  d=ResourcesManager.textures.get(reimu+22);
			  break;
            case 29:
			  d=ResourcesManager.textures.get(reimu+23);
			  break;
		  }
        return d;
	  }

	@Override
	public void update(){
		// TODO: Implement this method
		super.update();
		move();
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
        image.setSize(128,128);
		new BulletShooter(this,new Vector2(0,-3));
		moveManager=new MoveManager(this,
									new MoveMethodStraight(1,new Vector2(0,0)));
	  }


    protected void move(){
        if(objectCenter.x>380){
            xx=true;
		  }else if(objectCenter.x<10){
            xx=false;
		  }
        if(objectCenter.y>430){
            yy=true;
		  }else if(objectCenter.y<100){
            yy=false;
		  }
        objectCenter.x=xx?objectCenter.x+vx:objectCenter.x-vx;
        objectCenter.y=yy?objectCenter.y+vy:objectCenter.y-vy;
	  }

    @Override
    protected void shoot(){
        if(time%30==31){
            int randVal=MathUtils.random(0,360);
            Vector2 vel=new Vector2(3,0);
            vel.rotate(randVal);
            for(int i=0;i<12;i++){
                SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,new MoveMethodStraight());
                vel.rotate(30);
			  }
		  }
	  }

    @Override
    protected void anim(){
        if(objectCenter.x>enemyLastX){
            enemyLastX=objectCenter.x;
            image.setDrawable(getRightMoveAnim());
		  }else if(objectCenter.x<enemyLastX){
            enemyLastX=objectCenter.x;
            image.setDrawable(getLeftMoveAnim());
		  }else{
            enemyLastX=objectCenter.x;
            image.setDrawable(getDrawable());
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
            SimpleRedBullet.create(objectCenter,BulletForm.ganjundan,BulletColor.purple,new MoveMethodStraight());
            vel.rotate(15);
		  }
        super.Kill();
	  }

  }
