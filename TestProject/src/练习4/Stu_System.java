package 练习4;
import java.io.*;
import java.util.*;

public class Stu_System implements Service,Views{	
	/*向班级中添加一个学生（包括姓名、学号、年龄、性别、职务）
	根据学号修改学生信息
	用姓名查询学生
	通过学号删除学生信息
	用户窗口操作和学生信息增删改查需实现Service和Views两个接口。*/
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
			System.out.println("已成功删除");
			System.out.println(sc.toString());
		}		
		else
			System.out.println("删除失败");
	}
//增加用户界面
	@Override
	public Student showAddStuView() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s;
		Student newstu = null;
		try {
			String[] split;
	        while(true) {
	        	System.out.println("输入学生的信息（学号 姓名 年龄（1~100） 性别(m/f) 职务）");
	        	s=br.readLine();
	            split = s.split(" ");
	            if(split.length!=5) {
	            	System.out.println("[warning:格式有误]");
	            }
	            else if(!split[3].equals("m")&&!split[3].equals("f")) {
	            	System.out.println("[warning:性别只允许输入'm'或'f']");
	            }
	            else if(!split[2].matches("\\d+")) {
	            	System.out.println("[warning:年龄应为数字]");
	            }
	            else if(Integer.parseInt(split[2])<1||Integer.parseInt(split[2])>100) {
	            	System.out.println("[warning:年龄超过范围，请检查]");
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
				System.out.println("[warning:班级人数已满]");
			}
			else {
				System.out.println("[warning:学号重复,请重新输入]");
				student = showAddStuView();
			}
		}
		System.out.println("添加成功");
		System.out.println("新添加的学生信息为：");
		System.out.println("学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务");
		System.out.println(student.toString());
	}
//修改用户界面
	@Override
	public String showInputStuIdView() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s = null;
		System.out.print("请输入将要修改学生的学号：");
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
			System.out.println("====输入你想编辑的内容序号====");
			System.out.println("1.姓名");
			System.out.println("2.年龄");
			System.out.println("3.性别");
			System.out.println("4.职务");
			System.out.println("5.退出编辑");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
			String s;
			try {
				s = br.readLine();
				while(s.equals("1")&&s.equals("2")&&s.equals("3")&&s.equals("4")) {
					System.out.println("输入有误，请重新输入");
					s=br.readLine();
				}		
				switch(s) {
				case "1":{
					System.out.println("修改前的名字为："+student.getName());
					System.out.print("新名字为：");
					s=br.readLine();
					student.setName(s);
					System.out.println("修改成功！");
				};break;
				case "2":{
					System.out.println("修改前的年龄为："+student.getAge());
					System.out.print("新年龄为：");
					s=br.readLine();
					student.setAge(s);
					System.out.println("修改成功！");
				};break;
				case "3":{
					System.out.println("修改前的性别为："+student.getSex());
					System.out.print("新性别为：m/f ");
					s=br.readLine();
					while(!s.equals("m")&&!s.equals("f")) {
						System.out.println("[warning:输入有误，请重新输入]");
						s=br.readLine();
					}
					student.setSex(s);
					System.out.println("修改成功！");
				};break;
				case "4":{
					System.out.println("修改前的职务为："+student.getName());
					System.out.print("新职务为：");
					s=br.readLine();
					student.setName(s);
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
		
		return student;
	}

	@Override
	public void showEditStuResultView(Student student) {
		// TODO Auto-generated method stub
		System.out.println("修改后的新学生信息为：");
		System.out.println("学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务");
		System.out.println(student.toString());
	}

	@Override
	public String showSearchStuByNameView() {
		// TODO Auto-generated method stub
		System.out.print("请输入姓名：");
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
			System.out.println("该学生不存在！");
		else {
			System.out.println("学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务");
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
		System.out.println("==========主菜单==========");
		System.out.println("\t1.添加学生");
		System.out.println("\t2.编辑学生");
		System.out.println("\t3.查询学生");
		System.out.println("\t4.删除学生");
		System.out.println("\t5.退出");
		System.out.print("请选择菜单项：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s = null;
		try {
			s = br.readLine();
			while(!s.equals("1")&&!s.equals("2")&&!s.equals("3")&&!s.equals("4")&&!s.equals("5")) {
				System.out.println("输入有误，请重新输入");
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
			case "1":{//添加学生
				mainSystem.showAddStuResultView(mainSystem.showAddStuView());
			};break;
			case "2":{	//编辑学生	
				s = mainSystem.showInputStuIdView();
				Student stu = Stu_System.sc.getStudent(s);
				if(stu==null)
					System.out.println("该学生不存在！");
				else {
					stu=mainSystem.showEditStuView(stu);
					mainSystem.showEditStuResultView(stu);
				}			
			};break;
			case "3":{//查询学生			
				s = mainSystem.showSearchStuByNameView();
				ArrayList<Student> group = mainSystem.findStudentByName(s);
				mainSystem.showStuResultView(group);				
			};break;
			case "4":{//删除学生				
				System.out.print("请输入将要删除学生的学号：");
				s=br.readLine();
				mainSystem.showDeleteStuResultView(s);
			};break;
			case "5":{//退出
				control = false;
				System.out.println("成功退出!");
			};break;
			default:break;
			}
		}
	}
	
}
