﻿package com.meng.stg.helpers.CrazyStorm;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CS_Data {
        private String[] MBGtext;
        public HashSet<BaseEmitter_CS> EmitterList;
        public int TimeTotal;
        public CS_Data() {
        }

        public CS_Data(String FileName) {
            MBGtext=Gdx.files.external(FileName).readString().split("\n");
            EmitterList=new HashSet<BaseEmitter_CS>();
        }

        public void String2Data( ) {
            EmitterList.clear();
            int num1 = 0;
            String[] mbGtext1 = MBGtext;
            int index1 = num1;
            int num2 = index1+1;
            if(mbGtext1[index1]!="Crazy Storm Data 1.01") {
                MessageBox.Show("当前暂不支持除1.01以外的数据格式","错误",MessageBoxButtons.OK,MessageBoxIcon.Hand);
            } else {
                String[] mbGtext2 = MBGtext;
                int index2 = num2;
                int num4 = index2+1;
                String str1 = mbGtext2[index2];
                if(str1.contains("Types")) {
                    int num5 = Integer.parseInt(str1.split("\\s")[0]);
                    for(int index3 = 0;index3<num5;++index3) {
                        MBGtext[num4++].split("_");
                    }
                    str1=MBGtext[num4++];
                }
                if(str1.contains("GlobalEvents")) {
                    int num5 = Integer.parseInt(str1.split("\\s")[0]);
                    for(int index3 = 0;index3<num5;++index3) {
                        String str2 = MBGtext[num4++];
                    }
                    str1=MBGtext[num4++];
                }
                if(str1.contains("Sounds")) {
                    int num5 = Integer.parseInt(str1.split("\\s")[0]);
                    for(int index3 = 0;index3<num5;++index3) {
                        String str2 = MBGtext[num4++];
                    }
                    String str3 = MBGtext[num4++];
                }
                String[] mbGtext3 = MBGtext;
                int index4 = num4;
                int num6 = index4+1;
                TimeTotal=Integer.parseInt(mbGtext3[index4].split(":")[1]);
                for(int index3 = 0;index3<4;++index3) {
                    String str2 = MBGtext[num6++];
                    if(str2.split(":")[1].split(",")[0]!="empty") {
                        int num5 = Integer.parseInt(str2.split(":")[1].split(",")[3]);
                        for(int index5 = 0;index5<num5;++index5) {
                            String[] strArray = MBGtext[num6++].split(",");
                            EmitterMode EmitterMode = EmitterMode.Bullet;
                            if(strArray.length>91) {
                                if(Boolean.parseBoolean(strArray[81])) {
                                    EmitterMode=EmitterMode.Enemy;
                                } else if(Integer.parseInt(strArray[91])>0) {
                                    EmitterMode=EmitterMode.Effect;
                                }
                            }
                            BaseEmitter_CS baseEmitterCs = new BaseEmitter_CS( this,EmitterMode);
                            if(baseEmitterCs.EmitterMode==EmitterMode.Enemy)
                                baseEmitterCs.SubBullet=new EnemyFactory(strArray[82]) {
                                HealthPoint=Integer.parseInt(strArray[83]),
                                RedCount=Integer.parseInt(strArray[84]),
                                BlueCount=Integer.parseInt(strArray[85]),
                                ColorType=byte.Parse(strArray[86]),
                                BackFire=Boolean.parseBoolean(strArray[87]),
                                ClearRadius=Integer.parseInt(strArray[88]),
                                GreenCount=Integer.parseInt(strArray[89]),
                                StarFall=(strArray[90]!="0")
                            }.GenerateEnemy(StageData);
                            baseEmitterCs.ID=Integer.parseInt(strArray[0]);
                            baseEmitterCs.LayerID=Integer.parseInt(strArray[1]);
                            baseEmitterCs.BindingState=Boolean.parseBoolean(strArray[2]);
                            baseEmitterCs.BindingID=Integer.parseInt(strArray[3]);
                            baseEmitterCs.BindWithDirection=Boolean.parseBoolean(strArray[4]);
                            baseEmitterCs.CS_Position=new PointF(float.Parse(strArray[6]),float.Parse(strArray[7]));
                            baseEmitterCs.StartTime=Integer.parseInt(strArray[8]);
                            baseEmitterCs.Duration=Integer.parseInt(strArray[9]);
                            baseEmitterCs.EmitPoint=new PointF(float.Parse(strArray[10]),float.Parse(strArray[11]));
                            baseEmitterCs.EmitRadius=Integer.parseInt(strArray[12]);
                            baseEmitterCs.RadiusDirection=float.Parse(strArray[13]);
                            String str3 = strArray[14].Replace("{","").Replace("}","");
                            baseEmitterCs.RadiusDirectionPoint=new PointF(float.Parse(str3.split("\\s")[0].split(":")[1]),float.Parse(str3.split("\\s")[1].split(":")[1]));
                            baseEmitterCs.Way=Integer.parseInt(strArray[15]);
                            baseEmitterCs.Circle=Integer.parseInt(strArray[16]);
                            baseEmitterCs.EmitDirection=float.Parse(strArray[17]);
                            String str4 = strArray[18].Replace("{","").Replace("}","");
                            baseEmitterCs.EmitDirectionPoint=new PointF(float.Parse(str4.split("\\s")[0].split(":")[1]),float.Parse(str4.split("\\s")[1].split(":")[1]));
                            baseEmitterCs.Range=Integer.parseInt(strArray[19]);
                            baseEmitterCs.Velocity=float.Parse(strArray[20]);
                            baseEmitterCs.DirectionDegree=float.Parse(strArray[21]);
                            String str5 = strArray[22].Replace("{","").Replace("}","");
                            baseEmitterCs.DestPoint=new PointF(float.Parse(str5.split("\\s")[0].split(":")[1]),float.Parse(str5.split("\\s")[1].split(":")[1]));
                            baseEmitterCs.AccelerateCS=float.Parse(strArray[23]);
                            baseEmitterCs.AccDirection=float.Parse(strArray[24]);
                            baseEmitterCs.SubBullet.LifeTime=Integer.parseInt(strArray[26]);
                            baseEmitterCs.SubBullet.Type=Integer.parseInt(strArray[27]);
                            baseEmitterCs.SubBullet.ScaleWidth=float.Parse(strArray[28]);
                            baseEmitterCs.SubBullet.Scalelength=float.Parse(strArray[29]);
                            baseEmitterCs.SubBullet.ColorValue=Color.FromArgb(Integer.parseInt(strArray[30]),Integer.parseInt(strArray[31]),Integer.parseInt(strArray[32]));
                            baseEmitterCs.SubBullet.TransparentValueF=Integer.parseInt(strArray[33])*byte.MaxValue/100;
                            baseEmitterCs.SubBullet.AngleDegree=float.Parse(strArray[34])+180.0;
                            baseEmitterCs.SubBullet.AngleWithDirection=Boolean.parseBoolean(strArray[36]);
                            baseEmitterCs.SubBullet.Velocity=float.Parse(strArray[37]);
                            baseEmitterCs.SubBullet.DirectionDegree=float.Parse(strArray[38]);
                            baseEmitterCs.SubBullet.DestPoint=new PointF(float.Parse(str5.split("\\s")[0].split(":")[1]),float.Parse(str5.split("\\s")[1].split(":")[1]));
                            baseEmitterCs.SubBullet.AccelerateCS=float.Parse(strArray[40]);
                            baseEmitterCs.SubBullet.AccDirection=float.Parse(strArray[41]);
                            baseEmitterCs.SubBullet.ScaleX=float.Parse(strArray[43]);
                            baseEmitterCs.SubBullet.ScaleY=float.Parse(strArray[44]);
                            baseEmitterCs.SubBullet.BeginningEffect=Boolean.parseBoolean(strArray[45]);
                            baseEmitterCs.SubBullet.EndingEffect=Boolean.parseBoolean(strArray[46]);
                            baseEmitterCs.SubBullet.Active=Boolean.parseBoolean(strArray[47]);
                            baseEmitterCs.SubBullet.Ghosting=Boolean.parseBoolean(strArray[48]);
                            baseEmitterCs.SubBullet.OutBound=Boolean.parseBoolean(strArray[49]);
                            baseEmitterCs.SubBullet.UnRemoveable=Boolean.parseBoolean(strArray[50]);
                            String str6 = strArray[51];
                            int index6 = 0;
                            while(true) {
                                if(index6<str6.Split('&').length-1) {
                                    String str7 = str6.Split('&')[index6];
                                    EventGroup eventGroup = new EventGroup {
                                        index=index6,
                                        tag=str7.Split('|')[0],
                                        t=Integer.parseInt(str7.Split('|')[1]),
                                        addtime=Integer.parseInt(str7.Split('|')[2])
                                    };
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str7.Split('|')[3].Split(';').length-1) {
                                            Event @event = new Event {
                                                EventString=str7.Split('|')[3].Split(';')[index7]
                                            };
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
                            String str8 = strArray[52];
                            int index8 = 0;
                            while(true) {
                                if(index8<str8.Split('&').length-1) {
                                    String str7 = str8.Split('&')[index8];
                                    EventGroup eventGroup = new EventGroup {
                                        index=index8,
                                        tag=str7.Split('|')[0],
                                        t=Integer.parseInt(str7.Split('|')[1]),
                                        addtime=Integer.parseInt(str7.Split('|')[2])
                                    };
                                    int index7 = 0;
                                    while(true) {
                                        if(index7<str7.Split('|')[3].Split(';').length-1) {
                                            Event @event = new Event {
                                                EventString=str7.Split('|')[3].Split(';')[index7]
                                            };
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
                            baseEmitterCs.RanRadius=Integer.parseInt(strArray[55]);
                            baseEmitterCs.RanRadiusDirection=float.Parse(strArray[56]);
                            baseEmitterCs.RanWay=Integer.parseInt(strArray[57]);
                            baseEmitterCs.RanCircle=Integer.parseInt(strArray[58]);
                            baseEmitterCs.RanEmitDirection=float.Parse(strArray[59]);
                            baseEmitterCs.RanRange=Integer.parseInt(strArray[60]);
                            baseEmitterCs.RanVelocity=float.Parse(strArray[61]);
                            baseEmitterCs.RanDirection=float.Parse(strArray[62]);
                            baseEmitterCs.RanAccelerate=float.Parse(strArray[63]);
                            baseEmitterCs.RanAccDirection=float.Parse(strArray[64]);
                            baseEmitterCs.SubBullet.RanAngle=float.Parse(strArray[65]);
                            baseEmitterCs.SubBullet.RanVelocity=float.Parse(strArray[66]);
                            baseEmitterCs.SubBullet.RanDirection=float.Parse(strArray[67]);
                            baseEmitterCs.SubBullet.RanAccelerate=float.Parse(strArray[68]);
                            baseEmitterCs.SubBullet.RanAccDirection=float.Parse(strArray[69]);
                            if(strArray.length>72) {
                                baseEmitterCs.SubBullet.Cover=Boolean.parseBoolean(strArray[70]);
                                baseEmitterCs.SubBullet.Rebound=Boolean.parseBoolean(strArray[71]);
                                baseEmitterCs.SubBullet.Force=Boolean.parseBoolean(strArray[72]);
                            }
                            if(strArray.length>73)
                                baseEmitterCs.DeepBinding=Boolean.parseBoolean(strArray[73]);
                            if(strArray.length>79) {
                                baseEmitterCs.MotionBinding=Boolean.parseBoolean(strArray[74]);
                                if(baseEmitterCs.SubBullet is BaseBullet_Touhou) {
                                    ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=byte.Parse(strArray[75]);
                                    ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Layer=Integer.parseInt(strArray[78]);
                                }
                                baseEmitterCs.SoundName=strArray[76];
                                baseEmitterCs.SpecifySE=Boolean.parseBoolean(strArray[77]);
                                if(Integer.parseInt(strArray[79])!=0) {
                                    baseEmitterCs.SubBullet.Region=Integer.parseInt(strArray[79]);
                                    ((BaseBullet_Touhou)baseEmitterCs.SubBullet).SizeValue=baseEmitterCs.SubBullet.Region*2;
                                }
                            }
                            if(strArray.length>95) {
                                baseEmitterCs.EffectType=Integer.parseInt(strArray[91]);
                                if(strArray[92]!=null&&StageData.TextureObjectDictionary.containsKey(strArray[92]))
                                    baseEmitterCs.SubBullet.TxtureObject=StageData.TextureObjectDictionary[strArray[92]];
                                baseEmitterCs.Count=Integer.parseInt(strArray[94]);
                                baseEmitterCs.Count=baseEmitterCs.Count<1 ? 1 : baseEmitterCs.Count;
                                baseEmitterCs.DeltaV=float.Parse(strArray[95]);
                            }
                            if(strArray.length>110) baseEmitterCs.RDirectionWithDirection=!(strArray[96]=="0");
                            EmitterList.Add(baseEmitterCs);
                        }
                        if(str2.split(":")[1].split(",").length>=7) {
                            int num7 = Integer.parseInt(str2.split(":")[1].split(",")[4]);
                            for(int index5 = 0;index5<num7;++index5) {
                                String str3 = MBGtext[num6++];
                                String[] strArray = str3.split(",");
                                BaseEmitter_CS baseEmitterCs = !Boolean.parseBoolean(strArray[29]) ? (strArray.length<=61 ? new BaseEmitter_CS(StageData,this,EmitterMode.StraightLaser) : (!Boolean.parseBoolean(strArray[61]) ? new BaseEmitter_CS(StageData,this,EmitterMode.StraightLaser) : new BaseEmitter_CS(StageData,this,EmitterMode.BendLaser))) : new BaseEmitter_CS(StageData,this,EmitterMode.RadialLaser);
                                baseEmitterCs.ID=Integer.parseInt(strArray[0]);
                                baseEmitterCs.LayerID=Integer.parseInt(strArray[1]);
                                baseEmitterCs.BindingState=Boolean.parseBoolean(strArray[2]);
                                baseEmitterCs.BindingID=Integer.parseInt(strArray[3]);
                                baseEmitterCs.BindWithDirection=Boolean.parseBoolean(strArray[4]);
                                baseEmitterCs.CS_Position=new PointF(float.Parse(strArray[6]),float.Parse(strArray[7]));
                                baseEmitterCs.StartTime=Integer.parseInt(strArray[8]);
                                baseEmitterCs.Duration=Integer.parseInt(strArray[9]);
                                baseEmitterCs.EmitRadius=Integer.parseInt(strArray[10]);
                                baseEmitterCs.RadiusDirection=float.Parse(strArray[11]);
                                baseEmitterCs.Way=Integer.parseInt(strArray[13]);
                                baseEmitterCs.Circle=Integer.parseInt(strArray[14]);
                                baseEmitterCs.EmitDirection=float.Parse(strArray[15]);
                                baseEmitterCs.Range=Integer.parseInt(strArray[17]);
                                baseEmitterCs.Velocity=float.Parse(strArray[18]);
                                baseEmitterCs.DirectionDegree=float.Parse(strArray[19]);
                                baseEmitterCs.AccelerateCS=float.Parse(strArray[21]);
                                baseEmitterCs.AccDirection=float.Parse(strArray[22]);
                                baseEmitterCs.SubBullet.LifeTime=Integer.parseInt(strArray[24]);
                                baseEmitterCs.SubBullet.Type=Integer.parseInt(strArray[25]);
                                if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser||baseEmitterCs.EmitterMode==EmitterMode.BendLaser) {
                                    baseEmitterCs.SubBullet.Scalelength=float.Parse(strArray[26]);
                                    baseEmitterCs.SubBullet.GhostingCount=Integer.parseInt(strArray[27]);
                                } else {
                                    baseEmitterCs.SubBullet.MaxScaleW=Integer.parseInt(strArray[27])/256f;
                                    baseEmitterCs.SubBullet.ScaleWidth=baseEmitterCs.SubBullet.MaxScaleW;
                                    baseEmitterCs.SubBullet.MaxScaleL=1f;
                                    baseEmitterCs.SubBullet.Scalelength=0.2f;
                                }
                                baseEmitterCs.SubBullet.TransparentValueF=Integer.parseInt(strArray[28])*byte.MaxValue/100;
                                baseEmitterCs.SubBullet.Velocity=float.Parse(strArray[30]);
                                baseEmitterCs.SubBullet.DirectionDegree=float.Parse(strArray[31]);
                                baseEmitterCs.SubBullet.AccelerateCS=float.Parse(strArray[33]);
                                baseEmitterCs.SubBullet.AccDirection=float.Parse(strArray[34]);
                                baseEmitterCs.SubBullet.Active=Boolean.parseBoolean(strArray[38]);
                                baseEmitterCs.SubBullet.UnRemoveable=Boolean.parseBoolean(strArray[40]);
                                String str4 = strArray[42];
                                int index6 = 0;
                                while(true) {
                                    if(index6<str4.Split('&').length-1) {
                                        String str5 = str4.Split('&')[index6];
                                        EventGroup eventGroup = new EventGroup {
                                            index=index6,
                                            tag=str5.Split('|')[0],
                                            t=Integer.parseInt(str5.Split('|')[1]),
                                            addtime=Integer.parseInt(str5.Split('|')[2])
                                        };
                                        int index7 = 0;
                                        while(true) {
                                            if(index7<str5.Split('|')[3].Split(';').length-1) {
                                                Event @event = new Event {
                                                    EventString=str5.Split('|')[3].Split(';')[index7]
                                                };
                                                @event.String2EmitterEvent();
                                                eventGroup.EventList.Add(@event);
                                                ++index7;
                                            } else {
                                                break;
                                            }
                                        }
                                        baseEmitterCs.EventGroupList.Add(eventGroup);
                                        ++index6;
                                    } else {
                                        break;
                                    }
                                }
                                String str6 = strArray[43];
                                int index8 = 0;
                                while(true) {
                                    if(index8<str6.Split('&').length-1) {
                                        String str5 = str6.Split('&')[index8];
                                        EventGroup eventGroup = new EventGroup {
                                            index=index8,
                                            tag=str5.Split('|')[0],
                                            t=Integer.parseInt(str5.Split('|')[1]),
                                            addtime=Integer.parseInt(str5.Split('|')[2])
                                        };
                                        int index7 = 0;
                                        while(true) {
                                            if(index7<str5.Split('|')[3].Split(';').length-1) {
                                                Event @event = new Event {
                                                    EventString=str5.Split('|')[3].Split(';')[index7]
                                                };
                                                @event.String2BulletEvent();
                                                eventGroup.EventList.Add(@event);
                                                ++index7;
                                            } else {
                                                break;
                                            }
                                        }
                                        baseEmitterCs.SubBullet.EventGroupList.Add(eventGroup);
                                        ++index8;
                                    } else {
                                        break;
                                    }
                                }
                                baseEmitterCs.RanRadius=Integer.parseInt(strArray[44]);
                                baseEmitterCs.RanRadiusDirection=float.Parse(strArray[45]);
                                baseEmitterCs.RanWay=Integer.parseInt(strArray[46]);
                                baseEmitterCs.RanCircle=Integer.parseInt(strArray[47]);
                                baseEmitterCs.RanEmitDirection=float.Parse(strArray[48]);
                                baseEmitterCs.RanRange=Integer.parseInt(strArray[49]);
                                baseEmitterCs.RanVelocity=float.Parse(strArray[50]);
                                baseEmitterCs.RanDirection=float.Parse(strArray[51]);
                                baseEmitterCs.RanAccelerate=float.Parse(strArray[52]);
                                baseEmitterCs.RanAccDirection=float.Parse(strArray[53]);
                                baseEmitterCs.SubBullet.RanVelocity=float.Parse(strArray[54]);
                                baseEmitterCs.SubBullet.RanDirection=float.Parse(strArray[55]);
                                baseEmitterCs.SubBullet.RanAccelerate=float.Parse(strArray[56]);
                                baseEmitterCs.SubBullet.RanAccDirection=float.Parse(strArray[57]);
                                if(strArray.length>58) baseEmitterCs.DeepBinding=Boolean.parseBoolean(strArray[58]);
                                if(strArray.length>62) {
                                    if(baseEmitterCs.SubBullet is BaseBullet_Touhou) ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=byte.Parse(str3.split(",")[59]);
                                    baseEmitterCs.MotionBinding=(Boolean.parseBoolean(str3.split(",")[60]) ? 1 : 0)!=0;
                                    if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser) ((Bullet_StraightLaser)baseEmitterCs.SubBullet).LaserHead=(Boolean.parseBoolean(str3.split(",")[62]) ? 1 : 0)!=0;
                                }
                                EmitterList.Add(baseEmitterCs);
                            }
                            int num8 = Integer.parseInt(str2.split(":")[1].split(",")[5]);
                            for(int index5 = 0;index5<num8;++index5) {
                                String[] strArray = MBGtext[num6++].split(",");
                                BaseCover_CS baseCoverCs = new BaseCover_CS(StageData) {
                                    MaxScale=1000f,
                                    ID=Integer.parseInt(strArray[0]),
                                    LayerID=Integer.parseInt(strArray[1]),
                                    CS_Position=new PointF(float.Parse(strArray[2]),float.Parse(strArray[3])),
                                    StartTime=Integer.parseInt(strArray[4]),
                                    Duration=Integer.parseInt(strArray[5]),
                                    ScaleWidth=Integer.parseInt(strArray[6]),
                                    Scalelength=Integer.parseInt(strArray[7]),
                                    Roundness=Boolean.parseBoolean(strArray[8]),
                                    Type=Integer.parseInt(strArray[9]),
                                    CtrlID=Integer.parseInt(strArray[10]),
                                    Velocity=float.Parse(strArray[11]),
                                    DirectionDegree=float.Parse(strArray[12]),
                                    AccelerateCS=float.Parse(strArray[14]),
                                    AccDirection=float.Parse(strArray[15])
                                };
                                String str3 = strArray[17];
                                int index6 = 0;
                                while(true) {
                                    if(index6<str3.Split('&').length-1) {
                                        String str4 = str3.Split('&')[index6];
                                        EventGroup eventGroup = new EventGroup {
                                            index=index6,
                                            tag=str4.Split('|')[0],
                                            t=Integer.parseInt(str4.Split('|')[1]),
                                            addtime=Integer.parseInt(str4.Split('|')[2])
                                        };
                                        int index7 = 0;
                                        while(true) {
                                            if(index7<str4.Split('|')[3].Split(';').length-1) {
                                                Event @event = new Event {
                                                    EventString=str4.Split('|')[3].Split(';')[index7]
                                                };
                                                @event.String2EmitterEvent();
                                                eventGroup.EventList.Add(@event);
                                                ++index7;
                                            } else {
                                                break;
                                            }
                                        }
                                        baseCoverCs.EventGroupList.Add(eventGroup);
                                        ++index6;
                                    } else {
                                        break;
                                    }
                                }
                                String str5 = strArray[18];
                                int index8 = 0;
                                while(true) {
                                    if(index8<str5.Split('&').length-1) {
                                        String str4 = str5.Split('&')[index8];
                                        EventGroup eventGroup = new EventGroup {
                                            index=index8,
                                            tag=str4.Split('|')[0],
                                            t=Integer.parseInt(str4.Split('|')[1]),
                                            addtime=Integer.parseInt(str4.Split('|')[2])
                                        };
                                        int index7 = 0;
                                        while(true) {
                                            if(index7<str4.Split('|')[3].Split(';').length-1) {
                                                Event @event = new Event {
                                                    EventString=str4.Split('|')[3].Split(';')[index7]
                                                };
                                                @event.String2BulletEvent();
                                                eventGroup.EventList.Add(@event);
                                                ++index7;
                                            } else {
                                                break;
                                            }
                                        }
                                        baseCoverCs.SubEventGroupList.Add(eventGroup);
                                        ++index8;
                                    } else {
                                        break;
                                    }
                                }
                                baseCoverCs.RanVelocity=float.Parse(strArray[19]);
                                baseCoverCs.RanDirection=float.Parse(strArray[20]);
                                baseCoverCs.RanAccelerate=float.Parse(strArray[21]);
                                baseCoverCs.RanAccDirection=float.Parse(strArray[22]);
                                if(strArray.length>=24) baseCoverCs.BindingID=Integer.parseInt(strArray[23]);
                                if(strArray.length>=25&&strArray[24]!="") baseCoverCs.DeepBinding=Boolean.parseBoolean(strArray[24]);
                                EmitterList.Add(baseCoverCs);
                            }
                            int num9 = Integer.parseInt(str2.split(":")[1].split(",")[6]);
                            String str7;
                            for(int index5 = 0;index5<num9;++index5) {
                                str7=MBGtext[num6++];
                            }
                            int num10 = Integer.parseInt(str2.split(":")[1].split(",")[7]);
                            for(int index5 = 0;index5<num10;++index5) {
                                str7=MBGtext[num6++];
                            }
                        }
                    }
                }
            }
        }

        private void AddNode(int id,TreeNode treeNode) {
            EmitterList.ForEach(b => {
                if(b.BindingID!=id) return;
                String str1 = !(b is BaseCover_CS) ? b.EmitterMode.ToString() : "Cover";
                TreeNode treeNode1 = new TreeNode(b.ID.ToString());
                TreeNode treeNode2 = treeNode1;
                int id1 = b.ID;
                String str2 = id1.ToString();
                treeNode2.Name=str2;
                TreeNode treeNode3 = treeNode1;
                id1=b.ID;
                String str3 = "ID："+id1.ToString()+"  "+str1;
                treeNode3.Text=str3;
                treeNode.Nodes.Add(treeNode1);
                AddNode(b.ID,treeNode1);
            });
        }

}
