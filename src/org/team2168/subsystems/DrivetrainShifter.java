package org.team2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.PID.sensors.ADXRS453Gyro;
import org.team2168.PID.sensors.AverageEncoder;
import org.team2168.commands.drivetrain.DriveWithJoystick;
import org.team2168.subsystems.DrivetrainShifter;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem class for the Drivetrain
 */
public class DrivetrainShifter extends Subsystem {

	private static DrivetrainShifter instance = null;
	private static Solenoid gearChanger;
	
	/**
	 * Default constructors for Drivetrain
	 */
	private DrivetrainShifter() {
		gearChanger = new Solenoid(RobotMap.DRIVETRAIN_GEAR_SHIFT);

		//Log sensor data
		//ConsolePrinter.putNumber("Drivetrain Right Encoder",
		//		() -> {return DrivetrainShifter.getInstance().getRightPosition();}, true, false);
		ConsolePrinter.putBoolean("Drivetrain in High Gear", () -> {return Robot.drivetrainShifter.isInHighGear();}, true, false);
		ConsolePrinter.putBoolean("Drivetrain in Low Gear", () -> {return Robot.drivetrainShifter.isInLowGear();}, true, false);

	}
	
	/**
	 * Calls instance object and makes it a singleton object of type Drivetrain
	 * @returns Drivetrain object "instance"
	 */
	public static DrivetrainShifter getInstance() {
		if(instance == null)
			instance = new DrivetrainShifter();
		
		return instance;
	}
	
    /**
     * Calls for default command of the drivetrainShifter
     */
    public void initDefaultCommand() {
    }

	/**
	 * Shifts the Drivetrain from High to Low Gear
	 */
    public void shiftToLow() {
    	gearChanger.set(true);
    }
    
	/**
	 * Shifts the Drivetrain from Low to High Gear
	 */
    public void shiftToHigh() {
    	gearChanger.set(false);
    }
    
	/**
	 * Returns true if last commanded shift was Low Gear
	 */
    public boolean inLowGear() {
    	return gearChanger.get() == true;
    }

	/**
	 * Returns true if last commanded shift was High Gear
	 */
    public boolean inHighGear() {
    	return gearChanger.get() == false;
    }
    
    public boolean isInLowGear() {
    	return inLowGear();
    }
    
    public boolean isInHighGear() {
    	return inHighGear();
    }
}
