package cs.layer;

public class CExecution {
        public int change;
        public int changetype;
        public int changevalue;
        public String region;
        public float value;
        public float value2;
        public int time;
        public int ctime;
        public boolean NeedDelete;
        public void Update() {
            if(this.changetype==0) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0)
                            Center.ospeed=(Center.ospeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            Center.ospeed+=this.value/(float)this.time;
                        else if(this.change==2)
                            Center.ospeed-=this.value/(float)this.time;
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 1:
                        if(this.change==0)
                            Center.ospeedd=(Center.ospeedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            Center.ospeedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            Center.ospeedd-=this.value/(float)this.time;
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 2:
                        if(this.change==0)
                            Center.oaspeed=(Center.oaspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            Center.oaspeed+=this.value/(float)this.time;
                        else if(this.change==2)
                            Center.oaspeed-=this.value/(float)this.time;
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                    case 3:
                        if(this.change==0)
                            Center.oaspeedd=(Center.oaspeedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            Center.oaspeedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            Center.oaspeedd-=this.value/(float)this.time;
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                }
            } else if(this.changetype==1) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0)
                            Center.ospeed=this.value;
                        else if(this.change==1)
                            Center.ospeed+=this.value;
                        else if(this.change==2)
                            Center.ospeed-=this.value;
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 1:
                        if(this.change==0)
                            Center.ospeedd=this.value;
                        else if(this.change==1)
                            Center.ospeedd+=this.value;
                        else if(this.change==2)
                            Center.ospeedd-=this.value;
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 2:
                        if(this.change==0)
                            Center.oaspeed=this.value;
                        else if(this.change==1)
                            Center.oaspeed+=this.value;
                        else if(this.change==2)
                            Center.oaspeed-=this.value;
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                    case 3:
                        if(this.change==0)
                            Center.oaspeedd=this.value;
                        else if(this.change==1)
                            Center.ospeedd+=this.value;
                        else if(this.change==2)
                            Center.ospeedd-=this.value;
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                }
            } else if(this.changetype==2) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0)
                            Center.ospeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            Center.ospeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            Center.ospeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 1:
                        if(this.change==0)
                            Center.ospeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            Center.ospeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            Center.ospeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        Center.speedx=Center.ospeed*(float)Math.cos((double)MathHelper.ToRadians(Center.ospeedd));
                        Center.speedy=Center.ospeed*(float)Math.sin((double)MathHelper.ToRadians(Center.ospeedd));
                        break;
                    case 2:
                        if(this.change==0)
                            Center.oaspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            Center.oaspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            Center.oaspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                    case 3:
                        if(this.change==0)
                            Center.oaspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            Center.oaspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            Center.oaspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        Center.aspeedx=Center.oaspeed*(float)Math.cos((double)MathHelper.ToRadians(Center.oaspeedd));
                        Center.aspeedy=Center.oaspeed*(float)Math.sin((double)MathHelper.ToRadians(Center.oaspeedd));
                        break;
                }
            } else if(this.changetype==3)
                Center.ox=(float)(((double)Center.ox*19.0+(double)Player.position.x)/20.0);
            else if(this.changetype==4) {
                Center.ox=(float)(((double)Center.ox*19.0+(double)this.value)/20.0);
                Center.oy=(float)(((double)Center.oy*19.0+(double)this.value2)/20.0);
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
