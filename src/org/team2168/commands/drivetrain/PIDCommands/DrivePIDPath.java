
package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DrivePIDPath extends Command {
	
	private double[] setPointLeft;
    private double[] setPointRight;
	
    int counter;
    double ff_term;
   public DrivePIDPath(double[] setPointLeft, double[] setPointRight){
	   requires(Robot.drivetrain);
	   this.setPointLeft = setPointLeft;
	   this.setPointRight = setPointRight;
	   
	   
   }

    // Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.leftSpeedController.reset();
		Robot.drivetrain.leftSpeedController.Enable();
		Robot.drivetrain.leftSpeedController.setSetPoint(setPointLeft);
		
		Robot.drivetrain.rightSpeedController.reset();
		Robot.drivetrain.rightSpeedController.Enable();
		Robot.drivetrain.rightSpeedController.setSetPoint(setPointRight);
    
		counter = 0;
		ff_term = 0.08;
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	//Robot.drivetrain.tankDrive(Robot.drivetrain.leftSpeedController.getControlOutput(),
    	//Robot.drivetrain.rightSpeedController.getControlOutput());
        
		if(counter<setPointLeft.length)
		{
			double speed = ff_term*setPointLeft[counter];
			if (speed<0.12 && counter!=0)
				speed = 0.12;
			Robot.drivetrain.tankDrive(speed,speed);
			counter++;
			
			SmartDashboard.putNumber("DriveArraySpeed", speed);
		}
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return (!Robot.drivetrain.leftSpeedController.isSetPointByArray() &&  Robot.drivetrain.leftSpeedController.isFinished()) && (!Robot.drivetrain.rightSpeedController.isSetPointByArray() &&  Robot.drivetrain.rightSpeedController.isFinished());
    }

    // Called once after isFinished returns true
    
	protected void end() {
		Robot.drivetrain.leftSpeedController.Pause();
		Robot.drivetrain.rightSpeedController.Pause();
    }

    //delete me
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
