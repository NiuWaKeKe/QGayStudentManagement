package 练习4;
import java.util.*;

public interface Views {
	//增加用户界面
	Student showAddStuView();
	void showAddStuResultView(Student student);

	//修改用户界面
	String showInputStuIdView();
	Student showEditStuView(Student student);
	void showEditStuResultView(Student student);

	//查询用户界面;根据（名字）查询
	String showSearchStuByNameView();
	void showStuResultView(ArrayList<Student> students);

	//删除用户界面,通过ID删除
	void showDeleteStuResultView(String id);
	//显示主菜单
	String showMainMenu();

}
