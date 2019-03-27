package cn.s3bit.th902.gamecontents;

public interface IJudgeCallback {
    public static final IJudgeCallback NONE = new IJudgeCallback() {
        @Override
        public float getDamage() {
            return 1;
        }

        @Override
        public boolean canHurt() {
            return false;
        }

        @Override
        public void onHurt(float damage) {

        }

        @Override
        public void onCollide() {

        }

        @Override
        public float getBombResist() {
            return 1;
        }
    };/* {
		
	};*/

    float getDamage();/* {
		return 1;
	}*/

    boolean canHurt();/* {
		return false;
	}*/

    void onHurt(float damage);/*{
		return;
	}*/

    void onCollide();/*{
		return;
	}*/

    float getBombResist();/*{
		return 1f;
	}*/
}
