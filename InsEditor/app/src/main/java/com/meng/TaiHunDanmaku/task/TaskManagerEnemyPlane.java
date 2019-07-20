package com.meng.TaiHunDanmaku.task;


import com.badlogic.gdx.math.Vector2;
import com.meng.TaiHunDanmaku.baseObjects.bullets.*;
import com.meng.TaiHunDanmaku.helpers.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.enemyPlane.*;
import com.meng.TaiHunDanmaku.baseObjects.planes.myPlane.BaseMyPlane;

import java.util.*;

public class TaskManagerEnemyPlane {
    private TaskRepeatMode repeatMode;
    private BaseBossPlane enemy = null;
    private int holdingTime = 0;
    private int addTaskFlag = 0;
    private int getTaskFlag = 0;
    private HashMap<Integer, Task> tasks;

    public TaskManagerEnemyPlane(BaseBossPlane enemy, TaskRepeatMode repeatMode) {
        this.repeatMode = repeatMode;
        this.enemy = enemy;
        tasks = new HashMap<Integer, Task>();
	  }

    public void addTask(Task t) {
        tasks.put(addTaskFlag, t);
        addTaskFlag++;
	  }

    public void update() {
        switch (repeatMode) {
            case repeatAll:
			  if (getTaskFlag < addTaskFlag) {
				  if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
					  doTask(tasks.get(getTaskFlag));
                    } else {
					  holdingTime = 0;
					  getTaskFlag++;
                    }
                } else {
				  holdingTime = 0;
				  getTaskFlag = 0;
                }
			  break;
            case repeatLast:
			  if (getTaskFlag < addTaskFlag) {
				  if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
					  doTask(tasks.get(getTaskFlag));
                    } else {
					  holdingTime = 0;
					  getTaskFlag++;
                    }
                } else {
				  holdingTime = 0;
				  getTaskFlag = addTaskFlag - 1;
                }
			  break;
            case noRepeat:
			  if (getTaskFlag < addTaskFlag) {
				  if (tasks.get(getTaskFlag).holdingTime - holdingTime > 0) {
					  doTask(tasks.get(getTaskFlag));
                    } else {
					  holdingTime = 0;
					  getTaskFlag++;
                    }
                }
			  break;
		  }
	  }

    public void doTask(Task task) {

        if (task instanceof TaskMoveTo) {
            if (task.tmpVector2.x == 10000 && task.tmpVector2.y == 10000) {
                enemy.moveTo(ObjectPools.randomPool.nextInt(250) + 136, ObjectPools.randomPool.nextInt(250) + 150);
			  } else {
                enemy.moveTo(task.tmpVector2.x, task.tmpVector2.y);
			  }
		  } else if (task instanceof TaskShoot) {
            for (int i = 0; i < task.bulletShooter.length; ++i) {
                task.bulletShooter[i].shoot();
			  }
		  } else if (task instanceof TaskRunnable) {
            task.runnable.run();
		  }
        holdingTime++;
	  }
  }

