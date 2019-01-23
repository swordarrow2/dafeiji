package com.meng.stg.entities;

import com.badlogic.gdx.math.*;
import com.meng.stg.*;
import java.util.*;
import java.util.concurrent.*;
import com.meng.stg.entities.bullet.*;

public abstract class baseEnemyObject extends BaseObject
{
	public static HashSet<baseEnemyObject> Instances = new HashSet<baseEnemyObject>();
	public static LinkedBlockingQueue<baseEnemyObject> ToDelete = new LinkedBlockingQueue<baseEnemyObject>();
	public static LinkedBlockingQueue<baseEnemyObject> ToAdd = new LinkedBlockingQueue<baseEnemyObject>();
	//建立一个对所有Projectile的引用
	
	protected Rectangle drawBox = new Rectangle();
	@Override
	public void Init()
	{
		super.Init();
		ToAdd.add(this);
	}
	@Override
	public void Update()
	{
		drawBox.set(Drawer.getX(), Drawer.getY(), Drawer.getWidth(), Drawer.getHeight());
		if (!drawBox.overlaps(MainScreen.FightArea))
			Kill();
		super.Update();
		Judge();
	}
	
	protected abstract Shape2D getCollisionArea();
	
	public void Judge()
	{
		if (getCollisionArea().contains(basePlayer.Instance.Center))
		{
			basePlayer.Instance.Kill();
		}
	}
	
	@Override
	public void Kill()
	{
		ToDelete.add(this);
		//在这里直接remove会报ConcurrentModification异常，加入队列中等待下一帧开始时处理
		if(this instanceof SimpleRedBullet){
		  SimpleRedBullet.bulletCount--;
		}
		super.Kill();
	}
	
	public static void UpdateAll()
	{
		while (!ToDelete.isEmpty())
			Instances.remove(ToDelete.poll());
		while (!ToAdd.isEmpty())
			Instances.add(ToAdd.poll());
		for (baseEnemyObject projectile : Instances)
		{
			projectile.Update();
		}
	}
}
