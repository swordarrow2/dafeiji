package com.meng.stg.thsss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.meng.stg.backup.dialogs.ErrDialog;
import com.meng.stg.helpers.BaseEmitter_CS;
import com.meng.stg.helpers.EmitterMode;
import com.meng.stg.helpers.Event;
import com.meng.stg.helpers.EventGroup;

import java.util.HashSet;

public class CS_Data {
    private string[] MBGtext;

    public List<BaseEmitter_CS> EmitterList { get; private set; }

    public int TimeTotal { get; private set; }

    public CS_Data() {
    }

    public CS_Data(string FileName) {
        MBGtext=File.ReadAllLines(FileName,Encoding.UTF8);
        EmitterList=new List<BaseEmitter_CS>();
    }

    public void String2Data(StageDataPackage StageData) {
        EmitterList.Clear();
        int num1 = 0;
        string[] mbGtext1 = MBGtext;
        int index1 = num1;
        int num2 = index1+1;
        if(mbGtext1[index1]!="Crazy Storm Data 1.01") {
            int num3 = (int)MessageBox.Show("当前暂不支持除1.01以外的数据格式","错误",MessageBoxButtons.OK,MessageBoxIcon.Hand);
        } else {
            string[] mbGtext2 = MBGtext;
            int index2 = num2;
            int num4 = index2+1;
            string str1 = mbGtext2[index2];
            if(str1.Contains("Types")) {
                int num5 = int.Parse(str1.Split(' ')[0]);
                for(int index3 = 0;index3<num5;++index3)
                    MBGtext[num4++].Split('_');
                str1=MBGtext[num4++];
            }
            if(str1.Contains("GlobalEvents")) {
                int num5 = int.Parse(str1.Split(' ')[0]);
                for(int index3 = 0;index3<num5;++index3) {
                    string str2 = MBGtext[num4++];
                }
                str1=MBGtext[num4++];
            }
            if(str1.Contains("Sounds")) {
                int num5 = int.Parse(str1.Split(' ')[0]);
                for(int index3 = 0;index3<num5;++index3) {
                    string str2 = MBGtext[num4++];
                }
                string str3 = MBGtext[num4++];
            }
            string[] mbGtext3 = MBGtext;
            int index4 = num4;
            int num6 = index4+1;
            TimeTotal=int.Parse(mbGtext3[index4].Split(':')[1]);
            for(int index3 = 0;index3<4;++index3) {
                string str2 = MBGtext[num6++];
                if(str2.Split(':')[1].Split(',')[0]!="empty") {
                    int num5 = int.Parse(str2.Split(':')[1].Split(',')[3]);
                    for(int index5 = 0;index5<num5;++index5) {
                        string[] strArray = MBGtext[num6++].Split(',');
                        EmitterMode EmitterMode = EmitterMode.Bullet;
                        if(strArray.Length>91) {
                            if(bool.Parse(strArray[81]))
                                EmitterMode=EmitterMode.Enemy;
                            else if(int.Parse(strArray[91])>0)
                                EmitterMode=EmitterMode.Effect;
                        }
                        BaseEmitter_CS baseEmitterCs = new BaseEmitter_CS(StageData,this,EmitterMode);
                        if(baseEmitterCs.EmitterMode==EmitterMode.Enemy) {
                            EnemyFactory ef = new EnemyFactory(strArray[82]);
                            ef.HealthPoint=int.Parse(strArray[83]);
                            ef.RedCount=int.Parse(strArray[84]);
                            ef.BlueCount=int.Parse(strArray[85]);
                            ef.ColorType=byte.Parse(strArray[86]);
                            ef.BackFire=bool.Parse(strArray[87]);
                            ef.ClearRadius=int.Parse(strArray[88]);
                            ef.GreenCount=int.Parse(strArray[89]);
                            ef.StarFall=(strArray[90]!="0");
                            baseEmitterCs.SubBullet=ef.GenerateEnemy(StageData);
                        }
                        baseEmitterCs.ID=int.Parse(strArray[0]);
                        baseEmitterCs.LayerID=int.Parse(strArray[1]);
                        baseEmitterCs.BindingState=bool.Parse(strArray[2]);
                        baseEmitterCs.BindingID=int.Parse(strArray[3]);
                        baseEmitterCs.BindWithDirection=bool.Parse(strArray[4]);
                        baseEmitterCs.CS_Position=new PointF(float.Parse(strArray[6]),float.Parse(strArray[7]));
                        baseEmitterCs.StartTime=int.Parse(strArray[8]);
                        baseEmitterCs.Duration=int.Parse(strArray[9]);
                        baseEmitterCs.EmitPoint=new PointF(float.Parse(strArray[10]),float.Parse(strArray[11]));
                        baseEmitterCs.EmitRadius=(float)int.Parse(strArray[12]);
                        baseEmitterCs.RadiusDirection=(double)float.Parse(strArray[13]);
                        string str3 = strArray[14].Replace("{","").Replace("}","");
                        baseEmitterCs.RadiusDirectionPoint=new PointF(float.Parse(str3.Split(' ')[0].Split(':')[1]),float.Parse(str3.Split(' ')[1].Split(':')[1]));
                        baseEmitterCs.Way=int.Parse(strArray[15]);
                        baseEmitterCs.Circle=int.Parse(strArray[16]);
                        baseEmitterCs.EmitDirection=(double)float.Parse(strArray[17]);
                        string str4 = strArray[18].Replace("{","").Replace("}","");
                        baseEmitterCs.EmitDirectionPoint=new PointF(float.Parse(str4.Split(' ')[0].Split(':')[1]),float.Parse(str4.Split(' ')[1].Split(':')[1]));
                        baseEmitterCs.Range=(double)int.Parse(strArray[19]);
                        baseEmitterCs.Velocity=float.Parse(strArray[20]);
                        baseEmitterCs.DirectionDegree=(double)float.Parse(strArray[21]);
                        string str5 = strArray[22].Replace("{","").Replace("}","");
                        baseEmitterCs.DestPoint=new PointF(float.Parse(str5.Split(' ')[0].Split(':')[1]),float.Parse(str5.Split(' ')[1].Split(':')[1]));
                        baseEmitterCs.AccelerateCS=float.Parse(strArray[23]);
                        baseEmitterCs.AccDirection=(double)float.Parse(strArray[24]);
                        baseEmitterCs.SubBullet.LifeTime=int.Parse(strArray[26]);
                        baseEmitterCs.SubBullet.Type=int.Parse(strArray[27]);
                        baseEmitterCs.SubBullet.ScaleWidth=float.Parse(strArray[28]);
                        baseEmitterCs.SubBullet.ScaleLength=float.Parse(strArray[29]);
                        baseEmitterCs.SubBullet.ColorValue=Color.FromArgb(int.Parse(strArray[30]),int.Parse(strArray[31]),int.Parse(strArray[32]));
                        baseEmitterCs.SubBullet.TransparentValueF=(float)(int.Parse(strArray[33])*(int)byte.MaxValue/100);
                        baseEmitterCs.SubBullet.AngleDegree=(double)float.Parse(strArray[34])+180.0;
                        baseEmitterCs.SubBullet.AngleWithDirection=bool.Parse(strArray[36]);
                        baseEmitterCs.SubBullet.Velocity=float.Parse(strArray[37]);
                        baseEmitterCs.SubBullet.DirectionDegree=(double)float.Parse(strArray[38]);
                        baseEmitterCs.SubBullet.DestPoint=new PointF(float.Parse(str5.Split(' ')[0].Split(':')[1]),float.Parse(str5.Split(' ')[1].Split(':')[1]));
                        baseEmitterCs.SubBullet.AccelerateCS=float.Parse(strArray[40]);
                        baseEmitterCs.SubBullet.AccDirection=(double)float.Parse(strArray[41]);
                        baseEmitterCs.SubBullet.ScaleX=float.Parse(strArray[43]);
                        baseEmitterCs.SubBullet.ScaleY=float.Parse(strArray[44]);
                        baseEmitterCs.SubBullet.BeginningEffect=bool.Parse(strArray[45]);
                        baseEmitterCs.SubBullet.EndingEffect=bool.Parse(strArray[46]);
                        baseEmitterCs.SubBullet.Active=bool.Parse(strArray[47]);
                        baseEmitterCs.SubBullet.Ghosting=bool.Parse(strArray[48]);
                        baseEmitterCs.SubBullet.OutBound=bool.Parse(strArray[49]);
                        baseEmitterCs.SubBullet.UnRemoveable=bool.Parse(strArray[50]);
                        string str6 = strArray[51];
                        int index6 = 0;
                        while(true) {
                            if(index6<str6.Split('&').Length-1) {
                                string str7 = str6.Split('&')[index6];
                                EventGroup eventGroup = new EventGroup();
                                eventGroup.index=index6;
                                eventGroup.tag=str7.Split('|')[0];
                                eventGroup.t=int.Parse(str7.Split('|')[1]);
                                eventGroup.addtime=int.Parse(str7.Split('|')[2]);
                                int index7 = 0;
                                while(true) {
                                    if(index7<str7.Split('|')[3].Split(';').Length-1) {
                                        Event @event = new Event();
                                        @event.EventString=str7.Split('|')[3].Split(';')[index7];
                                        @event.String2EmitterEvent();
                                        eventGroup.EventList.Add(@event);
                                        ++index7;
                                    } else
                                        break;
                                }
                                baseEmitterCs.EventGroupList.Add(eventGroup);
                                ++index6;
                            } else
                                break;
                        }
                        string str8 = strArray[52];
                        int index8 = 0;
                        while(true) {
                            if(index8<str8.Split('&').Length-1) {
                                string str7 = str8.Split('&')[index8];
                                EventGroup eventGroup = new EventGroup();
                                eventGroup.index=index8;
                                eventGroup.tag=str7.Split('|')[0];
                                eventGroup.t=int.Parse(str7.Split('|')[1]);
                                eventGroup.addtime=int.Parse(str7.Split('|')[2]);
                                int index7 = 0;
                                while(true) {
                                    if(index7<str7.Split('|')[3].Split(';').Length-1) {
                                        Event @event = new Event();
                                        @event.EventString=str7.Split('|')[3].Split(';')[index7];
                                        @event.String2BulletEvent();
                                        eventGroup.EventList.Add(@event);
                                        ++index7;
                                    } else
                                        break;
                                }
                                baseEmitterCs.SubBullet.EventGroupList.Add(eventGroup);
                                ++index8;
                            } else
                                break;
                        }
                        baseEmitterCs.RanX=float.Parse(strArray[53]);
                        baseEmitterCs.RanY=float.Parse(strArray[54]);
                        baseEmitterCs.RanRadius=(float)int.Parse(strArray[55]);
                        baseEmitterCs.RanRadiusDirection=(double)float.Parse(strArray[56]);
                        baseEmitterCs.RanWay=int.Parse(strArray[57]);
                        baseEmitterCs.RanCircle=int.Parse(strArray[58]);
                        baseEmitterCs.RanEmitDirection=(double)float.Parse(strArray[59]);
                        baseEmitterCs.RanRange=(double)int.Parse(strArray[60]);
                        baseEmitterCs.RanVelocity=float.Parse(strArray[61]);
                        baseEmitterCs.RanDirection=(double)float.Parse(strArray[62]);
                        baseEmitterCs.RanAccelerate=float.Parse(strArray[63]);
                        baseEmitterCs.RanAccDirection=(double)float.Parse(strArray[64]);
                        baseEmitterCs.SubBullet.RanAngle=(double)float.Parse(strArray[65]);
                        baseEmitterCs.SubBullet.RanVelocity=float.Parse(strArray[66]);
                        baseEmitterCs.SubBullet.RanDirection=(double)float.Parse(strArray[67]);
                        baseEmitterCs.SubBullet.RanAccelerate=float.Parse(strArray[68]);
                        baseEmitterCs.SubBullet.RanAccDirection=(double)float.Parse(strArray[69]);
                        if(strArray.Length>72) {
                            baseEmitterCs.SubBullet.Cover=bool.Parse(strArray[70]);
                            baseEmitterCs.SubBullet.Rebound=bool.Parse(strArray[71]);
                            baseEmitterCs.SubBullet.Force=bool.Parse(strArray[72]);
                        }
                        if(strArray.Length>73) baseEmitterCs.DeepBinding=bool.Parse(strArray[73]);
                        if(strArray.Length>79) {
                            baseEmitterCs.MotionBinding=bool.Parse(strArray[74]);
                            if(baseEmitterCs.SubBullet is BaseBullet_Touhou) {
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=byte.Parse(strArray[75]);
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Layer=int.Parse(strArray[78]);
                            }
                            baseEmitterCs.SoundName=strArray[76];
                            baseEmitterCs.SpecifySE=bool.Parse(strArray[77]);
                            if(int.Parse(strArray[79])!=0) {
                                baseEmitterCs.SubBullet.Region=int.Parse(strArray[79]);
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).SizeValue=baseEmitterCs.SubBullet.Region*2;
                            }
                        }
                        if(strArray.Length>95) {
                            baseEmitterCs.EffectType=int.Parse(strArray[91]);
                            if(strArray[92]!=null&&StageData.TextureObjectDictionary.ContainsKey(strArray[92]))
                                baseEmitterCs.SubBullet.TxtureObject=StageData.TextureObjectDictionary[strArray[92]];
                            baseEmitterCs.Count=int.Parse(strArray[94]);
                            baseEmitterCs.Count=baseEmitterCs.Count<1 ? 1 : baseEmitterCs.Count;
                            baseEmitterCs.DeltaV=float.Parse(strArray[95]);
                        }
                        if(strArray.Length>110) baseEmitterCs.RDirectionWithDirection=!(strArray[96]=="0");
                        EmitterList.Add(baseEmitterCs);
                        if(EmitterMode==EmitterMode.Bullet) {
                            BaseEmitter_CS b2 = new BaseEmitter_CS(StageData,this,EmitterMode);
                            b2=(BaseEmitter_CS)baseEmitterCs.Clone();
                            b2.SubBullet.Velocity=baseEmitterCs.SubBullet.Velocity*1.5f;
                            b2.SubBullet.AccelerateCS=baseEmitterCs.SubBullet.AccelerateCS*1.5f;
                            EmitterList.Add(b2);
                        }

                    }
                    if(str2.Split(':')[1].Split(',').Length>=7) {
                        int num7 = int.Parse(str2.Split(':')[1].Split(',')[4]);
                        for(int index5 = 0;index5<num7;++index5) {
                            string str3 = MBGtext[num6++];
                            string[] strArray = str3.Split(',');
                            BaseEmitter_CS baseEmitterCs =
                                    !bool.Parse(strArray[29]) ?
                                            (strArray.Length<=61 ?
                                                    new BaseEmitter_CS(StageData,this,EmitterMode.StraightLaser) :
                                                    (!bool.Parse(strArray[61]) ?
                                                            new BaseEmitter_CS(StageData,this,EmitterMode.StraightLaser) :
                                                            new BaseEmitter_CS(StageData,this,EmitterMode.BendLaser))) :
                                            new BaseEmitter_CS(StageData,this,EmitterMode.RadialLaser);
                            baseEmitterCs.ID=int.Parse(strArray[0]);
                            baseEmitterCs.LayerID=int.Parse(strArray[1]);
                            baseEmitterCs.BindingState=bool.Parse(strArray[2]);
                            baseEmitterCs.BindingID=int.Parse(strArray[3]);
                            baseEmitterCs.BindWithDirection=bool.Parse(strArray[4]);
                            baseEmitterCs.CS_Position=new PointF(float.Parse(strArray[6]),float.Parse(strArray[7]));
                            baseEmitterCs.StartTime=int.Parse(strArray[8]);
                            baseEmitterCs.Duration=int.Parse(strArray[9]);
                            baseEmitterCs.EmitRadius=(float)int.Parse(strArray[10]);
                            baseEmitterCs.RadiusDirection=(double)float.Parse(strArray[11]);
                            baseEmitterCs.Way=int.Parse(strArray[13]);
                            baseEmitterCs.Circle=int.Parse(strArray[14]);
                            baseEmitterCs.EmitDirection=(double)float.Parse(strArray[15]);
                            baseEmitterCs.Range=(double)int.Parse(strArray[17]);
                            baseEmitterCs.Velocity=float.Parse(strArray[18]);
                            baseEmitterCs.DirectionDegree=(double)float.Parse(strArray[19]);
                            baseEmitterCs.AccelerateCS=float.Parse(strArray[21]);
                            baseEmitterCs.AccDirection=(double)float.Parse(strArray[22]);
                            baseEmitterCs.SubBullet.LifeTime=int.Parse(strArray[24]);
                            baseEmitterCs.SubBullet.Type=int.Parse(strArray[25]);
                            if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser||baseEmitterCs.EmitterMode==EmitterMode.BendLaser) {
                                baseEmitterCs.SubBullet.ScaleLength=float.Parse(strArray[26]);
                                baseEmitterCs.SubBullet.GhostingCount=int.Parse(strArray[27]);
                            } else {
                                baseEmitterCs.SubBullet.MaxScaleW=(float)int.Parse(strArray[27])/256f;
                                baseEmitterCs.SubBullet.ScaleWidth=baseEmitterCs.SubBullet.MaxScaleW;
                                baseEmitterCs.SubBullet.MaxScaleL=1f;
                                baseEmitterCs.SubBullet.ScaleLength=0.2f;
                            }
                            baseEmitterCs.SubBullet.TransparentValueF=(float)(int.Parse(strArray[28])*(int)byte.MaxValue/100);
                            baseEmitterCs.SubBullet.Velocity=float.Parse(strArray[30]);
                            baseEmitterCs.SubBullet.DirectionDegree=(double)float.Parse(strArray[31]);
                            baseEmitterCs.SubBullet.AccelerateCS=float.Parse(strArray[33]);
                            baseEmitterCs.SubBullet.AccDirection=(double)float.Parse(strArray[34]);
                            baseEmitterCs.SubBullet.Active=bool.Parse(strArray[38]);
                            baseEmitterCs.SubBullet.UnRemoveable=bool.Parse(strArray[40]);
                            string str4 = strArray[42];
                            int index6 = 0;
                            while(true) {
                                if(index6<str4.Split('&').Length-1) {
                                    string str5 = str4.Split('&')[index6];
                                    EventGroup eventGroup = new EventGroup();
                                    eventGroup.index=index6;
                                    eventGroup.tag=str5.Split('|')[0];
                                    eventGroup.t=int.Parse(str5.Split('|')[1]);
                                    eventGroup.addtime=int.Parse(str5.Split('|')[2]);
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str5.Split('|')[3].Split(';').Length-1) {
                                            Event @event = new Event();
                                            @event.EventString=str5.Split('|')[3].Split(';')[index7];
                                            @event.String2EmitterEvent();
                                            eventGroup.EventList.Add(@event);
                                            ++index7;
                                        } else
                                            break;
                                    }
                                    baseEmitterCs.EventGroupList.Add(eventGroup);
                                    ++index6;
                                } else
                                    break;
                            }
                            string str6 = strArray[43];
                            int index8 = 0;
                            while(true) {
                                if(index8<str6.Split('&').Length-1) {
                                    string str5 = str6.Split('&')[index8];
                                    EventGroup eventGroup = new EventGroup();
                                    eventGroup.index=index8;
                                    eventGroup.tag=str5.Split('|')[0];
                                    eventGroup.t=int.Parse(str5.Split('|')[1]);
                                    eventGroup.addtime=int.Parse(str5.Split('|')[2]);
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str5.Split('|')[3].Split(';').Length-1) {
                                            Event @event = new Event();
                                            @event.EventString=str5.Split('|')[3].Split(';')[index7];
                                            @event.String2BulletEvent();
                                            eventGroup.EventList.Add(@event);
                                            ++index7;
                                        } else
                                            break;
                                    }
                                    baseEmitterCs.SubBullet.EventGroupList.Add(eventGroup);
                                    ++index8;
                                } else
                                    break;
                            }
                            baseEmitterCs.RanRadius=(float)int.Parse(strArray[44]);
                            baseEmitterCs.RanRadiusDirection=(double)float.Parse(strArray[45]);
                            baseEmitterCs.RanWay=int.Parse(strArray[46]);
                            baseEmitterCs.RanCircle=int.Parse(strArray[47]);
                            baseEmitterCs.RanEmitDirection=(double)float.Parse(strArray[48]);
                            baseEmitterCs.RanRange=(double)int.Parse(strArray[49]);
                            baseEmitterCs.RanVelocity=float.Parse(strArray[50]);
                            baseEmitterCs.RanDirection=(double)float.Parse(strArray[51]);
                            baseEmitterCs.RanAccelerate=float.Parse(strArray[52]);
                            baseEmitterCs.RanAccDirection=(double)float.Parse(strArray[53]);
                            baseEmitterCs.SubBullet.RanVelocity=float.Parse(strArray[54]);
                            baseEmitterCs.SubBullet.RanDirection=(double)float.Parse(strArray[55]);
                            baseEmitterCs.SubBullet.RanAccelerate=float.Parse(strArray[56]);
                            baseEmitterCs.SubBullet.RanAccDirection=(double)float.Parse(strArray[57]);
                            if(strArray.Length>58)
                                baseEmitterCs.DeepBinding=bool.Parse(strArray[58]);
                            if(strArray.Length>62) {
                                if(baseEmitterCs.SubBullet is BaseBullet_Touhou)
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=byte.Parse(str3.Split(',')[59]);
                                baseEmitterCs.MotionBinding=(bool.Parse(str3.Split(',')[60]) ? 1 : 0)!=0;
                                if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser)
                                    ((Bullet_StraightLaser)baseEmitterCs.SubBullet).LaserHead=(bool.Parse(str3.Split(',')[62]) ? 1 : 0)!=0;
                            }
                            EmitterList.Add(baseEmitterCs);
                        }
                        int num8 = int.Parse(str2.Split(':')[1].Split(',')[5]);
                        for(int index5 = 0;index5<num8;++index5) {
                            string[] strArray = MBGtext[num6++].Split(',');
                            BaseCover_CS baseCoverCs = new BaseCover_CS(StageData);
                            baseCoverCs.MaxScale=1000f;
                            baseCoverCs.ID=int.Parse(strArray[0]);
                            baseCoverCs.LayerID=int.Parse(strArray[1]);
                            baseCoverCs.CS_Position=new PointF(float.Parse(strArray[2]),float.Parse(strArray[3]));
                            baseCoverCs.StartTime=int.Parse(strArray[4]);
                            baseCoverCs.Duration=int.Parse(strArray[5]);
                            baseCoverCs.ScaleWidth=(float)int.Parse(strArray[6]);
                            baseCoverCs.ScaleLength=(float)int.Parse(strArray[7]);
                            baseCoverCs.Roundness=bool.Parse(strArray[8]);
                            baseCoverCs.Type=int.Parse(strArray[9]);
                            baseCoverCs.CtrlID=int.Parse(strArray[10]);
                            baseCoverCs.Velocity=float.Parse(strArray[11]);
                            baseCoverCs.DirectionDegree=(double)float.Parse(strArray[12]);
                            baseCoverCs.AccelerateCS=float.Parse(strArray[14]);
                            baseCoverCs.AccDirection=(double)float.Parse(strArray[15]);
                            string str3 = strArray[17];
                            int index6 = 0;
                            while(true) {
                                if(index6<str3.Split('&').Length-1) {
                                    string str4 = str3.Split('&')[index6];
                                    EventGroup eventGroup = new EventGroup();
                                    eventGroup.index=index6;
                                    eventGroup.tag=str4.Split('|')[0];
                                    eventGroup.t=int.Parse(str4.Split('|')[1]);
                                    eventGroup.addtime=int.Parse(str4.Split('|')[2]);
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str4.Split('|')[3].Split(';').Length-1) {
                                            Event @event = new Event();
                                            @event.EventString=str4.Split('|')[3].Split(';')[index7];
                                            @event.String2EmitterEvent();
                                            eventGroup.EventList.Add(@event);
                                            ++index7;
                                        } else
                                            break;
                                    }
                                    baseCoverCs.EventGroupList.Add(eventGroup);
                                    ++index6;
                                } else
                                    break;
                            }
                            string str5 = strArray[18];
                            int index8 = 0;
                            while(true) {
                                if(index8<str5.Split('&').Length-1) {
                                    string str4 = str5.Split('&')[index8];
                                    EventGroup eventGroup = new EventGroup();
                                    eventGroup.index=index8;
                                    eventGroup.tag=str4.Split('|')[0];
                                    eventGroup.t=int.Parse(str4.Split('|')[1]);
                                    eventGroup.addtime=int.Parse(str4.Split('|')[2]);
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str4.Split('|')[3].Split(';').Length-1) {
                                            Event @event = new Event();
                                            @event.EventString=str4.Split('|')[3].Split(';')[index7];
                                            @event.String2BulletEvent();
                                            eventGroup.EventList.Add(@event);
                                            ++index7;
                                        } else
                                            break;
                                    }
                                    baseCoverCs.SubEventGroupList.Add(eventGroup);
                                    ++index8;
                                } else
                                    break;
                            }
                            baseCoverCs.RanVelocity=float.Parse(strArray[19]);
                            baseCoverCs.RanDirection=(double)float.Parse(strArray[20]);
                            baseCoverCs.RanAccelerate=float.Parse(strArray[21]);
                            baseCoverCs.RanAccDirection=(double)float.Parse(strArray[22]);
                            if(strArray.Length>=24)
                                baseCoverCs.BindingID=int.Parse(strArray[23]);
                            if(strArray.Length>=25&&strArray[24]!="")
                                baseCoverCs.DeepBinding=bool.Parse(strArray[24]);
                            EmitterList.Add((BaseEmitter_CS)baseCoverCs);
                        }
                        int num9 = int.Parse(str2.Split(':')[1].Split(',')[6]);
                        string str7;
                        for(int index5 = 0;index5<num9;++index5)
                            str7=MBGtext[num6++];
                        int num10 = int.Parse(str2.Split(':')[1].Split(',')[7]);
                        for(int index5 = 0;index5<num10;++index5)
                            str7=MBGtext[num6++];
                    }
                }
            }
        }
    }

    private void AddNode(int id,TreeNode treeNode) {
        EmitterList.ForEach((Action<BaseEmitter_CS>)(b => {
        if(b.BindingID!=id)
            return;
        string str1 = !(b is BaseCover_CS) ? b.EmitterMode.ToString() : "Cover";
        TreeNode treeNode1 = new TreeNode(b.ID.ToString());
        TreeNode treeNode2 = treeNode1;
        int id1 = b.ID;
        string str2 = id1.ToString();
        treeNode2.Name=str2;
        TreeNode treeNode3 = treeNode1;
        id1=b.ID;
        string str3 = "ID："+id1.ToString()+"  "+str1;
        treeNode3.Text=str3;
        treeNode.Nodes.Add(treeNode1);
        AddNode(b.ID,treeNode1);
            }));
    }
}