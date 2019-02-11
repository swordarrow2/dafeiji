package com.meng.stg.boss.bossTask;

import com.meng.stg.boss.BaseBossPlane;

import java.util.HashMap;
import java.util.Random;

public class TaskManager{
    private Random ran=new Random();
    private TaskMode mode;
    private BaseBossPlane boss;
    private int holdingTime=0;
    private int addTaskFlag=0;
    private int getTaskFlag=0;
    private HashMap<Integer,Task> bossTask;

    public TaskManager(BaseBossPlane boss,TaskMode mode){
        this.mode=mode;
        this.boss=boss;
        bossTask=new HashMap<>();
    }

    public void addTask(Task t){
        bossTask.put(addTaskFlag,t);
        addTaskFlag++;
    }

    public void update(){
        switch(mode){
            case repeatAll:
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
                break;
            case repeatLast:
                if(getTaskFlag<addTaskFlag){
                    if(bossTask.get(getTaskFlag).holdingTime-holdingTime>0){
                        doTask(bossTask.get(getTaskFlag));
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
                    if(bossTask.get(getTaskFlag).holdingTime-holdingTime>0){
                        doTask(bossTask.get(getTaskFlag));
                    }else{
                        holdingTime=0;
                        getTaskFlag++;
                    }
                }
                break;
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
