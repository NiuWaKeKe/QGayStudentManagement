package stu_System;
import java.util.*;

public interface Views {
	//����������ʦ�Ŀγ���Ϣ
	String showInputTecIdView();
	void showEditCourseView(String id);
	
	//����ѧ���ɼ�
	String[] showTecCourseView(String id);
	String [] showChooseGradeView(String[] course);
	void showEditGradeView(String[] course);

	//�޸�ѧ����Ϣ
	String showInputStuIdView();
	void showEditStuView(String id);
	void showEditStuResultView(String id);

	//��ѯ���пγ̳ɼ�
	String showInputCnameView();
	void showSearchCourseView(String course_name);

	//��ʾ���˵�
	String showMainMenu(String identity);
}
