package com.meng.stg.thsss;

public class BaseObject_CS : BaseObject
        {
public int type;

public virtual int Type
        {
        get
        {
        return this.type;
        }
        set
        {
        this.type = value;
        }
        }

public bool Ghosting
        {
        get
        {
        return this.GhostingCount > 0;
        }
        set
        {
        if (value)
        {
        this.GhostingCount = 20;
        this.GhostingColor = Color.White;
        }
        else
        this.GhostingCount = 0;
        }
        }

public bool OutBound
        {
        get
        {
        return this.OutsideRegion < 80;
        }
        set
        {
        if (value)
        return;
        this.OutsideRegion = 200;
        }
        }

public bool BeginningEffect { get; set; }

public bool EndingEffect { get; set; }

public PointF CS_Position
        {
        get
        {
        PointF originalPosition = this.OriginalPosition;
        double num1 = (double) originalPosition.X + 320.0 - 192.0;
        originalPosition = this.OriginalPosition;
        double num2 = (double) originalPosition.Y + 240.0 - 224.0;
        return new PointF((float) num1, (float) num2);
        }
        set
        {
        this.OriginalPosition = new PointF((float) ((double) value.X - 320.0 + 192.0), (float) ((double) value.Y - 240.0 + 224.0));
        }
        }

public int ID { get; set; }

public int LayerID { get; set; }

public float AccelerateCS { get; set; }

public double AccDirection { get; set; }

public double RanAngle { get; set; }

public float RanVelocity { get; set; }

public double RanDirection { get; set; }

public float RanAccelerate { get; set; }

public double RanAccDirection { get; set; }

public bool Cover { get; set; }

public bool Rebound { get; set; }

public bool Force { get; set; }

public List<EventGroup> EventGroupList { get; set; }

public List<Execution> EventsExecutionList { get; set; }

public BaseObject_CS()
        {
        }

public BaseObject_CS(StageDataPackage StageData)
        : base(StageData)
        {
        }

public BaseObject_CS(
        StageDataPackage StageData,
        string textureName,
        PointF Position,
        float Velocity,
        double Direction)
        : base(StageData, textureName, Position, Velocity, Direction)
        {
        }

public override void Ctrl()
        {
        double num = this.AccDirection / 180.0 * Math.PI;
        this.Ax = this.AccelerateCS * (float) Math.Cos(num);
        this.Ay = this.AccelerateCS * (float) Math.Sin(num);
        this.EventCtrl();
        base.Ctrl();
        }

public virtual void EventCtrl()
        {
        if (this.EventGroupList != null)
        this.EventGroupList.ForEach((Action<EventGroup>) (a => a.Update(this)));
        if (this.EventsExecutionList == null)
        return;
        this.EventsExecutionList.ForEach((Action<Execution>) (a => a.Update(this)));
        }

public override object Clone()
        {
        BaseObject_CS b = (BaseObject_CS) base.Clone();
        b.EventsExecutionList = new List<Execution>();
        b.EventGroupList = new List<EventGroup>();
        this.EventGroupList.ForEach((Action<EventGroup>) (x => b.EventGroupList.Add((EventGroup) x.Clone())));
        return (object) b;
        }
        }