package com.meng.stg.backup.mbgparser.item;

import com.meng.stg.backup.mbgparser.Action;
import com.meng.stg.backup.mbgparser.Layer;
import com.meng.stg.backup.mbgparser.Life;
import com.meng.stg.backup.mbgparser.MRef;
import com.meng.stg.backup.mbgparser.MotionWithPosition;
import com.meng.stg.backup.mbgparser.MotionWithRandWithPosition;
import com.meng.stg.backup.mbgparser.Position;
import com.meng.stg.backup.mbgparser.Tuple;
import com.meng.stg.backup.mbgparser.ValueWithRand;
import com.meng.stg.backup.mbgparser.event.EventGroup;

import java.io.Serializable;
import java.util.ArrayList;

import static com.meng.stg.backup.mbgparser.MBGUtils.readBool;
import static com.meng.stg.backup.mbgparser.MBGUtils.readFloat;
import static com.meng.stg.backup.mbgparser.MBGUtils.readInt;
import static com.meng.stg.backup.mbgparser.MBGUtils.readPosition;
import static com.meng.stg.backup.mbgparser.MBGUtils.readString;


public class Mask implements BindState.IBindable, Serializable{
    private static final long serialVersionUID=-8912215868589722952L;
    public int ID, 层ID;
    public BindState 绑定状态=new BindState();
    public Position<Float> 位置坐标=new Position<>();
    public Life 生命=new Life();
    public float 半宽, 半高;
    public boolean 启用圆形;
    public ControlType 类型=ControlType.values()[0];
    public int 控制ID;
    public MotionWithPosition<ValueWithRand,Float> 运动=new MotionWithRandWithPosition<Float>();
    public ArrayList<EventGroup> 发射器事件组=new ArrayList<>(), 子弹事件组=new ArrayList<>();

    public static Tuple<Mask,Action> parseFrom(String content,final Layer layer){
        final Mask m=new Mask();

        MRef<String> tempRef_content=new MRef<String>(content);
        m.ID=readInt(tempRef_content);
        content=tempRef_content.argValue;
        m.层ID=readInt(tempRef_content);
        m.位置坐标.x=readFloat(tempRef_content);
        m.位置坐标.y=readFloat(tempRef_content);

        m.生命.begin=readInt(tempRef_content);
        m.生命.lifeTime=readInt(tempRef_content);
        m.半宽=readFloat(tempRef_content);
        m.半高=readFloat(tempRef_content);
        m.启用圆形=readBool(tempRef_content);

        m.类型=ControlType.forValue(readInt(tempRef_content));
        m.控制ID=readInt(tempRef_content);
        m.运动.motion.speed.baseValue=readFloat(tempRef_content);
        m.运动.motion.speedDirection.baseValue=readFloat(tempRef_content);
        m.运动.speedDirectionPosition=readPosition(tempRef_content);
        m.运动.motion.acceleration.baseValue=readFloat(tempRef_content);
        m.运动.motion.accelerationDirection.baseValue=readFloat(tempRef_content);
        m.运动.accelerationDirectionPosition=readPosition(tempRef_content);

        m.发射器事件组=EventGroup.parseEventGroups(readString(tempRef_content));
        m.子弹事件组=EventGroup.parseEventGroups(readString(tempRef_content));

        m.运动.motion.speed.randValue=readFloat(tempRef_content);
        m.运动.motion.speedDirection.randValue=readFloat(tempRef_content);
        m.运动.motion.acceleration.randValue=readFloat(tempRef_content);
        m.运动.motion.accelerationDirection.randValue=readFloat(tempRef_content);

        final int 绑定ID=readInt(tempRef_content);
        final boolean 深度绑定=readBool(tempRef_content);

        Action binder=new Action(){
            @Override
            public void invoke(){

            }
        };
        if(绑定ID!=-1){
            binder=new Action(){
                @Override
                public void invoke(){
                    m.绑定状态=layer.findBulletEmitterByID(绑定ID).bind(m,深度绑定,false);
                }
            };
        }

        content=tempRef_content.argValue;
        if(!content.equals("")){
            throw new RuntimeException("遮罩解析后剩余字符串："+content);
        }

        return Tuple.Create(m,binder);
    }


}