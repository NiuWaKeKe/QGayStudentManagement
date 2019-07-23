package Á·Ï°1;
import java.io.*;
class Test {
	public static void main(String[] args) throws Exception {
		int[] numbers = new int[3];
		int i =0;
		while(i<3) {
			System.out.print("Type in number"+(i+1)+":");
			try {
				numbers[i] = Keyboard.getInterger();
				i++;
				}
			catch(Exception e) {
				System.out.println(e);
			}
		}	
		System.out.println("Finsh!");
	}
	
}
public class Keyboard{
	static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
	public static int getInterger() throws Exception{
		try {
			return(Integer.valueOf(inputStream.readLine().trim()).intValue());
		}
		catch(Exception e) {
			throw new Exception("ÊäÈëÊý×ÖÓÐÎó");
		}
	}
}