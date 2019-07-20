package com.meng.TaiHunDanmaku.helpers;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import java.util.*;

public final class ResourcesManager {
    public static HashMap<String, Drawable> textures = new HashMap<String, Drawable>();
    public static HashMap<String, Drawable> flipedTextures = new HashMap<String, Drawable>();

    public static void Load() {
        loadMyPlane("pl00");
        loadEnemy("enemy");        
        loadBullets("bullet1");
        loadBoss("chunhu");       
        //p:519        
	  }

    private static void loadMyPlane(String name) {
        FileHandle plsanae = Gdx.files.internal("textures/player/" + name + ".png");
        FileHandle plsanaetxt = Gdx.files.internal(plsanae.pathWithoutExtension() + ".txt");
        String[] plsanaeWalkSheet = plsanaetxt.readString().replace("\n", " ").replace("*", " ").replace("+", " ").split("\\s");
        Texture tplsanae = new Texture(plsanae);
        int textureCount = getTextureCount(plsanaetxt);
        for (int i = 0, n = 0; n < textureCount; i++) {
            if (plsanaeWalkSheet[i].equals("Sprite:")) {
                textures.put("reimu" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]))));
                n++;
			  }
		  }
	  }

    private static void loadBoss(String name) {
        FileHandle plsanae = Gdx.files.internal("textures/enemy/" + name + ".png");
        FileHandle plsanaetxt = Gdx.files.internal(plsanae.pathWithoutExtension() + ".txt");
        String[] plsanaeWalkSheet = plsanaetxt.readString().replace("\n", " ").replace("*", " ").replace("+", " ").split("\\s");
        Texture tplsanae = new Texture(plsanae);
        int textureCount = getTextureCount(plsanaetxt);
        for (int i = 0, n = 0; n < textureCount; i++) {
            if (plsanaeWalkSheet[i].equals("Sprite:")) {
                textures.put("chunhu" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]))));
                TextureRegion tr = new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]));
                tr.flip(true, false);
                flipedTextures.put("chunhu" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(tr));
                n++;
			  }
		  }
	  }

    private static void loadEnemy(String name) {
        FileHandle plsanae = Gdx.files.internal("textures/enemy/" + name + ".png");
        FileHandle plsanaetxt = Gdx.files.internal(plsanae.pathWithoutExtension() + ".txt");
        String[] plsanaeWalkSheet = plsanaetxt.readString().replace("\n", " ").replace("*", " ").replace("+", " ").split("\\s");
        Texture tplsanae = new Texture(plsanae);
        int textureCount = getTextureCount(plsanaetxt);
        for (int i = 0, n = 0; n < textureCount; i++) {
            if (plsanaeWalkSheet[i].equals("Sprite:")) {
                textures.put("zayu" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]))));
                TextureRegion tr = new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]));
                tr.flip(true, false);
                flipedTextures.put("zayu" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(tr));
                n++;
			  }
		  }
	  }

    private static void loadBullets(String name) {
        FileHandle plsanae = Gdx.files.internal("textures/bullet/" + name + ".png");
        FileHandle plsanaetxt = Gdx.files.internal(plsanae.pathWithoutExtension() + ".txt");
        String[] plsanaeWalkSheet = plsanaetxt.readString().replace("\n", " ").replace("*", " ").replace("+", " ").split("\\s");
        Texture tplsanae = new Texture(plsanae);
        int textureCount = getTextureCount(plsanaetxt);
        for (int i = 0, n = 0; n < textureCount; i++) {
            if (plsanaeWalkSheet[i].equals("Sprite:")) {
                textures.put("bullet" + plsanaeWalkSheet[i + 1], new TextureRegionDrawable(new TextureRegion(tplsanae, Integer.parseInt(plsanaeWalkSheet[i + 4]), Integer.parseInt(plsanaeWalkSheet[i + 5]), Integer.parseInt(plsanaeWalkSheet[i + 2]), Integer.parseInt(plsanaeWalkSheet[i + 3]))));
                n++;
			  }
		  }
	  }
	
    private static int getTextureCount(FileHandle txt) {
        String str = txt.readString();
        String flagStr = "Sprite:";
        String newStr = str.replace(flagStr, "");
        return (str.length() - newStr.length()) / flagStr.length();
	  }
  }
