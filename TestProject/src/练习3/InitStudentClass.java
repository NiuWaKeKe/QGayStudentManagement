package 练习3;

import java.io.*;

public class InitStudentClass {
	public static void init() throws IOException {
		StudentClass sc = new StudentClass("幼儿园中班",5);
		int i = 0;
		Student []students = new Student[5];
		while(i<5) {		
			System.out.println("输入第"+(i+1)+"个学生的信息（学号 姓名 英语姓名 数学成绩 计算机成绩）");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
			String s=br.readLine();
            String[] split = s.split(" ");
            int j;
            for(j=0;j<split.length;j++) {
				if(split[j]==null)//判断学号重复
					break;
			}
            if(j!=split.length) {
            	System.out.println("格式有误");
            }
            else {
            	for(j=0;j<i;j++) {
    				if(split[0].equals(students[j].getId()))//判断学号重复
    					break;
    			}
    			if(j==i) {
    	            students[i] = new Student(split[0],split[1],Integer.parseInt(split[2]),Integer.parseInt(split[3]),Integer.parseInt(split[4]));   
    			    i++;
    			}else
    				System.out.println("学号重复");
            }	
		}
		System.out.println("班级信息初始化完毕");
		sc.setStudents(students);
		sc.save("D:\\Class1.dat");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
	}
}
