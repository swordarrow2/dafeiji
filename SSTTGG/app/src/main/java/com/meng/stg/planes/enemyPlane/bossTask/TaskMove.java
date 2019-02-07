package com.meng.stg.planes.enemyPlane.bossTask;

import com.badlogic.gdx.math.*;

public class TaskMove extends Task{
	
	public TaskMove(int holdingTime,float x,float y){
		this.holdingTime=holdingTime;
		targetPosition.x=x;
		targetPosition.y=y;
	  }
  }
