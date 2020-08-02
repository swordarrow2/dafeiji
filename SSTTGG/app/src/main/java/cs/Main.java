package cs;

 public class Main extends Game {
        public static int FPS = 0;
        public static String path = "";
        public static String registData = "";
        public static HashMap<String,Integer> conditions = new HashMap<String,Integer>();
        public static HashMap<String,Integer> results = new HashMap<String,Integer>();
        public static HashMap<String,Integer> type = new HashMap<String,Integer>();
        public static HashMap<String,Integer> conditions2 = new HashMap<String,Integer>();
        public static HashMap<String,Integer> results2 = new HashMap<String,Integer>();
        public static HashMap<String,Integer> results3 = new HashMap<String,Integer>();
        public static HashMap<String,Integer> cconditions = new HashMap<String,Integer>();
        public static HashMap<String,Integer> cresults = new HashMap<String,Integer>();
        public static HashMap<String,Integer> lconditions = new HashMap<String,Integer>();
        public static HashMap<String,Integer> lresults = new HashMap<String,Integer>();
        public static HashMap<String,Integer> lresults2 = new HashMap<String,Integer>();
        public static ArrayList<BarrageType> bgset = new ArrayList<BarrageType>();
        public static GraphicsDeviceManager graphics;
        public static GraphicsDevice gd;
        private SpriteBatch spriteBatch;
        public static Random rand;
        public static Vector2 display;
        public static boolean Selecting;
        public static int selects;
        public static String WindowTitle;
        public static Texture2D barrages;
        public static Texture2D barrages2;
        public static Texture2D mist;
        public static Texture2D player;
        public static boolean Missable;
        public static boolean Tip;
        public static String name;
        public static boolean Available;
        public Main() {
            graphics=new GraphicsDeviceManager(this) {
                PreferredBackBufferWidth=632,
                PreferredBackBufferHeight=482
            };
            WindowTitle="Crazy Storm 1.03I";
        }
        public static GraphicsDevice GetG() {
            return gd;
        }
        private static int Chaos_GetRandomSeed() {
            byte[] data = new byte[4];
            new RNGCryptoServiceProvider().GetBytes(data);
            return BitConverter.ToInt32(data,0);
        }
        protected override void Initialize() {
            Available=false;
            rand=new Random();
            new dataReader().Show();
            registData=Application.StartupPath+"\\";
            Missable=true;
            type.put("正比",0);
            type.put("固定",1);
            type.put("正弦",2);
            conditions.put("",0);
            conditions.put("当前帧",0);
            conditions.put("X坐标",1);
            conditions.put("Y坐标",2);
            conditions.put("半径",3);
            conditions.put("半径方向",4);
            conditions.put("条数",5);
            conditions.put("周期",6);
            conditions.put("角度",7);
            conditions.put("范围",8);
            conditions.put("宽比",9);
            conditions.put("高比",10);
            conditions.put("不透明度",11);
            conditions.put("朝向",12);
            results.put("X坐标",0);
            results.put("Y坐标",1);
            results.put("半径",2);
            results.put("半径方向",3);
            results.put("条数",4);
            results.put("周期",5);
            results.put("角度",6);
            results.put("范围",7);
            results.put("速度",8);
            results.put("速度方向",9);
            results.put("加速度",10);
            results.put("加速度方向",11);
            results.put("生命",12);
            results.put("类型",13);
            results.put("宽比",14);
            results.put("高比",15);
            results.put("R",16);
            results.put("G",17);
            results.put("B",18);
            results.put("不透明度",19);
            results.put("朝向",20);
            results.put("子弹速度",21);
            results.put("子弹速度方向",22);
            results.put("子弹加速度",23);
            results.put("子弹加速度方向",24);
            results.put("横比",25);
            results.put("纵比",26);
            results.put("雾化效果",27);
            results.put("消除效果",28);
            results.put("高光效果",29);
            results.put("拖影效果",30);
            results.put("出屏即消",31);
            results.put("无敌状态",32);
            conditions2.put("",0);
            conditions2.put("当前帧",0);
            conditions2.put("X坐标",1);
            conditions2.put("Y坐标",2);
            results2.put("生命",0);
            results2.put("类型",1);
            results2.put("宽比",2);
            results2.put("高比",3);
            results2.put("R",4);
            results2.put("G",5);
            results2.put("B",6);
            results2.put("不透明度",7);
            results2.put("朝向",8);
            results2.put("子弹速度",9);
            results2.put("子弹速度方向",10);
            results2.put("子弹加速度",11);
            results2.put("子弹加速度方向",12);
            results2.put("横比",13);
            results2.put("纵比",14);
            results2.put("雾化效果",15);
            results2.put("消除效果",16);
            results2.put("高光效果",17);
            results2.put("拖影效果",18);
            results2.put("出屏即消",19);
            results2.put("无敌状态",20);
            results3.put("速度",0);
            results3.put("速度方向",1);
            results3.put("加速度",2);
            results3.put("加速度方向",3);
            cconditions.put("",0);
            cconditions.put("当前帧",0);
            cconditions.put("X坐标",1);
            cconditions.put("Y坐标",2);
            cconditions.put("半宽",3);
            cconditions.put("半高",4);
            cresults.put("半宽",0);
            cresults.put("半高",1);
            cresults.put("启用圆形",2);
            cresults.put("速度",3);
            cresults.put("速度方向",4);
            cresults.put("加速度",5);
            cresults.put("加速度方向",6);
            cresults.put("类型",7);
            cresults.put("ID",8);
            cresults.put("X坐标",9);
            cresults.put("Y坐标",10);
            lconditions.put("",0);
            lconditions.put("当前帧",0);
            lconditions.put("半径",1);
            lconditions.put("半径方向",2);
            lconditions.put("条数",3);
            lconditions.put("周期",4);
            lconditions.put("角度",5);
            lconditions.put("范围",6);
            lconditions.put("宽比",7);
            lconditions.put("长度",8);
            lconditions.put("不透明度",9);
            lresults.put("半径",0);
            lresults.put("半径方向",1);
            lresults.put("条数",2);
            lresults.put("周期",3);
            lresults.put("角度",4);
            lresults.put("范围",5);
            lresults.put("速度",6);
            lresults.put("速度方向",7);
            lresults.put("加速度",8);
            lresults.put("加速度方向",9);
            lresults.put("生命",10);
            lresults.put("类型",11);
            lresults.put("宽比",12);
            lresults.put("长度",13);
            lresults.put("不透明度",14);
            lresults.put("子弹速度",15);
            lresults.put("子弹速度方向",16);
            lresults.put("子弹加速度",17);
            lresults.put("子弹加速度方向",18);
            lresults.put("横比",19);
            lresults.put("纵比",20);
            lresults.put("高光效果",21);
            lresults.put("出屏即消",22);
            lresults.put("无敌状态",23);
            lresults2.put("生命",0);
            lresults2.put("类型",1);
            lresults2.put("宽比",2);
            lresults2.put("长度",3);
            lresults2.put("不透明度",4);
            lresults2.put("子弹速度",5);
            lresults2.put("子弹速度方向",6);
            lresults2.put("子弹加速度",7);
            lresults2.put("子弹加速度方向",8);
            lresults2.put("横比",9);
            lresults2.put("纵比",10);
            lresults2.put("高光效果",11);
            lresults2.put("出屏即消",12);
            lresults2.put("无敌状态",13);
            base.Initialize();
        }
        protected override void LoadContent() {
            gd=GraphicsDevice;
            spriteBatch=new SpriteBatch(GraphicsDevice);
            barrages=Texture2D.FromFile(GraphicsDevice,Cry.Decry(registData+"Data/barrages.xna"));
            barrages2=Texture2D.FromFile(GraphicsDevice,registData+"Data/barrages.png");
            mist=Texture2D.FromFile(GraphicsDevice,Cry.Decry(registData+"Content/Graphics/mist.xna"));
            player=Texture2D.FromFile(GraphicsDevice,Cry.Decry(registData+"Content/Graphics/player.xna"));
            StreamReader streamReader1 = new StreamReader(Cry.Decry(registData+"Data/set.xna"));
            for(int index = 0;index<228;++index) {
                String str = streamReader1.ReadLine();
                BarrageType barrageType = new BarrageType {
                    rect=new Rectangle(Integer.parseInt(str.split("_")[1]),Integer.parseInt(str.split("_")[2]),Integer.parseInt(str.split("_")[3]),Integer.parseInt(str.split("_")[4])),
                    origin=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                    origin0=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                    pdr0=Integer.parseInt(str.split("_")[7])
                };
                if(str.split("_")[8]!="") {
                    barrageType.color=Integer.parseInt(str.split("_")[8]);
                } else {
                    barrageType.color=-1;
                }
                bgset.put(barrageType);
            }
            streamReader1.Close();
            StreamReader streamReader2 = new StreamReader(registData+"Data/set.txt");
            for(int index = 0;index<1000;++index) {
                String str = streamReader2.ReadLine();
                if(str!=null&str!="") {
                    BarrageType barrageType = new BarrageType {
                        name=str.split("_")[0],
                        rect=new Rectangle(Integer.parseInt(str.split("_")[1]),Integer.parseInt(str.split("_")[2]),Integer.parseInt(str.split("_")[3]),Integer.parseInt(str.split("_")[4])),
                        origin=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                        origin0=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                        pdr0=Integer.parseInt(str.split("_")[7])
                    };
                    if(str.split("_")[8]!="") {
                        barrageType.color=Integer.parseInt(str.split("_")[8]);
                    } else {
                        barrageType.color=-1;
                    }
                    bgset.put(barrageType);
                } else {
                    break;
                }
            }
            streamReader2.Close();
        }
        protected override void Update(GameTime gameTime) {
            //   updateAll();
            base.Update(gameTime);
        }
        public static void updateAll() {
            FPS++;
            rand=new Random(Chaos_GetRandomSeed());
            Time.Update();
            Th902Interface.bullets.clear();
            for(int index1 = 0;index1<Layer.LayerArray.size();++index1) {
                Th902Interface.bullets.putRange(Layer.LayerArray[index1].Barrages);
            }
            for(int index = 0;index<Layer.LayerArray.size();++index) {
                Layer.LayerArray[index].sort=index;
                Layer.LayerArray[index].Update();
            }
            Center.Update();
            Player.Update();
        }
        protected override void Draw(GameTime gameTime) {
            GraphicsDevice.Clear(Color.Black);
            spriteBatch.Begin();
            for(int index1 = 0;index1<Layer.LayerArray.size();++index1) {
                if(Layer.LayerArray[index1].Visible&!Time.Playing) {
                    for(int index2 = 0;index2<Layer.LayerArray[index1].ForceArray.size();++index2) {
                        if(!Layer.LayerArray[index1].ForceArray[index2].NeedDelete) {
                        } else {
                            Layer.LayerArray[index1].ForceArray.remove(index2);
                        }
                    }
                    for(int index2 = 0;index2<Layer.LayerArray[index1].ReboundArray.size();++index2) {
                        if(!Layer.LayerArray[index1].ReboundArray[index2].NeedDelete) {
                        } else {
                            Layer.LayerArray[index1].ReboundArray.remove(index2);
                        }
                    }
                    for(int index2 = 0;index2<Layer.LayerArray[index1].CoverArray.size();++index2) {
                        if(!Layer.LayerArray[index1].CoverArray[index2].NeedDelete) {
                        } else {
                            Layer.LayerArray[index1].CoverArray.remove(index2);
                        }
                    }
                    for(int index2 = 0;index2<Layer.LayerArray[index1].LaseArray.size();++index2) {
                        if(!Layer.LayerArray[index1].LaseArray[index2].NeedDelete) {
                        } else {
                            Layer.LayerArray[index1].LaseArray.remove(index2);
                        }
                    }
                    for(int index2 = 0;index2<Layer.LayerArray[index1].BatchArray.size();++index2) {
                        if(!Layer.LayerArray[index1].BatchArray[index2].NeedDelete) {
                        } else {
                            Layer.LayerArray[index1].BatchArray.remove(index2);
                        }
                    }
                }
                spriteBatch.End();
                spriteBatch.Begin(SpriteBlendMode.putitive);
                for(int index2 = 0;index2<Layer.LayerArray[index1].Barrages.size();++index2) {
                    if(Layer.LayerArray[index1].Barrages[index2].Blend) {
                        if(!Layer.LayerArray[index1].Barrages[index2].NeedDelete) {
                            Layer.LayerArray[index1].Barrages[index2].Draw(spriteBatch);
                            Layer.LayerArray[index1].Barrages[index2].LDraw(spriteBatch);
                        } else {
                            Layer.LayerArray[index1].Barrages.remove(index2);
                            --index2;
                        }
                    }
                }
                spriteBatch.End();
                spriteBatch.Begin();
                for(int index2 = 0;index2<Layer.LayerArray[index1].Barrages.size();++index2) {
                    if(!Layer.LayerArray[index1].Barrages[index2].Blend) {
                        if(!Layer.LayerArray[index1].Barrages[index2].NeedDelete) {
                            Layer.LayerArray[index1].Barrages[index2].Draw(spriteBatch);
                            Layer.LayerArray[index1].Barrages[index2].LDraw(spriteBatch);
                        } else {
                            Layer.LayerArray[index1].Barrages.remove(index2);
                            --index2;
                        }
                    }
                }
            }
            Player.Draw(spriteBatch);
            for(int index = 0;index<Layer.LayerArray.size();++index) {
                if(!Layer.LayerArray[index].NeedDelete) {
                } else {
                    Layer.LayerArray.remove(index);
                }
            }
            spriteBatch.End();
            base.Draw(gameTime);
        }
        public static String Cuts(String word,String num,int array) {
            char[] charArray = num.ToCharArray();
            return word.split(charArray)[array-1];
        }
        public static float Twopointangle(float x2,float y2,float x1,float y1) {
            float num = x2-(double)x1==0.0 ? (float)Math.atan((y2-(double)y1)/(x2-(double)x1+0.100000001490116)) : (float)Math.atan((y2-(double)y1)/(x2-(double)x1));
            if(x2-(double)x1<0.0) {
                num+=3.141593f;
            }
            return num;
        }
        private static float CrossMul(Vector2 pt1,Vector2 pt2) {
            return (float)((double)pt1.X*pt2.Y-pt1.Y*(double)pt2.X);
        }
        private static boolean CheckCrose(Line line1,Line line2) {
            Vector2 pt1_1 = new Vector2();
            Vector2 pt1_2 = new Vector2();
            Vector2 pt2 = new Vector2();
            pt1_1.X=line2.Start.X-line1.End.X;
            pt1_1.Y=line2.Start.Y-line1.End.Y;
            pt1_2.X=line2.End.X-line1.End.X;
            pt1_2.Y=line2.End.Y-line1.End.Y;
            pt2.X=line1.Start.X-line1.End.X;
            pt2.Y=line1.Start.Y-line1.End.Y;
            return (double)CrossMul(pt1_1,pt2)*CrossMul(pt1_2,pt2)<=0.0;
        }
        public static boolean CheckTwoLineCrose(Line line1,Line line2) {
            if(CheckCrose(line1,line2)) {
                return CheckCrose(line2,line1);
            }
            return false;
        }
        public SpriteBatch Getsb() {
            return spriteBatch;
        }
        public GraphicsDevice Getgd() {
            return GraphicsDevice;
        }
        public static void Open(String path) {
            StreamReader streamReader = new StreamReader(path);
            if(streamReader.ReadLine().equals("Crazy Storm Data 1.01") {
                Main.path=path;
                WindowTitle="Crazy Storm 1.03I - "+Main.path;
                Available=true;
                Layer.clear();
                Center.clear();
                Time.clear();
                GC.Collect();
                String source = streamReader.ReadLine();
                if(source.contains("Types")) {
                    int num = Integer.parseInt(source.split(" ")[0]);
                    for(int index = 0;index<num;++index) {
                        String str = streamReader.ReadLine();
                        BarrageType barrageType = new BarrageType {
                            name=str.split("_")[0],
                            rect=new Rectangle(Integer.parseInt(str.split("_")[1]),Integer.parseInt(str.split("_")[2]),Integer.parseInt(str.split("_")[3]),Integer.parseInt(str.split("_")[4])),
                            origin=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                            origin0=new Vector2(Integer.parseInt(str.split("_")[5]),Integer.parseInt(str.split("_")[6])),
                            pdr0=Integer.parseInt(str.split("_")[7])
                        };
                        if(str.split("_")[8]!="") {
                            barrageType.color=Integer.parseInt(str.split("_")[8]);
                        } else {
                            barrageType.color=-1;
                        }
                        bgset.put(barrageType);
                    }
                    source=streamReader.ReadLine();
                }
                if(source.contains("GlobalEvents")) {
                    int num1 = Integer.parseInt(source.split(" ")[0]);
                    for(int index = 0;index<num1;++index) {
                        String str = streamReader.ReadLine();
                        Time.GEcount.put(Integer.parseInt(str.split("_")[0])-1);
                        GlobalEvent globalEvent = new GlobalEvent {
                            gotocondition=Integer.parseInt(str.split("_")[1]),
                            gotoopreator=str.split("_")[2],
                            gotocvalue=Integer.parseInt(str.split("_")[3]),
                            isgoto=(bool.Parse(str.split("_")[4]) ? 1 : 0)!=0,
                            gototime=Integer.parseInt(str.split("_")[5]),
                            gotowhere=Integer.parseInt(str.split("_")[6]),
                            quakecondition=Integer.parseInt(str.split("_")[7]),
                            quakeopreator=str.split("_")[8],
                            quakecvalue=Integer.parseInt(str.split("_")[9]),
                            isquake=(bool.Parse(str.split("_")[10]) ? 1 : 0)!=0,
                            quaketime=Integer.parseInt(str.split("_")[11]),
                            quakelevel=Integer.parseInt(str.split("_")[12]),
                            stopcondition=Integer.parseInt(str.split("_")[13]),
                            stopopreator=str.split("_")[14],
                            stopcvalue=Integer.parseInt(str.split("_")[15]),
                            isstop=(bool.Parse(str.split("_")[16]) ? 1 : 0)!=0,
                            stoptime=Integer.parseInt(str.split("_")[17]),
                            stoplevel=Integer.parseInt(str.split("_")[18])
                        };
                        if(Time.GE.size()<Integer.parseInt(str.split("_")[0])) {
                            int num2 = 0;
                            while(true) {
                                if(num2<Integer.parseInt(str.split("_")[0])) {
                                    Time.GE.put(new GlobalEvent() {
                                        gotocondition=-1,
                                        quakecondition=-1,
                                        stopcondition=-1,
                                        stoplevel=-1
                                    });
                                    ++num2;
                                } else {
                                    break;
                                }
                            }
                        }
                        Time.GE[Integer.parseInt(str.split("_")[0])-1]=globalEvent;
                    }
                    source=streamReader.ReadLine();
                }
                if(source.contains("Sounds")) {
                    int num = Integer.parseInt(source.split(" ")[0]);
                    for(int index = 0;index<num;++index) {
                        streamReader.ReadLine();
                    }
                    source=streamReader.ReadLine();
                }
                if(source.contains(",")) {
                    Center.Available=true;
                    Center.x=Float.parseFloat(source.split(":")[1].split(",")[0]);
                    Center.y=Float.parseFloat(source.split(":")[1].split(",")[1]);
                    if(source.split(":")[1].split(",").Length>=7) {
                        Center.speed=Float.parseFloat(source.split(":")[1].split(",")[2]);
                        Center.speedd=Float.parseFloat(source.split(":")[1].split(",")[3]);
                        Center.aspeed=Float.parseFloat(source.split(":")[1].split(",")[4]);
                        Center.aspeedd=Float.parseFloat(source.split(":")[1].split(",")[5]);
                        int index = 0;
                        while(true) {
                            if(index<source.split(":")[1].split(",")[6].split(";").Length-1) {
                                Center.events.put(source.split(":")[1].split(",")[6].split(";")[index]);
                                ++index;
                            } else
                                break;
                        }
                    }
                } else {
                    Center.Available=false;
                }
                Time.total=Integer.parseInt(streamReader.ReadLine().split(":")[1]);
                for(int index1 = 0;index1<4;++index1) {
                    String str1 = streamReader.ReadLine();
                    if(str1.split(":")[1].split(",")[0]!="empty") {
                        Layer layer = new Layer(str1.split(":")[1].split(",")[0],Integer.parseInt(str1.split(":")[1].split(",")[1]),Integer.parseInt(str1.split(":")[1].split(",")[2]));
                        int num1 = Integer.parseInt(str1.split(":")[1].split(",")[3]);
                        for(int index2 = 0;index2<num1;++index2) {
                            String str2 = streamReader.ReadLine();
                            Batch batch = new Batch(Float.parseFloat(str2.split(",")[6]),Float.parseFloat(str2.split(",")[7]),Layer.LayerArray[Layer.LayerArray.size()-Layer.selection-1].color) {
                                id=Integer.parseInt(str2.split(",")[0]),
                                parentid=Integer.parseInt(str2.split(",")[1]),
                                Binding=(bool.Parse(str2.split(",")[2]) ? 1 : 0)!=0,
                                bindid=Integer.parseInt(str2.split(",")[3]),
                                Bindwithspeedd=(bool.Parse(str2.split(",")[4]) ? 1 : 0)!=0,
                                begin=Integer.parseInt(str2.split(",")[8]),
                                life=Integer.parseInt(str2.split(",")[9]),
                                fx=Float.parseFloat(str2.split(",")[10]),
                                fy=Float.parseFloat(str2.split(",")[11]),
                                r=Integer.parseInt(str2.split(",")[12]),
                                rdirection=Float.parseFloat(str2.split(",")[13])
                            };
                            String str3 = str2.split(",")[14].Replace("{","").Replace("}","");
                            batch.rdirections.X=Float.parseFloat(str3.split(" ")[0].split(":")[1]);
                            batch.rdirections.Y=Float.parseFloat(str3.split(" ")[1].split(":")[1]);
                            batch.tiao=Integer.parseInt(str2.split(",")[15]);
                            batch.t=Integer.parseInt(str2.split(",")[16]);
                            batch.fdirection=Float.parseFloat(str2.split(",")[17]);
                            String str4 = str2.split(",")[18].Replace("{","").Replace("}","");
                            batch.fdirections.X=Float.parseFloat(str4.split(" ")[0].split(":")[1]);
                            batch.fdirections.Y=Float.parseFloat(str4.split(" ")[1].split(":")[1]);
                            batch.range=Integer.parseInt(str2.split(",")[19]);
                            batch.speed=Float.parseFloat(str2.split(",")[20]);
                            batch.speedd=Float.parseFloat(str2.split(",")[21]);
                            String str5 = str2.split(",")[22].Replace("{","").Replace("}","");
                            batch.speedds.X=Float.parseFloat(str5.split(" ")[0].split(":")[1]);
                            batch.speedds.Y=Float.parseFloat(str5.split(" ")[1].split(":")[1]);
                            batch.aspeed=Float.parseFloat(str2.split(",")[23]);
                            batch.aspeedd=Float.parseFloat(str2.split(",")[24]);
                            String str6 = str2.split(",")[25].Replace("{","").Replace("}","");
                            batch.aspeedds.X=Float.parseFloat(str6.split(" ")[0].split(":")[1]);
                            batch.aspeedds.Y=Float.parseFloat(str6.split(" ")[1].split(":")[1]);
                            batch.sonlife=Integer.parseInt(str2.split(",")[26]);
                            batch.type=(float)Integer.parseInt(str2.split(",")[27]);
                            batch.wscale=Float.parseFloat(str2.split(",")[28]);
                            batch.hscale=Float.parseFloat(str2.split(",")[29]);
                            batch.colorR=(float)Integer.parseInt(str2.split(",")[30]);
                            batch.colorG=(float)Integer.parseInt(str2.split(",")[31]);
                            batch.colorB=(float)Integer.parseInt(str2.split(",")[32]);
                            batch.alpha=(float)Integer.parseInt(str2.split(",")[33]);
                            batch.head=Float.parseFloat(str2.split(",")[34]);
                            String str7 = str2.split(",")[35].Replace("{","").Replace("}","");
                            batch.heads.X=Float.parseFloat(str7.split(" ")[0].split(":")[1]);
                            batch.heads.Y=Float.parseFloat(str7.split(" ")[1].split(":")[1]);
                            batch.Withspeedd=(bool.Parse(str2.split(",")[36]) ? 1 : 0)!=0;
                            batch.sonspeed=Float.parseFloat(str2.split(",")[37]);
                            batch.sonspeedd=Float.parseFloat(str2.split(",")[38]);
                            String str8 = str2.split(",")[39].Replace("{","").Replace("}","");
                            batch.sonspeedds.X=Float.parseFloat(str8.split(" ")[0].split(":")[1]);
                            batch.sonspeedds.Y=Float.parseFloat(str8.split(" ")[1].split(":")[1]);
                            batch.sonaspeed=Float.parseFloat(str2.split(",")[40]);
                            batch.sonaspeedd=Float.parseFloat(str2.split(",")[41]);
                            String str9 = str2.split(",")[42].Replace("{","").Replace("}","");
                            batch.sonaspeedds.X=Float.parseFloat(str9.split(" ")[0].split(":")[1]);
                            batch.sonaspeedds.Y=Float.parseFloat(str9.split(" ")[1].split(":")[1]);
                            batch.xscale=Float.parseFloat(str2.split(",")[43]);
                            batch.yscale=Float.parseFloat(str2.split(",")[44]);
                            batch.Mist=(bool.Parse(str2.split(",")[45]) ? 1 : 0)!=0;
                            batch.Dispel=(bool.Parse(str2.split(",")[46]) ? 1 : 0)!=0;
                            batch.Blend=(bool.Parse(str2.split(",")[47]) ? 1 : 0)!=0;
                            batch.Afterimage=(bool.Parse(str2.split(",")[48]) ? 1 : 0)!=0;
                            batch.Outdispel=(bool.Parse(str2.split(",")[49]) ? 1 : 0)!=0;
                            batch.Invincible=(bool.Parse(str2.split(",")[50]) ? 1 : 0)!=0;
                            String str10 = str2.split(",")[51];
                            int idx1 = 0;
                            while(true) {
                                if(idx1<str10.split("&").Length-1) {
                                    String str11 = str10.split("&")[idx1];
                                    Event _event = new Event(idx1) {
                                        tag=str11.split("|")[0],
                                        t=Integer.parseInt(str11.split("|")[1]),
                                        addtime=Integer.parseInt(str11.split("|")[2])
                                    };
                                    int index3 = 0;
                                    while(true) {
                                        if(index3<str11.split("|")[3].split(";").Length-1) {
                                            _event.events.put(str11.split("|")[3].split(";")[index3]);
                                            ++index3;
                                        } else
                                            break;
                                    }
                                    batch.Parentevents.put(_event);
                                    ++idx1;
                                } else
                                    break;
                            }
                            String str12 = str2.split(",")[52];
                            int idx2 = 0;
                            while(true) {
                                if(idx2<str12.split("&").Length-1) {
                                    String str11 = str12.split("&")[idx2];
                                    Event _event = new Event(idx2) {
                                        tag=str11.split("|")[0],
                                        t=Integer.parseInt(str11.split("|")[1]),
                                        addtime=Integer.parseInt(str11.split("|")[2])
                                    };
                                    int index3 = 0;
                                    while(true) {
                                        if(index3<str11.split("|")[3].split(";").Length-1) {
                                            _event.events.put(str11.split("|")[3].split(";")[index3]);
                                            ++index3;
                                        } else
                                            break;
                                    }
                                    batch.Sonevents.put(_event);
                                    ++idx2;
                                } else
                                    break;
                            }
                            batch.rand.fx=Float.parseFloat(str2.split(",")[53]);
                            batch.rand.fy=Float.parseFloat(str2.split(",")[54]);
                            batch.rand.r=(float)Integer.parseInt(str2.split(",")[55]);
                            batch.rand.rdirection=Float.parseFloat(str2.split(",")[56]);
                            batch.rand.tiao=Integer.parseInt(str2.split(",")[57]);
                            batch.rand.t=Integer.parseInt(str2.split(",")[58]);
                            batch.rand.fdirection=Float.parseFloat(str2.split(",")[59]);
                            batch.rand.range=Integer.parseInt(str2.split(",")[60]);
                            batch.rand.speed=Float.parseFloat(str2.split(",")[61]);
                            batch.rand.speedd=Float.parseFloat(str2.split(",")[62]);
                            batch.rand.aspeed=Float.parseFloat(str2.split(",")[63]);
                            batch.rand.aspeedd=Float.parseFloat(str2.split(",")[64]);
                            batch.rand.head=Float.parseFloat(str2.split(",")[65]);
                            batch.rand.sonspeed=Float.parseFloat(str2.split(",")[66]);
                            batch.rand.sonspeedd=Float.parseFloat(str2.split(",")[67]);
                            batch.rand.sonaspeed=Float.parseFloat(str2.split(",")[68]);
                            batch.rand.sonaspeedd=Float.parseFloat(str2.split(",")[69]);
                            if(str2.split(",").Length>=72) {
                                batch.Cover=(bool.Parse(str2.split(",")[70]) ? 1 : 0)!=0;
                                batch.Rebound=(bool.Parse(str2.split(",")[71]) ? 1 : 0)!=0;
                                batch.Force=(bool.Parse(str2.split(",")[72]) ? 1 : 0)!=0;
                            }
                            if(str2.split(",").Length>=74)
                                batch.Deepbind=(bool.Parse(str2.split(",")[73]) ? 1 : 0)!=0;
                            Layer.LayerArray[index1].BatchArray.put(batch);
                        }
                        if(str1.split(":")[1].split(",").Length>=7) {
                            int num2 = Integer.parseInt(str1.split(":")[1].split(",")[4]);
                            for(int index2 = 0;index2<num2;++index2) {
                                String str2 = streamReader.ReadLine();
                                Lase lase = new Lase(Float.parseFloat(str2.split(",")[6]),Float.parseFloat(str2.split(",")[7]),Layer.LayerArray[Layer.LayerArray.size()-Layer.selection-1].color) {
                                    id=Integer.parseInt(str2.split(",")[0]),
                                    parentid=Integer.parseInt(str2.split(",")[1]),
                                    Binding=(bool.Parse(str2.split(",")[2]) ? 1 : 0)!=0,
                                    bindid=Integer.parseInt(str2.split(",")[3]),
                                    Bindwithspeedd=(bool.Parse(str2.split(",")[4]) ? 1 : 0)!=0,
                                    begin=Integer.parseInt(str2.split(",")[8]),
                                    life=Integer.parseInt(str2.split(",")[9]),
                                    r=(float)Integer.parseInt(str2.split(",")[10]),
                                    rdirection=Float.parseFloat(str2.split(",")[11])
                                };
                                String str3 = str2.split(",")[12].Replace("{","").Replace("}","");
                                lase.rdirections.X=Float.parseFloat(str3.split(" ")[0].split(":")[1]);
                                lase.rdirections.Y=Float.parseFloat(str3.split(" ")[1].split(":")[1]);
                                lase.tiao=Integer.parseInt(str2.split(",")[13]);
                                lase.t=Integer.parseInt(str2.split(",")[14]);
                                lase.fdirection=Float.parseFloat(str2.split(",")[15]);
                                String str4 = str2.split(",")[16].Replace("{","").Replace("}","");
                                lase.fdirections.X=Float.parseFloat(str4.split(" ")[0].split(":")[1]);
                                lase.fdirections.Y=Float.parseFloat(str4.split(" ")[1].split(":")[1]);
                                lase.range=Integer.parseInt(str2.split(",")[17]);
                                lase.speed=Float.parseFloat(str2.split(",")[18]);
                                lase.speedd=Float.parseFloat(str2.split(",")[19]);
                                String str5 = str2.split(",")[20].Replace("{","").Replace("}","");
                                lase.speedds.X=Float.parseFloat(str5.split(" ")[0].split(":")[1]);
                                lase.speedds.Y=Float.parseFloat(str5.split(" ")[1].split(":")[1]);
                                lase.aspeed=Float.parseFloat(str2.split(",")[21]);
                                lase.aspeedd=Float.parseFloat(str2.split(",")[22]);
                                String str6 = str2.split(",")[23].Replace("{","").Replace("}","");
                                lase.aspeedds.X=Float.parseFloat(str6.split(" ")[0].split(":")[1]);
                                lase.aspeedds.Y=Float.parseFloat(str6.split(" ")[1].split(":")[1]);
                                lase.sonlife=Integer.parseInt(str2.split(",")[24]);
                                lase.type=(float)Integer.parseInt(str2.split(",")[25]);
                                lase.wscale=Float.parseFloat(str2.split(",")[26]);
                                lase.longs=Float.parseFloat(str2.split(",")[27]);
                                lase.alpha=(float)Integer.parseInt(str2.split(",")[28]);
                                lase.Ray=(bool.Parse(str2.split(",")[29]) ? 1 : 0)!=0;
                                lase.sonspeed=Float.parseFloat(str2.split(",")[30]);
                                lase.sonspeedd=Float.parseFloat(str2.split(",")[31]);
                                String str7 = str2.split(",")[32].Replace("{","").Replace("}","");
                                lase.sonspeedds.X=Float.parseFloat(str7.split(" ")[0].split(":")[1]);
                                lase.sonspeedds.Y=Float.parseFloat(str7.split(" ")[1].split(":")[1]);
                                lase.sonaspeed=Float.parseFloat(str2.split(",")[33]);
                                lase.sonaspeedd=Float.parseFloat(str2.split(",")[34]);
                                String str8 = str2.split(",")[35].Replace("{","").Replace("}","");
                                lase.sonaspeedds.X=Float.parseFloat(str8.split(" ")[0].split(":")[1]);
                                lase.sonaspeedds.Y=Float.parseFloat(str8.split(" ")[1].split(":")[1]);
                                lase.xscale=Float.parseFloat(str2.split(",")[36]);
                                lase.yscale=Float.parseFloat(str2.split(",")[37]);
                                lase.Blend=(bool.Parse(str2.split(",")[38]) ? 1 : 0)!=0;
                                lase.Outdispel=(bool.Parse(str2.split(",")[39]) ? 1 : 0)!=0;
                                lase.Invincible=(bool.Parse(str2.split(",")[40]) ? 1 : 0)!=0;
                                String str9 = str2.split(",")[42];
                                int idx1 = 0;
                                while(true) {
                                    if(idx1<str9.split("&").Length-1) {
                                        String str10 = str9.split("&")[idx1];
                                        Event _event = new Event(idx1) {
                                            tag=str10.split("|")[0],
                                            t=Integer.parseInt(str10.split("|")[1]),
                                            addtime=Integer.parseInt(str10.split("|")[2])
                                        };
                                        int index3 = 0;
                                        while(true) {
                                            if(index3<str10.split("|")[3].split(";").Length-1) {
                                                _event.events.put(str10.split("|")[3].split(";")[index3]);
                                                ++index3;
                                            } else
                                                break;
                                        }
                                        lase.Parentevents.put(_event);
                                        ++idx1;
                                    } else
                                        break;
                                }
                                String str11 = str2.split(",")[43];
                                int idx2 = 0;
                                while(true) {
                                    if(idx2<str11.split("&").Length-1) {
                                        String str10 = str11.split("&")[idx2];
                                        Event _event = new Event(idx2) {
                                            tag=str10.split("|")[0],
                                            t=Integer.parseInt(str10.split("|")[1]),
                                            addtime=Integer.parseInt(str10.split("|")[2])
                                        };
                                        int index3 = 0;
                                        while(true) {
                                            if(index3<str10.split("|")[3].split(";").Length-1) {
                                                _event.events.put(str10.split("|")[3].split(";")[index3]);
                                                ++index3;
                                            } else
                                                break;
                                        }
                                        lase.Sonevents.put(_event);
                                        ++idx2;
                                    } else
                                        break;
                                }
                                lase.rand.r=(float)Integer.parseInt(str2.split(",")[44]);
                                lase.rand.rdirection=Float.parseFloat(str2.split(",")[45]);
                                lase.rand.tiao=Integer.parseInt(str2.split(",")[46]);
                                lase.rand.t=Integer.parseInt(str2.split(",")[47]);
                                lase.rand.fdirection=Float.parseFloat(str2.split(",")[48]);
                                lase.rand.range=Integer.parseInt(str2.split(",")[49]);
                                lase.rand.speed=Float.parseFloat(str2.split(",")[50]);
                                lase.rand.speedd=Float.parseFloat(str2.split(",")[51]);
                                lase.rand.aspeed=Float.parseFloat(str2.split(",")[52]);
                                lase.rand.aspeedd=Float.parseFloat(str2.split(",")[53]);
                                lase.rand.sonspeed=Float.parseFloat(str2.split(",")[54]);
                                lase.rand.sonspeedd=Float.parseFloat(str2.split(",")[55]);
                                lase.rand.sonaspeed=Float.parseFloat(str2.split(",")[56]);
                                lase.rand.sonaspeedd=Float.parseFloat(str2.split(",")[57]);
                                if(str2.split(",").Length>=59)
                                    lase.Deepbind=(bool.Parse(str2.split(",")[58]) ? 1 : 0)!=0;
                                Layer.LayerArray[index1].LaseArray.put(lase);
                            }
                            int num3 = Integer.parseInt(str1.split(":")[1].split(",")[5]);
                            for(int index2 = 0;index2<num3;++index2) {
                                String str2 = streamReader.ReadLine();
                                Cover cover = new Cover(Float.parseFloat(str2.split(",")[2]),Float.parseFloat(str2.split(",")[3]),Layer.LayerArray[Layer.LayerArray.size()-Layer.selection-1].color) {
                                    id=Integer.parseInt(str2.split(",")[0]),
                                    parentid=Integer.parseInt(str2.split(",")[1]),
                                    begin=Integer.parseInt(str2.split(",")[4]),
                                    life=Integer.parseInt(str2.split(",")[5]),
                                    halfw=Integer.parseInt(str2.split(",")[6]),
                                    halfh=Integer.parseInt(str2.split(",")[7]),
                                    Circle=(bool.Parse(str2.split(",")[8]) ? 1 : 0)!=0,
                                    type=Integer.parseInt(str2.split(",")[9]),
                                    controlid=Integer.parseInt(str2.split(",")[10]),
                                    speed=Float.parseFloat(str2.split(",")[11]),
                                    speedd=Float.parseFloat(str2.split(",")[12])
                                };
                                String str3 = str2.split(",")[13].Replace("{","").Replace("}","");
                                cover.speedds.X=Float.parseFloat(str3.split(" ")[0].split(":")[1]);
                                cover.speedds.Y=Float.parseFloat(str3.split(" ")[1].split(":")[1]);
                                cover.aspeed=Float.parseFloat(str2.split(",")[14]);
                                cover.aspeedd=Float.parseFloat(str2.split(",")[15]);
                                String str4 = str2.split(",")[16].Replace("{","").Replace("}","");
                                cover.aspeedds.X=Float.parseFloat(str4.split(" ")[0].split(":")[1]);
                                cover.aspeedds.Y=Float.parseFloat(str4.split(" ")[1].split(":")[1]);
                                String str5 = str2.split(",")[17];
                                int idx1 = 0;
                                while(true) {
                                    if(idx1<str5.split("&").Length-1) {
                                        String str6 = str5.split("&")[idx1];
                                        Event _event = new Event(idx1) {
                                            tag=str6.split("|")[0],
                                            t=Integer.parseInt(str6.split("|")[1]),
                                            addtime=Integer.parseInt(str6.split("|")[2])
                                        };
                                        int index3 = 0;
                                        while(true) {
                                            if(index3<str6.split("|")[3].split(";").Length-1) {
                                                _event.events.put(str6.split("|")[3].split(";")[index3]);
                                                ++index3;
                                            } else
                                                break;
                                        }
                                        cover.Parentevents.put(_event);
                                        ++idx1;
                                    } else
                                        break;
                                }
                                String str7 = str2.split(",")[18];
                                int idx2 = 0;
                                while(true) {
                                    if(idx2<str7.split("&").Length-1) {
                                        String str6 = str7.split("&")[idx2];
                                        Event _event = new Event(idx2) {
                                            tag=str6.split("|")[0],
                                            t=Integer.parseInt(str6.split("|")[1]),
                                            addtime=Integer.parseInt(str6.split("|")[2])
                                        };
                                        int index3 = 0;
                                        while(true) {
                                            if(index3<str6.split("|")[3].split(";").Length-1) {
                                                _event.events.put(str6.split("|")[3].split(";")[index3]);
                                                ++index3;
                                            } else
                                                break;
                                        }
                                        cover.Sonevents.put(_event);
                                        ++idx2;
                                    } else
                                        break;
                                }
                                cover.rand.speed=Float.parseFloat(str2.split(",")[19]);
                                cover.rand.speedd=Float.parseFloat(str2.split(",")[20]);
                                cover.rand.aspeed=Float.parseFloat(str2.split(",")[21]);
                                cover.rand.aspeedd=Float.parseFloat(str2.split(",")[22]);
                                if(str2.split(",").Length>=24)
                                    cover.bindid=Integer.parseInt(str2.split(",")[23]);
                                if(str2.split(",").Length>=25) {
                                    if(str2.split(",")[24]!="")
                                        cover.Deepbind=(bool.Parse(str2.split(",")[24]) ? 1 : 0)!=0;
                                }
                                Layer.LayerArray[index1].CoverArray.put(cover);
                            }
                            int num4 = Integer.parseInt(str1.split(":")[1].split(",")[6]);
                            for(int index2 = 0;index2<num4;++index2) {
                                String str2 = streamReader.ReadLine();
                                Rebound rebound = new Rebound(Float.parseFloat(str2.split(",")[2]),Float.parseFloat(str2.split(",")[3]),Layer.LayerArray[Layer.LayerArray.size()-Layer.selection-1].color) {
                                    id=Integer.parseInt(str2.split(",")[0]),
                                    parentid=Integer.parseInt(str2.split(",")[1]),
                                    begin=Integer.parseInt(str2.split(",")[4]),
                                    life=Integer.parseInt(str2.split(",")[5]),
                                    longs=Integer.parseInt(str2.split(",")[6]),
                                    angle=(float)Integer.parseInt(str2.split(",")[7]),
                                    time=Integer.parseInt(str2.split(",")[8]),
                                    speed=Float.parseFloat(str2.split(",")[9]),
                                    speedd=Float.parseFloat(str2.split(",")[10]),
                                    aspeed=Float.parseFloat(str2.split(",")[11]),
                                    aspeedd=Float.parseFloat(str2.split(",")[12])
                                };
                                String str3 = str2.split(",")[13];
                                int idx = 0;
                                while(true) {
                                    if(idx<str3.split("&").Length-1) {
                                        String str4 = str3.split("&")[idx];
                                        rebound.Parentevents.put(new Event(idx) {
                                            tag=str4
                                        });
                                        ++idx;
                                    } else
                                        break;
                                }
                                rebound.rand.speed=Float.parseFloat(str2.split(",")[14]);
                                rebound.rand.speedd=Float.parseFloat(str2.split(",")[15]);
                                rebound.rand.aspeed=Float.parseFloat(str2.split(",")[16]);
                                rebound.rand.aspeedd=Float.parseFloat(str2.split(",")[17]);
                                Layer.LayerArray[index1].ReboundArray.put(rebound);
                            }
                            int num5 = Integer.parseInt(str1.split(":")[1].split(",")[7]);
                            for(int index2 = 0;index2<num5;++index2) {
                                String str2 = streamReader.ReadLine();
                                Layer.LayerArray[index1].ForceArray.put(new Force(Float.parseFloat(str2.split(",")[2]),Float.parseFloat(str2.split(",")[3]),Layer.LayerArray[Layer.LayerArray.size()-Layer.selection-1].color) {
                                    id=Integer.parseInt(str2.split(",")[0]),
                                    parentid=Integer.parseInt(str2.split(",")[1]),
                                    begin=Integer.parseInt(str2.split(",")[4]),
                                    life=Integer.parseInt(str2.split(",")[5]),
                                    halfw=Integer.parseInt(str2.split(",")[6]),
                                    halfh=Integer.parseInt(str2.split(",")[7]),
                                    Circle=(bool.Parse(str2.split(",")[8]) ? 1 : 0)!=0,
                                    type=Integer.parseInt(str2.split(",")[9]),
                                    controlid=Integer.parseInt(str2.split(",")[10]),
                                    speed=Float.parseFloat(str2.split(",")[11]),
                                    speedd=Float.parseFloat(str2.split(",")[12]),
                                    aspeed=Float.parseFloat(str2.split(",")[13]),
                                    aspeedd=Float.parseFloat(str2.split(",")[14]),
                                    addaspeed=Float.parseFloat(str2.split(",")[15]),
                                    addaspeedd=Float.parseFloat(str2.split(",")[16]),
                                    Suction=(bool.Parse(str2.split(",")[17]) ? 1 : 0)!=0,
                                    Repulsion=(bool.Parse(str2.split(",")[18]) ? 1 : 0)!=0,
                                    addspeed=Float.parseFloat(str2.split(",")[19]),
                                    rand= {
                    speed = Float.parseFloat(str2.split(",")[20]),
                    speedd = Float.parseFloat(str2.split(",")[21]),
                    aspeed = Float.parseFloat(str2.split(",")[22]),
                    aspeedd = Float.parseFloat(str2.split(",")[23])
                  }
                                });
                            }
                        }
                    }
                }
            } else {
                System.Windows.Forms.MessageBox.Show("当前暂不支持除1.01以外的数据格式");
            }
            streamReader.Close();
        }
		}
