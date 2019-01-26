package com.meng.stg.helpers;

import com.badlogic.gdx.math.Vector2;
import com.meng.stg.BaseEnemyObject;
import com.meng.stg.bullets.BaseEnemyBullet;
import com.meng.stg.enemy.BaseEnemyPlane;

public class BaseEmitter_CS{
    public boolean BindingState;
    public int BindingID;
    public boolean BindWithDirection;
    public int StartTime;
    public int Duration;
    public Vector2 EmitPoint;
    public float EmitRadius;
    public double RadiusDirection;
    public Vector2 RadiusDirectionPoint;
    public int Way;
    public int Circle;
    public double EmitDirection;
    public Vector2 EmitDirectionPoint;
    public double Range;
    public boolean RDirectionWithDirection;
    public float RanX;
    public float RanY;
    public float RanRadius;
    public double RanRadiusDirection;
    public int RanWay;
    public int RanCircle;
    public double RanEmitDirection;
    public double RanRange;
    public boolean DeepBinding;
    public boolean RangeShoot;
    public boolean MotionBinding;
    public String SoundName;
    public boolean SpecifySE;
    public int EffectType;
    public int Count;
    public float DeltaV;
    public CS_Data CSData;
    private BaseEnemyBullet CSBullet;
    // private BaseStraightLaser_CS CSLaserS;
    //  private BaseRadialLaser_CS CSLaserR;
    //  private BaseBendLaser_CS CSLaserB;
    private BaseEnemyPlane CSEnemy;
    // private BaseEffect_CS CSEffect;
    public EmitterMode emitterMode;

    public BaseEnemyObject getBaseEnemyObject(){
        switch(emitterMode){
            case Enemy:
                return CSEnemy;
            case Effect:
                //       return (BaseEnemyObject)this.CSEffect;
            case StraightLaser:
                //      return (BaseEnemyObject)this.CSLaserS;
            case RadialLaser:
                //       return (BaseEnemyObject)this.CSLaserR;
            case BendLaser:
                //      return (BaseEnemyObject)this.CSLaserB;
            default:
                return CSBullet;
        }
    }

    public void setBaseEnemyObject(BaseEnemyObject value){
        switch(emitterMode){
            case Enemy:
                CSEnemy=(BaseEnemyPlane)value;
                break;
            case StraightLaser:
           //     CSLaserS=(BaseStraightLaser_CS)value;
           //     break;
            case RadialLaser:
            //    CSLaserR=(BaseRadialLaser_CS)value;
            //    break;
            case BendLaser:
            //    CSLaserB=(BaseBendLaser_CS)value;
            //    break;
            case Effect:
                //    CSEffect=(BaseEffect_CS)value;
                //    break;
            case Bullet:
                CSBullet=(BaseEnemyBullet)value;
                break;
        }
    }


    public BaseEmitter_CS(){
    }

    public BaseEmitter_CS( CS_Data CSData,EmitterMode EmitterMode){
        this.CSData=CSData;
        this.EventGroupList=new List<EventGroup>();
        this.EventsExecutionList=new List<Execution>();
        this.HealthPoint=1000000f;
        this.HitEnabled=false;
        this.Region=0;
        this.Count=1;
        this.emitterMode=EmitterMode;
        switch(EmitterMode){
            case EmitterMode.Bullet:
                this.CSBullet=new BaseBullet_CS(StageData);
                break;
            case EmitterMode.StraightLaser:
                this.CSLaserS=new BaseStraightLaser_CS(StageData);
                break;
            case EmitterMode.RadialLaser:
                this.CSLaserR=new BaseRadialLaser_CS(StageData);
                break;
            case EmitterMode.BendLaser:
                this.CSLaserB=new BaseBendLaser_CS(StageData);
                break;
            case EmitterMode.Effect:
                this.CSEffect=new BaseEffect_CS(StageData);
                break;
        }
    }

    public override

    void Ctrl(){
        if(this.Time==this.StartTime){
            this.Velocity+=this.RanVelocity*(float)this.Ran.NextPMDouble();
            this.DirectionDegree+=this.RanDirection*this.Ran.NextPMDouble();
            this.AccelerateCS+=this.RanAccelerate*(float)this.Ran.NextPMDouble();
            this.AccDirection+=this.RanAccDirection*this.Ran.NextPMDouble();
        }
        if(this.LifeTime>this.StartTime+this.Duration)
            this.LifeTime=this.StartTime+this.Duration;
        if(this.Range!=360.0)
            this.RangeShoot=true;
        if(this.BindingObj!=null&&this.BindingObj is BaseEnemyPlane_Touhou &&
        (double)this.BindingObj.HealthPoint<=0.0)
        this.EnemyPlaneList.Remove((BaseEnemyPlane)this);
        base.Ctrl();
    }

    public override

    void EventCtrl(){
        this.EventGroupList.ForEach((Action<EventGroup>)(a=>a.Update(this)));
        this.EventsExecutionList.ForEach((Action<Execution>)(a=>a.Update(this)));
    }

    public override

    void Shoot(){
        if(this.Time<this.StartTime||this.Time>this.StartTime+this.Duration)
            return;
        int num1=this.Circle+(int)((double)this.RanCircle*this.Ran.NextPMDouble());
        int num2=num1<1?1:num1;
        if(!this.DeepBinding&&(this.Time-this.StartTime+1)%num2==0){
            this.ShootBullet();
        }else{
            if(!this.DeepBinding||this.Time%num2!=0)
                return;
            this.ShootBullet();
        }
    }

    public void ShootBullet(){
        float x=this.EmitPoint.X;
        float y=this.EmitPoint.Y;
        Vector2 originalPosition;
        float num1;
        if((double)x==-99999.0){
            originalPosition=this.MyPlane.OriginalPosition;
            num1=originalPosition.X;
        }else if((double)x==-99998.0){
            originalPosition=this.OriginalPosition;
            num1=originalPosition.X;
        }else
            num1=(float)((double)x-320.0+192.0);
        float num2;
        if((double)y==-99999.0){
            originalPosition=this.MyPlane.OriginalPosition;
            num2=originalPosition.Y;
        }else if((double)y==-99998.0){
            originalPosition=this.OriginalPosition;
            num2=originalPosition.Y;
        }else
            num2=(float)((double)y-240.0+224.0);
        if(this.emitterMode==emitterMode.StraightLaser||this.emitterMode==emitterMode.RadialLaser||this.emitterMode==emitterMode.BendLaser){
            originalPosition=this.OriginalPosition;
            num1=originalPosition.X;
            originalPosition=this.OriginalPosition;
            num2=originalPosition.Y;
        }
        Vector2 OriginalPosition=new Vector2(num1+this.RanX*(float)this.Ran.NextPMDouble(),num2+this.RanY*(float)this.Ran.NextPMDouble());
        double edi=this.EmitDirection!=-99999.0?(this.EmitDirection+this.RanEmitDirection*this.Ran.NextPMDouble()+this.SubBullet.RanDirection*this.Ran.NextPMDouble())*Math.PI/180.0:this.GetDirection((BaseObject)this.MyPlane)+(this.RanEmitDirection*this.Ran.NextPMDouble()+this.SubBullet.RanDirection*this.Ran.NextPMDouble())*Math.PI/180.0;
        double num3=this.RadiusDirection!=-99999.0?(this.RadiusDirection+this.RanRadiusDirection*this.Ran.NextPMDouble())*Math.PI/180.0:this.GetDirection((BaseObject)this.MyPlane)+this.RanRadiusDirection*this.Ran.NextPMDouble()*Math.PI/180.0;
        float num4=this.EmitRadius+this.RanRadius*(float)this.Ran.NextPMDouble();
        int num5=this.Way+(int)((double)this.RanWay*this.Ran.NextPMDouble());
        float num6=this.SubBullet.Velocity+this.SubBullet.RanVelocity*(float)this.Ran.NextPMDouble();
        float num7=this.SubBullet.AccelerateCS+this.SubBullet.RanAccelerate*(float)this.Ran.NextPMDouble();
        double num8=this.SubBullet.AccDirection+this.SubBullet.RanAccDirection;
        double num9=this.Range+this.RanRange*this.Ran.NextPMDouble();
        if(this.RDirectionWithDirection)
            num3+=edi;
        double num10=num9*Math.PI/180.0/(double)num5;
        edi-=(double)(num5-1)*num10/2.0;
        double num11=num3-(double)(num5-1)*num10/2.0;
        if(this.EffectType==2){
            EmitterSaveEnegy3D emitterSaveEnegy3D=new EmitterSaveEnegy3D(this.StageData,OriginalPosition,this.CSEffect.ColorValue);
            this.StageData.SoundPlay("se_ch02.wav");
        }else if(this.EffectType==3){
            EmitterGiveOutEnegy3D emitterGiveOutEnegy3D=new EmitterGiveOutEnegy3D(this.StageData,OriginalPosition,this.CSEffect.ColorValue);
            this.StageData.SoundPlay("se_cat00.wav");
            this.StageData.SoundPlay("se_enep02.wav");
        }else{
            for(int index1=0;index1<num5;++index1){
                Vector2 p=new Vector2(OriginalPosition.X+num4*(float)Math.Cos(num11),OriginalPosition.Y+num4*(float)Math.Sin(num11));
                for(int index2=0;index2<this.Count;++index2){
                    if(this.emitterMode==emitterMode.Bullet||this.emitterMode==emitterMode.StraightLaser||this.emitterMode==emitterMode.RadialLaser||this.emitterMode==emitterMode.BendLaser){
                        BaseBullet_Touhou b=new BaseBullet_Touhou(this.StageData);
                        if(this.CSBullet!=null){
                            b=(BaseBullet_Touhou)this.CSBullet.Clone();
                            b.OriginalPosition=p;
                            b.GhostingCount=b.GhostingCount;
                            b.AngleDegree+=this.CSBullet.RanAngle*this.Ran.NextPMDouble();
                        }else if(this.CSLaserS!=null){
                            b=(BaseBullet_Touhou)this.CSLaserS.Clone();
                            b.OriginalPosition=p;
                            b.Angle=-1.0*Math.PI/2.0;
                            b.Active=true;
                        }else if(this.CSLaserR!=null){
                            b=(BaseBullet_Touhou)this.CSLaserR.Clone();
                            b.OriginalPosition=p;
                            b.Angle=Math.PI/2.0;
                            b.UnRemoveable=true;
                            b.Active=true;
                        }else if(this.CSLaserB!=null){
                            b=(BaseBullet_Touhou)this.CSLaserB.Clone();
                            b.OriginalPosition=p;
                            b.UnRemoveable=true;
                            b.Active=true;
                        }
                        b.GhostingCount=b.GhostingCount;
                        b.Velocity=num6-(float)index2*this.DeltaV;
                        b.Direction=edi;
                        b.AccelerateCS=num7;
                        b.AccDirection=num8;
                        b.ID=this.ID;
                        b.LayerID=this.LayerID;
                        if(this.MotionBinding)
                            b.SetBinding((BaseObject)this);
                        this.BulletList.Add(b);
                        if(this.emitterMode==emitterMode.Bullet)
                            this.CSData.EmitterList.ForEach((Action<BaseEmitter_CS>)(em=>
                                    {
                        if(em.BindingID!=this.ID)
                            return;
                        b.UnRemoveable=true;
                        BaseEmitter_CS baseEmitterCs=(BaseEmitter_CS)em.Clone();
                        this.StageData.EnemyPlaneList.Add((BaseEnemyPlane)baseEmitterCs);
                        baseEmitterCs.OriginalPosition=p;
                        baseEmitterCs.LifeTime=this.SubBullet.LifeTime;
                        baseEmitterCs.ColorValue=this.SubBullet.ColorValue;
                        baseEmitterCs.TransparentValueF=this.SubBullet.TransparentValueF;
                        baseEmitterCs.Direction=edi;
                        baseEmitterCs.DestPoint=this.SubBullet.DestPoint;
                        baseEmitterCs.Active=this.SubBullet.Active;
                        baseEmitterCs.OutBound=this.SubBullet.OutBound;
                        if(baseEmitterCs.BindWithDirection)
                            baseEmitterCs.EmitDirection+=edi*180.0/Math.PI;
                        baseEmitterCs.SetBinding((BaseObject)b);
                        if(!baseEmitterCs.DeepBinding){
                            baseEmitterCs.Time=this.Time;
                            baseEmitterCs.LifeTime=Math.Min(this.SubBullet.LifeTime+this.Time,em.StartTime+em.Duration);
                        }
                }));
                    }else if(this.emitterMode==emitterMode.Enemy){
                        BaseEnemyPlane_Touhou enemy=(BaseEnemyPlane_Touhou)this.SubBullet.Clone();
                        enemy.LifeTime=0;
                        enemy.OriginalPosition=p;
                        enemy.GhostingCount=enemy.GhostingCount;
                        enemy.Velocity=num6-(float)index2*this.DeltaV;
                        enemy.Direction=edi;
                        enemy.AccelerateCS=num7;
                        enemy.AccDirection=num8;
                        enemy.ID=this.ID;
                        enemy.LayerID=this.LayerID;
                        if(this.MotionBinding)
                            enemy.SetBinding((BaseObject)this);
                        this.EnemyPlaneList.Add((BaseEnemyPlane)enemy);
                        this.CSData.EmitterList.ForEach((Action<BaseEmitter_CS>)(em=>
                                {
                        if(em.BindingID!=this.ID)
                            return;
                        BaseEmitter_CS baseEmitterCs=(BaseEmitter_CS)em.Clone();
                        this.StageData.EnemyPlaneList.Add((BaseEnemyPlane)baseEmitterCs);
                        baseEmitterCs.OriginalPosition=p;
                        baseEmitterCs.LifeTime=this.SubBullet.LifeTime;
                        baseEmitterCs.ColorValue=this.SubBullet.ColorValue;
                        baseEmitterCs.TransparentValueF=this.SubBullet.TransparentValueF;
                        baseEmitterCs.Direction=edi;
                        baseEmitterCs.DestPoint=this.SubBullet.DestPoint;
                        baseEmitterCs.Active=this.SubBullet.Active;
                        baseEmitterCs.OutBound=this.SubBullet.OutBound;
                        baseEmitterCs.SetBinding((BaseObject)enemy);
                        if(baseEmitterCs.BindWithDirection)
                            baseEmitterCs.EmitDirection+=edi*180.0/Math.PI;
                        if(!baseEmitterCs.DeepBinding){
                            baseEmitterCs.Time=this.Time;
                            baseEmitterCs.LifeTime=Math.Min(this.SubBullet.LifeTime+this.Time,em.StartTime+em.Duration);
                        }
              }));
                    }else if(this.emitterMode==emitterMode.Effect){
                        BaseEffect_CS baseEffectCs=(BaseEffect_CS)this.CSEffect.Clone();
                        baseEffectCs.OriginalPosition=p;
                        baseEffectCs.GhostingCount=baseEffectCs.GhostingCount;
                        baseEffectCs.AngleDegree+=this.CSEffect.RanAngle*this.Ran.NextPMDouble();
                        baseEffectCs.Velocity=num6-(float)index2*this.DeltaV;
                        baseEffectCs.Direction=edi;
                        baseEffectCs.AccelerateCS=num7;
                        baseEffectCs.AccDirection=num8;
                        baseEffectCs.ID=this.ID;
                        baseEffectCs.LayerID=this.LayerID;
                        if(this.MotionBinding)
                            baseEffectCs.SetBinding((BaseObject)this);
                        this.EffectList.Add((BaseEffect)baseEffectCs);
                    }
                }
                num11+=num10;
                edi+=num10;
                if(this.SpecifySE)
                    this.StageData.SoundPlay(this.SoundName);
                else if(this.emitterMode==emitterMode.Bullet)
                    this.StageData.SoundPlay("se_tan00a.wav",OriginalPosition.X/(float)this.BoundRect.Width);
                else if(this.emitterMode==emitterMode.StraightLaser||this.emitterMode==emitterMode.RadialLaser)
                    this.StageData.SoundPlay("se_lazer00.wav",OriginalPosition.X/(float)this.BoundRect.Width);
            }
        }
    }

    public override object

    Clone(){
        BaseEmitter_CS baseEmitterCs=(BaseEmitter_CS)base.Clone();
        if(this.CSBullet!=null)
            baseEmitterCs.CSBullet=(BaseBullet_CS)this.CSBullet.Clone();
        if(this.CSLaserS!=null)
            baseEmitterCs.CSLaserS=(BaseStraightLaser_CS)this.CSLaserS.Clone();
        if(this.CSLaserR!=null)
            baseEmitterCs.CSLaserR=(BaseRadialLaser_CS)this.CSLaserR.Clone();
        if(this.CSLaserB!=null)
            baseEmitterCs.CSLaserB=(BaseBendLaser_CS)this.CSLaserB.Clone();
        if(this.CSEnemy!=null)
            baseEmitterCs.CSEnemy=(BaseEnemyPlane_Touhou)this.CSEnemy.Clone();
        if(this.CSEffect!=null)
            baseEmitterCs.CSEffect=(BaseEffect_CS)this.CSEffect.Clone();
        return (object)baseEmitterCs;
    }

    public override

    boolean HitCheck(BaseObject MyPlane){
        return false;
    }
}
