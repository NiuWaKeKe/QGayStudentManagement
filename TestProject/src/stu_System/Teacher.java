package stu_System;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Teacher {
    static String sql = null;  
    static DBHelper db2 = null;  
    static ResultSet ret = null;  
    static int upd = 0; 
	public void carry_search(String sql) {
		db2 = new DBHelper(sql);
		try {
			ret = db2.pst.executeQuery();
			ret.close();
			db2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
	}

	public void carry_update(String sql) {
		db2 = new DBHelper(sql);
		try {
			upd = db2.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
//		db.close();
	}
	public void setCname(String Cname,String id){
		sql = "update course set Course_name="+Cname+" where Teacher_id="+id;//SQLÓï¾ä
		carry_update(sql);		
	}

	public String getCname(String id){
		sql = "select Cname from grade where Teacher_id='"+id+"'";//SQLÓï¾ä
		carry_search(sql);
		String Cname = null;
		try {
			Cname = ret.getString("Course_name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Cname;
	}
}
