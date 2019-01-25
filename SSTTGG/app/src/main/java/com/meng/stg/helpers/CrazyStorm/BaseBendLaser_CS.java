package com.meng.stg.helpers.CrazyStorm;
  public class BaseBendLaser_CS //: Bullet_BendLaser
  {
    private byte[] BulletColorType = new byte[8]
    {
       1,
       3,
       5,
       7,
       9,
       13,
       14,
       15
    };

    public override int Type
    {
      get
      {
        return this.type;
      }
      set
      {
        this.type = value;
        if (value < 0 || value >= 16)
          return;
        this.ColorType = this.BulletColorType[value];
        this.TxtureObject = this.TextureObjectDictionary["Laser0_" + (object) this.ColorType];
        this.GhostingColor = Color.White;
        this.SizeValue = 32;
        this.Region = 1;
      }
    }

    public BaseBendLaser_CS(StageDataPackage StageData)
      : base(StageData)
    {
      this.Active = true;
      this.EventGroupList = new List<EventGroup>();
      this.EventsExecutionList = new List<Execution>();
    }

    public override void EventCtrl()
    {
      this.EventGroupList.ForEach((Action<EventGroup>) (a => a.Update((BaseObject_CS) this)));
      this.EventsExecutionList.ForEach((Action<Execution>) (a => a.Update((BaseObject_CS) this)));
    }
  }

