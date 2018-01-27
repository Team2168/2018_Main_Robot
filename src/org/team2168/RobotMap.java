package org.team2168;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	/*************************************************************************
	 *                              ROBORIO WIRING MAP
	 *************************************************************************/

	//Joysticks/////////////////////////
	
	public static int driverJoystick = 0;
	public static int operatorJoystick = 1;
	
	
	
	//PWM (0-9)
	public static final int LEFT_MOTOR_1_PWM = 0;
	public static final int LEFT_MOTOR_2_PWM = 1;
	public static final int LEFT_MOTOR_3_PWM = 2;
	
	public static final int RIGHT_MOTOR_1_PWM = 3;
	public static final int RIGHT_MOTOR_2_PWM = 4;
	public static final int RIGHT_MOTOR_3_PWM = 5;
	
	
	
	//PWM (10-19)
	
	////////////////////////////DRIVETRAIN////////////////////////////////////
	
	public static final boolean REVERSE_LEFT_1_MOTOR_DIRECTION = false;
	public static final boolean REVERSE_LEFT_2_MOTOR_DIRECTION = false;
	public static final boolean REVERSE_LEFT_3_MOTOR_DIRECTION = false;
	
	public static final boolean REVERSE_RIGHT_1_MOTOR_DIRECTION = true;
	public static final boolean REVERSE_RIGHT_2_MOTOR_DIRECTION = true;
	public static final boolean REVERSE_RIGHT_3_MOTOR_DIRECTION = true;
	
	
	
	///Solendods///
	
	
	
	///Relays///
	
	
	
	///DIO///
	
}