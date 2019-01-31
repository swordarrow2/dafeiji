package com.meng.stg.planes.enemyPlane;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.bullets.BulletShooter;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;

public class EnemyPlane extends BaseEnemyPlane{

	@Override
	protected void shoot(){
		// TODO: Implement this method
	  }


  Drawable d;
  String reimu="zayu";

    public Drawable getRightMoveAnim(){
        switch(animFlag%32){
            case 1:
			  d=ResourcesManager.textures.get(reimu+17);
			  break;
            case 5:
			  d=ResourcesManager.textures.get(reimu+18);
			  break;
            case 10:
			  d=ResourcesManager.textures.get(reimu+19);
			  break;
            case 15:
			  d=ResourcesManager.textures.get(reimu+20);
			  break;
            case 20:
			  d=ResourcesManager.textures.get(reimu+21);
			  break;
            case 25:
			  d=ResourcesManager.textures.get(reimu+22);
			  break;
            case 30:
			  d=ResourcesManager.textures.get(reimu+23);
			  break;
		  }
        return d;
	  }

	@Override
	public void Init(float x,float y,float vx,float vy,int hp){
		super.Init(x,y,vx,vy,hp);
		image.setSize(32,32);
	  }
	

    @Override
    public void Init(float x,float y,float vx,float vy){
        super.Init(x,y,vx,vy);
       new BulletShooter(this).setBulletCenter(objectCenter).setBulletColor(BulletColor.red).setBulletForm(BulletForm.liandan).setStraightMove(true).setWays(3).setWaysDegree(10).setInFrame(10);
    }

    @Override
    protected void anim(){

    }
}
