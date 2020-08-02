package cs.layer;

    public class COExecution {
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
        public void Update(Cover objects) {
            if(this.changetype==0) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0) {
                            objects.halfw=(int)(((double)objects.halfw*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.halfw+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.halfw-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.halfh=(int)(((double)objects.halfh*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                            break;
                        }
                        if(this.change==1) {
                            objects.halfh+=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        if(this.change==2) {
                            objects.halfh-=(int)((double)this.value/(double)this.time);
                            break;
                        }
                        break;
                    case 2:
                        if((double)this.value>0.0)
                            objects.Circle=true;
                        if((double)this.value<=0.0) {
                            objects.Circle=false;
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0)
                            objects.speed=(objects.speed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.speed+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.speed-=this.value/(float)this.time;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 4:
                        if(this.change==0)
                            objects.speedd=(objects.speedd*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.speedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.speedd-=this.value/(float)this.time;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 5:
                        if(this.change==0)
                            objects.aspeed=(objects.aspeed*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                        else if(this.change==1)
                            objects.aspeed+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.aspeed-=this.value/(float)this.time;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 6:
                        if(this.change==0)
                            objects.aspeedd=(float)(int)(((double)objects.aspeedd*((double)this.ctime-1.0)+(double)this.value)/(double)this.ctime);
                        else if(this.change==1)
                            objects.aspeedd+=this.value/(float)this.time;
                        else if(this.change==2)
                            objects.aspeedd-=this.value/(float)this.time;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 7:
                        if((double)this.value>0.0)
                            objects.type=1;
                        if((double)this.value<=0.0) {
                            objects.type=0;
                            break;
                        }
                        break;
                    case 8:
                        objects.controlid=(int)this.value;
                        break;
                    case 9:
                        if(this.change==0) {
                            objects.x=(objects.x*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.x+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.x-=this.value/(float)this.time;
                            break;
                        }
                        break;
                    case 10:
                        if(this.change==0) {
                            objects.y=(objects.y*((float)this.ctime-1f)+this.value)/(float)this.ctime;
                            break;
                        }
                        if(this.change==1) {
                            objects.y+=this.value/(float)this.time;
                            break;
                        }
                        if(this.change==2) {
                            objects.y-=this.value/(float)this.time;
                            break;
                        }
                        break;
                }
            } else if(this.changetype==1) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0) {
                            objects.halfw=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.halfw+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.halfw-=(int)this.value;
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.halfh=(int)this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.halfh+=(int)this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.halfh-=(int)this.value;
                            break;
                        }
                        break;
                    case 2:
                        if((double)this.value>0.0)
                            objects.Circle=true;
                        if((double)this.value<=0.0) {
                            objects.Circle=false;
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0)
                            objects.speed=this.value;
                        else if(this.change==1)
                            objects.speed+=this.value;
                        else if(this.change==2)
                            objects.speed-=this.value;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 4:
                        if(this.change==0)
                            objects.speedd=this.value;
                        else if(this.change==1)
                            objects.speedd+=this.value;
                        else if(this.change==2)
                            objects.speedd-=this.value;
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 5:
                        if(this.change==0)
                            objects.aspeed=this.value;
                        else if(this.change==1)
                            objects.aspeed+=this.value;
                        else if(this.change==2)
                            objects.aspeed-=this.value;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 6:
                        if(this.change==0)
                            objects.aspeedd=this.value;
                        else if(this.change==1)
                            objects.aspeedd+=this.value;
                        else if(this.change==2)
                            objects.aspeedd-=this.value;
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 7:
                        if((double)this.value>0.0)
                            objects.type=1;
                        if((double)this.value<=0.0) {
                            objects.type=0;
                            break;
                        }
                        break;
                    case 8:
                        objects.controlid=(int)this.value;
                        break;
                    case 9:
                        if(this.change==0) {
                            objects.x=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.x+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.x-=this.value;
                            break;
                        }
                        break;
                    case 10:
                        if(this.change==0) {
                            objects.y=this.value;
                            break;
                        }
                        if(this.change==1) {
                            objects.y+=this.value;
                            break;
                        }
                        if(this.change==2) {
                            objects.y-=this.value;
                            break;
                        }
                        break;
                }
            } else if(this.changetype==2) {
                switch(this.changevalue) {
                    case 0:
                        if(this.change==0) {
                            objects.halfw=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.halfw=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.halfw=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 1:
                        if(this.change==0) {
                            objects.halfh=(int)((double)Float.parseFloat(this.region)+((double)this.value-(double)Float.parseFloat(this.region))*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==1) {
                            objects.halfh=(int)((double)Float.parseFloat(this.region)+(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        if(this.change==2) {
                            objects.halfh=(int)((double)Float.parseFloat(this.region)-(double)this.value*Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime)))));
                            break;
                        }
                        break;
                    case 2:
                        if((double)this.value>0.0)
                            objects.Circle=true;
                        if((double)this.value<=0.0) {
                            objects.Circle=false;
                            break;
                        }
                        break;
                    case 3:
                        if(this.change==0)
                            objects.speed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.speed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.speed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 4:
                        if(this.change==0)
                            objects.speedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.speedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.speedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.speedx=objects.speed*(float)Math.cos((double)MathHelper.ToRadians(objects.speedd));
                        objects.speedy=objects.speed*(float)Math.sin((double)MathHelper.ToRadians(objects.speedd));
                        break;
                    case 5:
                        if(this.change==0)
                            objects.aspeed=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.aspeed=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.aspeed=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 6:
                        if(this.change==0)
                            objects.aspeedd=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==1)
                            objects.aspeedd=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        else if(this.change==2)
                            objects.aspeedd=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                        objects.aspeedx=objects.aspeed*(float)Math.cos((double)MathHelper.ToRadians(objects.aspeedd));
                        objects.aspeedy=objects.aspeed*(float)Math.sin((double)MathHelper.ToRadians(objects.aspeedd));
                        break;
                    case 7:
                        if((double)this.value>0.0)
                            objects.type=1;
                        if((double)this.value<=0.0) {
                            objects.type=0;
                            break;
                        }
                        break;
                    case 8:
                        objects.controlid=(int)this.value;
                        break;
                    case 9:
                        if(this.change==0) {
                            objects.x=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.x=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.x=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        break;
                    case 10:
                        if(this.change==0) {
                            objects.y=Float.parseFloat(this.region)+(this.value-Float.parseFloat(this.region))*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==1) {
                            objects.y=Float.parseFloat(this.region)+this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
                            break;
                        }
                        if(this.change==2) {
                            objects.y=Float.parseFloat(this.region)-this.value*(float)Math.sin((double)MathHelper.ToRadians((float)(360.0/(double)this.time*((double)this.time-(double)this.ctime))));
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
