package com.meng.grzxv2.javaBean;
import java.util.*;

public class GroupConfig{
	public long groupNumber = 0;
	public boolean reply = true;
	public ArrayList<Boolean> booleans=new ArrayList<Boolean>(14);
	
	public int repeatMode = 0;
	
	/*public boolean repeat = true;
	public boolean setu = false;
	public boolean pohai = false;
	public boolean dic = false;
	public boolean bilibiliCheck = false;
	public boolean cuigeng = false;
	public boolean searchPic = false;
	public boolean checkLink = false;
	public boolean roll = false;
	public boolean barcode = false;
	public boolean kuiping = false;
	public boolean cqCode = false;
	public boolean zan = false;
	public boolean moshenfusong = false;
*/
	public void setRepeat(boolean repeat){
		booleans.set(0,repeat);
	  }

	public boolean isRepeat(){
		return booleans.get(0);
	  }

	public void setRepeatMode(int repeatMode){
		this.repeatMode=repeatMode;
	  }

	public int getRepeatMode(){
		return repeatMode;
	  }

	public void setSetu(boolean setu){
		booleans.set(1,setu);
	  }

	public boolean isSetu(){
		return booleans.get(1);
	  }

	public void setPohai(boolean pohai){
		booleans.set(2,pohai);
	  }

	public boolean isPohai(){
		return booleans.get(2);
	  }

	public void setDic(boolean dic){
		this.dic=dic;
	  }

	public boolean isDic(){
		return dic;
	  }

	public void setBilibiliCheck(boolean bilibiliCheck){
		this.bilibiliCheck=bilibiliCheck;
	  }

	public boolean isBilibiliCheck(){
		return bilibiliCheck;
	  }

	public void setCuigeng(boolean cuigeng){
		this.cuigeng=cuigeng;
	  }

	public boolean isCuigeng(){
		return cuigeng;
	  }

	public void setSearchPic(boolean searchPic){
		this.searchPic=searchPic;
	  }

	public boolean isSearchPic(){
		return searchPic;
	  }

	public void setCheckLink(boolean checkLink){
		this.checkLink=checkLink;
	  }

	public boolean isCheckLink(){
		return checkLink;
	  }

	public void setRoll(boolean roll){
		this.roll=roll;
	  }

	public boolean isRoll(){
		return roll;
	  }

	public void setBarcode(boolean barcode){
		this.barcode=barcode;
	  }

	public boolean isBarcode(){
		return barcode;
	  }

	public void setKuiping(boolean kuiping){
		this.kuiping=kuiping;
	  }

	public boolean isKuiping(){
		return kuiping;
	  }

	public void setCqCode(boolean cqCode){
		this.cqCode=cqCode;
	  }

	public boolean isCqCode(){
		return cqCode;
	  }

	public void setZan(boolean zan){
		this.zan=zan;
	  }

	public boolean isZan(){
		return zan;
	  }


	public void setMoshenfusong(boolean moshenfusong){
		this.moshenfusong=moshenfusong;
	  }

	public boolean isMoshenfusong(){
		return moshenfusong;
	  }}
