package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.helpers.Resources;

public class SimpleGreenBullet extends Projectile{
    public static Pool<SimpleGreenBullet> Pool = new Pool<SimpleGreenBullet>(1000) {
        @Override
        protected SimpleGreenBullet newObject(){
            return new SimpleGreenBullet();
		  }
	  };
    Circle judgeCircle;
    static Drawable drawable;
    public Vector2 Size = new Vector2();
    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        Size.set(24,24);
        Drawer.setSize(Size.x,Size.y);
        judgeCircle=new Circle(Center,Drawer.getWidth()/2); //中心、半径
        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x,center.y,Align.center);
        bullet.bulletCount++;
	  }

    @Override
    public void Update(){
        judgeCircle.setPosition(Center);
        super.Update();
	  }

    @Override
    protected Shape2D getCollisionArea(){
        return judgeCircle;
	  }

    @Override
    protected Drawable getDrawable(){
        if(drawable==null){
			//   if (bullet.color==bullet.colorRed) {
			drawable=Resources.Textures.get("SimpleGreenBullet");
			//   }
		  }
        return drawable;
	  }

    public void Judge(){
		//  if (getCollisionArea().contains(Player.Instance.Center))
		//  {
		//      Player.Instance.Kill();
		//      Kill();
		//  }
	  }
    @Override
    public void Kill(){
        ToDelete.add(this);
        bullet.bulletCount--;
        //在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
        super.Kill();
	  }
  }
