package com.meng.stg.entities;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.*;
import com.meng.stg.helpers.*;

import com.meng.stg.helpers.Pools;

public abstract class BaseObject
{
	public Vector2 Center = new Vector2();
	public Vector2 Velocity = new Vector2();
	public Image Drawer = null;
	public int ExistTime;
	public void Init()
	{
		//获取一个Image
		Drawer = Pools.ImagePool.obtain();
		Drawable drawable = getDrawable();
		//设置材质
		Drawer.setDrawable(drawable);
		//加入舞台中
		MainScreen.MainGroup.addActor(Drawer);
		ExistTime = 0;
	}
	public void Kill()
	{
		Drawer.remove();
		//将Drawer从舞台上撤下并且将其回归到Pool中
		Pools.ImagePool.free(Drawer);
	}
	public void Update()
	{
		//更新位置
		Center.add(Velocity);
		Drawer.setPosition(Center.x, Center.y, Align.center);
		ExistTime++;
	}
	protected abstract Drawable getDrawable();
}
