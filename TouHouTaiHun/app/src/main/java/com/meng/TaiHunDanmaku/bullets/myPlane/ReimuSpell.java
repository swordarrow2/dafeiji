package com.meng.TaiHunDanmaku.bullets.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.bullets.enemy.*;
import com.meng.TaiHunDanmaku.effects.*;
import com.meng.TaiHunDanmaku.helpers.*;

import static com.meng.TaiHunDanmaku.ui.MainScreen.enemys;

public class ReimuSpell extends BaseMyBullet{

	@Override
	public void init(Vector2 center,Vector2 velocity){
		super.init(center,velocity);
		judgeCircle.setRadius(20);
	  }

  
    @Override
    public Drawable getDrawable(){
        if(drawable==null){
            drawable=ResourcesManager.textures.get("reimu55");
        }
        return drawable;
    }

    @Override
    public Vector2 getSize(){
        return new Vector2(64,16);
    }

    public void judge(){
        try{
            for(int i=0;i<32;i++){
                if(!(enemys[i]==null)){
                    if(((Circle)getCollisionArea()).overlaps(((Circle)enemys[i].getJudgeCircle()))){
                        enemys[i].hit(70.5f);
                    }
                }
            }
			for(BaseEnemyBullet baseBullet : BaseEnemyBullet.instances){
				if(judgeCircle.contains(baseBullet.objectCenter)){
					Effect.create(baseBullet.objectCenter.cpy(),EffectType.explore);
					baseBullet.killByJudge(BulletKillMode.KillOnBossLastDeath);
				  }
			  }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public float getRotationDegree(){
        return 90;
    }
}
