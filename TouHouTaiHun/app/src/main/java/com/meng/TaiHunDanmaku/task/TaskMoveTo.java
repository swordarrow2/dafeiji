package com.meng.TaiHunDanmaku.task;

import com.badlogic.gdx.math.Vector2;

public class TaskMoveTo extends Task {

    public TaskMoveTo(float x, float y) {
        tmpVector2.x = x;
        tmpVector2.y = y;
    }

    public TaskMoveTo(Vector2 vector2) {
        tmpVector2.x = vector2.x;
        tmpVector2.y = vector2.y;
    }
}
