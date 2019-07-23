package stu_System;
import java.io.*;
import java.text.*;
import java.util.*;

interface Loggable{
	enum TYPE{
		INFORMATION,WARNING,ERROR
	};
	void addLog(Loggable.TYPE type,String logContent) throws IOException;
	String readLog() throws IOException;
}
public class MyLog implements Loggable{
	File MyLog = new File("D:\\", "MyLog.txt");  
	public void addLog(Loggable.TYPE type,String logContent) throws IOException {
		if(!MyLog.exists())    
		{    
			try {    			
				MyLog.createNewFile();    		
			} catch (IOException e) {     			
				e.printStackTrace();    	
			}    
		}    
		BufferedWriter bw = new BufferedWriter(new FileWriter(MyLog,true));
		
		Date nowTime = new Date();   
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
        String time = matter.format(nowTime);  
		
        try {
        	bw.write("*****************************");   
    		bw.newLine();   
    		bw.write(time);   
    		bw.newLine();   
    		bw.write(type.toString());   
    		bw.newLine();   
    		bw.write(logContent);   
    		bw.newLine();   
    		bw.write("*****************************");   
        }catch(IOException e) {
        	e.printStackTrace();  
        }finally {
        	bw.flush();
    		bw.close(); 
        }		  
	}
	public String readLog() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(MyLog));
		StringBuffer sbLog = new StringBuffer();
		String str;
		while ((str=br.readLine())!=null) {
			sbLog.append(str + "\n");
		}
		return sbLog.toString();
	}


}
