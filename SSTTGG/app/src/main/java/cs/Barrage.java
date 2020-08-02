package cs;
import cs.layer.*;

public class Barrage {
        public int id = -1;
        public int parentid = -2;
        public Shadows[] savesha = new Shadows[50];
        public ArrayList<Integer> Covered = new ArrayList<Integer>();
        public float dscale = 0.9f;
        private float[] conditions = new float[3];
        private float[] results = new float[21];
        public boolean IsLase;
        public boolean IsRay;
        public int shatime;
        public boolean NeedDelete;
        public boolean Dis;
        public int life;
        public int time;
        public int type;
        public float x;
        public float y;
        public float wscale;
        public float rwscale;
        public float hscale;
        public float longs;
        public float rlongs;
        public float randf;
        public float R;
        public float G;
        public float B;
        public float alpha;
        public float head;
        public float speed;
        public float speedx;
        public float speedy;
        public float bspeedx;
        public float bspeedy;
        public float speedd;
        public float vf;
        public float aspeed;
        public float aspeedx;
        public float aspeedy;
        public float aspeedd;
        public boolean Withspeedd;
        public float fdirection;
        public float sonaspeedd;
        public float fx;
        public float fy;
        public Vector2 fdirections;
        public Vector2 sonaspeedds;
        public float randfdirection;
        public float randsonaspeedd;
        public int g;
        public int tiaos;
        public int range;
        public int randrange;
        public float bindspeedd;
        public boolean Bindwithspeedd;
        public float xscale;
        public float yscale;
        public boolean Mist;
        public boolean Dispel;
        public boolean Blend;
        public boolean Afterimage;
        public boolean Outdispel;
        public boolean Invincible;
        public boolean Cover;
        public boolean Rebound;
        public boolean Force;
        public boolean Alreadylong;
        public int reboundtime;
        public int fadeout;
        public Batch batch;
        public Lase lase;
        public Cover cover;
        public ArrayList<Event> Events;
        public ArrayList<BExecution> Eventsexe;
        public ArrayList<BLExecution> LEventsexe;
        public Barrage() {
            NeedDelete=false;
            for(int index = 0;index<50;++index) {
                savesha[index]=new Shadows {
                    x=x,
                    y=y,
                    alpha=0.0f
                };
            }
            Events=new ArrayList<Event>();
            Eventsexe=new ArrayList<BExecution>();
            LEventsexe=new ArrayList<BLExecution>();
        }
        public void Update() {
            if(!IsLase&type!=-2) {
                float x1 = x;
                float y1 = y;
                float x2 = Player.position.x;
                float y2 = Player.position.y;
                int num1 = 0;
                if(Mist) {
                    num1=15;
                }
                ++time;
                if(type<=-1) {
                    type=-1;
                }
                if(type>=Main.bgset.size()) {
                    type=Main.bgset.size()-1;
                }
                if(time>15||!Mist) {
                    if(Mist&time==16||!Mist&time==1) {
                        if(fdirection==-99998.0) {
                            fdirection=MathHelper.ToDegrees(Main.Twopointangle(fx,fy,x,y));
                        } else if(fdirection==-99999.0) {
                            fdirection=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                        } else if(this.fdirection==-100000.0) {
                            fdirection=MathHelper.ToDegrees(Main.Twopointangle(fdirections.X,fdirections.Y,x,y));
                        }
                        speedd=!Bindwithspeedd ? (float)(fdirection+(double)randfdirection+(g-(float)((tiaos-1.0)/2.0))*(double)(range+randrange)/tiaos) : (float)(fdirection+(double)randfdirection+(g-(float)((tiaos-1.0)/2.0))*(double)(range+randrange)/tiaos)+bindspeedd;
                        if(sonaspeedd==-99998.0) {
                            sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(fx,fy,x,y));
                        } else if(sonaspeedd==-99999.0) {
                            sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                        } else if(sonaspeedd==-100000.0) {
                            sonaspeedd=MathHelper.ToDegrees(Main.Twopointangle(sonaspeedds.X,sonaspeedds.Y,x,y));
                        }
                        aspeedd=sonaspeedd+randsonaspeedd;
                        speedx=xscale*speed*(float)Math.cos(MathHelper.ToRadians(speedd));
                        speedy=yscale*speed*(float)Math.sin(MathHelper.ToRadians(speedd));
                        aspeedx=xscale*aspeed*(float)Math.cos(MathHelper.ToRadians(aspeedd));
                        aspeedy=yscale*aspeed*(float)Math.sin(MathHelper.ToRadians(aspeedd));
                        if(Withspeedd) {
                            head=speedd+90f;
                        }
                    }
                    if(!Dis) {
                        speedx+=aspeedx*Time.stop;
                        speedy+=aspeedy*Time.stop;
                        x+=speedx*Time.stop;
                        y+=speedy*Time.stop;
                    }
                    if(speed!=0.0) {
                        if(speedy!=0.0) {
                            vf=1.570796f-(float)Math.atan(speedx/(double)xscale/(speedy/(double)yscale));
                            if(speedy<0.0) {
                                vf+=3.141593f;
                            }
                        } else {
                            if(speedx>=0.0) {
                                vf=0.0f;
                            }
                            if(speedx<0.0) {
                                vf=3.141593f;
                            }
                        }
                        if(speed>0.0) {
                            speedd=MathHelper.ToDegrees(vf);
                            if(Withspeedd) {
                                head=speedd;
                            }
                        } else if(Withspeedd) {
                            head=MathHelper.ToDegrees(vf);
                        }
                    }
                    if(Afterimage&time<=num1+life) {
                        savesha[shatime].alpha=(float)(0.400000005960464*(alpha/100.0));
                        savesha[shatime].x=x;
                        savesha[shatime].y=y;
                        savesha[shatime].d=head;
                        ++shatime;
                        if(shatime>=49) {
                            shatime=0;
                        }
                    } else {
                        shatime=0;
                    }
                    conditions[0]=time-num1;
                    conditions[1]=x;
                    conditions[2]=y;
                    results[0]=life;
                    results[1]=type;
                    results[2]=wscale;
                    results[3]=hscale;
                    results[4]=R;
                    results[5]=G;
                    results[6]=B;
                    results[7]=alpha;
                    results[8]=head;
                    results[9]=speed;
                    results[10]=speedd;
                    results[11]=aspeed;
                    results[12]=aspeedd;
                    results[13]=xscale;
                    results[14]=yscale;
                    results[15]=0.0f;
                    results[16]=0.0f;
                    results[17]=0.0f;
                    results[18]=0.0f;
                    results[19]=0.0f;
                    results[20]=0.0f;
                    for(Event _event : Events) {
                        if(_event.t<=0) {
                            _event.t=1;
                        }
                        if((time-num1)%_event.t==0) {
                            ++_event.loop;
                        }
                        for(EventRead result : _event.results) {
                            if(result.special2==1) {
                                conditions[0]=Time.now;
                            }
                            if(result.opreator.equals(">") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>(double)Float.parseFloat(result.condition)+_event.loop*_event.addtime&conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+_event.loop*_event.addtime) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>(double)Float.parseFloat(result.condition)+_event.loop*_event.addtime||conditions[result.contype2]==(double)Float.parseFloat(result.condition2)+_event.loop*_event.addtime)) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==10) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        if(result.changevalue==12) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                    }
                                    BExecution bexecution = new BExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        bexecution.change=result.change;
                                        bexecution.changetype=result.changetype;
                                        bexecution.changevalue=result.changevalue;
                                        bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        bexecution.region=results[result.changename].ToString();
                                        bexecution.time=result.times;
                                        bexecution.ctime=bexecution.time;
                                        Eventsexe.put(bexecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.opreator.equals("=") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==10) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        if(result.changevalue==12) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                    }
                                    BExecution bexecution = new BExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        bexecution.change=result.change;
                                        bexecution.changetype=result.changetype;
                                        bexecution.changevalue=result.changevalue;
                                        bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        bexecution.region=results[result.changename].ToString();
                                        bexecution.time=result.times;
                                        bexecution.ctime=bexecution.time;
                                        Eventsexe.put(bexecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.opreator.equals("<") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<(double)Float.parseFloat(result.condition)+_event.loop*_event.addtime&conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==10) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                                if(result.changevalue==12) {
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                }
                                            }
                                            BExecution bexecution = new BExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                bexecution.change=result.change;
                                                bexecution.changetype=result.changetype;
                                                bexecution.changevalue=result.changevalue;
                                                bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                bexecution.region=results[result.changename].ToString();
                                                bexecution.time=result.times;
                                                bexecution.ctime=bexecution.time;
                                                Eventsexe.put(bexecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==10) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            if(result.changevalue==12) {
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                        }
                                        BExecution bexecution = new BExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            bexecution.change=result.change;
                                            bexecution.changetype=result.changetype;
                                            bexecution.changevalue=result.changevalue;
                                            bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            bexecution.region=results[result.changename].ToString();
                                            bexecution.time=result.times;
                                            bexecution.ctime=bexecution.time;
                                            Eventsexe.put(bexecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==10) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        if(result.changevalue==12) {
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                    }
                                    BExecution bexecution = new BExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        bexecution.change=result.change;
                                        bexecution.changetype=result.changetype;
                                        bexecution.changevalue=result.changevalue;
                                        bexecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        bexecution.region=results[result.changename].ToString();
                                        bexecution.time=result.times;
                                        bexecution.ctime=bexecution.time;
                                        Eventsexe.put(bexecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.special==5) {
                                x=Center.ox;
                                y=Center.oy;
                            }
                            if(result.special2==1) {
                                conditions[0]=Time.now;
                            }
                        }
                    }
                    for(int index = 0;index<Eventsexe.size();++index) {
                        if(!Eventsexe[index].NeedDelete) {
                            Eventsexe[index].Update(this);
                        } else {
                            Eventsexe.remove(index);
                            --index;
                        }
                    }
                    if(Main.Missable&!Dis&!Player.Dis&alpha>95.0&type>=0&&Judge(x1,y1,x,y,x2,y2,Player.position.x,Player.position.y,wscale,hscale,Main.bgset[type].pdr0,head)) {
                        if(!Invincible) {
                            time=1+num1+life;
                            Dis=true;
                            Blend=true;
                            randf=10f*(float)Main.rand.NextDouble();
                        }
                        Player.Dis=true;
                    }
                    if(Main.Missable&!Dis&&Math.sqrt((x-(double)Player.position.x)*(x-(double)Player.position.x)+(y-(double)Player.position.y)*(y-(double)Player.position.y))<Math.abs(Player.time*15)&&!Invincible) {
                        time=1+num1+life;
                        Dis=true;
                        Blend=true;
                        randf=10f*(float)Main.rand.NextDouble();
                    }
                    if(time>num1+life) {
                        if(Dispel&type>=0) {
                            if(Main.bgset[type].rect.Width<=32) {
                                fadeout+=5;
                                alpha-=5f;
                                if(alpha<=0.0) {
                                    alpha=0.0f;
                                }
                                wscale=MathHelper.Clamp(wscale-0.06f,0.0f,100f);
                                hscale=MathHelper.Clamp(hscale-0.06f,0.0f,100f);
                                if(time-(num1+life)>=20) {
                                    NeedDelete=true;
                                }
                            } else {
                                fadeout+=5;
                                alpha-=5f;
                                if(alpha<=0.0) {
                                    alpha=0.0f;
                                }
                                wscale+=0.06f;
                                hscale+=0.06f;
                                if(time-(num1+life)>=20) {
                                    NeedDelete=true;
                                }
                            }
                        } else {
                            NeedDelete=true;
                        }
                    }
                } else if(!Invincible&&Math.sqrt((x-(double)Player.position.x)*(x-(double)Player.position.x)+(y-(double)Player.position.y)*(y-(double)Player.position.y))<=10.0) {
                    NeedDelete=true;
                }
                int num2 = 0;
                for(Shadows shadows : savesha) {
                    if(shadows.alpha<=0.0)
                        ++num2;
                }
                if(Outdispel) {
                    if(num2==savesha.Length) {
                        if(x<0||x>630||(y<0||y>480)) {
                            NeedDelete=true;
                        }
                    }
                } else if(num2==savesha.Length) {
                    if(x<-110.0||x>740.0||(y<-250.0||y>730.0)) {
                        NeedDelete=true;
                    }
                }
            }
            if(!(!IsLase&type==-2)) {
                return;
            }
            NeedDelete=true;
        }
        public void Draw(SpriteBatch s) {
            if(IsLase||type==-1) {
                return;
            }
            if(time<=15&Mist) {
                if(Main.bgset[type].rect.Width<=48) {
                    if(Main.bgset[type].color!=-1) {
                        s.Draw(Main.mist,new Vector2(x,y),new Rectangle(new Rectangle(Main.bgset[type].color*32,0,32,30)),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,this.B/(float)Byte.MAX_VALUE,(float)(time/15.0*(alpha/100.0))),0.0f,new Vector2(16f,15f),(float)(Main.bgset[type].rect.Width/30.0+1.5*(15.0-time)/15.0),SpriteEffects.None,0.0f);
                    } else if(type<228) {
                        s.Draw(Main.barrages,new Vector2(x,y),new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,(float)(time/15.0*(alpha/100.0))),MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale,hscale),SpriteEffects.None,0.0f);
                    } else {
                        s.Draw(Main.barrages2,new Vector2(x,y),new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,(float)(time/15.0*(alpha/100.0))),MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(this.wscale,this.hscale),SpriteEffects.None,0.0f);
                    }
                } else if(type<228) {
                    s.Draw(Main.barrages,new Vector2(x,y),new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,(float)(time/15.0*(alpha/100.0))),MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale+(float)((15.0-time)/15.0),hscale+(float)((15.0-time)/15.0)),SpriteEffects.None,0.0f);
                } else {
                    s.Draw(Main.barrages2,new Vector2(x,y),new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,(float)(time/15.0*(alpha/100.0))),MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale+(float)((15.0-time)/15.0),hscale+(float)((15.0-time)/15.0)),SpriteEffects.None,0.0f);
                }
            } else {
                if(type<228) {
                    s.Draw(Main.barrages,new Vector2(x,y),
                        new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,alpha/100f),
                        MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale,hscale),SpriteEffects.None,0.0f);
                } else {
                    s.Draw(Main.barrages2,new Vector2(x,y),
                        new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,alpha/100f),
                        MathHelper.ToRadians(head)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale,hscale),SpriteEffects.None,0.0f);
                }
                if(Afterimage) {
                    for(Shadows shadows : savesha) {
                        if(shadows.alpha>0.0) {
                            shadows.alpha-=0.02f;
                            if(type<228) {
                                s.Draw(Main.barrages,new Vector2(shadows.x,+shadows.y),
                                    new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,shadows.alpha),
                                    MathHelper.ToRadians(shadows.d)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale,hscale),SpriteEffects.None,0.0f);
                            } else {
                                s.Draw(Main.barrages2,new Vector2(shadows.x,+shadows.y),
                                    new Rectangle(Main.bgset[type].rect),new Color(R/Byte.MAX_VALUE,G/Byte.MAX_VALUE,B/Byte.MAX_VALUE,shadows.alpha),
                                    MathHelper.ToRadians(shadows.d)+1.570796f,new Vector2(Main.bgset[type].origin.X,Main.bgset[type].origin.Y),new Vector2(wscale,hscale),SpriteEffects.None,0.0f);
                            }
                        }
                    }
                }
            }
        }
        public void LUpdate() {
            if(IsLase&type!=-1) {
                float x1 = x;
                float y1 = y;
                float x2 = Player.position.x;
                float y2 = Player.position.y;
                ++time;
                if(time<=life) {
                    conditions[0]=time;
                    results[0]=life;
                    results[1]=type;
                    results[2]=wscale;
                    results[3]=longs;
                    results[4]=alpha;
                    results[5]=speed;
                    results[6]=speedd;
                    results[7]=aspeed;
                    results[8]=aspeedd;
                    results[9]=xscale;
                    results[10]=yscale;
                    results[11]=0.0f;
                    results[12]=0.0f;
                    results[13]=0.0f;
                    for(Event _event : Events) {
                        if(_event.t<=0)
                            _event.t=1;
                        if(time%_event.t==0)
                            ++_event.loop;
                        for(EventRead result : _event.results) {
                            if(result.opreator.equals(">") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]>Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==6)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        if(result.changevalue==8)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                    }
                                    BLExecution blExecution = new BLExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        blExecution.change=result.change;
                                        blExecution.changetype=result.changetype;
                                        blExecution.changevalue=result.changevalue;
                                        blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        blExecution.region=results[result.changename].ToString();
                                        blExecution.time=result.times;
                                        blExecution.ctime=blExecution.time;
                                        LEventsexe.put(blExecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.opreator.equals("=") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]==Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==6)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        if(result.changevalue==8)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                    }
                                    BLExecution blExecution = new BLExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        blExecution.change=result.change;
                                        blExecution.changetype=result.changetype;
                                        blExecution.changevalue=result.changevalue;
                                        blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        blExecution.region=results[result.changename].ToString();
                                        blExecution.time=result.times;
                                        blExecution.ctime=blExecution.time;
                                        LEventsexe.put(blExecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.opreator.equals("<") {
                                if(result.opreator2.equals(">") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<(double)Float.parseFloat(result.condition)+_event.loop*_event.addtime&conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]>Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("=") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]==Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(result.opreator2.equals("<") {
                                    if(result.collector.equals("且") {
                                        if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)&conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime)) {
                                            if(result.special==4) {
                                                if(result.changevalue==6)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                                if(result.changevalue==8)
                                                    result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            }
                                            BLExecution blExecution = new BLExecution();
                                            if(!result.noloop) {
                                                if(result.time>0) {
                                                    --result.time;
                                                    if(result.time==0)
                                                        result.noloop=true;
                                                }
                                                blExecution.change=result.change;
                                                blExecution.changetype=result.changetype;
                                                blExecution.changevalue=result.changevalue;
                                                blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                                blExecution.region=results[result.changename].ToString();
                                                blExecution.time=result.times;
                                                blExecution.ctime=blExecution.time;
                                                LEventsexe.put(blExecution);
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else if(result.collector.equals("或"&&(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)||conditions[result.contype2]<Float.parseFloat(result.condition2)+(double)(_event.loop*_event.addtime))) {
                                        if(result.special==4) {
                                            if(result.changevalue==6)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                            if(result.changevalue==8)
                                                result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        }
                                        BLExecution blExecution = new BLExecution();
                                        if(!result.noloop) {
                                            if(result.time>0) {
                                                --result.time;
                                                if(result.time==0)
                                                    result.noloop=true;
                                            }
                                            blExecution.change=result.change;
                                            blExecution.changetype=result.changetype;
                                            blExecution.changevalue=result.changevalue;
                                            blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                            blExecution.region=results[result.changename].ToString();
                                            blExecution.time=result.times;
                                            blExecution.ctime=blExecution.time;
                                            LEventsexe.put(blExecution);
                                        } else {
                                            continue;
                                        }
                                    }
                                } else if(conditions[result.contype]<Float.parseFloat(result.condition)+(double)(_event.loop*_event.addtime)) {
                                    if(result.special==4) {
                                        if(result.changevalue==6)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                        if(result.changevalue==8)
                                            result.res=MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,x,y));
                                    }
                                    BLExecution blExecution = new BLExecution();
                                    if(!result.noloop) {
                                        if(result.time>0) {
                                            --result.time;
                                            if(result.time==0)
                                                result.noloop=true;
                                        }
                                        blExecution.change=result.change;
                                        blExecution.changetype=result.changetype;
                                        blExecution.changevalue=result.changevalue;
                                        blExecution.value=result.res+MathHelper.Lerp(-result.rand,result.rand,(float)Main.rand.NextDouble());
                                        blExecution.region=results[result.changename].ToString();
                                        blExecution.time=result.times;
                                        blExecution.ctime=blExecution.time;
                                        LEventsexe.put(blExecution);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(result.special==5) {
                                x=Center.ox;
                                y=Center.oy;
                            }
                        }
                    }
                    for(int index = 0;index<LEventsexe.size();++index) {
                        if(!LEventsexe[index].NeedDelete) {
                            LEventsexe[index].Update(this);
                        } else {
                            LEventsexe.remove(index);
                            --index;
                        }
                    }
                    rwscale=wscale;
                    if(!IsRay) {
                        if(speedy!=0.0) {
                            vf=1.570796f-(float)Math.atan(speedx/(double)speedy);
                            if(speedy<0.0) {
                                vf+=3.141593f;
                            }
                        } else {
                            if(speedx>=0.0) {
                                vf=0.0f;
                            }
                            if(speedx<0.0) {
                                vf=3.141593f;
                            }
                        }
                        head=MathHelper.ToDegrees(vf);
                        if(rlongs<(double)longs&!Alreadylong) {
                            rlongs+=speed;
                        }
                        if(rlongs>=(double)longs) {
                            Alreadylong=true;
                        }
                        if(rlongs>=(double)longs||Alreadylong) {
                            rlongs=longs;
                            speedx+=aspeedx*Time.stop;
                            speedy+=aspeedy*Time.stop;
                            x+=speedx*Time.stop;
                            y+=speedy*Time.stop;
                            if(Outdispel) {
                                if(x<0||x>630||(y<0||y>480)) {
                                    NeedDelete=true;
                                }
                            } else if(x<-110.0||x>740.0||(y<-250.0||y>730.0)) {
                                NeedDelete=true;
                            }
                        }
                        if(Main.Missable&!Player.Dis&alpha>95.0) {
                            float bx = (float)((x1+(double)x1+rlongs*Math.cos(MathHelper.ToRadians(speedd)))/2.0);
                            float by = (float)((y1+(double)y1+rlongs*Math.sin(MathHelper.ToRadians(speedd)))/2.0);
                            float x3 = (float)((x+(double)x+rlongs*Math.cos(MathHelper.ToRadians(speedd)))/2.0);
                            float y3 = (float)((y+(double)y+rlongs*Math.sin(MathHelper.ToRadians(speedd)))/2.0);
                            float hs = rlongs/6f;
                            if(Judge(bx,by,x3,y3,x2,y2,Player.position.x,Player.position.y,wscale,hs,2f,head)&wscale>=0.5) {
                                if(!Invincible) {
                                    time=1+life;
                                    Dis=true;
                                    randf=10f*(float)Main.rand.NextDouble();
                                }
                                Player.Dis=true;
                            }
                        }
                        if(Main.Missable&!Dis&&Math.sqrt((x-(double)Player.position.x)*(x-(double)Player.position.x)+(y-(double)Player.position.y)*(y-(double)Player.position.y))<Math.abs(Player.time*15)&&!Invincible) {
                            time=1+life;
                            Dis=true;
                            randf=10f*(float)Main.rand.NextDouble();
                        }
                    } else {
                        rlongs=792f;
                        head=speedd;
                        speedx+=aspeedx*Time.stop;
                        speedy+=aspeedy*Time.stop;
                        x+=speedx*Time.stop;
                        y+=speedy*Time.stop;
                        if(Main.Missable&!Dis&!Player.Dis&alpha>95.0) {
                            float bx = (float)((x1+(double)x1+rlongs*Math.cos(MathHelper.ToRadians(speedd)))/2.0);
                            float by = (float)((y1+(double)y1+rlongs*Math.sin(MathHelper.ToRadians(speedd)))/2.0);
                            float x3 = (float)((x+(double)x+rlongs*Math.cos(MathHelper.ToRadians(speedd)))/2.0);
                            float y3 = (float)((y+(double)y+rlongs*Math.sin(MathHelper.ToRadians(speedd)))/2.0);
                            float hs = rlongs/6f;
                            if(Judge(bx,by,x3,y3,x2,y2,Player.position.x,Player.position.y,wscale,hs,2f,head)&wscale>=0.5) {
                                if(!Invincible) {
                                    time=1+life;
                                    Dis=true;
                                    randf=10f*(float)Main.rand.NextDouble();
                                }
                                Player.Dis=true;
                            }
                        }
                        if(Main.Missable&!Dis&&Math.sqrt((x-(double)Player.position.x)*(x-(double)Player.position.x)+(y-(double)Player.position.y)*(y-(double)Player.position.y))<Math.abs(Player.time*15)&&!Invincible) {
                            time=1+life;
                            Dis=true;
                            randf=10f*(float)Main.rand.NextDouble();
                        }
                    }
                } else {
                    if(!IsRay) {
                        speedx+=aspeedx;
                        speedy+=aspeedy;
                        x+=speedx;
                        y+=speedy;
                        rlongs-=speed;
                        wscale-=0.1f*rwscale;
                        if(wscale<0.0) {
                            wscale=0.0f;
                        }
                        if(rlongs<0.0) {
                            rlongs=0.0f;
                        }
                        if(rlongs==0.0) {
                            NeedDelete=true;
                        }
                    } else {
                        head=speedd;
                        wscale-=0.1f*rwscale;
                        if(wscale<0.0) {
                            wscale=0.0f;
                        }
                        if(wscale==0.0) {
                            NeedDelete=true;
                        }
                    }
                    for(int index = 0;index<LEventsexe.size();++index) {
                        if(!LEventsexe[index].NeedDelete) {
                            LEventsexe[index].Update(this);
                        } else {
                            LEventsexe.remove(index);
                            --index;
                        }
                    }
                }
            }
            if(!(IsLase&type==-1)) {
                return;
            }
            NeedDelete=true;
        }
        public void LDraw(SpriteBatch s) {
            if(!(IsLase&type!=-1))
                return;
            if(((time<=life ? 1 : 0)&((double)rlongs<longs&!Alreadylong ? 1 : (IsRay ? 1 : 0)))!=0) {
                s.Draw(Main.mist,new Vector2(x,y),new Rectangle(new Rectangle(Main.bgset[32+type].color*32,0,32,30)),new Color(1f,1f,1f,0.8f),MathHelper.ToDegrees(time*5),new Vector2(16f,15f),1f,SpriteEffects.None,0.0f);
            }
            s.Draw(Main.barrages,new Vector2(x,y),new Rectangle(Main.bgset[32+type].rect),new Color(1f,1f,1f,alpha/100f),MathHelper.ToRadians(head)-1.570796f,new Vector2(Main.bgset[32+type].rect.Width/2,0.0f),new Vector2(wscale,rlongs/Main.bgset[32+type].rect.Height),SpriteEffects.None,0.0f);
        }
        private boolean Judge(float bx,float by,float x,float y,float bpx,float bpy,float px,float py,float ws,float hs,float pdr,float dr) {
            ++pdr;
            bpx=x+bpx-bx;
            bpy=y+bpy-by;
            float num1 = px-bpx;
            float num2 = py-bpy;
            float num3;
            float num4;
            if(num1!=0.0) {
                float num5 = num2/num1;
                if(num5!=0.0) {
                    num3=(float)((y-(double)bpy+1.0/num5*x+num5*(double)bpx)/(num5+1.0/num5));
                    num4=bpy+num5*(num3-bpx);
                } else {
                    num3=x;
                    num4=py;
                }
                if(Math.abs(Math.abs(px-num3)+Math.abs(bpx-num3)-Math.abs(px-bpx))>0.0) {
                    num3=px;
                    num4=py;
                }
            } else if(num2!=0.0) {
                num3=px;
                num4=y;
                if(Math.abs(Math.abs(py-num4)+Math.abs(bpy-num4)-Math.abs(py-bpy))>0.0) {
                    num3=px;
                    num4=py;
                }
            } else {
                num3=px;
                num4=py;
            }
            dr=MathHelper.ToRadians(dr);
            double num6;
            if((double)num3-x!=0.0) {
                num6=Math.atan((num4-(double)y)/(num3-(double)x));
                if((double)num3-x<0.0) {
                    num6+=3.14159274101257;
                }
            } else {
                num6=num4-(double)y<=0.0 ? -1.57079637050629 : 1.57079637050629;
            }
            float num7 = (float)Math.sqrt((x-(double)num3)*(x-(double)num3)+(y-(double)num4)*(y-(double)num4));
            float num8 = x+num7*(float)Math.cos(num6-dr);
            float num9 = y+num7*(float)Math.sin(num6-dr);
            x=(float)(((double)x-num8)*(x-(double)num8));
            y=(float)(((double)y-num9)*(y-(double)num9));
            float num10 = (float)(pdr*(double)ws*pdr*ws);
            float num11 = (float)(pdr*(double)hs*pdr*hs);
            return (double)x/num11+y/(double)num10<=1.0;
        }
		}
