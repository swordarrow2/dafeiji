package cs.layer;

import com.badlogic.gdx.math.*;
import cs.*;
import java.util.*;

public class Rebound implements Cloneable {
	private int clcount;
	private int clwait;
	public boolean NeedDelete;
	public int Searched;
	public int id;
	public int parentid;
	public int parentcolor;
	public float x;
	public float y;
	public int begin;
	public int life;
	public int longs;
	public int time;
	public float angle;
	public float speed;
	public float speedd;
	public float speedx;
	public float speedy;
	public Vector2 speedds;
	public float aspeed;
	public float aspeedx;
	public float aspeedy;
	public float aspeedd;
	public Vector2 aspeedds;
	public Rebound rand;
	public ArrayList<Event> Parentevents;
	public Rebound copys;
	public Rebound() {
	}
	public Rebound(float xs, float ys, int pc) {
		this.rand = new Rebound();
		this.Parentevents = new ArrayList<Event>();
		this.x = xs;
		this.y = ys;
		this.parentcolor = pc;
		this.begin = Layer.LayerArray.get(this.parentid).begin;
		this.life = Layer.LayerArray.get(this.parentid).end - Layer.LayerArray.get(this.parentid).begin + 1;
		this.longs = 100;
		this.time = 1;
		this.angle = 0.0f;
	}
	public void Update() {
		if (this.clcount == 1) {
			++this.clwait;
			if (this.clwait > 15) {
				this.clwait = 0;
				this.clcount = 0;
			}
		}
		if (!Time.Playing) {
			this.aspeedx = this.aspeed * (float)Math.cos((double)MathHelper.ToRadians(this.aspeedd));
			this.aspeedy = this.aspeed * (float)Math.sin((double)MathHelper.ToRadians(this.aspeedd));
			this.speedx = this.speed * (float)Math.cos((double)MathHelper.ToRadians(this.speedd));
			this.speedy = this.speed * (float)Math.sin((double)MathHelper.ToRadians(this.speedd));
			this.begin = (int)MathHelper.Clamp((float)this.begin, (float)Layer.LayerArray.get(this.parentid).begin, (float)(1 + Layer.LayerArray.get(this.parentid).end - Layer.LayerArray.get(this.parentid).begin));
			this.life = (int)MathHelper.Clamp((float)this.life, 1f, (float)(Layer.LayerArray.get(this.parentid).end - Layer.LayerArray.get(this.parentid).begin + 2 - this.begin));
		}
		if (!Time.Playing || !(Time.now >= this.begin & Time.now <= this.begin + this.life - 1))
			return;
		int now = Time.now;
		this.speedx += this.aspeedx;
		this.speedy += this.aspeedy;
		this.x += this.speedx;
		this.y += this.speedy;
		float x2 = this.x - 4f;
		float y2 = this.y + 16f;
		float x3 = (float)((double)this.x - 4.0 + (double)this.longs * Math.cos((double)MathHelper.ToRadians(this.angle)));
		float y3 = (float)((double)this.y + 16.0 + (double)this.longs * Math.sin((double)MathHelper.ToRadians(this.angle)));
		Line line1 = new Line(new Vector2(x2, y2), new Vector2(x3, y3));
		for (Barrage barrage : Layer.LayerArray.get(this.parentid).Barrages) {
			if (barrage.Rebound && (barrage.time > 15 || !barrage.Mist) && !barrage.Dis) {
				float speedx = barrage.speedx;
				float speedy = barrage.speedy;
				float num1 = speedx + barrage.aspeedx;
				float num2 = speedy + barrage.aspeedy;
				float x4 = barrage.x;
				float y4 = barrage.y;
				float num3 = x4 + (num1 - this.speedx);
				float num4 = y4 + (num2 - this.speedy);
				float x5 = num3;
				float y5 = num4;
				float x6 = barrage.x;
				float y6 = barrage.y;
				Line line2 = new Line(new Vector2(x5, y5), new Vector2(x6, y6));
				if (((Main.CheckTwoLineCrose(line1, line2) ? 1 : 0) & (barrage.reboundtime < this.time ? 1 : (this.time == 0 ? 1 : 0))) != 0) {
					float num5 = (float)(((double)y3 - (double)y2) * ((double)x6 - (double)x5) - ((double)y6 - (double)y5) * ((double)x3 - (double)x2));
					float num6 = (float)(((double)x3 - (double)x2) * ((double)x6 - (double)x5) * ((double)y5 - (double)y2) + ((double)y3 - (double)y2) * ((double)x6 - (double)x5) * (double)x2 - ((double)y6 - (double)y5) * ((double)x3 - (double)x2) * (double)x5) / num5;
					float num7 = (float)((((double)y3 - (double)y2) * ((double)y6 - (double)y5) * ((double)x5 - (double)x2) + ((double)x3 - (double)x2) * ((double)y6 - (double)y5) * (double)y2 - ((double)x6 - (double)x5) * ((double)y3 - (double)y2) * (double)y5) / -(double)num5);
					barrage.speedd = 2f * this.angle - barrage.speedd;
					float num8 = (float)(((double)num6 - (double)x5) * ((double)num6 - (double)x5) + ((double)num7 - (double)y5) * ((double)num7 - (double)y5));
					barrage.x = num6 + barrage.xscale * (float)(Math.sqrt((double)num8) * Math.cos((double)MathHelper.ToRadians(barrage.speedd)));
					barrage.y = num7 + barrage.yscale * (float)(Math.sqrt((double)num8) * Math.sin((double)MathHelper.ToRadians(barrage.speedd)));
					barrage.speedx = barrage.xscale * barrage.speed * (float)Math.cos((double)MathHelper.ToRadians(barrage.speedd));
					barrage.speedy = barrage.yscale * barrage.speed * (float)Math.sin((double)MathHelper.ToRadians(barrage.speedd));
					++barrage.reboundtime;
					for (Event parentevent : this.Parentevents) {
						String str = "";
						String s = "";
						int num9 = 0;
						String tag = parentevent.tag;
						if (parentevent.tag.contains("变化到")) {
							str = tag.split("变化到)[0];
							s = tag.split("变化到)[3].split(" + ")[0];
							num9 = 1;
						}
						if (parentevent.tag.contains("增加")) {
							str = tag.split("增)[0];
							s = tag.split("增)[1].Replace("加","").split(" + ")[0];
							num9 = 2;
						}
						if (parentevent.tag.contains("减少")) {
							str = tag.split("减少)[0];
							s = tag.split("减少)[2].split(" + ")[0];
							num9 = 3;
						}
						if (parentevent.tag.contains("+")) {
							float num10 = Float.parseFloat(parentevent.tag.split("+")[1]);
							s = (Float.parseFloat(s) + MathHelper.Lerp(-num10, num10, (float)Main.rand.NextDouble())).ToString();
						}
						if (str.equals("生命"){
							switch (num9) {
								case 1:
									barrage.life = (int)Float.parseFloat(s);
									break;
								case 2:
									barrage.life += (int)Float.parseFloat(s);
									break;
								case 3:
									barrage.life -= (int)Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("类型"){
							switch (num9) {
								case 1:
									barrage.type = (int)Float.parseFloat(s);
									break;
								case 2:
									barrage.type += (int)Float.parseFloat(s);
									break;
								case 3:
									barrage.type -= (int)Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("宽比"){
							switch (num9) {
								case 1:
									barrage.wscale = Float.parseFloat(s);
									break;
								case 2:
									barrage.wscale += Float.parseFloat(s);
									break;
								case 3:
									barrage.wscale -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("高比"){
							switch (num9) {
								case 1:
									barrage.hscale = Float.parseFloat(s);
									break;
								case 2:
									barrage.hscale += Float.parseFloat(s);
									break;
								case 3:
									barrage.hscale -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("R"){
							switch (num9) {
								case 1:
									barrage.R = Float.parseFloat(s);
									break;
								case 2:
									barrage.R += Float.parseFloat(s);
									break;
								case 3:
									barrage.R -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("G"){
							switch (num9) {
								case 1:
									barrage.G = Float.parseFloat(s);
									break;
								case 2:
									barrage.G += Float.parseFloat(s);
									break;
								case 3:
									barrage.G -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("B"){
							switch (num9) {
								case 1:
									barrage.B = Float.parseFloat(s);
									break;
								case 2:
									barrage.B += Float.parseFloat(s);
									break;
								case 3:
									barrage.B -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("不透明度"){
							switch (num9) {
								case 1:
									barrage.alpha = Float.parseFloat(s);
									break;
								case 2:
									barrage.alpha += Float.parseFloat(s);
									break;
								case 3:
									barrage.alpha -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("朝向"){
							switch (num9) {
								case 1:
									barrage.head = Float.parseFloat(s);
									break;
								case 2:
									barrage.head += Float.parseFloat(s);
									break;
								case 3:
									barrage.head -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("子弹速度"){
							switch (num9) {
								case 1:
									barrage.speed = Float.parseFloat(s);
									break;
								case 2:
									barrage.speed += Float.parseFloat(s);
									break;
								case 3:
									barrage.speed -= Float.parseFloat(s);
									break;
							}
							barrage.speedx = barrage.xscale * barrage.speed * (float)Math.cos((double)MathHelper.ToRadians(barrage.speedd));
							barrage.speedy = barrage.yscale * barrage.speed * (float)Math.sin((double)MathHelper.ToRadians(barrage.speedd));
						}
						if (str.equals("子弹速度方向"){
							if (s.contains("自机")) {
								barrage.speedd = MathHelper.ToDegrees(Main.Twopointangle(Player.position.x, Player.position.y, barrage.x, barrage.y));
							} else {
								switch (num9) {
									case 1:
										barrage.speedd = Float.parseFloat(s);
										break;
									case 2:
										barrage.speedd += Float.parseFloat(s);
										break;
									case 3:
										barrage.speedd -= Float.parseFloat(s);
										break;
								}
							}
							barrage.speedx = barrage.xscale * barrage.speed * (float)Math.cos((double)MathHelper.ToRadians(barrage.speedd));
							barrage.speedy = barrage.yscale * barrage.speed * (float)Math.sin((double)MathHelper.ToRadians(barrage.speedd));
						}
						if (str.equals("子弹加速度"){
							switch (num9) {
								case 1:
									barrage.aspeed = Float.parseFloat(s);
									break;
								case 2:
									barrage.aspeed += Float.parseFloat(s);
									break;
								case 3:
									barrage.aspeed -= Float.parseFloat(s);
									break;
							}
							barrage.aspeedx = barrage.xscale * barrage.aspeed * (float)Math.cos((double)MathHelper.ToRadians(barrage.aspeedd));
							barrage.aspeedy = barrage.yscale * barrage.aspeed * (float)Math.sin((double)MathHelper.ToRadians(barrage.aspeedd));
						}
						if (str.equals("子弹加速度方向"){
							if (s.contains("自机")) {
								barrage.aspeedd = MathHelper.ToDegrees(Main.Twopointangle(Player.position.x, Player.position.y, barrage.x, barrage.y));
							} else {
								switch (num9) {
									case 1:
										barrage.aspeedd = Float.parseFloat(s);
										break;
									case 2:
										barrage.aspeedd += Float.parseFloat(s);
										break;
									case 3:
										barrage.aspeedd -= Float.parseFloat(s);
										break;
								}
							}
							barrage.aspeedx = barrage.xscale * barrage.aspeed * (float)Math.cos((double)MathHelper.ToRadians(barrage.aspeedd));
							barrage.aspeedy = barrage.yscale * barrage.aspeed * (float)Math.sin((double)MathHelper.ToRadians(barrage.aspeedd));
						}
						if (str.equals("横比"){
							switch (num9) {
								case 1:
									barrage.xscale = Float.parseFloat(s);
									break;
								case 2:
									barrage.xscale += Float.parseFloat(s);
									break;
								case 3:
									barrage.xscale -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("纵比"){
							switch (num9) {
								case 1:
									barrage.yscale = Float.parseFloat(s);
									break;
								case 2:
									barrage.yscale += Float.parseFloat(s);
									break;
								case 3:
									barrage.yscale -= Float.parseFloat(s);
									break;
							}
						}
						if (str.equals("雾化效果")
							barrage.Mist = (int)Float.parseFloat(s) > 0;
						if (str.equals("消除效果")
							barrage.Dispel = (int)Float.parseFloat(s) > 0;
						if (str.equals("高光效果")
							barrage.Blend = (int)Float.parseFloat(s) > 0;
						if (str.equals("拖影效果")
							barrage.Afterimage = (int)Float.parseFloat(s) > 0;
						if (str.equals("出屏即消")
							barrage.Outdispel = (int)Float.parseFloat(s) > 0;
						if (str.equals("无敌状态")
							barrage.Invincible = (int)Float.parseFloat(s) > 0;
					}
				}
			}
		}
	}
	public object Clone() {
		MemoryStream memoryStream = new MemoryStream();
		BinaryFormatter binaryFormatter = new BinaryFormatter();
		binaryFormatter.Serialize((Stream)memoryStream, (object)this);
		memoryStream.Seek(0L, SeekOrigin.Begin);
		object obj = binaryFormatter.Deserialize((Stream)memoryStream);
		memoryStream.Close();
		return obj;
	}
	public object Copy() {
		return this.MemberwiseClone();
	}
	public void PreviewUpdate() {
		if (!(Time.now >= this.begin & Time.now <= this.begin + this.life - 1))
			return;
		int now = Time.now;
		this.speedx += this.aspeedx;
		this.speedy += this.aspeedy;
		this.x += this.speedx;
		this.y += this.speedy;
	}
}
