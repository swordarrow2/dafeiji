package com.meng.stg.item;
import com.badlogic.gdx.math.*;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.helpers.ObjectPools;
import com.meng.stg.move.*;

public class ItemCreator{
	private ItemType type=ItemType.power;
	private Vector2 itemCenter=new Vector2();
	private MoveManager moveManager=null;
	private BaseItem baseItem=null;

	public static void create(Vector2 center,BulletForm bf,BulletColor bc,int ref,BaseMoveMethod... mm){
		ObjectPools.enemyBulletPool.obtain().init(center,bf,bc,ref,mm);
	}

	public ItemCreator setType(ItemType type){
		this.type=type;
		return this;
	}

	public ItemCreator setItemCenter(Vector2 itemCenter){
		this.itemCenter=itemCenter;
		return this;
	}

}
