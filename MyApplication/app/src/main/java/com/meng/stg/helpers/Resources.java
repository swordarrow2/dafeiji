package com.meng.stg.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public final class Resources{
    public static HashMap<String,Texture> Textures=new HashMap<String,Texture>();

    public static void Load(){
        FileHandle projDir=Gdx.files.internal("textures/proj/");
        FileHandle[] projFiles=projDir.list();

        for(int i=0;i<projFiles.length;i++){
            Texture texture=new Texture(projFiles[i]);
            Textures.put(projFiles[i].nameWithoutExtension(),texture);
        }

        FileHandle plrDir=Gdx.files.internal("textures/player/");
        FileHandle[] plrFiles=plrDir.list();

        for(int i=0;i<plrFiles.length;i++){
            Texture texture=new Texture(plrFiles[i]);
            Textures.put(plrFiles[i].nameWithoutExtension(),texture);
        }
        if(true){
            return;
        }
        FileHandle npcDir=Gdx.files.internal("textures/npc/");
        FileHandle[] npcFiles=npcDir.list();

        for(int i=0;i<npcFiles.length;i++){
            Texture texture=new Texture(npcFiles[i]);
            Textures.put(npcFiles[i].nameWithoutExtension(),texture);
        }


        npcDir=Gdx.files.internal("textures/bullet/");
        npcFiles=npcDir.list();

        for(int i=0;i<npcFiles.length;i++){
            Texture texture=new Texture(npcFiles[i]);
            Textures.put(npcFiles[i].nameWithoutExtension(),texture);
        }

        FileHandle[] Background=Gdx.files.external("/thsss/Image/Background").list();
        for(FileHandle tex : Background){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Boss=Gdx.files.external("/thsss/Image/Boss").list();
        for(FileHandle tex : Boss){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Bullet=Gdx.files.external("/thsss/Image/Bullet").list();
        for(FileHandle tex : Bullet){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] ED=Gdx.files.external("/thsss/Image/ED").list();
        for(FileHandle tex : ED){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Effect=Gdx.files.external("/thsss/Image/Effect").list();
        for(FileHandle tex : Effect){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Enemy=Gdx.files.external("/thsss/Image/Enemy").list();
        for(FileHandle tex : Enemy){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Face=Gdx.files.external("/thsss/Image/Face").list();
        for(FileHandle tex : Face){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Font=Gdx.files.external("/thsss/Image/Font").list();
        for(FileHandle tex : Font){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Interface=Gdx.files.external("/thsss/Image/Interface").list();
        for(FileHandle tex : Interface){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] Menu=Gdx.files.external("/thsss/Image/Menu").list();
        for(FileHandle tex : Menu){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] MyPlane=Gdx.files.external("/thsss/Image/MyPlane").list();
        for(FileHandle tex : MyPlane){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        FileHandle[] NowLoading=Gdx.files.external("/thsss/Image/NowLoading").list();
        for(FileHandle tex : NowLoading){
            if(tex.extension().equals("png")){
                try{
                    Textures.put(tex.nameWithoutExtension(),new Texture(tex));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
