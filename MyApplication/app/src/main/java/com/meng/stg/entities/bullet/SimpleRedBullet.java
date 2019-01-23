package com.meng.stg.entities.bullet;

import android.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.entities.*;
import com.meng.stg.helpers.*;

public class SimpleRedBullet extends baseEnemyObject
{
  public static int bulletCount=0;
	public static Pool<SimpleRedBullet> Pool = new Pool<SimpleRedBullet>()
			{
				@Override
				protected SimpleRedBullet newObject() {
					return new SimpleRedBullet();
				}
			};
	Circle judgeCircle;
	static Drawable drawable;
	public Vector2 Size = new Vector2();
	public void Init(Vector2 center, Vector2 velocity)
	{
		super.Init();
		Size.set(24, 24);
		Drawer.setSize(Size.x, Size.y);
		judgeCircle = new Circle(Center, Drawer.getWidth() / 2); //中心、半径
		Center.set(center);
		Velocity.set(velocity);
		Drawer.setPosition(center.x, center.y, Align.center);
		bulletCount++;
	}
	@Override
	public void Update()
	{
		judgeCircle.setPosition(Center);
		super.Update();
	}
	@Override
	protected Shape2D getCollisionArea()
	{
		return judgeCircle;
	}
	@Override
	protected Drawable getDrawable()
	{
		if (drawable == null)
			drawable = Resources.Textures.get("SimpleRedBullet");
		return drawable;
	}
}
