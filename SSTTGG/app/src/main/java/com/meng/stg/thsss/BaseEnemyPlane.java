package com.meng.stg.thsss;

/**
 * Created by Administrator on 2019/1/26.
 */

public class BaseEnemyPlane:BaseObject_CS {
public BaseEnemyPlane() {
        }

public BaseEnemyPlane(
        StageDataPackage StageData,
        string textureName,
        PointF p,
        float v,
        double drctn)
        : base(StageData,textureName,p,v,drctn) {
        this.Region=4;
        this.EnemyPlaneList.Add(this);
        }

public override void Shoot() {
        }

public override void Ctrl() {
        base.Ctrl();
        if(this.ShootEnabled)
        this.Shoot();
        if(!this.OutBoundary())
        return;
        this.EnemyPlaneList.Remove(this);
        }

public override void HitCheckAll() {
        for(int index = this.MyBulletList.Count-1;index>=0;--index) {
        if(this.HitCheck(this.MyBulletList[index])) {
        float da = MyBulletList[index].Damage;

        if(MyPlane.SpellList.Count>0) {
        da*=1.25f;
        }
        if(MyPlane.EnchantmentState==EnchantmentType.Red) {
        da*=1.25f;
        }
        if(MyPlane.LastColor==EnchantmentType.Red) {
        da*=1.25f;
        }
        HealthPoint-=da;

        this.MyBulletList[index].GiveEndEffect();
        this.MyBulletList.RemoveAt(index);
        this.MyPlane.Score+=10L;
        PointF originalPosition;
        if((double)this.HealthPoint<=0.0) {
        this.GiveEndEffect();
        this.GiveItems();
        this.EnemyPlaneList.Remove(this);
        StageDataPackage stageData = this.StageData;
        originalPosition=this.OriginalPosition;
        double num = (double)originalPosition.X/(double)this.BoundRect.Width;
        stageData.SoundPlay("se_enep00.wav",(float)num);
        break;
        }
        StageDataPackage stageData1 = this.StageData;
        originalPosition=this.OriginalPosition;
        double num1 = (double)originalPosition.X/(double)this.BoundRect.Width;
        stageData1.SoundPlay("se_damage01.wav",(float)num1);
        }
        }
        if(!this.MyPlane.HitEnabled||!this.HitCheck((BaseObject)this.MyPlane))
        return;
        this.MyPlane.PreMiss();
        }

public override void GiveEndEffect() {
        }

public override void GiveItems() {
        }
        }