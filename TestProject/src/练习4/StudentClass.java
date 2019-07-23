package 练习4;
import java.io.*;
import java.util.*;

public class StudentClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;             //班级名称
	static int capacity = 40;        //最大容量
	private ArrayList<Student> students;
	
	public StudentClass(String name, int size){
		this.name = name;
		students = new ArrayList<Student>();
	}	 
    public String getName(){
		return name;
	}        
    public int getCapacity(){
		return capacity;
	}        
	public ArrayList<Student> getStudents(){
		return students;
	}	
	public int getSize(){
		return students.size();
	}    
	public void setName(String name){
		this.name = name;
	}        
    public void setCapacity(int capacity){
		StudentClass.capacity = capacity;
	}        
	public String toString(){
		String s;
		s = " 班级:" + name +"\t" + "容量:" + capacity + "\t" + "实际人数:" + students.size() +"\n\n";
		s = s + "学号"+"\t" + "姓名"+"\t" + "年龄"+"\t" + "性别" + "\t" + "职务\n";	
		for (int i=0; i<students.size(); i++)
		  s = s + students.get(i).getId()+"\t"+students.get(i).getName()+"\t"
		       +students.get(i).getAge()+"\t"+students.get(i).getSex()+"\t"
	               +students.get(i).getRole()+"\n";  
	   	return s;
	}
	public boolean addStudent(Student stu) {
		int size = students.size();
		if(size<=capacity) {
			int i;
			for(i=0;i<size;i++) {
				if(stu.getId().equals(students.get(i).getId()))//判断学号重复
					break;
			}
			if(i==size) {
				students.add(stu);
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
		for(int i = 0;i<students.size();i++){
			 if(students.get(i).getId().equals(id)) {
				 s=students.get(i);
			 }		 
		 }
		return s;
	}
	public boolean deleteStudent(String id) { 
		for(int i = 0;i<students.size();i++){
			 if(students.get(i).getId().equals(id)) {
				 students.remove(i);
				 return true;
			 }		 
		 }
		return false;
	}
	public Collection<Student>getStudents(String stuName){
		ArrayList<Student> ss = new ArrayList<Student>();
		Student s = null;
		for(int i = 0;i<students.size();i++){
			 if(students.get(i).getName().equals(stuName)) {
				 s = students.get(i);
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
