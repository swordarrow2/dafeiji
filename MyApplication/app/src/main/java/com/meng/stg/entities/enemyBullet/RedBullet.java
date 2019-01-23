package com.meng.stg.entities.enemyBullet;

import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.helpers.*;

public class RedBullet extends BaseBullet
{
	public static Pool<RedBullet> Pool = new Pool<RedBullet>()
			{
				@Override
				protected RedBullet newObject() {
					return new RedBullet();
				}
			};

	@Override
	protected Drawable getDrawable()
	{
		if (drawable == null)
			drawable = Resources.Textures.get("RedBullet");
		return drawable;
	}
}
