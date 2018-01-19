package org.team2168.subsystems;

import org.team2168.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LeftWing extends Subsystem 
{
	private static LeftWing instance = null;
	
	private static VictorSP leftWingMotor1;
	private static VictorSP leftWingMotor2;
	
	private DoubleSolenoid leftWingPiston;
	
	public LeftWing(){
		leftWingMotor1 = new VictorSP(RobotMap.LEFT_WING_MOTOR_1);
		leftWingMotor2 = new VictorSP(RobotMap.LEFT_WING_MOTOR_2);
		
		leftWingPiston = new DoubleSolenoid(RobotMap.LEFT_WING_PISTON_EXTEND, 
				RobotMap.LEFT_WING_PISTON_RETRACT);
	}
	
	public static LeftWing getInstance(){
		if (instance == null)
			instance = new LeftWing();
		return instance;
	}
	
	public void driveLeftWingMotor1(double speed){
		if (RobotMap.LW_REVERSE1)
			speed = -speed;
		leftWingMotor1.set(speed);
	}
	
	public void driveLeftWingMotor2(double speed){
		if (RobotMap.LW_REVERSE2)
			speed = -speed;
		leftWingMotor2.set(speed);
	}
	
	public void driveLeftWing(double speed){
		driveLeftWingMotor1(speed);
		driveLeftWingMotor2(speed);
	}
	
	
	public void Retract(){
		leftWingPiston.set(Value.kReverse);
	}

	public void Extend(){
		leftWingPiston.set(Value.kForward);
	}
	
	public boolean isWingRaised(){
		//if no sensor is installed use last commanded value
		return leftWingPiston.get() == DoubleSolenoid.Value.kReverse;
	}
	
	public boolean isWingLowered(){
		//if no sensor is installed use last commanded value
		return leftWingPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public void initDefaultCommand(){
	}
	
}
