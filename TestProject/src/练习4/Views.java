package ��ϰ4;
import java.util.*;

public interface Views {
	//�����û�����
	Student showAddStuView();
	void showAddStuResultView(Student student);

	//�޸��û�����
	String showInputStuIdView();
	Student showEditStuView(Student student);
	void showEditStuResultView(Student student);

	//��ѯ�û�����;���ݣ����֣���ѯ
	String showSearchStuByNameView();
	void showStuResultView(ArrayList<Student> students);

	//ɾ���û�����,ͨ��IDɾ��
	void showDeleteStuResultView(String id);
	//��ʾ���˵�
	String showMainMenu();

}
