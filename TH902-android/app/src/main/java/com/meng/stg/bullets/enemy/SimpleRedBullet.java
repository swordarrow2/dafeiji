package com.meng.stg.bullets.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.meng.stg.ResourcesManager;
import com.meng.stg.ui.MainScreen;

public class SimpleRedBullet extends BaseEnemyBullet{

    public static Pool<SimpleRedBullet> Pool=new Pool<SimpleRedBullet>(){
        @Override
        protected SimpleRedBullet newObject(){
            return new SimpleRedBullet();
        }
    };

    public void Init(Vector2 center,Vector2 velocity){
        super.Init();
        objectCenter.set(center);
        this.velocity.set(velocity);
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/2);
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get(this.getClass().getSimpleName().replace(".class",""));
        }
        return drawable;
    }
}
