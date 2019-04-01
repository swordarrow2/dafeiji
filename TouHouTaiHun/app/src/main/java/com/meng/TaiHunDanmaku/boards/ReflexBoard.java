package com.meng.TaiHunDanmaku.boards;
import com.meng.TaiHunDanmaku.bullets.*;
import com.meng.TaiHunDanmaku.effects.*;
import com.badlogic.gdx.math.*;

public class ReflexBoard{
	public Vector2 point1=new Vector2();
	public Vector2 point2=new Vector2();
	public ReflexBoard(){

	  }
	public void update(){

		for(BaseEnemyBullet baseBullet : BaseEnemyBullet.instances){
            if(pointToLine(point1,point2,baseBullet.objectCenter)<5){
				if(baseBullet.refCount>0){
					--baseBullet.refCount;
					baseBullet.velocity.x=-baseBullet.velocity.x;
				  }  
			  }  
		  }  
	  }
	private double pointToLine(Vector2 point1,Vector2 point2,Vector2 point){
		return pointToLine(point1.x,point1.y,point2.x,point2.y,point.x,point.y);
	  }
	private double pointToLine(double x1,double y1,double x2,double y2,double x0,double y0){
        double space = 0;
        double a, b, c;
        a=lineSpace(x1,y1,x2,y2);// 线段的长度
        b=lineSpace(x1,y1,x0,y0);// (x1,y1)到点的距离
        c=lineSpace(x2,y2,x0,y0);// (x2,y2)到点的距离
        if(c<=0.000001||b<=0.000001){
            space=0;
            return space;
		  }
        if(a<=0.000001){
            space=b;
            return space;
		  }
        if(c*c>=a*a+b*b){
            space=b;
            return space;
		  }
        if(b*b>=a*a+c*c){
            space=c;
            return space;
		  }
        double p = (a+b+c)/2;// 半周长
        double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));// 海伦公式求面积
        space=2*s/a;// 返回点到线的距离（利用三角形面积公式求高）
        return space;
	  }

    // 计算两点之间的距离
    private double lineSpace(double x1,double y1,double x2,double y2){
        double lineLength = 0;
        lineLength=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return lineLength;
	  }
  }
