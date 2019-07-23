package 练习4;
import java.io.IOException;
import java.util.*;

public interface Service {
	//添加用户信息
		boolean addStudent(Student student);
		
		//编辑用户信息
		//根据ID取学生
		Student findStudentById(String id);
		//编辑学生信息
		void editStudent(Student student) throws IOException;
		
		//查询用户
		ArrayList<Student> findStudentByName(String name);
		
		//删除用户
		void deleteStudentById(String id);
}
