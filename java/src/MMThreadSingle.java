

public class MMThreadSingle extends Thread {

	private long[][] resultArray;
	public long[][] run(long[][] array1, long[][] array2) 
	{

		int size = array1[0].length;

		resultArray = new long[size][size];
		for (int i = 0; i < size; i++) { // aRow
			for (int j = 0; j < size; j++) { // bColumn
				for (int k = 0; k < size; k++) { // aColumn
					resultArray[i][j] += array1[i][k] * array2[k][j];
				}
			}


		}
			return resultArray;
	
	}
}
