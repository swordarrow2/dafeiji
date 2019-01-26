package com.meng.stg.thsss;

public class BaseObject
{
    private float velocity = 0.0f;
    private float maxVelocity = 100f;
    private float minVelocity = -100f;
    private double direction = 0.0;
    private boolean enabled = true;
    private boolean shootEnabled = true;
    private boolean hitEnabled = true;
    private int region = 3;
    private int outsideRegion = 3;
    private int damage = 40;
    private float healthPoint = 1f;
    private float transparentValue = (float) byte.MaxValue;
    private int maxTransparent = (int) byte.MaxValue;
    private Color colorValue = Color.White;
    private double angle = 0.0;
    private float scaleLength = 1f;
    private float scaleWidth = 1f;
    private float maxScaleL = 100f;
    private float maxScaleW = 100f;
    private float minScaleL = 0.0001f;
    private float minScaleW = 0.0001f;
    private boolean angleWithDirection = true;
    private float scaleX = 1f;
    private float scaleY = 1f;
    public Dictionary<int, float> AccelerateDictionary = new Dictionary<int, float>();
    public Dictionary<int, float> VelocityDictionary = new Dictionary<int, float>();
    public Dictionary<int, float> DirectionVelocityDegreeDictionary = new Dictionary<int, float>();
    public Dictionary<int, float> DirectionDegreeDictionary = new Dictionary<int, float>();
    public Dictionary<int, Vector2> DestPointDictionary = new Dictionary<int, Vector2>();
    public Dictionary<int, float> TransparentVelocityDictionary = new Dictionary<int, float>();
    public Dictionary<int, float> ScaleWidthVelocityDictionary = new Dictionary<int, float>();
    public Dictionary<int, float> ScaleLengthVelocityDictionary = new Dictionary<int, float>();
    protected BaseObject BindingObj;
    private BaseObject LastBindingObj;
    public BaseObject.CtrlActionCallBack CtrlCall;

    public int Time;
    public TextureObject TxtureObject;
    public boolean Active;
    public Vector2 OriginalPosition;
    public Vector2 Position
    {
        get
        {
            Vector2 originalPosition = this.OriginalPosition;
            double num1 = (double) originalPosition.X + (double) this.BoundRect.Left;
            originalPosition = this.OriginalPosition;
            double num2 = (double) originalPosition.Y + (double) this.BoundRect.Top;
            return new Vector2((float) num1, (float) num2);
        }
        set
        {
            this.OriginalPosition = new Vector2(value.X - (float) this.BoundRect.Left, value.Y - (float) this.BoundRect.Top);
        }
    }

    public Vector2 DestPoint;
    public boolean Mirrored;
    public float Velocity
    {
        get
        {
            return this.velocity;
        }
        set
        {
            if ((double) value > (double) this.MaxVelocity)
                this.velocity = this.MaxVelocity;
            else if ((double) value < (double) this.MinVelocity)
                this.velocity = this.minVelocity;
            else
                this.velocity = value;
        }
    }

    public float MaxVelocity
    {
        get
        {
            return this.maxVelocity;
        }
        set
        {
            this.maxVelocity = value;
        }
    }

    public float MinVelocity
    {
        get
        {
            return this.minVelocity;
        }
        set
        {
            this.minVelocity = value;
        }
    }

    public float Vx
    {
        get
        {
            return this.Velocity * (float) Math.Cos(this.Direction);
        }
        set
        {
            float vy = this.Vy;
            this.Velocity = (float) Math.Sqrt((double) value * (double) value + (double) vy * (double) vy);
            this.Direction = (double) this.Velocity == 0.0 ? 0.0 : Math.Atan2((double) vy, (double) value);
        }
    }

    public float Vy
    {
        get
        {
            return this.Velocity * (float) Math.Sin(this.Direction);
        }
        set
        {
            float vx = this.Vx;
            this.Velocity = (float) Math.Sqrt((double) value * (double) value + (double) vx * (double) vx);
            this.Direction = (double) this.Velocity == 0.0 ? 0.0 : Math.Atan2((double) value, (double) vx);
        }
    }

    public double Angle
    {
        get
        {
            return this.angle;
        }
        set
        {
            while (value > 2.0 * Math.PI)
                value -= 2.0 * Math.PI;
            while (value < 0.0)
                value += 2.0 * Math.PI;
            this.angle = value;
        }
    }

    public double AngleDegree
    {
        get
        {
            return this.Angle / Math.PI * 180.0;
        }
        set
        {
            this.Angle = value * Math.PI / 180.0;
        }
    }

    public double Direction
    {
        get
        {
            return this.direction;
        }
        set
        {
            while (value > 2.0 * Math.PI)
                value -= 2.0 * Math.PI;
            while (value < 0.0)
                value += 2.0 * Math.PI;
            this.direction = value;
        }
    }

    public double DirectionDegree
    {
        get
        {
            return this.Direction / Math.PI * 180.0;
        }
        set
        {
            this.Direction = value * Math.PI / 180.0;
        }
    }

    public float Scale
    {
        get
        {
            return this.scaleLength;
        }
        set
        {
            if ((double) value > (double) this.MaxScale)
            {
                this.scaleLength = this.MaxScale;
                this.scaleWidth = this.MaxScale;
            }
            else if ((double) value < (double) this.MinScale)
            {
                this.scaleLength = 0.0f;
                this.scaleWidth = 0.0f;
            }
            else
            {
                this.scaleLength = value;
                this.scaleWidth = value;
            }
        }
    }

    public float MaxScale
    {
        get
        {
            return this.maxScaleL;
        }
        set
        {
            this.maxScaleL = value;
            this.maxScaleW = value;
        }
    }

    public float MinScale
    {
        get
        {
            return this.minScaleL;
        }
        set
        {
            this.minScaleL = value;
            this.minScaleW = value;
        }
    }

    public float MaxScaleL
    {
        get
        {
            return this.maxScaleL;
        }
        set
        {
            this.maxScaleL = value;
        }
    }

    public float MaxScaleW
    {
        get
        {
            return this.maxScaleW;
        }
        set
        {
            this.maxScaleW = value;
        }
    }

    public float MinScaleL
    {
        get
        {
            return this.minScaleL;
        }
        set
        {
            this.minScaleL = value;
        }
    }

    public float MinScaleW
    {
        get
        {
            return this.minScaleW;
        }
        set
        {
            this.minScaleW = value;
        }
    }

    public float ScaleX
    {
        get
        {
            return this.scaleX;
        }
        set
        {
            this.scaleX = value;
        }
    }

    public float ScaleY
    {
        get
        {
            return this.scaleY;
        }
        set
        {
            this.scaleY = value;
        }
    }

    public boolean Enabled
    {
        get
        {
            return this.enabled;
        }
        set
        {
            this.enabled = value;
        }
    }

    public boolean HitEnabled
    {
        get
        {
            return this.hitEnabled;
        }
        set
        {
            this.hitEnabled = value;
        }
    }

    public boolean ShootEnabled
    {
        get
        {
            return this.shootEnabled;
        }
        set
        {
            this.shootEnabled = value;
        }
    }

    public int Region
    {
        set
        {
            this.region = value;
            this.outsideRegion = value;
        }
        get
        {
            return this.region;
        }
    }

    public int OutsideRegion
    {
        set
        {
            this.outsideRegion = value;
        }
        get
        {
            return this.outsideRegion;
        }
    }

    public int Damage
    {
        set
        {
            this.damage = value;
        }
        get
        {
            return this.damage;
        }
    }

    public float HealthPoint
    {
        get
        {
            return this.healthPoint;
        }
        set
        {
            this.healthPoint = value;
        }
    }

    public float ScaleLength
    {
        get
        {
            return this.scaleLength;
        }
        set
        {
            if ((double) value > (double) this.MaxScaleL)
                this.scaleLength = this.MaxScaleL;
            else if ((double) value < (double) this.MinScaleL)
                this.scaleLength = this.MinScaleL;
            else
                this.scaleLength = value;
        }
    }

    public float ScaleWidth
    {
        get
        {
            return this.scaleWidth;
        }
        set
        {
            if ((double) value > (double) this.MaxScaleW)
                this.scaleWidth = this.MaxScaleW;
            else if ((double) value < (double) this.MinScaleW)
                this.scaleWidth = this.MinScaleW;
            else
                this.scaleWidth = value;
        }
    }

    public int MaxTransparent
    {
        get
        {
            return this.maxTransparent;
        }
        set
        {
            this.maxTransparent = value >= 0 ? (value <= (int) byte.MaxValue ? value : (int) byte.MaxValue) : 0;
            if ((double) this.transparentValue <= (double) this.maxTransparent)
                return;
            this.transparentValue = (float) this.maxTransparent;
        }
    }

    public float TransparentValueF
    {
        get
        {
            return this.transparentValue;
        }
        set
        {
            if ((double) value < 0.0)
                this.transparentValue = 0.0f;
            else if ((double) value > (double) this.MaxTransparent)
                this.transparentValue = (float) this.MaxTransparent;
            else
                this.transparentValue = value;
        }
    }

    public int TransparentValue
    {
        get
        {
            return (int) this.transparentValue;
        }
    }

    public Color ColorValue
    {
        get
        {
            return this.colorValue;
        }
        set
        {
            this.colorValue = value;
        }
    }

    public int LifeTime;
    public float Accelerate;
    public float Ax;
    public float Ay;
    public float AngularVelocityDegree;
    public float DirectionVelocityDegree;
    public float ScaleVelocity
    {
        get
        {
            return this.ScaleVL;
        }
        set
        {
            this.ScaleVL = value;
            this.ScaleVW = value;
        }
    }

    public float ScaleVL;
    public float ScaleVW;
    public float TransparentVelocity;
    public boolean UnRemoveable;
    public Vector2[] LastPoints;
    public int GhostingCount
    {
        get
        {
            if (this.LastPoints != null)
                return this.LastPoints.Length;
            return 0;
        }
        set
        {
            this.LastPoints = new Vector2[value];
            for (int index = 0; index < value; ++index)
                this.LastPoints[index] = this.OriginalPosition;
        }
    }

    public Color GhostingColor;
    public boolean AngleWithDirection
    {
        get
        {
            return this.angleWithDirection;
        }
        set
        {
            this.angleWithDirection = value;
        }
    }

    public StageDataPackage StageData;
    public MySprite SpriteMain
    {
        get
        {
            return this.StageData.SpriteMain;
        }
    }

    public Rectangle BoundRect
    {
        get
        {
            return this.StageData.BoundRect;
        }
        set
        {
            this.StageData.BoundRect = value;
        }
    }

    public int TimeMain
    {
        get
        {
            return this.StageData.TimeMain;
        }
        set
        {
            this.StageData.TimeMain = value;
        }
    }

    public BaseMyPlane MyPlane
    {
        get
        {
            return this.StageData.MyPlane;
        }
        set
        {
            this.StageData.MyPlane = value;
        }
    }

    public List<BaseBullet_Touhou> BulletList
    {
        get
        {
            return this.StageData.BulletList;
        }
        set
        {
            this.StageData.BulletList = value;
        }
    }

    public List<BaseObject> MyBulletList
    {
        get
        {
            return this.StageData.MyBulletList;
        }
        set
        {
            this.StageData.MyBulletList = value;
        }
    }

    public List<BaseObject> SpellList
    {
        get
        {
            return this.StageData.SpellList;
        }
        set
        {
            this.StageData.SpellList = value;
        }
    }

    public List<BaseEnemyPlane> EnemyPlaneList
    {
        get
        {
            return this.StageData.EnemyPlaneList;
        }
        set
        {
            this.StageData.EnemyPlaneList = value;
        }
    }

    public List<BaseEffect> EffectList
    {
        get
        {
            return this.StageData.EffectList;
        }
        set
        {
            this.StageData.EffectList = value;
        }
    }

    public List<BaseItem> ItemList
    {
        get
        {
            return this.StageData.ItemList;
        }
        set
        {
            this.StageData.ItemList = value;
        }
    }

    public BaseStory Story
    {
        get
        {
            return this.StageData.Story;
        }
        set
        {
            this.StageData.Story = value;
        }
    }

    public BackgroundManager Background
    {
        get
        {
            return this.StageData.Background;
        }
        set
        {
            this.StageData.Background = value;
        }
    }

    public BackgroundManager Background2
    {
        get
        {
            return this.StageData.Background2;
        }
        set
        {
            this.StageData.Background2 = value;
        }
    }

    public BackgroundManager3D Background3D
    {
        get
        {
            return this.StageData.Background3D;
        }
        set
        {
            this.StageData.Background3D = value;
        }
    }

    public BaseBossTouhou Boss
    {
        get
        {
            return this.StageData.Boss;
        }
        set
        {
            this.StageData.Boss = value;
        }
    }

    public List<XAudio2_Player> SoundPlayList
    {
        get
        {
            return this.StageData.SoundPlayList;
        }
        set
        {
            this.StageData.SoundPlayList = value;
        }
    }

    public Dictionary<string, TextureObject> TextureObjectDictionary
    {
        get
        {
            return this.StageData.TextureObjectDictionary;
        }
    }

    public Wave_Player BGM_Player
    {
        get
        {
            return this.StageData.BGM_Player;
        }
    }

    public KeyClass KClass
    {
        get
        {
            return this.StageData.KClass;
        }
    }

    public MyRandom Ran
    {
        get
        {
            return this.StageData.Ran;
        }
        set
        {
            this.StageData.Ran = value;
        }
    }

    public DifficultLevel Difficulty
    {
        get
        {
            return this.StageData.Difficulty;
        }
    }

    public BaseObject()
    {
    }

    public BaseObject(StageDataPackage StageData)
    {
        this.StageData = StageData;
    }

    public BaseObject(
            StageDataPackage StageData,
            string textureName,
            Vector2 Position,
            float Velocity,
            double Direction)
    {
        this.StageData = StageData;
        this.Position = Position;
        this.Velocity = Velocity;
        this.Direction = Direction;
        if (textureName != null)
            this.TxtureObject = this.TextureObjectDictionary[textureName];
        this.GhostingColor = Color.White;
    }

    public virtual void Move()
{
    int num;
    if (this.DestPoint != new Vector2(0.0f, 0.0f))
    {
        Vector2 destPoint = this.DestPoint;
        num = false ? 1 : 0;
    }
    else
        num = 1;
    if (num == 0)
        this.MovePoint();
    this.Position = new Vector2(this.Position.X + this.Velocity * (float) Math.Cos(this.Direction) * this.scaleX, this.Position.Y + this.Velocity * (float) Math.Sin(this.Direction) * this.scaleY);
}

    public void MovePoint()
    {
        float distance = (float) this.GetDistance(this.DestPoint);
        if ((double) distance > (double) Math.Abs(this.Velocity))
        {
            this.Direction = this.GetDirection(this.DestPoint);
            this.Accelerate = (float) (-(double) this.Velocity * (double) this.Velocity / (2.0 * (double) distance));
            if ((double) this.Velocity >= 0.0)
                return;
            this.Velocity = 0.0f;
        }
        else
        {
            this.Accelerate = 0.0f;
            this.Velocity = 0.0f;
        }
    }

    public virtual boolean OutBoundary()
{
    if (this.LifeTime != 0 && this.Time > this.LifeTime)
        return true;
    if (this.Time < 100)
        return false;
    int num1 = (double) this.OutsideRegion * (double) this.Scale * 2.0 >= 20.0 ? (int) ((double) this.OutsideRegion * (double) this.Scale * 2.0) : 20;
    Rectangle boundRect;
    int num2;
    if ((double) -num1 < (double) this.OriginalPosition.X)
    {
        Vector2 originalPosition = this.OriginalPosition;
        double x = (double) originalPosition.X;
        boundRect = this.BoundRect;
        double num3 = (double) (boundRect.Width + num1);
        if (x < num3)
        {
            double num4 = (double) -num1;
            originalPosition = this.OriginalPosition;
            double y1 = (double) originalPosition.Y;
            if (num4 < y1)
            {
                originalPosition = this.OriginalPosition;
                double y2 = (double) originalPosition.Y;
                boundRect = this.BoundRect;
                double num5 = (double) (boundRect.Height + num1);
                num2 = y2 >= num5 ? 1 : 0;
            goto label_9;
            }
        }
    }
    num2 = 1;
    label_9:
    if (num2 == 0)
        return false;
    if (this.GhostingCount > 0)
    {
        int num3;
        if ((double) -num1 < (double) this.LastPoints[this.GhostingCount - 1].X)
        {
            double x = (double) this.LastPoints[this.GhostingCount - 1].X;
            boundRect = this.BoundRect;
            double num4 = (double) (boundRect.Width + num1);
            if (x < num4 && (double) -num1 < (double) this.LastPoints[this.GhostingCount - 1].Y)
            {
                double y = (double) this.LastPoints[this.GhostingCount - 1].Y;
                boundRect = this.BoundRect;
                double num5 = (double) (boundRect.Height + num1);
                num3 = y >= num5 ? 1 : 0;
            goto label_16;
            }
        }
        num3 = 1;
        label_16:
        if (num3 == 0)
            return false;
    }
    return true;
}

    public virtual boolean HitCheck(BaseObject Target)
{
    return this.HitCheck(Target, (float) this.Region);
}

    public virtual boolean HitCheck(BaseObject Target, float Radius)
{
    if (this.Time < 9)
        return false;
    if ((double) this.ScaleLength == (double) this.ScaleWidth)
    {
        if (this.GetDistance(Target) - (double) Target.Region * (double) Target.Scale < (double) Radius * (double) this.Scale)
            return true;
    }
    else
    {
        double num1 = this.AngleWithDirection ? this.Direction + this.Angle : this.Angle;
        double num2 = this.GetDistance(Target) + (double) Target.Region * (double) Target.Scale;
        double direction = this.GetDirection(Target);
        double num3 = num2 * Math.Cos(num1 - direction);
        double num4 = num2 * Math.Sin(num1 - direction);
        double num5 = (double) Radius * (double) this.ScaleWidth;
        double num6 = (double) Radius * (double) this.ScaleLength;
        if (num4 * num4 / num5 / num5 + num3 * num3 / num6 / num6 < 1.0)
            return true;
    }
    return false;
}

    public virtual void HitCheckAll()
{
}

    public virtual void Shoot()
{
}

    public virtual void Ctrl()
{
    if (!this.Enabled)
        return;
    ++this.Time;
    this.Move();
    if (this.BindingObj != null)
    {
        if (this.LastBindingObj == null)
        {
            this.LastBindingObj = new BaseObject();
            this.LastBindingObj.StageData = this.StageData;
            this.LastBindingObj.CopyPosition(this.BindingObj);
        }
        double distance = this.GetDistance(this.LastBindingObj);
        double num1 = this.GetDirection(this.LastBindingObj) + Math.PI + this.BindingObj.Angle - this.LastBindingObj.Angle;
        this.Direction += this.BindingObj.Angle - this.LastBindingObj.Angle;
        if (this.BindingObj.AngleWithDirection)
        {
            num1 += this.BindingObj.Direction - this.LastBindingObj.Direction;
            this.Direction += this.BindingObj.Direction - this.LastBindingObj.Direction;
        }
        Vector2 originalPosition = this.BindingObj.OriginalPosition;
        double num2 = (double) originalPosition.X + distance * Math.Cos(num1);
        originalPosition = this.BindingObj.OriginalPosition;
        double num3 = (double) originalPosition.Y + distance * Math.Sin(num1);
        this.OriginalPosition = new Vector2((float) num2, (float) num3);
        this.LastBindingObj.CopyPosition(this.BindingObj);
    }
    if ((double) this.Ax != 0.0 || (double) this.Ay != 0.0)
    {
        float velocity = this.Velocity;
        double direction = this.Direction;
        this.Velocity = (float) Math.Sqrt((double) velocity * (double) velocity + (double) this.Ax * (double) this.Ax + (double) this.Ay * (double) this.Ay + 2.0 * (double) velocity * (double) this.Ax * Math.Cos(direction) + 2.0 * (double) velocity * (double) this.Ay * Math.Sin(direction));
        this.Direction = Math.Atan2((double) velocity * Math.Sin(direction) + (double) this.Ay, (double) velocity * Math.Cos(direction) + (double) this.Ax);
    }
    this.Velocity += this.Accelerate;
    this.AngleDegree += (double) this.AngularVelocityDegree;
    this.DirectionDegree += (double) this.DirectionVelocityDegree;
    this.TransparentValueF += this.TransparentVelocity;
    this.ScaleLength += this.ScaleVL;
    this.ScaleWidth += this.ScaleVW;
    if (this.AccelerateDictionary.ContainsKey(this.Time))
        this.Accelerate = this.AccelerateDictionary[this.Time];
    if (this.VelocityDictionary.ContainsKey(this.Time))
        this.Velocity = this.VelocityDictionary[this.Time];
    if (this.DirectionVelocityDegreeDictionary.ContainsKey(this.Time))
        this.DirectionVelocityDegree = this.DirectionVelocityDegreeDictionary[this.Time];
    if (this.DirectionDegreeDictionary.ContainsKey(this.Time))
        this.DirectionDegree = (double) this.DirectionDegreeDictionary[this.Time];
    if (this.DestPointDictionary.ContainsKey(this.Time))
        this.DestPoint = this.DestPointDictionary[this.Time];
    if (this.TransparentVelocityDictionary.ContainsKey(this.Time))
        this.TransparentVelocity = this.TransparentVelocityDictionary[this.Time];
    if (this.ScaleWidthVelocityDictionary.ContainsKey(this.Time))
        this.ScaleVW = this.ScaleWidthVelocityDictionary[this.Time];
    if (this.ScaleLengthVelocityDictionary.ContainsKey(this.Time))
        this.ScaleVL = this.ScaleLengthVelocityDictionary[this.Time];
    if (this.CtrlCall != null)
        this.CtrlCall((string) null);
    if (this.GhostingCount <= 0)
        return;
    for (int index = this.LastPoints.Length - 1; index > 0; --index)
        this.LastPoints[index] = this.LastPoints[index - 1];
    this.LastPoints[0] = this.OriginalPosition;
}

    public void SetBinding(BaseObject BindingObj)
    {
        this.BindingObj = BindingObj;
        this.Ctrl();
    }

    public virtual void GiveEndEffect()
{
}

    public virtual void GiveItems()
{
}

    public virtual void Show()
{
    if (this.TxtureObject == null || this.TransparentValue <= 0)
        return;
    double num1 = this.AngleWithDirection ? this.Direction + this.Angle - Math.PI / 2.0 : this.Angle - Math.PI / 2.0;
    for (int index = this.GhostingCount - 1; index >= 0; index -= 2)
    {
        float num2 = (float) (1.0 - 0.100000001490116 * (double) index / (double) this.LastPoints.Length);
        Vector2 position = new Vector2();
        ref Vector2 local = ref position;
        double x = (double) this.LastPoints[index].X;
        Rectangle boundRect = this.BoundRect;
        double left = (double) boundRect.Left;
        double num3 = x + left;
        double y = (double) this.LastPoints[index].Y;
        boundRect = this.BoundRect;
        double top = (double) boundRect.Top;
        double num4 = y + top;
        local = new Vector2((float) num3, (float) num4);
        this.SpriteMain.Draw2D(this.TxtureObject, this.ScaleWidth * num2, this.ScaleLength * num2, (float) num1, position, Color.FromArgb(this.TransparentValue * (this.GhostingCount - index) / (this.GhostingCount + 1) / 4, this.GhostingColor), this.Mirrored);
    }
    this.SpriteMain.Draw2D(this.TxtureObject, this.ScaleWidth, this.ScaleLength, (float) num1, this.Position, Color.FromArgb(this.TransparentValue, this.ColorValue), this.Mirrored);
}

    public virtual void ShowRegion()
{
    if (this.Region < 0)
        return;
    this.SpriteMain.Draw2D(this.TextureObjectDictionary["Region"], 2f * (float) this.Region / (float) this.TextureObjectDictionary["Region"].Width * this.ScaleWidth, 2f * (float) this.Region / (float) this.TextureObjectDictionary["Region"].Height * this.ScaleLength, this.AngleWithDirection ? (float) (this.Direction + this.Angle - Math.PI / 2.0) : (float) (this.Angle - Math.PI / 2.0), this.Position, Color.White, this.Mirrored);
}

    public double GetDirection(BaseObject Target)
    {
        return this.GetDirection(Target.OriginalPosition);
    }

    public double GetDirection(Vector2 TargetOriginalPosition)
    {
        Vector2 originalPosition = this.OriginalPosition;
        double y = (double) originalPosition.Y - (double) TargetOriginalPosition.Y;
        originalPosition = this.OriginalPosition;
        double x = (double) originalPosition.X - (double) TargetOriginalPosition.X;
        return Math.PI + Math.Atan2(y, x);
    }

    public double GetDistance(BaseObject Target)
    {
        return this.GetDistance(Target.OriginalPosition);
    }

    public double GetDistance(Vector2 TargetOriginalPosition)
    {
        Vector2 originalPosition = this.OriginalPosition;
        double num1 = (double) originalPosition.X - (double) TargetOriginalPosition.X;
        originalPosition = this.OriginalPosition;
        double num2 = (double) originalPosition.X - (double) TargetOriginalPosition.X;
        double num3 = num1 * num2;
        originalPosition = this.OriginalPosition;
        double num4 = (double) originalPosition.Y - (double) TargetOriginalPosition.Y;
        originalPosition = this.OriginalPosition;
        double num5 = (double) originalPosition.Y - (double) TargetOriginalPosition.Y;
        double num6 = num4 * num5;
        return Math.Sqrt(num3 + num6);
    }

    public virtual void Copy(BaseObject b)
{
    this.Time = b.Time;
    this.TxtureObject = b.TxtureObject;
    this.Active = b.Active;
    this.Position = b.Position;
    this.DestPoint = b.DestPoint;
    this.Mirrored = b.Mirrored;
    this.Velocity = b.Velocity;
    this.Angle = b.Angle;
    this.Direction = b.Direction;
    this.Scale = b.Scale;
    this.Enabled = b.Enabled;
    this.HitEnabled = b.HitEnabled;
    this.Region = b.Region;
    this.Damage = b.Damage;
    this.HealthPoint = b.HealthPoint;
    this.ScaleLength = b.ScaleLength;
    this.ScaleWidth = b.ScaleWidth;
    this.MaxTransparent = b.MaxTransparent;
    this.TransparentValueF = b.TransparentValueF;
    this.ColorValue = b.ColorValue;
    this.LifeTime = b.LifeTime;
    this.Accelerate = b.Accelerate;
    this.AngularVelocityDegree = b.AngularVelocityDegree;
    this.DirectionVelocityDegree = b.DirectionVelocityDegree;
    this.ScaleVelocity = b.ScaleVelocity;
    this.TransparentVelocity = b.TransparentVelocity;
    this.GhostingCount = b.GhostingCount;
    this.GhostingColor = b.GhostingColor;
    this.UnRemoveable = b.UnRemoveable;
    this.AngleWithDirection = b.AngleWithDirection;
    this.BindingObj = b.BindingObj;
}

    public virtual void CopyPosition(BaseObject b)
{
    this.Angle = b.Angle;
    this.Direction = b.Direction;
    this.Position = b.Position;
}

    public object Clone(object Obj)
    {
        if (Obj == null)
            return (object) null;
        MemoryStream memoryStream = new MemoryStream();
        BinaryFormatter binaryFormatter = new BinaryFormatter();
        binaryFormatter.Serialize((Stream) memoryStream, Obj);
        memoryStream.Seek(0L, SeekOrigin.Begin);
        object obj = binaryFormatter.Deserialize((Stream) memoryStream);
        memoryStream.Close();
        return obj;
    }

    public virtual object Clone()
{
    BaseObject baseObject = (BaseObject) this.MemberwiseClone();
    baseObject.GhostingCount = this.GhostingCount;
    return (object) baseObject;
}

    public virtual void Dispose()
{
}

    public delegate void CtrlActionCallBack(string s);
}
