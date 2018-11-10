import java.security.SecureRandom;
import java.util.Scanner;

public class SingleThreadExec {

	public static void main(String[] args) throws InterruptedException {
		int input;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a matrix size: ");
		input = scan.nextInt();
		scan.close();
		MMThreadSingle thread = new MMThreadSingle();
		SecureRandom ran = new SecureRandom();
		long[][] matrix1 = new long[input][input];
		long[][] matrix2 = new long[input][input];
		long[][] resultMatrix = new long[input][input];
		
		for(int i = 0; i < input; i++) //generate 2 random matrices
		{			
			for(int j = 0; j < input; j++)
			{
				matrix1[i][j] = ran.nextInt(1000);
				matrix2[i][j] = ran.nextInt(1000);
			}
		}

		toString(matrix1);
		toString(matrix2);
		resultMatrix = thread.run(matrix1,matrix2);
		toString(resultMatrix);
		/*
		FOR EACH ROW IN MATRIX1: 
		create a thread, run the thread (multiply single row by single column)
		append all of the resulting rows into result matrix
		*/
		int numToAverage = 50;
		long startTime = System.nanoTime();
		
		for(int k = 0; k < numToAverage; k++)
		{
			resultMatrix = thread.run(matrix1, matrix2);
		}
		
		long nanos = System.nanoTime()-startTime;
		double seconds = nanos/1000000000.0;
		double aveSeconds = seconds/numToAverage;
		System.out.println("Total nanos: " + nanos);
		System.out.println("Total Seconds: " + seconds);
		System.out.println("Average seconds: " + aveSeconds);
	}
	
	public static void toString(long[][] array)
	{
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array.length; j++)
			{
				System.out.print(array[i][j] + " ");
			}System.out.println("\n");
		}
	}
}
