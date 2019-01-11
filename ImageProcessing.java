import java.io.*;
import java.util.Scanner;
public class ImageProcessing{
	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	public int imgAry[][];
	
	public ImageProcessing(int r, int c, int min, int max){
		numRows = r;
		numCols = c;
		minVal = min;
		maxVal = max;
		imgAry = new int[numRows][numCols];
	}
	
	public void loadImage(Scanner inFile){
		try{
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numCols; j++){
					imgAry[i][j] = inFile.nextInt();
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}