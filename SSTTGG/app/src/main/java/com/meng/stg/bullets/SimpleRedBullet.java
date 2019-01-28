package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.MainScreen;
import com.meng.stg.GameTextureManager;

public class SimpleRedBullet extends BaseEnemyBullet{


    public static Pool<SimpleRedBullet> Pool=new Pool<SimpleRedBullet>(){
        @Override
        protected SimpleRedBullet newObject(){
            return new SimpleRedBullet();
		  }
	  };

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
		thoughCount=0;
		refCount=0;
        objectCenter.set(center);
        this.velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,Drawer.getWidth()/2);
        MainScreen.MainGroup.addActor(Drawer);
	  }


    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=GameTextureManager.Textures.get(this.getClass().getSimpleName().replace(".class",""));
		  }
        return drawable;
	  }

  }
