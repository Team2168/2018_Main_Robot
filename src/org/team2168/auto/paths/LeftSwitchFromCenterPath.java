package org.team2168.auto.paths;

import org.team2168.PID.trajectory.OneDimensionalMotionProfiling;
import org.team2168.PID.trajectory.QuinticTrajectory;

public class LeftSwitchFromCenterPath {
	public static double[] leftVelPathQuintic;
    public static double[] rightVelPathQuintic;
   
    public static OneDimensionalMotionProfiling motion;
   
	public void LeftSwitchFromCenter() {
	 
    
    double[][] waypointPath = new double[][]{
    {10, 18, Math.PI/2}, //For l switch from center 
	{4.2, 22,Math.PI*.999}
    
   // {10, 18, Math.PI}, //For l switch from center (new path
	//{4.2, 22,Math.PI*1.4999}
    
};


	QuinticTrajectory quinticPath = new QuinticTrajectory(waypointPath);
	quinticPath.calculate();
	this.leftVelPathQuintic = quinticPath.getLeftVel();
	this.rightVelPathQuintic = quinticPath.getRightVel();
	}


}
