package com.meng.stg.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Data;
import com.meng.stg.helpers.Pools;

public abstract class BaseMyPlane
{
	public Vector2 Center = new Vector2();
	public Vector2 Velocity = new Vector2();
	public Image Drawer = null;
	public int ExistTime;

	public static float x = 0;
	public static float y = 0;
	public static int missTime = 0;
	public int unmatchedTime;
	public boolean onUnmatched =false;
	public int bombTime;
	public boolean onBomb=false;

	public static int animTime = 0;
	public void Init()
	{

		if (!MainScreen.gameOver) {
            //获取一个Image
            Drawer = Pools.ImagePool.obtain();
            Drawable drawable = getDrawable();
            //设置材质
            Drawer.setDrawable(drawable);
            //加入舞台中
            MainScreen.MainGroup.addActor(Drawer);
            ExistTime = 0;
        }
	}
	public void Kill()
	{

	}
	public void Update()
	{
		//更新位置
		Center.add(Velocity);
		Drawer.setPosition(Center.x, Center.y, Align.center);
		ExistTime++;
		if(onBomb){
			onUnmatched =true;
			bomb();
			bombTime--;
		}
		if (onUnmatched){
		unmatchedTime--;
	}
	onUnmatched=false;
	}
	public void bomb(){

	}

	public float[] getPosition(){
		return new float[]{Center.x,Center.y};
	};
	protected abstract Drawable getDrawable();
}
