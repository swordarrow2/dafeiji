package com.meng.stg.planes.enemyPlane.bossTask;
import java.util.*;
import com.meng.stg.planes.enemyPlane.boss.*;

public class TaskManager{
	Random ran=new Random();

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
			if(bossTask.get(getTaskFlag).holdingTime-holdingTime>0){
				doTask(bossTask.get(getTaskFlag));				 	 
			  }else{
				holdingTime=0;
				getTaskFlag++;
			  }
		  }else{
			holdingTime=0;
			getTaskFlag=0;
		  }		
	  }
	public void doTask(Task t){
		if(t instanceof TaskMove){
			if(t.targetPosition.x==10000&&t.targetPosition.y==10000){
				boss.moveTo(ran.nextInt(250)+136,ran.nextInt(250)+150);
			  }else{
				boss.moveTo(t.targetPosition.x,t.targetPosition.y);
			  } 
		  }else if(t instanceof TaskShoot){
			for(int i=0;i<t.bulletShooter.length;++i){
				t.bulletShooter[i].shoot();
			  }	
		  }else if(t instanceof TaskRunnable){
			t.r.run();
		  }
		holdingTime++;
	  }
  }
