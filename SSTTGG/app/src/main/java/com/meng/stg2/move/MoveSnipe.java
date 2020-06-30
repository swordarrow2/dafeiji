package com.meng.stg2.move;
import com.badlogic.gdx.math.*;
import com.meng.stg2.planes.myPlane.*;

public class MoveSnipe extends BaseMoveMethod{

	private Vector2 v=new Vector2();

	public MoveSnipe(Vector2 bulletCenter,float speed){
		time=60;
		afterFrames=1;
		v=bulletCenter.cpy().sub(BaseMyPlane.instance.position).nor().scl(speed);
	  }
	@Override
	public Vector2 getVelocity(){
		return v;
	  }

	@Override
	public int getTime(){
		return time;
	  }

	@Override
	public void update(){
		if(afterFrames>0){
			--afterFrames;
			return;
		  }
		time--;
	  }

  }
