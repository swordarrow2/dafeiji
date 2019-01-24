package com.meng.stg.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.stg.MainScreen;
import com.meng.stg.helpers.Pools;

public abstract class BaseBullet
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
		//if (!MainScreen.gameOver) {
			Center.add(Velocity);
		//}
		Drawer.setPosition(Center.x, Center.y, Align.center);
		ExistTime++;
	}
	protected abstract Drawable getDrawable();
}
