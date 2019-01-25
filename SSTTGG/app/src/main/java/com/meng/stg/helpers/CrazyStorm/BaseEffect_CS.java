package com.meng.stg.helpers.CrazyStorm;
  public class BaseEffect_CS : BaseEffect
  {
    public BaseEffect_CS()
    {
    }

    public BaseEffect_CS(StageDataPackage StageData)
    {
      this.StageData = StageData;
      this.EventGroupList = new List<EventGroup>();
      this.EventsExecutionList = new List<Execution>();
    }

    public override void EventCtrl()
    {
      this.EventGroupList.ForEach((Action<EventGroup>) (a => a.Update((BaseObject_CS) this)));
      this.EventsExecutionList.ForEach((Action<Execution>) (a => a.Update((BaseObject_CS) this)));
    }
  }

