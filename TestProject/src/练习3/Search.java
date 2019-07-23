package 练习3;

import java.io.*;
import java.util.Vector;

public class Search {
	public static void search() throws IOException{
		StudentClass sc = StudentClass.read("D:\\Class1.dat");		
		
		System.out.println("输入要查询的学生学号：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s=br.readLine();	
		Student stu = sc.getStudent(s);
		System.out.println("查找结果");
		if(stu==null)
			System.out.println("查无此人");
		else
			System.out.println(stu.toString());
		
		Vector<Student> ss;
		System.out.println("输入要查询的学生姓名：");	
		s=br.readLine();	
		ss = (Vector<Student>) sc.getStudents(s);
		
		System.out.println("查找结果");	
		if(ss.isEmpty())
			System.out.println("查无此人");
		else {
			for(Student st:ss)
				System.out.println(st.toString());
		}	
		System.out.println("查询完毕");
	}
	public static void main(String[] args) throws IOException {
		search();
	}
}
