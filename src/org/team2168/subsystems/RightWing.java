package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RightWing extends Subsystem 
{
	private static RightWing instance = null;
	
	private static VictorSP rightWingMotor1;
	private static VictorSP rightWingMotor2;
	
	private DoubleSolenoid rightWingPiston;
	
	public RightWing(){
		rightWingMotor1 = new VictorSP(RobotMap.RIGHT_WING_MOTOR_1);
		rightWingMotor2 = new VictorSP(RobotMap.RIGHT_WING_MOTOR_2);
		
		rightWingPiston = new DoubleSolenoid(RobotMap.RIGHT_WING_PISTON_EXTEND, 
				RobotMap.RIGHT_WING_PISTON_RETRACT);
	}
	
	public static RightWing getInstance(){
		if (instance == null)
			instance = new RightWing();
		return instance;
	}
	
	public void driveRightWingMotor1(double speed){
		if (RobotMap.RW_REVERSE1)
			speed = -speed;
		rightWingMotor1.set(speed);
	}
	
	public void driveRightWingMotor2(double speed){
		if (RobotMap.RW_REVERSE2)
			speed = -speed;
		rightWingMotor2.set(speed);
	}
	
	public void driveRightWing(double speed){
		driveRightWingMotor1(speed);
		driveRightWingMotor2(speed);
	}
	
	
	public void Retract(){
		rightWingPiston.set(Value.kReverse);
	}

	public void Extend(){
		rightWingPiston.set(Value.kForward);
	}
	
	public boolean isWingRaised(){
		//if no sensor is installed use last commanded value
		return rightWingPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	public boolean isWingLowered(){
		//if no sensor is installed use last commanded value
		return rightWingPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public void initDefaultCommand(){
	}
	
}
