package com.meng.stg.item.itemTest.enemy;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.bullets.enemy.BulletColor;
import com.meng.stg.bullets.enemy.BulletForm;
import com.meng.stg.move.BaseMoveMethod;
import com.meng.stg.move.MoveMethodStraight;
import com.meng.stg.planes.enemyPlane.BaseEnemyPlane;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2019/1/25.
 */

public class ItemPoint{

    private BaseEnemyPlane baseEnemyPlane;

    public static HashSet<ItemPoint> instances=new HashSet<ItemPoint>();
    public static LinkedBlockingQueue<ItemPoint> toDelete=new LinkedBlockingQueue<ItemPoint>();
    public static LinkedBlockingQueue<ItemPoint> toAdd=new LinkedBlockingQueue<ItemPoint>();
    private int time=0;
    private Vector2 bulletCenter=new Vector2(200,200);
    private Vector2 bulletVelocity=new Vector2(0,1);
    private BulletForm bf=BulletForm.lindan;
    private BulletColor bc=BulletColor.white;
    private Vector2 offset=new Vector2(0,0);
    private int inFrame=1;
    private int ways=1;
    private float waysDegree=0;
    private int cengShu=1;
    private float cengDanSuCha=0.1f;
    private boolean straightMove=true;
    private int reflex=0;
    private int through=0;
    private float beilv=1;
    private int afterFrames=0;
    private int interval=1;
    private float randomX=0;
    private float randomY=0;
    private boolean useRandomCenter=false;
    private boolean useRandomDegree=false;
    private float randomDegree=0;
    private Random ran=new Random();

    private BaseMoveMethod[] moveMethods=new BaseMoveMethod[]{new MoveMethodStraight()};

    public ItemPoint(){
    }

    public ItemPoint setThrough(int through){
        this.through=through;
        return this;
    }

    public ItemPoint setUseRandomDegree(boolean useRandomDegree){
        this.useRandomDegree=useRandomDegree;
        return this;
    }

    public ItemPoint setBulletSpeed(float f){
        bulletVelocity.nor().scl(f);
        return this;
    }

    public ItemPoint setRandomDegree(float randomDegree){
        setUseRandomDegree(true);
        this.randomDegree=randomDegree;
        return this;
    }

    public ItemPoint setUseRandomCenter(boolean useRandomCenter){
        this.useRandomCenter=useRandomCenter;
        return this;
    }

    public ItemPoint setRandomCenter(float x,float y){
        setUseRandomCenter(true);
        randomX=x;
        randomY=y;
        return this;
    }

    public ItemPoint setInterval(int interval){
        this.interval=interval;
        return this;
    }

    public ItemPoint init(){
        toAdd.add(this);
        return this;
    }

    public ItemPoint setOffset(Vector2 v){
        offset=v;
        return this;
    }

    public ItemPoint setBaseEnemyPlane(BaseEnemyPlane baseEnemyPlane){
        this.baseEnemyPlane=baseEnemyPlane;
        return this;
    }

    public ItemPoint setReflex(int reflex){
        this.reflex=reflex;
        return this;
    }

    public ItemPoint setCengDanSuCha(float cengDanSuCha){
        this.cengDanSuCha=cengDanSuCha;
        return this;
    }

    public ItemPoint setStraightMove(boolean straightMove){
        this.straightMove=straightMove;
        return this;
    }

    public ItemPoint setMoveMethods(BaseMoveMethod... moveMethods){
        this.moveMethods=moveMethods;
        return this;
    }

    public ItemPoint setWays(int ways){
        this.ways=ways;
        return this;
    }

    public ItemPoint setCengShu(int cengShu){
        this.cengShu=cengShu;
        return this;
    }

    public ItemPoint setWaysDegree(float waysDegree){
        this.waysDegree=waysDegree;
        return this;
    }

    public ItemPoint setBulletCenter(Vector2 bulletCenter){
        this.bulletCenter=bulletCenter;
        return this;
    }

    public ItemPoint setBulletColor(BulletColor bc){
        this.bc=bc;
        return this;
    }

    public ItemPoint setBulletForm(BulletForm bf){
        this.bf=bf;
        return this;
    }

    public ItemPoint setBulletVelocity(Vector2 bulletVelocity){
        this.bulletVelocity=bulletVelocity;
        return this;
    }

    public ItemPoint setInFrame(int inFrame){
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
        for(ItemPoint shooter : instances){
            shooter.update();
        }
    }

    public void shoot(){
        if(afterFrames>0){
            --afterFrames;
            return;
        }
        if(time%interval!=1){
            return;
        }
        beilv=1;
        if(useRandomDegree){
            bulletVelocity.rotate(bulletVelocity.angle()+ran.nextFloat()*randomDegree);
        }
        moveMethods=straightMove?new BaseMoveMethod[]{new MoveMethodStraight(inFrame,15,bulletVelocity)}:moveMethods;

        float nowCenterX=-randomX/2+ran.nextFloat()*randomX;
        float nowCenterY=-randomY/2+ran.nextFloat()*randomY;
        for(int ceng=0;ceng<cengShu;++ceng){
            float allAngle=(ways-1)*waysDegree;
            Vector2 tmpv=bulletVelocity.cpy().scl(beilv);
            tmpv.rotate(-allAngle/2);
            for(int i=0;i<ways;i++){
                if(useRandomCenter){
                    BaseItem.create(new Vector2(bulletCenter.x+offset.x+nowCenterX,bulletCenter.y+offset.y+nowCenterY),bf,bc,reflex,new MoveMethodStraight(inFrame,0,tmpv.cpy()));
                }else{
                    BaseItem.create(new Vector2(bulletCenter.x+offset.x,bulletCenter.y+offset.y),bf,bc,reflex,new MoveMethodStraight(inFrame,0,tmpv.cpy()));
                }
                tmpv.rotate(waysDegree);
            }
            beilv+=cengDanSuCha;
        }
    }
}
