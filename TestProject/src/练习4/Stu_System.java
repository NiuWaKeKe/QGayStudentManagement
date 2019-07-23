package ��ϰ4;
import java.io.*;
import java.util.*;

public class Stu_System implements Service,Views{	
	/*��༶�����һ��ѧ��������������ѧ�š����䡢�Ա�ְ��
	����ѧ���޸�ѧ����Ϣ
	��������ѯѧ��
	ͨ��ѧ��ɾ��ѧ����Ϣ
	�û����ڲ�����ѧ����Ϣ��ɾ�Ĳ���ʵ��Service��Views�����ӿڡ�*/
	static StudentClass sc = new StudentClass("testClass", 0);

	public boolean addStudent(Student student) {
		// TODO Auto-generated method stub
		return sc.addStudent(student);
	}

	@Override
	public Student findStudentById(String id) {
		// TODO Auto-generated method stub
		return sc.getStudent(id);
	}

	@Override
	public void editStudent(Student student) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Student> findStudentByName(String name) {
		// TODO Auto-generated method stub
		return (ArrayList<Student>) sc.getStudents(name);
	}

	@Override
	public void deleteStudentById(String id) {
		// TODO Auto-generated method stub
		if(sc.deleteStudent(id)) {
			System.out.println("�ѳɹ�ɾ��");
			System.out.println(sc.toString());
		}		
		else
			System.out.println("ɾ��ʧ��");
	}
//�����û�����
	@Override
	public Student showAddStuView() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s;
		Student newstu = null;
		try {
			String[] split;
	        while(true) {
	        	System.out.println("����ѧ������Ϣ��ѧ�� ���� ���䣨1~100�� �Ա�(m/f) ְ��");
	        	s=br.readLine();
	            split = s.split(" ");
	            if(split.length!=5) {
	            	System.out.println("[warning:��ʽ����]");
	            }
	            else if(!split[3].equals("m")&&!split[3].equals("f")) {
	            	System.out.println("[warning:�Ա�ֻ��������'m'��'f']");
	            }
	            else if(!split[2].matches("\\d+")) {
	            	System.out.println("[warning:����ӦΪ����]");
	            }
	            else if(Integer.parseInt(split[2])<1||Integer.parseInt(split[2])>100) {
	            	System.out.println("[warning:���䳬����Χ������]");
	            }
	            else
	            	break;
	        }
	        newstu = new Student(split[0],split[1],split[2],split[3],split[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstu;
	}

	@Override
	public void showAddStuResultView(Student student) {
		// TODO Auto-generated method stub
		while(addStudent(student)==false) {
			if(Stu_System.sc.getSize()>=Stu_System.sc.getCapacity()) {
				System.out.println("[warning:�༶��������]");
			}
			else {
				System.out.println("[warning:ѧ���ظ�,����������]");
				student = showAddStuView();
			}
		}
		System.out.println("��ӳɹ�");
		System.out.println("����ӵ�ѧ����ϢΪ��");
		System.out.println("ѧ��"+"\t" + "����"+"\t" + "����"+"\t" + "�Ա�" + "\t" + "ְ��");
		System.out.println(student.toString());
	}
//�޸��û�����
	@Override
	public String showInputStuIdView() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s = null;
		System.out.print("�����뽫Ҫ�޸�ѧ����ѧ�ţ�");
		try {
			s=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Student showEditStuView(Student student) {
		// TODO Auto-generated method stub
		boolean control = true;
		while(control) {
			System.out.println("====��������༭���������====");
			System.out.println("1.����");
			System.out.println("2.����");
			System.out.println("3.�Ա�");
			System.out.println("4.ְ��");
			System.out.println("5.�˳��༭");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
			String s;
			try {
				s = br.readLine();
				while(s.equals("1")&&s.equals("2")&&s.equals("3")&&s.equals("4")) {
					System.out.println("������������������");
					s=br.readLine();
				}		
				switch(s) {
				case "1":{
					System.out.println("�޸�ǰ������Ϊ��"+student.getName());
					System.out.print("������Ϊ��");
					s=br.readLine();
					student.setName(s);
					System.out.println("�޸ĳɹ���");
				};break;
				case "2":{
					System.out.println("�޸�ǰ������Ϊ��"+student.getAge());
					System.out.print("������Ϊ��");
					s=br.readLine();
					student.setAge(s);
					System.out.println("�޸ĳɹ���");
				};break;
				case "3":{
					System.out.println("�޸�ǰ���Ա�Ϊ��"+student.getSex());
					System.out.print("���Ա�Ϊ��m/f ");
					s=br.readLine();
					while(!s.equals("m")&&!s.equals("f")) {
						System.out.println("[warning:������������������]");
						s=br.readLine();
					}
					student.setSex(s);
					System.out.println("�޸ĳɹ���");
				};break;
				case "4":{
					System.out.println("�޸�ǰ��ְ��Ϊ��"+student.getName());
					System.out.print("��ְ��Ϊ��");
					s=br.readLine();
					student.setName(s);
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
		
		return student;
	}

	@Override
	public void showEditStuResultView(Student student) {
		// TODO Auto-generated method stub
		System.out.println("�޸ĺ����ѧ����ϢΪ��");
		System.out.println("ѧ��"+"\t" + "����"+"\t" + "����"+"\t" + "�Ա�" + "\t" + "ְ��");
		System.out.println(student.toString());
	}

	@Override
	public String showSearchStuByNameView() {
		// TODO Auto-generated method stub
		System.out.print("������������");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s = null;
		try {
			s=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void showStuResultView(ArrayList<Student> students) {
		// TODO Auto-generated method stub
		if(students.isEmpty())
			System.out.println("��ѧ�������ڣ�");
		else {
			System.out.println("ѧ��"+"\t" + "����"+"\t" + "����"+"\t" + "�Ա�" + "\t" + "ְ��");
			for(Student stu: students){	
				System.out.println(stu.toString());
			}
		}
	}

	@Override
	public void showDeleteStuResultView(String id) {
		// TODO Auto-generated method stub
		deleteStudentById(id);
	}

	@Override
	public String showMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("==========���˵�==========");
		System.out.println("\t1.���ѧ��");
		System.out.println("\t2.�༭ѧ��");
		System.out.println("\t3.��ѯѧ��");
		System.out.println("\t4.ɾ��ѧ��");
		System.out.println("\t5.�˳�");
		System.out.print("��ѡ��˵��");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s = null;
		try {
			s = br.readLine();
			while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")&&!s.equals("5")) {
				System.out.println("������������������");
				s=br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Stu_System mainSystem  = new Stu_System();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean control = true;
		while(control) {
			String s = mainSystem.showMainMenu();
					
			switch(s) {
			case "1":{//���ѧ��
				mainSystem.showAddStuResultView(mainSystem.showAddStuView());
			};break;
			case "2":{	//�༭ѧ��	
				s = mainSystem.showInputStuIdView();
				Student stu = Stu_System.sc.getStudent(s);
				if(stu==null)
					System.out.println("��ѧ�������ڣ�");
				else {
					stu=mainSystem.showEditStuView(stu);
					mainSystem.showEditStuResultView(stu);
				}			
			};break;
			case "3":{//��ѯѧ��			
				s = mainSystem.showSearchStuByNameView();
				ArrayList<Student> group = mainSystem.findStudentByName(s);
				mainSystem.showStuResultView(group);				
			};break;
			case "4":{//ɾ��ѧ��				
				System.out.print("�����뽫Ҫɾ��ѧ����ѧ�ţ�");
				s=br.readLine();
				mainSystem.showDeleteStuResultView(s);
			};break;
			case "5":{//�˳�
				control = false;
				System.out.println("�ɹ��˳�!");
			};break;
			default:break;
			}
		}
	}
	
}
