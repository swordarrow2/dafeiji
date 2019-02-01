package com.meng.stg.planes.enemyPlane;
import com.badlogic.gdx.math.*;
import com.meng.stg.move.*;

public class EnemyPlaneCreator
{
  EnemyType enemyType=EnemyType.xiaozayu;
  Vector2 enemyCenter=Vector2.Zero;
  int hp=10;
 BaseMoveMethod[] moveMethods=new BaseMoveMethod[]{new MoveMethodStraight()};
	
  
  public EnemyPlaneCreator(){
	
  }
  public EnemyPlaneCreator setCenter(float x,float y){
	enemyCenter.x=x;
	enemyCenter.y=y;
	return this;
  }
  
  public void createEnemy(){
	  new EnemyPlane().Init(enemyCenter.x,enemyCenter.y,0,-1);
  }
}
