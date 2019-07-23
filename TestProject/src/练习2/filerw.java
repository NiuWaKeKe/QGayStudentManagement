package Á·Ï°2;
import java.io.*;

class PerformanceTest {
	static MyLog myLog = new MyLog();

	public static void main(String[] args) throws IOException {
		
		System.out.println("running, wait... ");
		int loopTime = 100000;
		double[] number = new double[loopTime];

		for (int i = 0; i < loopTime; i++)
			number[i] = Math.random();

		long startTime = System.currentTimeMillis();
		DataOutputStream dos = null;
		try {
			File test = new File("D:\\", "test.dat");		
			if(!test.exists())    
			{    
				try {    			
					test.createNewFile();    		
				} catch (IOException e) {     			
					e.printStackTrace();    	
				}    
			}  
			dos = new DataOutputStream(new FileOutputStream(test));
			for (int i = 0; i < loopTime; i++)
				dos.writeDouble(number[i]);
			
			long endTime = System.currentTimeMillis();
			myLog.addLog(Loggable.TYPE.INFORMATION,"Done, cost " + (endTime - startTime)+ " without buffer");

			startTime = System.currentTimeMillis();
			
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(test)));

			for (int i = 0; i < loopTime; i++) {
				dos.writeDouble(number[i]);				
				}
		
			endTime = System.currentTimeMillis();
			myLog.addLog(Loggable.TYPE.INFORMATION,"Done, cost " + (endTime - startTime)+ " with buffer");

		} catch (IOException e) {
			myLog.addLog(Loggable.TYPE.ERROR, e.getMessage());
		} finally {
			dos.close();
		}
		System.out.println("done, please check your log file.");
	}
}
