
package org.team2168.commands.auto.RealOnes;

import org.team2168.Robot;
import org.team2168.RobotMap;
import org.team2168.commands.auto.Sleep;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPath;
import org.team2168.commands.drivetrain.PIDCommands.DrivePIDPathQuintic;
import org.team2168.commands.drivetrain.PIDCommands.DriveXDistance;
import org.team2168.commands.drivetrain.PIDCommands.DriveXUntilCube;
import org.team2168.commands.drivetrain.PIDCommands.RotateXDistancePIDZZZ;
import org.team2168.commands.hardStop.DisEngageIntakePivotHardStop;
import org.team2168.commands.hardStop.EngageIntakePivotHardStop;
import org.team2168.commands.intake.CloseIntake;
import org.team2168.commands.intake.DriveIntakeWheelsWithConstant;
import org.team2168.commands.intake.PivotIntakeUp;
import org.team2168.commands.intake.IntakeUntilCube;
import org.team2168.commands.intake.OpenIntake;
import org.team2168.commands.intake.OperationKeepCube;
import org.team2168.commands.intake.PivotIntakeDown;
import org.team2168.commands.intake.StopWheels;
import org.team2168.commands.intake.RobotPrep;
import org.team2168.commands.lift.PIDCommands.DriveLiftPIDZZZ;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive To swtich from Left side
 */
public class DriveToLeftScale2CubeFromLeftSideV2 extends CommandGroup {

    public DriveToLeftScale2CubeFromLeftSideV2() {
    	
    	//Fold intake down and pop out bottom plate to keep cube
    	addParallel(new DriveIntakeWheelsWithConstant(RobotMap.AUTO_CUBE_INTAKE_VALUE), 0.25);
    	addParallel(new RobotPrep());
    	addParallel(new OperationKeepCube());
    	addParallel(new PivotIntakeDown()); 
    	
    	
    	//try to increase speed out of the gate, in parallel with raising lift
    	addParallel(new DrivePIDPath(5.0, 5.0));
    	addParallel(new DriveLiftPIDZZZ(75.0, 0.5, 0.1,1.0,true));
    	addParallel(new DrivePIDPath(3.5, 5.0)); //stuff might go down
    	
    	//drive PID path to first cube
    	addSequential(new DrivePIDPathQuintic(Robot.leftVelPathQuintic6, Robot.rightVelPathQuintic6, Robot.headingQuintic6));
    	
    	//score cube
    	//addParallel(new OpenIntake());
    	addParallel(new DriveIntakeWheelsWithConstant(-0.40), 0.3); //spit
    	
    	
    	// 2nd cube
    	//-----------------------------------------------------------------//
    	//-----------------------------------------------------------------//
    	

    	//drive back and fold intake up to hardstop
    	addParallel(new EngageIntakePivotHardStop());
    	addParallel(new PivotIntakeUp());
    	addParallel(new DrivePIDPath(2.8,true)); //drive back 2.2 //stuff will go down
    	addSequential(new Sleep(), .3);
    	
    	
    	//lower lift and rotate to 2nd cube
    	addSequential(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addParallel(new  DrivePIDPathQuintic(20, 150, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(150,0.5,0.15,0.1,true));
    	
    	//pivot intake down and drive to cube
    	addSequential(new PivotIntakeDown());
    	addParallel(new OpenIntake());
    	addParallel(new IntakeUntilCube(), 0.7);
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(2.2, 5.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//backup with cube
    	addSequential(new DrivePIDPath(2.3, 5.0,true)); //drive back originally 2.3 //stuff will go down
    	
    	//rotate with cube to scale and raise intake and lift
    	addSequential(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(30, 1.0, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new DrivePIDPathQuintic(150, 20, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(20.0,0.9,0.2,0.1,true));
 
    	//raise lift up, intake down, drive fwd to scale
    	addParallel(new DriveLiftPIDZZZ(78.0, 1.0, 0.1,1.0,true)); //70
    	addParallel(new PivotIntakeDown());
    	addSequential(new DrivePIDPath(1.6, 5.0)); //drive back 2.2 //stuff might go down
    	
    	//score second cube
    	addSequential(new DriveIntakeWheelsWithConstant(-0.4), 0.3);
    	//addParallel(new OpenIntake());
    	
    	
    	
    	
    	// 3rd cube
    	//-----------------------------------------------------------------//
    	//-----------------------------------------------------------------//
    	
    	//intake up and drive lift down and drive backwards
    	addParallel(new PivotIntakeUp());
    	addParallel(new DriveLiftPIDZZZ(30, 0.7, 0.2,1.0,true)); //drive lift down slCSXowly
    	addSequential(new DrivePIDPath(2.8, 5.0, true)); //drive back 2.2 //stuff will go down 2.0
    	
    	//roate to 3rd cube and drive fwd
    	addParallel(new CloseIntake());
    	addParallel(new  DrivePIDPathQuintic(20, 120, 2500, 3000, 30000));//rotate A to B
    	addParallel(new PivotIntakeDown());
    	addSequential(new DriveLiftPIDZZZ(0.5, 0.7, 0.2,1.0,true)); //drive lift down slowly
    	addSequential(new RotateXDistancePIDZZZ(120,0.5,0.15,0.1,true));
    	
    	//drive to 3rd cube and get it
    	//addParallel(new OpenIntake());
    	addParallel(new OperationKeepCube());
    	addSequential(new DrivePIDPath(3.4, 5.0)); //stuff might go down
    	addParallel(new CloseIntake());
    	addParallel(new OperationKeepCube());
    	
    	
    	//back up with cube, intake up, lift up, and rotate to scale
    	addSequential(new DrivePIDPath(3.5, 5.0, true)); //drive back5 
    	addParallel(new DriveLiftPIDZZZ(45.0, 1.0, 0.1,1.0,true));
    	addParallel(new PivotIntakeUp());
    	addSequential(new  DrivePIDPathQuintic(120, 15, 2500, 3000, 30000));//rotate A to B
    	addParallel(new RotateXDistancePIDZZZ(15,0.5,0.15,0.1,true),0.2);
    	
    	
    	//lift up, intake down, drive fwd
    	addParallel(new DriveLiftPIDZZZ(75.0, 1.0, 0.1,1.0,true)); //70
    	addParallel(new PivotIntakeDown());
    	addSequential(new DrivePIDPath(1.6, 3.5)); //stuff might go down
    	
    	//score cube
    	addParallel(new OpenIntake());
    	addSequential(new DriveIntakeWheelsWithConstant(-0.3), 0.3);
   
    }
}
 																									