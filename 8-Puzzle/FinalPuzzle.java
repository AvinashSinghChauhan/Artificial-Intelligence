/*
Author : AVINASH SINGH CHAUHAN
This is main driving class.
Give the path to the input file(start.txt) and final result file(goal.txt) to run this assignemnt.
You can make changes in start.txt and goal.txt to see performance of the different approaches.
*/

package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class FinalPuzzle {

	//private static final String NULL = null;
	static int number_of_steps_optimal_path=0,number_of_steps_total_taken=0,number_of_steps_optimal_pathf2=0,number_of_steps_total_takenf2=0,number_of_steps_total_takenf3=0;
	static int number_of_steps_optimal_pathf3=0,number_of_steps_optimal_pathf4=0,number_of_steps_total_takenf4=0;;
	
	public int Manhattan(int[][] initial, int[][] goal)
	{
		 int distance = 0,x=0,y=0;
		 int n = initial.length;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
	                if (initial[i][j] != 0) {
	                	x= ((initial[i][j]-1)/3);
	                	 y=((initial[i][j]-1)%3);
	                
	                distance += Math.abs(x - i) + Math.abs(y - j);}
		}}
	//System.out.println(distance);
	return distance;
					}
    public boolean IsSafe(int l,int m,int n) //will check if the x,y combination is feasible in given size of matrix or not
    {
    	 if(l<n && m<n && l>=0 && m>=0 )
    	 return true;
    	 else
    		return false;
    }
	public int calculateCost(int[][] initial, int[][] goal) { //calculate cost as no of tiles displaced from their position
		int count = 0;
		int n = initial.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	public ArrayList<Matrix> movements(Matrix initial)
	{
		int n = initial.matrix.length;
		ArrayList<Matrix> child = new ArrayList<Matrix> ();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				           if(initial.matrix[i][j]==0)
				           {
				        	   
				           
				HashMap<Integer,Integer> hmap = new HashMap<Integer, Integer>();
				//hmap.put(i,j+1);
				//hmap.put(i, j+1);
				int g[]= {i-1,i,i+1,i};
				int h[]= {j,j+1,j,j-1};
				for(int s=0;s<=3;s++)
				{		
								
					int x=g[s];int y=h[s];
                  
                   if(IsSafe(g[s],h[s],n) )
                   {
                	   
                	   int newmatrix[][]=new int[n][n];
                	   for (int k = 0; k < initial.matrix.length; k++) { 
                                                                            //create a new matrix with value swapping with position(i,j)with (x,y)
                                                                            //and store the matrix in child arraylist
               			newmatrix[k] = initial.matrix[k].clone();
                	   }//System.out.print("x="+x+"y=" +y+"i= "+i+"j="+j);
                	// Swap value
               		newmatrix[x][y] = newmatrix[x][y] + newmatrix[i][j];
               		newmatrix[i][j] = newmatrix[x][y] - newmatrix[i][j];
               		newmatrix[x][y] = newmatrix[x][y] - newmatrix[i][j];
               		Matrix newchild=new Matrix(newmatrix,initial.level+1,initial);
               		child.add(newchild);
                   }  }     		
               		
                   } }                	   
                   }             
                        
               	return child;
                }
				
		
				
	
	public void solutionf(Matrix initial, int[][] goal)
	{
		ArrayList<Matrix> Openlist = new ArrayList<Matrix> ();
		ArrayList<Matrix> Closelist = new ArrayList<Matrix> ();
		Openlist.add(initial);
		//printMatrix(Openlist.get(0).matrix);
		 //1. Employee ids in ascending order
       
        while(Openlist!=null) {
        	Collections.sort(Openlist, Matrix.getfComparator());
        	
        	//Collections.sort(Openlist);
        	Matrix leastcostchild=Openlist.get(0);
        	//printMatrix(leastcostchild.matrix);
        	if(equalsMatrix(leastcostchild.matrix,goal))
        	{   number_of_steps_total_taken=Closelist.size()+ Openlist.size();
        	System.out.println("*********************** OPTIMAL PATH SUCCESS***************************");
        		printPathf(leastcostchild);
        		return;
        	}
        	ArrayList<Matrix> childMatrixList = new ArrayList<Matrix> ();
        	childMatrixList=movements(leastcostchild);
        	for(int l=0;l<childMatrixList.size();l++)
        	{
        		if(!Openlist.contains(childMatrixList.get(l)))
        				{
        			

        			childMatrixList.get(l).h=Manhattan(childMatrixList.get(l).matrix,goal);
        			//childMatrixList.get(l).cost=calculateCost(childMatrixList.get(1).matrix,goal);
        			Openlist.add(childMatrixList.get(l));
        			
        				}
        	}
        	Closelist.add(leastcostchild);
        	Openlist.remove(leastcostchild);
        }
        if(Openlist==null)
        {
        	System.out.println("*********************************************** OPTIMAL PATH UNSUCCESSFULL ****************************************************");
        }
	}
	public void solutionf2(Matrix initial, int[][] goal)
	{
		ArrayList<Matrix> Openlist = new ArrayList<Matrix> ();
		ArrayList<Matrix> Closelist = new ArrayList<Matrix> ();
		Openlist.add(initial);
		//printMatrix(Openlist.get(0).matrix);
		 //1. Employee ids in ascending order
       
        while(Openlist!=null) {
        	Collections.sort(Openlist, Matrix.getf2Comparator());
        	
        	//Collections.sort(Openlist);
        	Matrix leastcostchild=Openlist.get(0);
        	//System.out.println("it's working fine wait.............");
        	//printMatrix(leastcostchild.matrix);
        	if(equalsMatrix(leastcostchild.matrix,goal))
        	{   number_of_steps_total_takenf2=Closelist.size()+ Openlist.size();
        	System.out.println("*********************** OPTIMAL PATH SUCCESS***************************");
        		printPathf2(leastcostchild);
        		return;
        	}
        	
        	ArrayList<Matrix> childMatrixList = new ArrayList<Matrix> ();
        	childMatrixList=movements(leastcostchild);
        	for(int l=0;l<childMatrixList.size();l++)
        	{
        		if(!Openlist.contains(childMatrixList.get(l)))
        				{
        			

        			//childMatrixList.get(l).h=Manhattan(childMatrixList.get(1).matrix,goal);
        			childMatrixList.get(l).f2=calculateCost(childMatrixList.get(l).matrix,goal);
        			Openlist.add(childMatrixList.get(l));
        			
        				}
        	}
        	Closelist.add(leastcostchild);
        	Openlist.remove(leastcostchild);
        }
        if(Openlist==null)
        {
        	System.out.println("*********************************************** OPTIMAL PATH UNSUCCESSFULL ****************************************************");
        }
        
	}
	
	public void solutionf3(Matrix initial, int[][] goal)
	{
		ArrayList<Matrix> Openlist = new ArrayList<Matrix> ();
		ArrayList<Matrix> Closelist = new ArrayList<Matrix> ();
		Openlist.add(initial);
		//printMatrix(Openlist.get(0).matrix);
		 //1. Employee ids in ascending order
       
        while(Openlist!=null) {
        	Collections.sort(Openlist, Matrix.getf3Comparator());
        	
        	//Collections.sort(Openlist);
        	Matrix leastcostchild=Openlist.get(0);
        	//System.out.println("it's working fine wait.............");
        	//printMatrix(leastcostchild.matrix);
        	if(equalsMatrix(leastcostchild.matrix,goal))
        	{   number_of_steps_total_takenf3=Closelist.size()+ Openlist.size();
        	System.out.println("*********************** OPTIMAL PATH SUCCESS***************************");
        		printPathf3(leastcostchild);
        		return;
        	}
        	
        	ArrayList<Matrix> childMatrixList = new ArrayList<Matrix> ();
        	childMatrixList=movements(leastcostchild);
        	for(int l=0;l<childMatrixList.size();l++)
        	{
        		if(!Openlist.contains(childMatrixList.get(l)))
        				{
        			

        			//childMatrixList.get(l).h=Manhattan(childMatrixList.get(1).matrix,goal);
        			//childMatrixList.get(l).cost=calculateCost(childMatrixList.get(1).matrix,goal);
        			Openlist.add(childMatrixList.get(l));
        			
        				}
        	}
        	Closelist.add(leastcostchild);
        	Openlist.remove(leastcostchild);
        }
        if(Openlist==null)
        {
        	System.out.println("*********************************************** OPTIMAL PATH UNSUCCESSFULL ****************************************************");
        }
        
	}
	
	public void solutionf4(Matrix initial, int[][] goal)
	{
		ArrayList<Matrix> Openlist = new ArrayList<Matrix> ();
		ArrayList<Matrix> Closelist = new ArrayList<Matrix> ();
		Openlist.add(initial);
		//printMatrix(Openlist.get(0).matrix);
		 //1. Employee ids in ascending order
       
        while(Openlist!=null) {
        	Collections.sort(Openlist, Matrix.getf4Comparator());
        	
        	//Collections.sort(Openlist);
        	Matrix leastcostchild=Openlist.get(0);
        	//System.out.println("it's working fine wait.............");
        	//printMatrix(leastcostchild.matrix);
        	if(equalsMatrix(leastcostchild.matrix,goal))
        	{   number_of_steps_total_takenf4=Closelist.size()+ Openlist.size();
        	System.out.println("*********************** OPTIMAL PATH SUCCESS***************************");
        		printPathf4(leastcostchild);
        		return;
        	}
        	
        	ArrayList<Matrix> childMatrixList = new ArrayList<Matrix> ();
        	childMatrixList=movements(leastcostchild);
        	for(int l=0;l<childMatrixList.size();l++)
        	{
        		if(!Openlist.contains(childMatrixList.get(l)))
        				{
        			

        			childMatrixList.get(l).h=Manhattan(childMatrixList.get(1).matrix,goal);
        			childMatrixList.get(l).h2=calculateCost(childMatrixList.get(1).matrix,goal);
        			//childMatrixList.get(l).getf4();//not required cause at the time of sorting getf4()is called
        			Openlist.add(childMatrixList.get(l));
        			
        				}
        	}
        	Closelist.add(leastcostchild);
        	Openlist.remove(leastcostchild);
        }
        if(Openlist==null)
        {
        	System.out.println("*********************************************** OPTIMAL PATH UNSUCCESSFULL ****************************************************");
        }
        
	}
	
	public boolean equalsMatrix(int[][] matrix,int[][]goal)
	{
		if (matrix == null) {
				 
				 
				return (goal == null);
				 
				  }
				 
				 
				  if (matrix == null) {
				 
				 
				return false;
				 
				  }
				 
				 
				  if (matrix.length != matrix.length) {
				 
				 
				return false;
				 
				  }
				 
				 
				  for (int i = 0; i < matrix.length; i++) {
				 
				 
				if (!Arrays.equals(matrix[i], goal[i])) {
				 
				 
				    return false;
				 
				 
				}
				 
				  }
				 
				  return true;
				    }
	
	public void printMatrix(int[][] matrix) {
		// number_of_steps_optimal_path=0;
		//number_of_steps_optimal_path+=1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
				
				
			}
			System.out.println();
		}
		//System.out.println("Number of steps in optimal path="+number_of_steps_optimal_path);
	}
	
	
	public void printPathf(Matrix root) {
		
		if (root == null) {
			return;
		}
		
		printPathf(root.parent);
		//number_of_steps_optimal_path+=1;
		printMatrix(root.matrix);
		number_of_steps_optimal_path+=1;
		System.out.println();
	}
	public void printPathf2(Matrix root) {
		
		if (root == null) {
			return;
		}
		
		printPathf2(root.parent);
		//number_of_steps_optimal_pathf2+=1;
		printMatrix(root.matrix);
		number_of_steps_optimal_pathf2+=1;
		System.out.println();
	}
public void printPathf3(Matrix root) {
		
		if (root == null) {
			return;
		}
		
		printPathf3(root.parent);
		//number_of_steps_optimal_pathf2+=1;
		printMatrix(root.matrix);
		number_of_steps_optimal_pathf3+=1;
		System.out.println();
	}
public void printPathf4(Matrix root) {
	
	if (root == null) {
		return;
	}
	
	printPathf4(root.parent);
	//number_of_steps_optimal_pathf2+=1;
	printMatrix(root.matrix);
	number_of_steps_optimal_pathf4+=1;
	System.out.println();
}
	
	public static void main(String args[]) throws IOException
	{
		 Scanner sc = new Scanner(new BufferedReader(new FileReader("E:\\Study\\Programs\\8puzzle1\\src\\start.txt")));  //give the path to the input file
		 Scanner sc1 = new Scanner(new BufferedReader(new FileReader("E:\\Study\\Programs\\8puzzle1\\src\\goal.txt"))); //path to the goal state file
	      int [][] initial = new int[3][3];
	      int [][] goal =new int[3][3];
	      while(sc.hasNextLine()) {
	         for (int i=0; i<initial.length; i++) {
	            String[] line = sc.nextLine().trim().split("\t");
	            for (int j=0; j<line.length; j++) {
	               initial[i][j] = Integer.parseInt(line[j]);
	            }
	         }
	      }
	      while(sc1.hasNextLine()) {
		         for (int i=0; i<initial.length; i++) {
		            String[] line = sc1.nextLine().trim().split("\t");
		            for (int j=0; j<line.length; j++) {
		               goal[i][j] = Integer.parseInt(line[j]);
		            }
		         }
		      }
	     // System.out.println(Arrays.deepToString(myArray));
	      /*
		int[][] initial = { {1,2,3}, {4,5,6}, {7,0,8} };
		int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7,8,0} };

		*/
	Matrix rootMatrix=new Matrix(initial,0,null);
		Matrix rootmatrix=new Matrix(initial,0,null);
		FinalPuzzle fp=new FinalPuzzle();
		rootmatrix.f=fp.Manhattan(initial,goal);
		//rootmatrix.cost=fp.calculateCost(initial,goal);
		//***************************************************for h=  Manhattan distance **************************************************************
		
		System.out.println("***************   FOR HEURISTIC(h)= MANHATTEN DISTANCE     **********************");
		System.out.println("***********************    START STATE      ***************************");
		fp.printMatrix(rootmatrix.matrix);
		System.out.println("***********************    GOAL STATE      ***************************");
		fp.printMatrix(goal);
		long startTimef = System.currentTimeMillis();
		fp.solutionf(rootmatrix,goal);
		long endTimef = System.currentTimeMillis();
		long timeElapsed = endTimef - startTimef;

		System.out.println("***********************    OPTIMAL PATH LENGTH      ***************************");
		System.out.println(number_of_steps_optimal_path);
		System.out.println("***********************    TOTAL NUMBER OF NODES OPERATED(COST)      ***************************");
		System.out.println(number_of_steps_total_taken);
		System.out.println("***********************    EXECUTION TIME(IN Milli Sec)        ******************************** ");
		System.out.println(timeElapsed);
		
		System.out.println("************************************************************************************************ ");
		
		Matrix rootmatrixf2=new Matrix(initial,0,null);
		FinalPuzzle fp1=new FinalPuzzle();
		rootmatrixf2.f2=fp1.calculateCost(initial,goal);
		System.out.println("***************   FOR HEURISTIC(h)= NUMBER OF TILES DISPLACED     **********************");
		
		System.out.println("***********************    START STATE      ***************************");
		fp.printMatrix(rootmatrixf2.matrix);
		System.out.println("***********************    GOAL STATE      ***************************");
		fp.printMatrix(goal);
		long startTimef2 = System.currentTimeMillis();
		fp1.solutionf2(rootmatrixf2, goal);
		long endTimef2 = System.currentTimeMillis();
		long timeElapsedf2 = endTimef2 - startTimef2;
		System.out.println("***********************    OPTIMAL PATH LENGTH      ***************************");
		System.out.println(number_of_steps_optimal_pathf2);
		System.out.println("***********************    TOTAL NUMBER OF NODES OPERATED(COST)      ***************************");
		System.out.println(number_of_steps_total_takenf2);
		System.out.println("***********************    EXECUTION TIME(IN Milli Sec)        ******************************** ");
		System.out.println(timeElapsedf2);
		System.out.println("************************************************************************************************ ");
		
		Matrix rootmatrixf3=new Matrix(initial,0,null);
		FinalPuzzle fp3=new FinalPuzzle();
		rootmatrixf3.f3=fp3.calculateCost(initial,goal);
		System.out.println("***************   FOR HEURISTIC(h)= 0     **********************");
		
		System.out.println("***********************    START STATE      ***************************");
		fp3.printMatrix(rootmatrixf3.matrix);
		System.out.println("***********************    GOAL STATE      ***************************");
		fp3.printMatrix(goal);
		long startTimef3 = System.currentTimeMillis();
		fp1.solutionf3(rootmatrixf3, goal);
		long endTimef3 = System.currentTimeMillis();
		long timeElapsedf3 = endTimef3 - startTimef3;
		System.out.println("***********************    OPTIMAL PATH LENGTH      ***************************");
		System.out.println(number_of_steps_optimal_pathf3);
		System.out.println("***********************    TOTAL NUMBER OF NODES OPERATED(COST)      ***************************");
		System.out.println(number_of_steps_total_takenf3);
		System.out.println("***********************    EXECUTION TIME(IN Milli Sec)        ******************************** ");
		System.out.println(timeElapsedf3);
		System.out.println("************************************************************************************************ ");
		
		Matrix rootmatrixf4=new Matrix(initial,0,null);
		FinalPuzzle fp4=new FinalPuzzle();
		rootmatrixf4.f4=fp4.calculateCost(initial,goal);
		System.out.println("***************   FOR HEURISTIC(h)= Manhattan+displace tiles    **********************");
		
		System.out.println("***********************    START STATE      ***************************");
		fp.printMatrix(rootmatrixf4.matrix);
		System.out.println("***********************    GOAL STATE      ***************************");
		fp.printMatrix(goal);
		long startTimef4 = System.currentTimeMillis();
		fp1.solutionf4(rootmatrixf4, goal);
		long endTimef4 = System.currentTimeMillis();
		long timeElapsedf4 = endTimef4 - startTimef4;
		System.out.println("***********************    OPTIMAL PATH LENGTH      ***************************");
		System.out.println(number_of_steps_optimal_pathf4);
		System.out.println("***********************    TOTAL NUMBER OF NODES OPERATED(COST)      ***************************");
		System.out.println(number_of_steps_total_takenf4);
		System.out.println("***********************    EXECUTION TIME(IN Milli Sec)        ******************************** ");
		System.out.println(timeElapsedf4);
		System.out.println("************************************************************************************************ ");
	}
}
