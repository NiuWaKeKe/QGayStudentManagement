package ��ϰ3;

import java.io.*;

public class InitStudentClass {
	public static void init() throws IOException {
		StudentClass sc = new StudentClass("�׶�԰�а�",5);
		int i = 0;
		Student []students = new Student[5];
		while(i<5) {		
			System.out.println("�����"+(i+1)+"��ѧ������Ϣ��ѧ�� ���� Ӣ������ ��ѧ�ɼ� ������ɼ���");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
			String s=br.readLine();
            String[] split = s.split(" ");
            int j;
            for(j=0;j<split.length;j++) {
				if(split[j]==null)//�ж�ѧ���ظ�
					break;
			}
            if(j!=split.length) {
            	System.out.println("��ʽ����");
            }
            else {
            	for(j=0;j<i;j++) {
    				if(split[0].equals(students[j].getId()))//�ж�ѧ���ظ�
    					break;
    			}
    			if(j==i) {
    	            students[i] = new Student(split[0],split[1],Integer.parseInt(split[2]),Integer.parseInt(split[3]),Integer.parseInt(split[4]));   
    			    i++;
    			}else
    				System.out.println("ѧ���ظ�");
            }	
		}
		System.out.println("�༶��Ϣ��ʼ�����");
		sc.setStudents(students);
		sc.save("D:\\Class1.dat");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		init();
	}
}
