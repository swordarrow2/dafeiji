package com.meng.stg.helpers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class Resources
{
	public static HashMap<String, Drawable> ProjDrawables = new HashMap<String, Drawable>();
	//public static HashMap<String, Drawable> PlrDrawables = new HashMap<String, Drawable>();
	public static HashMap<String, Drawable> NPCDrawables = new HashMap<String, Drawable>();
	public static void Load()
	{
		FileHandle projDir = Gdx.files.internal("textures/bullet/");
		FileHandle[] projFiles = projDir.list();
		
		for (int i = 0; i < projFiles.length; i++) {
			Texture texture = new Texture(projFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			ProjDrawables.put(projFiles[i].nameWithoutExtension(), drawable);
		}
		
		//FileHandle plrDir = Gdx.files.internal("textures/player/");
		//FileHandle[] plrFiles = plrDir.list();
		
		//for (int i = 0; i < plrFiles.length; i++) {
		//	Texture texture = new Texture(plrFiles[i]);
		//	TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
		//	PlrDrawables.put(plrFiles[i].nameWithoutExtension(), drawable);
		//}
		
		FileHandle npcDir = Gdx.files.internal("textures/enemy/");
		FileHandle[] npcFiles = npcDir.list();
		
		for (int i = 0; i < npcFiles.length; i++) {
			Texture texture = new Texture(npcFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			NPCDrawables.put(npcFiles[i].nameWithoutExtension(), drawable);
		}
	}
}
