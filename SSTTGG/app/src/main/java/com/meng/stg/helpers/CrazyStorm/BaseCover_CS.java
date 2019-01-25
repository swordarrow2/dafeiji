package com.meng.stg.helpers.CrazyStorm;
  public class BaseCover_CS : BaseEmitter_CS
  {
    public bool Roundness { get; set; }

    public int CtrlID { get; set; }

    public List<EventGroup> SubEventGroupList { get; set; }

    public BaseCover_CS(StageDataPackage StageData)
    {
      this.StageData = StageData;
      this.EventGroupList = new List<EventGroup>();
      this.EventsExecutionList = new List<Execution>();
      this.SubEventGroupList = new List<EventGroup>();
      this.HealthPoint = 1000000f;
      this.HitEnabled = false;
      this.Region = 1;
      this.Angle = Math.PI / 2.0;
    }

    public override void Shoot()
    {
      this.AngleWithDirection = this.Roundness;
    }

    public override void EventCtrl()
    {
      this.EventGroupList.ForEach((Action<EventGroup>) (a => a.Update((BaseEmitter_CS) this)));
      this.EventsExecutionList.ForEach((Action<Execution>) (a => a.Update((BaseEmitter_CS) this)));
    }

    public override void HitCheckAll()
    {
      if (this.Time < this.StartTime || this.Time > this.StartTime + this.Duration)
        return;
      this.BulletList.ForEach((Action<BaseBullet_Touhou>) (b =>
      {
        if (!b.Cover || (this.Type != 0 && b.ID != this.CtrlID || !this.HitCheck((BaseObject) b)))
          return;
        this.SubEventGroupList.ForEach((Action<EventGroup>) (e => e.Update((BaseObject_CS) b)));
      }));
    }

    public override bool HitCheck(BaseObject Target)
    {
      if (this.Roundness)
      {
        if ((double) this.ScaleLength == (double) this.ScaleWidth)
        {
          if (this.GetDistance(Target) - (double) Target.Region < (double) this.Region * (double) this.Scale)
            return true;
        }
        else
        {
          double num1 = this.AngleWithDirection ? this.Direction + this.Angle : this.Angle;
          double distance = this.GetDistance(Target);
          double direction = this.GetDirection(Target);
          double num2 = distance * Math.Cos(num1 - direction);
          double num3 = distance * Math.Sin(num1 - direction);
          double num4 = (double) this.Region * (double) this.ScaleWidth;
          double num5 = (double) this.Region * (double) this.ScaleLength;
          if (num3 * num3 / num4 / num4 + num2 * num2 / num5 / num5 < 1.0)
            return true;
        }
        return false;
      }
      return (double) Math.Abs(Target.OriginalPosition.X - this.OriginalPosition.X) < (double) this.Region * (double) this.ScaleWidth && (double) Math.Abs(Target.OriginalPosition.Y - this.OriginalPosition.Y) < (double) this.Region * (double) this.ScaleLength;
    }
  }

