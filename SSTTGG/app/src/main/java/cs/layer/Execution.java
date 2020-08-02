package cs.layer;

    public class Execution {
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
        public void Update(Batch objects) {
            if(this.changetype==0) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0) {
                            objects.fx=(objects.fx*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.fx+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.fx-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.fy=(objects.fy*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.fy+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.fy-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 2:
                        if(this.change==0) {
                            objects.r=(objects.r*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.r+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.r-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0) {
                            objects.rdirection=(objects.rdirection*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.rdirection+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.rdirection-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 4:
                        if(this.change==0) {
                            objects.tiao=(int)(((double)objects.tiao*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.tiao+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.tiao-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 5:
                        if(this.change==0) {
                            objects.t=(int)(((double)objects.t*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.t+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.t-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 6:
                        if(this.change==0) {
                            objects.fdirection=(objects.fdirection*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.fdirection+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.fdirection-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 7:
                        if(this.change==0) {
                            objects.range=(int)(((double)objects.range*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.range+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.range-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 8:
                        if(this.change==0)
                            objects.speed=(objects.speed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.speed+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.speed-=this.value/(float)this.time;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 9:
                        if(this.change==0)
                            objects.speedd=(objects.speedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.speedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.speedd-=this.value/(float)this.time;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 10:
                        if(this.change==0)
                            objects.aspeed=(objects.aspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.aspeed+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.aspeed-=this.value/(float)this.time;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 11:
                        if(this.change==0)
                            objects.aspeedd=(float)(int)(((double)objects.aspeedd*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                        else if(this.change==1)
                            objects.aspeedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.aspeedd-=this.value/(float)this.time;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 12:
                        if(this.change==0) {
                            objects.sonlife=(int)(((double)objects.sonlife*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.sonlife+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.sonlife-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 13:
                        if(this.change==0) {
                            objects.type=(objects.type*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.type+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.type-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 14:
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
                    case 15:
                        if(this.change==0) {
                            objects.hscale=(objects.hscale*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.hscale+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.hscale-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 16:
                        if(this.change==0) {
                            objects.colorR=(float)(int)(((double)objects.colorR*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.colorR+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorR-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 17:
                        if(this.change==0) {
                            objects.colorG=(float)(int)(((double)objects.colorG*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.colorG+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorG-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 18:
                        if(this.change==0) {
                            objects.colorB=(float)(int)(((double)objects.colorB*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.colorB+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorB-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 19:
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
                    case 20:
                        if(this.change==0) {
                            objects.head=(objects.head*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.head+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.head-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 21:
                        if(this.change==0) {
                            objects.sonspeed=(objects.sonspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeed+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeed-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 22:
                        if(this.change==0) {
                            objects.sonspeedd=(float)(int)(((double)objects.sonspeedd*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeedd+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeedd-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 23:
                        if(this.change==0) {
                            objects.sonaspeed=(objects.sonaspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeed+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeed-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 24:
                        if(this.change==0) {
                            objects.sonaspeedd=(float)(int)(((double)objects.sonaspeedd*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeedd+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeedd-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 25:
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
                    case 26:
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
                    case 27:
                        if((double)this.value>0.0)
                            objects.Mist=true;
                        if((double)this.value<=0.0) {
                            objects.Mist=false;
                            break;
                        }
                        break;
                    case 28:
                        if((double)this.value>0.0)
                            objects.Dispel=true;
                        if((double)this.value<=0.0) {
                            objects.Dispel=false;
                            break;
                        }
                        break;
                    case 29:
                        if((double)this.value>0.0)
                            objects.Blend=true;
                        if((double)this.value<=0.0) {
                            objects.Blend=false;
                            break;
                        }
                        break;
                    case 30:
                        if((double)this.value>0.0)
                            objects.Afterimage=true;
                        if((double)this.value<=0.0) {
                            objects.Afterimage=false;
                            break;
                        }
                        break;
                    case 31:
                        if((double)this.value>0.0)
                            objects.Outdispel=true;
                        if((double)this.value<=0.0) {
                            objects.Outdispel=false;
                            break;
                        }
                        break;
                    case 32:
                        if((double)this.value>0.0)
                            objects.Invincible=true;
                        if((double)this.value<=0.0) {
                            objects.Invincible=false;
                            break;
                        }
                        break;
                }
            } else if(this.changetype==1) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0) {
                            objects.fx=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.fx+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.fx-=this.value;
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.fy=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.fy+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.fy-=this.value;
                            break;
                        }
                        break;
                    case 2:
                        if(this.change==0) {
                            objects.r=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.r+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.r-=this.value;
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0) {
                            objects.rdirection=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.rdirection+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.rdirection-=this.value;
                            break;
                        }
                        break;
                    case 4:
                        if(this.change==0) {
                            objects.tiao=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.tiao+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.tiao-=(int)this.value;
                            break;
                        }
                        break;
                    case 5:
                        if(this.change==0) {
                            objects.t=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.t+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.t-=(int)this.value;
                            break;
                        }
                        break;
                    case 6:
                        if(this.change==0) {
                            objects.fdirection=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.fdirection+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.fdirection-=this.value;
                            break;
                        }
                        break;
                    case 7:
                        if(this.change==0) {
                            objects.range=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.range+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.range-=(int)this.value;
                            break;
                        }
                        break;
                    case 8:
                        if(this.change==0)
                            objects.speed=this.value;
                        else if(this.change==1)
                            objects.speed+=this.value;
                        else if(this.change==2)
                            objects.speed-=this.value;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 9:
                        if(this.change==0)
                            objects.speedd=this.value;
                        else if(this.change==1)
                            objects.speedd+=this.value;
                        else if(this.change==2)
                            objects.speedd-=this.value;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 10:
                        if(this.change==0)
                            objects.aspeed=this.value;
                        else if(this.change==1)
                            objects.aspeed+=this.value;
                        else if(this.change==2)
                            objects.aspeed-=this.value;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 11:
                        if(this.change==0)
                            objects.aspeedd=this.value;
                        else if(this.change==1)
                            objects.aspeedd+=this.value;
                        else if(this.change==2)
                            objects.aspeedd-=this.value;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 12:
                        if(this.change==0) {
                            objects.sonlife=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonlife+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonlife-=(int)this.value;
                            break;
                        }
                        break;
                    case 13:
                        if(this.change==0) {
                            objects.type=(float)(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.type+=(float)(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.type-=(float)(int)this.value;
                            break;
                        }
                        break;
                    case 14:
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
                    case 15:
                        if(this.change==0) {
                            objects.hscale=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.hscale+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.hscale-=this.value;
                            break;
                        }
                        break;
                    case 16:
                        if(this.change==0) {
                            objects.colorR=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.colorR+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorR-=this.value;
                            break;
                        }
                        break;
                    case 17:
                        if(this.change==0) {
                            objects.colorG=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.colorG+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorG-=this.value;
                            break;
                        }
                        break;
                    case 18:
                        if(this.change==0) {
                            objects.colorB=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.colorB+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.colorB-=this.value;
                            break;
                        }
                        break;
                    case 19:
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
                    case 20:
                        if(this.change==0) {
                            objects.head=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.head+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.head-=this.value;
                            break;
                        }
                        break;
                    case 21:
                        if(this.change==0) {
                            objects.sonspeed=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeed+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeed-=this.value;
                            break;
                        }
                        break;
                    case 22:
                        if(this.change==0) {
                            objects.sonspeedd=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeedd+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeedd-=this.value;
                            break;
                        }
                        break;
                    case 23:
                        if(this.change==0) {
                            objects.sonaspeed=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeed+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeed-=this.value;
                            break;
                        }
                        break;
                    case 24:
                        if(this.change==0) {
                            objects.sonaspeedd=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeedd+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeedd-=this.value;
                            break;
                        }
                        break;
                    case 25:
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
                    case 26:
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
                    case 27:
                        if((double)this.value>0.0)
                            objects.Mist=true;
                        if((double)this.value<=0.0) {
                            objects.Mist=false;
                            break;
                        }
                        break;
                    case 28:
                        if((double)this.value>0.0)
                            objects.Dispel=true;
                        if((double)this.value<=0.0) {
                            objects.Dispel=false;
                            break;
                        }
                        break;
                    case 29:
                        if((double)this.value>0.0)
                            objects.Blend=true;
                        if((double)this.value<=0.0) {
                            objects.Blend=false;
                            break;
                        }
                        break;
                    case 30:
                        if((double)this.value>0.0)
                            objects.Afterimage=true;
                        if((double)this.value<=0.0) {
                            objects.Afterimage=false;
                            break;
                        }
                        break;
                    case 31:
                        if((double)this.value>0.0)
                            objects.Outdispel=true;
                        if((double)this.value<=0.0) {
                            objects.Outdispel=false;
                            break;
                        }
                        break;
                    case 32:
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
                            objects.fx=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.fx=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.fx=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.fy=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.fy=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.fy=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 2:
                        if(this.change==0) {
                            objects.r=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.r=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.r=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0) {
                            objects.rdirection=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.rdirection=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.rdirection=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 4:
                        if(this.change==0) {
                            objects.tiao=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.tiao=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.tiao=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 5:
                        if(this.change==0) {
                            objects.t=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.t=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.t=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 6:
                        if(this.change==0) {
                            objects.fdirection=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians(360f/(float)this.time*(float)(this.time-this.ctime)));
                            break;
                        }
                        if(this.change==1) {
                            objects.fdirection=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians(360f/(float)this.time*(float)(this.time-this.ctime)));
                            break;
                        }
                        if(this.change==2) {
                            objects.fdirection=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians(360f/(float)this.time*(float)(this.time-this.ctime)));
                            break;
                        }
                        break;
                    case 7:
                        if(this.change==0) {
                            objects.range=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.range=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.range=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 8:
                        if(this.change==0)
                            objects.speed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.speed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.speed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 9:
                        if(this.change==0)
                            objects.speedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.speedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.speedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 10:
                        if(this.change==0)
                            objects.aspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.aspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.aspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 11:
                        if(this.change==0)
                            objects.aspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.aspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.aspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 12:
                        if(this.change==0) {
                            objects.sonlife=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.sonlife=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.sonlife=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 13:
                        if(this.change==0) {
                            objects.type=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.type=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.type=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 14:
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
                    case 15:
                        if(this.change==0) {
                            objects.hscale=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.hscale=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.hscale=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 16:
                        if(this.change==0) {
                            objects.colorR=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.colorR=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.colorR=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 17:
                        if(this.change==0) {
                            objects.colorG=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.colorG=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.colorG=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 18:
                        if(this.change==0) {
                            objects.colorB=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.colorB=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.colorB=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 19:
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
                    case 20:
                        if(this.change==0) {
                            objects.head=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.head=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.head=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 21:
                        if(this.change==0) {
                            objects.sonspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 22:
                        if(this.change==0) {
                            objects.sonspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.sonspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.sonspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 23:
                        if(this.change==0) {
                            objects.sonaspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 24:
                        if(this.change==0) {
                            objects.sonaspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.sonaspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.sonaspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 25:
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
                    case 26:
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
                    case 27:
                        if((double)this.value>0.0)
                            objects.Mist=true;
                        if((double)this.value<=0.0) {
                            objects.Mist=false;
                            break;
                        }
                        break;
                    case 28:
                        if((double)this.value>0.0)
                            objects.Dispel=true;
                        if((double)this.value<=0.0) {
                            objects.Dispel=false;
                            break;
                        }
                        break;
                    case 29:
                        if((double)this.value>0.0)
                            objects.Blend=true;
                        if((double)this.value<=0.0) {
                            objects.Blend=false;
                            break;
                        }
                        break;
                    case 30:
                        if((double)this.value>0.0)
                            objects.Afterimage=true;
                        if((double)this.value<=0.0) {
                            objects.Afterimage=false;
                            break;
                        }
                        break;
                    case 31:
                        if((double)this.value>0.0)
                            objects.Outdispel=true;
                        if((double)this.value<=0.0) {
                            objects.Outdispel=false;
                            break;
                        }
                        break;
                    case 32:
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
        public object Clone() {
            MemoryStream memoryStream = new MemoryStream();
            BinaryFormatter binaryFormatter = new BinaryFormatter();
            binaryFormatter.Serialize((Stream)memoryStream,(object)this);
            memoryStream.Seek(0L,SeekOrigin.Begin);
            object obj = binaryFormatter.Deserialize((Stream)memoryStream);
            memoryStream.Close();
            return obj;
        }
		}
