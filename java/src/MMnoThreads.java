import java.util.Scanner;
import java.security.SecureRandom;

public class MMnoThreads {

	public static void main(String[] args) 
	{
		int input;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a matrix size: ");
		input = scan.nextInt();
		
		scan.close();
		
		int numToAverage = 50;
		SecureRandom ran = new SecureRandom();
		int[][] matrix1 = new int[input][input];
		int[][] matrix2 = new int[input][input];
		int[][] resultMatrix = new int[input][input];
		
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
		resultMatrix = matrixMultiplication(matrix1, matrix2);
		toString(resultMatrix);		
		long startTime = System.nanoTime();
		for(int i = 0; i < numToAverage; i++)
		{
				resultMatrix = matrixMultiplication(matrix1, matrix2);
		}
		long nanos = System.nanoTime()-startTime;
		double seconds = nanos/1000000000.0;
		double aveSeconds = seconds/numToAverage;
		System.out.println("Total nanos: " + nanos);
		System.out.println("Total Seconds: " + seconds);
		//System.out.println("Average millis: " + aveTime);
		System.out.println("Average seconds: " + aveSeconds);
	}
	
	public static int[][] matrixMultiplication(int[][] array1, int[][] array2)
	{
		int aRows = array1.length;
		int aCols = array1[0].length;
		int bRows = array2.length;
		int bCols = array2[0].length;
		
		if (aCols != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aCols + " did not match B:Columns " + bRows + ".");
        }
		
		int[][] resultArray = new int[aRows][bCols];
		
		
		
		for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bCols; j++) { // bColumn
                for (int k = 0; k < aCols; k++) { // aColumn
                    resultArray[i][j] += array1[i][k] * array2[k][j];
                }
            }
        }
		return resultArray;
	}
	
	public static void toString(int[][] array)
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
