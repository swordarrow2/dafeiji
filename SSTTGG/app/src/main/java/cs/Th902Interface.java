package cs;

import com.badlogic.gdx.math.*;
import cs.*;
import java.util.*;

public class Th902Interface {
        public static ArrayList<Barrage> bullets = new ArrayList<Barrage>();
        public static ArrayList<Barrage> getBulletInfo() {
            return bullets;
        }
        public static int getBulletCount() {
            return bullets.size();
        }
        public static void openDanmakuFile(String mbgPath) {
            Main.Open(mbgPath);
        }
        public static void update() {
            Main.updateAll();
        }
        public static Vector2 getPalyerPosition() {
            return Player.position;
        }
        public static void setPlayerX(float x) {
            Player.position.x=x;
        }
        public static void setPlayerY(float y) {
            Player.position.y=y;
        }
        public static void setPlayerPosition(float x,float y) {
            Player.position.x=x;
            Player.position.y=y;
        }
		}
