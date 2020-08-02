package cs;

public class Center {
        public static float ox = 315f;
        public static float oy = 240f;
        public static float ospeed = 0.0f;
        public static float ospeedd = 0.0f;
        public static float oaspeed = 0.0f;
        public static float oaspeedd = 0.0f;
        public static float speedx = 0.0f;
        public static float speedy = 0.0f;
        public static float aspeedx = 0.0f;
        public static float aspeedy = 0.0f;
        public static float x = 315f;
        public static float y = 240f;
        public static float speed = 0.0f;
        public static float speedd = 0.0f;
        public static float aspeed = 0.0f;
        public static float aspeedd = 0.0f;
        public static ArrayList<String> events = new ArrayList<String>();
        public static boolean Available = true;
        public static boolean Aim = false;
        public static ArrayList<Event> Events = new ArrayList<Event>();
        public static ArrayList<CExecution> Eventsexe = new ArrayList<CExecution>();
        public static void Clear() {
            x=315f;
            y=240f;
            speed=0.0f;
            speedd=0.0f;
            aspeed=0.0f;
            aspeedd=0.0f;
            ox=x;
            oy=y;
            ospeed=speed;
            ospeedd=speedd;
            oaspeed=aspeed;
            oaspeedd=aspeedd;
            speedx=0.0f;
            speedy=0.0f;
            aspeedx=0.0f;
            aspeedy=0.0f;
            events=new ArrayList<String>();
            Available=true;
        }
        public static void Update() {
            speedx+=aspeedx;
            speedy+=aspeedy;
            ox+=speedx;
            oy+=speedy;
            HashMap<String,Integer> hashtable1 = new HashMap<String,Integer>();
            HashMap<String,Integer> hashtable2 = new HashMap<String,Integer>();
            hashtable1.put("当前帧",Time.now);
            hashtable2.put("速度",ospeed);
            hashtable2.put("速度方向",ospeedd);
            hashtable2.put("加速度",oaspeed);
            hashtable2.put("加速度方向",oaspeedd);
            for(String str1 : events) {
                if(!str1.contains("PlayMusic")&&!str1.contains("UseKira")&&!str1.contains("BanSound")) {
                    String s = str1.split("：")[0];
                    String str2 = "";
                    String str3 = "";
                    String str4 = str1.split("：")[1];
                    int num1 = 0;
                    String str5 = "";
                    int num2 = 0;
                    String str6 = "";
                    float num3 = 0.0f;
                    int num4 = 0;
                    if(s.contains("=")) {
                        str2=s.split("=")[0];
                        str3="=";
                        s=s.split("=")[1];
                    }
                    if(str3.equals("="&&(double)Float.parseFloat(hashtable1[str2].ToString())==Float.parseFloat(s)) {
                        if(str1.contains("变化到")) {
                            num1=0;
                            String[] strArray = str4.split("变化到)[3].split("，);
                            num2=(int)Main.results3[str4.split("变化到)[0]];
                            str6=str4.split("变化到)[0];
                            if(strArray[0].contains("+")) {
                                num3=(float)(Float.parseFloat(strArray[0].split("+")[0])+(double)MathHelper.Lerp(-Float.parseFloat(strArray[0].split("+")[1]),Float.parseFloat(strArray[0].split("+")[1]),(float)Main.rand.NextDouble()));
                            } else {
                                num3=num2==1||num2==3 ? (!strArray[0].contains("自机") ? Float.parseFloat(strArray[0]) : MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,ox,oy))) : Float.parseFloat(strArray[0]);
                            }
                            str5=strArray[1];
                            num4=Integer.parseInt(strArray[2].split("帧)[0]);
                        } else if(str1.contains("增加")) {
                            num1=1;
                            String[] strArray = str4.split("增)[1].split("，);
                            strArray[0]=strArray[0].Replace("加","");
                            num2=(int)Main.results3[str4.split("增)[0]];
                            str6=str4.split("增)[0];
                            if(strArray[0].contains("+")) {
                                num3=(float)(Float.parseFloat(strArray[0].split("+")[0])+(double)MathHelper.Lerp(-Float.parseFloat(strArray[0].split("+")[1]),Float.parseFloat(strArray[0].split("+")[1]),(float)Main.rand.NextDouble()));
                            } else {
                                num3=num2==1||num2==3 ? (!strArray[0].contains("自机") ? Float.parseFloat(strArray[0]) : MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,ox,oy))) : Float.parseFloat(strArray[0]);
                            }
                            str5=strArray[1];
                            num4=Integer.parseInt(strArray[2].split("帧)[0]);
                        } else if(str1.contains("减少")) {
                            num1=2;
                            String[] strArray = str4.split("减少)[2].split("，);
                            num2=(int)Main.results3[str4.split("减少)[0]];
                            str6=str4.split("减少)[0];
                            if(strArray[0].contains("+")) {
                                num3=(float)(Float.parseFloat(strArray[0].split("+")[0])+(double)MathHelper.Lerp(-Float.parseFloat(strArray[0].split("+")[1]),Float.parseFloat(strArray[0].split("+")[1]),(float)Main.rand.NextDouble()));
                            } else {
                                num3=num2==1||num2==3 ? (!strArray[0].contains("自机") ? Float.parseFloat(strArray[0]) : MathHelper.ToDegrees(Main.Twopointangle(Player.position.x,Player.position.y,ox,oy))) : Float.parseFloat(strArray[0]);
                            }
                            str5=strArray[1];
                            num4=Integer.parseInt(strArray[2].split("帧)[0]);
                        }
                        if(str1.contains("跟随自机")) {
                            Eventsexe.put(new CExecution() {
                                changetype=3,
                                ctime=60
                            });
                        } else if(str1.contains("范围移动")) {
                            Eventsexe.put(new CExecution() {
                                changetype=4,
                                ctime=60,
                                value=MathHelper.Lerp(Float.parseFloat(str1.split("，")[1]),Float.parseFloat(str1.split("，")[2]),(float)Main.rand.NextDouble()),
                                value2=MathHelper.Lerp(Float.parseFloat(str1.split("，")[3]),Float.parseFloat(str1.split("，")[4]),(float)Main.rand.NextDouble())
                            });
                        } else {
                            CExecution cexecution = new CExecution() {
                                change=num1,
                                changetype=(int)Main.type[str5],
                                changevalue=num2,
                                value=num3,
                                region=hashtable2[str6].ToString(),
                                time=num4
                            };
                            cexecution.ctime=cexecution.time;
                            Eventsexe.put(cexecution);
                        }
                    }
                }
            }
            for(int index = 0;index<Eventsexe.size();++index) {
                if(!Eventsexe[index].NeedDelete) {
                    Eventsexe[index].Update();
                } else {
                    Eventsexe.remove(index);
                    --index;
                }
            }
        }
		}
