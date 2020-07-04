package com.meng.stg2.characters.player;

public class SubInfo {

	/*0,20,
	 0,32,
	 -16,20,16,20,
	 -32,0,32,0,
	 -16,20,0,30,16,20,
	 -32,0,0,32,32,0,
	 -16,20,-8,30,8,30,16,20,
	 -32,0,-16,32,16,32,32,0
	 */
	/*0,20,
	 0,32,
	 -16,-20,16,-20,
	 -32,0,32,0,
	 -16,-20,0,-30,16,-20,
	 -32,0,0,-32,32,0,
	 -16,-20,-8,-30,8,-30,16,-20,
	 -32,0,-16,-32,16,-32,32,0
	 */
	private static Info[][] infob = {
		{new Info(0, 20, 0, 32)},
		{new Info(-16, 20, -32, 0),new Info(16, 20, 32, 0)},
		{new Info(-16, 20, -32, 0),new Info(0, 30, 0, 32),new Info(16, 20, 32, 0)},
		{new Info(-16, 20, -32, 0),new Info(-8, 30, -16, 32),new Info(8, 30, 16, 32),new Info(16, 20, 32, 0)}
	};
	private static Info[][] infoa = {
		{new Info(0, 20, 0, 32)},
		{new Info(-16, -20, -32, 0),new Info(16, -20, 32, 0)},
		{new Info(-16, -20, -32, 0),new Info(0, -30, 0, -32),new Info(16, -20, 32, 0)},
		{new Info(-16, -20, -32, 0),new Info(-8, -30, -16, -32),new Info(8, -30, 16, -32),new Info(16, -20, 32, 0)}
	};

	public static Info geta(int p, int s) {
		return infoa[p][s];
	}

	public static Info[][] geta() {
		return infoa;
	}

	public static Info getb(int p, int s) {
		return infob[p][s];
	}

	public static Info[][] getb() {
		return infob;
	}

	public static class Info {
		public float normalX;
		public float normalY;
		public float focusX;
		public float focusY;
		public float normalAngle;
		public float focusAngle;
		public float normalRate;
		public float focusRate;
		public float normalDamage;
		public float focusDamage;

		public Info(float fX, float fY, float nX, float nY) {
			focusX = fX;
			focusY = fY;
			normalX = nX;
			normalY = nY;
		}
	}
}
