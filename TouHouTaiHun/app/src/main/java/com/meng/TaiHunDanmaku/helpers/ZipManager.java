package com.meng.TaiHunDanmaku.helpers;

import java.io.*;
import java.util.zip.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.*;
import android.os.*;

public class ZipManager{

	public static void saveReplayFile(String txt,String fileName) throws  IOException{ 

		FileHandle fh=Gdx.files.external(fileName);
		InputStream input = new ByteArrayInputStream(txt.getBytes()) ; 
		ZipOutputStream zipOut =new ZipOutputStream(fh.write(false)) ; 
		zipOut.putNextEntry(new ZipEntry("bin.txt")) ; // 设置ZipEntry对象 
		zipOut.setComment("FaFaFa") ;  // 设置注释 
		int temp = 0 ; 
		while((temp=input.read())!=-1){ // 读取内容 
			zipOut.write(temp) ;    // 压缩输出 
		  } 
		input.close() ; // 关闭输入流 
		zipOut.flush();
		zipOut.close() ;  // 关闭输出流 

	  }


	public static String readReplayFile(String fileName){
		byte doc[]=null;
		
		try{
			//这里filename是文件名，如xxx.zip
			ZipInputStream zipis=new ZipInputStream(Gdx.files.external(fileName).read());
			ZipEntry fentry=null;
			while((fentry=zipis.getNextEntry())!=null){
				//fentry逐个读取zip中的条目，第一个读取的名称为test。
				//test条目是文件夹，因此会创建一个File对象，File对象接收的参数为地址
				//然后就会用exists,判断该参数所指定的路径的文件或者目录是否存在
				//如果不存在，则构建一个文件夹；若存在，跳过
				//如果读到一个zip，也继续创建一个文件夹，然后继续读zip里面的文件，如txt

				//fname是文件名,fileoutputstream与该文件名关联
		
				try{
					//新建一个out,指向fname，fname是输出地址
					FileOutputStream out = (FileOutputStream) Gdx.files.external(fileName+11).write(false);
					doc=new byte[512];
					int n;
					
					//若没有读到，即读取到末尾，则返回-1
					while((n=zipis.read(doc,0,512))!=-1){
						//这就把读取到的n个字节全部都写入到指定路径了
						out.write(doc,0,n);
//                            System.out.println(n);
					  }
					  
			
					out.close();
					out=null;
					doc=null;
				  }catch(Exception ex){
					System.out.println("there is a problem");
				  }
			  }
			zipis.close();
		  }catch(IOException ioex){ System.out.println("io错误："+ioex);}
		System.out.println("finished!");
	  


	/*
	 ZipFile zipFile=new ZipFile(new File(Environment.getExternalStorageDirectory()+File.separator+"replay/"+fileName));
	 InputStream in=zipFile.getInputStream(zipFile.getEntry("bin"));
	 InputStreamReader ir = new InputStreamReader(in);
	 BufferedReader bin = new BufferedReader(ir);//为输入流提供缓冲区
	 String s;
	 StringBuilder sb=new StringBuilder();
	 while((s=bin.readLine())!="end"){
	 sb.append(s);
	 }
	 */
		return Gdx.files.external(fileName+11).readString();// sb.toString();
	}

	
	private static final String DEFAULT_ENCODING = "GBK";//编码
    private static final int PROTECTED_LENGTH = 51200;// 输入流保护 50KB

    public static String readInfoStream(InputStream input) throws Exception {
        if (input == null) {
            throw new Exception("输入流为null");
		  }
        //字节数组
        byte[] bcache = new byte[2048];
        int readSize = 0;//每次读取的字节长度
        int totalSize = 0;//总字节长度
        ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
        try {
            //一次性读取2048字节
            while ((readSize = input.read(bcache)) > 0) {
                totalSize += readSize;
                if (totalSize > PROTECTED_LENGTH) {
                    throw new Exception("输入流超出50K大小限制");
				  }
                //将bcache中读取的input数据写入infoStream
                infoStream.write(bcache,0,readSize);
			  }
		  } catch (IOException e1) {
            throw new Exception("输入流读取异常");
		  } finally {
            try {
                //输入流关闭
                input.close();
			  } catch (IOException e) {
                throw new Exception("输入流关闭异常");
			  }
		  }

        try {
            return infoStream.toString(DEFAULT_ENCODING);
		  } catch (UnsupportedEncodingException e) {
            throw new Exception("输出异常");
		  }
	  }
	
	
	
	
  }
