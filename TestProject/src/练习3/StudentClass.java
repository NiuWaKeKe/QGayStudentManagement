package 练习3;
import java.io.*;
import java.util.*;

public class StudentClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;             //班级名称
	static int capacity = 40;        //最大容量
	private Student students[];      //学生
	private int size;                //实际人数
	
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
			System.out.println("不能超过当前容量");
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
		s = " 班级:" + name +"\t" + "容量:" + capacity + "\t" + "实际人数:" + size +"\n\n";
		s = s + "学号"+"\t" + "姓名"+"\t" + "英语"+"\t" + "数学" + "\t" + "计算机" +"\t" + "总成绩\n";	
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
				if(stu.getId()==students[i].getId())//判断学号重复
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
