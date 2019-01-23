package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Resources;
import com.meng.stg.player.Player;

public class SimpleRedBullet extends Projectile {

   public static Pool<SimpleRedBullet> Pool = new Pool<SimpleRedBullet>() {
        @Override
        protected SimpleRedBullet newObject() {
            return new SimpleRedBullet();
        }
  };

    static Drawable drawable;
    public Vector2 Size = new Vector2();
    public void Init(Vector2 center, Vector2 velocity) {
        super.Init();
        Size.set(24, 24);
        Drawer.setSize(Size.x, Size.y);

        Center.set(center);
        Velocity.set(velocity);
        Drawer.setPosition(center.x, center.y, Align.center);
        judgeCircle = new Circle(Center, Drawer.getWidth() / 2); //中心、半径
        MainScreen.MainGroup.addActor(Drawer);
    }
public SimpleRedBullet createBullet(Vector2 center, Vector2 velocity){
   Pool.obtain().Init(center,velocity);
    return this;
}
    @Override
    public void Update() {
        super.Update();
    }

    @Override
    protected Shape2D getCollisionArea() {
        return judgeCircle;
    }

    @Override
    protected Drawable getDrawable() {
        if (drawable == null) {
                drawable = Resources.ProjDrawables.get("SimpleRedBullet");
        }
        return drawable;
    }
    public void Kill()
    {
       // ToDelete.add(this);
        //在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
        super.Kill();
    }
}
