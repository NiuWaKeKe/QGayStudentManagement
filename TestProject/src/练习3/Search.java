package ��ϰ3;

import java.io.*;
import java.util.Vector;

public class Search {
	public static void search() throws IOException{
		StudentClass sc = StudentClass.read("D:\\Class1.dat");		
		
		System.out.println("����Ҫ��ѯ��ѧ��ѧ�ţ�");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String s=br.readLine();	
		Student stu = sc.getStudent(s);
		System.out.println("���ҽ��");
		if(stu==null)
			System.out.println("���޴���");
		else
			System.out.println(stu.toString());
		
		Vector<Student> ss;
		System.out.println("����Ҫ��ѯ��ѧ��������");	
		s=br.readLine();	
		ss = (Vector<Student>) sc.getStudents(s);
		
		System.out.println("���ҽ��");	
		if(ss.isEmpty())
			System.out.println("���޴���");
		else {
			for(Student st:ss)
				System.out.println(st.toString());
		}	
		System.out.println("��ѯ���");
	}
	public static void main(String[] args) throws IOException {
		search();
	}
}
