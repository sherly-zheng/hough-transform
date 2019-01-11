import java.io.*;
import java.util.Scanner;
public class HoughTransform{
	public xyCoord point;
	public int angleInDegree;
	public double angleInRadians;
	public int Hough_dist;
	public int Hough_angle;
	public int HoughMinVal;
	public int HoughMaxVal;
	public int HoughAry[][];
	
	public HoughTransform(int dist, int angle){
		angleInDegree = 0;
		angleInRadians = 0;
		Hough_dist = dist;
		Hough_angle = angle;
		HoughMinVal = 10000;
		HoughMaxVal = 0;
		HoughAry = new int[Hough_dist][Hough_angle];
	}
	
	public double computeDistance(){
		double x = point.getX() * 1.0;
		double y = point.getY() * 1.0;
		double t = angleInRadians - Math.atan(y/x) - (Math.PI/2);
		double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * Math.cos(t);
		return dist;
	}
	
	public void determineMinMax(){
		for(int i = 0; i < Hough_dist; i++){
			for(int j = 0; j < Hough_angle; j++){
				if(HoughAry[i][j] > HoughMaxVal)
					HoughMaxVal = HoughAry[i][j];
				if(HoughAry[i][j] < HoughMinVal)
					HoughMinVal = HoughAry[i][j];
			}
		}
	}
	
	public void prettyPrint(BufferedWriter outFile1){
		try{
			for(int i = 0; i < Hough_dist; i++){
				for(int j = 0; j < Hough_angle; j++){
					if(HoughAry[i][j] > 0)
						outFile1.write(".");
					else
						outFile1.write(" ");
				}
				outFile1.newLine();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void printHeader(BufferedWriter outFile2){
		try{
			outFile2.write(Hough_dist + " " + Hough_angle + " " + HoughMinVal + " " + HoughMaxVal);
			outFile2.newLine();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void printHoughAry(BufferedWriter outFile2){
		try{
			for(int i = 0; i < Hough_dist; i++){
				for(int j = 0; j < Hough_angle; j++){
					outFile2.write(HoughAry[i][j] + " ");
				}
				outFile2.newLine();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}