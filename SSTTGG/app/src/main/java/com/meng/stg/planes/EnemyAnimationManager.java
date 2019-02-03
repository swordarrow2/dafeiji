package com.meng.stg.planes;

import com.meng.stg.*;
import com.meng.stg.helpers.*;
import com.meng.stg.planes.enemyPlane.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.utils.*;

public class EnemyAnimationManager extends BaseGameObject{

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

    public EnemyAnimationManager(BaseEnemyPlane obj,EnemyColor enemyColor,int everyAnimFrameTime){
        this.everyAnimFrameTime=everyAnimFrameTime;
        baseEnemyPlane=obj;
        objectName="zayu";
        if(obj instanceof EnemyXiaozayu){
            switch(enemyColor){
                case red:
                    animNum=xiaozayuAnimHong;
                    break;
                case blue:
                    animNum=xiaozayuAnimLan;
                    break;
                case green:
                    animNum=xiaozayuAnimLv;
                    break;
                case yellow:
                    animNum=xiaozayuAnimHuang;
                    break;
            }
        }else if(obj instanceof EnemyDahudie){
            animNum=dahudieAnim;
        }
    }

    public void setStatus(MoveStatus mov){
        if(mov==status) return;
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

	@Override
	public void init(){
		super.init();
		size.set(32,32);
		image.setRotation(0);
		image.setSize(size.x,size.y);
        MainScreen.mainGroup.addActor(image);
	  }

	@Override
	public void kill(){
		super.kill();
		image.remove();
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
		objectCenter.set(baseEnemyPlane.objectCenter.x,baseEnemyPlane.objectCenter.y);
		image.setPosition(objectCenter.x,objectCenter.y,Align.center);
        if(flip){
            image.setDrawable(ResourcesManager.flipedTextures.get(objectName+curFrameNumber));
        }else{
            image.setDrawable(ResourcesManager.textures.get(objectName+curFrameNumber));
        }
    }
}
