package com.meng.TaiHunDanmaku.bullets;

import com.badlogic.gdx.math.*;

public class ReflexAndThrough {
    private Rectangle rectangle;
    private Position position;

    public ReflexAndThrough(Position position) {
        this.position = position;
        switch (position) {
            case Top:
                rectangle = new Rectangle(0, 450, 386, 5);
                break;
            case Bottom:
                rectangle = new Rectangle(0, 5, 386, 5);
                break;
            case Left:
                rectangle = new Rectangle(0, 0, 5, 450);
                break;
            case Right:
                rectangle = new Rectangle(381, 0, 5, 450);
                break;
        }
    }

    public void update() {
        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
            if (rectangle.contains(baseBullet.objectCenter)) {
                switch (position) {
                    case Top:
                        if (baseBullet.reflexTopCount > 0) {
                            --baseBullet.reflexTopCount;
                            baseBullet.objectCenter.y -= baseBullet.velocity.y;
                            baseBullet.velocity.y = -baseBullet.velocity.y;
                            baseBullet.acceleration.y = -baseBullet.acceleration.y;
                        } else if (baseBullet.thoughTopCount > 0) {
                            --baseBullet.thoughTopCount;
                            baseBullet.objectCenter.y = 6;
                        }
                        break;
                    case Bottom:
                        if (baseBullet.reflexBottomCount > 0) {
                            --baseBullet.reflexBottomCount;
                            baseBullet.objectCenter.y -= baseBullet.velocity.y;
                            baseBullet.velocity.y = -baseBullet.velocity.y;
                            baseBullet.acceleration.y = -baseBullet.acceleration.y;
                        } else if (baseBullet.thoughBottomCount > 0) {
                            --baseBullet.thoughBottomCount;
                            baseBullet.objectCenter.y = 444;
                        }
                        break;
                    case Left:
                        if (baseBullet.reflexLeftCount > 0) {
                            --baseBullet.reflexLeftCount;
                            baseBullet.objectCenter.x -= baseBullet.velocity.x;
                            baseBullet.velocity.x = -baseBullet.velocity.x;
                            baseBullet.acceleration.x = -baseBullet.acceleration.x;
                        } else if (baseBullet.thoughLeftCount > 0) {
                            --baseBullet.thoughLeftCount;
                            baseBullet.objectCenter.x = 380;
                        }
                        break;
                    case Right:
                        if (baseBullet.reflexRightCount > 0) {
                            --baseBullet.reflexRightCount;
                            baseBullet.objectCenter.x -= baseBullet.velocity.x;
                            baseBullet.velocity.x = -baseBullet.velocity.x;
                            baseBullet.acceleration.x = -baseBullet.acceleration.x;
                        } else if (baseBullet.thoughRightCount > 0) {
                            --baseBullet.thoughRightCount;
                            baseBullet.objectCenter.x = 6;
                        }
                        break;
                }

            }
        }
    }

    public enum Position {
        Top,
        Bottom,
        Left,
        Right
    }
}
