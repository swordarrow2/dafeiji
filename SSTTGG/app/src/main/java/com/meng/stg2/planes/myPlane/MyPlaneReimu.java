package com.meng.stg2.planes.myPlane;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.meng.stg2.helpers.*;
import com.meng.stg2.ui.*;

public class MyPlaneReimu extends BaseMyPlane {

    @Override
    public void shoot() {
        if (existTime % 3 == 1) {
            Vector2 vel=new Vector2(0, 47);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(position.x + 8, position.y + 32), vel);
            ObjectPools.reimuShootPool.obtain().init(new Vector2(position.x - 8, position.y + 32), vel);
        }
    }

    @Override
    public void init() {
        super.init();
		playerAnim.setName("reimu");
        bombTime = Data.ReimuBombTime;
		if (onStartActivity.pl.equals("A:诱导")) {
            switch (power) {
                case 4:
                    subPlane4 = new SubPlaneReimuA();
                    subPlane4.init(4);
                case 3:
                    subPlane3 = new SubPlaneReimuA();
                    subPlane3.init(3);
                case 2:
                    subPlane2 = new SubPlaneReimuA();
                    subPlane2.init(2);
                case 1:
                    subPlane1 = new SubPlaneReimuA();
                    subPlane1.init(1);
            }
        } else if (onStartActivity.pl.equals("B:针")) {
            switch (power) {
                case 4:
                    subPlane4 = new SubPlaneReimuB();
                    subPlane4.init(4);
                case 3:
                    subPlane3 = new SubPlaneReimuB();
                    subPlane3.init(3);
                case 2:
                    subPlane2 = new SubPlaneReimuB();
                    subPlane2.init(2);
                case 1:
                    subPlane1 = new SubPlaneReimuB();
                    subPlane1.init(1);
            }
        } else {
            power = 0;
        }
    }


    @Override
    public void kill() {
        miss++;
        if (true) {
            return;
        }
        super.kill();
        switch (power) {
            case 4:
                subPlane4.kill();
            case 3:
                subPlane3.kill();
            case 2:
                subPlane2.kill();
            case 1:
                subPlane1.kill();
        }
        new MyPlaneReimu().init();
    }

    @Override
    public void update() {
        super.update();
        switch (power) {
            case 4:
                subPlane4.update();
            case 3:
                subPlane3.update();
            case 2:
                subPlane2.update();
            case 1:
                subPlane1.update();
        }
    }

    @Override
    public void onPowerInc() {
        if (power >= 4) {
            power = 4;
        }
        if (onStartActivity.pl.equals("A:诱导")) {
            switch (power) {
                case 4:
                    if (subPlane4 == null) {
                        subPlane4 = new SubPlaneReimuA();
                        subPlane4.init(4);
                    }
                case 3:
                    if (subPlane3 == null) {
                        subPlane3 = new SubPlaneReimuA();
                        subPlane3.init(3);
                    }
                case 2:
                    if (subPlane2 == null) {
                        subPlane2 = new SubPlaneReimuA();
                        subPlane2.init(2);
                    }
                case 1:
                    if (subPlane1 == null) {
                        subPlane1 = new SubPlaneReimuA();
                        subPlane1.init(1);
                    }
            }
        } else if (onStartActivity.pl.equals("B:针")) {
            switch (power) {
                case 4:
                    if (subPlane4 == null) {
                        subPlane4 = new SubPlaneReimuB();
                        subPlane4.init(4);
                    }
                case 3:
                    if (subPlane4 == null) {
                        subPlane3 = new SubPlaneReimuB();
                        subPlane3.init(3);
                    }
                case 2:
                    if (subPlane4 == null) {
                        subPlane2 = new SubPlaneReimuB();
                        subPlane2.init(2);
                    }
                case 1:
                    if (subPlane4 == null) {
                        subPlane1 = new SubPlaneReimuB();
                        subPlane1.init(1);
                    }
            }
        }
    }

    @Override
    public void onPowerDec() {
        // TODO: Implement this method
    }


    @Override
    public void bomb() {
        Vector2 vel=new Vector2(0, 30);
        if (bombTime % 16 == 0) {
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x, 0), vel);
        }
        if (bombTime % 16 == 4) {
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x - 20, 0), vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x + 20, 0), vel);
        }
        if (bombTime % 16 == 8) {
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x - 40, 0), vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x + 40, 0), vel);
        }
        if (bombTime % 16 == 12) {
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x - 20, 0), vel);
            ObjectPools.reimuBombPool.obtain().init(new Vector2(position.x + 20, 0), vel);
        }
    }

	public class SubPlaneReimuA extends BaseSubPlane {
		private int degree=0;
		private Vector2 tmpv=new Vector2();
		private int[] pos=new int[]{
			0,20,
			0,32,
			-16,-20,16,-20,
			-32,0,32,0,
			-16,-20,0,-30,16,-20,
			-32,0,0,-32,32,0,
			-16,-20,-8,-30,8,-30,16,-20,
			-32,0,-16,-32,16,-32,32,0
		};

		@Override
		public Drawable getDrawable() {
			return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
		}

		@Override
		public void init(int subPlaneNumber) {
			super.init(subPlaneNumber);
			switch (bianHao) {
				case 1:
					tmpv.set(0, 8).rotate(10);
					break;
				case 2:
					tmpv.set(0, 8);
					break;
				case 3:
					tmpv.set(0, 8);
					break;
				case 4:
					tmpv.set(0, 8).rotate(-10);
					break;
			}
		}


		@Override
		public float getRotationDegree() {
			degree += 5;
			return degree;
		}

		@Override
		public int[] getSubPlanePosition() {
			return pos;
		}

		@Override
		public Vector2 getSize() {
			return new Vector2(16, 16);
		}

		@Override
		public void shoot() {
			if (existTime % 7 == 1) {
				ObjectPools.reimuSubPlaneBulletInducePool.obtain().init(new Vector2(position.x, position.y), tmpv);
			}
		}
	}

	public class SubPlaneReimuB extends BaseSubPlane {
		private int degree=0;
		private Vector2 vel=new Vector2(0, 37);
		private int[] pos=new int[]{
			0,20,
			0,32,
			-16,20,16,20,
			-32,0,32,0,
			-16,20,0,30,16,20,
			-32,0,0,32,32,0,
			-16,20,-8,30,8,30,16,20,
			-32,0,-16,32,16,32,32,0
		};

		@Override
		public Drawable getDrawable() {
			return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
		}

		@Override
		public void init(int subPlaneNumber) {
			super.init(subPlaneNumber);
		}


		@Override
		public float getRotationDegree() {
			degree += 5;
			return degree;
		}

		@Override
		public int[] getSubPlanePosition() {
			return pos;
		}

		@Override
		public Vector2 getSize() {
			return new Vector2(16, 16);
		}

		@Override
		public void shoot() {
			if (existTime % 4 == 1) {
				ObjectPools.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(position.x + 4, position.y + 16), vel);
				ObjectPools.reimuSubPlaneBulletStraightPool.obtain().init(new Vector2(position.x - 4, position.y + 16), vel);
			}
		}
	}
}
