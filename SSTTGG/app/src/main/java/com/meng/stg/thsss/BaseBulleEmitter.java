package com.meng.stg.thsss;

/**
 * Created by Administrator on 2019/1/26.
 */

public class BaseBulleEmitter : BaseEnemyPlane
        {
public BaseBulleEmitter(StageDataPackage StageData)
        {
        this.StageData = StageData;
        this.OriginalPosition = new PointF(0.0f, 0.0f);
        this.Velocity = 0.0f;
        this.Direction = 0.0;
        this.HealthPoint = 1000000f;
        this.HitEnabled = false;
        this.EnemyPlaneList.Add((BaseEnemyPlane) this);
        }

public override bool HitCheck(BaseObject MyPlane)
        {
        return false;
        }
        }
