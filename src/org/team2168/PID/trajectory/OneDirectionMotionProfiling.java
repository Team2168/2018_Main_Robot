package org.team2168.PID.trajectory;

import java.awt.Color;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import org.team2168.PID.pathplanner.*;

public class OneDirectionMotionProfiling {

	
	

	double q0 = 5.0;
	double q1 = 25;
	double v0 = 0.0
			;
	double v1 =  0.0;
	double a0 = 0.0;
	double a1 = 0.0;
	double t0 = 0.0;
	double t1= 10.0;
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
	double Tv = 0;
	
	// vector array thing
	double spacing = 1000.0;
	
	double[] time;
	double[] pos;
	double[] vel;		
	double[] acc;
	double[] jerk; 
	 
	
	public void S_curves()
	{
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
		
		
		// Tj1 = 1/6
		// Ta = 4/5+1/6 = 29/30
		//Tj2 = 1/6 
		// Td =	4/5 + 1/6 = 29/30	
		// Tv = 5/4 - (29/60)*(1/4) - 29/60 = 155/240 = 31/48
		// t1 = 681/240 = 1.22
		
		//duration of constant velocity
		Tv = (q1-q0)/vMax - (Ta/2)*(1+v0/vMax) - (Td/2)*(1+(v1/vMax));
		t1 = Ta + Tv + Td;
		T = t1 - t0;
		if (Tv < 0){
			double ratio = Ta/(T/2);
			Tj1 = Tj1/ratio;
			Tj2 = Tj2/ratio;
			Ta = T/2;
			Td = T/2;
			Tv = 0.0;
		}
		
		System.out.println(Ta);
		System.out.println(T);
		System.out.println(Tv);
		 time = new double[(int)((T)*spacing)]; 
		 pos = new double[(int)((T)*spacing)];
		 vel = new double[(int)((T)*spacing)];		
		 acc = new double[(int)((T)*spacing)];
		 jerk = new double[(int)((T)*spacing)];
		
		//Linspace time = new Linspace(t0, spacing, t1);
		

		//Compute actual min/max a and vc
		double aLimA = jMax*Tj1;
		double aLimD = -jMax*Tj2;
		double vLim = v0 + (Ta-Tj1)*aLimA;
		System.out.println(vLim+ " hi");
		System.out.println(Tj1 + " " + Ta);
		//alima = 5
		//vlim = 4
								
		for(int i=0; i<time.length; i++)
		{
			time[i]=i*1.0/spacing + t0;
		}
		

		// Calculation of trajectory for q1 > q2
		// ??
		for(int i=0;i<time.length;i++)
		{
			//System.out.println(time[i]);
			 if( time[i] <= Tj1) {//t<1/6
				 pos[i]=q0 + v0*(time[i] + t0) + jMax*Math.pow((time[i] + t0),3)/6;
			     vel[i]= v0 + jMax*Math.pow((time[i] + t0), 2)/2;
			     acc[i] = jMax*(time[i] + t0);
			     jerk[i] = jMax;}
			    else if ((time[i] + t0) > Tj1 && (time[i] + t0) <= Ta - Tj1) //1/6<t<4/5
			    {
			     pos[i] = q0 + v0*(time[i] + t0) + (aLimA/6)*3*Math.pow((time[i] + t0), 2) - 3*Tj1*(time[i] + t0) + Math.pow(Tj1, 2);
			     //pos =1.228
			     vel[i] = v0 + aLimA*((time[i] + t0) - (Tj1/2));
			     acc[i] = jMax*Tj1;
			     jerk[i] = 0; }
			    else if ((time[i] + t0)>  Ta - Tj1 && (time[i] + t0) <= Ta) //4/5<t<29/30
			    {
			    pos[i] = q0 + (vLim + v0)*Ta/2 - vLim*(Ta-(time[i] + t0)) - jMin*(Math.pow((Ta-(time[i] + t0)),3)/6);
			    //pos = 1.290
			    vel[i] = vLim + jMin*(Math.pow((Ta-(time[i] + t0)),2)/2);
			    acc[i] = -jMin*(Ta-(time[i] + t0));
			    jerk[i] = jMin;
			    }
			    else if ((time[i] + t0) > Ta && (time[i] + t0) <= Ta + Tv) //29/30<t< 261/240
			    {
			    	pos[i] = q0 + (vLim + v0)*(Ta/2)+vLim*((time[i] + t0)-Ta);
			        vel[i] = vLim;
			        acc[i] = 0;
			        jerk[i] = 0;
			    }
			    else if ((time[i] + t0) > T-Td && (time[i] + t0) <= T-Td+Tj2)   // 9 1/30 < t < 9 1/5
			    {
			        pos[i] = q1 - (vLim +v1)*(Td/2) + vLim*((time[i] + t0)-T+Td)-jMax*(Math.pow(((time[i] + t0)-T+Td),3)/6);
			        vel[i] = vLim - jMax*(Math.pow(((time[i] + t0)-T+Td),2)/2);
			        acc[i] = -jMax*((time[i] + t0)-T+Td);
			        jerk[i] = jMin;
			    }
			    else if ((time[i] + t0) > T-Td+Tj2 && (time[i] + t0) <= T-Tj2) // 9 1/5 < t < 9 5/6
			    {
			        pos[i] = q1 - (vLim+v1)*(Td/2)+vLim*((time[i] + t0)-T+Td) + (aLimD/6)
			        		*(3*Math.pow(((time[i] + t0)-T+Td),2) - 3*Tj2*((time[i] + t0)-T+Td) + Math.pow(Tj2,2));
			        vel[i] = vLim+aLimD*((time[i] + t0)-T+Td-Tj2/2);
			        acc[i] = aLimD;
			        jerk[i] = 0;
			    }
			    else if ((time[i] + t0) > T-Tj2 && (time[i] + t0) <=T)  // 9 5/6 < t < 10
			    {
			    	pos[i] = q1-v1*(T-(time[i] + t0))-jMax*(Math.pow((T-(time[i] + t0)),3)/6);
			        vel[i] = v1+jMax*(Math.pow((T-(time[i] + t0)),2)/2);
			        acc[i] = -jMax*(T-(time[i] + t0));
			        jerk[i] = jMax;  
			    }
		}
				
		System.out.println(pos[85]);
		System.out.println(pos[95]);
		System.out.println(vel[85]);
		System.out.println(vel[95]);
		System.out.println("abc");
		//Lets create a bank image
	
	}
		
	
	
	public static void main(String[] args){
		
		OneDirectionMotionProfiling oneDirection= new OneDirectionMotionProfiling();
		System.out.println("Hello");
		oneDirection.S_curves();
		
		for(int i=0; i<oneDirection.getPosArray().length; i++)
			System.out.println(oneDirection.getPosArray()[i]);
		
		FalconLinePlot fig3 = new FalconLinePlot(oneDirection.time, oneDirection.pos ,Color.black);
		fig3.yGridOn();
		fig3.xGridOn();
		fig3.setYLabel("Position (inches)");
		fig3.setXLabel("time (seconds)");
		fig3.setTitle("Top Down View of FRC Field (30ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");
		fig3.setSize(600,400);
		
//force graph to show 1/2 field dimensions of 24.8ft x 27 feet
double fieldWidth = 27.0;
//fig3.setXTic(0, 54, 1);
//fig3.setYTic(0, fieldWidth, 1);


//Velocity
		FalconLinePlot fig4 = new FalconLinePlot(new double[][]{{0.0,0.0}});
		fig4.yGridOn();
		fig4.xGridOn();
		fig4.setYLabel("Velocity (ft/sec)");
		fig4.setXLabel("time (seconds)");
		fig4.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
		fig4.addData(oneDirection.time,oneDirection.vel, Color.magenta);
	
		
	}


public double[] getTimeArray()
{
	return time;
}

public double[] getPosArray()
{
	return pos;
}

public double[] getVelArray()
{
	return vel;
}

public double[] getAccelArray()
{
	return acc;
}

public double[] getJerkArray()
{
	return jerk;
}



}