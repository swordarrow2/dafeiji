package com.meng.stg.planes.enemyPlane;
import com.badlogic.gdx.math.*;

public class MoveMethod
{
  private Vector2 velocity;
  private int time;
  public MoveMethod(int inFrame,Vector2 velo){
	time=inFrame;
	velocity=velo;
	}

  public Vector2 getVelocity(){
	  return velocity;
	}  
  
  public int getTime(){
	return time;
  }
  
  public void update(){
	time--;
  }
  
}
