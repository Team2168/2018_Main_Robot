/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team2168.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.RobotMap;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  PWMSpeedController leftMotor1;
  PWMSpeedController leftMotor2;
  PWMSpeedController rightMotor1;
  PWMSpeedController rightMotor2;

  public Drivetrain() {
    leftMotor1 = new VictorSP(RobotMap.DRIVETRAIN_LEFT_1_PWM);
    leftMotor2 = new VictorSP(RobotMap.DRIVETRAIN_LEFT_2_PWM);
    rightMotor1 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_1_PWM);
    rightMotor2 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_2_PWM);
  }

  public void driveLeftMotor1(double speed) {
    leftMotor1.set(speed);
  }
  public void driveRightMotor1(double speed) {
    rightMotor1.set(speed);
  }

  public void driveMotors(double leftSpeed, double rightSpeed) {
    driveLeftMotor1(leftSpeed);
    driveRightMotor1(rightSpeed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
