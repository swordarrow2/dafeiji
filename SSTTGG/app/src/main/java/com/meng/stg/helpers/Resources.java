package com.meng.stg.helpers;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;

public final class Resources{
    public static HashMap<String,Drawable> Textures=new HashMap<String,Drawable>();
	public static void Load(){
		/*
		 FileHandle plReimu=Gdx.files.external("/thsss/Image/MyPlane/Reimu.png");
		 String[] plReimuWalkSheet=Gdx.files.external(plReimu.pathWithoutExtension()+".txt").readString().replace("\t"," ").replace("\n"," ").split("\\s");
		 Texture tplReimu=new Texture(plReimu);
		 TextureRegionDrawable dplReimu;
		 for(int i=1, n=0;n<24;i+=8,n++){
		 dplReimu=new TextureRegionDrawable(new TextureRegion(tplReimu,Integer.parseInt(plReimuWalkSheet[i]),Integer.parseInt(plReimuWalkSheet[i+1]),Integer.parseInt(plReimuWalkSheet[i+2]),Integer.parseInt(plReimuWalkSheet[i+3])));
		 Textures.put("reimu"+n,dplReimu);
		 }

		 FileHandle plmarisa=Gdx.files.external("/thsss/Image/MyPlane/Marisa.png");
		 String[] plmarisaWalkSheet=Gdx.files.external(plmarisa.pathWithoutExtension()+".txt").readString().replace("\t"," ").replace("\n"," ").split("\\s");
		 Texture tplmarisa=new Texture(plmarisa);
		 TextureRegionDrawable dplmarisa;
		 for(int i=1, n=0;n<24;i+=8,n++){
		 dplmarisa=new TextureRegionDrawable(new TextureRegion(tplmarisa,Integer.parseInt(plmarisaWalkSheet[i]),Integer.parseInt(plmarisaWalkSheet[i+1]),Integer.parseInt(plmarisaWalkSheet[i+2]),Integer.parseInt(plmarisaWalkSheet[i+3])));
		 Textures.put("marisa"+n,dplmarisa);
		 }

		 FileHandle plsanae=Gdx.files.external("/thsss/Image/MyPlane/Sanae.png");
		 String[] plsanaeWalkSheet=Gdx.files.external(plsanae.pathWithoutExtension()+".txt").readString().replace("\t"," ").replace("\n"," ").split("\\s");
		 Texture tplsanae=new Texture(plsanae);
		 TextureRegionDrawable dplsanae;
		 for(int i=1, n=0;n<24;i+=8,n++){
		 dplsanae=new TextureRegionDrawable(new TextureRegion(tplsanae,Integer.parseInt(plsanaeWalkSheet[i]),Integer.parseInt(plsanaeWalkSheet[i+1]),Integer.parseInt(plsanaeWalkSheet[i+2]),Integer.parseInt(plsanaeWalkSheet[i+3])));
		 Textures.put("sanae"+n,dplsanae);
		 }

		 FileHandle plkoishi=Gdx.files.external("/thsss/Image/MyPlane/Koishi.png");
		 String[] plkoishiWalkSheet=Gdx.files.external(plkoishi.pathWithoutExtension()+".txt").readString().replace("\t"," ").replace("\n"," ").split("\\s");
		 Texture tplkoishi=new Texture(plkoishi);
		 TextureRegionDrawable dplkoishi;
		 for(int i=1, n=0;n<24;i+=8,n++){
		 dplkoishi=new TextureRegionDrawable(new TextureRegion(tplkoishi,Integer.parseInt(plkoishiWalkSheet[i]),Integer.parseInt(plkoishiWalkSheet[i+1]),Integer.parseInt(plkoishiWalkSheet[i+2]),Integer.parseInt(plkoishiWalkSheet[i+3])));
		 Textures.put("koishi"+n,dplkoishi);
		 }
		 */
        loadMyPlane("pl00");

        FileHandle projDir=Gdx.files.internal("textures/bullet/");
        FileHandle[] projFiles=projDir.list();

        for(int i=0;i<projFiles.length;i++){
            if(projFiles[i].extension().equals("png")){
                Texture texture=new Texture(projFiles[i]);
                TextureRegionDrawable drawable=new TextureRegionDrawable(new TextureRegion(texture));
                Textures.put(projFiles[i].nameWithoutExtension(),drawable);
			  }
		  }

        //FileHandle plrDir = Gdx.files.internal("textures/player/");
        //FileHandle[] plrFiles = plrDir.list();

        //for (int i = 0; i < plrFiles.length; i++) {
        //	Texture texture = new Texture(plrFiles[i]);
        //	TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        //	PlrDrawables.put(plrFiles[i].nameWithoutExtension(), drawable);
        //}

        FileHandle npcDir=Gdx.files.internal("textures/enemy/");
        FileHandle[] npcFiles=npcDir.list();

        for(int i=0;i<npcFiles.length;i++){
            Texture texture=new Texture(npcFiles[i]);
            TextureRegionDrawable drawable=new TextureRegionDrawable(new TextureRegion(texture));
            Textures.put(npcFiles[i].nameWithoutExtension(),drawable);
		  }


	  }

    private static void loadMyPlane(String name){
        FileHandle plsanae=Gdx.files.internal("textures/player/"+name+".png");
        String[] plsanaeWalkSheet=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt").readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);

        for(String s : plsanaeWalkSheet){
            Log.i("TAG","Load: "+s+"\n");
		  }

        for(int i=2, n=0;n<43;i+=7,n++){
            if(n<24){
                int x=Integer.parseInt(plsanaeWalkSheet[i+2]);
                int y=Integer.parseInt(plsanaeWalkSheet[i+3]);
                int width=Integer.parseInt(plsanaeWalkSheet[i]);
                int height=Integer.parseInt(plsanaeWalkSheet[i+1]);
                TextureRegion tr=new TextureRegion(tplsanae,x,y,width,height);
                TextureRegionDrawable trd=new TextureRegionDrawable(tr);
                Textures.put("reimu"+n,trd);
			  }
			 }
	  }
  }
