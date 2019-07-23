package stu_System;

import java.io.*;
import java.sql.ResultSet;  
import java.sql.SQLException; 

public class Student{
	/**
	 * 
	 */
    static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
    static int upd = 0; 
    static boolean bol = true;; 
	
	public void carry_search(String sql) {
		db1 = new DBHelper(sql);
		try {
			ret = db1.pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	public void carry_update(String sql) {
		db1 = new DBHelper(sql);
		try {
			upd = db1.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
//		db.close();
	}
	public void carry(String sql) {
		db1 = new DBHelper(sql);
		try {
			bol = db1.pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
//		db.close();
	}
	public void setInfo(String content,String id,String column){
		sql = "update student set "+column+" = '"+content+"' where id = '"+id+"'";//SQLÓï¾ä
		carry_update(sql);
		db1.close();
	}
	public void setGrade(int content,String id,String course_name){
		sql = "update mark set grade = "+content+" where id = '"+id+"' and Course_name = '"+course_name+"'";//SQLÓï¾ä
		carry_update(sql);
		db1.close();
	}
//	public int sum(String id){
//		int eng = getGrade(id,"eng");
//		int math = getGrade(id,"math");
//		int comp = getGrade(id,"comp");
//		int sum = eng+math+comp;
//		sql = "update mark set sum = "+sum+" where id = '"+id+"'";//SQLÓï¾ä
//		carry_update(sql);
//		db1.close();
//		return sum;
//	}  
	public String getInfo(String id,String column){
		sql = "select "+column+" from student where id = '"+id+"'";//SQLÓï¾ä
		carry_search(sql);
		String info=null;
		try {
			while(ret.next()) {
				info=ret.getString(column);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.close();
		return info;
	}
	public int getGrade(String id,String course_name){
		sql = "select grade from mark where id = '"+id+"' and Course_name = '"+course_name+"'";//SQLÓï¾ä
		carry_search(sql);
		int grade=0;
		try {
			while(ret.next()) {
				grade=ret.getInt(grade);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.close();
		return grade;
	}
//	public boolean gradejudge(String id) {
//		sql = "select id from mark";
//		carry_search(sql);
//		boolean exit = false;
//		try {
//			while(ret.next()){
//				if(id.equals(ret.getString("id"))) {
//					exit = true;
//					break;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		db1.close();
//		return exit;
//	}
	public boolean coursejudge(String id,String course_name) {
		sql = "select* from mark where id = '"+id+"' and Course_name = '"+course_name+"'";
		carry_search(sql);
		try {
			if(!ret.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
	}
	public String infotoString(String id){
		sql = "select* from student where id = '"+id+"'";
		carry_search(sql);
		String s = "";
		try {
			while(ret.next()) {	
				s = s+ret.getString("id");
				s = s+"\t"+ret.getString("name");
				s = s+"\t"+ret.getString("age");
				s = s+"\t"+ret.getString("sex");
				s = s+"\t"+ret.getString("role");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return s;
	}	
	public String gradetoString(String id){
		sql = "select* from mark where id = '"+id+"'";
		carry_search(sql);
		String s = "";
		try {
			while(ret.next()) {	
				s = s+ret.getString("id");
				s = s+"\t"+ret.getString("Course_name");
				s = s+"\t"+ret.getInt("grade");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return s;
	}	
}