﻿package com.meng.stg.helpers.CrazyStorm;
  public class BaseRadialLaser_CS : Bullet_RadialLaser
  {
    private byte[] BulletColorType = new byte[8]
    {
      (byte) 1,
      (byte) 3,
      (byte) 5,
      (byte) 7,
      (byte) 9,
      (byte) 13,
      (byte) 14,
      (byte) 15
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
        this.Region = 3;
      }
    }

    public BaseRadialLaser_CS(StageDataPackage StageData)
      : base(StageData)
    {
      this.Active = true;
      this.UnmatchedTime = 60;
      this.UnRemoveable = true;
      this.EventGroupList = new List<EventGroup>();
      this.EventsExecutionList = new List<Execution>();
    }

    public override void EventCtrl()
    {
      this.EventGroupList.ForEach((Action<EventGroup>) (a => a.Update((BaseObject_CS) this)));
      this.EventsExecutionList.ForEach((Action<Execution>) (a => a.Update((BaseObject_CS) this)));
    }
  }

