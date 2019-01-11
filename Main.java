import java.io.*;
import java.util.Scanner;
public class Main{
	public static void main(String args[]){
		int numRows, numCols, minVal, maxVal;
		try{
			Scanner inFile = new Scanner(new FileReader(args[0]));
			BufferedWriter outFile1 = new BufferedWriter(new FileWriter(args[1]));
			BufferedWriter outFile2 = new BufferedWriter(new FileWriter(args[2]));
			
			numRows = inFile.nextInt();
			numCols = inFile.nextInt();
			minVal = inFile.nextInt();
			maxVal = inFile.nextInt();
			
			int Hough_angle = 180;
			int Hough_dist = ((int) Math.sqrt(Math.pow(numRows, 2) + Math.pow(numCols, 2))) * 2;
			int offset = Hough_dist/2;
			HoughTransform HoughSpace = new HoughTransform(Hough_dist, Hough_angle);

			ImageProcessing image = new ImageProcessing(numRows, numCols, minVal, maxVal);
			image.loadImage(inFile);
			
			int deg, distInt;
			double dist;
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numCols; j++){
					if(image.imgAry[i][j] > 0){
						HoughSpace.point = new xyCoord(i, j);
						HoughSpace.angleInDegree = 0;
						while(HoughSpace.angleInDegree <= 179){
							deg = HoughSpace.angleInDegree;
							HoughSpace.angleInRadians = (deg * (Math.PI / 180.00));
							dist = HoughSpace.computeDistance();
							distInt = ((int) dist) + offset;
							HoughSpace.HoughAry[distInt][deg]++;
							HoughSpace.angleInDegree++;
						}
					}
				}
			}
	
			HoughSpace.prettyPrint(outFile1);
			HoughSpace.determineMinMax();
			HoughSpace.printHeader(outFile2);
			HoughSpace.printHoughAry(outFile2);
			inFile.close();
			outFile1.close();
			outFile2.close();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}