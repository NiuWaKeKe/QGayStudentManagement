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
    
    //��¼�ж�
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
	
	//����������ʦ�Ŀγ���Ϣ
	@Override
	public String showInputTecIdView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select Teacher_id from course";
			carry_search(sql);
			while(judge==false) {
				System.out.println("�����뽫Ҫ�޸���ʦ�Ĺ��ţ�");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("Teacher_id").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("���޴���");
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
			System.out.println("==�γ�����==");
			while (ret.next()) {	
				System.out.println("=="+ret.getString("Course_name")+"==");
			}
			System.out.println("ѡ���������ͣ�1.ɾ�� 2.��� 3.�˳�");
			choice = br.readLine();
			while(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")) {
				System.out.println("�����������������");
				choice = br.readLine();
			}
			if(choice.equals("1")) {
				System.out.println("ѡ��ɾ���Ŀγ�����");
				String tobedelete = br.readLine();
				sql = "delete from course where Teacher_id='"+id+"' and Course_name='"+tobedelete+"'";
				carry(sql);
				db.close();
				
				sql = "delete from mark where Course_name = '"+tobedelete+"'";
				carry(sql);
				db.close();
				System.out.println("ɾ���ɹ�");
			}
			else if(choice.equals("2")) {
				System.out.println("ѡ����ӵĿγ�����");
				String tobeadd = br.readLine();
				sql = "insert into course(Course_name,Teacher_id) values('"+tobeadd+"','"+id+"')";
				carry(sql);
				db.close();
				System.out.println("��ӳɹ�");
			}
			else
				System.out.println("�˳��ɹ�");
				return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�޸�ѧ����Ϣ
	@Override
	public String showInputStuIdView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select id from student";
			carry_search(sql);
			while(judge==false) {
				System.out.println("�����뽫Ҫ�޸�ѧ����ѧ�ţ�");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("id").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("���޴���");
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
			System.out.println("====��������༭���������====");
			System.out.println("1.����");
			System.out.println("2.����");
			System.out.println("3.�Ա�");
			System.out.println("4.ְ��");
			System.out.println("5.�˳�");	
			String s;
			try {
				s = br.readLine();
				while(s.equals("1")&&s.equals("2")&&s.equals("3")&&s.equals("4")) {
					System.out.println("������������������");
					s=br.readLine();
				}		
				switch(s) {
				case "1":{
					System.out.println("�޸�ǰ������Ϊ��"+student.getInfo(id,"name"));
					System.out.print("������Ϊ��");
					s=br.readLine();
					student.setInfo(s,id,"name");
					System.out.println("�޸ĳɹ���");
				};break;
				case "2":{
					System.out.println("�޸�ǰ������Ϊ��"+student.getInfo(id,"age"));
					System.out.print("������Ϊ��");
					s=br.readLine();
					student.setInfo(s,id,"age");
					System.out.println("�޸ĳɹ���");
				};break;
				case "3":{
					System.out.println("�޸�ǰ���Ա�Ϊ��"+student.getInfo(id,"sex"));
					System.out.print("���Ա�Ϊ��m/f ");
					s=br.readLine();
					while(!s.equals("m")&&!s.equals("f")) {
						System.out.println("[warning:������������������]");
						s=br.readLine();
					}
					student.setInfo(s,id,"sex");
					System.out.println("�޸ĳɹ���");
				};break;
				case "4":{
					System.out.println("�޸�ǰ��ְ��Ϊ��"+student.getInfo(id,"role"));
					System.out.print("��ְ��Ϊ��");
					s=br.readLine();
					student.setInfo(s,id,"role");
					System.out.println("�޸ĳɹ���");
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
		System.out.println("�޸ĺ����ѧ����ϢΪ��");
		System.out.println("ѧ��"+"\t" + "����"+"\t" + "����"+"\t" + "�Ա�" + "\t" + "ְ��");
		System.out.println(student.infotoString(id));
	}

	//��ѯѡ���γ̳ɼ�
	@Override
	public String showInputCnameView() {
		// TODO Auto-generated method stub
		String s = null;	
		boolean judge = false;
		try {
			sql="select Course_name from course";
			carry_search(sql);
			while(judge==false) {
				System.out.println("�������ѯ�γ����ƣ�");
				s=br.readLine();	
				while(ret.next()) {
					if(ret.getString("Course_name").equals(s))
						judge = true;
				}
				if(judge==false) {
					System.out.println("û����ѡ��ÿγ�");
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
	public void showSearchCourseView(String course_name) {//ͨ���γ����Ʋ�ѯ�ÿγ�������ѧ���ĳɼ�
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
	//������ʦ�γ̵�ѧ���ɼ�
	@Override
	public String[] showTecCourseView(String id) {
		// TODO Auto-generated method stub
		sql = "select Course_name from course where Teacher_id = '"+id+"'";
		carry_search(sql);
		int i=0;
		String course[] = new String[10];
		try {
			System.out.println("==�γ�����==");
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
		System.out.println("ѡ��Ҫ��������(���ӡ��޸ġ���ѯ��ɾ��)�Ŀγ����ƣ�");
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
				System.out.println("ѡ����ʿγ�����");
				System.out.println("ѡ��Ҫ��������(���ӡ��޸ġ���ѯ��ɾ��)�Ŀγ����ƣ�");
				deliver[0] = br.readLine();//�γ�����
				for(int i=0;course[i]!=null;i++) {
					if(deliver[0].equals(course[i])) {
						jud = true;
						break;
					}
				}
			}				
			System.out.println("ѡ��Ҫ���гɼ�����(���ӡ��޸ġ���ѯ��ɾ��)��ѧ��id��");
			deliver[1] = br.readLine();//ѧ��id
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
				System.out.println("�����ڸ���ѧ��");
				System.out.println("ѡ��Ҫ���гɼ�����(���ӡ��޸ġ���ѯ��ɾ��)��ѧ��id��");
				deliver[1] = br.readLine();//ѧ��id
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
		}//�γ�����
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
				System.out.println("=ѧ��="+course[i]+"=");
				while(ret.next()) {		
					System.out.println("="+ret.getString("id")+"="+ret.getInt("grade")+"=");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db.close();
		System.out.println("����Գɼ������÷���");
		System.out.println("1.����  2.�޸�  3.��ѯ  4.ɾ�� 5.�˳�");
		try {
			String cho = br.readLine();
			while(cho.equals("1")&&cho.equals("2")&&cho.equals("3")&&cho.equals("4")&&cho.equals("5")) {
				System.out.println("������������������");
				cho=br.readLine();
			}
			String course_name = null;
			String id = null;
			String[] recevier  = new String[2];
			recevier = showChooseGradeView(course);
			course_name = recevier[0];
			id = recevier[1];
			while(student.coursejudge(id,course_name)&&cho.equals("1")) {
				System.out.println("�Ѵ��ڸ�ѧ���ڸÿγ��ϵĳɼ�");
				System.out.println("1.�������� 2.�˳�");
				String t = br.readLine();
				while(t.equals("1")&&t.equals("2")) {
					System.out.println("������������������");
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
				System.out.println("�����ڸ�ѧ���ڸÿγ��ϵĳɼ�");
				recevier = showChooseGradeView(course);
				course_name = recevier[0];
				id = recevier[1];	
			}
			switch(cho) {
			case "1":{
				System.out.println("ѡ��Ҫ��ӵĿγ̳ɼ���");
				String s = br.readLine();
				int grade = Integer.parseInt(s);
				while(!s.matches("\\d+")) {
					System.out.println("���������ֶ����ַ�!");
					grade = Integer.parseInt(s);
				}
				sql = "insert into mark(id,Course_name,grade) values('"+id+"','"+course_name+"',"+grade+")";
				carry(sql);
				db.close();		
				System.out.println("��ӳɹ�");
			};break;
			case "2":{
				System.out.println("ѡ��Ҫ�޸ĵĿγ̳ɼ���");
				int grade = Integer.parseInt(br.readLine());
				student.setGrade(grade, id, course_name);
				System.out.println("�޸ĳɹ�");
			};break;
			case "3":{
				System.out.println("�ɼ�Ϊ:");
				System.out.println(student.getGrade(id, course_name));	
				System.out.println("���ҳɹ�");
			};break;
			case "4":{				
				sql = "delete from grade where id = '"+id+"' and Course_name = '"+course_name+"'";
				carry(sql);
				db.close();	
				System.out.println("ɾ���ɹ�");
			};break;
			default:{
				System.out.println("�˳��ɹ�");
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
		//��ͨ��ʦ
		if(identity.equals("1")) {
			System.out.println("==========���˵�==========");
			System.out.println("\t1.���ñ��˿γ�");
			System.out.println("\t2.�鿴�������пγ���Ϣ");
			System.out.println("\t3.����ѧ���ɼ�");
			System.out.println("\t4.�˳����˵�");
			System.out.print("��ѡ��˵��");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")) {
					System.out.println("������������������");
					s=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
		//������ʦ
		else if(identity.equals("2")) {
			System.out.println("==========���˵�==========");
			System.out.println("\t1.�޸�ѧ����Ϣ");
			System.out.println("\t2.��ѯѧ���γ̳ɼ�");
			System.out.println("\t3.���ÿγ���Ϣ");
			System.out.println("\t4.�˳����˵�");
			System.out.print("��ѡ��˵��");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")) {
					System.out.println("������������������");
					s=br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
		}
		//ѧ��
		else {
			System.out.println("==========���˵�==========");
			System.out.println("\t1.��ѯ������Ϣ");
			System.out.println("\t2.��ѯ���˿γ̳ɼ�");
			System.out.println("\t3.�˳����˵�");
			System.out.print("��ѡ��˵��");
			br = new BufferedReader(new InputStreamReader(System.in));		
			String s = null;
			try {
				s = br.readLine();
				while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")) {
					System.out.println("������������������");
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
			System.out.println("=============�û�ѡ��=============");
			System.out.println("1.��ͨ��ʦ  2.������ʦ  3.ѧ���û�");
			choice=br.readLine();
			while(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")) {
				System.out.println("ѡ��������������");
				choice=br.readLine();
			}
			break;
		}
		while(true) {
			System.out.println("�������û���");				
			Login_id=br.readLine();
			System.out.println("����������");				
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
				System.out.println("��½�ɹ�");	
				break;
			}
			else
				System.out.println("��ƥ��");	
		}
		boolean control = true;
		while(control&&choice.equals("1")) {
			myLog.addLog(Loggable.TYPE.INFORMATION,"��ͨ��ʦ"+Login_id+"��½�ɹ�");
			String s = mainSystem.showMainMenu("1");		
			switch(s) {
			case "1":{	//���ñ��˿γ�
				mainSystem.showEditCourseView(Login_id);
				myLog.addLog(Loggable.TYPE.INFORMATION,"��ͨ��ʦ"+Login_id+"�����˱��˿γ�");
			};break;
			case "2":{//�鿴�������пγ���Ϣ
				String courses[] = new String[10];
				courses = mainSystem.showTecCourseView(Login_id);
				for(int i = 0;courses[i]!=null;i++) {
					mainSystem.showSearchCourseView(courses[i]);
				}		
				myLog.addLog(Loggable.TYPE.INFORMATION,"��ͨ��ʦ"+Login_id+"�鿴�˱������пγ���Ϣ");
			};break;
			case "3":{//����ѧ���ɼ�	
				String courses[] = new String[10];
				courses = mainSystem.showTecCourseView(Login_id);
				mainSystem.showEditGradeView(courses);
				myLog.addLog(Loggable.TYPE.INFORMATION,"��ͨ��ʦ"+Login_id+"������ѧ���ɼ�");
			};break;
			case "4":{//�˳����˵�
				control = false;
				System.out.println("�ɹ��˳�!");
				myLog.addLog(Loggable.TYPE.INFORMATION,"��ͨ��ʦ"+Login_id+"�ɹ��˳�");
			};break;
			default:break;
			}
		}
		while(control&&choice.equals("2")) {
			myLog.addLog(Loggable.TYPE.INFORMATION,"������ʦ"+Login_id+"��½�ɹ�");
			String s = mainSystem.showMainMenu("2");			
			switch(s) {
			case "1":{	//�޸�ѧ����Ϣ	
				s = mainSystem.showInputStuIdView();
				mainSystem.showEditStuView(s);
				mainSystem.showEditStuResultView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"������ʦ"+Login_id+"�޸���ѧ����Ϣ");
			};break;
			case "2":{//��ѯѧ���γ̳ɼ�		
				s = mainSystem.showInputCnameView();
				mainSystem.showSearchCourseView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"������ʦ"+Login_id+"��ѯ��ѧ���γ̳ɼ�");
			};break;
			case "3":{//���ÿγ���Ϣ	
				s = mainSystem.showInputTecIdView();
				mainSystem.showEditCourseView(s);
				myLog.addLog(Loggable.TYPE.INFORMATION,"������ʦ"+Login_id+"�����˿γ���Ϣ");
			};break;
			case "4":{//�˳����˵�
				control = false;
				System.out.println("�ɹ��˳�!");
				myLog.addLog(Loggable.TYPE.INFORMATION,"������ʦ"+Login_id+"�ɹ��˳�");
			};break;
			default:break;
			}
		}
		while(control&&choice.equals("3")) {
			String s = mainSystem.showMainMenu("3");			
			switch(s) {
			case "1":{	//��ѯ������Ϣ
				System.out.println("ѧ��"+"\t" + "����"+"\t" + "����"+"\t" + "�Ա�" + "\t" + "ְ��");
				System.out.println(student.infotoString(Login_id));			
			};break;
			case "2":{//��ѯ���˿γ̳ɼ�	
				System.out.println("ѧ��"+"\t" + "�γ�"+"\t" + "�ɼ�");
				System.out.println(student.gradetoString(Login_id));		
			};break;
			case "3":{//�˳����˵�
				control = false;
				System.out.println("�ɹ��˳�!");
			};break;
			default:break;
			}
		}
	}
}

