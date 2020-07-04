package com.meng.stg2.characters.player;

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
			SubInfo.Info[][] info = SubInfo.geta();
			switch (power / 100) {
				case 4:
					SubInfo.Info[] si4 = info[3];
					subPlane4 = new SubPlaneReimuA();
					subPlane4.init();
					subPlane4.setSubInfo(si4[3]);
					subPlane3 = new SubPlaneReimuA();
					subPlane3.init();
					subPlane3.setSubInfo(si4[2]);
					subPlane2 = new SubPlaneReimuA();
					subPlane2.init();
					subPlane2.setSubInfo(si4[1]);
					subPlane1 = new SubPlaneReimuA();
					subPlane1.init();
					subPlane1.setSubInfo(si4[0]);
					break;
				case 3:
					SubInfo.Info[] si3 = info[2];
					subPlane3 = new SubPlaneReimuA();
					subPlane3.init();
					subPlane3.setSubInfo(si3[2]);
					subPlane2 = new SubPlaneReimuA();
					subPlane2.init();
					subPlane2.setSubInfo(si3[1]);
					subPlane1 = new SubPlaneReimuA();
					subPlane1.init();
					subPlane1.setSubInfo(si3[0]);
					break;
				case 2:
					SubInfo.Info[] si2 = info[1];
					subPlane2 = new SubPlaneReimuA();
					subPlane2.init();
					subPlane2.setSubInfo(si2[1]);
					subPlane1 = new SubPlaneReimuA();
					subPlane1.init();
					subPlane1.setSubInfo(si2[0]);
					break;
				case 1:
					SubInfo.Info[] si1 = info[0];
					subPlane1 = new SubPlaneReimuA();
					subPlane1.init();
					subPlane1.setSubInfo(si1[0]);
					break;
			}
		} else if (onStartActivity.pl.equals("B:针")) {
            SubInfo.Info[][] info = SubInfo.getb();
			switch (power / 100) {
				case 4:
					SubInfo.Info[] si4 = info[3];
					subPlane4 = new SubPlaneReimuB();
					subPlane4.init();
					subPlane4.setSubInfo(si4[3]);
					subPlane3 = new SubPlaneReimuB();
					subPlane3.init();
					subPlane3.setSubInfo(si4[2]);
					subPlane2 = new SubPlaneReimuB();
					subPlane2.init();
					subPlane2.setSubInfo(si4[1]);
					subPlane1 = new SubPlaneReimuB();
					subPlane1.init();
					subPlane1.setSubInfo(si4[0]);
					break;
				case 3:
					SubInfo.Info[] si3 = info[2];
					subPlane3 = new SubPlaneReimuB();
					subPlane3.init();
					subPlane3.setSubInfo(si3[2]);
					subPlane2 = new SubPlaneReimuB();
					subPlane2.init();
					subPlane2.setSubInfo(si3[1]);
					subPlane1 = new SubPlaneReimuB();
					subPlane1.init();
					subPlane1.setSubInfo(si3[0]);
					break;
				case 2:
					SubInfo.Info[] si2 = info[1];
					subPlane2 = new SubPlaneReimuB();
					subPlane2.init();
					subPlane2.setSubInfo(si2[1]);
					subPlane1 = new SubPlaneReimuB();
					subPlane1.init();
					subPlane1.setSubInfo(si2[0]);
					break;
				case 1:
					SubInfo.Info[] si1 = info[0];
					subPlane1 = new SubPlaneReimuB();
					subPlane1.init();
					subPlane1.setSubInfo(si1[0]);
					break;
			}
        } else {
            power = 0;
        }
    }

    @Override
    public void kill() {
        miss++;
        super.kill();
        switch (power / 100) {
            case 4:
                subPlane4.kill();
				subPlane4 = null;
            case 3:
                subPlane3.kill();
				subPlane3 = null;
            case 2:
                subPlane2.kill();
				subPlane2 = null;
            case 1:
                subPlane1.kill();
				subPlane1 = null;
        }
		power -= 100;
        BaseMyPlane.instance.init();
    }

    @Override
    public void update() {
        super.update();
		if (power >= 400) {
			subPlane4.update();
		}
		if (power >= 300) {
			subPlane3.update();
		}
		if (power >= 200) {
			subPlane2.update();
		}
		if (power >= 100) {
			subPlane1.update();
		}
    }

    @Override
    public void onPowerInc() {
        if (power > 400) {
            power = 400;
        }
        if (onStartActivity.pl.equals("A:诱导")) {
			SubInfo.Info[][] info = SubInfo.geta();
			switch (power / 100) {
                case 4:
					if (subPlane4 == null) {
                        subPlane4 = new SubPlaneReimuA();
                        subPlane4.init();
                    }
					SubInfo.Info[] si4 = info[3];
					subPlane4.setSubInfo(si4[3]);
					subPlane3.setSubInfo(si4[2]);
					subPlane2.setSubInfo(si4[1]);
					subPlane1.setSubInfo(si4[0]);
					break;
                case 3:
                    if (subPlane3 == null) {
                        subPlane3 = new SubPlaneReimuA();
                        subPlane3.init();
                    }
					SubInfo.Info[] si3 = info[2];
					subPlane3.setSubInfo(si3[2]);
					subPlane2.setSubInfo(si3[1]);
					subPlane1.setSubInfo(si3[0]);
					break;
                case 2:
                    if (subPlane2 == null) {
                        subPlane2 = new SubPlaneReimuA();
                        subPlane2.init();
                    }
					SubInfo.Info[] si2 = info[1];
					subPlane2.setSubInfo(si2[1]);
					subPlane1.setSubInfo(si2[0]);
					break;
                case 1:
                    if (subPlane1 == null) {
                        subPlane1 = new SubPlaneReimuA();
                        subPlane1.init();
                    }
					subPlane1.setSubInfo(info[0][0]);
            }
        } else if (onStartActivity.pl.equals("B:针")) {
			SubInfo.Info[][] info = SubInfo.getb();
			switch (power / 100) {
                case 4:
					if (subPlane4 == null) {
                        subPlane4 = new SubPlaneReimuB();
                        subPlane4.init();
                    }
					SubInfo.Info[] si4 = info[3];
					subPlane4.setSubInfo(si4[3]);
					subPlane3.setSubInfo(si4[2]);
					subPlane2.setSubInfo(si4[1]);
					subPlane1.setSubInfo(si4[0]);
					break;
                case 3:
                    if (subPlane3 == null) {
                        subPlane3 = new SubPlaneReimuB();
                        subPlane3.init();
                    }
					SubInfo.Info[] si3 = info[2];
					subPlane3.setSubInfo(si3[2]);
					subPlane2.setSubInfo(si3[1]);
					subPlane1.setSubInfo(si3[0]);
					break;
                case 2:
                    if (subPlane2 == null) {
                        subPlane2 = new SubPlaneReimuB();
                        subPlane2.init();
                    }
					SubInfo.Info[] si2 = info[1];
					subPlane2.setSubInfo(si2[1]);
					subPlane1.setSubInfo(si2[0]);
					break;
                case 1:
                    if (subPlane1 == null) {
                        subPlane1 = new SubPlaneReimuB();
                        subPlane1.init();
                    }
					subPlane1.setSubInfo(info[0][0]);
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

		@Override
		public Drawable getDrawable() {
			return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
		}

		@Override
		public void init() {
			super.init();
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

		@Override
		public Drawable getDrawable() {
			return ResourcesManager.textures.get(TextureNameManager.ReimuSubPlane);
		}

		@Override
		public void init() {
			super.init();
		}


		@Override
		public float getRotationDegree() {
			degree += 5;
			return degree;
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
