package com.meng.stg2.task;

import java.util.HashMap;
import java.util.Random;

import com.meng.stg2.bullets.BaseEnemyBullet;
import com.meng.stg2.planes.enemyPlane.*;

public class TaskManager{
    private Random ran=new Random();
    private TaskRepeatMode repeatMode;
    private BaseEnemyPlane enemy=null;
    private BaseEnemyBullet bullet=null;
    private int holdingTime=0;
    private int addTaskFlag=0;
    private int getTaskFlag=0;
    private HashMap<Integer,Task> tasks;

    public TaskManager(BaseEnemyPlane enemy,TaskRepeatMode repeatMode){
        this.repeatMode=repeatMode;
        this.enemy=enemy;
        tasks=new HashMap<>();
    }

    public TaskManager(BaseEnemyBullet bullet,TaskRepeatMode repeatMode){
        this.repeatMode=repeatMode;
        this.bullet=bullet;
        tasks=new HashMap<>();
    }

    public void addTask(Task t){
        tasks.put(addTaskFlag,t);
        addTaskFlag++;
    }

    public void update(){
        switch(repeatMode){
            case repeatAll:
                if(getTaskFlag<addTaskFlag){
                    if(tasks.get(getTaskFlag).holdingTime-holdingTime>0){
                        doTask(tasks.get(getTaskFlag));
                    }else{
                        holdingTime=0;
                        getTaskFlag++;
                    }
                }else{
                    holdingTime=0;
                    getTaskFlag=0;
                }
                break;
            case repeatLast:
                if(getTaskFlag<addTaskFlag){
                    if(tasks.get(getTaskFlag).holdingTime-holdingTime>0){
                        doTask(tasks.get(getTaskFlag));
                    }else{
                        holdingTime=0;
                        getTaskFlag++;
                    }
                }else{
                    holdingTime=0;
                    getTaskFlag=addTaskFlag-1;
                }
                break;
            case noRepeat:
                if(getTaskFlag<addTaskFlag){
                    if(tasks.get(getTaskFlag).holdingTime-holdingTime>0){
                        doTask(tasks.get(getTaskFlag));
                    }else{
                        holdingTime=0;
                        getTaskFlag++;
                    }
                }
                break;
        }


    }

    public void doTask(Task t){
        if(enemy==null){
            if(t instanceof TaskMove){
                if(t.targetPosition.x==10000&&t.targetPosition.y==10000){
                    bullet.moveTo(ran.nextInt(250)+136,ran.nextInt(250)+150);
                }else{
                    bullet.moveTo(t.targetPosition.x,t.targetPosition.y);
                }
            }
        }else {
            if(t instanceof TaskMove){
                if(t.targetPosition.x==10000&&t.targetPosition.y==10000){
                    enemy.moveTo(ran.nextInt(250)+136,ran.nextInt(250)+150);
                }else{
                    enemy.moveTo(t.targetPosition.x,t.targetPosition.y);
                }
            }else if(t instanceof TaskShoot){
                for(int i=0;i<t.bulletShooter.length;++i){
                    t.bulletShooter[i].shoot();
                }
            }else if(t instanceof TaskRunnable){
                t.r.run();
            }
        }
        holdingTime++;
    }
}
