package com.meng.TaiHunDanmaku.task;

import com.badlogic.gdx.math.Vector2;

public class TaskMoveBy extends Task {

    public TaskMoveBy(float x, float y) {
        tmpVector2.x = x;
        tmpVector2.y = y;
    }

    public TaskMoveBy(Vector2 vector2) {
        tmpVector2.x = vector2.x;
        tmpVector2.y = vector2.y;
    }
}
