package com.meng.TaiHunDanmaku.task;

import com.badlogic.gdx.math.*;
import com.meng.TaiHunDanmaku.baseObjects.bullets.enemy.*;

public class Task {
    public int holdingTime = 1;
    public BulletShooter[] bulletShooter;
    public Vector2 tmpVector2 = new Vector2();
    public Runnable runnable;
    public ChangeMode changeMode;
}
