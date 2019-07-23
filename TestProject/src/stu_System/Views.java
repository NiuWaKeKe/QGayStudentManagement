package stu_System;
import java.util.*;

public interface Views {
	//设置所有老师的课程信息
	String showInputTecIdView();
	void showEditCourseView(String id);
	
	//设置学生成绩
	String[] showTecCourseView(String id);
	String [] showChooseGradeView(String[] course);
	void showEditGradeView(String[] course);

	//修改学生信息
	String showInputStuIdView();
	void showEditStuView(String id);
	void showEditStuResultView(String id);

	//查询所有课程成绩
	String showInputCnameView();
	void showSearchCourseView(String course_name);

	//显示主菜单
	String showMainMenu(String identity);
}
