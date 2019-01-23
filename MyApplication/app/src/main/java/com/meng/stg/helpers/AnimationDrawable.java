package com.meng.stg.helpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Administrator on 2019/1/23.
 */

public class AnimationDrawable extends TextureRegionDrawable{
    private Animation<TextureRegion> mAnimation;
    public float currentTime = 0;
    public void advance(float delta) {
        currentTime += delta;
        setRegion(mAnimation.getKeyFrame(currentTime, false));
    }
    public void setAnimation(Animation<TextureRegion> animation) {
        mAnimation = animation;
        currentTime = 0;
        setRegion(mAnimation.getKeyFrame(currentTime));
    }
    public Animation<TextureRegion> getAnimation() {
        return mAnimation;
    }
}