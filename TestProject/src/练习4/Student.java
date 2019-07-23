package 练习4;

import java.io.*;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;		//学号
	private String name;		//姓名
	private String age;		//年龄
	private String sex;		//性别
	private String role;		//职务
	
	public Student(String id,String name,String age,String sex,String role){
		this.id=id;
		this.name=name;
		this.age=age;
		this.sex=sex;
		this.role=role;
	}
	public Student(Student s) {
		// TODO Auto-generated constructor stub
		this.id=s.id;
		this.name=new String(s.name);
		this.age=s.age;
		this.sex=s.sex;
		this.role=s.role;		
	}
	public void setId(String id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setAge(String age){
		this.age=age;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public void setRole(String role){
		this.role=role;
	}    	

	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getAge(){
		return age;
	}
	public String getSex(){
		return sex;
	}
	public String getRole(){
		return role;
	}

	public String toString(){
		return getId() + "\t"+getName() + "\t"+getAge() + "\t"+getSex() +"\t"+getRole();
	}
	public boolean equals(Object x) { 
      	if (this.getClass() != x.getClass()) return false;      
      	Student b = (Student) x;     
      	return (this.getId().equals(b.getId())); 
    	}		
}