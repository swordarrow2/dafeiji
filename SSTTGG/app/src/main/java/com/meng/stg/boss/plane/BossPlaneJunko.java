package com.meng.stg.boss.plane;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.boss.BaseBossPlane;
import com.meng.stg.boss.danmaku.Spell_PurelyBulletHell;
import com.meng.stg.bullets.BaseBullet;
import com.meng.stg.move.BaseMoveMethod;

public class BossPlaneJunko extends BaseBossPlane{

    private final int[][] junkoAnim=new int[][]{
            {10,14},
            {5,9}
    };

    @Override
    public void update(){
        super.update();
        //  moveManager.update();
        //  am.update();
        objectCenter.set(193,350);
    }

    @Override
    public void init(Vector2 center,int everyAnimFrameTime,int hp,BaseMoveMethod... bmm){
        super.init(center,everyAnimFrameTime,hp,bmm);
        BaseBullet.killAllBullet();
        objectName="chunhu";
        this.everyAnimFrameTime=everyAnimFrameTime;
        animNum=junkoAnim;
        spellCard=new Spell_PurelyBulletHell();
        spellCard.init(this);
  /*      bulletShooter=new BulletShooter().init()
          .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.red)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(24)
		  .setReflex(0)
		  .setWaysDegree(15)
		  .setCengShu(1)
		  .setInterval(30)
		  .setRandomCenter(64,64)
		  .setInFrame(1);


		p2a=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(-120,0))
		  .setBulletColor(BulletColor.purple)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(12)
		  .setReflex(0)
		  .setWaysDegree(30)
		  .setCengShu(1)
		  .setInterval(60)
		  .setRandomDegree(360)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p2b=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(120,0))
		  .setBulletColor(BulletColor.purple)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(12)
		  .setReflex(0)
		  .setWaysDegree(30)
		  .setCengShu(1)
		  .setInterval(60)
		  .setRandomDegree(360)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p3a=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(-160,0))
		  .setBulletColor(BulletColor.blue)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(40)
		  .setReflex(0)
		  .setWaysDegree(9)
		  .setInterval(40)
		  .setCengShu(1)
		  .setRandomDegree(360)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p3b=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setOffset(new Vector2(160,0))
		  .setBulletColor(BulletColor.blue)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(40)
		  .setReflex(0)
		  .setWaysDegree(9)
		  .setInterval(40)
		  .setCengShu(1)
		  .setRandomDegree(360)
		  .setRandomCenter(64,64)
		  .setInFrame(1);

		p4=new BulletShooter().init()
		  .setBaseEnemyPlane(this)
		  .setBulletCenter(objectCenter)
		  .setBulletColor(BulletColor.yellow_dark)
		  .setBulletForm(BulletForm.xiaoyu)
		  .setStraightMove(true)
		  .setWays(36)
		  .setReflex(0)
		  .setWaysDegree(10)
		  .setCengShu(1)
		  .setInterval(8)
		  .setInFrame(1);
*/
    }


    @Override
    public Vector2 getSize(){
        return new Vector2(96,128);
    }

  /*  @Override
    public void shoot(){
        spellCard.update();
		bulletShooter
		  .setBulletSpeed(2)
		  //	  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
		  .setStraightMove(true)
		  .shoot();

		p2a
		  .setBulletSpeed(0.7f)
		  //	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
		  //  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(0.7f))
		  .setStraightMove(true)
		  .shoot();


		p2b
		  .setBulletSpeed(0.7f)
		  //	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
		  //	  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(0.7f))
		  .setStraightMove(true)
		  .shoot();

		if(hp<3020&&hp>3000){
			BaseBullet.killAllBullet();
		  }


		p3a
		  .setBulletSpeed(2)
		  //	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
		  //	  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
		  .setStraightMove(true)
		  .shoot();

		p3b.setBulletSpeed(2)
		  //	  .setBulletCenter(new Vector2(objectCenter.x-32+ran.nextInt(64),objectCenter.y-32+ran.nextInt(64)))
		  //  .setBulletVelocity(new Vector2(ran.nextInt(1024),ran.nextInt(1024)).nor().scl(2))
		  .setStraightMove(true)
		  .shoot();

		p4.setBulletSpeed(6)
		  //  .setBulletVelocity(new Vector2(0,-1).nor().scl(6))
		  .setStraightMove(true)
		  .shoot();

    }*/
}
