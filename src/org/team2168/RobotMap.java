package org.team2168;

import org.team2168.PID.sensors.AverageEncoder;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.I2C;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{


	public static final double MAIN_PERIOD_S = 0.005; // Main loop 200Hz

	
	/*************************************************************************
	 * ROBORIO WIRING MAP
	 *************************************************************************/

	// Joysticks///////////////////////////////////////////////////////////////
	public static final int DRIVER_JOYSTICK = 0;
	public static final int OPERATOR_JOYSTICK = 1;
	public static final int DRIVER_OPERATOR_E_BACKUP = 2;
	public static final int COMMANDS_TEST_JOYSTICK = 4;
	public static final int PID_TEST_JOYSTICK = 5;


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
	
	public static final int FORKLIFT_MOTOR = 4;
	public static final int SCISSOR_MOTOR = 4;
	
	public static final int LIFT_MOTOR_1 = 4;
	public static final int LIFT_MOTOR_2 = 5;
	public static final int LIFT_MOTOR_3 = 6;
	
	public static final int CUBE_LIFT_MOTOR1 = 9;

   //SPI Channels/////////////////////////////////////////////////////////////
	public static final int GYRO = 0;
	
	// Digital IO Channels//////////////////////////////////////////////////////
	// Channels 0-9 on RoboRio
	public static final int RIGHT_DRIVE_ENCODER_A = 0;
	public static final int RIGHT_DRIVE_ENCODER_B = 1;
	
	public static final int FORK_LIFT_RAISED_LIMIT = 2;
	public static final int FORK_LIFT_LOWERED_LIMIT= 3;
	public static final int SCISSOR_LIFT_LOWERED_LIMIT = 2;
	public static final int SCISSOR_LIFT_RAISED_LIMIT = 3;
	
	public static final int LEFT_DRIVE_ENCODER_B = 4;
	public static final int LEFT_DRIVE_ENCODER_A = 5;

	public static final int LIFT_FULLY_UP = 6;
	public static final int LIFT_FULLY_DOWN = 7;
	public static final int TX1_TURN_ON = 8;
	public static final int TX1_ON_STATUS = 9;


	//Channels 10-25 on MXP (PWM and DIO)
//	public static final int CONVELATOR_MOTOR = 10;
//	public static final int TURRET_MOTOR = 11;
	public static final int CUBE_INTAKE_MOTOR_LEFT = 12;
	public static final int CUBE_INTAKE_MOTOR_RIGHT = 19; //PWM 19 on board
//	public static final int TURRET_LIMIT_SWITCH_RIGHT = 19; //PWM 15 on board
//	public static final int TURRET_LIMIT_SWITCH_LEFT = 20; //PWM 16 on board
//	public static final int SHOOTER_ENCODER_A = 21; //PWM 17 on board
//	public static final int SHOOTER_ENCODER_B = 22; //PWM 18 on board
	public static final int PRACTICE_BOT_JUMPER = 24;

	// PBOT Differences
	// public static final int GEAR_INTAKE_ARM_HALL_EFECT_PBOT = 0;
	public static final int LEFT_DRIVE_ENCODER_B_PBOT = 1;
	public static final int LEFT_DRIVE_ENCODER_A_PBOT = 2;
	public static final int RIGHT_DRIVE_ENCODER_A_PBOT = 3;
	public static final int RIGHT_DRIVE_ENCODER_B_PBOT = 4;

	//SolenoidsSolenoid Channels////////////////////////////////////////////////////////
	//Single Solenoids
	public static final int DRIVETRAIN_GEAR_SHIFT = 0; //Single Solenoid
	public static final int LIFT_SHIFT_HIGH_LOW = 4;
	public static final int FORK_LIFT_PISTON = 5;
	public static final int SCISSOR_LIFT_PISTON = 5;
	
	//Double Soldenoids
	public final static int DRIVETRAIN_HIGH_GEAR = 0; //TODO: Remove
	public final static int DRIVETRAIN_LOW_GEAR= 1; //TODO: Remove
	public static final int CUBE_INTAKE_PIVOT_EXTEND = 2;
	public static final int CUBE_INTAKE_PIVOT_RETRACT = 3;
	
	
	public static final int CUBE_INTAKE_GRIPPER_EXTENED = 5; //going on the relay
	public static final int LIFT_RACHET_ENGAGE = 6;
	public static final int LIFT_RACHET_DISENGAGE = 7;
	public static final int LIFT_BRAKE_ENGAGE = 8;
	public static final int LIFT_BRAKE_DISENGAGE = 9;
	
	//PCM 2
	public static final int LIFT_HIGH_GEAR =0;
	public static final int LIFT_LOW_GEAR = 1;
	
	
	
	public static final boolean SOLENOID_ON = true;
	public static final boolean SOLENOID_OFF = false;

	//Analog Input Channels////////////////////////////////////////////////////
	//Channels 0-3 on Roborio
	public static final int LIFT_POSITION_POT = 0;
	public static final int CUBE_INTAKE_IR_SENSOR1 = 1;
	public static final int DRIVETRAIN_IR_SENSOR = 2;
	public static final int CUBE_INTAKE_IR_SENSOR2 = 3;
	public static final int PRESSURE_SENSOR = 4;
	

	// Channels 4-7 on MXP

	// TODO: Confirm PDP Ports
	// TODO: Should be changed to match the new configuration
	// PDP Channels/////////////////////////////////////////////////////////////
	public static final int DRIVETRAIN_RIGHT_MOTOR_1_PDP = 12;
	public static final int DRIVETRAIN_RIGHT_MOTOR_2_PDP = 3;
	public static final int BALL_INTAKE_MOTOR_PDP = 11;
	public static final int TURRET_MOTOR_PDP = 5;
	public static final int CLIMBER_MOTOR_LEFT_PDP = 13;
	public static final int CLIMBER_MOTOR_RIGHT_PDP = 14;
	public static final int LIFT_MOTOR_PDP = 4;
	public static final int GEAR_INTAKE_MOTOR_PDP = 6;
	public static final int DRIVETRAIN_LEFT_MOTOR_1_PDP = 1;
	public static final int DRIVETRAIN_LEFT_MOTOR_2_PDP = 2;	
	public static final int INDEXER_WHEEL_PDP = 10;
	public static final int SHOOTER_MOTOR_LEFT_PDP = 0;
	public static final int SHOOTER_MOTOR_RIGHT_PDP = 15;
	public static final int PCM_POWER = 7;;
	
	
	//CAN Device IDs///////////////////////////////////////////////////////////
	public static final int PCM_CAN_ID = 0;
	public static final int PCM_CAN_ID_2 = 1;
	public static final int PDP_CAN_ID = 0;
	
	
	// Relay Channels///////////////////////////////////////////////////////////
	public static final int FLASHLIGHT_RELAY = 0;

	/*************************************************************************
	 * DRIVETRAIN PARAMETERS
	 *************************************************************************/
	// TODO check if the reverse values match the physical robot
	public static final boolean DT_REVERSE_RIGHT = true;
	public static final boolean DT_REVERSE_LEFT = false;

	private static final int DRIVE_PULSE_PER_ROTATION = 256; // encoder ticks per rotation
	// TODO find ratio
	private static final double DRIVE_GEAR_RATIO = 1.0 / 1.0; // ratio between wheel over encoder
	private static final double DRIVE_WHEEL_DIAMETER = 6;
	public static final int DRIVE_ENCODER_PULSE_PER_ROT = (int) (DRIVE_PULSE_PER_ROTATION * DRIVE_GEAR_RATIO); // pulse
																												// per
																												// rotation
																												// *
																												// gear																					// ratio
	public static final double DRIVE_ENCODER_DIST_PER_TICK = (Math.PI * DRIVE_WHEEL_DIAMETER
			/ DRIVE_ENCODER_PULSE_PER_ROT);
	public static final CounterBase.EncodingType DRIVE_ENCODING_TYPE = CounterBase.EncodingType.k4X; // count rising and
																										// falling edges
																										// on
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
	 *                           FORK LIFT PARAMETERS
	 *************************************************************************/
	//TODO check if the reverse values match the physical robot
	public static final boolean FORK_LIFT_REVERSE = true;
	
	
	/*************************************************************************
	 *                           SCISSOR LIFT PARAMETERS
	 *************************************************************************/
	//TODO check if the reverse values match the physical robot
	public static final boolean SCISSOR_LIFT_REVERSE = true;
	/*************************************************************************
	 *                              LIFT PARAMETERS
	 *************************************************************************/
	public static final boolean LIFT_MOTOR1_REVERSE = false;
	public static final boolean LIFT_MOTOR2_REVERSE = false;
	public static final boolean LIFT_MOTOR3_REVERSE = false;
	public static final double LIFT_MAX_JOYSTICK_SPEED = 1.0;
	/*************************************************************************
	 *                               PDP PARAMETERS
	 *************************************************************************/
	public static final long PDPThreadPeriod = 20;
	public static final double WARNING_CURRENT_LIMIT = 20;
	public static final double STALL_CURRENT_LIMIT = 35;
	public static final double CURRENT_LIMIT_TIME_THRESHOLD_SECONDS = 1;

	/*************************************************************************
	 *                                 PID PARAMETERS
	 *************************************************************************/
	// period to run PID loops on drive train
	public static final long DRIVE_TRAIN_PID_PERIOD = 20;// 70ms loop
	public static final int DRIVE_TRAIN_PID_ARRAY_SIZE = 30;

	// PID Gains for Left & Right Speed and Position
	// Bandwidth =
	// Phase Margin =
	public static final double DRIVE_TRAIN_LEFT_SPEED_P = 0.4779;
	public static final double DRIVE_TRAIN_LEFT_SPEED_I = 1.0526;
	public static final double DRIVE_TRAIN_LEFT_SPEED_D = 0.0543;

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
	
	public static final double ROTATE_POSITION_P_Drive_Straight = 0.055; //0.045 comp
	public static final double ROTATE_POSITION_I_Drive_Straight = 0.001;
	public static final double ROTATE_POSITION_D_Drive_Straight = 0.0064778888124088;
	
	// Shooter PID Speed
	// Bandwidth =
	// Phase Margin =
	// public static final double SHOOTER_SPEED_P = 0.000035;
	// public static final double SHOOTER_SPEED_I = 0.000053;
	// public static final double SHOOTER_SPEED_D = 0.0000011838;
	// public static final double SHOOTER_SPEED_N = 6.8807;

	public static final double SHOOTER_SPEED_P = 0.0000727;
	public static final double SHOOTER_SPEED_I = 0.000093;
	public static final double SHOOTER_SPEED_D = 0.0000015838;
	public static final double SHOOTER_SPEED_N = 100;

	// Turret Period
	public static final long TURRET_PID_PERIOD = 20;
	public static final int TURRET_PID_ARRAY_SIZE = 30;

	/****************************************************************
	 * TCP Servers (ONLY FOR DEBUGGING) *
	 ****************************************************************/
	public static final int TCP_SERVER_DRIVE_TRAIN_POS = 1180;
	public static final int TCP_SERVER_ROTATE_CONTROLLER = 1181;
	public static final int TCO_SERVER_RIGHT_DRIVE_TRAIN_SPEED = 1182;
	public static final int TCP_SERVER_LEFT_DRIVE_TRAIN_SPEED = 1183;
	public static final int TCP_SERVER_SHOOTER_SPEED = 1184;
	public static final int TCP_SERVER_ROTATE_CONTROLLER_STRAIGHT = 1185;
	public static final int TCP_SERVER_ROTATE_CAMERA_CONTROLLER = 1186;
	public static final int TCP_SERVER_ROTATE_TURRET_CAMERA_CONTROLLER = 1187;
	public static final int TCP_SERVER_ROTATE_TURRET_POT_CONTROLLER = 1188;

	/******************************************************************
	 * ConsolePrinter PARAMETERS *
	 ******************************************************************/
	public static final boolean PRINT_SD_DEBUG_DATA = true;
	public static final long SmartDashThreadPeriod = 100; // ms
	public static final long CONSOLE_PRINTER_LOG_RATE_MS = 100; // ms

	/******************************************************************
	 * Lights I2C *
	 ******************************************************************/
	public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;
	public static final int I2C_ADDRESS = 10;
}