package com.meng.TaiHunDanmaku.planes.subPlane;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.meng.TaiHunDanmaku.helpers.ObjectPools;
import com.meng.TaiHunDanmaku.helpers.ResourcesManager;
import com.meng.TaiHunDanmaku.helpers.TextureNameManager;
import com.meng.TaiHunDanmaku.planes.myPlane.BaseMyPlane;

public class SubPlaneReimuB extends BaseSubPlane{
    private int degree=0;
    private Vector2 vel=new Vector2(0,37);
    private int[] pos=new int[]{
		0,20,
		0,32,
		-16,20,16,20,
		-32,0,32,0,
		-16,20,0,30,16,20,
		-32,0,0,32,32,0,
		-16,20,-8,30,8,30,16,20,
		-32,0,-16,32,16,32,32,0
	  };

    @Override
    public Drawable getDrawable(){
        return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
	  }

    @Override
    public void init(BaseMyPlane myPlane,int subPlaneNumber){
        super.init(myPlane,subPlaneNumber);
	  }


    @Override
    public float getRotationDegree(){
        degree+=5;
        return degree;
	  }

    @Override
    public int[] getSubPlanePosition(){
        return pos;
	  }

    @Override
    public Vector2 getSize(){
        return new Vector2(16,16);
	  }

    @Override
    public void shoot(){
        if(myPlane.existTime%4==1){
			ObjectPools.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(objectCenter.x+4,objectCenter.y+16),vel);
			ObjectPools.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(objectCenter.x-4,objectCenter.y+16),vel);
		  }
	  }
  }
