package org.usfirst.frc.team2399.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	DigitalInput limitSwitch0;//limit switches yay
	DigitalInput limitSwitch1;
	
	RobotDrive drive; // motor

	Joystick stick; // joystick

	// Jag #s:
	// frontleft=Jag #3
	// frontright=Jag #6
	// rearright=Jag #4
	// rearleft=Jag #2

	CANJaguar frontleft; // jags on the bot
	CANJaguar frontright;
	CANJaguar rearright;
	CANJaguar rearleft;

	Gyro gyroChloe; // gyro name

	// LiveWindow LiveWindow;

	int teleloopcounter; // loop counter for teleop
	int autonomousstate; // loop counter for auto
	int autonomouscounter; //loop counter for auto
	
	// int autoLoopCounter;

	/*
	 * Servo sv0; //servos (sand crabs) Servo sv1; Servo sv2; Servo sv3;
	 * 
	 * int servoAngle; //angles of servos int servoAngle1; int servoAngle2; int
	 * servoAngle3;
	 */

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		limitSwitch0= new DigitalInput(0);//channel #
		limitSwitch1= new DigitalInput(1);
		
		stick = new Joystick(0);
		// stick.

		frontright = new CANJaguar(6);//assignments for which gyro is which
		frontleft = new CANJaguar(3);
		rearright = new CANJaguar(4);
		rearleft = new CANJaguar(2);

		drive = new RobotDrive(frontleft, frontright, rearleft, rearright);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true); // invert
																		// motors
																		// so
																		// they
																		// run
																		// in
																		// the
																		// direction
																		// we
																		// want
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);//invert the motor

		gyroChloe = new Gyro(0);
		// LiveWindow.addSensor("Sensor", "Gyro(0)", gyroChloe); //livewindow
		// gives values for gyro, etc in the driver station

		// gyro.pidGet();

		/*
		 * sv0 = new Servo(0); sv1= new Servo (1); sv2= new Servo (2); sv3= new
		 * Servo (3); servoAngle= 0; servoAngle1= 0; servoAngle2= 0;
		 * servoAngle3= 0;
		 */
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autonomousstate = 0; // reset counter
		gyroChloe.reset(); // reset gyro
		//frontleft.configEncoderCodesPerRev(256);//establishes number of revs
		autonomouscounter= 0;//sets counter to 0
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() 
	{
		double position;//est double position
		
		if(limitSwitch0.get() == true)//if limit switch is pressed
		{
			frontleft.set(0);//stop motor
		}
		else
		{
			frontleft.set(.25);//run frontleft motor
		}
		
		
		if(++autonomouscounter==50)//if adding one to the autocounter will set it = to 50
		{
			autonomouscounter= 0;//set autocounter = to 0
			//position= frontleft.getPosition();//get Position of robot
			//SmartDashboard.putNumber("frontleft position", position);//show position of robot in SmartDashboard
			SmartDashboard.putBoolean("Limit Switch 0", limitSwitch0.get());//gives values on SmartDashboard with a label
			
		}
		

		{
			
		}
		
		/*double Kp = 1;//sets doubles for PID control
		double PV;
		double CO;
		double E;
		double SP = 180;

		// state 0; trying to get to set point
		if (autonomousstate == 0) {
			PV = gyroChloe.getAngle(); // read process
			if ((PV >= 179) && (PV <= 181)) {
				autonomousstate = 1;
				drive.stopMotor();
				frontleft.updateSyncGroup((byte) 0x80);
				frontright.updateSyncGroup((byte) 0x80);
				rearleft.updateSyncGroup((byte) 0x80);
				rearright.updateSyncGroup((byte) 0x80);
				return;
			}

			E = SP - PV; // compute difference of where we are and where we want
							// to be
			CO = Kp * E; // control output = proportional gain * error
			CO = CO / 180; // slashy slash

			drive.mecanumDrive_Cartesian(0.0, 0.0, CO, 0); // speed; control
															// output is what is
															// sent to the
															// motors
			frontleft.updateSyncGroup((byte) 0x80);
			frontright.updateSyncGroup((byte) 0x80);
			rearleft.updateSyncGroup((byte) 0x80);
			rearright.updateSyncGroup((byte) 0x80);
		}

		if (autonomousstate == 1) {
			// Timer.delay(1.0);
			SmartDashboard.putNumber("gyroChloe", gyroChloe.getAngle());
			autonomousstate = 0;
		}*/

		
		/*
		 * if(autoLoopCounter < 100) //Check if we've completed 100 loops
		 * (approximately 2 seconds) { myRobot.drive(-0.5, 0.0); // drive
		 * forwards half speed autoLoopCounter++; } else { myRobot.drive(0.0,
		 * 0.0); // stop robot }
		 */
		/*
		 * { if(autoLoopCounter < 100) //Check if we've completed 100 loops
		 * (approximately 2 seconds) { //myRobot.drive(-0.5, 0.0); // drive
		 * forwards half speed autoLoopCounter++; } else { //myRobot.drive(0.0,
		 * 0.0); // stop robot } }
		 */

	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	public void teleopInit() {
		// autoLoopCounter= 0;
		gyroChloe.reset();
		teleloopcounter = 0;

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		double x;
		double y;
		double twist;

		x = stick.getX(); // assigns values for x and y
		y = stick.getY();
		twist= deadband(stick.getTwist()); //see static class lower down; restricts what registers as twist
		//System.out.println(twist);
		SmartDashboard.putNumber("twist", twist);
		

		// drive.mecanumDrive_Polar(stick.getY(), stick.getX(),
		// stick.getTwist()); //values for joystick in polar
		drive.mecanumDrive_Cartesian(y, x, twist, gyroChloe.getAngle()); // values
																		// for
																		// joystick
																		// in
																		// Cartesian;
																		// took
																		// out
																		// twist
																		// to
																		// make
																		// the
																		// robot
																		// easier
																		// to
																		// drive
		frontleft.updateSyncGroup((byte) 0x80);
		frontright.updateSyncGroup((byte) 0x80);
		rearleft.updateSyncGroup((byte) 0x80);
		rearright.updateSyncGroup((byte) 0x80);

		LiveWindow.run(); // runs LiveWindow so you can get values of gyro, etc.

		teleloopcounter++; // add 1 to teleloopcounter
		if (teleloopcounter == 10) {
			teleloopcounter = 0;
			SmartDashboard.putNumber("gyro chloe", gyroChloe.getAngle());

		}

	}

	/*
	 * if (autoLoopCounter < 25) { autoLoopCounter++; } else { autoLoopCounter =
	 * 0; sv0.setAngle(servoAngle); sv1.setAngle(servoAngle1);
	 * sv2.setAngle(servoAngle2); sv3.setAngle(servoAngle3); servoAngle =
	 * servoAngle+ 30; servoAngle1 = servoAngle1+ 60; servoAngle2 = servoAngle2+
	 * 20; servoAngle3 = servoAngle3+ 15; } if (servoAngle > 170) { servoAngle=
	 * 0; } if (servoAngle1 > 170) { servoAngle1= 0; } if (servoAngle2 > 170) {
	 * servoAngle2= 0; } if (servoAngle3 > 170) { servoAngle3= 0; }
	 */

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public static double deadband(double input) //static because the values don't change
	{
		double deadbandconstant= .25;
		
		if (Math.abs(input) < deadbandconstant) { //abs = absolute value
			return 0;
		}
		
		else 
		{
			return (input/(Math.abs(input))*(((Math.abs(input)-deadbandconstant))/(1-deadbandconstant)));
		}
	}
}
