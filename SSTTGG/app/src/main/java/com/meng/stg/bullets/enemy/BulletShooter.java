package com.meng.stg.bullets.enemy;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2019/1/25.
 */

public class BulletShooter{

    private BaseEnemyPlane baseEnemyPlane;

    public static HashSet<BulletShooter> instances=new HashSet<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toDelete=new LinkedBlockingQueue<BulletShooter>();
    public static LinkedBlockingQueue<BulletShooter> toAdd=new LinkedBlockingQueue<BulletShooter>();
    private int time=0;
    private Vector2 bulletCenter=new Vector2(200,200);
    private Vector2 bulletVelocity=new Vector2(0,-1);
    private BulletForm bf=BulletForm.lindan;
    private BulletColor bc=BulletColor.white;
    private Vector2 offset=new Vector2(0,0);
    private int inFrame=1;
    private int ways=1;
    private int waysDegree=0;
    private int cengShu=1;
    private float cengDanSuCha=0.1f;
    private boolean straightMove=true;
    private int reflex=1;
    private float beilv=1;
	private int afterFrames=0;
	private int interval=60;
    private BaseMoveMethod[] moveMethods=new BaseMoveMethod[]{new MoveMethodStraight()};

    public BulletShooter(){
	  }

	public BulletShooter setInterval(int interval){
		this.interval=interval;
		return this;
	  }

	public BulletShooter init(){
		toAdd.add(this);
		return this;  
	  }
	public BulletShooter setOffset(Vector2 v){
	  offset=v;
	  return this;
	}
	public BulletShooter setBaseEnemyPlane(BaseEnemyPlane baseEnemyPlane){
		this.baseEnemyPlane=baseEnemyPlane;
		return this;
	  }

    public BulletShooter setReflex(int reflex){
        this.reflex=reflex;
		return this;
	  }

    public BulletShooter setCengDanSuCha(float cengDanSuCha){
        this.cengDanSuCha=cengDanSuCha;
		return this;
	  }

    public BulletShooter setStraightMove(boolean straightMove){
        this.straightMove=straightMove;
        moveMethods=straightMove?new BaseMoveMethod[]{new MoveMethodStraight(inFrame,15,bulletVelocity)}:moveMethods;
        return this;
	  }

    public BulletShooter setMoveMethods(BaseMoveMethod... moveMethods){
        this.moveMethods=moveMethods;
        return this;
	  }

    public BulletShooter setWays(int ways){
        this.ways=ways;
        return this;
	  }

    public BulletShooter setCengShu(int cengShu){
        this.cengShu=cengShu;
        return this;
	  }

    public BulletShooter setWaysDegree(int waysDegree){
        this.waysDegree=waysDegree;
        return this;
	  }

    public BulletShooter setBulletCenter(Vector2 bulletCenter){
        this.bulletCenter=bulletCenter;
        return this;
	  }

    public BulletShooter setBulletColor(BulletColor bc){
        this.bc=bc;
        return this;
	  }

    public BulletShooter setBulletForm(BulletForm bf){
        this.bf=bf;
        return this;
	  }

    public BulletShooter setBulletVelocity(Vector2 bulletVelocity){
        this.bulletVelocity=bulletVelocity;
        return this;
	  }

    public BulletShooter setInFrame(int inFrame){
        this.inFrame=inFrame;
        return this;
	  }

    public void update(){
        ++time;
        if(baseEnemyPlane.judgeCircle==null){
            kill();
		  }
	  }

    public void kill(){
        toDelete.add(this);
	  }

    public static void updateAll(){
        while(!toDelete.isEmpty()){
            instances.remove(toDelete.poll());
		  }
        while(!toAdd.isEmpty()){
            instances.add(toAdd.poll());
		  }
        for(BulletShooter shooter : instances){
            shooter.update();
		  }
	  }

    public void shoot(){
		if(afterFrames>0){
			-- afterFrames;
			return;
		  }
		  if(time%interval!=1){
			return;
		  }
        beilv=1;
        for(int ceng=0;ceng<cengShu;++ceng){
            float allAngle=(ways-1)*waysDegree;
            Vector2 tmpv=bulletVelocity.cpy().scl(beilv);
            tmpv.rotate(-allAngle/2);
            for(int i=0;i<ways;i++){
                EnemyBullet.create(new Vector2(bulletCenter.x+offset.x,bulletCenter.y+offset.y),bf,bc,reflex,new MoveMethodStraight(inFrame,0,tmpv.cpy()));
                tmpv.rotate(waysDegree);
			  }
            beilv+=cengDanSuCha;
		  }
	  }
  }
