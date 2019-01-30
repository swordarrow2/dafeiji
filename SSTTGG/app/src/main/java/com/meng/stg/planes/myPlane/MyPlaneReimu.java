package com.meng.stg.planes.myPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.stg.ResourcesManager;
import com.meng.stg.bullets.myPlane.ReimuBomb;
import com.meng.stg.bullets.myPlane.ReimuShoot;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.subPlane.subPlaneReimu;

/*
 my plane
 */
public class MyPlaneReimu extends BaseMyPlane{

    private Drawable d=null;
    private String reimu="reimu";
    subPlaneReimu sp0, sp1,sp2,sp3;

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
    public void shoot(){
        if(existTime%3==1){
            Vector2 vel=new Vector2(0,47);
            ReimuShoot.Pool.obtain().Init(new Vector2(objectCenter.x+8,objectCenter.y+32),vel);
            ReimuShoot.Pool.obtain().Init(new Vector2(objectCenter.x-8,objectCenter.y+32),vel);
		  }
	  }

    @Override
    public void Init(){
        super.Init();
        bombTime=Data.ReimuBombTime;
        sp0=new subPlaneReimu();
        sp0.init(this,0);
        sp1=new subPlaneReimu();
        sp1.init(this,1);
		sp2=new subPlaneReimu();
        sp2.init(this,2);
        sp3=new subPlaneReimu();
        sp3.init(this,3);
	  }

    @Override
    public void Kill(){
        super.Kill();
        sp0.kill();
        sp1.kill();
		sp2.kill();
        sp3.kill();
        Pools.imagePool.free(image);
        new MyPlaneReimu().Init();
	  }

    @Override
    public void update(){
        super.update();
		sp0.update();
        sp1.update();
        sp2.update();
		sp3.update();
	  }


    @Override
    public void bomb(){
        Vector2 vel=new Vector2(0,30);
        if(bombTime%16==0){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x,0),vel);
		  }
        if(bombTime%16==4){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+20,0),vel);
		  }
        if(bombTime%16==8){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-40,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+40,0),vel);
		  }
        if(bombTime%16==12){
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x-20,0),vel);
            ReimuBomb.Pool.obtain().Init(new Vector2(objectCenter.x+20,0),vel);
		  }
	  }
  }
