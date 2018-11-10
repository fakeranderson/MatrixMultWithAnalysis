

public class MMThread extends Thread {

	private long[] resultArray;
	public void run(long[] matrix1Row, long[][] matrix2) 
	{
		
		int matrixSize = matrix1Row.length;
		
//		if (aCols != bRows) {
//            throw new IllegalArgumentException("A:Rows: " + aCols + " did not match B:Columns " + bRows + ".");
//        }
		resultArray = new long[matrixSize];
		for(int i = 0; i < matrixSize; i++)
		{
			for(int j = 0; j < matrixSize; j++)
			{
				resultArray[i] += matrix1Row[j] * matrix2[j][i];					
			}
			
		}
		
	}
	public long[] getResult()
	{
		return resultArray;
	}
}
