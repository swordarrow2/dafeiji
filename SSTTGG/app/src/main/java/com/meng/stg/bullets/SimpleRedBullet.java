package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Resources;
import com.meng.stg.enemyPlane.*;

public class SimpleRedBullet extends BaseEnemyBullet{

	private MoveCtrl mvc;

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
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(Center,Drawer.getWidth()/2);
        MainScreen.MainGroup.addActor(Drawer);
		mvc=new MoveCtrl(this,
						 new MoveMethod(60,new Vector2(0,-5)),
						 new MoveMethod(20,new Vector2(1,9)),
						 new MoveMethod(20,new Vector2(-5,-9)), 
						 new MoveMethod(20,new Vector2(-1,3)),
						 new MoveMethod(10,new Vector2(-1,-21))
						 );
	  }

	@Override
	public void Update(){
		super.Update();
		mvc.update();
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get(this.getClass().getSimpleName().replace(".class",""));
		  }
        return drawable;
	  }

  }
