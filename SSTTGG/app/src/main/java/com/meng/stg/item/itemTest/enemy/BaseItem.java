package com.meng.stg.item.itemTest.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.item.ItemType;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveManager;
import com.meng.stg.ui.MainScreen;

public class BaseItem extends BaseEnemyBullet{

    private int colorNum=0;
    private int formNum=0;

    public static void create(Vector2 center,ItemType type){
        ObjectPools.enemyBulletPool.obtain().init(center,type);
    }

    public void init(Vector2 center,ItemType type){
        super.init();
        objectCenter.set(center);
   //     moveManager=new MoveManager(this,mm);
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/2);
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }
	
    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("bullet"+(formNum*16+colorNum));
        }
        return drawable;
    }
}
