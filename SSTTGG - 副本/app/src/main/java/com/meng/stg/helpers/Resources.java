package com.meng.stg.helpers;

import android.util.Log;

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

        FileHandle projDir=Gdx.files.internal("textures/bullet/");
        FileHandle[] projFiles=projDir.list();

        for(int i=0;i<projFiles.length;i++){
            Texture texture=new Texture(projFiles[i]);
            TextureRegionDrawable drawable=new TextureRegionDrawable(new TextureRegion(texture));
            Textures.put(projFiles[i].nameWithoutExtension(),drawable);
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

        if(true){
            return;
        }
        FileHandle[] Background=Gdx.files.external("/thsss/Image/Background").list();
        for(FileHandle tex : Background){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Boss=Gdx.files.external("/thsss/Image/Boss").list();
        for(FileHandle tex : Boss){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Bullet=Gdx.files.external("/thsss/Image/Bullet").list();
        for(FileHandle tex : Bullet){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] ED=Gdx.files.external("/thsss/Image/ED").list();
        for(FileHandle tex : ED){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Effect=Gdx.files.external("/thsss/Image/Effect").list();
        for(FileHandle tex : Effect){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Enemy=Gdx.files.external("/thsss/Image/Enemy").list();
        for(FileHandle tex : Enemy){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Face=Gdx.files.external("/thsss/Image/Face").list();
        for(FileHandle tex : Face){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Font=Gdx.files.external("/thsss/Image/Font").list();
        for(FileHandle tex : Font){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Interface=Gdx.files.external("/thsss/Image/Interface").list();
        for(FileHandle tex : Interface){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Menu=Gdx.files.external("/thsss/Image/Menu").list();
        for(FileHandle tex : Menu){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] MyPlane=Gdx.files.external("/thsss/Image/MyPlane").list();
        for(FileHandle tex : MyPlane){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] NowLoading=Gdx.files.external("/thsss/Image/NowLoading").list();
        for(FileHandle tex : NowLoading){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new TextureRegionDrawable(new TextureRegion(new Texture(tex))));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
