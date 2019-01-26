package com.meng.stg.helpers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 2019/1/26.
 */

public class EventGroup{
    public String tag = "";
    public int t = 1;
    public List<Event> EventList = new List<Event>();
    public int index;
    public int loop;
    public int addtime;
    public int special;

    public void Update(BaseEmitter_CS CS_Emitter)
    {
        if (CS_Emitter.Time < 1)
            CS_Emitter.Time = 1;
        float[] conditions = new float[13];
        float[] numArray1 = new float[33];
        conditions[0] = (float) (CS_Emitter.Time - CS_Emitter.StartTime + 1);
        float[] numArray2 = conditions;
        Vector2 csPosition = CS_Emitter.CS_Position;
        double x1 = (double) csPosition.X;
        numArray2[1] = (float) x1;
        float[] numArray3 = conditions;
        csPosition = CS_Emitter.CS_Position;
        double y1 = (double) csPosition.Y;
        numArray3[2] = (float) y1;
        conditions[3] = CS_Emitter.EmitRadius;
        conditions[4] = (float) CS_Emitter.RadiusDirection;
        conditions[5] = (float) CS_Emitter.Way;
        conditions[6] = (float) CS_Emitter.Circle;
        conditions[7] = (float) CS_Emitter.EmitDirection;
        conditions[8] = (float) CS_Emitter.Range;
        conditions[9] = CS_Emitter.SubBullet.ScaleWidth;
        conditions[10] = CS_Emitter.SubBullet.ScaleLength;
        conditions[11] = CS_Emitter.SubBullet.TransparentValueF;
        conditions[12] = (float) CS_Emitter.SubBullet.AngleDegree;
        numArray1[0] = CS_Emitter.CS_Position.X;
        numArray1[1] = CS_Emitter.CS_Position.Y;
        numArray1[2] = CS_Emitter.EmitRadius;
        numArray1[3] = (float) CS_Emitter.RadiusDirection;
        numArray1[4] = (float) CS_Emitter.Way;
        numArray1[5] = (float) CS_Emitter.Circle;
        numArray1[6] = (float) CS_Emitter.EmitDirection;
        numArray1[7] = (float) CS_Emitter.Range;
        numArray1[8] = CS_Emitter.Velocity;
        numArray1[9] = (float) CS_Emitter.DirectionDegree;
        numArray1[10] = CS_Emitter.AccelerateCS;
        numArray1[11] = (float) CS_Emitter.AccDirection;
        numArray1[12] = (float) CS_Emitter.SubBullet.LifeTime;
        numArray1[13] = (float) CS_Emitter.SubBullet.Type;
        numArray1[14] = CS_Emitter.SubBullet.ScaleLength;
        numArray1[15] = CS_Emitter.SubBullet.ScaleLength;
        numArray1[16] = (float) CS_Emitter.SubBullet.ColorValue.R;
        float[] numArray4 = numArray1;
        Color colorValue = CS_Emitter.SubBullet.ColorValue;
        double g = (double) colorValue.G;
        numArray4[17] = (float) g;
        float[] numArray5 = numArray1;
        colorValue = CS_Emitter.SubBullet.ColorValue;
        double b = (double) colorValue.B;
        numArray5[18] = (float) b;
        numArray1[19] = CS_Emitter.SubBullet.TransparentValueF;
        numArray1[20] = (float) CS_Emitter.SubBullet.AngleDegree;
        numArray1[21] = CS_Emitter.SubBullet.Velocity;
        numArray1[22] = (float) CS_Emitter.SubBullet.DirectionDegree;
        numArray1[23] = CS_Emitter.SubBullet.AccelerateCS;
        numArray1[24] = (float) CS_Emitter.SubBullet.AccDirection;
        numArray1[25] = CS_Emitter.SubBullet.ScaleX;
        numArray1[26] = CS_Emitter.SubBullet.ScaleY;
        numArray1[27] = 0.0f;
        numArray1[28] = 0.0f;
        numArray1[29] = 0.0f;
        numArray1[30] = 0.0f;
        numArray1[31] = 0.0f;
        numArray1[32] = 0.0f;
        if (this.t < 1)
            this.t = 1;
        if ((CS_Emitter.Time - CS_Emitter.StartTime + 1) % this.t == 0)
            ++this.loop;
        foreach (Event ev in this.EventList)
        {
            if (this.CheckCondition(conditions, ev))
            {
                Vector2 pointF;
                if (ev.special == 3)
                {
                    if (ev.changevalue == 0)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.CS_Position;
                        double num = (double) pointF.X - 4.0;
                        @event.res = (float) num;
                    }
                    if (ev.changevalue == 1)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.CS_Position;
                        double num = (double) pointF.Y + 16.0;
                        @event.res = (float) num;
                    }
                    if (ev.changevalue == 6 || ev.changevalue == 24)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.OriginalPosition;
                        double num1 = (double) pointF.X - 4.0;
                        pointF = CS_Emitter.OriginalPosition;
                        double num2 = (double) pointF.Y + 16.0;
                        pointF = CS_Emitter.OriginalPosition;
                        double x2 = (double) pointF.X;
                        pointF = CS_Emitter.OriginalPosition;
                        double y2 = (double) pointF.Y;
                        double num3 = (double) this.Twopointangle((float) num1, (float) num2, (float) x2, (float) y2) * 180.0 / 3.14159274101257;
                        @event.res = (float) num3;
                    }
                }
                else if (ev.special == 4)
                {
                    if (ev.changevalue == 0)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.MyPlane.OriginalPosition;
                        double num = (double) pointF.X + 320.0 - 192.0;
                        @event.res = (float) num;
                    }
                    if (ev.changevalue == 1)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.MyPlane.OriginalPosition;
                        double num = (double) pointF.Y + 240.0 - 224.0;
                        @event.res = (float) num;
                    }
                    if (ev.changevalue == 3 || ev.changevalue == 6 || (ev.changevalue == 9 || ev.changevalue == 11) || ev.changevalue == 24)
                    {
                        Event @event = ev;
                        pointF = CS_Emitter.MyPlane.OriginalPosition;
                        double x2 = (double) pointF.X;
                        pointF = CS_Emitter.MyPlane.OriginalPosition;
                        double y2 = (double) pointF.Y;
                        pointF = CS_Emitter.OriginalPosition;
                        double x3 = (double) pointF.X;
                        pointF = CS_Emitter.OriginalPosition;
                        double y3 = (double) pointF.Y;
                        double num = (double) this.Twopointangle((float) x2, (float) y2, (float) x3, (float) y3) * 180.0 / 3.14159274101257;
                        @event.res = (float) num;
                    }
                }
                if (ev.special != 1)
                {
                    if (ev.special == 2)
                    {
                        CS_Emitter.ShootBullet();
                    }
                    else
                    {
                        Execution execution = new Execution();
                        if (!ev.noloop)
                        {
                            if (ev.time > 0)
                            {
                                --ev.time;
                                if (ev.time == 0)
                                    ev.noloop = true;
                            }
                            execution.parentid = CS_Emitter.LayerID;
                            execution.id = CS_Emitter.ID;
                            execution.change = ev.change;
                            execution.changetype = ev.changetype;
                            execution.changevalue = ev.changevalue;
                            execution.value = (double) ev.rand == 0.0 ? ev.res : ev.res + ev.rand * (float) CS_Emitter.Ran.NextPMDouble();
                            execution.region = numArray1[ev.changename].ToString();
                            execution.time = ev.times;
                            execution.ctime = execution.time;
                            CS_Emitter.EventsExecutionList.Add(execution);
                        }
                    }
                }
            }
        }
    }

    public void Update(BaseObject_CS CS_Bullet)
    {
        float[] conditions = new float[3];
        float[] numArray = new float[21];
        conditions[0] = (float) CS_Bullet.Time;
        conditions[1] = CS_Bullet.CS_Position.X;
        conditions[2] = CS_Bullet.CS_Position.Y;
        numArray[0] = (float) CS_Bullet.LifeTime;
        numArray[1] = (float) CS_Bullet.Type;
        numArray[2] = CS_Bullet.ScaleWidth;
        numArray[3] = CS_Bullet.ScaleLength;
        numArray[4] = (float) CS_Bullet.ColorValue.R;
        numArray[5] = (float) CS_Bullet.ColorValue.G;
        numArray[6] = (float) CS_Bullet.ColorValue.B;
        numArray[7] = CS_Bullet.TransparentValueF;
        numArray[8] = (float) CS_Bullet.AngleDegree;
        numArray[9] = CS_Bullet.Velocity;
        numArray[10] = (float) CS_Bullet.DirectionDegree;
        numArray[11] = CS_Bullet.AccelerateCS;
        numArray[12] = (float) CS_Bullet.AccDirection;
        numArray[13] = CS_Bullet.ScaleX;
        numArray[14] = CS_Bullet.ScaleY;
        numArray[15] = 0.0f;
        numArray[16] = 0.0f;
        numArray[17] = 0.0f;
        numArray[18] = 0.0f;
        numArray[19] = 0.0f;
        numArray[20] = 0.0f;
        if (this.t < 1)
            this.t = 1;
        if ((CS_Bullet.Time + 1) % this.t == 0)
            ++this.loop;
        foreach (Event ev in this.EventList)
        {
            if (ev.special2 == 1)
                conditions[0] = (float) CS_Bullet.StageData.TimeMain;
            if (this.CheckCondition(conditions, ev))
            {
                if (ev.special == 4 && (ev.changevalue == 10 || ev.changevalue == 12))
                {
                    Event @event = ev;
                    Vector2 originalPosition = CS_Bullet.MyPlane.OriginalPosition;
                    double x1 = (double) originalPosition.X;
                    originalPosition = CS_Bullet.MyPlane.OriginalPosition;
                    double y1 = (double) originalPosition.Y;
                    originalPosition = CS_Bullet.OriginalPosition;
                    double x2 = (double) originalPosition.X;
                    double y2 = (double) CS_Bullet.OriginalPosition.Y;
                    double num = (double) this.Twopointangle((float) x1, (float) y1, (float) x2, (float) y2) * 180.0 / 3.14159274101257;
                    @event.res = (float) num;
                }
                Execution execution = new Execution();
                if (!ev.noloop)
                {
                    if (ev.time > 0)
                    {
                        --ev.time;
                        if (ev.time == 0)
                            ev.noloop = true;
                    }
                    execution.change = ev.change;
                    execution.changetype = ev.changetype;
                    execution.changevalue = ev.changevalue;
                    execution.value = (double) ev.rand != 0.0 ? ev.res + ev.rand * (float) CS_Bullet.StageData.Ran.NextPMDouble() : ev.res;
                    execution.region = numArray[ev.changename].ToString();
                    execution.time = ev.times;
                    execution.ctime = execution.time;
                    CS_Bullet.EventsExecutionList.Add(execution);
                }
            }
        }
    }

    private bool CheckCondition(float[] conditions, Event ev)
    {
        bool flag1 = false;
        bool flag2 = false;
        if (ev.opreator == ">")
            flag1 = (double) conditions[ev.contype] > (double) float.Parse(ev.condition) + (double) (this.loop * this.addtime);
        else if (ev.opreator == "<")
            flag1 = (double) conditions[ev.contype] < (double) float.Parse(ev.condition) + (double) (this.loop * this.addtime);
        else if (ev.opreator == "=")
            flag1 = (double) conditions[ev.contype] == (double) float.Parse(ev.condition) + (double) (this.loop * this.addtime);
        if (ev.opreator2 == ">")
            flag2 = (double) conditions[ev.contype2] > (double) float.Parse(ev.condition2) + (double) (this.loop * this.addtime);
        else if (ev.opreator2 == "<")
            flag2 = (double) conditions[ev.contype2] < (double) float.Parse(ev.condition2) + (double) (this.loop * this.addtime);
        else if (ev.opreator2 == "=")
            flag2 = (double) conditions[ev.contype2] == (double) float.Parse(ev.condition2) + (double) (this.loop * this.addtime);
        return !(ev.collector == "且") ? (!(ev.collector == "或") ? flag1 : flag1 || flag2) : flag1 && flag2;
    }

    private float Twopointangle(float x2, float y2, float x1, float y1)
    {
        float num = (double) x2 - (double) x1 == 0.0 ? (float) Math.Atan(((double) y2 - (double) y1) / ((double) x2 - (double) x1 + 0.100000001490116)) : (float) Math.Atan(((double) y2 - (double) y1) / ((double) x2 - (double) x1));
        if ((double) x2 - (double) x1 < 0.0)
            num += 3.141593f;
        return num;
    }

    public object Clone()
    {
        EventGroup ev = (EventGroup) this.MemberwiseClone();
        ev.EventList = new List<Event>();
        this.EventList.ForEach((Action<Event>) (x => ev.EventList.Add((Event) x.Clone())));
        return (object) ev;
    }
}
