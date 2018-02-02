package org.team2168.PID.trajectory;

import java.awt.Color;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

public class OneDirectionMotionProfiling {

	
	

	double q0 = 0.0;
	double q1 = 10.0;
	double v0 = 0.0;
	double v1 =  0.0;
	double a0 = 0.0;
	double a1 = 0.0;
	double t0 = 0.0;
	double t1= 3.5;
	double vMax = 4.0;
	double aMax = 5.0;
	double jMax = 30.0;
	
	double vMin = -vMax;
	double aMin = -aMax;
	double jMin = -jMax;
	
    double	T = t1 - t0;
	double  h = q1 - q0;
	
	double A0 = q0;
	double A1 = v0;
	double A2 = 1/2 * a0;
	double A3 = 1/(2* Math.pow(T, 3)) * (20*h - (8*v1 + 12*v0)*T - (3*a0 - a1)*Math.pow(T, 2));
	double A4 = 1/(2* Math.pow(T, 4)) * (-30*h +(14*v1 + 16*v0)*T + (3*a0 - 2*a1)* Math.pow(T, 2));
	double A5 = 1/(2* Math.pow(T, 5)) * (12*h - 6*(v1+v0)*T + (a1-a0)* Math.pow(T, 2));
	
	double Tj1 = 0;
	double Ta = 0;
	double Tj2 = 0;
	double Td = 0;
	private double[] rightPath;
	private double[] leftPath;
	 
	
	public void S_curves(){
		if (((vMax- v0) * jMax ) < Math.pow(aMax, 2)){
			Tj1 = Math.sqrt((vMax - v0)/jMax);
			Ta = 2*Tj1;
		}
		else {
			Tj1 = aMax/jMax;
			Ta = Tj1 + ((vMax-v0)/aMax);
		}	
		
		if (((vMax- v1) * jMax ) < Math.pow(aMax, 2)){
			Tj2 = Math.sqrt((vMax - v1)/jMax);
			Td = Tj2 + ((vMax-v1)/aMax);
		}
		else {
			Tj2 = aMax/jMax;
			Td = Tj2 + ((vMax-v1)/aMax);
		}	
		
		//duration of constant velocity
		double Tv = (q1-q0)/vMax - (Ta/2)*(1+v0/vMax) - (Td/2)*(1+(v1/vMax));
	
		t1 = Ta + Tv + Td;
		T = t1 + t0;
	
		// vector array thing
		double spacing = 1/100;
		//Linspace time = new Linspace(t0, spacing, t1);
		
		double[] time = new double[(int)((t1-t0)/spacing)];
		double[] pos = new double[(int)((t1-t0)/spacing)];
		double[] vel = new double[(int)((t1-t0)/spacing)];		
		double[] acc = new double[(int)((t1-t0)/spacing)];
		double[] jerk = new double[(int)((t1-t0)/spacing)];
		double[][] leftPath;
		double[][] rightPath;
				

		//Lets create a bank image
				FalconLinePlot fig3 = new FalconLinePlot(time, pos ,Color.black);
				fig3.yGridOn();
				fig3.xGridOn();
				fig3.setYLabel("Y (feet)");
				fig3.setXLabel("X (feet)");
				fig3.setTitle("Top Down View of FRC Field (30ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");
				fig3.setSize(600,400);
				
		OneDirectionMotionProfiling oneDirection= new OneDirectionMotionProfiling();
		//force graph to show 1/2 field dimensions of 24.8ft x 27 feet
		double fieldWidth = 27.0;
		fig3.setXTic(0, 54, 1);
		fig3.setYTic(0, fieldWidth, 1);
		fig3.addData(oneDirection.rightPath, Color.magenta);
		fig3.addData(oneDirection.leftPath, Color.cyan);
		
		//Velocity
				FalconLinePlot fig4 = new FalconLinePlot(new double[][]{{0.0,0.0}});
				fig4.yGridOn();
				fig4.xGridOn();
				fig4.setYLabel("Velocity (ft/sec)");
				fig4.setXLabel("time (seconds)");
				fig4.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
				fig4.addData(vel, Color.magenta);
				

		
		for(int i=0; i<time.length; i++)
		{
			time[i]=i*1/100 + t0;
		}
		
		//Compute actual min/max a and vc
		double aLimA = jMax*Tj1;
		double aLimD = -jMax*Tj2;
		double vLim = v0 + (Ta-Tj1)*aLimA;
		
		// Calculation of trajectory for q1 > q2
		
		
		
		
		
		
		
		
		
		
		
		// ??
		for(int i=1;i<time.length;i++)
		{
			 if( i <= Tj1) {
				 pos[i]=q0 + v0*(i*1/100 + t0) + jMax*Math.pow((i*1/100 + t0),3)/6;
			     vel[i]= v0 + jMax*Math.pow((i*1/100 + t0), 2)/2;
			     acc[i] = jMax*(i*1/100 + t0);
			     jerk[i] = jMax;}
			    else if ((i*1/100 + t0) > Tj1 && (i*1/100 + t0) <= Ta - Tj1) 
			    {
			     pos[i] = q0 + v0*(i*1/100 + t0) + (aLimA/6)*3*Math.pow((i*1/100 + t0), 2) - 3*Tj1*(i*1/100 + t0) + Math.pow(Tj1, 2);
			     vel[i] = v0 + aLimA*((i*1/100 + t0) - (Tj1/2));
			     acc[i] = jMax*Tj1;
			     jerk[i] = 0; }
			    else if ((i*1/100 + t0)>  Ta - Tj1 && (i*1/100 + t0) <= Ta)
			    {
			    pos[i] = q0 + (vLim + v0)*Ta/2 - vLim*(Ta-(i*1/100 + t0)) - jMin*(Math.pow((Ta-(i*1/100 + t0)),3)/6);
			    vel[i] = vLim + jMin*(Math.pow((Ta-(i*1/100 + t0)),2)/2);
			    acc[i] = -jMin*(Ta-(i*1/100 + t0));
			    jerk[i] = jMin;
			    }
			    else if ((i*1/100 + t0) > Ta && (i*1/100 + t0) <= Ta + Tv)
			    {
			    	pos[i] = q0 + (vLim + v0)*(Ta/2)+vLim*((i*1/100 + t0)-Ta);
			        vel[i] = vLim;
			        acc[i] = 0;
			        jerk[i] = 0;
			    }
			    else if ((i*1/100 + t0) > T-Td && (i*1/100 + t0) <= T-Td+Tj2)     
			    {
			        pos[i] = q1 - (vLim +v1)*(Td/2) + vLim*((i*1/100 + t0)-T+Td)-jMax*(Math.pow(((i*1/100 + t0)-T+Td),3)/6);
			        vel[i] = vLim - jMax*(Math.pow(((i*1/100 + t0)-T+Td),2)/2);
			        acc[i] = -jMax*((i*1/100 + t0)-T+Td);
			        jerk[i] = jMin;
			    }
			    else if ((i*1/100 + t0) > T-Td+Tj2 && (i*1/100 + t0) <= T-Tj2)
			    {
			        pos[i] = q1 - (vLim+v1)*(Td/2)+vLim*((i*1/100 + t0)-T+Td) + (aLimD/6)
			        		*(3*Math.pow(((i*1/100 + t0)-T+Td),2) - 3*Tj2*((i*1/100 + t0)-T+Td) + Math.pow(Tj2,2));
			        vel[i] = vLim+aLimD*((i*1/100 + t0)-T+Td-Tj2/2);
			        acc[i] = aLimD;
			        jerk[i] = 0;
			    }
			    else if ((i*1/100 + t0) > T-Tj2 && (i*1/100 + t0) <=T)  
			    {
			    	pos[i] = q1-v1*(T-(i*1/100 + t0))-jMax*(Math.pow((T-(i*1/100 + t0)),3)/6);
			        vel[i] = v1+jMax*(Math.pow((T-(i*1/100 + t0)),2)/2);
			        acc[i] = -jMax*(T-(i*1/100 + t0));
			        jerk[i] = jMax;  
			    }
		}
				
	}
	
	
	public static void main(String[] args){


		OneDirectionMotionProfiling oneDirection= new OneDirectionMotionProfiling();
		oneDirection.S_curves();
		
	}
}




//	double[] P = {A5, A4, A3, A2, A1, A0};
	
//	double[] Pd = new double[P.length-1];{
//	for(int i = 0; i < P.length-1; i++)
//		Pd[i] = P[i]*(P.length-i-1);
//	}

//	double[] Pdd = new double [Pd.length-1]; {
//	for(int i = 0; i < Pd.length-1; i++)
//		Pdd[i] = Pd[i]*(Pd.length-i-1);
//	}
	
	

