package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BulletShooter;
import com.meng.stg.GameTextureManager;

public class EnemyPlane extends BaseEnemyPlane{

  Drawable d;
  String reimu="zayu";
    @Override
    protected Drawable getDrawable(){
        return GameTextureManager.Textures.get("EnemyPlane");
    }

    @Override
    protected void shoot(){
    //    if(time%42==1){
     //       Vector2 vel=new Vector2(0,-5);
     //       SimpleRedBullet.Pool.obtain().Init(objectCenter,vel);
    //    }

     //   if(time%42==21){
     //       Vector2 vel=new Vector2(0,-5);
     //       SimpleGreenBullet.Pool.obtain().Init(objectCenter,vel);
     //   }
	  }
	  
	@Override
    public Drawable getStayAnim(){
        switch(animFlag%32){
            case 1:
			  d=GameTextureManager.Textures.get(reimu+12);
			  break;
            case 7:
			  d=GameTextureManager.Textures.get(reimu+13);
			  break;
            case 14:
			  d=GameTextureManager.Textures.get(reimu+14);
			  break;
            case 21:
			  d=GameTextureManager.Textures.get(reimu+15);
			  break;
            case 28:
			  d=GameTextureManager.Textures.get(reimu+16);
			  break;
            
		  }
        return d;
	  }

    public Drawable getLeftMoveAnim(){
        switch(animFlag%32){
            case 1:
			  d=GameTextureManager.Textures.get(reimu+17);
			  break;
            case 5:
			  d=GameTextureManager.Textures.get(reimu+18);
			  break;
            case 10:
			  d=GameTextureManager.Textures.get(reimu+19);
			  break;
            case 15:
			  d=GameTextureManager.Textures.get(reimu+20);
			  break;
            case 20:
			  d=GameTextureManager.Textures.get(reimu+21);
			  break;
            case 25:
			  d=GameTextureManager.Textures.get(reimu+22);
			  break;
            case 30:
			  d=GameTextureManager.Textures.get(reimu+23);
			  break;
		  }
        return d;
	  }

    public Drawable getRightMoveAnim(){
        switch(animFlag%32){
            case 1:
			  d=GameTextureManager.Textures.get(reimu+17);
			  break;
            case 5:
			  d=GameTextureManager.Textures.get(reimu+18);
			  break;
            case 10:
			  d=GameTextureManager.Textures.get(reimu+19);
			  break;
            case 15:
			  d=GameTextureManager.Textures.get(reimu+20);
			  break;
            case 20:
			  d=GameTextureManager.Textures.get(reimu+21);
			  break;
            case 25:
			  d=GameTextureManager.Textures.get(reimu+22);
			  break;
            case 30:
			  d=GameTextureManager.Textures.get(reimu+23);
			  break;
		  }
        return d;
	  }

	@Override
	public void Init(float x,float y,float vx,float vy,int hp){
		// TODO: Implement this method
		super.Init(x,y,vx,vy,hp);
		Drawer.setSize(32,32);
	  }
	

    @Override
    public void Init(float x,float y,float vx,float vy){
        super.Init(x,y,vx,vy);
       BulletShooter bs=new BulletShooter(this,new Vector2(0,-3));
    }

    @Override
    protected void anim(){

    }
}
