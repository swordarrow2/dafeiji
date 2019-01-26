package com.meng.stg.thsss;

public class StageDataPackage
{
    public MyRandom Ran = new MyRandom();
    public bool C_Check = false;
    public bool OnReplay = false;
    public bool OnPractice = false;
    public int ContinueTimes = 0;
    private int VibrateTime = -1;
    public int SlowMode = 0;
    public string CurrentStageName = (string) null;
    public string SCBstring = (string) null;
    public Dictionary<string, CS_Data> CS_Cathe = new Dictionary<string, CS_Data>();
    public StateSwitchDataPackage StateSwitchData;
    public ReplayInfo RepInfo;
    public long TimeTotal;
    public long RealTimeTotal;
    public int TimeMain;
    public Rectangle BoundRect;
    public BaseMyPlane MyPlane;
    public BaseBossTouhou Boss;
    public List<BaseBullet_Touhou> BulletList;
    public List<BaseObject> MyBulletList;
    public List<BaseEnemyPlane> EnemyPlaneList;
    public List<BaseEffect> EffectList;
    public List<BaseObject> SpellList;
    public List<BaseObject> InterfaceList;
    public List<BaseItem> ItemList;
    public BaseStory Story;
    public BackgroundManager Background;
    public BackgroundManager Background2;
    public BackgroundManager3D Background3D;
    public ParticleManager3D Particle3D;
    public List<XAudio2_Player> SoundPlayList;
    public List<BaseMenuGroup> MenuGroupList;
    public DifficultLevel Difficulty;
    private Rectangle VibrateRectSave;

    public ClearHistory C_History { get; set; }

    public List<ScoreHistory> S_History { get; set; }

    public GlobalDataPackage GlobalData { get; set; }

    public Device DeviceMain
    {
        get
        {
            return this.GlobalData.DeviceMain;
        }
    }

    public Dictionary<string, IModel> ModelDictionary
    {
        get
        {
            return this.GlobalData.ModelDictionary;
        }
    }

    public Dictionary<string, TextureObject> TextureObjectDictionary
    {
        get
        {
            return this.GlobalData.TextureObjectDictionary;
        }
    }

    public Dictionary<string, XAudio2_Player> SoundDictionary
    {
        get
        {
            return this.GlobalData.SoundDictionary;
        }
    }

    public Wave_Player BGM_Player
    {
        get
        {
            return this.GlobalData.BGM_Player;
        }
        set
        {
            this.GlobalData.BGM_Player = value;
        }
    }

    public KeyClass KClass
    {
        get
        {
            return this.GlobalData.KClass;
        }
    }

    public MySprite SpriteMain
    {
        get
        {
            return this.GlobalData.SpriteMain;
        }
    }

    public Replay Rep
    {
        get
        {
            return this.GlobalData.Rep;
        }
    }

    public ScreenTexManager ScreenTexMan
    {
        get
        {
            return this.GlobalData.ScreenTexMan;
        }
    }

    public Effect EffectMain
    {
        get
        {
            return this.GlobalData.EffectMain;
        }
        set
        {
            this.GlobalData.EffectMain = value;
        }
    }

    public Dictionary<string, Effect> EffectDictionary
    {
        get
        {
            return this.GlobalData.EffectDictionary;
        }
    }

    public PlayerData PData
    {
        get
        {
            return this.GlobalData.PData;
        }
        set
        {
            this.GlobalData.PData = value;
        }
    }

    public string SlowRate
    {
        get
        {
            float num1 = 60f * (float) this.RealTimeTotal / (float) Stopwatch.Frequency;
            float num2 = (float) (((double) num1 - (double) this.TimeTotal) * 100.0) / num1;
            return ((double) num2 < 0.0 ? 0.0f : num2).ToString("F1") + "%";
        }
    }

    public StageDataPackage(GlobalDataPackage GlobalData)
    {
        this.GlobalData = GlobalData;
        this.Init();
    }

    public void Init()
    {
        this.BoundRect = new Rectangle(0, 0, 640, 480);
        this.BulletList = new List<BaseBullet_Touhou>();
        this.EnemyPlaneList = new List<BaseEnemyPlane>();
        this.MyBulletList = new List<BaseObject>();
        this.SpellList = new List<BaseObject>();
        this.EffectList = new List<BaseEffect>();
        this.ItemList = new List<BaseItem>();
        this.InterfaceList = new List<BaseObject>();
        this.SoundPlayList = new List<XAudio2_Player>();
        this.Background = new BackgroundManager();
        this.Background2 = new BackgroundManager();
        this.Background3D = new BackgroundManager3D(this);
        this.Particle3D = new ParticleManager3D(this);
        this.MenuGroupList = new List<BaseMenuGroup>();
        this.TimeMain = 0;
        this.RepInfo = new ReplayInfo();
    }

    public void Vibrate()
    {
        if (this.VibrateTime > this.TimeMain)
        {
            int maxValue = this.VibrateTime - this.TimeMain >= 20 ? 10 : (this.VibrateTime - this.TimeMain) / 2;
            this.BoundRect = new Rectangle(this.VibrateRectSave.X + (this.Ran.Next(maxValue) - maxValue / 2), this.VibrateRectSave.Y + (this.Ran.Next(maxValue) - maxValue / 2), this.VibrateRectSave.Width, this.VibrateRectSave.Height);
        }
        else
        {
            if (this.VibrateTime != this.TimeMain)
                return;
            this.BoundRect = this.VibrateRectSave;
        }
    }

    public void VibrateStart(int CuntinueTime)
    {
        if (this.VibrateTime < this.TimeMain)
            this.VibrateRectSave = this.BoundRect;
        this.VibrateTime = this.TimeMain + CuntinueTime;
    }

    public void VibrateStop()
    {
        if (this.VibrateTime < this.TimeMain)
            return;
        this.VibrateTime = -1;
        this.BoundRect = this.VibrateRectSave;
    }

    public void RemoveBullets()
    {
        foreach (BaseObject bullet in this.BulletList)
        bullet.GiveEndEffect();
        this.BulletList.Clear();
        foreach (BaseEnemyPlane enemyPlane in this.EnemyPlaneList)
        {
            enemyPlane.GiveEndEffect();
            if (enemyPlane.TxtureObject != null)
                this.SoundPlay("se_enep00.wav");
        }
        this.EnemyPlaneList.Clear();
        foreach (BaseObject bullet in this.MyBulletList)
        {
            ParticleSmaller particleSmaller = new ParticleSmaller(this, "光点", bullet.Position, bullet.Velocity, bullet.Direction);
            particleSmaller.LifeTime = 30;
            particleSmaller.Scale = 0.5f;
        }
        this.MyBulletList.Clear();
    }

    public void SoundPlay(string MusicName)
    {
        this.SoundPlay(MusicName, 0.5f);
    }

    public void SoundPlay(string MusicName, float VoicePos)
    {
        if (MusicName == null || !this.SoundDictionary.ContainsKey(MusicName))
            return;
        if (!this.SoundPlayList.Contains(this.SoundDictionary[MusicName]))
        {
            this.SoundDictionary[MusicName].VoicePos = VoicePos;
            this.SoundPlayList.Add(this.SoundDictionary[MusicName]);
        }
        else
            this.SoundDictionary[MusicName].VoicePos = (float) (((double) VoicePos + (double) this.SoundDictionary[MusicName].VoicePos) / 2.0);
    }

    public void ChangeBGM(
            string MusicName,
            int PlayBegin,
            int PlayLength,
            int LoopCount,
            int LoopBegin,
            int LoopEnd)
    {
        int bgmVolume = this.GlobalData.BGMVolume;
        this.BGM_Player.FileName = MusicName;
        this.BGM_Player.Volume = bgmVolume;
        this.BGM_Player.PlayRepeat(PlayBegin, PlayLength, LoopCount, LoopBegin, LoopEnd);
    }

    public void ChangeBGM(string MusicName, bool Repeat)
    {
        if (Repeat)
            this.ChangeBGM(MusicName, 0, 0, (int) byte.MaxValue, 0, 0);
        else
            this.ChangeBGM(MusicName, 0, 0, 0, 0, 0);
    }

    public void SetReplayInfo(string StartStage)
    {
        this.RepInfo.MyPlaneName = this.MyPlane.Name;
        this.RepInfo.Rank = this.Difficulty;
        this.RepInfo.WeaponType = this.MyPlane.WeaponType;
        this.RepInfo.StartStage = StartStage;
        this.SetReplayStageInfo();
    }

    public void SetReplayStageInfo()
    {
        if (this.OnReplay || this.RepInfo.MyPlaneData.Count > 0 && this.RepInfo.MyPlaneData[this.RepInfo.MyPlaneData.Count - 1].DataPosition + 2L == this.Rep.DataPosition)
            return;
        this.RepInfo.LastStage = this.CurrentStageName;
        this.RepInfo.SlowRate = this.SlowRate;
        List<MyPlaneInfo> planeData = this.RepInfo.MyPlaneData;
        MyPlaneInfo myPlaneInfo1 = new MyPlaneInfo();
        myPlaneInfo1.Score = this.MyPlane.Score;
        myPlaneInfo1.Life = this.MyPlane.Life;
        myPlaneInfo1.Spell = this.MyPlane.Spell;
        myPlaneInfo1.Power = this.MyPlane.Power;
        myPlaneInfo1.Graze = this.MyPlane.Graze;
        MyPlaneInfo myPlaneInfo2 = myPlaneInfo1;
        PointF originalPosition = this.MyPlane.OriginalPosition;
        double x = (double) originalPosition.X;
        myPlaneInfo2.PosX = (float) x;
        MyPlaneInfo myPlaneInfo3 = myPlaneInfo1;
        originalPosition = this.MyPlane.OriginalPosition;
        double y = (double) originalPosition.Y;
        myPlaneInfo3.PosY = (float) y;
        myPlaneInfo1.LifeChip = this.MyPlane.LifeChip;
        myPlaneInfo1.SpellChip = this.MyPlane.SpellChip;
        myPlaneInfo1.LifeUpCount = this.MyPlane.LifeUpCount;
        myPlaneInfo1.StarPoint = this.MyPlane.StarPoint;
        myPlaneInfo1.HighItemScore = this.MyPlane.HighItemScore;
        myPlaneInfo1.Rate = this.MyPlane.Rate;
        myPlaneInfo1.LastColor = this.MyPlane.LastColor;
        myPlaneInfo1.DataPosition = this.Rep.DataPosition;
        MyPlaneInfo myPlaneInfo4 = myPlaneInfo1;
        planeData.Add(myPlaneInfo4);
        this.Rep.WriteKey(57358);
    }

    public bool FindScore(ScoreHistory SH)
    {
        return SH.Rank == this.Difficulty && SH.MyPlaneFullName == this.MyPlane.FullName;
    }

    public bool FindClear(ClearHistory CH)
    {
        return CH.Rank == this.Difficulty && CH.MyPlaneFullName == this.MyPlane.FullName;
    }

    public void PreLoadCS(string Path)
    {
        foreach (string file in Directory.GetFiles(Path, "*.mbg"))
        {
            FileInfo fileInfo = new FileInfo(file);
            CS_Data csData = new CS_Data(file);
            csData.String2Data(this);
            this.CS_Cathe.Add(file, csData);
        }
        foreach (string directory in Directory.GetDirectories(Path))
        this.PreLoadCS(directory);
    }

    public void ClearCSCathe()
    {
        this.CS_Cathe.Clear();
    }

    public CS_Data LoadCS(string FileName)
    {
        if (this.CS_Cathe.ContainsKey(FileName))
            return this.CS_Cathe[FileName];
        CS_Data csData = new CS_Data(FileName);
        csData.String2Data(this);
        return csData;
    }

    public Texture DrawString(string text, System.Drawing.Font font, Brush brush, int width, int height)
    {
        Bitmap bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
        Graphics graphics = Graphics.FromImage((Image) bitmap);
        graphics.TextRenderingHint = TextRenderingHint.AntiAlias;
        graphics.DrawString(text, font, brush, 0.0f, 0.0f);
        graphics.Dispose();
        Texture texture = new Texture(this.DeviceMain, width, height, 1, Usage.None, Format.A8R8G8B8, Pool.Managed);
        texture.GetLevelDescription(0);
        DataRectangle dataRectangle = texture.LockRectangle(0, new Rectangle(0, 0, width, height), LockFlags.Discard);
        BitmapData bitmapdata = bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, bitmap.PixelFormat);
        IntPtr scan0 = bitmapdata.Scan0;
        dataRectangle.Data.WriteRange(scan0, (long) (bitmapdata.Width * bitmapdata.Height * 4));
        bitmap.UnlockBits(bitmapdata);
        texture.UnlockRectangle(0);
        bitmap.Dispose();
        return texture;
    }

    public void Dispose()
    {
        if (this.Background3D != null)
            this.Background3D.Dispose();
        if (this.Particle3D != null)
            this.Particle3D.Dispose();
        if (this.Rep == null)
            return;
        this.Rep.Dispose();
    }
}
