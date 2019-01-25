 package com.meng.stg.helpers.CrazyStorm;

 import com.badlogic.gdx.math.Vector2;

 public class BaseEmitter_CS {//BaseEnemyPlane {
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
        private BaseBullet_CS CSBullet;
        private BaseStraightLaser_CS CSLaserS;
        private BaseRadialLaser_CS CSLaserR;
        private BaseBendLaser_CS CSLaserB;
        private BaseEnemyPlane_Touhou CSEnemy;
        private BaseEffect_CS CSEffect;
        public EmitterMode EmitterMode;

        public BaseObject_CS SubBullet {
            get {
                if(EmitterMode==EmitterMode.Enemy) {
                    return CSEnemy;
                }
                if(EmitterMode==EmitterMode.Effect) {
                    return CSEffect;
                }
                if(EmitterMode==EmitterMode.StraightLaser) {
                    return CSLaserS;
                }
                if(EmitterMode==EmitterMode.RadialLaser) {
                    return CSLaserR;
                }
                if(EmitterMode==EmitterMode.BendLaser) {
                    return CSLaserB;
                }
                return CSBullet;
            }
            set {
                if(EmitterMode==EmitterMode.Enemy) {
                    CSEnemy=(BaseEnemyPlane_Touhou)value;
                } else if(EmitterMode==EmitterMode.Effect) {
                    CSEffect=(BaseEffect_CS)value;
                } else if(EmitterMode==EmitterMode.StraightLaser) {
                    CSLaserS=(BaseStraightLaser_CS)value;
                } else if(EmitterMode==EmitterMode.RadialLaser) {
                    CSLaserR=(BaseRadialLaser_CS)value;
                } else if(EmitterMode==EmitterMode.BendLaser) {
                    CSLaserB=(BaseBendLaser_CS)value;
                } else {
                    CSBullet=(BaseBullet_CS)value;
                }
            }
        }

        public BaseEmitter_CS() {
        }

        public BaseEmitter_CS(StageDataPackage StageData,CS_Data CSData,EmitterMode EmitterMode) {
            this.StageData=StageData;
            this.CSData=CSData;
            EventGroupList=new List<EventGroup>();
            EventsExecutionList=new List<Execution>();
            HealthPoint=1000000f;
            HitEnabled=false;
            Region=0;
            Count=1;
            this.EmitterMode=EmitterMode;
            switch(EmitterMode) {
                case EmitterMode.Bullet:
                    CSBullet=new BaseBullet_CS(StageData);
                    break;
                case EmitterMode.StraightLaser:
                    CSLaserS=new BaseStraightLaser_CS(StageData);
                    break;
                case EmitterMode.RadialLaser:
                    CSLaserR=new BaseRadialLaser_CS(StageData);
                    break;
                case EmitterMode.BendLaser:
                    CSLaserB=new BaseBendLaser_CS(StageData);
                    break;
                case EmitterMode.Effect:
                    CSEffect=new BaseEffect_CS(StageData);
                    break;
            }
        }

        public override void Ctrl() {
            if(Time==StartTime) {
                Velocity+=RanVelocity*(float)Ran.NextPMDouble();
                DirectionDegree+=RanDirection*Ran.NextPMDouble();
                AccelerateCS+=RanAccelerate*(float)Ran.NextPMDouble();
                AccDirection+=RanAccDirection*Ran.NextPMDouble();
            }
            if(LifeTime>StartTime+Duration) {
                LifeTime=StartTime+Duration;
            }
            if(Range!=360.0) {
                RangeShoot=true;
            }
            if(BindingObj!=null&&BindingObj is BaseEnemyPlane_Touhou&&BindingObj.HealthPoint<=0.0) {
                EnemyPlaneList.Remove(this);
            }
            base.Ctrl();
        }

        public override void EventCtrl() {
            EventGroupList.ForEach(a => a.Update(this));
            EventsExecutionList.ForEach(a => a.Update(this));
        }

        public override void Shoot() {
            if(Time<StartTime||Time>StartTime+Duration) {
                return;
            }
            int num1 = Circle+(int)(RanCircle*Ran.NextPMDouble());
            int num2 = num1<1 ? 1 : num1;
            if(!DeepBinding&&(Time-StartTime+1)%num2==0) {
                ShootBullet();
            } else {
                if(!DeepBinding||Time%num2!=0) {
                    return;
                }
                ShootBullet();
            }
        }

        public void ShootBullet() {
            float x = EmitPoint.X;
            float y = EmitPoint.Y;
            Vector2 originalPosition;
            float num1;
            if(x==-99999.0) {
                originalPosition=MyPlane.OriginalPosition;
                num1=originalPosition.X;
            } else if(x==-99998.0) {
                originalPosition=this.OriginalPosition;
                num1=originalPosition.X;
            } else {
                num1=(float)(x-320.0+192.0);
            }
            float num2;
            if(y==-99999.0) {
                originalPosition=MyPlane.OriginalPosition;
                num2=originalPosition.Y;
            } else if(y==-99998.0) {
                originalPosition=this.OriginalPosition;
                num2=originalPosition.Y;
            } else {
                num2=(float)(y-240.0+224.0);
            }
            if(EmitterMode==EmitterMode.StraightLaser||EmitterMode==EmitterMode.RadialLaser||EmitterMode==EmitterMode.BendLaser) {
                originalPosition=this.OriginalPosition;
                num1=originalPosition.X;
                originalPosition=this.OriginalPosition;
                num2=originalPosition.Y;
            }
            Vector2 OriginalPosition = new Vector2(num1+RanX*(float)Ran.NextPMDouble(),num2+RanY*(float)Ran.NextPMDouble());
            double edi = EmitDirection!=-99999.0 ? (EmitDirection+RanEmitDirection*Ran.NextPMDouble()+SubBullet.RanDirection*Ran.NextPMDouble())*Math.PI/180.0 : GetDirection(MyPlane)+(RanEmitDirection*Ran.NextPMDouble()+SubBullet.RanDirection*Ran.NextPMDouble())*Math.PI/180.0;
            double num3 = RadiusDirection!=-99999.0 ? (RadiusDirection+RanRadiusDirection*Ran.NextPMDouble())*Math.PI/180.0 : GetDirection(MyPlane)+RanRadiusDirection*Ran.NextPMDouble()*Math.PI/180.0;
            float num4 = EmitRadius+RanRadius*(float)Ran.NextPMDouble();
            int num5 = Way+(int)(RanWay*Ran.NextPMDouble());
            float num6 = SubBullet.Velocity+SubBullet.RanVelocity*(float)Ran.NextPMDouble();
            float num7 = SubBullet.AccelerateCS+SubBullet.RanAccelerate*(float)Ran.NextPMDouble();
            double num8 = SubBullet.AccDirection+SubBullet.RanAccDirection;
            double num9 = Range+RanRange*Ran.NextPMDouble();
            if(RDirectionWithDirection) num3+=edi;
            double num10 = num9*Math.PI/180.0/num5;
            edi-=(num5-1)*num10/2.0;
            double num11 = num3-(num5-1)*num10/2.0;
            if(EffectType==2) {
                EmitterSaveEnegy3D emitterSaveEnegy3D = new EmitterSaveEnegy3D(StageData,OriginalPosition,CSEffect.ColorValue);
                StageData.SoundPlay("se_ch02.wav");
            } else if(EffectType==3) {
                EmitterGiveOutEnegy3D emitterGiveOutEnegy3D = new EmitterGiveOutEnegy3D(StageData,OriginalPosition,CSEffect.ColorValue);
                StageData.SoundPlay("se_cat00.wav");
                StageData.SoundPlay("se_enep02.wav");
            } else {
                for(int index1 = 0;index1<num5;++index1) {
                    Vector2 p = new Vector2(OriginalPosition.X+num4*(float)Math.Cos(num11),OriginalPosition.Y+num4*(float)Math.Sin(num11));
                    for(int index2 = 0;index2<Count;++index2) {
                        if(EmitterMode==EmitterMode.Bullet||EmitterMode==EmitterMode.StraightLaser||EmitterMode==EmitterMode.RadialLaser||EmitterMode==EmitterMode.BendLaser) {
                            BaseBullet_Touhou b = new BaseBullet_Touhou(StageData);
                            if(CSBullet!=null) {
                                b=(BaseBullet_Touhou)CSBullet.Clone();
                                b.OriginalPosition=p;
                                b.GhostingCount=b.GhostingCount;
                                b.AngleDegree+=CSBullet.RanAngle*Ran.NextPMDouble();
                            } else if(CSLaserS!=null) {
                                b=(BaseBullet_Touhou)CSLaserS.Clone();
                                b.OriginalPosition=p;
                                b.Angle=-1.0*Math.PI/2.0;
                                b.Active=true;
                            } else if(CSLaserR!=null) {
                                b=(BaseBullet_Touhou)CSLaserR.Clone();
                                b.OriginalPosition=p;
                                b.Angle=Math.PI/2.0;
                                b.UnRemoveable=true;
                                b.Active=true;
                            } else if(CSLaserB!=null) {
                                b=(BaseBullet_Touhou)CSLaserB.Clone();
                                b.OriginalPosition=p;
                                b.UnRemoveable=true;
                                b.Active=true;
                            }
                            b.GhostingCount=b.GhostingCount;
                            b.Velocity=num6-index2*DeltaV;
                            b.Direction=edi;
                            b.AccelerateCS=num7;
                            b.AccDirection=num8;
                            b.ID=ID;
                            b.LayerID=LayerID;
                            if(MotionBinding) b.SetBinding(this);
                            BulletList.Add(b);
                            if(EmitterMode==EmitterMode.Bullet) {
                                CSData.EmitterList.ForEach(em => {
                                    if(em.BindingID!=ID) return;
                                    b.UnRemoveable=true;
                                    BaseEmitter_CS baseEmitterCs = (BaseEmitter_CS)em.Clone();
                                    StageData.EnemyPlaneList.Add(baseEmitterCs);
                                    baseEmitterCs.OriginalPosition=p;
                                    baseEmitterCs.LifeTime=SubBullet.LifeTime;
                                    baseEmitterCs.ColorValue=SubBullet.ColorValue;
                                    baseEmitterCs.TransparentValueF=SubBullet.TransparentValueF;
                                    baseEmitterCs.Direction=edi;
                                    baseEmitterCs.DestPoint=SubBullet.DestPoint;
                                    baseEmitterCs.Active=SubBullet.Active;
                                    baseEmitterCs.OutBound=SubBullet.OutBound;
                                    if(baseEmitterCs.BindWithDirection) baseEmitterCs.EmitDirection+=edi*180.0/Math.PI;
                                    baseEmitterCs.SetBinding(b);
                                    if(!baseEmitterCs.DeepBinding) {
                                        baseEmitterCs.Time=Time;
                                        baseEmitterCs.LifeTime=Math.Min(SubBullet.LifeTime+Time,em.StartTime+em.Duration);
                                    }
                                });
                            }
                        } else if(EmitterMode==EmitterMode.Enemy) {
                            BaseEnemyPlane_Touhou enemy = (BaseEnemyPlane_Touhou)SubBullet.Clone();
                            enemy.LifeTime=0;
                            enemy.OriginalPosition=p;
                            enemy.GhostingCount=enemy.GhostingCount;
                            enemy.Velocity=num6-index2*DeltaV;
                            enemy.Direction=edi;
                            enemy.AccelerateCS=num7;
                            enemy.AccDirection=num8;
                            enemy.ID=ID;
                            enemy.LayerID=LayerID;
                            if(MotionBinding) enemy.SetBinding(this);
                            EnemyPlaneList.Add(enemy);
                            CSData.EmitterList.ForEach(em => {
                                if(em.BindingID!=ID) return;
                                BaseEmitter_CS baseEmitterCs = (BaseEmitter_CS)em.Clone();
                                StageData.EnemyPlaneList.Add(baseEmitterCs);
                                baseEmitterCs.OriginalPosition=p;
                                baseEmitterCs.LifeTime=SubBullet.LifeTime;
                                baseEmitterCs.ColorValue=SubBullet.ColorValue;
                                baseEmitterCs.TransparentValueF=SubBullet.TransparentValueF;
                                baseEmitterCs.Direction=edi;
                                baseEmitterCs.DestPoint=SubBullet.DestPoint;
                                baseEmitterCs.Active=SubBullet.Active;
                                baseEmitterCs.OutBound=SubBullet.OutBound;
                                baseEmitterCs.SetBinding(enemy);
                                if(baseEmitterCs.BindWithDirection) baseEmitterCs.EmitDirection+=edi*180.0/Math.PI;
                                if(!baseEmitterCs.DeepBinding) {
                                    baseEmitterCs.Time=Time;
                                    baseEmitterCs.LifeTime=Math.Min(SubBullet.LifeTime+Time,em.StartTime+em.Duration);
                                }
                            });
                        } else if(EmitterMode==EmitterMode.Effect) {
                            BaseEffect_CS baseEffectCs = (BaseEffect_CS)CSEffect.Clone();
                            baseEffectCs.OriginalPosition=p;
                            baseEffectCs.GhostingCount=baseEffectCs.GhostingCount;
                            baseEffectCs.AngleDegree+=CSEffect.RanAngle*Ran.NextPMDouble();
                            baseEffectCs.Velocity=num6-index2*DeltaV;
                            baseEffectCs.Direction=edi;
                            baseEffectCs.AccelerateCS=num7;
                            baseEffectCs.AccDirection=num8;
                            baseEffectCs.ID=ID;
                            baseEffectCs.LayerID=LayerID;
                            if(MotionBinding) baseEffectCs.SetBinding(this);
                            EffectList.Add(baseEffectCs);
                        }
                    }
                    num11+=num10;
                    edi+=num10;
                    if(SpecifySE) {
                        StageData.SoundPlay(SoundName);
                    } else if(EmitterMode==EmitterMode.Bullet) {
                        StageData.SoundPlay("se_tan00a.wav",OriginalPosition.X/BoundRect.Width);
                    } else if(EmitterMode==EmitterMode.StraightLaser||EmitterMode==EmitterMode.RadialLaser) {
                        StageData.SoundPlay("se_lazer00.wav",OriginalPosition.X/BoundRect.Width);
                    }
                }
            }
        }

        public override object Clone() {
            BaseEmitter_CS baseEmitterCs = (BaseEmitter_CS)base.Clone();
            if(CSBullet!=null) baseEmitterCs.CSBullet=(BaseBullet_CS)CSBullet.Clone();
            if(CSLaserS!=null) baseEmitterCs.CSLaserS=(BaseStraightLaser_CS)CSLaserS.Clone();
            if(CSLaserR!=null) baseEmitterCs.CSLaserR=(BaseRadialLaser_CS)CSLaserR.Clone();
            if(CSLaserB!=null) baseEmitterCs.CSLaserB=(BaseBendLaser_CS)CSLaserB.Clone();
            if(CSEnemy!=null) baseEmitterCs.CSEnemy=(BaseEnemyPlane_Touhou)CSEnemy.Clone();
            if(CSEffect!=null) baseEmitterCs.CSEffect=(BaseEffect_CS)CSEffect.Clone();
            return baseEmitterCs;
        }

        public override boolean HitCheck(BaseObject MyPlane) {
            return false;
        }
    }
