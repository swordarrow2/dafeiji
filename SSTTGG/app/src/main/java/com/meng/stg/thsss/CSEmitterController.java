package com.meng.stg.thsss;

public class CSEmitterController:BaseBulleEmitter {
private CS_Data CSData;

public bool BossBinding { get; set; }

public bool OnRoad { get; set; }

public PointF BaseOriginalPosition { get; set; }

public CSEmitterController(StageDataPackage StageData,CS_Data CSData)
        : base(StageData) {
        this.CSData=CSData;
        this.BaseOriginalPosition=new PointF((float)(this.BoundRect.Width/2),130f);
        }

public override void Shoot() {
        if(this.Time>=this.CSData.TimeTotal)
        this.Time-=this.CSData.TimeTotal;
        if(this.OnRoad&&this.Boss!=null)
        return;
        this.CSData.EmitterList.ForEach((Action<BaseEmitter_CS>)(em => {
        if(em.BindingID!=-1||em.StartTime!=this.Time)
        return;
        BaseEmitter_CS baseEmitterCs1 = (BaseEmitter_CS)em.Clone();
        if(this.BossBinding&&this.Boss!=null) {
        BaseEmitter_CS baseEmitterCs2 = baseEmitterCs1;
        double x1 = (double)baseEmitterCs1.OriginalPosition.X;
        PointF originalPosition1 = this.Boss.OriginalPosition;
        double x2 = (double)originalPosition1.X;
        double num1 = x1+x2;
        originalPosition1=this.BaseOriginalPosition;
        double x3 = (double)originalPosition1.X;
        double num2 = num1-x3;
        double y1 = (double)baseEmitterCs1.OriginalPosition.Y;
        PointF originalPosition2 = this.Boss.OriginalPosition;
        double y2 = (double)originalPosition2.Y;
        double num3 = y1+y2;
        originalPosition2=this.BaseOriginalPosition;
        double y3 = (double)originalPosition2.Y;
        double num4 = num3-y3;
        PointF pointF = new PointF((float)num2,(float)num4);
        baseEmitterCs2.OriginalPosition=pointF;
        baseEmitterCs1.EmitPoint=new PointF(-99998f,-99998f);
        baseEmitterCs1.SetBinding((BaseObject)this.Boss);
        }
        baseEmitterCs1.Time=this.Time;
        this.StageData.EnemyPlaneList.Add((BaseEnemyPlane)baseEmitterCs1);
        }));
        }
        }
