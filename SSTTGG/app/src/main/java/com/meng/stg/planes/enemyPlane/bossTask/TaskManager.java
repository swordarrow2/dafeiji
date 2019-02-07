package com.meng.stg.planes.enemyPlane.bossTask;
import java.util.*;
import com.meng.stg.planes.enemyPlane.boss.*;

public class TaskManager{
	BaseBossPlane boss;
	int holdingTime=0;
	int addTaskFlag=0;
	int getTaskFlag=0;
	Task ta=null;
	public HashMap<Integer,Task> bossTask=new HashMap<>();

	public TaskManager(BaseBossPlane boss){
		this.boss=boss;
	  }
	public void addTask(Task t){
		bossTask.put(addTaskFlag,t);
		addTaskFlag++;
	  }
	public void update(){
		if(getTaskFlag<addTaskFlag){          
			if(bossTask.get(getTaskFlag).holdingTime>0){
				doTask(bossTask.get(getTaskFlag));				 	 
			  }else{
				getTaskFlag++;
			  }		
		  }   
	  }
	public void doTask(Task t){
		if(t instanceof TaskMove){
			boss.moveTo(t.targetPosition.x,t.targetPosition.y);
		  }else if(t instanceof TaskShoot){
			t.bulletShooter.shoot();
		  }
		bossTask.get(getTaskFlag).holdingTime--;

	  }
  }
