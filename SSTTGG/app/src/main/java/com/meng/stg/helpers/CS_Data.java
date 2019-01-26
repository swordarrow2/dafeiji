package com.meng.stg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;

public class CS_Data{
    private String[] MBGtext;
    public HashSet<BaseEmitter_CS> EmitterList;
    public int TimeTotal;

    public CS_Data(){
    }

    public CS_Data(String FileName){
        MBGtext=Gdx.files.external(FileName).readString().split("\n");
        EmitterList=new HashSet<BaseEmitter_CS>();
    }

    public void String2Data(){
        EmitterList.clear();
        int num1=0;
        String[] mbGtext1=MBGtext;
        int index1=num1;
        int num2=index1+1;
        if(mbGtext1[index1]!="Crazy Storm Data 1.01"){
            //       MessageBox.Show("当前暂不支持除1.01以外的数据格式","错误",MessageBoxButtons.OK,MessageBoxIcon.Hand);
        }else{
            String[] mbGtext2=MBGtext;
            int index2=num2;
            int num4=index2+1;
            String str1=mbGtext2[index2];
            if(str1.contains("Types")){
                int num5=Integer.parseInt(str1.split("\\s")[0]);
                for(int index3=0;index3<num5;++index3){
                    MBGtext[num4++].split("_");
                }
                str1=MBGtext[num4++];
            }
            if(str1.contains("GlobalEvents")){
                int num5=Integer.parseInt(str1.split("\\s")[0]);
                for(int index3=0;index3<num5;++index3){
                    String str2=MBGtext[num4++];
                }
                str1=MBGtext[num4++];
            }
            if(str1.contains("Sounds")){
                int num5=Integer.parseInt(str1.split("\\s")[0]);
                for(int index3=0;index3<num5;++index3){
                    String str2=MBGtext[num4++];
                }
                String str3=MBGtext[num4++];
            }
            String[] mbGtext3=MBGtext;
            int index4=num4;
            int num6=index4+1;
            TimeTotal=Integer.parseInt(mbGtext3[index4].split(":")[1]);
            for(int index3=0;index3<4;++index3){
                String str2=MBGtext[num6++];
                if(str2.split(":")[1].split(",")[0]!="empty"){
                    int num5=Integer.parseInt(str2.split(":")[1].split(",")[3]);
                    for(int index5=0;index5<num5;++index5){
                        String[] strArray=MBGtext[num6++].split(",");
                        EmitterMode emitterMode=EmitterMode.Bullet;
                        if(strArray.length>91){
                            if(Boolean.parseBoolean(strArray[81])){
                                emitterMode=EmitterMode.Enemy;
                            }else if(Integer.parseInt(strArray[91])>0){
                                emitterMode=EmitterMode.Effect;
                            }
                        }
                        BaseEmitter_CS baseEmitterCs=new BaseEmitter_CS(this,emitterMode);
                        if(baseEmitterCs.emitterMode==EmitterMode.Enemy){
                            EnemyFactory ef=new EnemyFactory(strArray[82]);
                            ef.HealthPoint=Integer.parseInt(strArray[83]);
                            ef.RedCount=Integer.parseInt(strArray[84]);
                            ef.BlueCount=Integer.parseInt(strArray[85]);
                            ef.ColorType=Byte.parseByte(strArray[86]);
                            ef.BackFire=Boolean.parseBoolean(strArray[87]);
                            ef.ClearRadius=Integer.parseInt(strArray[88]);
                            ef.GreenCount=Integer.parseInt(strArray[89]);
                            ef.StarFall=(strArray[90]!="0");
                            baseEmitterCs.SubBullet=ef.GenerateEnemy(StageData);
                        }
                        baseEmitterCs.ID=Integer.parseInt(strArray[0]);
                        baseEmitterCs.LayerID=Integer.parseInt(strArray[1]);
                        baseEmitterCs.BindingState=Boolean.parseBoolean(strArray[2]);
                        baseEmitterCs.BindingID=Integer.parseInt(strArray[3]);
                        baseEmitterCs.BindWithDirection=Boolean.parseBoolean(strArray[4]);
                        baseEmitterCs.CS_Position=new Vector2(Float.parseFloat(strArray[6]),Float.parseFloat(strArray[7]));
                        baseEmitterCs.StartTime=Integer.parseInt(strArray[8]);
                        baseEmitterCs.Duration=Integer.parseInt(strArray[9]);
                        baseEmitterCs.EmitPoint=new Vector2(Float.parseFloat(strArray[10]),Float.parseFloat(strArray[11]));
                        baseEmitterCs.EmitRadius=Integer.parseInt(strArray[12]);
                        baseEmitterCs.RadiusDirection=Float.parseFloat(strArray[13]);
                        String str3=strArray[14].replace("\\{","").replace("\\}","");
                        baseEmitterCs.RadiusDirectionPoint=new Vector2(Float.parseFloat(str3.split("\\s")[0].split(":")[1]),Float.parseFloat(str3.split("\\s")[1].split(":")[1]));
                        baseEmitterCs.Way=Integer.parseInt(strArray[15]);
                        baseEmitterCs.Circle=Integer.parseInt(strArray[16]);
                        baseEmitterCs.EmitDirection=Float.parseFloat(strArray[17]);
                        String str4=strArray[18].replace("\\{","").replace("\\}","");
                        baseEmitterCs.EmitDirectionPoint=new Vector2(Float.parseFloat(str4.split("\\s")[0].split(":")[1]),Float.parseFloat(str4.split("\\s")[1].split(":")[1]));
                        baseEmitterCs.Range=Integer.parseInt(strArray[19]);
                        baseEmitterCs.Velocity=Float.parseFloat(strArray[20]);
                        baseEmitterCs.DirectionDegree=Float.parseFloat(strArray[21]);
                        String str5=strArray[22].replace("\\{","").replace("\\}","");
                        baseEmitterCs.DestPoint=new Vector2(Float.parseFloat(str5.split("\\s")[0].split(":")[1]),Float.parseFloat(str5.split("\\s")[1].split(":")[1]));
                        baseEmitterCs.AccelerateCS=Float.parseFloat(strArray[23]);
                        baseEmitterCs.AccDirection=Float.parseFloat(strArray[24]);
                        baseEmitterCs.SubBullet.LifeTime=Integer.parseInt(strArray[26]);
                        baseEmitterCs.SubBullet.Type=Integer.parseInt(strArray[27]);
                        baseEmitterCs.SubBullet.ScaleWidth=Float.parseFloat(strArray[28]);
                        baseEmitterCs.SubBullet.Scalelength=Float.parseFloat(strArray[29]);
                        baseEmitterCs.SubBullet.ColorValue=new Color(Integer.parseInt(strArray[30]),Integer.parseInt(strArray[31]),Integer.parseInt(strArray[32]),255);
                        baseEmitterCs.SubBullet.TransparentValueF=Integer.parseInt(strArray[33])*Byte.MAX_VALUE/100;
                        baseEmitterCs.SubBullet.AngleDegree=Float.parseFloat(strArray[34])+180.0;
                        baseEmitterCs.SubBullet.AngleWithDirection=Boolean.parseBoolean(strArray[36]);
                        baseEmitterCs.SubBullet.Velocity=Float.parseFloat(strArray[37]);
                        baseEmitterCs.SubBullet.DirectionDegree=Float.parseFloat(strArray[38]);
                        baseEmitterCs.SubBullet.DestPoint=new Vector2(Float.parseFloat(str5.split("\\s")[0].split(":")[1]),Float.parseFloat(str5.split("\\s")[1].split(":")[1]));
                        baseEmitterCs.SubBullet.AccelerateCS=Float.parseFloat(strArray[40]);
                        baseEmitterCs.SubBullet.AccDirection=Float.parseFloat(strArray[41]);
                        baseEmitterCs.SubBullet.ScaleX=Float.parseFloat(strArray[43]);
                        baseEmitterCs.SubBullet.ScaleY=Float.parseFloat(strArray[44]);
                        baseEmitterCs.SubBullet.BeginningEffect=Boolean.parseBoolean(strArray[45]);
                        baseEmitterCs.SubBullet.EndingEffect=Boolean.parseBoolean(strArray[46]);
                        baseEmitterCs.SubBullet.Active=Boolean.parseBoolean(strArray[47]);
                        baseEmitterCs.SubBullet.Ghosting=Boolean.parseBoolean(strArray[48]);
                        baseEmitterCs.SubBullet.OutBound=Boolean.parseBoolean(strArray[49]);
                        baseEmitterCs.SubBullet.UnRemoveable=Boolean.parseBoolean(strArray[50]);
                        String str6=strArray[51];
                        int index6=0;
                        while(true){
                            if(index6<str6.split("&").length-1){
                                String str7=str6.split("&")[index6];
                                EventGroup eventGroup=new EventGroup();
                                eventGroup.index=index6;
                                eventGroup.tag=str7.split("\\|")[0];
                                eventGroup.t=Integer.parseInt(str7.split("\\|")[1]);
                                eventGroup.addtime=Integer.parseInt(str7.split("\\|")[2]);

                                int index7=0;
                                while(true){
                                    if(index7<str7.split("\\|")[3].split(";").length-1){
                                        Event _event=new Event();
                                        _event.EventString=str7.split("\\|")[3].split(";")[index7];
                                        _event.String2EmitterEvent();
                                        eventGroup.EventList.Add(_event);
                                        ++index7;
                                    }else
                                        break;
                                }
                                baseEmitterCs.EventGroupList.Add(eventGroup);
                                ++index6;
                            }else
                                break;
                        }
                        String str8=strArray[52];
                        int index8=0;
                        while(true){
                            if(index8<str8.split("&").length-1){
                                String str7=str8.split("&")[index8];
                                EventGroup eventGroup=new EventGroup();
                                eventGroup.index=index8;
                                eventGroup.tag=str7.split("\\|")[0];
                                eventGroup.t=Integer.parseInt(str7.split("\\|")[1]);
                                eventGroup.addtime=Integer.parseInt(str7.split("\\|")[2]);

                                int index7=0;
                                while(true){
                                    if(index7<str7.split("\\|")[3].split(";").length-1){
                                        Event _event=new Event();
                                        _event.EventString=str7.split("\\|")[3].split(";")[index7];
                                        _event.String2BulletEvent();
                                        eventGroup.EventList.Add(_event);
                                        ++index7;
                                    }else
                                        break;
                                }
                                baseEmitterCs.SubBullet.EventGroupList.Add(eventGroup);
                                ++index8;
                            }else
                                break;
                        }
                        baseEmitterCs.RanX=Float.parseFloat(strArray[53]);
                        baseEmitterCs.RanY=Float.parseFloat(strArray[54]);
                        baseEmitterCs.RanRadius=Integer.parseInt(strArray[55]);
                        baseEmitterCs.RanRadiusDirection=Float.parseFloat(strArray[56]);
                        baseEmitterCs.RanWay=Integer.parseInt(strArray[57]);
                        baseEmitterCs.RanCircle=Integer.parseInt(strArray[58]);
                        baseEmitterCs.RanEmitDirection=Float.parseFloat(strArray[59]);
                        baseEmitterCs.RanRange=Integer.parseInt(strArray[60]);
                        baseEmitterCs.RanVelocity=Float.parseFloat(strArray[61]);
                        baseEmitterCs.RanDirection=Float.parseFloat(strArray[62]);
                        baseEmitterCs.RanAccelerate=Float.parseFloat(strArray[63]);
                        baseEmitterCs.RanAccDirection=Float.parseFloat(strArray[64]);
                        baseEmitterCs.SubBullet.RanAngle=Float.parseFloat(strArray[65]);
                        baseEmitterCs.SubBullet.RanVelocity=Float.parseFloat(strArray[66]);
                        baseEmitterCs.SubBullet.RanDirection=Float.parseFloat(strArray[67]);
                        baseEmitterCs.SubBullet.RanAccelerate=Float.parseFloat(strArray[68]);
                        baseEmitterCs.SubBullet.RanAccDirection=Float.parseFloat(strArray[69]);
                        if(strArray.length>72){
                            baseEmitterCs.SubBullet.Cover=Boolean.parseBoolean(strArray[70]);
                            baseEmitterCs.SubBullet.Rebound=Boolean.parseBoolean(strArray[71]);
                            baseEmitterCs.SubBullet.Force=Boolean.parseBoolean(strArray[72]);
                        }
                        if(strArray.length>73)
                            baseEmitterCs.DeepBinding=Boolean.parseBoolean(strArray[73]);
                        if(strArray.length>79){
                            baseEmitterCs.MotionBinding=Boolean.parseBoolean(strArray[74]);
                            if(baseEmitterCs.SubBullet is BaseBullet_Touhou){
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=Byte.parseByte(strArray[75]);
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Layer=Integer.parseInt(strArray[78]);
                            }
                            baseEmitterCs.SoundName=strArray[76];
                            baseEmitterCs.SpecifySE=Boolean.parseBoolean(strArray[77]);
                            if(Integer.parseInt(strArray[79])!=0){
                                baseEmitterCs.SubBullet.Region=Integer.parseInt(strArray[79]);
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).SizeValue=baseEmitterCs.SubBullet.Region*2;
                            }
                        }
                        if(strArray.length>95){
                            baseEmitterCs.EffectType=Integer.parseInt(strArray[91]);
                            if(strArray[92]!=null&&StageData.TextureObjectDictionary.containsKey(strArray[92]))
                                baseEmitterCs.SubBullet.TxtureObject=StageData.TextureObjectDictionary[strArray[92]];
                            baseEmitterCs.Count=Integer.parseInt(strArray[94]);
                            baseEmitterCs.Count=baseEmitterCs.Count<1?1:baseEmitterCs.Count;
                            baseEmitterCs.DeltaV=Float.parseFloat(strArray[95]);
                        }
                        if(strArray.length>110)
                            baseEmitterCs.RDirectionWithDirection=!(strArray[96]=="0");
                        EmitterList.Add(baseEmitterCs);
                    }
                    if(str2.split(":")[1].split(",").length>=7){
                        int num7=Integer.parseInt(str2.split(":")[1].split(",")[4]);
                        for(int index5=0;index5<num7;++index5){
                            String str3=MBGtext[num6++];
                            String[] strArray=str3.split(",");
                            BaseEmitter_CS baseEmitterCs=
                                    !Boolean.parseBoolean(strArray[29])?
                                    (strArray.length<=61?
                                            new BaseEmitter_CS(this,EmitterMode.StraightLaser):
                                            (!Boolean.parseBoolean(strArray[61])?
                                                    new BaseEmitter_CS(this,EmitterMode.StraightLaser):
                                                    new BaseEmitter_CS(this,EmitterMode.BendLaser)))
                                    :new BaseEmitter_CS(this,EmitterMode.RadialLaser);
                            baseEmitterCs.ID=Integer.parseInt(strArray[0]);
                            baseEmitterCs.LayerID=Integer.parseInt(strArray[1]);
                            baseEmitterCs.BindingState=Boolean.parseBoolean(strArray[2]);
                            baseEmitterCs.BindingID=Integer.parseInt(strArray[3]);
                            baseEmitterCs.BindWithDirection=Boolean.parseBoolean(strArray[4]);
                            baseEmitterCs.CS_Position=new Vector2(Float.parseFloat(strArray[6]),Float.parseFloat(strArray[7]));
                            baseEmitterCs.StartTime=Integer.parseInt(strArray[8]);
                            baseEmitterCs.Duration=Integer.parseInt(strArray[9]);
                            baseEmitterCs.EmitRadius=Integer.parseInt(strArray[10]);
                            baseEmitterCs.RadiusDirection=Float.parseFloat(strArray[11]);
                            baseEmitterCs.Way=Integer.parseInt(strArray[13]);
                            baseEmitterCs.Circle=Integer.parseInt(strArray[14]);
                            baseEmitterCs.EmitDirection=Float.parseFloat(strArray[15]);
                            baseEmitterCs.Range=Integer.parseInt(strArray[17]);
                            baseEmitterCs.Velocity=Float.parseFloat(strArray[18]);
                            baseEmitterCs.DirectionDegree=Float.parseFloat(strArray[19]);
                            baseEmitterCs.AccelerateCS=Float.parseFloat(strArray[21]);
                            baseEmitterCs.AccDirection=Float.parseFloat(strArray[22]);
                            baseEmitterCs.SubBullet.LifeTime=Integer.parseInt(strArray[24]);
                            baseEmitterCs.SubBullet.Type=Integer.parseInt(strArray[25]);
                            if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser||baseEmitterCs.EmitterMode==EmitterMode.BendLaser){
                                baseEmitterCs.SubBullet.Scalelength=Float.parseFloat(strArray[26]);
                                baseEmitterCs.SubBullet.GhostingCount=Integer.parseInt(strArray[27]);
                            }else{
                                baseEmitterCs.SubBullet.MaxScaleW=Integer.parseInt(strArray[27])/256f;
                                baseEmitterCs.SubBullet.ScaleWidth=baseEmitterCs.SubBullet.MaxScaleW;
                                baseEmitterCs.SubBullet.MaxScaleL=1f;
                                baseEmitterCs.SubBullet.Scalelength=0.2f;
                            }
                            baseEmitterCs.SubBullet.TransparentValueF=Integer.parseInt(strArray[28])*byte.MaxValue/100;
                            baseEmitterCs.SubBullet.Velocity=Float.parseFloat(strArray[30]);
                            baseEmitterCs.SubBullet.DirectionDegree=Float.parseFloat(strArray[31]);
                            baseEmitterCs.SubBullet.AccelerateCS=Float.parseFloat(strArray[33]);
                            baseEmitterCs.SubBullet.AccDirection=Float.parseFloat(strArray[34]);
                            baseEmitterCs.SubBullet.Active=Boolean.parseBoolean(strArray[38]);
                            baseEmitterCs.SubBullet.UnRemoveable=Boolean.parseBoolean(strArray[40]);
                            String str4=strArray[42];
                            int index6=0;
                            while(true){
                                if(index6<str4.split("&").length-1){
                                    String str5=str4.split("&")[index6];
                                    EventGroup eventGroup=new EventGroup {
                                        index=index6,
                                                tag=str5.split("\\|")[0],
                                                t=Integer.parseInt(str5.split("\\|")[1]),
                                                addtime=Integer.parseInt(str5.split("\\|")[2])
                                    } ;
                                    int index7=0;
                                    while(true){
                                        if(index7<str5.split("\\|")[3].split(";").length-1){
                                            Event _event=new Event {
                                                EventString=str5.split("\\|")[3].split(";")[index7]
                                            } ;
                                            _event.String2EmitterEvent();
                                            eventGroup.EventList.Add(_event);
                                            ++index7;
                                        }else{
                                            break;
                                        }
                                    }
                                    baseEmitterCs.EventGroupList.Add(eventGroup);
                                    ++index6;
                                }else{
                                    break;
                                }
                            }
                            String str6=strArray[43];
                            int index8=0;
                            while(true){
                                if(index8<str6.split("&").length-1){
                                    String str5=str6.split("&")[index8];
                                    EventGroup eventGroup=new EventGroup {
                                        index=index8,
                                                tag=str5.split("\\|")[0],
                                                t=Integer.parseInt(str5.split("\\|")[1]),
                                                addtime=Integer.parseInt(str5.split("\\|")[2])
                                    } ;
                                    int index7=0;
                                    while(true){
                                        if(index7<str5.split("\\|")[3].split(";").length-1){
                                            Event _event=new Event {
                                                EventString=str5.split("\\|")[3].split(";")[index7]
                                            } ;
                                            _event.String2BulletEvent();
                                            eventGroup.EventList.Add(_event);
                                            ++index7;
                                        }else{
                                            break;
                                        }
                                    }
                                    baseEmitterCs.SubBullet.EventGroupList.Add(eventGroup);
                                    ++index8;
                                }else{
                                    break;
                                }
                            }
                            baseEmitterCs.RanRadius=Integer.parseInt(strArray[44]);
                            baseEmitterCs.RanRadiusDirection=Float.parseFloat(strArray[45]);
                            baseEmitterCs.RanWay=Integer.parseInt(strArray[46]);
                            baseEmitterCs.RanCircle=Integer.parseInt(strArray[47]);
                            baseEmitterCs.RanEmitDirection=Float.parseFloat(strArray[48]);
                            baseEmitterCs.RanRange=Integer.parseInt(strArray[49]);
                            baseEmitterCs.RanVelocity=Float.parseFloat(strArray[50]);
                            baseEmitterCs.RanDirection=Float.parseFloat(strArray[51]);
                            baseEmitterCs.RanAccelerate=Float.parseFloat(strArray[52]);
                            baseEmitterCs.RanAccDirection=Float.parseFloat(strArray[53]);
                            baseEmitterCs.SubBullet.RanVelocity=Float.parseFloat(strArray[54]);
                            baseEmitterCs.SubBullet.RanDirection=Float.parseFloat(strArray[55]);
                            baseEmitterCs.SubBullet.RanAccelerate=Float.parseFloat(strArray[56]);
                            baseEmitterCs.SubBullet.RanAccDirection=Float.parseFloat(strArray[57]);
                            if(strArray.length>58)
                                baseEmitterCs.DeepBinding=Boolean.parseBoolean(strArray[58]);
                            if(strArray.length>62){
                                if(baseEmitterCs.SubBullet is BaseBullet_Touhou)
                                ((BaseBullet_Touhou)baseEmitterCs.SubBullet).Reflect=Byte.parseByte(str3.split(",")[59]);
                                baseEmitterCs.MotionBinding=(Boolean.parseBoolean(str3.split(",")[60])?1:0)!=0;
                                if(baseEmitterCs.EmitterMode==EmitterMode.StraightLaser)
                                    ((Bullet_StraightLaser)baseEmitterCs.SubBullet).LaserHead=(Boolean.parseBoolean(str3.split(",")[62])?1:0)!=0;
                            }
                            EmitterList.Add(baseEmitterCs);
                        }
                        int num8=Integer.parseInt(str2.split(":")[1].split(",")[5]);
                        for(int index5=0;index5<num8;++index5){
                            String[] strArray=MBGtext[num6++].split(",");
                            BaseCover_CS baseCoverCs=new BaseCover_CS(StageData){
                                MaxScale=1000f,
                                ID=Integer.parseInt(strArray[0]),
                                LayerID=Integer.parseInt(strArray[1]),
                                CS_Position=new

                                Vector2(Float.parseFloat(strArray[2]),Float.

                                parseFloat(strArray[3])),
                                StartTime=Integer.parseInt(strArray[4]),
                                Duration=Integer.parseInt(strArray[5]),
                                ScaleWidth=Integer.parseInt(strArray[6]),
                                Scalelength=Integer.parseInt(strArray[7]),
                                Roundness=Boolean.parseBoolean(strArray[8]),
                                Type=Integer.parseInt(strArray[9]),
                                CtrlID=Integer.parseInt(strArray[10]),
                                Velocity=Float.parseFloat(strArray[11]),
                                DirectionDegree=Float.parseFloat(strArray[12]),
                                AccelerateCS=Float.parseFloat(strArray[14]),
                                AccDirection=Float.parseFloat(strArray[15])
                            };
                            String str3=strArray[17];
                            int index6=0;
                            while(true){
                                if(index6<str3.split("&").length-1){
                                    String str4=str3.split("&")[index6];
                                    EventGroup eventGroup=new EventGroup {
                                        index=index6,
                                                tag=str4.split("\\|")[0],
                                                t=Integer.parseInt(str4.split("\\|")[1]),
                                                addtime=Integer.parseInt(str4.split("\\|")[2])
                                    } ;
                                    int index7=0;
                                    while(true){
                                        if(index7<str4.split("\\|")[3].split(";").length-1){
                                            Event _event=new Event {
                                                EventString=str4.split("\\|")[3].split(";")[index7]
                                            } ;
                                            _event.String2EmitterEvent();
                                            eventGroup.EventList.Add(_event);
                                            ++index7;
                                        }else{
                                            break;
                                        }
                                    }
                                    baseCoverCs.EventGroupList.Add(eventGroup);
                                    ++index6;
                                }else{
                                    break;
                                }
                            }
                            String str5=strArray[18];
                            int index8=0;
                            while(true){
                                if(index8<str5.split("&").length-1){
                                    String str4=str5.split("&")[index8];
                                    EventGroup eventGroup=new EventGroup {
                                        index=index8,
                                                tag=str4.split("\\|")[0],
                                                t=Integer.parseInt(str4.split("\\|")[1]),
                                                addtime=Integer.parseInt(str4.split("\\|")[2])
                                    } ;
                                    int index7=0;
                                    while(true){
                                        if(index7<str4.split("\\|")[3].split(";").length-1){
                                            Event _event=new Event {
                                                EventString=str4.split("\\|")[3].split(";")[index7]
                                            } ;
                                            _event.String2BulletEvent();
                                            eventGroup.EventList.Add(_event);
                                            ++index7;
                                        }else{
                                            break;
                                        }
                                    }
                                    baseCoverCs.SubEventGroupList.Add(eventGroup);
                                    ++index8;
                                }else{
                                    break;
                                }
                            }
                            baseCoverCs.RanVelocity=Float.parseFloat(strArray[19]);
                            baseCoverCs.RanDirection=Float.parseFloat(strArray[20]);
                            baseCoverCs.RanAccelerate=Float.parseFloat(strArray[21]);
                            baseCoverCs.RanAccDirection=Float.parseFloat(strArray[22]);
                            if(strArray.length>=24)
                                baseCoverCs.BindingID=Integer.parseInt(strArray[23]);
                            if(strArray.length>=25&&strArray[24]!="")
                                baseCoverCs.DeepBinding=Boolean.parseBoolean(strArray[24]);
                            EmitterList.Add(baseCoverCs);
                        }
                        int num9=Integer.parseInt(str2.split(":")[1].split(",")[6]);
                        String str7;
                        for(int index5=0;index5<num9;++index5){
                            str7=MBGtext[num6++];
                        }
                        int num10=Integer.parseInt(str2.split(":")[1].split(",")[7]);
                        for(int index5=0;index5<num10;++index5){
                            str7=MBGtext[num6++];
                        }
                    }
                }
            }
        }
    }

    private void AddNode(int id,TreeNode treeNode){
        EmitterList.ForEach(b=>{
        if(b.BindingID!=id) return;
        String str1=!(b is BaseCover_CS) ?b.EmitterMode.ToString() :"Cover";
        TreeNode treeNode1=new TreeNode(b.ID.ToString());
        TreeNode treeNode2=treeNode1;
        int id1=b.ID;
        String str2=id1.ToString();
        treeNode2.Name=str2;
        TreeNode treeNode3=treeNode1;
        id1=b.ID;
        String str3="ID："+id1.ToString()+"  "+str1;
        treeNode3.Text=str3;
        treeNode.Nodes.Add(treeNode1);
        AddNode(b.ID,treeNode1);
            });
    }

}
