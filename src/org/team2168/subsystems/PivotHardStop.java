package org.team2168.subsystems;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.utils.consoleprinter.ConsolePrinter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem Class for cube intake pivot piston
 */
public class PivotHardStop extends Subsystem {

	private static PivotHardStop instance = null;
	private static DoubleSolenoid HardStop;
	
	public boolean isHardStopExtended = false;
	
	
	public PivotHardStop() {
		HardStop = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.FLIPPY_FLOOPED, RobotMap.FLIPPER_FLUPED);;
		ConsolePrinter.putBoolean("isFlipperyFlooped", () -> {return getHardStopStatus();}, true, false);
	
	}
	
	public static PivotHardStop getInstance(){
		if (instance == null)
			instance = new PivotHardStop();
		return instance;
	}
	
	
	
	/**
	 * Extends the cube intake pivot piston
	 */
    public void extendHardstop() {
    	HardStop.set(DoubleSolenoid.Value.kForward);
    	this.isHardStopExtended = true;
    }
    
	/**
	 * Retracts cube intake pivot piston
	 */
    public void retractHardStop() {
    	HardStop.set(DoubleSolenoid.Value.kReverse);
    	this.isHardStopExtended = false;
    }
    
    /**
     * true if extended    
     * @return
     */
    public boolean getHardStopStatus() {
    	return isHardStopExtended;
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); //Fluppy floop flip flap
    }
}

