package com.cosine.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 连接工具
 * 应该完成上传下载数据文件接口
 * @author Administrator
 *
 */
public class FileUtils {
	private static BufferedReader reader = null;
	private static BufferedWriter writer = null;
	
	
	public static void writeJson(String path,String jsondata){
		 try{
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			writer.append(jsondata);
			writer.flush();
		 }catch(IOException e){
			 e.printStackTrace();
		 }finally{
			 try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
			
	}
	public static String readJson(String path){
		String datastr = "";
		 try{
			 FileInputStream fileInputStream = new FileInputStream(path);
			 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			 reader = new BufferedReader(inputStreamReader);
			 String line = null;
			 while((line = reader.readLine()) != null){
				 datastr += line; 
			} 
		 }catch(IOException e){
			 e.printStackTrace();
		 }finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 return datastr;
	}

}
