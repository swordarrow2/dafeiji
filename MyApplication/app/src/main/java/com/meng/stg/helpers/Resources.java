package com.meng.stg.helpers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class Resources{
	public static HashMap<String, Drawable> Textures = new HashMap<String, Drawable>();
	public static void Load(){
		FileHandle projDir = Gdx.files.internal("textures/proj/");
		FileHandle[] projFiles = projDir.list();

		for(int i = 0; i<projFiles.length; i++){
			Texture texture = new Texture(projFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			Textures.put(projFiles[i].nameWithoutExtension(),drawable);
		  }

		FileHandle plrDir = Gdx.files.internal("textures/player/");
		FileHandle[] plrFiles = plrDir.list();

		for(int i = 0; i<plrFiles.length; i++){
			Texture texture = new Texture(plrFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			Textures.put(plrFiles[i].nameWithoutExtension(),drawable);
		  }
		if(true){return;}
		FileHandle npcDir = Gdx.files.internal("textures/npc/");
		FileHandle[] npcFiles = npcDir.list();

		for(int i = 0; i<npcFiles.length; i++){
			Texture texture = new Texture(npcFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			Textures.put(npcFiles[i].nameWithoutExtension(),drawable);
		  }


		npcDir=Gdx.files.internal("textures/bullet/");
		npcFiles=npcDir.list();

		for(int i = 0; i<npcFiles.length; i++){
			Texture texture = new Texture(npcFiles[i]);
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
			Textures.put(npcFiles[i].nameWithoutExtension(),drawable);
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
		  }}

  }
