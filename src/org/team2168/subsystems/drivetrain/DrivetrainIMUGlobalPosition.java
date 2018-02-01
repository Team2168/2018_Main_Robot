package org.team2168.subsystems.drivetrain;

import java.util.TimerTask;

import org.team2168.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * IMU for drivetrain, used for dead reckoning of turret
 * 
 * @author Wen Baid
 */
public class DrivetrainIMUGlobalPosition {

	private java.util.Timer executor;
	private long period;

	double lastLeftEncoderPosition;
	double lastRightEncoderPosition;

	double globalX;
	double globalY;

	double STARTING_GLOBAL_X;
	double STARTING_GLOBAL_Y;

	double BOILER_X = 24;
	double BOILER_Y = 0;

	double TurretAngleToBoiler;

	public DrivetrainIMUGlobalPosition(long period) {
		this.period = period;
		initialize();
	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new DTIMUTask(this), 0L, this.period);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		lastLeftEncoderPosition = 0;
		lastRightEncoderPosition = 0;

		globalX = 15.0;
		globalY = 0.0;
	}

	public void reset() {
		globalX = 15;
		globalY = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	private void run() {
		boolean blue = Robot.onBlueAlliance();

		SmartDashboard.putBoolean("Blue Alliance", blue);

		if (blue) {
			BOILER_X = 0.1;
			BOILER_Y = 0.1;
		} else {
			BOILER_X = 27.0;
			BOILER_Y = 0.1;
		}

		double difLeftEncoder;
		double difRightEncoder;

		difLeftEncoder = Robot.drivetrain.getLeftPosition() - lastLeftEncoderPosition;
		difRightEncoder = Robot.drivetrain.getRightPosition() - lastRightEncoderPosition;

		double averageDif = (difLeftEncoder + difRightEncoder) / 2.0;

		globalX += averageDif * Math.sin(Robot.drivetrain.getHeading() * (Math.PI / 180.0));
		globalY += averageDif * Math.cos(Robot.drivetrain.getHeading() * (Math.PI / 180.0));

		SmartDashboard.putNumber("globalX", globalX);
		SmartDashboard.putNumber("globalY", globalY);

		lastLeftEncoderPosition = Robot.drivetrain.getLeftPosition();
		lastRightEncoderPosition = Robot.drivetrain.getRightPosition();

		double AngleToBoiler = Math.atan((globalY - BOILER_Y) / (globalX - BOILER_X)) * (180.0 / Math.PI);

		SmartDashboard.putNumber("AngleToBoilerDeg", AngleToBoiler);

		if (blue) {
			TurretAngleToBoiler = -Robot.drivetrain.getHeading() + 90.0 - AngleToBoiler;
		} else {
			TurretAngleToBoiler = -Robot.drivetrain.getHeading() - 90.0 - AngleToBoiler;
		}

		SmartDashboard.putNumber("TurretAngleToBoilerDeg", TurretAngleToBoiler);

	}

	public double getTurretAngleToBoiler() {
		return TurretAngleToBoiler;
	}

	private class DTIMUTask extends TimerTask {
		private DrivetrainIMUGlobalPosition dtIMU;

		private DTIMUTask(DrivetrainIMUGlobalPosition dtImu) {
			if (dtImu == null) {
				throw new NullPointerException("DTImu was null");
			}
			this.dtIMU = dtImu;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			dtIMU.run();
		}
	}
}
