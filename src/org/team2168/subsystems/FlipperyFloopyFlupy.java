package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem Class for cube intake pivot piston
 */
public class FlipperyFloopyFlupy extends Subsystem {

	private static FlipperyFloopyFlupy instance = null;
	private static DoubleSolenoid Flippery;
	
	public boolean isFlippyFlooped = false;
	
	
	public FlipperyFloopyFlupy() {
		Flippery = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.FLIPPY_FLOOPED, RobotMap.FLIPPER_FLUPED);;
		ConsolePrinter.putBoolean("isFlipperyFlooped", () -> {return getFlippyStatus();}, true, false);
	
	}
	
	public static FlipperyFloopyFlupy getInstance(){
		if (instance == null)
			instance = new FlipperyFloopyFlupy();
		return instance;
	}
	
	
	
	/**
	 * Extends the cube intake pivot piston
	 */
    public void extendFloppy() {
    	Flippery.set(DoubleSolenoid.Value.kForward);
    	this.isFlippyFlooped = true;
    }
    
	/**
	 * Retracts cube intake pivot piston
	 */
    public void retractFlippy() {
    	Flippery.set(DoubleSolenoid.Value.kReverse);
    	this.isFlippyFlooped = false;
    }
    
    
    public boolean getFlippyStatus() {
    	return isFlippyFlooped;
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); //Fluppy floop flip flap
    }
}

