package cs.layer;

    public class Cover {
        private float[] conditions = new float[5];
        private float[] results = new float[11];
        private int clcount;
        private int clwait;
        public boolean NeedDelete;
        public int Searched;
        public int id;
        public int childid;
        public int parentid;
        public int bindid;
        public boolean Deepbind;
        public boolean Deepbinded;
        public int parentcolor;
        public float x;
        public float y;
        public int time;
        public int begin;
        public int life;
        public int halfw;
        public int halfh;
        public boolean Circle;
        public int type;
        public int controlid;
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
        public Cover rand;
        public ArrayList<Event> Parentevents;
        public ArrayList<COExecution> Eventsexe;
        public ArrayList<Event> Sonevents;
        public Cover copys;
        public Cover() {
        }
        public Cover(float xs,float ys,int pc) {
            this.rand=new Cover();
            this.Parentevents=new ArrayList<Event>();
            this.Sonevents=new ArrayList<Event>();
            this.Eventsexe=new ArrayList<COExecution>();
            this.x=xs;
            this.y=ys;
            this.parentcolor=pc;
            this.begin=Layer.LayerArray.get(this.parentid).begin;
            this.life=Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin+1;
            this.halfw=100;
            this.halfh=100;
            this.type=0;
            this.controlid=1;
            this.bindid=-1;
            this.speed=0.0f;
            this.speedd=0.0f;
            this.aspeed=0.0f;
            this.aspeedd=0.0f;
            this.Circle=false;
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
                this.childid=0;
                this.aspeedx=this.aspeed*(float)Math.cos((double)MathHelper.ToRadians(this.aspeedd));
                this.aspeedy=this.aspeed*(float)Math.sin((double)MathHelper.ToRadians(this.aspeedd));
                this.speedx=this.speed*(float)Math.cos((double)MathHelper.ToRadians(this.speedd));
                this.speedy=this.speed*(float)Math.sin((double)MathHelper.ToRadians(this.speedd));
                this.begin=(int)MathHelper.Clamp((float)this.begin,(float)Layer.LayerArray.get(this.parentid).begin,(float)(1+Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin));
                this.life=(int)MathHelper.Clamp((float)this.life,1f,(float)(Layer.LayerArray.get(this.parentid).end-Layer.LayerArray.get(this.parentid).begin+2-this.begin));
            }
            if(this.bindid!=-1&&this.bindid>=Layer.LayerArray.get(this.parentid).BatchArray.size()) {
                this.bindid=-1;
                this.Deepbind=false;
            }
            if(!Time.Playing)
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
                this.conditions[1]=this.x+16f;
                this.conditions[2]=this.y+16f;
                this.conditions[3]=(float)this.halfw;
                this.conditions[4]=(float)this.halfh;
                this.results[0]=(float)this.halfw;
                this.results[1]=(float)this.halfh;
                this.results[2]=0.0f;
                this.results[3]=this.speed;
                this.results[4]=this.speedd;
                this.results[5]=this.aspeed;
                this.results[6]=this.aspeedd;
                this.results[7]=(float)this.type;
                this.results[8]=(float)this.controlid;
                this.results[9]=this.x-4f;
                this.results[10]=this.y+16f;
                for(Event parentevent : this.Parentevents) {
                    if(parentevent.t<=0)
                        parentevent.t=1;
                    if(this.time%parentevent.t==0)
                        ++parentevent.loop;
                    for(EventRead result : parentevent.results) {
                        if(result.special==4) {
                            if(result.changevalue==9)
                                result.res=Player.position.x;
                            if(result.changevalue==10)
                                result.res=Player.position.y;
                            if(result.changevalue==4)
                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f,this.y+16f));
                            if(result.changevalue==6)
                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,this.x-4f,this.y+16f));
                        }
                        if(result.opreator.equals(">") {
                            if(result.opreator2.equals(">") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if(result.opreator2.equals("=") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if(result.opreator2.equals("<") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if((double)this.conditions[result.contype]>(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
                                COExecution coExecution = new COExecution();
                                if(!result.noloop) {
                                    if(result.time>0) {
                                        --result.time;
                                        if(result.time==0)
                                            result.noloop=true;
                                    }
                                    coExecution.parentid=this.parentid;
                                    coExecution.id=this.id;
                                    coExecution.change=result.change;
                                    coExecution.changetype=result.changetype;
                                    coExecution.changevalue=result.changevalue;
                                    coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                    coExecution.region=this.results[result.changename].ToString();
                                    coExecution.time=result.times;
                                    coExecution.ctime=coExecution.time;
                                    this.Eventsexe.put(coExecution);
                                } else
                                    continue;
                            }
                        }
                        if(result.opreator.equals("=") {
                            if(result.opreator2.equals(">") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if(result.opreator2.equals("=") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if(result.opreator2.equals("<") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        } else
                                            continue;
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    } else
                                        continue;
                                }
                            } else if((double)this.conditions[result.contype]==(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
                                COExecution coExecution = new COExecution();
                                if(!result.noloop) {
                                    if(result.time>0) {
                                        --result.time;
                                        if(result.time==0)
                                            result.noloop=true;
                                    }
                                    coExecution.parentid=this.parentid;
                                    coExecution.id=this.id;
                                    coExecution.change=result.change;
                                    coExecution.changetype=result.changetype;
                                    coExecution.changevalue=result.changevalue;
                                    coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                    coExecution.region=this.results[result.changename].ToString();
                                    coExecution.time=result.times;
                                    coExecution.ctime=coExecution.time;
                                    this.Eventsexe.put(coExecution);
                                } else
                                    continue;
                            }
                        }
                        if(result.opreator.equals("<") {
                            if(result.opreator2.equals(">") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        }
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]>(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    }
                                }
                            } else if(result.opreator2.equals("=") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        }
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    }
                                }
                            } else if(result.opreator2.equals("<") {
                                if(result.collector.equals("且") {
                                    if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)&(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime)) {
                                        COExecution coExecution = new COExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            coExecution.parentid=this.parentid;
                                            coExecution.id=this.id;
                                            coExecution.change=result.change;
                                            coExecution.changetype=result.changetype;
                                            coExecution.changevalue=result.changevalue;
                                            coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            coExecution.region=this.results[result.changename].ToString();
                                            coExecution.time=result.times;
                                            coExecution.ctime=coExecution.time;
                                            this.Eventsexe.put(coExecution);
                                        }
                                    }
                                } else if(result.collector.equals("或"&&((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)||(double)this.conditions[result.contype2]<(double)Float.parseFloat(result.condition2)+(double)(parentevent.loop*parentevent.addtime))) {
                                    COExecution coExecution = new COExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        coExecution.parentid=this.parentid;
                                        coExecution.id=this.id;
                                        coExecution.change=result.change;
                                        coExecution.changetype=result.changetype;
                                        coExecution.changevalue=result.changevalue;
                                        coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        coExecution.region=this.results[result.changename].ToString();
                                        coExecution.time=result.times;
                                        coExecution.ctime=coExecution.time;
                                        this.Eventsexe.put(coExecution);
                                    }
                                }
                            } else if((double)this.conditions[result.contype]<(double)Float.parseFloat(result.condition)+(double)(parentevent.loop*parentevent.addtime)) {
                                COExecution coExecution = new COExecution();
                                if(!result.noloop) {
                                    if(result.time>0) {
                                        --result.time;
                                        if(result.time==0)
                                            result.noloop=true;
                                    }
                                    coExecution.parentid=this.parentid;
                                    coExecution.id=this.id;
                                    coExecution.change=result.change;
                                    coExecution.changetype=result.changetype;
                                    coExecution.changevalue=result.changevalue;
                                    coExecution.value=(double)result.rand==0.0 ? result.res : result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                    coExecution.region=this.results[result.changename].ToString();
                                    coExecution.time=result.times;
                                    coExecution.ctime=coExecution.time;
                                    this.Eventsexe.put(coExecution);
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
            if(this.bindid==-1) {
                for(Barrage barrage : Layer.LayerArray.get(this.parentid).Barrages) {
                    if(((barrage.Cover&!barrage.IsLase ? 1 : 0)&(barrage.time>15 ? 1 : (!barrage.Mist ? 1 : 0))&(!barrage.NeedDelete ? 1 : 0))!=0) {
                        if(this.Circle) {
                            if(this.type==0) {
                                if(Math.sqrt(((double)this.x-4.0-(double)barrage.x)*((double)this.x-4.0-(double)barrage.x)+((double)this.y+16.0-(double)barrage.y)*((double)this.y+16.0-(double)barrage.y))<=(double)Math.Max(this.halfw,this.halfh)) {
                                    if(!barrage.Covered.contains(this.id)) {
                                        for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                            barrage.Events.put(new Event(idx) {
                                                t=this.Sonevents[idx].t,
                                                addtime=this.Sonevents[idx].addtime,
                                                events=this.Sonevents[idx].events,
                                                results=this.Sonevents[idx].results,
                                                index=this.Sonevents[idx].index,
                                                special=this.id
                                            });
                                            barrage.Covered.put(this.id);
                                        }
                                    }
                                } else {
                                    if(barrage.Covered.contains(this.id)) {
                                        for(int index = 0;index<barrage.Events.size();++index) {
                                            if(barrage.Events[index].special==this.id) {
                                                barrage.Events.remove(index);
                                                --index;
                                            }
                                        }
                                    }
                                    barrage.Covered.Remove(this.id);
                                }
                            } else if(this.type==1) {
                                if(barrage.parentid==this.controlid-1&Math.sqrt(((double)this.x-4.0-(double)barrage.x)*((double)this.x-4.0-(double)barrage.x)+((double)this.y+16.0-(double)barrage.y)*((double)this.y+16.0-(double)barrage.y))<=(double)Math.Max(this.halfw,this.halfh)) {
                                    if(!barrage.Covered.contains(this.id)) {
                                        for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                            barrage.Events.put(new Event(idx) {
                                                t=this.Sonevents[idx].t,
                                                addtime=this.Sonevents[idx].addtime,
                                                events=this.Sonevents[idx].events,
                                                results=this.Sonevents[idx].results,
                                                index=this.Sonevents[idx].index,
                                                special=this.id
                                            });
                                            barrage.Covered.put(this.id);
                                        }
                                    }
                                } else {
                                    if(barrage.Covered.contains(this.id)) {
                                        for(int index = 0;index<barrage.Events.size();++index) {
                                            if(barrage.Events[index].special==this.id) {
                                                barrage.Events.remove(index);
                                                --index;
                                            }
                                        }
                                    }
                                    barrage.Covered.Remove(this.id);
                                }
                            }
                        } else if(this.type==0) {
                            if((double)Math.abs(this.x-4f-barrage.x)<=(double)this.halfw&(double)Math.abs(this.y+16f-barrage.y)<=(double)this.halfh) {
                                if(!barrage.Covered.contains(this.id)) {
                                    for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                        barrage.Events.put(new Event(idx) {
                                            t=this.Sonevents[idx].t,
                                            addtime=this.Sonevents[idx].addtime,
                                            events=this.Sonevents[idx].events,
                                            results=this.Sonevents[idx].results,
                                            index=this.Sonevents[idx].index,
                                            special=this.id
                                        });
                                        barrage.Covered.put(this.id);
                                    }
                                }
                            } else {
                                if(barrage.Covered.contains(this.id)) {
                                    for(int index = 0;index<barrage.Events.size();++index) {
                                        if(barrage.Events[index].special==this.id) {
                                            barrage.Events.remove(index);
                                            --index;
                                        }
                                    }
                                }
                                barrage.Covered.Remove(this.id);
                            }
                        } else if(this.type==1) {
                            if(barrage.parentid==this.controlid-1&(double)Math.abs(this.x-4f-barrage.x)<=(double)this.halfw&(double)Math.abs(this.y+16f-barrage.y)<=(double)this.halfh) {
                                if(!barrage.Covered.contains(this.id)) {
                                    for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                        barrage.Events.put(new Event(idx) {
                                            t=this.Sonevents[idx].t,
                                            addtime=this.Sonevents[idx].addtime,
                                            events=this.Sonevents[idx].events,
                                            results=this.Sonevents[idx].results,
                                            index=this.Sonevents[idx].index,
                                            special=this.id
                                        });
                                        barrage.Covered.put(this.id);
                                    }
                                }
                            } else {
                                if(barrage.Covered.contains(this.id)) {
                                    for(int index = 0;index<barrage.Events.size();++index) {
                                        if(barrage.Events[index].special==this.id) {
                                            barrage.Events.remove(index);
                                            --index;
                                        }
                                    }
                                }
                                barrage.Covered.Remove(this.id);
                            }
                        }
                    }
                }
            } else {
                int num1 = 1000;
                int num2 = 0;
                for(Barrage barrage1 : Layer.LayerArray.get(this.parentid).Barrages) {
                    if(!barrage1.IsLase&barrage1.parentid==this.bindid&!barrage1.NeedDelete) {
                        if(this.Deepbind) {
                            if(barrage1.cover!=null) {
                                barrage1.cover.x=barrage1.x;
                                barrage1.cover.y=barrage1.y;
                                barrage1.cover.Update();
                            } else {
                                barrage1.cover=this.BindClone();
                                barrage1.cover.id=this.childid;
                                barrage1.cover.Deepbind=false;
                                barrage1.cover.Deepbinded=true;
                                barrage1.cover.bindid=-1;
                            }
                        } else if(barrage1.time>=15||!barrage1.Mist) {
                            for(Barrage barrage2 : Layer.LayerArray.get(this.parentid).Barrages) {
                                if(((barrage2.id!=barrage1.id&barrage2.Cover&!barrage2.IsLase ? 1 : 0)&(barrage2.time>15 ? 1 : (!barrage2.Mist ? 1 : 0))&(!barrage2.NeedDelete ? 1 : 0))!=0) {
                                    if(this.Circle) {
                                        if(this.type==0) {
                                            if(Math.sqrt(((double)barrage1.x-(double)barrage2.x)*((double)barrage1.x-(double)barrage2.x)+((double)barrage1.y-(double)barrage2.y)*((double)barrage1.y-(double)barrage2.y))<=(double)Math.Max(this.halfw,this.halfh)) {
                                                if(!barrage2.Covered.contains(num1)) {
                                                    for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                                        barrage2.Events.put(new Event(idx) {
                                                            t=this.Sonevents[idx].t,
                                                            addtime=this.Sonevents[idx].addtime,
                                                            events=this.Sonevents[idx].events,
                                                            results=this.Sonevents[idx].results,
                                                            index=this.Sonevents[idx].index,
                                                            special=barrage2.id
                                                        });
                                                        barrage2.Covered.put(num1);
                                                        ++num2;
                                                    }
                                                }
                                            } else {
                                                if(barrage2.Covered.contains(num1)) {
                                                    for(int index = 0;index<barrage2.Events.size();++index) {
                                                        if(barrage2.Events[index].special==barrage2.id) {
                                                            barrage2.Events.remove(index);
                                                            --index;
                                                        }
                                                    }
                                                }
                                                barrage2.Covered.Remove(num1);
                                            }
                                        } else if(this.type==1) {
                                            if(barrage2.parentid==this.controlid-1&Math.sqrt(((double)barrage1.x-(double)barrage2.x)*((double)barrage1.x-(double)barrage2.x)+((double)barrage1.y-(double)barrage2.y)*((double)barrage1.y-(double)barrage2.y))<=(double)Math.Max(this.halfw,this.halfh)) {
                                                if(!barrage2.Covered.contains(num1)) {
                                                    for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                                        barrage2.Events.put(new Event(idx) {
                                                            t=this.Sonevents[idx].t,
                                                            addtime=this.Sonevents[idx].addtime,
                                                            events=this.Sonevents[idx].events,
                                                            results=this.Sonevents[idx].results,
                                                            index=this.Sonevents[idx].index,
                                                            special=barrage2.id
                                                        });
                                                        barrage2.Covered.put(num1);
                                                    }
                                                }
                                            } else {
                                                if(barrage2.Covered.contains(num1)) {
                                                    for(int index = 0;index<barrage2.Events.size();++index) {
                                                        if(barrage2.Events[index].special==barrage2.id) {
                                                            barrage2.Events.remove(index);
                                                            --index;
                                                        }
                                                    }
                                                }
                                                barrage2.Covered.Remove(num1);
                                            }
                                        }
                                    } else if(this.type==0) {
                                        if((double)Math.abs(barrage1.x-barrage2.x)<=(double)this.halfw&(double)Math.abs(barrage1.y-barrage2.y)<=(double)this.halfh) {
                                            if(!barrage2.Covered.contains(num1)) {
                                                for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                                    barrage2.Events.put(new Event(idx) {
                                                        t=this.Sonevents[idx].t,
                                                        addtime=this.Sonevents[idx].addtime,
                                                        events=this.Sonevents[idx].events,
                                                        results=this.Sonevents[idx].results,
                                                        index=this.Sonevents[idx].index,
                                                        special=barrage2.id
                                                    });
                                                    barrage2.Covered.put(num1);
                                                }
                                            }
                                        } else {
                                            if(barrage2.Covered.contains(num1)) {
                                                for(int index = 0;index<barrage2.Events.size();++index) {
                                                    if(barrage2.Events[index].special==barrage2.id) {
                                                        barrage2.Events.remove(index);
                                                        --index;
                                                    }
                                                }
                                            }
                                            barrage2.Covered.Remove(num1);
                                        }
                                    } else if(this.type==1) {
                                        if(barrage2.parentid==this.controlid-1&(double)Math.abs(barrage1.x-barrage2.x)<=(double)this.halfw&(double)Math.abs(barrage1.y-barrage2.y)<=(double)this.halfh) {
                                            if(!barrage2.Covered.contains(num1)) {
                                                for(int idx = 0;idx<this.Sonevents.size();++idx) {
                                                    barrage2.Events.put(new Event(idx) {
                                                        t=this.Sonevents[idx].t,
                                                        addtime=this.Sonevents[idx].addtime,
                                                        events=this.Sonevents[idx].events,
                                                        results=this.Sonevents[idx].results,
                                                        index=this.Sonevents[idx].index,
                                                        special=barrage2.id
                                                    });
                                                    barrage2.Covered.put(num1);
                                                }
                                            }
                                        } else {
                                            if(barrage2.Covered.contains(num1)) {
                                                for(int index = 0;index<barrage2.Events.size();++index) {
                                                    if(barrage2.Events[index].special==barrage2.id) {
                                                        barrage2.Events.remove(index);
                                                        --index;
                                                    }
                                                }
                                            }
                                            barrage2.Covered.Remove(num1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ++num1;
                    ++this.childid;
                }
            }
        }
        public Cover BindClone() {
            Cover cover = this.Copy() as Cover;
            cover.Parentevents=new ArrayList<Event>();
            for(Event parentevent : this.Parentevents)
                cover.Parentevents.put((Event)parentevent.Clone());
            cover.Eventsexe=new ArrayList<COExecution>();
            for(COExecution coExecution : this.Eventsexe)
                cover.Eventsexe.put((COExecution)coExecution.Clone());
            cover.Sonevents=new ArrayList<Event>();
            for(Event sonevent : this.Sonevents)
                cover.Sonevents.put((Event)sonevent.Clone());
            return cover;
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
