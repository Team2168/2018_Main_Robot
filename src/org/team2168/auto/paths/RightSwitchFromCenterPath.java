package org.team2168.auto.paths;

import org.team2168.PID.trajectory.OneDimensionalMotionProfiling;
import org.team2168.PID.trajectory.QuinticTrajectory;

public class RightSwitchFromCenterPath {
	public static double[] leftVelPathQuintic;
    public static double[] rightVelPathQuintic;
   
    public static OneDimensionalMotionProfiling motion;
   
	public void LeftSwitchFromCenter() {
	 
    
    double[][] waypointPath = new double[][]{
    	//{5, 17, Math.PI/2}, //For Right switch from center 
		//{5, 19, Math.PI/2},
		//{8.5, 23, Math.PI/2},
		//{8.5, 24, Math.PI/2}
		
		{5, 17, 0}, //Right switch Path
		{7, 17, 0},
		{11, 20.5, 0},
		{12, 20.5, 0}
	};


	QuinticTrajectory quinticPath = new QuinticTrajectory(waypointPath);
	quinticPath.calculate();
	this.leftVelPathQuintic = quinticPath.getLeftVel();
	this.rightVelPathQuintic = quinticPath.getRightVel();
	}


}



