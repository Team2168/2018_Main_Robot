package org.team2168.PID.trajectory;

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
		Linspace time = new Linspace(t0, 1/100, t1);
		
		//Compute actual min/max a and v
		double aLimA = jMax*Tj1;
		double aLimD = -jMax*Tj2;
		double vLim = v0 + (Ta-Tj1)*aLimA;
		
		// Calculation of trajectory for q1 > q2
		
		// ??
		for(int i=1;,length(time);  ){
			 if (time(i) <= Tj1) {
			        pos(i) = q0 + v0*time(i) + jMax*time(i)^3/6;
			        vel(i) = v0 + jMax*time(i)^2/2;
			        acc(i) = jMax*time(i);
			        jerk(i) = jMax;}
			    else if (time(i) > Tj1 && time(i) <= Ta - Tj1) 
			    {
			        pos(i) = q0 + v0*time(i) + (aLimA/6)*(3*time(i)^2 - 3*Tj1*time(i) + Tj1^2);
			        vel(i) = v0 + a_lima*(time(i) - (Tj1/2));
			        acc(i) = jMax*Tj1;
			        jerk(i) = 0; }
			    else if (time(i)>  Ta - Tj1 && time(i) <= Ta)
			    {
			    	pos(i) = q0 + (v_lim + v0)*Ta/2 - v_lim*(Ta-time(i)) - j_min*((Ta-time(i))^3/6);
			        vel(i) = v_lim + j_min*((Ta-time(i))^2/2);
			        acc(i) = -j_min*(Ta-time(i));
			        jerk(i) = j_min;
			    }
			    else if (time(i) > Ta && time(i) <= Ta + Tv)
			    {
			    	pos(i) = q0 + (v_lim + v0)*(Ta/2)+v_lim*(time(i)-Ta);
			        vel(i) = v_lim;
			        acc(i) = 0;
			        jerk(i) = 0;
			    }
			    else if (time(i) > T-Td && time(i) <= T-Td+Tj2)     
			    {
			        pos(i) = q1 - (v_lim +v1)*(Td/2) + v_lim*(time(i)-T+Td)-j_max*(((time(i)-T+Td)^3)/6);
			        vel(i) = v_lim - j_max*((((time(i)-T+Td)^2))/2);
			        acc(i) = -j_max*(time(i)-T+Td);
			        jerk(i) = j_min;
			    }
			    else if (time(i) > T-Td+Tj2 && time(i) <= T-Tj2)
			    {
			        pos(i) = q1 - (v_lim+v1)*(Td/2)+v_lim*(time(i)-T+Td) + (a_limd/6)*(3*(time(i)-T+Td)^2 - 3*Tj2*(time(i)-T+Td) + Tj2^2);
			        vel(i) = v_lim+a_limd*(time(i)-T+Td-Tj2/2);
			        acc(i) = a_limd;
			        jerk(i) = 0;
			    }
			    else if (time(i) > T-Tj2 && time(i) <=T)  
			    {
			    	(i) = q1-v1*(T-time(i))-j_max*((T-time(i))^3/6);
			        vel(i) = v1+j_max*((T-time(i))^2/2);
			        acc(i) = -j_max*(T-time(i));
			        jerk(i) = j_max;  
			    }
		}
				
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
	
	

