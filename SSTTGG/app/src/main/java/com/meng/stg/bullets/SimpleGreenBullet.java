package com.meng.stg.bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.helpers.Resources;

public class SimpleGreenBullet extends BaseEnemyBullet{

    public static Pool<SimpleGreenBullet> Pool=new Pool<SimpleGreenBullet>(){
        @Override
        protected SimpleGreenBullet newObject(){
            return new SimpleGreenBullet();
        }
    };

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
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=Resources.Textures.get("SimpleGreenBullet");
        }
        return drawable;
    }
}
