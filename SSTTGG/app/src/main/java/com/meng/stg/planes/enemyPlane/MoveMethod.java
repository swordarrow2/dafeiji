package com.meng.stg.planes.enemyPlane;
import com.badlogic.gdx.math.*;

public class MoveMethod
{
  public Vector2 velocity;
  public int time;
  public MoveMethod(int inFrame,Vector2 velo){
	time=inFrame;
	velocity=velo;
  }  
}
