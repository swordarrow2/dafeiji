package com.meng.stg.bullets.enemy;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg.bullets.*;
import com.meng.stg.helpers.*;
import com.meng.stg.task.*;
import com.meng.stg.ui.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class bulletLaser extends BaseEnemyBullet{

    private int colorNum=0;
    private int formNum=0;
	
	private Ellipse judgeAera;

    public void init(Vector2 center,float speed,Task[] tasks){
        super.init();
        for(Task task : tasks){
            taskManager.addTask(task);
        }
		bulletSpeed=speed;
        objectCenter.set(center);
        image.setPosition(center.x,center.y,Align.center);
        judgeCircle=new Circle(objectCenter,1);
        
        image.setDrawable(getDrawable());
        MainScreen.mainGroup.addActor(image);
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
    }

    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("bullet49");
        }
        return drawable;
	  }

	@Override
	public void update(){
		// TODO: Implement this method
		super.update();
		
	  }
	  
	  

/*	private boolean isPointInRect(int x, int y,Image i) {
	  
		final Vector2 A = mLBPoint;
		final Vector2 B = mLTPoint;
		final Vector2 C = mRTPoint;
		final Vector2 D = mRBPoint;
		final int a = (B.x - A.x)*(y - A.y) - (B.y - A.y)*(x - A.x);
		final int b = (C.x - B.x)*(y - B.y) - (C.y - B.y)*(x - B.x);
		final int c = (D.x - C.x)*(y - C.y) - (D.y - C.y)*(x - C.x);
		final int d = (A.x - D.x)*(y - D.y) - (A.y - D.y)*(x - D.x);
		if((a > 0 && b > 0 && c > 0 && d > 0) || (a < 0 && b < 0 && c < 0 && d < 0)) {
			return true;
		  }

//		AB X AP = (b.x - a.x, b.y - a.y) x (p.x - a.x, p.y - a.y) = (b.x - a.x) * (p.y - a.y) - (b.y - a.y) * (p.x - a.x);
//		BC X BP = (c.x - b.x, c.y - b.y) x (p.x - b.x, p.y - b.y) = (c.x - b.x) * (p.y - b.y) - (c.y - b.y) * (p.x - b.x);
		return false; 
	  }
	
	*/  
}
