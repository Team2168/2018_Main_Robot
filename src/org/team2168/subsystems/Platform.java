//package org.team2168.subsystems;
//
//import org.team2168.RobotMap;
//
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**  
// * Subsystem class for the fork lift
// * @author Aidan
// */
//public class Platform extends Subsystem {
//	private static Platform instance = null;
//	
//	private DoubleSolenoid platformPiston;
//	
//	/**
//	 * Default constructors
//	 */
//	public Platform() {
//		platformPiston = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.PLATFORM_PISTON_EXTEND, RobotMap.PLATFORM_PISTON_RETRACT);
//	}
//	
//	/**
//	 * Calls instance object and makes it singleton object of class fork lift
//	 * @return singleton object instance
//	 */
//	public static Platform getInstance(){
//		if (instance == null)
//			instance = new Platform();
//		return instance;
//	}
//
//	/**
//	 * Retracts fork lift piston
//	 */
//	public void retract(){
//		platformPiston.set(DoubleSolenoid.Value.kReverse);
//	}
//
//	/**
//	 * Extends fork lift piston
//	 */
//	public void extend(){
//		platformPiston.set(DoubleSolenoid.Value.kForward);
//	}
//	
//	/**
//	 * reads last commanded value of fork lift to determine its status
//	 * @return true if fork lift is extended, false if lift is raised
//	 */
//	public boolean isCommandedExtend() {
//		return platformPiston.get() == DoubleSolenoid.Value.kForward;
//	}
//	
//	/**
//	 * reads last commanded value of fork lift to determine its status
//	 * @return true if fork lift is raised, false if lift is extended
//	 */
//	public boolean isCommandedRetract(){
//		return platformPiston.get() == DoubleSolenoid.Value.kReverse;
//	}
//	
//	public void initDefaultCommand(){
//	}
//	
//}