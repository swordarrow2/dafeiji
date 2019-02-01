package com.meng.stg.planes;

import com.badlogic.gdx.graphics.Color;
import com.meng.stg.helpers.ResourcesManager;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;
import com.meng.stg.planes.enemyPlane.EnemyDahudie;
import com.meng.stg.planes.enemyPlane.EnemyXiaozayu;

public class EnemyAnimationManager{

    private BaseEnemyPlane baseEnemyPlane;
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;
    private String objectName="";
    private boolean flip=false;
    private int[][] animNum;
    private Color color=Color.RED;
    private final int[][] dahudieAnim=new int[][]{
            {96,100},
            {101,107}
    };
    private final int[][] xiaozayuAnimLan=new int[][]{
            {0,5},
            {5,11}
    };
    private final int[][] xiaozayuAnimHong=new int[][]{
            {12,17},
            {17,23}
    };
    private final int[][] xiaozayuAnimLv=new int[][]{
            {24,29},
            {29,35}
    };
    private final int[][] xiaozayuAnimHuang=new int[][]{
            {36,41},
            {41,47}
    };

    public EnemyAnimationManager(BaseEnemyPlane obj,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;
        baseEnemyPlane=obj;
        objectName="zayu";
        if(obj instanceof EnemyXiaozayu){
            if(color.equals(Color.BLUE)){
                animNum=xiaozayuAnimLan;
            }else if(color.equals(Color.RED)){
                animNum=xiaozayuAnimHong;
            }else if(color.equals(Color.GREEN)){
                animNum=xiaozayuAnimLv;
            }else if(color.equals(Color.YELLOW)){
                animNum=xiaozayuAnimHuang;
            }else{
                animNum=xiaozayuAnimLan;
            }
        }else if(obj instanceof EnemyDahudie){
            animNum=dahudieAnim;
        }
    }

    public void setStatus(MoveStatus mov){
        if(mov==status){
            return;
        }
        status=mov;
        switch(status){
            case stay:
                flip=false;
                animFrom=animNum[0][0];
                animTo=animNum[0][1];
                curFrameNumber=animFrom;
                break;
            case moveLeft:
                flip=true;
                animFrom=animNum[1][0];
                animTo=animNum[1][1];
                curFrameNumber=animFrom;
                break;
            case moveRight:
                flip=false;
                animFrom=animNum[1][0];
                animTo=animNum[1][1];
                curFrameNumber=animFrom;
                break;
        }
    }

    public void update(){
        ++time;
        if(time>=everyAnimFrameTime){
            ++curFrameNumber;
            time=0;
        }
        if(curFrameNumber>animTo){
            curFrameNumber=animFrom+4;
        }
        if(flip){
            baseEnemyPlane.image.setDrawable(ResourcesManager.flipedTextures.get(objectName+curFrameNumber));
        }else{
            baseEnemyPlane.image.setDrawable(ResourcesManager.textures.get(objectName+curFrameNumber));
        }
    }
}
