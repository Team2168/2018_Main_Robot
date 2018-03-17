package org.team2168.PID.trajectory;
import java.awt.Color;

import org.team2168.PID.pathplanner.FalconLinePlot;


/**
 * The purpose of this project is to generate functions which provide smooth 
 * paths between global waypoints. The approach this project takes is to use
 * quintic (5th order splines) hermite splines to create a continue path
 * between governing waypoints. 
 * 
 * The objective is to have a solution which interpolates the control points
 * provided by the user, and to also have C2 continuity (continuous 1st and 2nd
 * order derivatives).
 * 
 * Since this project is to be used for the mobile navigation of a differential
 * drive mobile ground robot. This algorithm also provides the position, velocity,
 * acceleration, and jerk motion profiles, for the left and right wheels, while
 * trying to maintain max velocity, max acceleration, and max jerk constraints.
 * 
 * @author Kevin Harrilal
 * Reference https://www.rose-hulman.edu/~finn/CCLI/Notes/day09.pdf
 * https://www.siggraph.org/education/materials/HyperGraph/modeling/splines/hermite.htm
 * http://paulbourke.net/miscellaneous/interpolation/
 * https://www.cs.utexas.edu/~fussell/courses/cs384g/lectures/lecture16-Interpolating_curves.pdf
 * https://www.youtube.com/watch?v=dxvmafuP9Wk
 * http://www.cs.cmu.edu/afs/cs/academic/class/15462-s10/www/lec-slides/lec06.pdf
 * 
 * 
 */



public class QuinticTrajectory
{
	//Path Variables
	public double[][] origPath;
	private Spline[] splines = null;
	
	Trajectory traj;
	TrajectoryGenerator.Config config;
	Trajectory.Pair leftRightTraj; 
	
	public double[] leftVel;
	public double[] rightVel;
	public double[][] leftPath;
	public double[][] rightPath;
	public double[][] rightVelocity;
	public double[][] leftVelocity;
	public double[][] rightAccel;
	public double[][] leftAccel;
	public double[][] rightJerk;
	public double[][] leftJerk;
	public double[] heading;
	
	double totalSplineLength = 0;

	public static void main(String[] args)
	{
		
		System.out.println("Hello World");
		
		
//		// straight path		
//		double[][] waypointPath = new double[][]{
//				{4, 3, Math.PI/2},
//				{4, 18, Math.PI/2},
//		};
		
//		//curve Path
//		double[][] waypointPath = new double[][]{
//				{0, 0, 1.4217},
//				{5, 8, 0.28363},
//				{9, 8, -0.3363},
//				{15, 5, -0.4363},
//				{25, 3, 0},
//				{30, 3, 0},
//
//				
//		};
		
//		//Square Path
		double[][] waypointPath = new double[][]{
		//	{5, 17, 0}, //For Right switch from center 
		//	{5, 19, Math.PI/2},
		//	{8.5, 23, Math.PI/2},
		//	{8.5, 24, Math.PI/2*0.987}
      
			//use for 1switch auto
//			{10, 24, 0},
//			{20, 24, 0},
//			{25, 20, -Math.PI/2+0.0001},
//			{25, 8, -Math.PI/2+0.0001},
//			{27,5, 0}
			
			{10, 24, 0},
			{24, 24, 0},
			{27, 20, -Math.PI/2+0.0001},
			{27, 12, -Math.PI/2+0.0001},
			{29, 10, 0}
			
			
		};
		
		double[][] waypointPath2 = new double[][]{
			{5, 17, 0}, //Right switch Path
			{6, 17, 0},
			{13, 12.5, 0}	
	};
		
//		//Square Path
//		double[][] waypointPath = new double[][]{
//				{5, 3, Math.PI/2},
//				{5, 18, Math.PI/2},
//				{10, 24, 0.0001},
//				{20, 24, 0},
//				{25, 18, -Math.PI/2+0.0001},
//				{25, 8, -Math.PI/2},
//				{20, 4, -Math.PI+0.0001},
//				{7, 4, -Math.PI},
//				
//		};
//		
//		//Clockwise Lap path
//		double[][] waypointPath = new double[][]{
//				{5, 8, Math.PI/2},
//				{5, 18, Math.PI/2},
//				{10, 24, 0.0001},
//				{20, 24, 0},
//				{25, 18, -Math.PI/2+0.0001},
//				{25, 8, -Math.PI/2},
//				{20, 4, -Math.PI+0.0001},
//				{10, 4, -Math.PI},
//				{5, 8,  Math.PI/2+0.0001},
//				{28, 29,  Math.PI/4},
//				
//		};
		
		QuinticTrajectory quinticPath= new QuinticTrajectory(waypointPath);
		quinticPath.calculate();
		//System.out.println(quinticPath.traj.toStringEuclidean());

		QuinticTrajectory quinticPath2= new QuinticTrajectory(waypointPath2);
		quinticPath2.calculate();
		
		for(int i = 0; i<quinticPath.traj.getNumSegments(); i++)
			System.out.println(quinticPath.getHeadingDeg()[i]);

		
		
		//Lets create a bank image
		FalconLinePlot fig3 = new FalconLinePlot(waypointPath, null, Color.black);
		fig3.yGridOn();
		fig3.xGridOn();
		fig3.setYLabel("Y (feet)");
		fig3.setXLabel("X (feet)");
		fig3.setTitle("Quintic Path (Robot Complete Path)");
		//fig3.setTitle("Top Down View of FRC Field (30ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");


//		//force graph to show 1/2 field dimensions of 24.8ft x 27 feet
		double fieldWidth = 32;
		fig3.setXTic(0, 40, 1);
		fig3.setYTic(0, fieldWidth, 1);
		fig3.addData(quinticPath.rightPath, Color.magenta);
		fig3.addData(quinticPath.leftPath, Color.blue);

		//fig3.addData(quinticPath2.leftPath, Color.blue);
		//fig3.addData(quinticPath2.rightPath, Color.magenta);
		//fig3.addData(waypointPath2, null, Color.black);
 
		fig3.addData(new double[][]{{4.667, 3}}, Color.black);
		
		//outline field perimeter
		double[][] edge = {{1,16+27/2.0-2-4.81/12},{1,16-27/2.0+2+4.81/12}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{1,16+27/2.0-2-4.81/12},{1+2+10.9/12,16+27/2.0}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{1,16-27/2.0+2+4.81/12},{1+2+10.9/12, 16-27/2.0}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{1+2+10.9/12,16+27/2.0},{39,16+27/2.0}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{1+2+10.9/12, 16-27/2.0},{39,16-27/2.0}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{28,16+27/2.0}, {28, 16-27/2.0}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{12+7.6/12, 16+27/2.0-7-1.5/12}, {16+3.2/12, 16+27/2.0-7-1.5/12}, {16+3.2/12, 16-27/2.0+7+1.5/12}, {12+7.6/12, 16-27/2.0+7+1.5/12}, {12+7.6/12, 16+27/2.0-7-1.5/12}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{12+7.6/12,16+27/2.0-11-7.75/12}, {12+7.6/12-3.5, 16+27/2.0-11-7.75/12}, {12+7.6/12-3.5, 16-27/2.0+11+7.75/12}, {12+7.6/12, 16-27/2.0+11+7.75/12}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{12+7.6/12-3.5, 16+1.875},{12+7.6/12-3.5, 16-1.875}};
		fig3.addData(edge, Color.black);
		
		edge = new double[][] {{28,16+28/2.0-5-11.6/12}, {28-1-11.7/12, 16+28/2.0-5-11.6/12}, {28-1-11.7/12,  16+28/2.0-5-11.6/12-2-1.4/12}, 
			{28-1-11.7/12-3-2.9/12, 16+28/2.0-5-11.6/12-2-1.4/12}, {28-1-11.7/12-3-2.9/12, 16-28/2.0+5+11.6/12+2+1.4/12}, {28-1-11.7/12, 16-28/2.0+5+11.6/12+2+1.4/12},
			{28-1-11.7/12, 16-28/2.0+5+11.6/12}, {28, 16-28/2.0+5+11.6/12}};
		fig3.addData(edge, Color.black);
		
		//Velocity
//		
//				FalconLinePlot fig4 = new FalconLinePlot(new double[][]{{0.0,0.0}});
//				fig4.yGridOn();
//				fig4.xGridOn();
//				fig4.setYLabel("Velocity (ft/sec)");
//				fig4.setXLabel("time (seconds)");
//				fig4.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
//				fig4.addData(quinticPath.rightVelocity, Color.magenta);
//				fig4.addData(quinticPath.leftVelocity, Color.cyan);
//				
//				
//				//Velocity
//				FalconLinePlot fig5 = new FalconLinePlot(new double[][]{{0.0,0.0}});
//				fig5.yGridOn();
//				fig5.xGridOn();
//				fig5.setYLabel("accel (ft^2/sec)");
//				fig5.setXLabel("time (seconds)");
//				fig5.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
//				fig5.addData(quinticPath.rightAccel, Color.magenta);
//				fig5.addData(quinticPath.leftAccel, Color.cyan);
//				
//				

		
	}
	
	
	
	public QuinticTrajectory(double[][] path)
	{
		
		this.origPath = doubleArrayCopy(path);
		
		config = new TrajectoryGenerator.Config();
	    config.dt = .02;
	    config.max_acc = 4.0;
	    config.max_jerk = 30.0;
	    config.max_vel = 8.0;
	}
	
	public void calculate()
	{
		long start = System.nanoTime();
		//Calculate Total Arc Length Using Distance
		quinticSplines(origPath);
		
		//calculate total distance
		for (int i = 0; i < splines.length; ++i)
			this.totalSplineLength += splines[i].arc_length_;
		
		

		// Generate a smooth trajectory over the total distance.
	    this.traj = TrajectoryGenerator.generate(config,
	            TrajectoryGenerator.SCurvesStrategy, 0.0, this.origPath[0][2],
	            this.totalSplineLength, 0.0, this.origPath[0][2]);
	   

	    long end = System.nanoTime();
//	    System.out.println("Final time is " + (end - start)/1e9 + " seconds");
//	    
//	    System.out.println("Total Length = " + this.totalSplineLength);
//		System.out.println("Traj num segments=" + this.traj.getNumSegments() );
//	    
	    
	    fixHeadings();
	   
	    leftRightTraj = makeLeftAndRightTrajectories(traj, 2.0);
	    
	    
	    
//	    System.out.println("Left = " + leftRightTraj.left.getNumSegments());
	    
	    copyWheelPaths();
	    
//	    print(this.leftPath);
//	    print(this.leftVelocity);
	    
		
	}
	
	public static void print(double[] path)
	{
		System.out.println("X: \t Y:");

		for(double u: path)
			System.out.println(u);
	}



	/**
	 * Prints Cartesian Coordinates to the System Output as Column Vectors in the Form X	Y
	 * @param path
	 */
	public static void print(double[][] path)
	{
		System.out.println("X: \t Y:");

		for(double[] u: path)
			System.out.println(u[0]+ "\t" +u[1]);
	}

	/**
	 * Performs a deep copy of a 2 Dimensional Array looping thorough each element in the 2D array
	 * 
	 * BigO: Order N x M
	 * @param arr
	 * @return
	 */
	public static double[][] doubleArrayCopy(double[][] arr)
	{

		//size first dimension of array
		double[][] temp = new double[arr.length][arr[0].length];

		for(int i=0; i<arr.length; i++)
		{
			//Resize second dimension of array
			temp[i] = new double[arr[i].length];

			//Copy Contents
			for(int j=0; j<arr[i].length; j++)
				temp[i][j] = arr[i][j];
		}

		return temp;

	}
	
	  public Spline[] quinticSplines(double[][] path) {
	    if (path.length < 2) {
	      return null;
	    }

	    // Compute the total length of the path by creating splines for each pair
	    // of waypoints.
	    this.splines = new Spline[path.length - 1];
	    double[] spline_lengths = new double[splines.length];
	    
	    double total_distance = 0;
	    
	    for (int i = 0; i < splines.length; ++i) 
	    {
	      splines[i] = new Spline();
	      if (!Spline.reticulateSplines(path[i][0],path[i][1],path[i][2],
	    		  path[i+1][0],path[i+1][1],path[i+1][2], splines[i])) {
	        return null;
	      }
	      spline_lengths[i] = splines[i].calculateLength();
	      total_distance += spline_lengths[i];
	    }
	    
	    return splines;
	

}
	  private void fixHeadings()
	  {
	  // Assign headings based on the splines.
	    int cur_spline = 0;
	    double cur_spline_start_pos = 0;
	    double length_of_splines_finished = 0;
	    for (int i = 0; i < traj.getNumSegments(); ++i) {
	      double cur_pos = traj.getSegment(i).pos;

	      boolean found_spline = false;
	      while (!found_spline) {
	        double cur_pos_relative = cur_pos - cur_spline_start_pos;
	        if (cur_pos_relative <= this.splines[cur_spline].arc_length_) {
	          double percentage = splines[cur_spline].getPercentageForDistance(
	                  cur_pos_relative);
	          traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
	          double[] coords = splines[cur_spline].getXandY(percentage);
	          traj.getSegment(i).x = coords[0];
	          traj.getSegment(i).y = coords[1];
	          found_spline = true;
	        } else if (cur_spline < splines.length - 1) {
	          length_of_splines_finished += this.splines[cur_spline].arc_length_;
	          cur_spline_start_pos = length_of_splines_finished;
	          ++cur_spline;
	        } else {
	          traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
	          double[] coords = splines[splines.length - 1].getXandY(1.0);
	          traj.getSegment(i).x = coords[0];
	          traj.getSegment(i).y = coords[1];
	          found_spline = true;
	        }
	      }
	    }

	  
}
	  
	  /**
	   * Generate left and right wheel trajectories from a reference.
	   *
	   * @param input The reference trajectory.
	   * @param wheelbase_width The center-to-center distance between the left and
	   * right sides.
	   * @return [0] is left, [1] is right
	   */
	  static Trajectory.Pair makeLeftAndRightTrajectories(Trajectory input,
	          double wheelbase_width) {
	    Trajectory[] output = new Trajectory[2];
	    output[0] = input.copy();
	    output[1] = input.copy();
	    Trajectory left = output[0];
	    Trajectory right = output[1];

	    for (int i = 0; i < input.getNumSegments(); ++i) {
	      Trajectory.Segment current = input.getSegment(i);
	      double cos_angle = Math.cos(current.heading);
	      double sin_angle = Math.sin(current.heading);

	      Trajectory.Segment s_left = left.getSegment(i);
	      s_left.x = current.x - wheelbase_width / 2 * sin_angle;
	      s_left.y = current.y + wheelbase_width / 2 * cos_angle;
	      if (i > 0) {
	        // Get distance between current and last segment
	        double dist = Math.sqrt((s_left.x - left.getSegment(i - 1).x)
	                * (s_left.x - left.getSegment(i - 1).x)
	                + (s_left.y - left.getSegment(i - 1).y)
	                * (s_left.y - left.getSegment(i - 1).y));
	        s_left.pos = left.getSegment(i - 1).pos + dist;
	        s_left.vel = dist / s_left.dt;
	        s_left.acc = (s_left.vel - left.getSegment(i - 1).vel) / s_left.dt;
	        s_left.jerk = (s_left.acc - left.getSegment(i - 1).acc) / s_left.dt;
	      }

	      Trajectory.Segment s_right = right.getSegment(i);
	      s_right.x = current.x + wheelbase_width / 2 * sin_angle;
	      s_right.y = current.y - wheelbase_width / 2 * cos_angle;
	      if (i > 0) {
	        // Get distance between current and last segment
	        double dist = Math.sqrt((s_right.x - right.getSegment(i - 1).x)
	                * (s_right.x - right.getSegment(i - 1).x)
	                + (s_right.y - right.getSegment(i - 1).y)
	                * (s_right.y - right.getSegment(i - 1).y));
	        s_right.pos = right.getSegment(i - 1).pos + dist;
	        s_right.vel = dist / s_right.dt;
	        s_right.acc = (s_right.vel - right.getSegment(i - 1).vel) / s_right.dt;
	        s_right.jerk = (s_right.acc - right.getSegment(i - 1).acc) / s_right.dt;
	      }
	    }

	    return new Trajectory.Pair(output[0], output[1]);
	  }
	  
	  
	  
	  void copyWheelPaths()
	  {
		  this.leftPath = new double[this.leftRightTraj.left.getNumSegments()][2];
		  this.rightPath = new double[this.leftRightTraj.right.getNumSegments()][2];
		  this.leftVelocity = new double[this.leftRightTraj.left.getNumSegments()][2];
		  this.rightVelocity = new double[this.leftRightTraj.right.getNumSegments()][2];
		  this.leftAccel = new double[this.leftRightTraj.left.getNumSegments()][2];
		  this.rightAccel = new double[this.leftRightTraj.right.getNumSegments()][2];
		  this.leftJerk = new double[this.leftRightTraj.left.getNumSegments()][2];
		  this.rightJerk = new double[this.leftRightTraj.right.getNumSegments()][2];
		  
		  this.leftVel = new double[this.leftRightTraj.right.getNumSegments()];
		  this.rightVel =  new double[this.leftRightTraj.right.getNumSegments()];
		  this.heading =  new double[this.leftRightTraj.right.getNumSegments()];
		  
		  //copy left
		  for( int i =0; i < this.leftRightTraj.left.getNumSegments(); i++)
		  {
			  this.leftPath[i][0] = this.leftRightTraj.left.getSegment(i).x;
			  this.leftPath[i][1] = this.leftRightTraj.left.getSegment(i).y;
			  this.rightPath[i][0] = this.leftRightTraj.right.getSegment(i).x;
			  this.rightPath[i][1] = this.leftRightTraj.right.getSegment(i).y;
			  
			  this.leftVelocity[i][0] = this.leftRightTraj.left.getSegment(i).dt*i;
			  this.leftVelocity[i][1] = this.leftRightTraj.left.getSegment(i).vel;
			  this.rightVelocity[i][0] = this.leftRightTraj.right.getSegment(i).dt*i;
			  this.rightVelocity[i][1] = this.leftRightTraj.right.getSegment(i).vel;
			  
			  this.leftVel[i] = this.leftVelocity[i][1];
			  this.rightVel[i] = this.rightVelocity[i][1];
			  this.heading[i] =	ChezyMath.boundAngleNeg180to180Degrees(360-this.traj.segments_[i].heading*180/Math.PI);
			  
			  
			  this.leftAccel[i][0] = this.leftRightTraj.left.getSegment(i).dt*i;
			  this.leftAccel[i][1] = this.leftRightTraj.left.getSegment(i).acc;
			  this.rightAccel[i][0] = this.leftRightTraj.right.getSegment(i).dt*i;
			  this.rightAccel[i][1] = this.leftRightTraj.right.getSegment(i).acc;
			  
			  this.leftJerk[i][0] = this.leftRightTraj.left.getSegment(i).dt*i;
			  this.leftJerk[i][1] = this.leftRightTraj.left.getSegment(i).jerk;
			  this.rightJerk[i][0] = this.leftRightTraj.right.getSegment(i).dt*i;
			  this.rightJerk[i][1] = this.leftRightTraj.right.getSegment(i).jerk;
			  
		  }
	  }
	  
	  public double[] getLeftVel()
	  {
		  
		  return this.leftVel;
	  }
	  
	  public double[] getRightVel()
	  {
		  return this.rightVel;
	  }
	  
	  public double[] getHeadingDeg()
	  {
		  return this.heading;
	  }
	  
	  
	  
	}
	  
