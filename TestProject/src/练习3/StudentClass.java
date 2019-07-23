package ��ϰ3;
import java.io.*;
import java.util.*;

public class StudentClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;             //�༶����
	static int capacity = 40;        //�������
	private Student students[];      //ѧ��
	private int size;                //ʵ������
	
	public StudentClass(String name, int size){
		this.name = name;
		this.size = size;
		students = new Student[capacity];
	}	 
    public String getName(){
		return name;
	}        
    public int getCapacity(){
		return capacity;
	}        
	public Student[] getStudents(){
		return students;
	}	
	public int getSize(){
		return size;
	}    
	public void setName(String name){
		this.name = name;
	}        
    public void setCapacity(int capacity){
		StudentClass.capacity = capacity;
	}        
	public void setSize(int size){
		if(size>capacity) {
			System.out.println("���ܳ�����ǰ����");
			return;
		}
		this.size = size;		
	}
	public void setStudents(Student[] students){
		for (int i = 0; i<size; i++)
			this.students[i] = new Student(students[i]);
		this.size=students.length;
	}	
	public String toString(){
		String s;
		s = " �༶:" + name +"\t" + "����:" + capacity + "\t" + "ʵ������:" + size +"\n\n";
		s = s + "ѧ��"+"\t" + "����"+"\t" + "Ӣ��"+"\t" + "��ѧ" + "\t" + "�����" +"\t" + "�ܳɼ�\n";	
		for (int i=0; i<size; i++)
		  s = s + students[i].getId()+"\t"+students[i].getName()+"\t"
		       +students[i].getEng()+"\t"+students[i].getMath()+"\t"
	               +students[i].getComp()+"\t"+students[i].getSum()+"\n";  
	   	return s;
	}
	public boolean addStudent(Student stu) {
		if(size<=capacity) {
			int i;
			for(i=0;i<size;i++) {
				if(stu.getId()==students[i].getId())//�ж�ѧ���ظ�
					break;
			}
			if(i==size) {
				students[size] = stu;
				size++;
				return true;
			}	
		}
		return false;
	}
	public void save(String filePath) throws IOException {
		File Class = new File(filePath); 
		if(!Class.exists())    
		{    
			try {    			
				Class.createNewFile();    		
			} catch (IOException e) {     			
				e.printStackTrace();    	
			}    
		}  
		ObjectOutputStream oos = null;
		try {    			
			oos = new ObjectOutputStream(new FileOutputStream(Class,true));
			oos.writeObject(this);    		
		} catch (IOException e) {     			
			e.printStackTrace();    	
		}finally {
			oos.close();
		}
	}
	public static StudentClass read(String filePath) throws IOException {
		File Class = new File(filePath); 
		ObjectInputStream ois = null;
		StudentClass sc = null;
		try {    			
			ois = new ObjectInputStream(new FileInputStream(Class));
			sc = (StudentClass)ois.readObject();	
		} catch (IOException e) {     			
			e.printStackTrace();    	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ois.close();
		}
		return sc;	
	}
	public Student getStudent(String id) { 
		Student s = null;
		for(int i = 0;i<size;i++){
			 if(students[i].getId().equals(id)) {
				 s=students[i];
			 }		 
		 }
		return s;
	}
	public Collection<Student>getStudents(String stuName){
		Vector<Student> ss = new Vector<Student>();
		Student s = null;
		for(int i = 0;i<size;i++){
			 if(students[i].getName().equals(stuName)) {
				 s = students[i];
				 ss.add(s);
			 }		 
		 }
//		for(Student s:students){
//			 if(s.getName().equals(stuName)) {
//				 ss.add(s);
//			 }
//		 }
		return ss;
	}
}
