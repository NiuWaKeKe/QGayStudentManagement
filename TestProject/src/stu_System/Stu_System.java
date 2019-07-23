package stu_System;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stu_System implements Service,Views{	
    static String sql = null;  
    static DBHelper db = null;  
    static ResultSet ret = null;  
    static int upd = 0;  
    static boolean bol = true;; 
    static MyLog myLog = new MyLog();
    
    static Student student = new Student();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public void carry_search(String sql) {
		db = new DBHelper(sql);
		try {
			ret = db.pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
    public void carry_update(String sql) {
		db = new DBHelper(sql);
		try {
			upd = db.pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
    public void carry(String sql) {
		db = new DBHelper(sql);
		try {
			bol = db.pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
    
    //登录判断
	@Override
	public boolean login_judge(String Login_id, String Login_key, String identity) {
		// TODO Auto-generated method stub
		sql = "select Loginkey from "+identity+" where Loginid = '"+Login_id+"'";
		carry_search(sql);
		String truekey = null;
		try {
			while(ret.next()) {
				truekey = ret.getString("Loginkey");
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(truekey==null)
			return false;
		if(truekey.equals(Login_key))
			return true;
		return false;
	}
	
	//设置所有老师的课程信息
	@Override
	public String showInputTecIdView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select Teacher_id from course";
			carry_search(sql);
			while(judge==false) {
				System.out.println("请输入将要修改老师的工号：");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("Teacher_id").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("查无此人");
					carry_search(sql);
				}		
			}	
			db.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void showEditCourseView(String id) {
		// TODO Auto-generated method stub
			
		sql = "select* from course where Teacher_id = '"+id+"'";
		carry_search(sql);
		String choice;
		try {
			System.out.println("==课程名称==");
			while (ret.next()) {	
				System.out.println("=="+ret.getString("Course_name")+"==");
			}
			System.out.println("选择设置类型：1.删除 2.添加 3.退出");
			choice = br.readLine();
			while(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")) {
				System.out.println("输入错误，请重新输入");
				choice = br.readLine();
			}
			if(choice.equals("1")) {
				System.out.println("选择删除的课程名称");
				String tobedelete = br.readLine();
				sql = "delete from course where Teacher_id='"+id+"' and Course_name='"+tobedelete+"'";
				carry(sql);
				db.close();
				
				sql = "delete from mark where Course_name = '"+tobedelete+"'";
				carry(sql);
				db.close();
				System.out.println("删除成功");
			}
			else if(choice.equals("2")) {
				System.out.println("选择添加的课程名称");
				String tobeadd = br.readLine();
				sql = "insert into course(Course_name,Teacher_id) values('"+tobeadd+"','"+id+"')";
				carry(sql);
				db.close();
				System.out.println("添加成功");
			}
			else
				System.out.println("退出成功");
				return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//修改学生信息
	@Override
	public String showInputStuIdView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select id from student";
			carry_search(sql);
			while(judge==false) {
				System.out.println("请输入将要修改学生的学号：");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("id").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("查无此人");
					carry_search(sql);
				}		
			}	
			db.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void showEditStuView(String id) {
		// TODO Auto-generated method stub
		boolean control = true;
		while(control) {
			System.out.println("====输入你想编辑的内容序号====");
			System.out.println("1.姓名");
			System.out.println("2.年龄");
			System.out.println("3.性别");
			System.out.println("4.职务");
			System.out.println("5.退出");	
			String s;
			try {
				s = br.readLine();
				while(s.equals("1")&&s.equals("2")&&s.equals("3")&&s.equals("4")) {
					System.out.println("输入有误，请重新输入");
					s=br.readLine();
				}		
				switch(s) {
				case "1":{
					System.out.println("修改前的名字为："+student.getInfo(id,"name"));
					System.out.print("新名字为：");
					s=br.readLine();
					student.setInfo(s,id,"name");
					System.out.println("修改成功！");
				};break;
				case "2":{
					System.out.println("修改前的年龄为："+student.getInfo(id,"age"));
					System.out.print("新年龄为：");
					s=br.readLine();
					student.setInfo(s,id,"age");
					System.out.println("修改成功！");
				};break;
				case "3":{
					System.out.println("修改前的性别为："+student.getInfo(id,"sex"));
					System.out.print("新性别为：m/f ");
					s=br.readLine();
					while(!s.equals("m")&&!s.equals("f")) {
						System.out.println("[warning:输入有误，请重新输入]");
						s=br.readLine();
					}
					student.setInfo(s,id,"sex");
					System.out.println("修改成功！");
				};break;
				case "4":{
					System.out.println("修改前的职务为："+student.getInfo(id,"role"));
					System.out.print("新职务为：");
					s=br.readLine();
					student.setInfo(s,id,"role");
					System.out.println("修改成功！");
				};break;
				case "5":{
					control = false;
				};break;
				default:break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void showEditStuResultView(String id) {
		// TODO Auto-generated method stub
		System.out.println("修改后的新学生信息为：");
		System.out.println("学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务");
		System.out.println(student.infotoString(id));
	}

	//查询选定课程成绩
	@Override
	public String showInputCnameView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select Course_name from course";
			carry_search(sql);
			while(judge==false) {
				System.out.println("请输入查询课程名称：");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("Course_name").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("没有人选择该课程");
					carry_search(sql);
				}		
			}	
			db.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void showSearchCourseView(String course_name) {//通过课程名称查询该课程下所有学生的成绩
		// TODO Auto-generated method stub
		sql="select id,grade from mark where Course_name = '"+course_name+"'";
		carry_search(sql);
		String id;
		int grade;
		System.out.println("==id=="+course_name+"==");
		try {
			while(ret.next()) {
				id = ret.getString("id");
				grade = ret.getInt("grade");
				System.out.println("=="+id+"=="+grade+"==");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//设置老师课程的学生成绩
	@Override
	public String[] showTecCourseView(String id) {
		// TODO Auto-generated method stub
		sql = "select Course_name from course where Teacher_id = '"+id+"'";
		carry_search(sql);
		int i=0;
		String course[] = new String[10];
		try {
			System.out.println("==课程名称==");
			while(ret.next()) {
				course[i] = ret.getString("Course_name");
				System.out.println("=="+ret.getString("Course_name")+"==");
				i++;
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	@Override
	public String[] showChooseGradeView(String[] course) {
		// TODO Auto-generated method stub
		System.out.println("选择将要进行设置(增加、修改、查询、删除)的课程名称：");
		String[] deliver = new String[2];
		try {
			deliver[0] = br.readLine();
			boolean jud = false;
			for(int i=0;course[i]!=null;i++) {
				if(deliver[0].equals(course[i])) {
					jud = true;
					break;
				}
			}
			while(jud == false) {
				System.out.println("选择合适课程名称");
				System.out.println("选择将要进行设置(增加、修改、查询、删除)的课程名称：");
				deliver[0] = br.readLine();//课程名称
				for(int i=0;course[i]!=null;i++) {
					if(deliver[0].equals(course[i])) {
						jud = true;
						break;
					}
				}
			}				
			System.out.println("选择将要进行成绩设置(增加、修改、查询、删除)的学生id：");
			deliver[1] = br.readLine();//学生id
			sql = "select id from student";
			carry_search(sql);
			boolean j = false;
			while(ret.next()) {
				if(deliver[1].equals(ret.getString("id"))) {
					j = true;
					db.close();
					break;
				}
			}
			while(j == false) {
				System.out.println("不存在该名学生");
				System.out.println("选择将要进行成绩设置(增加、修改、查询、删除)的学生id：");
				deliver[1] = br.readLine();//学生id
				carry_search(sql);
				while(ret.next()) {
					if(deliver[1].equals(ret.getString("id"))) {
						j = true;
						db.close();
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//课程名称
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deliver;
	}
	@Override
	public void showEditGradeView(String[] course) {
		// TODO Auto-generated method stub
		for(int i = 0;course[i]!=null;i++) {
			sql = "select* from mark where Course_name = '"+course[i]+"'";
			carry_search(sql);
			try {
				System.out.println("=学号="+course[i]+"=");
				while(ret.next()) {		
					System.out.println("="+ret.getString("id")+"="+ret.getInt("grade")+"=");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db.close();
		System.out.println("输入对成绩的设置方法");
		System.out.println("1.增加  2.修改  3.查询  4.删除 5.退出");
		try {
			String cho = br.readLine();
			while(cho.equals("1")&&cho.equals("2")&&cho.equals("3")&&cho.equals("4")&&cho.equals("5")) {
				System.out.println("输入有误，请重新输入");
				cho=br.readLine();
			}
			String course_name = null;
			String id = null;
			String[] recevier  = new String[2];
			recevier = showChooseGradeView(course);
			course_name = recevier[0];
			id = recevier[1];
			while(student.coursejudge(id,course_name)&&cho.equals("1")) {
				System.out.println("已存在该学生在该课程上的成绩");
				System.out.println("1.重新输入 2.退出");
				String t = br.readLine();
				while(t.equals("1")&&t.equals("2")) {
					System.out.println("输入有误，请重新输入");
					t=br.readLine();
				}
				if(t.equals("1")) {
					recevier = showChooseGradeView(course);
					course_name = recevier[0];
					id = recevier[1];	
				}
				else {
					return;
				}
				
			}
			while(!student.coursejudge(id,course_name)&&!cho.equals("1")&&!cho.equals("5")) {
				System.out.println("不存在该学生在该课程上的成绩");
				recevier = showChooseGradeView(course);
				course_name = recevier[0];
				id = recevier[1];	
			}
			switch(cho) {
			case "1":{
				System.out.println("选择将要添加的课程成绩：");
				String s = br.readLine();
				int grade = Integer.parseInt(s);
				while(!s.matches("\\d+")) {
					System.out.println("请输入数字而非字符!");
					grade = Integer.parseInt(s);
				}
				sql = "insert into mark(id,Course_name,grade) values('"+id+"','"+course_name+"',"+grade+")";
				carry(sql);
				db.close();		
				System.out.println("添加成功");
			};break;
			case "2":{
				System.out.println("选择将要修改的课程成绩：");
				int grade = Integer.parseInt(br.readLine());
				student.setGrade(grade, id, course_name);
				System.out.println("修改成功");
			};break;
			case "3":{
				System.out.println("成绩为:");
				System.out.println(student.getGrade(id, course_name));	
				System.out.println("查找成功");
			};break;
			case "4":{				
				sql = "delete from grade where id = '"+id+"' and Course_name = '"+course_name+"'";
				carry(sql);
				db.close();	
				System.out.println("删除成功");
			};break;
			default:{
				System.out.println("退出成功");
			}break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public String showMainMenu(String identity) {
		// TODO Auto-generated method stub
		//普通老师
		if(identity.equals("1")) {
			System.out.println("==========主菜单==========");
			System.out.println("\t1.设置本人课程");
			System.out.println("\t2.查看本人所有课程信息");
			System.out.println("\t3.设置学生成绩");
			System.out.println("\t4.退出主菜单");
			System.out.print("请选择菜单项：");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")) {
					System.out.println("输入有误，请重新输入");
					s=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
		//教务处老师
		else if(identity.equals("2")) {
			System.out.println("==========主菜单==========");
			System.out.println("\t1.修改学生信息");
			System.out.println("\t2.查询学生课程成绩");
			System.out.println("\t3.设置课程信息");
			System.out.println("\t4.退出主菜单");
			System.out.print("请选择菜单项：");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")) {
					System.out.println("输入有误，请重新输入");
					s=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
		//学生
		else {
			System.out.println("==========主菜单==========");
			System.out.println("\t1.查询本人信息");
			System.out.println("\t2.查询本人课程成绩");
			System.out.println("\t3.退出主菜单");
			System.out.print("请选择菜单项：");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")) {
					System.out.println("输入有误，请重新输入");
					s=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Stu_System mainSystem  = new Stu_System();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice;
		String Login_id;
		String Login_key;
		while(true) {
			System.out.println("=============用户选择=============");
			System.out.println("1.普通老师  2.教务处老师  3.学生用户");
			choice=br.readLine();
			while(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")) {
				System.out.println("选择有误，重新输入");
				choice=br.readLine();
			}
			break;
		}
		while(true) {
			System.out.println("请输入用户名");				
			Login_id=br.readLine();
			System.out.println("请输入密码");				
			Login_key=br.readLine();
			String identity = null;
			switch(choice) {
			case "1":{
				identity = "pttec_account";
			};break;
			case "2":{
				identity = "jwtec_account";
			};break;
			case "3":{
				identity = "stu_account";
			};break;		
			}
			if(mainSystem.login_judge(Login_id,Login_key,identity))
			{
				System.out.println("登陆成功");	
				break;
			}
			else
				System.out.println("不匹配");	
		}
		boolean control = true;
		while(control&&choice.equals("1")) {
			myLog.addLog(Loggable.TYPE.INFORMATION,"普通老师"+Login_id+"登陆成功");
			String s = mainSystem.showMainMenu("1");		
			switch(s) {
			case "1":{	//设置本人课程
				mainSystem.showEditCourseView(Login_id);
				myLog.addLog(Loggable.TYPE.INFORMATION,"普通老师"+Login_id+"设置了本人课程");
			};break;
			case "2":{//查看本人所有课程信息
				String courses[] = new String[10];
				courses = mainSystem.showTecCourseView(Login_id);
				for(int i = 0;courses[i]!=null;i++) {
					mainSystem.showSearchCourseView(courses[i]);
				}		
				myLog.addLog(Loggable.TYPE.INFORMATION,"普通老师"+Login_id+"查看了本人所有课程信息");
			};break;
			case "3":{//设置学生成绩	
				String courses[] = new String[10];
				courses = mainSystem.showTecCourseView(Login_id);
				mainSystem.showEditGradeView(courses);
				myLog.addLog(Loggable.TYPE.INFORMATION,"普通老师"+Login_id+"设置了学生成绩");
			};break;
			case "4":{//退出主菜单
				control = false;
				System.out.println("成功退出!");
				myLog.addLog(Loggable.TYPE.INFORMATION,"普通老师"+Login_id+"成功退出");
			};break;
			default:break;
			}
		}
		while(control&&choice.equals("2")) {
			myLog.addLog(Loggable.TYPE.INFORMATION,"教务老师"+Login_id+"登陆成功");
			String s = mainSystem.showMainMenu("2");			
			switch(s) {
			case "1":{	//修改学生信息	
				s = mainSystem.showInputStuIdView();
				mainSystem.showEditStuView(s);
				mainSystem.showEditStuResultView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"教务老师"+Login_id+"修改了学生信息");
			};break;
			case "2":{//查询学生课程成绩		
				s = mainSystem.showInputCnameView();
				mainSystem.showSearchCourseView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"教务老师"+Login_id+"查询了学生课程成绩");
			};break;
			case "3":{//设置课程信息	
				s = mainSystem.showInputTecIdView();
				mainSystem.showEditCourseView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"教务老师"+Login_id+"设置了课程信息");
			};break;
			case "4":{//退出主菜单
				control = false;
				System.out.println("成功退出!");
				myLog.addLog(Loggable.TYPE.INFORMATION,"教务老师"+Login_id+"成功退出");
			};break;
			default:break;
			}
		}
		while(control&&choice.equals("3")) {
			String s = mainSystem.showMainMenu("3");			
			switch(s) {
			case "1":{	//查询本人信息
				System.out.println("学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务");
				System.out.println(student.infotoString(Login_id));			
			};break;
			case "2":{//查询本人课程成绩	
				System.out.println("学号"+"\t" + "课程"+"\t" + "成绩");
				System.out.println(student.gradetoString(Login_id));		
			};break;
			case "3":{//退出主菜单
				control = false;
				System.out.println("成功退出!");
			};break;
			default:break;
			}
		}
	}
}

