package com.meng.stg.item.item;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.ui.MainScreen;

public class EnemyItem extends BaseEnemyBullet{

    private ItemType type=ItemType.power;
    private Vector2 size=new Vector2();
    private int DrawableNumber=0;

    public static void create(Vector2 center,ItemType bf){
        ObjectPools.itemPool.obtain().init(center,bf);
    }

    public void init(Vector2 center,ItemType bf){
        super.init();
        objectCenter.set(center);
        //  moveManager=new MoveManager(this,mm);
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/2);

        switch(bf){
            case power:
                DrawableNumber=500  ;
                size=new Vector2(16,16);
                break;
            case powerBig:
                DrawableNumber= 504 ;
                size=new Vector2(32,32);
                break;
            case point:
                DrawableNumber= 502 ;
                size=new Vector2(16,16);
                break;
            case player:
                DrawableNumber= 508 ;
                size=new Vector2(32,32);
                break;
            case playerFragment:
                DrawableNumber= 506 ;
                size=new Vector2(32,32);
                break;
            case bomb:
                DrawableNumber=  512;
                size=new Vector2(32,32);
                break;
            case bombFragment:
                DrawableNumber= 510 ;
                size=new Vector2(32,32);
                break;
            case powerFull:
                DrawableNumber= 514 ;
                size=new Vector2(32,32);
                break;
            case powerPointer:
                DrawableNumber= 501 ;
                size=new Vector2(16,16);
                break;
            case powerBigPointer:
                DrawableNumber= 505 ;
                size=new Vector2(32,32);
                break;
            case pointPointer:
                DrawableNumber= 503 ;
                size=new Vector2(16,16);
                break;
            case playerPointer:
                DrawableNumber= 509 ;
                size=new Vector2(32,32);
                break;
            case playerFragmentPointer:
                DrawableNumber=  507;
                size=new Vector2(32,32);
                break;
            case bombPointer:
                DrawableNumber= 513 ;
                size=new Vector2(32,32);
                break;
            case bombFragmentPointer:
                DrawableNumber= 511 ;
                size=new Vector2(32,32);
                break;
            case powerFullPointer:
                DrawableNumber= 515 ;
                size=new Vector2(32,32);
                break;
            case highScoreSmall:
                DrawableNumber=  516;
                size=new Vector2(16,16);
                break;
            case highScoreMediam:
                DrawableNumber= 517 ;
                size=new Vector2(16,16);
                break;
            case highScoreLarge:
                DrawableNumber= 518 ;
                size=new Vector2(16,16);
                break;
        }
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public Vector2 getSize(){
        return size;
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("item"+DrawableNumber);
        }
        return drawable;
    }
}
