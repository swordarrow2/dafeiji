package cs.layer;

import com.badlogic.gdx.math.*;
import cs.*;
import java.util.*;

public class Lase {
	public int bindid = -1;
	private float[] conditions = new float[10];
	private float[] results = new float[24];
	private int clcount;
	private int clwait;
	public int Searched;
	public boolean NeedDelete;
	public int id;
	public int parentid;
	public int parentcolor;
	public boolean Binding;
	public boolean Bindwithspeedd;
	public boolean Deepbind;
	public boolean Deepbinded;
	public float x;
	public float y;
	public int time;
	public int begin;
	public int life;
	public float r;
	public float rdirection;
	public Vector2 rdirections;
	public int tiao;
	public int t;
	public float fdirection;
	public Vector2 fdirections;
	public int range;
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
	public int sonlife;
	public float type;
	public float longs;
	public float wscale;
	public float alpha;
	public boolean Ray;
	public float sonspeed;
	public float sonspeedd;
	public Vector2 sonspeedds;
	public float sonaspeed;
	public float sonaspeedd;
	public Vector2 sonaspeedds;
	public float xscale;
	public float yscale;
	public boolean Blend;
	public boolean Outdispel;
	public boolean Invincible;
	public Lase rand;
	public ArrayList<Event> Parentevents;
	public ArrayList<LExecution> Eventsexe;
	public ArrayList<Event> Sonevents;
	public Lase copys;
	public Lase() {
	}
	public Lase(float xs,float ys,int pc) {
		this.rand=new Lase();
		this.Parentevents=new ArrayList<Event>();
		this.Sonevents=new ArrayList<Event>();
		this.Eventsexe=new ArrayList<LExecution>();
		this.x=xs;
		this.y=ys;
		this.parentcolor=pc;
		this.begin=Layer.LayerArray.get(this.parentid).begin;
		this.life=Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin+1;
		this.r=0.0f;
		this.rdirection=0.0f;
		this.tiao=1;
		this.t=5;
		this.fdirection=0.0f;
		this.range=360;
		this.speed=0.0f;
		this.speedd=0.0f;
		this.aspeed=0.0f;
		this.aspeedd=0.0f;
		this.sonlife=200;
		this.type=0.0f;
		this.longs=100f;
		this.wscale=1f;
		this.alpha=100f;
		this.Ray=false;
		this.sonspeed=5f;
		this.sonspeedd=0.0f;
		this.sonaspeed=0.0f;
		this.sonaspeedd=0.0f;
		this.xscale=1f;
		this.yscale=1f;
		this.Blend=false;
		this.Outdispel=true;
		this.Invincible=false;
	}
	public void Update() {
		if(this.clcount==1) {
			++this.clwait;
			if(this.clwait>15) {
				this.clwait=0;
				this.clcount=0;
			}
		}
		if(!Time.Playing) {
			this.aspeedx=this.aspeed*(float)Math.cos((double)MathHelper.ToRadians(this.aspeedd));
			this.aspeedy=this.aspeed*(float)Math.sin((double)MathHelper.ToRadians(this.aspeedd));
			this.speedx=this.speed*(float)Math.cos((double)MathHelper.ToRadians(this.speedd));
			this.speedy=this.speed*(float)Math.sin((double)MathHelper.ToRadians(this.speedd));
			this.begin=(int)MathHelper.Clamp((float)this.begin,(float)Layer.LayerArray.get(this.parentid).begin,(float)(1+Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin));
			this.life=(int)MathHelper.Clamp((float)this.life,1f,(float)(Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin+2-this.begin));
		}
		if((double)this.type-1.0>=(double)Main.bgset.size())
			this.type=0.0f;
		if(this.bindid!=-1&&this.bindid>=Layer.LayerArray.get(this.parentid).BatchArray.size()) {
			this.bindid=-1;
			this.Deepbind=false;
			this.Bindwithspeedd=false;
		}
		if(!(Time.Playing&type!=-1.0))
			return;
		if(this.Deepbinded)
			++this.time;
		if(!(!this.Deepbinded&Time.now>=this.begin&Time.now<=this.begin+this.life-1)&&!(this.Deepbinded&this.time>=this.begin&this.time<=this.begin+this.life-1)&&!this.Deepbind)
			return;
		if(!this.Deepbind&!this.Deepbinded)
			this.time=Time.now-this.begin+1;
		if(!this.Deepbind) {
			this.speedx+=this.aspeedx;
			this.speedy+=this.aspeedy;
			this.x+=this.speedx;
			this.y+=this.speedy;
			this.conditions[0]=!this.Deepbinded ? (float)this.time : (float)(this.time-this.begin+1);
			this.conditions[1]=this.r;
			this.conditions[2]=this.rdirection;
			this.conditions[3]=(float)this.tiao;
			this.conditions[4]=(float)this.t;
			this.conditions[5]=this.fdirection;
			this.conditions[6]=(float)this.range;
			this.conditions[7]=this.wscale;
			this.conditions[8]=this.longs;
			this.conditions[9]=this.alpha;
			this.results[0]=this.r;
			this.results[1]=this.rdirection;
			this.results[2]=(float)this.tiao;
			this.results[3]=(float)this.t;
			this.results[4]=this.fdirection;
			this.results[5]=(float)this.range;
			this.results[6]=this.speed;
			this.results[7]=this.speedd;
			this.results[8]=this.aspeed;
			this.results[9]=this.aspeedd;
			this.results[10]=(float)this.life;
			this.results[11]=this.type;
			this.results[12]=this.wscale;
			this.results[13]=this.longs;
			this.results[14]=this.alpha;
			this.results[15]=this.sonspeed;
			this.results[16]=this.sonspeedd;
			this.results[17]=this.sonaspeed;
			this.results[18]=this.sonaspeedd;
			this.results[19]=this.xscale;
			this.results[20]=this.yscale;
			this.results[21]=0.0f;
			this.results[22]=0.0f;
			this.results[23]=0.0f;
			for(Event parentevent : this.Parentevents) {
				if(parentevent.t<=0)
					parentevent.t=1;
				if(this.time%parentevent.t==0)
					++parentevent.loop;
				for(EventRead result : parentevent.results) {
					if(result.special==4) {
						if(result.changevalue==1)
							result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f+Center.ox-Center.x,this.y+16f+Center.oy-Center.y));
						if(result.changevalue==4)
							result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f+Center.ox-Center.x,this.y+16f+Center.oy-Center.y));
						if(result.changevalue==18)
							result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f+Center.ox-Center.x,this.y+16f+Center.oy-Center.y));
					}
					if(result.opreator.equals(">")) {
						if(result.opreator2.equals(">")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].toString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if(result.opreator2.equals("=")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if(result.opreator2.equals("<")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
							LExecution lexecution = new LExecution();
							if(!result.noloop) {
								if(result.time>0) {
									--result.time;
									if(result.time==0)
										result.noloop=true;
								}
								lexecution.parentid=this.parentid;
								lexecution.id=this.id;
								lexecution.change=result.change;
								lexecution.changetype=result.changetype;
								lexecution.changevalue=result.changevalue;
								lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
								lexecution.region=this.results[result.changename].ToString();
								lexecution.time=result.times;
								lexecution.ctime=lexecution.time;
								this.Eventsexe.add(lexecution);
							} else
								continue;
						}
					}
					if(result.opreator.equals("=")) {
						if(result.opreator2.equals(">")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if(result.opreator2.equals("=")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if(result.opreator2.equals("<")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									} else
										continue;
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								} else
									continue;
							}
						} else if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
							LExecution lexecution = new LExecution();
							if(!result.noloop) {
								if(result.time>0) {
									--result.time;
									if(result.time==0)
										result.noloop=true;
								}
								lexecution.parentid=this.parentid;
								lexecution.id=this.id;
								lexecution.change=result.change;
								lexecution.changetype=result.changetype;
								lexecution.changevalue=result.changevalue;
								lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
								lexecution.region=this.results[result.changename].ToString();
								lexecution.time=result.times;
								lexecution.ctime=lexecution.time;
								this.Eventsexe.add(lexecution);
							} else
								continue;
						}
					}
					if(result.opreator.equals("<")) {
						if(result.opreator2.equals(">")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									}
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								}
							}
						} else if(result.opreator2.equals("=")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									}
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								}
							}
						} else if(result.opreator2.equals("<")) {
							if(result.collector.equals("且")) {
								if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
									LExecution lexecution = new LExecution();
									if(!result.noloop) {
										if(result.time>0) {
											--result.time;
											if(result.time==0)
												result.noloop=true;
										}
										lexecution.parentid=this.parentid;
										lexecution.id=this.id;
										lexecution.change=result.change;
										lexecution.changetype=result.changetype;
										lexecution.changevalue=result.changevalue;
										lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
										lexecution.region=this.results[result.changename].ToString();
										lexecution.time=result.times;
										lexecution.ctime=lexecution.time;
										this.Eventsexe.add(lexecution);
									}
								}
							} else if(result.collector.equals("或")&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
								LExecution lexecution = new LExecution();
								if(!result.noloop) {
									if(result.time>0) {
										--result.time;
										if(result.time==0)
											result.noloop=true;
									}
									lexecution.parentid=this.parentid;
									lexecution.id=this.id;
									lexecution.change=result.change;
									lexecution.changetype=result.changetype;
									lexecution.changevalue=result.changevalue;
									lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
									lexecution.region=this.results[result.changename].ToString();
									lexecution.time=result.times;
									lexecution.ctime=lexecution.time;
									this.Eventsexe.add(lexecution);
								}
							}
						} else if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
							LExecution lexecution = new LExecution();
							if(!result.noloop) {
								if(result.time>0) {
									--result.time;
									if(result.time==0)
										result.noloop=true;
								}
								lexecution.parentid=this.parentid;
								lexecution.id=this.id;
								lexecution.change=result.change;
								lexecution.changetype=result.changetype;
								lexecution.changevalue=result.changevalue;
								lexecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
								lexecution.region=this.results[result.changename].ToString();
								lexecution.time=result.times;
								lexecution.ctime=lexecution.time;
								this.Eventsexe.add(lexecution);
							}
						}
					}
				}
			}
			for(int index = 0;index<this.Eventsexe.size();++index) {
				if(!this.Eventsexe[index].NeedDelete) {
					this.Eventsexe[index].Update(this);
				} else {
					this.Eventsexe.remove(index);
					--index;
				}
			}
		}
		if(this.t<=0)
			return;
		if(this.Deepbind) {
			this.Shoot();
		} else {
			if(this.time%this.t+(int)MathHelper.Lerp((float)-this.rand.t,(float)this.rand.t,(float)Main.rand.NextDouble())!=0)
				return;
			this.Shoot();
		}
	}
	private void Shoot() {
		int num1 = this.tiao+(int)MathHelper.Lerp((float)-this.rand.tiao,(float)this.rand.tiao,(float)Main.rand.NextDouble());
		int num2 = (int)MathHelper.Lerp(-this.rand.r,this.rand.r,(float)Main.rand.NextDouble());
		float num3 = MathHelper.Lerp(-this.rand.rdirection,this.rand.rdirection,(float)Main.rand.NextDouble());
		int num4 = (int)MathHelper.Lerp((float)-this.rand.range,(float)this.rand.range,(float)Main.rand.NextDouble());
		float num5 = MathHelper.Lerp(-this.rand.sonspeed,this.rand.sonspeed,(float)Main.rand.NextDouble());
		float num6 = MathHelper.Lerp(-this.rand.fdirection,this.rand.fdirection,(float)Main.rand.NextDouble());
		float num7 = MathHelper.Lerp(-this.rand.sonaspeed,this.rand.sonaspeed,(float)Main.rand.NextDouble());
		float num8 = MathHelper.Lerp(-this.rand.sonaspeedd,this.rand.sonaspeedd,(float)Main.rand.NextDouble());
		if(this.bindid==-1) {
			for(int index1 = 0;index1<num1;++index1) {
				Barrage barrage = new Barrage();
				barrage.IsLase=true;
				if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).rdirection==-99999.0)
					this.rdirection=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f,this.y+16f));
				float degrees = this.rdirection+((float)index1-(float)(((double)num1-1.0)/2.0))*(float)(this.range+num4)/(float)num1+num3;
				barrage.x=(float)((double)this.x-4.0+((double)this.r+(double)num2)*Math.cos((double)MathHelper.ToRadians(degrees)))+Center.ox-Center.x;
				barrage.y=(float)((double)this.y+16.0+((double)this.r+(double)num2)*Math.sin((double)MathHelper.ToRadians(degrees)))+Center.oy-Center.y;
				barrage.life=this.sonlife;
				barrage.type=(int)this.type;
				barrage.wscale=this.wscale;
				barrage.longs=this.longs;
				barrage.alpha=this.alpha;
				barrage.speed=this.sonspeed+num5;
				if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).fdirection==-99999.0)
					this.fdirection=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,barrage.x,barrage.y));
				else if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).fdirection==-100000.0)
					this.fdirection=MathHelper.ToDegrees(Main.Twopointangle(this.fdirections.X,this.fdirections.Y,barrage.x,barrage.y));
				barrage.speedd=(float)((double)this.fdirection+(double)num6+(double)((float)index1-(float)(((double)num1-1.0)/2.0))*(double)(this.range+num4)/(double)num1);
				barrage.aspeed=this.sonaspeed+num7;
				if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).sonaspeedd==-99999.0)
					Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).copys.sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,barrage.x,barrage.y));
				else if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).sonaspeedd==-100000.0)
					Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).copys.sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(this.sonaspeedds.X,this.sonaspeedds.Y,barrage.x,barrage.y));
				barrage.aspeedd=this.sonaspeedd+num8;
				barrage.speedx=this.xscale*barrage.speed*(float)Math.cos((double)MathHelper.ToRadians(barrage.speedd));
				barrage.speedy=this.yscale*barrage.speed*(float)Math.sin((double)MathHelper.ToRadians(barrage.speedd));
				barrage.aspeedx=this.xscale*barrage.aspeed*(float)Math.cos((double)MathHelper.ToRadians(barrage.aspeedd));
				barrage.aspeedy=this.yscale*barrage.aspeed*(float)Math.sin((double)MathHelper.ToRadians(barrage.aspeedd));
				barrage.IsRay=this.Ray;
				barrage.xscale=this.xscale;
				barrage.yscale=this.yscale;
				barrage.Blend=this.Blend;
				barrage.Outdispel=this.Outdispel;
				barrage.Invincible=this.Invincible;
				for(int idx = 0;idx<this.Sonevents.size();++idx) {
					Event _event = new Event(idx);
					_event.t=this.Sonevents.get(idx).t;
					_event.addtime=this.Sonevents.get(idx).addtime;
					_event.events=this.Sonevents.get(idx).events;
					for(int index2 = 0;index2<this.Sonevents.get(idx).results.size();++index2)
						_event.results.put((EventRead)this.Sonevents.get(idx).results[index2].Copy());
					_event.index=this.Sonevents.get(idx).index;
					barrage.Events.put(_event);
				}
				barrage.parentid=this.id;
				Layer.LayerArray.get(this.parentid).Barrages.put(barrage);
			}
		} else {
			for(int index1 = 0;index1<Layer.LayerArray.get(this.parentid).Barrages.size();++index1) {
				if(((!Layer.LayerArray.get(this.parentid).Barrages.get(index1).IsLase&Layer.LayerArray.get(this.parentid).Barrages.get(index1).parentid==this.bindid ? 1 : 0)&(Layer.LayerArray.get(this.parentid).Barrages.get(index1).time>15 ? 1 : (!Layer.LayerArray.get(this.parentid).Barrages.get(index1).Mist ? 1 : 0))&(!Layer.LayerArray.get(this.parentid).Barrages.get(index1).NeedDelete ? 1 : 0))!=0) {
					if(this.Deepbind) {
						if(Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase!=null) {
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.x=Layer.LayerArray.get(this.parentid).Barrages.get(index1).x;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.y=Layer.LayerArray.get(this.parentid).Barrages.get(index1).y;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.Update();
						} else {
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase=this.BindClone();
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.Deepbind=false;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.Deepbinded=true;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.bindid=-1;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.time=0;
							if(this.Bindwithspeedd)
								Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.fdirection+=Layer.LayerArray.get(this.parentid).Barrages.get(index1).fdirection;
							Layer.LayerArray.get(this.parentid).Barrages.get(index1).lase.Bindwithspeedd=false;
						}
					} else {
						for(int index2 = 0;index2<num1;++index2) {
							Barrage barrage = new Barrage();
							barrage.IsLase=true;
							if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).rdirection==-99999.0)
								this.rdirection=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,Layer.LayerArray.get(this.parentid).Barrages.get(index1).x,Layer.LayerArray.get(this.parentid).Barrages.get(index1).y));
							float degrees = this.rdirection+((float)index2-(float)(((double)num1-1.0)/2.0))*(float)(this.range+num4)/(float)num1+num3;
							barrage.x=Layer.LayerArray.get(this.parentid).Barrages.get(index1).x+(this.r+(float)num2)*(float)Math.cos((double)MathHelper.ToRadians(degrees));
							barrage.y=Layer.LayerArray.get(this.parentid).Barrages.get(index1).y+(this.r+(float)num2)*(float)Math.sin((double)MathHelper.ToRadians(degrees));
							barrage.life=this.sonlife;
							barrage.type=(int)this.type;
							barrage.wscale=this.wscale;
							barrage.longs=this.longs;
							barrage.alpha=this.alpha;
							barrage.speed=this.sonspeed+num5;
							if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).fdirection==-99999.0)
								this.fdirection=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,barrage.x,barrage.y));
							else if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).fdirection==-100000.0)
								this.fdirection=MathHelper.ToDegrees(Main.Twopointangle(this.fdirections.x,this.fdirections.y,barrage.x,barrage.y));
							barrage.speedd=!this.Bindwithspeedd ? (float)((double)this.fdirection+(double)num6+(double)((float)index2-(float)(((double)num1-1.0)/2.0))*(double)(this.range+num4)/(double)num1) : (float)((double)this.fdirection+(double)num6+(double)((float)index2-(float)(((double)num1-1.0)/2.0))*(double)(this.range+num4)/(double)num1)+Layer.LayerArray.get(this.parentid).Barrages.get(index1).speedd;
							barrage.aspeed=this.sonaspeed+num7;
							if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).sonaspeedd==-99999.0)
								this.sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,barrage.x,barrage.y));
							else if((double)Layer.LayerArray.get(this.parentid).LaseArray.get(this.id).sonaspeedd==-100000.0)
								this.sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(this.sonaspeedds.x,this.sonaspeedds.y,barrage.x,barrage.y));
							barrage.aspeedd=this.sonaspeedd+num8;
							barrage.speedx=this.xscale*barrage.speed*(float)Math.cos((double)MathHelper.ToRadians(barrage.speedd));
							barrage.speedy=this.yscale*barrage.speed*(float)Math.sin((double)MathHelper.ToRadians(barrage.speedd));
							barrage.aspeedx=this.xscale*barrage.aspeed*(float)Math.cos((double)MathHelper.ToRadians(barrage.aspeedd));
							barrage.aspeedy=this.yscale*barrage.aspeed*(float)Math.sin((double)MathHelper.ToRadians(barrage.aspeedd));
							barrage.IsRay=this.Ray;
							barrage.xscale=this.xscale;
							barrage.yscale=this.yscale;
							barrage.Blend=this.Blend;
							barrage.Outdispel=this.Outdispel;
							barrage.Invincible=this.Invincible;
							for(int idx = 0;idx<this.Sonevents.size();++idx) {
								Event _event = new Event(idx);
								_event.t=this.Sonevents.get(idx).t;
								_event.addtime=this.Sonevents.get(idx).addtime;
								_event.events=this.Sonevents.get(idx).events;
								for(int index3 = 0;index3<this.Sonevents.get(idx).results.size();++index3)
									_event.results.add((EventRead)this.Sonevents.get(idx).results.get(index3).Copy());
								_event.index=this.Sonevents.get(idx).index;
								barrage.Events.put(_event);
							}
							barrage.parentid=this.id;
							Layer.LayerArray.get(this.parentid).Barrages.put(barrage);
						}
					}
				}
			}
		}
	}
	public Lase BindClone() {
		Lase lase = this.Copy() as Lase;
		lase.Parentevents=new ArrayList<Event>();
		for(Event parentevent : this.Parentevents)
			lase.Parentevents.put((Event)parentevent.Clone());
		lase.Eventsexe=new ArrayList<LExecution>();
		for(LExecution lexecution : this.Eventsexe)
			lase.Eventsexe.add((LExecution)lexecution.Clone());
		lase.Sonevents=new ArrayList<Event>();
		for(Event sonevent : this.Sonevents)
			lase.Sonevents.put((Event)sonevent.Clone());
		return lase;
	}
	public object Clone() {
		MemoryStream memoryStream = new MemoryStream();
		BinaryFormatter binaryFormatter = new BinaryFormatter();
		binaryFormatter.Serialize((Stream)memoryStream,(object)this);
		memoryStream.Seek(0L,SeekOrigin.Begin);
		object obj = binaryFormatter.Deserialize((Stream)memoryStream);
		memoryStream.Close();
		return obj;
	}
	public object Copy() {
		return this.MemberwiseClone();
	}
	public void PreviewUpdate() {
		if(!(Time.now>=this.begin&Time.now<=this.begin+this.life-1))
			return;
		int now = Time.now;
		this.speedx+=this.aspeedx;
		this.speedy+=this.aspeedy;
		this.x+=this.speedx;
		this.y+=this.speedy;
	}
}
