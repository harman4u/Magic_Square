/** 
 	This program randomly generate a 3X3 matrix and check if the matrix is the
	magic square. It tests two features:
 		* The numbers 1,2,3...9 occur in the matrix.
 		* Sum of rows and columns and diagonals equal to each other.
*/

public class MagicSquare {
	static int[] rowSumArray = new int[3];
	static int[] columnSumArray = new int[3];
	static int[] diagonalSumArray = new int[2];
	static int rowSum;
	static int columnSum;
	static int primaryDiagonalSum = 0;
	static int secondaryDiagonalSum =0;

	public static void main(String[] args) {
		int[][] squareMatrix = new int[3][3];	
		boolean isMagicSquare = true;	
		do {			
			isMagicSquare = false;
			squareMatrix = generateMatrix();
			isMagicSquare = checkNumbers(squareMatrix);
			
			if(isMagicSquare) {
				calculateSums(squareMatrix);
				isMagicSquare = checkSums();
			}
		} while(!isMagicSquare);
				
		displayMatrix(squareMatrix);		
	}	
	
	
	/**
	 * This method generates a 2D array and assign values to it.
	 */
	public static int[][] generateMatrix() {
		int[][] array = new int[3][3];
		int number = 0;
		int random1,random2;
		//This while loop helps to assign value to each and every
		//space in the 3X3 matrix.
		while(number <10) {
			//Range of random1, random2 is 0-2.
			random1 = (int)(Math.random()*3);
			random2 = (int)(Math.random()*3);
			if(array[random1][random2] == 0) {
				array[random1][random2] = number;
				number++;
			}			
		}	
		return array;
	}
		
	
	/**
	 * This method checks whether all the numbers from 1-9 are present or not.
	 */
	public static boolean checkNumbers(int[][] array) {
		boolean allPresent = true;
		boolean flag = true;
		int[] numbers = {1,2,3,4,5,6,7,8,9};
		//Every Number in numbers Array is being compared with every element in 
		//squareMatrix.
		for(int i=0; i<numbers.length;i++) {
			flag = false;
			for(int j=0; j<array.length; j++) {
				for(int k=0; k<array[j].length;k++) {
					if(numbers[i] == array[j][k]){
						flag = true;
						break;
					}
				}
			}
			//When flag is equal to false at this point then it means numbers[i] 
			//was not found in the squareMatrix.(Hence not every element from
			//1 to 9 are present in squareMatrix.)
			if(flag == false) {
				allPresent = false;
				break;
			}
		}
		return allPresent;
	}
	
	
	/**
	 * Calculates Sum of Rows, Columns, Diagonal.
	 */
	public static void calculateSums(int[][] array) {
		primaryDiagonalSum = 0;
		secondaryDiagonalSum = 0;
		//These for loops help to calculate the sum of numbers in each row and column.
		for(int i=0; i<array.length; i++) {
			//Every time we switch to new row or column sum of numbers becomes zero. 
			rowSum = 0;
			columnSum = 0;
			for(int j=0; j<array[i].length; j++) {
				//Conditions for row Sum and Column sum in a Square Array. 
				rowSum = rowSum + array[i][j];
				columnSum = columnSum + array[j][i];
				//Condition for primary diagonal.
				if(i == j) {
					primaryDiagonalSum = primaryDiagonalSum + array[i][j];
				}
				//Condition for Secondary Diagonal
				 if ((i + j) == 2) {
		                secondaryDiagonalSum = secondaryDiagonalSum + array[i][j]; 
		        } 
			}
			//These two statements store the value of sum of each and column in another array.
			rowSumArray[i] = rowSum;
			columnSumArray[i] = columnSum;
		}
		
		diagonalSumArray[0] = primaryDiagonalSum;
		diagonalSumArray[1] = secondaryDiagonalSum;
	}	
	
	
	/**
	 * Verifies whether sum of rows and columns is equal or not.
	 */
	public static boolean checkSums(){	
		//Checking if all elements of rowSumArray and columnSumArray are equal or not.
		boolean flag = true;
		boolean isSumEqual = true;
		int verifyRowSum =rowSumArray[0];
		int verifyColumnSum = columnSumArray[0];
		for(int i=1; i<rowSumArray.length; i++) {
			//if statement verifies if every element in the array equal to another element.
			//If an element is found which is not equal to first element then it means
			//not all elements are equal.(Hence sum of all rows is not equal.)
			if (verifyRowSum != rowSumArray[i]) {
				flag = false;
				isSumEqual = false;
				break;
			}
			//If there exists an element which is not equal to first element then it
			//means that not all elements are equal.(Hence sum of columns is not equal.)
			if(verifyColumnSum != columnSumArray[i]) {
				flag = false;
				isSumEqual = false;
				break;
			}
		}
		
		//Checking if diagonalSum is equal to columnSum
		if((diagonalSumArray[0] != diagonalSumArray[1]) || (diagonalSumArray[0] != verifyColumnSum)) {
			isSumEqual = false;
		}
		return isSumEqual;
	}	
	
	
	/**
	 * Displays 2D Array
	 */
	public static void displayMatrix(int[][] array) {
		//These for loops Displays the matrix.
				System.out.println("The randomly generated matrix is:");
				for(int i=0; i<array.length; i++) {
					for(int j=0; j<array[i].length; j++) {
						System.out.print(array[i][j]+" ");
					}
					System.out.println();
				}
	}
}
