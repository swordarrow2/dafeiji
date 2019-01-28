package com.meng.stg.planes.subPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;

import com.meng.stg.helpers.Pools;
import com.meng.stg.planes.myPlane.BaseMyPlane;

public abstract class BaseSubPlane
{
	public Vector2 Center=new Vector2();
    public Vector2 size=new Vector2();
    public Image Drawer=null;
    public int ExistTime;
    protected Rectangle drawBox=new Rectangle();

    public Drawable drawable=null;
	public int num=0;

    public void Init(int n){
	  num=n;
        size=getSize();
        Drawer=Pools.ImagePool.obtain();
        Drawable da=getDrawable();
        Drawer.setDrawable(da);
        Drawer.setSize(size.x,size.y);
		Drawer.setRotation(getRotationDegree());
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        ExistTime=0;
		MainScreen.MainGroup.addActor(Drawer);
	  }

    public void Kill(){
        Drawer.setRotation(0);
        Drawer.remove();
        Pools.ImagePool.free(Drawer);
	  }

    public void Update(){
        ExistTime++;
		switch(num){
		  case 0:
			  Center=BaseMyPlane.Instance.objectCenter.cpy().add(-32,0);
			break;
			case 1:
			  Center=BaseMyPlane.Instance.objectCenter.cpy().add(32,0);
			  break;
		}
		Drawer.setDrawable(getDrawable());
		Drawer.setRotation(getRotationDegree());
        Drawer.setPosition(Center.x,Center.y,Align.center);
		Drawer.setOrigin(Drawer.getWidth()/2,Drawer.getHeight()/2);
        drawBox.set(Drawer.getX(),Drawer.getY(),Drawer.getWidth(),Drawer.getHeight());
		shoot();
	  }


    public abstract Drawable getDrawable();

    public abstract float getRotationDegree();

    public abstract Vector2 getSize();
	
	public abstract void shoot();
  
}
