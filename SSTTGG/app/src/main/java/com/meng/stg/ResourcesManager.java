package com.meng.stg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;

/*
 all game resources
 */
public final class ResourcesManager{
    public static HashMap<String,Drawable> textures=new HashMap<String,Drawable>();

    public static void Load(){
        loadMyPlane("pl00");
        loadEnemy("enemy");
        loadEffect("slow");
        loadBullets("bullet1");
    }

    private static void loadMyPlane(String name){
        FileHandle plsanae=Gdx.files.internal("textures/player/"+name+".png");
        FileHandle plsanaetxt=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt");
        String[] plsanaeWalkSheet=plsanaetxt.readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);
        int textureCount=getTextureCount(plsanaetxt);
        for(int i=0, n=0;n<textureCount;i++){
            if(plsanaeWalkSheet[i].equals("Sprite:")){
                textures.put("reimu"+plsanaeWalkSheet[i+1],new TextureRegionDrawable(new TextureRegion(tplsanae,Integer.parseInt(plsanaeWalkSheet[i+4]),Integer.parseInt(plsanaeWalkSheet[i+5]),Integer.parseInt(plsanaeWalkSheet[i+2]),Integer.parseInt(plsanaeWalkSheet[i+3]))));
                n++;
            }
        }
    }

    private static void loadEnemy(String name){
        FileHandle plsanae=Gdx.files.internal("textures/enemy/"+name+".png");
        FileHandle plsanaetxt=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt");
        String[] plsanaeWalkSheet=plsanaetxt.readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);
        int textureCount=getTextureCount(plsanaetxt);
        for(int i=0, n=0;n<textureCount;i++){
            if(plsanaeWalkSheet[i].equals("Sprite:")){
                textures.put("zayu"+plsanaeWalkSheet[i+1],new TextureRegionDrawable(new TextureRegion(tplsanae,Integer.parseInt(plsanaeWalkSheet[i+4]),Integer.parseInt(plsanaeWalkSheet[i+5]),Integer.parseInt(plsanaeWalkSheet[i+2]),Integer.parseInt(plsanaeWalkSheet[i+3]))));
                n++;
            }
        }
    }

    private static void loadEffect(String name){
        FileHandle plsanae=Gdx.files.internal("textures/effect/"+name+".png");
        FileHandle plsanaetxt=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt");
        String[] plsanaeWalkSheet=plsanaetxt.readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);
        int textureCount=getTextureCount(plsanaetxt);
        for(int i=0, n=0;n<textureCount;i++){
            if(plsanaeWalkSheet[i].equals("Sprite:")){
                textures.put("slow"+plsanaeWalkSheet[i+1],new TextureRegionDrawable(new TextureRegion(tplsanae,Integer.parseInt(plsanaeWalkSheet[i+4]),Integer.parseInt(plsanaeWalkSheet[i+5]),Integer.parseInt(plsanaeWalkSheet[i+2]),Integer.parseInt(plsanaeWalkSheet[i+3]))));
                n++;
            }
        }
    }

    private static void loadBullets(String name){
        FileHandle plsanae=Gdx.files.internal("textures/bullet/"+name+".png");
        FileHandle plsanaetxt=Gdx.files.internal(plsanae.pathWithoutExtension()+".txt");
        String[] plsanaeWalkSheet=plsanaetxt.readString().replace("\n"," ").replace("*"," ").replace("+"," ").split("\\s");
        Texture tplsanae=new Texture(plsanae);
        int textureCount=getTextureCount(plsanaetxt);
        for(int i=0, n=0;n<textureCount;i++){
            if(plsanaeWalkSheet[i].equals("Sprite:")){
                textures.put("bullet"+plsanaeWalkSheet[i+1],new TextureRegionDrawable(new TextureRegion(tplsanae,Integer.parseInt(plsanaeWalkSheet[i+4]),Integer.parseInt(plsanaeWalkSheet[i+5]),Integer.parseInt(plsanaeWalkSheet[i+2]),Integer.parseInt(plsanaeWalkSheet[i+3]))));
                n++;
            }
        }
    }

    private static int getTextureCount(FileHandle txt){
        String str=txt.readString();
        String flagStr="Sprite:";
        String newStr=str.replace(flagStr,"");
        return (str.length()-newStr.length())/flagStr.length();
    }
}
