package com.meng.stg.item.item;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.bullets.enemy.*;
import com.meng.stg.helpers.*;
import com.meng.stg.item.*;
import com.meng.stg.ui.*;

public class EnemyItem extends BaseItem{

    private int DrawableNumber=0;

	public static void create(Vector2 cpy,ItemType power){
		create(cpy,power,BulletKillMode.killWithScorePoint);
	  }
    
	public static void create(Vector2 center,ItemType bf,BulletKillMode bkm){
        ObjectPools.itemPool.obtain().init(center,bf,bkm);
	  }

	private void init(Vector2 center,ItemType bf,BulletKillMode bkm){		
        super.init();
        objectCenter.set(center);
		bulletKillMode=bkm;
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,image.getWidth()/2);
		itemType=bf;
        switch(bf){
            case power:
			  DrawableNumber=500  ;
			  size=new Vector2(16,16);
			  break;
            case powerBig:
			  DrawableNumber=504 ;
			  size=new Vector2(32,32);
			  break;
            case point:
			  DrawableNumber=502 ;
			  size=new Vector2(16,16);
			  break;
            case player:
			  DrawableNumber=508 ;
			  size=new Vector2(32,32);
			  break;
            case playerFragment:
			  DrawableNumber=506 ;
			  size=new Vector2(32,32);
			  break;
            case bomb:
			  DrawableNumber=512;
			  size=new Vector2(32,32);
			  break;
            case bombFragment:
			  DrawableNumber=510 ;
			  size=new Vector2(32,32);
			  break;
            case powerFull:
			  DrawableNumber=514 ;
			  size=new Vector2(32,32);
			  break;
            case powerPointer:
			  DrawableNumber=501 ;
			  size=new Vector2(16,16);
			  break;
            case powerBigPointer:
			  DrawableNumber=505 ;
			  size=new Vector2(32,32);
			  break;
            case pointPointer:
			  DrawableNumber=503 ;
			  size=new Vector2(16,16);
			  break;
            case playerPointer:
			  DrawableNumber=509 ;
			  size=new Vector2(32,32);
			  break;
            case playerFragmentPointer:
			  DrawableNumber=507;
			  size=new Vector2(32,32);
			  break;
            case bombPointer:
			  DrawableNumber=513 ;
			  size=new Vector2(32,32);
			  break;
            case bombFragmentPointer:
			  DrawableNumber=511 ;
			  size=new Vector2(32,32);
			  break;
            case powerFullPointer:
			  DrawableNumber=515 ;
			  size=new Vector2(32,32);
			  break;
            case highScoreSmall:
			  DrawableNumber=516;
			  size=new Vector2(16,16);
			  break;
            case highScoreMediam:
			  DrawableNumber=517 ;
			  size=new Vector2(16,16);
			  break;
            case highScoreLarge:
			  DrawableNumber=518 ;
			  size=new Vector2(16,16);
			  break;
		  }  
		size=getSize();
        image.setSize(size.x,size.y);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);  
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
