package com.meng.TaiHunDanmaku.bullets.enemy;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.meng.TaiHunDanmaku.bullets.BaseEnemyBullet;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.task.Task;
import com.meng.TaiHunDanmaku.ui.MainScreen;

public class EnemyBullet extends BaseEnemyBullet{

    private int colorNum = 0;
    private int formNum = 0;

    public static void create(Vector2 center,BulletForm bulletForm,BulletColor bulletColor,float speed,int ref,int through,Task[] mm){
        ObjectPools.enemyBulletPool.obtain().init(center,bulletForm,bulletColor,speed,ref,through,mm);
	  }

    public void init(Vector2 center,BulletForm bulletForm,BulletColor bulletColor,float speed,int ref,int though,Task[] tasks){
        super.init();
        for(Task task : tasks){
            taskManager.addTask(task);
		  }
        bulletSpeed=speed;
        refCount=ref;
        thoughCount=though;
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,Math.min(image.getWidth(),image.getHeight())/3);
        switch(bulletColor){
            case gray:
			  colorNum=0;
			  break;
            case grayAndRed:
			  colorNum=1;
			  break;
            case red:
			  colorNum=2;
			  break;
            case grayAndPurple:
			  colorNum=3;
			  break;
            case purple:
			  colorNum=4;
			  break;
            case grayAndBlue:
			  colorNum=5;
			  break;
            case blue:
			  colorNum=6;
			  break;
            case grayAndLightBlue:
			  colorNum=7;
			  break;
            case lightBlue:
			  colorNum=8;
			  break;
            case grayAndGreen:
			  colorNum=9;
			  break;
            case green:
			  colorNum=10;
			  break;
            case grayAndYellow:
			  colorNum=11;
			  break;
            case yellow_dark:
			  colorNum=12;
			  break;
            case yellow_light:
			  colorNum=13;
			  break;
            case orange:
			  colorNum=14;
			  break;
            case white:
			  colorNum=15;
			  break;
		  }
        switch(bulletForm){
            case jiguangkuai:
			  formNum=22;
			  break;
            case lindan:
			  formNum=14;
			  break;
            case huanyu:
			  formNum=6;
			  break;
            case xiaoyu:
			  formNum=4;
			  break;
            case midan:
			  formNum=8;
			  break;
            case liandan:
			  formNum=10;
			  break;
            case zhendan:
			  formNum=11;
			  break;
            case zadan:
			  formNum=13;
			  break;
            case chongdan:
			  formNum=15;
			  break;
            case ganjundan:
			  formNum=17;
			  break;
            case xingdan:
			  formNum=18;
			  break;
            case xiaodan:
			  formNum=16;
			  break;
            case jundan:
			  formNum=3;
			  break;
            case lidan:
			  formNum=4;
			  break;
            case diandan:
			  formNum=0;
			  break;
		  }
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public void kill(){
        super.kill();
        drawable=null;
        ObjectPools.enemyBulletPool.free(this);
	  }

    @Override
    public Drawable getDrawable(){
        if(formNum==13||formNum==14){
            image.setSize(14,16);
		  }
		if(formNum==0){
			image.setSize(8,8);
		  }
		if(formNum==8){
			image.setSize(8,10);
		  }
        if(drawable==null){
            drawable=ResourcesManager.textures.get("bullet"+(formNum*16+colorNum));
		  }
        return drawable;
	  }
  }
