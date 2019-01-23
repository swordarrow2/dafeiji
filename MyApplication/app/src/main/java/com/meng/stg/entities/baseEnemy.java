package com.meng.stg.entities;

public abstract class BaseEnemy extends BaseEnemyObject{
	public int Life;
	@Override
	public void Init() {
		super.Init();
		Life = GetMaxLife();
	}
	@Override
	public void Update() {
		super.Update();
		if (Life <= 0) {
			Kill();
		}
	}
	public abstract int GetMaxLife();
}
