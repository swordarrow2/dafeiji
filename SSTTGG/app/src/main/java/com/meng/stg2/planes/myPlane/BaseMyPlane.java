package com.meng.stg2.planes.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.meng.stg2.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.planes.*;
import com.meng.stg2.ui.*;

public abstract class BaseMyPlane extends BaseGameObject {

    public static BaseMyPlane instance;

    public int unmatchedTime;
    public boolean onUnmatched = false;
    public int bombTime;
    public boolean onBomb = false;

    public int power = 3;
    public int maxPoint = 10000;
    public int miss = 0;

    public JudgePointAnim judgeAnim = new JudgePointAnim();
	public PlayerAnim playerAnim = new PlayerAnim();

	public boolean slow = false;

    public BaseSubPlane subPlane1, subPlane2, subPlane3, subPlane4;

    public void init() {
        super.init();
        instance = this;
        judgeAnim.init();
        existTime = 0;
        position.set(MainScreen.width / 2, 80);
        image.setSize(30, 46);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        unmatchedTime = 1;
        onUnmatched = true;
        MainScreen.mainGroup.addActor(image);
		lastPosition.x = position.x = 270;
    }

    public void kill() {
        super.kill();
    }

    public void update() {
        super.update();
        animFlag++;
        position.set(MathUtils.clamp(position.x, 10, 376), MathUtils.clamp(position.y, 10, 440));
		if (MainActivity.status == 1) {
		instance.position.x += 5;
		}
		if (MainActivity.status == 2) {
		position.y += 5;
		}
        image.setPosition(position.x, position.y, Align.center);
        shoot();
     //   judge();
        if (onBomb) {
            onUnmatched = true;
            bomb();
            bombTime--;
        }
        if (onUnmatched) {
            unmatchedTime--;
        }
        if (bombTime == 0) {
            onBomb = false;
            bombTime = Data.ReimuBombTime;
        }
        if (unmatchedTime == 0) {
            onUnmatched = false;
            unmatchedTime = Data.ReimuUnmatchedTime;
        }
		playerAnim.update(position.x, position.y);
        image.toBack();
		//  animation2.update();
        judgeAnim.update();

    }

    public void incPower(int p) {
        power += p;
        onPowerInc();
    }

    public void decPower(int p) {
        power -= p;
        onPowerDec();
    }

//    public void judge() {
//        for (BaseEnemyBullet baseBullet : BaseEnemyBullet.instances) {
//            if (baseBullet.getCollisionArea().contains(position)) {
//                baseBullet.kill();
//                kill();
//            }
//        }
//    }

    public abstract void bomb();

    public abstract void shoot();

    public abstract void onPowerInc();

    public abstract void onPowerDec();

	public class PlayerAnim {
		private int animFrom = 0;
		private int animTo = 7;
		private int everyAnimFrameTime = 5;
		private int time = 0;
		private int curFrameNumber = 0;
		private String name = "reimu";
		private MoveStatus status = MoveStatus.stay;

		public void setName(String name) {
			this.name = name;
		}

		public void update(float x, float y) {
			++time;
			if (x > lastPosition.x) {
				lastPosition.x = x;
				if (status != MoveStatus.moveRight) {
					animFrom = 16;
					animTo = 23;
					curFrameNumber = 16;
					status = MoveStatus.moveRight;
				}
			} else if (x < lastPosition.x) {
				lastPosition.x = x;
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
			if (time >= everyAnimFrameTime) {
				++curFrameNumber;
				time = 0;
			}
			if (curFrameNumber > animTo) {
				curFrameNumber = animFrom + 5;
			}
			image.setDrawable(ResourcesManager.textures.get(name + curFrameNumber));
		}
	}
	
	public class JudgePointAnim {

		private int stat = 0;
		private Image jImage = new Image();

		public void init() {
			jImage.setDrawable(ResourcesManager.textures.get("effect23"));
			jImage.setSize(48, 48);
			jImage.setOrigin(jImage.getWidth() / 2, jImage.getHeight() / 2);
			MainScreen.mainGroup.addActor(jImage);
		}

		public void update() {
			jImage.setRotation(stat);
			if (slow) {
				jImage.setSize(48, 48);
			} else {
				jImage.setSize(0, 0);
			}
			stat += 2;
			jImage.setPosition(position.x, position.y, Align.center);
			jImage.toFront();
		}
	}
	
	public abstract class BaseSubPlane extends BaseGameObject{

		public Vector2 nowPosition=Vector2.Zero.cpy();
		//public BaseMyPlane myPlane;
		public int bianHao=1;

		private int[] subPlanePosition;

		public void init(int subPlaneNumber){
			super.init();
			bianHao=subPlaneNumber;
			subPlanePosition=getSubPlanePosition();
			size=getSize();
			BaseSubPlane.this.position=BaseMyPlane.this.position.cpy();
			image.setDrawable(getDrawable());
			image.setSize(size.x,size.y);
			image.setRotation(getRotationDegree());
			image.setOrigin(image.getWidth()/2,image.getHeight()/2);
			MainScreen.mainGroup.addActor(image);
		}

		public void kill(){
			super.kill();
			image.remove();
		}

		public void update(){
			super.update();
			switch(power){
				case 1:
					if(slow){
						nowPosition.set(position.x+subPlanePosition[0],position.y+subPlanePosition[1]);
					}else{
						nowPosition.set(position.x+subPlanePosition[2],position.y+subPlanePosition[3]);
					}
					break;
				case 2:
					if(slow){
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[4],position.y+subPlanePosition[5]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[6],position.y+subPlanePosition[7]);
								break;
						}
					}else{
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[8],position.y+subPlanePosition[9]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[10],position.y+subPlanePosition[11]);
								break;
						}
					}
					break;
				case 3:
					if(slow){
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[12],position.y+subPlanePosition[13]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[14],position.y+subPlanePosition[15]);
								break;
							case 3:
								nowPosition.set(position.x+subPlanePosition[16],position.y+subPlanePosition[17]);
								break;
						}
					}else{
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[18],position.y+subPlanePosition[19]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[20],position.y+subPlanePosition[21]);
								break;
							case 3:
								nowPosition.set(position.x+subPlanePosition[22],position.y+subPlanePosition[23]);
								break;
						}
					}
					break;
				case 4:
					if(slow){
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[24],position.y+subPlanePosition[25]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[26],position.y+subPlanePosition[27]);
								break;
							case 3:
								nowPosition.set(position.x+subPlanePosition[28],position.y+subPlanePosition[29]);
								break;
							case 4:
								nowPosition.set(position.x+subPlanePosition[30],position.y+subPlanePosition[31]);
								break;
						}
					}else{
						switch(bianHao){
							case 1:
								nowPosition.set(position.x+subPlanePosition[32],position.y+subPlanePosition[33]);
								break;
							case 2:
								nowPosition.set(position.x+subPlanePosition[34],position.y+subPlanePosition[35]);
								break;
							case 3:
								nowPosition.set(position.x+subPlanePosition[36],position.y+subPlanePosition[37]);
								break;
							case 4:
								nowPosition.set(position.x+subPlanePosition[38],position.y+subPlanePosition[39]);
								break;
						}
					}
					break;
			}
			position.add(nowPosition.sub(position).scl(0.2f));
			// image.setDrawable(getDrawable());
			image.setRotation(getRotationDegree());
			image.setPosition(position.x,position.y,Align.center);
			//image.setOrigin(image.getWidth()/2,image.getHeight()/2);
			shoot();
		}


		public abstract Drawable getDrawable();

		public abstract float getRotationDegree();

		public abstract Vector2 getSize();

		public abstract void shoot();

		public abstract int[] getSubPlanePosition();
	}
}
