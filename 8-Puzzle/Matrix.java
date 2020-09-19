package assignment1;

import java.util.Comparator;

public class Matrix implements Comparator< Matrix >{
	

	public Matrix parent;
	public int[][] matrix;
	
	// Blank tile cordinates
	public int x, y;
	
	// Number of misplaced tiles
	public int cost;
	
	// The number of moves so far
	public int level;
	
	//h,f,g value
	public int h,f,g,h2;
	public  int f2,f3,f4;
	
	//h2,f2
	
	/* @Override
	    public String toString() {
	        return "matrix ="+matrix;
	    }
	 
	    @Override
	    public int compareTo(Matrix o) {
	    	
	        return this.getf().compareTo(o.getf());
	    }*/
	    	
	    static Comparator<Matrix> getfComparator() {
	        return new Comparator<Matrix>() {

				@Override
				public int compare(Matrix o1, Matrix o2) {
					// TODO Auto-generated method stub
					return o1.getf() - o2.getf();
				}
	            // compare using attribute 1
	        };
	    }

	    static Comparator<Matrix> getf3Comparator() { //WHEN H=0
	        return new Comparator<Matrix>() {

				@Override
				public int compare(Matrix o1, Matrix o2) {
					// TODO Auto-generated method stub
					return o1.getf3() - o2.getf3();
				}
	            // compare using attribute 1
	        };
	    }
	    static Comparator<Matrix> getf2Comparator() {
	        return new Comparator<Matrix>() {

				@Override
				public int compare(Matrix o1, Matrix o2) {
					return o1.getf2() - o2.getf2();// TODO Auto-generated method stub
					
				}
	            // compare using attribute 2
	        };
	    }
	    static Comparator<Matrix> getf4Comparator() { //WHEN H=0
	        return new Comparator<Matrix>() {

				@Override
				public int compare(Matrix o1, Matrix o2) {
					// TODO Auto-generated method stub
					return o1.getf4() - o2.getf4();
				}
	            // compare using attribute 1
	        };
	    }
	    public Integer getf() {
	    	f=h+g;
	        return f;
	    }
	    public Integer getf2() {
	    	f2=h2+g;
	        return f2;
	    }
	    public Integer getf3() {
	    	f3=g;
	        return f;
	    }
	
	    public Integer getf4() {
	    	f4=getf2()+getf();
	        return f4;
	    }
	
	public Matrix(int[][] matrix, int level, Matrix parent) {
		this.parent = parent;
		this.matrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			this.matrix[i] = matrix[i].clone();
		}
		
				this.cost = Integer.MAX_VALUE;
				this.level = level;
				this.g=level;
				
			}

	@Override
	public int compare(Matrix o1, Matrix o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
			
}
