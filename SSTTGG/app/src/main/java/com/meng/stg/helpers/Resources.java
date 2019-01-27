package com.meng.stg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;

public final class Resources{
    public static HashMap<String,Drawable> Textures=new HashMap<String,Drawable>();

    public static void Load(){
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
        FileHandle npcDir=Gdx.files.internal("textures/enemy/");
        FileHandle[] npcFiles=npcDir.list();
        for(int i=0;i<npcFiles.length;i++){
            if(projFiles[i].extension().equals("png")){
                Texture texture=new Texture(npcFiles[i]);
                TextureRegionDrawable drawable=new TextureRegionDrawable(new TextureRegion(texture));
                Textures.put(npcFiles[i].nameWithoutExtension(),drawable);
            }
        }
    }

    private static void loadMyPlane(String name){
        FileHandle plsanae=Gdx.files.internal("textures/player/"+name+".png");
        String[] plsanaeWalkSheet=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt").readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);
        for(int i=2, n=0;n<43;i+=7,n++){
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
