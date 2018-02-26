
package org.team2168.commands.drivetrain.PIDCommands;

import org.team2168.Robot;
import org.team2168.PID.trajectory.OneDimensionalMotionProfiling;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DrivePIDPath extends Command {
	
	private double[] setPointLeft;
    private double[] setPointRight;
    
    OneDimensionalMotionProfiling motion;
	
    int counter;
    double ff_term;
    double oldClock;
    double angle;
    double lastRotateOutput;
    boolean direction = false;
    int directionValue = 1;
    
    public DrivePIDPath(double distance )
    {
    	this(distance,false);
    }
    
    public DrivePIDPath(double distance, boolean reverseDirection )
    {
    	requires(Robot.drivetrain);
    	motion = new OneDimensionalMotionProfiling(distance);
  	   this.setPointLeft =  motion.getVelArray();
  	   this.setPointRight = motion.getVelArray();
  	   this.direction = reverseDirection;
    }
   
    public DrivePIDPath(double[] setPointLeft, double[] setPointRight){
 	   requires(Robot.drivetrain);
 	   this.setPointLeft = setPointLeft;
 	   this.setPointRight = setPointRight;
 	   SmartDashboard.putNumber("FF_term", 0);
 	   ff_term = SmartDashboard.getNumber("FF_term", 0);
 	   
 	   direction = false;
 	   
 	   
    } 
    
   public DrivePIDPath(double[] setPointLeft, double[] setPointRight, boolean reverseDirection){
	   requires(Robot.drivetrain);
	   this.setPointLeft = setPointLeft;
	   this.setPointRight = setPointRight;
	   SmartDashboard.putNumber("FF_term", 0);
	   ff_term = SmartDashboard.getNumber("FF_term", 0);
	   
	   direction = reverseDirection;
	   
	   
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
		ff_term = 1.11;
		oldClock = Timer.getFPGATimestamp();
		
		
		Robot.drivetrain.resetPosition();

		//reset controller
		Robot.drivetrain.imu.reset();
		Robot.drivetrain.driveTrainPosController.reset();
		Robot.drivetrain.rotateDriveStraightController.reset();

		angle = Robot.drivetrain.getHeading();
		this.lastRotateOutput = 0;
		
		Robot.drivetrain.rotateDriveStraightController.Enable();
		
		//if true we want to reverse else we want to go forward
		if (direction)
			directionValue = -1;
		else
			directionValue = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() 
	{
    	//Robot.drivetrain.tankDrive(Robot.drivetrain.leftSpeedController.getControlOutput(),
    	//Robot.drivetrain.rightSpeedController.getControlOutput());
        
		double currTime = Timer.getFPGATimestamp(); 
		SmartDashboard.putNumber("Command Execution Time", (currTime - oldClock));
		oldClock = currTime;
		
		//ff_term = SmartDashboard.getNumber("FF_term", 0);
		
		lastRotateOutput = Robot.drivetrain.rotateDriveStraightController.getControlOutput();
		double headingCorrection = (Robot.drivetrain.rotateDriveStraightController.getControlOutput()) ;
		
		if(counter<setPointLeft.length)
		{
			double speed = (ff_term*directionValue*setPointLeft[counter])/(Robot.pdp.getBatteryVoltage());
			if (Math.abs(speed)<0.12 && counter!=0)
				speed = directionValue*0.12;
			
			Robot.drivetrain.tankDrive(speed+headingCorrection,speed-headingCorrection);
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
