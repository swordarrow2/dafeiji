package cs.layer;

import cs.*;

public class BLExecution {
	public int parentid;
	public int id;
	public int change;
	public int changetype;
	public int changevalue;
	public String region;
	public float value;
	public int time;
	public int ctime;
	public boolean NeedDelete;
	public void Update(Barrage objects) {
		if(this.changetype==1) {
			switch(this.changevalue) {
				case 0:
					if(this.change==0) {
						objects.life=(int)this.value;
						break;
					}
					if(this.change==1) {
						objects.life+=(int)this.value;
						break;
					}
					if(this.change==2) {
						objects.life-=(int)this.value;
						break;
					}
					break;
				case 1:
					if(this.change==0) {
						objects.type=(int)this.value;
						break;
					}
					if(this.change==1) {
						objects.type+=(int)this.value;
						break;
					}
					if(this.change==2) {
						objects.type-=(int)this.value;
						break;
					}
					break;
				case 2:
					if(this.change==0) {
						objects.wscale=this.value;
						break;
					}
					if(this.change==1) {
						objects.wscale+=this.value;
						break;
					}
					if(this.change==2) {
						objects.wscale-=this.value;
						break;
					}
					break;
				case 3:
					if(this.change==0) {
						objects.longs=this.value;
						break;
					}
					if(this.change==1) {
						objects.longs+=this.value;
						break;
					}
					if(this.change==2) {
						objects.longs-=this.value;
						break;
					}
					break;
				case 4:
					if(this.change==0) {
						objects.alpha=this.value;
						break;
					}
					if(this.change==1) {
						objects.alpha+=this.value;
						break;
					}
					if(this.change==2) {
						objects.alpha-=this.value;
						break;
					}
					break;
				case 5:
					if(this.change==0)
						objects.speed=this.value;
					else if(this.change==1)
						objects.speed+=this.value;
					else if(this.change==2)
						objects.speed-=this.value;
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 6:
					if(this.change==0)
						objects.speedd=this.value;
					else if(this.change==1)
						objects.speedd+=this.value;
					else if(this.change==2)
						objects.speedd-=this.value;
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 7:
					if(this.change==0)
						objects.aspeed=this.value;
					else if(this.change==1)
						objects.aspeed+=this.value;
					else if(this.change==2)
						objects.aspeed-=this.value;
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 8:
					if(this.change==0)
						objects.aspeedd=this.value;
					else if(this.change==1)
						objects.aspeedd+=this.value;
					else if(this.change==2)
						objects.aspeedd-=this.value;
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 9:
					if(this.change==0) {
						objects.xscale=this.value;
						break;
					}
					if(this.change==1) {
						objects.xscale+=this.value;
						break;
					}
					if(this.change==2) {
						objects.xscale-=this.value;
						break;
					}
					break;
				case 10:
					if(this.change==0) {
						objects.yscale=this.value;
						break;
					}
					if(this.change==1) {
						objects.yscale+=this.value;
						break;
					}
					if(this.change==2) {
						objects.yscale-=this.value;
						break;
					}
					break;
				case 11:
					if((double)this.value>0.0)
						objects.Blend=true;
					if((double)this.value<=0.0) {
						objects.Blend=false;
						break;
					}
					break;
				case 12:
					if((double)this.value>0.0)
						objects.Outdispel=true;
					if((double)this.value<=0.0) {
						objects.Outdispel=false;
						break;
					}
					break;
				case 13:
					if((double)this.value>0.0)
						objects.Invincible=true;
					if((double)this.value<=0.0) {
						objects.Invincible=false;
						break;
					}
					break;
			}
		} else if(this.changetype==0) {
			switch(this.changevalue) {
				case 0:
					if(this.change==0) {
						objects.life=(int)(((double)objects.life*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
						break;
					}
					if(this.change==1) {
						objects.life+=(int)((double)this.value/(double)this.time);
						break;
					}
					if(this.change==2) {
						objects.life-=(int)((double)this.value/(double)this.time);
						break;
					}
					break;
				case 1:
					if(this.change==0) {
						objects.type=(int)(((double)objects.type*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
						break;
					}
					if(this.change==1) {
						objects.type+=(int)((double)this.value/(double)this.time);
						break;
					}
					if(this.change==2) {
						objects.type-=(int)((double)this.value/(double)this.time);
						break;
					}
					break;
				case 2:
					if(this.change==0) {
						objects.wscale=(objects.wscale*((float)this.ctime-1f)+this.value)/(float)this.ctime;
						break;
					}
					if(this.change==1) {
						objects.wscale+=this.value/(float)this.time;
						break;
					}
					if(this.change==2) {
						objects.wscale-=this.value/(float)this.time;
						break;
					}
					break;
				case 3:
					if(this.change==0) {
						objects.longs=(objects.longs*((float)this.ctime-1f)+this.value)/(float)this.ctime;
						break;
					}
					if(this.change==1) {
						objects.longs+=this.value/(float)this.time;
						break;
					}
					if(this.change==2) {
						objects.longs-=this.value/(float)this.time;
						break;
					}
					break;
				case 4:
					if(this.change==0) {
						objects.alpha=(objects.alpha*((float)this.ctime-1f)+this.value)/(float)this.ctime;
						break;
					}
					if(this.change==1) {
						objects.alpha+=this.value/(float)this.time;
						break;
					}
					if(this.change==2) {
						objects.alpha-=this.value/(float)this.time;
						break;
					}
					break;
				case 5:
					if(this.change==0)
						objects.speed=(objects.speed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
					else if(this.change==1)
						objects.speed+=this.value/(float)this.time;
					else if(this.change==2)
						objects.speed-=this.value/(float)this.time;
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 6:
					if(this.change==0)
						objects.speedd=(objects.speedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
					else if(this.change==1)
						objects.speedd+=this.value/(float)this.time;
					else if(this.change==2)
						objects.speedd-=this.value/(float)this.time;
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 7:
					if(this.change==0)
						objects.aspeed=(objects.aspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
					else if(this.change==1)
						objects.aspeed+=this.value/(float)this.time;
					else if(this.change==2)
						objects.aspeed-=this.value/(float)this.time;
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 8:
					if(this.change==0)
						objects.aspeedd=(objects.aspeedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
					else if(this.change==1)
						objects.aspeedd+=this.value/(float)this.time;
					else if(this.change==2)
						objects.aspeedd-=this.value/(float)this.time;
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 9:
					if(this.change==0) {
						objects.xscale=(objects.xscale*((float)this.ctime-1f)+this.value)/(float)this.ctime;
						break;
					}
					if(this.change==1) {
						objects.xscale+=this.value/(float)this.time;
						break;
					}
					if(this.change==2) {
						objects.xscale-=this.value/(float)this.time;
						break;
					}
					break;
				case 10:
					if(this.change==0) {
						objects.yscale=(objects.yscale*((float)this.ctime-1f)+this.value)/(float)this.ctime;
						break;
					}
					if(this.change==1) {
						objects.yscale+=this.value/(float)this.time;
						break;
					}
					if(this.change==2) {
						objects.yscale-=this.value/(float)this.time;
						break;
					}
					break;
				case 11:
					if((double)this.value>0.0)
						objects.Blend=true;
					if((double)this.value<=0.0) {
						objects.Blend=false;
						break;
					}
					break;
				case 12:
					if((double)this.value>0.0)
						objects.Outdispel=true;
					if((double)this.value<=0.0) {
						objects.Outdispel=false;
						break;
					}
					break;
				case 13:
					if((double)this.value>0.0)
						objects.Invincible=true;
					if((double)this.value<=0.0) {
						objects.Invincible=false;
						break;
					}
					break;
			}
		} else if(this.changetype==2) {
			switch(this.changevalue) {
				case 0:
					if(this.change==0) {
						objects.life=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					if(this.change==1) {
						objects.life=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					if(this.change==2) {
						objects.life=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					break;
				case 1:
					if(this.change==0) {
						objects.type=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					if(this.change==1) {
						objects.type=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					if(this.change==2) {
						objects.type=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
						break;
					}
					break;
				case 2:
					if(this.change==0) {
						objects.wscale=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==1) {
						objects.wscale=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==2) {
						objects.wscale=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					break;
				case 3:
					if(this.change==0) {
						objects.longs=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==1) {
						objects.longs=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==2) {
						objects.longs=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					break;
				case 4:
					if(this.change==0) {
						objects.alpha=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==1) {
						objects.alpha=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==2) {
						objects.alpha=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					break;
				case 5:
					if(this.change==0)
						objects.speed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==1)
						objects.speed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==2)
						objects.speed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 6:
					if(this.change==0)
						objects.speedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==1)
						objects.speedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==2)
						objects.speedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					objects.speedx=objects.xscale*objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
					objects.speedy=objects.yscale*objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
					break;
				case 7:
					if(this.change==0)
						objects.aspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==1)
						objects.aspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==2)
						objects.aspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 8:
					if(this.change==0)
						objects.aspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==1)
						objects.aspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					else if(this.change==2)
						objects.aspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
					objects.aspeedx=objects.xscale*objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
					objects.aspeedy=objects.yscale*objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
					break;
				case 9:
					if(this.change==0) {
						objects.xscale=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==1) {
						objects.xscale=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==2) {
						objects.xscale=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					break;
				case 10:
					if(this.change==0) {
						objects.yscale=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==1) {
						objects.yscale=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					if(this.change==2) {
						objects.yscale=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
						break;
					}
					break;
				case 11:
					if((double)this.value>0.0)
						objects.Blend=true;
					if((double)this.value<=0.0) {
						objects.Blend=false;
						break;
					}
					break;
				case 12:
					if((double)this.value>0.0)
						objects.Outdispel=true;
					if((double)this.value<=0.0) {
						objects.Outdispel=false;
						break;
					}
					break;
				case 13:
					if((double)this.value>0.0)
						objects.Invincible=true;
					if((double)this.value<=0.0) {
						objects.Invincible=false;
						break;
					}
					break;
			}
		}
		--this.ctime;
		if(this.changetype==2&this.ctime==-1) {
			this.NeedDelete=true;
		} else {
			if(!(this.changetype!=2&this.ctime==0))
				return;
			this.NeedDelete=true;
		}
	}
}
