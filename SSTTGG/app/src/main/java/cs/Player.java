package cs;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import cs.*;

public class Player {
	public static Vector2 position = new Vector2(315f, 300f);
	private static float alpha = 1f;
	public static int time = 0;
	public static boolean Dis; //敌方单位碰撞
	public static void Clear() {
		position = new Vector2(315f, 300f);
		alpha = 1f;
		Dis = false;
		time = 0;
	}
	public static void Update() {
		if (!Time.Playing)
			return;
		if (Dis) {
			++time;
			if (time <= 40) {
				return;
			}
			Dis = false;
		} else {
			time = 0;
		}
	}
	public static void Draw(SpriteBatch s) {
		if (!(Time.Playing & !Dis))
			return;
		s.Draw(Main.player, new Vector2(position.x, position.y), new Rectangle(), new Color(1f, 1f, 1f, alpha), 0.0f, new Vector2(4.5f, 4.5f), 1f, SpriteEffects.None, 1f);
	}
}
