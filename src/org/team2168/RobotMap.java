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
	
<<<<<<< HEAD
	public static int driverJoystick = 0;
	public static int operatorJoystick = 1;
=======
	// Joystick Control Styles/////////////////////////////////////////////////
	public static final int TANK_DRIVE_STYLE_ENUM = 0;
	public static final int GUN_STYLE_ENUM = 1;
	public static final int ARCADE_STYLE_ENUM = 2;
	public static final int GTA_STYLE_ENUM = 3;
	
	// PWM (0 to 9) on RoboRio/////////////////////////////////////////////////
	public static final int RIGHT_DRIVE_MOTOR_1 = 0;
	public static final int RIGHT_DRIVE_MOTOR_2 = 1;
	public static final int LEFT_DRIVE_MOTOR_1 = 2;
	public static final int LEFT_DRIVE_MOTOR_2 = 3;	
//	public static final int BALL_INTAKE_MOTOR = 4;
//	public static final int CLIMBER_MOTOR_LEFT = 5;
//	public static final int CLIMBER_MOTOR_RIGHT = 6;
//	public static final int SHOOTER_WHEEL_LEFT = 7;
//	public static final int SHOOTER_WHEEL_RIGHT = 8;
//
//	public static final int SHOOTER_HOOD_SERVO = 9;
	// More PWM channels in common PWM/DIO MXP section below.


	//Digital IO Channels//////////////////////////////////////////////////////
	//Channels 0-9 on RoboRio
	public static final int RIGHT_DRIVE_ENCODER_A = 0;
	public static final int RIGHT_DRIVE_ENCODER_B = 1;	
//	public static final int BALL_INTAKE_ARM_HALL_EFFECT = 2;
//	public static final int GEAR_INTAKE_ARM_HALL_EFECT = 3;
	public static final int LEFT_DRIVE_ENCODER_B = 4;
	public static final int LEFT_DRIVE_ENCODER_A = 5;
//	public static final int INDEXER_LOWER_BALL_PRESENT = 6;
//	public static final int INDEXER_UPPER_BALL_PRESENT = 7;
	public static final int TX1_TURN_ON = 8;
	public static final int TX1_ON_STATUS = 9;


	//Channels 10-25 on MXP (PWM and DIO)
//	public static final int CONVELATOR_MOTOR = 10;
//	public static final int TURRET_MOTOR = 11;
	public static final int INTAKE_MOTOR_LEFT = 12;
	public static final int INTAKE_MOTOR_RIGHT = 12;
//	public static final int TURRET_LIMIT_SWITCH_RIGHT = 19; //PWM 15 on board
//	public static final int TURRET_LIMIT_SWITCH_LEFT = 20; //PWM 16 on board
//	public static final int SHOOTER_ENCODER_A = 21; //PWM 17 on board
//	public static final int SHOOTER_ENCODER_B = 22; //PWM 18 on board
	public static final int PRACTICE_BOT_JUMPER = 24;
	
	//PBOT Differences
//	public static final int GEAR_INTAKE_ARM_HALL_EFECT_PBOT = 0;
	public static final int LEFT_DRIVE_ENCODER_B_PBOT = 1;
	public static final int LEFT_DRIVE_ENCODER_A_PBOT = 2;
	public static final int RIGHT_DRIVE_ENCODER_A_PBOT = 3;
	public static final int RIGHT_DRIVE_ENCODER_B_PBOT = 4;
//	public static final int BALL_INTAKE_ARM_HALL_EFFECT_PBOT = 5;
//	public static final int SHOOTER_ENCODER_A_PBOT = 6; 
//	public static final int SHOOTER_ENCODER_B_PBOT = 7;
//	public static final int INDEXER_LOWER_BALL_PRESENT_PBOT = 21; //PWM 17 on board
//	public static final int INDEXER_UPPER_BALL_PRESENT_PBOT = 22; //PWM 18 on board
>>>>>>> refs/remotes/origin/_CubeIntake_AS
	
	
<<<<<<< HEAD
=======
	//Solenoid Channels////////////////////////////////////////////////////////
	public final static int DRIVETRAIN_HIGH_GEAR = 0;
	public final static int DRIVETRAIN_LOW_GEAR= 1;
	public final static int INTAKE_PIVOT_PISTON_DOWN = 2;
	public final static int INTAKE_PIVOT_PISTON_UP = 3;
	public final static int INTAKE_OPEN_PISTON_CLOSED = 4;

	//Analog Input Channels////////////////////////////////////////////////////
	//Channels 0-3 on Roborio
//	public static final int TURRET_POTENTIOMETER = 0;
	public static final int CUBE_INTAKE_IR_SENSOR = 1;
	public static final int DRIVETRAIN_IR_SENSOR = 2;
	public static final int PRESSURE_SENSOR = 3;


	//Channels 4-7 on MXP


	//TODO: Confirm PDP Ports
	//TODO: Should be changed to match the new configuration 
	//PDP Channels/////////////////////////////////////////////////////////////
	public final static int DRIVETRAIN_RIGHT_MOTOR_1_PDP = 12;
	public final static int DRIVETRAIN_RIGHT_MOTOR_2_PDP = 3;
	public static final int BALL_INTAKE_MOTOR_PDP = 11;
	public static final int TURRET_MOTOR_PDP = 5;
	public static final int CLIMBER_MOTOR_LEFT_PDP = 13;
	public static final int CLIMBER_MOTOR_RIGHT_PDP = 14;
	public static final int ELEVATOR_MOTOR_PDP = 4;
	public final static int GEAR_INTAKE_MOTOR_PDP = 6;
	public final static int DRIVETRAIN_LEFT_MOTOR_1_PDP = 1;
	public final static int DRIVETRAIN_LEFT_MOTOR_2_PDP = 2;
>>>>>>> refs/remotes/origin/_CubeIntake_AS
	
	//PWM (0-9)
	public static final int LEFT_MOTOR_1_PWM = 0;
	public static final int LEFT_MOTOR_2_PWM = 1;
	public static final int LEFT_MOTOR_3_PWM = 2;
	
<<<<<<< HEAD
	public static final int RIGHT_MOTOR_1_PWM = 3;
	public static final int RIGHT_MOTOR_2_PWM = 4;
	public static final int RIGHT_MOTOR_3_PWM = 5;
=======
	//CAN Device IDs///////////////////////////////////////////////////////////


	//Relay Channels///////////////////////////////////////////////////////////
	public static final int FLASHLIGHT_RELAY = 0;


	/*************************************************************************
	 *                         DRIVETRAIN PARAMETERS
	 *************************************************************************/
	//TODO check if the reverse values match the physical robot
	public static final boolean DT_REVERSE_RIGHT = true;
	public static final boolean DT_REVERSE_LEFT = false;

	private static final int DRIVE_PULSE_PER_ROTATION = 256; //encoder ticks per rotation
	//TODO find ratio
	private static final double DRIVE_GEAR_RATIO = 1.0/1.0; //ratio between wheel over encoder
	private static final double DRIVE_WHEEL_DIAMETER = 6;
	public static final int DRIVE_ENCODER_PULSE_PER_ROT = (int) (DRIVE_PULSE_PER_ROTATION * DRIVE_GEAR_RATIO); //pulse per rotation * gear ratio
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_DIAMETER / DRIVE_ENCODER_PULSE_PER_ROT);
	public static final CounterBase.EncodingType DRIVE_ENCODING_TYPE = CounterBase.EncodingType.k4X; //count rising and falling edges on
	public static final AverageEncoder.PositionReturnType DRIVE_POS_RETURN_TYPE = AverageEncoder.PositionReturnType.FEET;
	public static final AverageEncoder.SpeedReturnType DRIVE_SPEED_RETURN_TYPE = AverageEncoder.SpeedReturnType.FPS;
	public static final int DRIVE_ENCODER_MIN_RATE = 0;
	public static final int DRIVE_ENCODER_MIN_PERIOD = 1;
	public static final boolean LEFT_DRIVE_TRAIN_ENCODER_REVERSE = true;
	public static final boolean RIGHT_DRIVE_TRAIN_ENCODER_REVERSE = true;
	public static final boolean LEFT_DRIVE_TRAIN_ENCODER_REVERSE_PBOT = true;
	public static final boolean RIGHT_DRIVE_TRAIN_ENCODER_REVERSE_PBOT = true;
	public static final int DRIVE_AVG_ENCODER_VAL = 5;
	public static final double MIN_DRIVE_SPEED = 0.2;
	public static final double AUTO_NORMAL_SPEED = 0.5;
	public static final double WHEEL_BASE = 2; //units must match PositionReturnType (feet)
	
	
	/*************************************************************************
	 *                         FORKLIFT PARAMETERS
	 *************************************************************************/
	public static final boolean FORKLIFT_LEFT_REVERSE = false;
	public static final boolean FORKLIFT_RIGHT_REVERSE = false;
	
	
	/*************************************************************************
	 *                         CUBE INTAKE PARAMETERS
	 *************************************************************************/
	public static final boolean INTAKE_LEFT_REVERSE = false;
	public static final boolean INTAKE_RIGHT_REVERSE = false;
	public static final double CUBE_INTAKE_IR_THRESHOLD = 1.4;
	
	/*************************************************************************
	 *                            PDP PARAMETERS
	 *************************************************************************/
	public final static long PDPThreadPeriod = 20;
	public static final double WARNING_CURRENT_LIMIT = 20;
	public static final double STALL_CURRENT_LIMIT = 35;
	public static final double CURRENT_LIMIT_TIME_THRESHOLD_SECONDS = 1; 

	/*************************************************************************
	 *                            PID PARAMETERS
	 *************************************************************************/
	//period to run PID loops on drive train
	public static final long DRIVE_TRAIN_PID_PERIOD = 20;//70ms loop
	public static final int DRIVE_TRAIN_PID_ARRAY_SIZE = 30;

	//PID Gains for Left & Right Speed and Position
	//Bandwidth =
	//Phase Margin =
	public static final double DRIVE_TRAIN_LEFT_SPEED_P =  0.4779;
	public static final double DRIVE_TRAIN_LEFT_SPEED_I =  1.0526;
	public static final double DRIVE_TRAIN_LEFT_SPEED_D =  0.0543;

	public static final double DRIVE_TRAIN_RIGHT_SPEED_P = 0.4779;
	public static final double DRIVE_TRAIN_RIGHT_SPEED_I = 1.0526;
	public static final double DRIVE_TRAIN_RIGHT_SPEED_D = 0.0543;

	public static final double DRIVE_TRAIN_LEFT_POSITION_P = 0.2;
	public static final double DRIVE_TRAIN_LEFT_POSITION_I = 0.0001412646174233;
	public static final double DRIVE_TRAIN_LEFT_POSITION_D = 0.0074778888124088;

	public static final double DRIVE_TRAIN_RIGHT_POSITION_P = 0.25;
	public static final double DRIVE_TRAIN_RIGHT_POSITION_I = 0.0001412646174233;
	public static final double DRIVE_TRAIN_RIGHT_POSITION_D = 0.0074778888124088;

	public static final double ROTATE_POSITION_P = 0.024;
	public static final double ROTATE_POSITION_I = 0.027;
	public static final double ROTATE_POSITION_D = 0.000000067;
>>>>>>> refs/remotes/origin/_CubeIntake_AS
	
	
	
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