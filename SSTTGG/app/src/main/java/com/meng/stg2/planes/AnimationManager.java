package com.meng.stg2.planes;

import com.meng.stg2.helpers.ResourcesManager;
import com.meng.stg2.planes.myPlane.BaseMyPlane;
import com.meng.stg2.*;

public class AnimationManager {
    private BaseGameObject myPlane;
    private int animFrom=0;
    private int animTo=7;
    private int everyAnimFrameTime=0;
    private int time=0;
    private int curFrameNumber=0;
    private MoveStatus status=MoveStatus.stay;

    public AnimationManager(BaseMyPlane obj, int everyAnimFrameTime) {
        this.everyAnimFrameTime = everyAnimFrameTime;
        myPlane = obj;
    }

	public void updatePosition(float x, float y) {
		if (x > myPlane.lastPosition.x) {
			myPlane.lastPosition.x = x;
            if (status != MoveStatus.moveRight) {
				animFrom = 16;
				animTo = 23;
				curFrameNumber = 16;
				status = MoveStatus.moveRight;
			}
		} else if (x < myPlane.lastPosition.x) {
			myPlane.lastPosition.x = x;
			if (status != MoveStatus.moveLeft) {
				animFrom = 8;
				animTo = 15;
				curFrameNumber = 8;
				status = MoveStatus.moveLeft;
			}
        } else if (status != MoveStatus.stay) {
			animFrom = 0;
			animTo = 7;
			curFrameNumber = 0;
			status = MoveStatus.stay;
		}
	}

    public void update() {
        ++time;
        if (time >= everyAnimFrameTime) {
            ++curFrameNumber;
            time = 0;
        }
        if (curFrameNumber > animTo) {
            curFrameNumber = animFrom + 5;
        }
        myPlane.image.setDrawable(ResourcesManager.textures.get("reimu" + curFrameNumber));
    }
}
