package ��ϰ4;
import java.io.IOException;
import java.util.*;

public interface Service {
	//����û���Ϣ
		boolean addStudent(Student student);
		
		//�༭�û���Ϣ
		//����IDȡѧ��
		Student findStudentById(String id);
		//�༭ѧ����Ϣ
		void editStudent(Student student) throws IOException;
		
		//��ѯ�û�
		ArrayList<Student> findStudentByName(String name);
		
		//ɾ���û�
		void deleteStudentById(String id);
}
