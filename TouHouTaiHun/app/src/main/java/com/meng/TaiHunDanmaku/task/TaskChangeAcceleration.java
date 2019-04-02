package com.meng.TaiHunDanmaku.task;

public class TaskChangeAcceleration extends Task {

    public TaskChangeAcceleration(float x, float y,ChangeMode changeMode) {
        this.changeMode=changeMode;
        tmpVector2.x = x;
        tmpVector2.y = y;
    }
}
