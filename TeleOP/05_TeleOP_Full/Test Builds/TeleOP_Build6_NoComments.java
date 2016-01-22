/* Copyright (c) 2014 Qualcomm Technologies Inc

TeleOP Build 6

*/

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class TeleOP extends OpMode {

	public DcMotor motorFR;
	public DcMotor motorFL;
	public DcMotor motorBR;
	public DcMotor motorBL;
	public DcMotor motorCR;
	public DcMotor motorCL;

	public Servo CServoLift;
	public Servo CServoMS;
	public Servo CServoIntS;
	public Servo ServoRF;
	public Servo ServoLF;	

	double CServoLiftPosition;
	double CServoLiftDelta = 0.1;

	double CServoMSPosition;
	double CServoMSDelta = 0.1;

	double CServoIntSPosition;
	double CServoIntSDelta = 0.1;
	
	double ServoRFPosition;
	double ServoRFDelta = 0.5;
	
	double ServoLFPosition;
	double ServoLFDelta = 0.5;
	
	public TouchSensor LTLimit;
	public TouchSensor LBLimit;
	
	public TeleOP() {

	}

	@Override
	public void init() {

		motorFR = hardwareMap.dcMotor.get("motor_1");
		motorFL = hardwareMap.dcMotor.get("motor_2");
		motorBR = hardwareMap.dcMotor.get("motor_3");
		motorBL = hardwareMap.dcMotor.get("motor_4");
		motorCL = hardwareMap.dcMotor.get("motor_5");
		motorCL = hardwareMap.dcMotor.get("motor_6");		

		motorFR.setDirection(DcMotor.Direction.REVERSE);
		motorBR.setDirection(DcMotor.Direction.REVERSE);
		motorCR.setDirection(DcMotor.Direction.REVERSE);		

		CServoLift = hardwareMap.servo.get("LiftMotor");
		CServoMS = hardwareMap.servo.get("MSMotor");
		CServoIntS = hardwareMap.servo.get("ISMotor");
		ServoRF = hardwareMap.servo.get("RFMotor");
		ServoLF = hardwareMap.servo.get("LFMotor");
		
		CServoLiftPosition = 0.5;
		
		CServoMSPosition = 0.5;

		CServoIntSPosition = 0.5;
		
		ServoRFPosition = 1;
		
		ServoLFPosition = 0;
		
		LTLimit = hardwareMap.touchSensor.get("LiftLimit_T");
		LBLimit = hardwareMap.touchSensor.get("LiftLimit_B");
		
	} 

	@Override
	public void loop() {


		float leftstick = gamepad1.left_stick_y;
		float rightstick = gamepad1.right_stick_y;

		rightstick = Range.clip(rightstick, -1, 1);
		leftstick = Range.clip(leftstick, -1, 1);

		rightstick = (float) scaleInput(rightstick);
		leftstick = (float) scaleInput(leftstick);

		motorFR.setPower(rightstick);
		motorFL.setPower(leftstick);
		motorBR.setPower(rightstick);
		motorBL.setPower(leftstick);
		motorCR.setPower(rightstick);
		motorCL.setPower(leftstick);		

		if (gamepad2.dpad_up) {
		     CServoLiftPosition += CServoLiftDelta;
		}

		if(LTLimit.isPressed()) {
			CServoLiftPosition = 0.5;
		}
				
		if (gamepad2.dpad_down) {
			CServoLiftPosition -= CServoLiftDelta;
		}

		if(LBLimit.isPressed()) {
			CServoLiftPosition = 0.5;
		}

		if (gamepad2.y) {
		   CServoLiftPosition = 0.5;
		}		
		
		CServoLiftPosition = Range.clip(CServoLiftPosition, .2, .8);

		CServoLift.setPosition(CServoLiftPosition);

		if (gamepad2.right_bumper) {
			CServoMSPosition += CServoMSDelta;
		}
		
		if (gamepad2.left_bumper) {
			CServoMSPosition -= CServoMSDelta;
		}

		if (gamepad2.y) {
		   CServoMSPosition = 0.5;
		}		
				
		CServoMSPosition = Range.clip(CServoMSPosition, .2, .8);

		CServoMS.setPosition(CServoMSPosition);

		if (gamepad2.dpad_right) {
			CServoIntSPosition += CServoIntSDelta;
		}
		
		if (gamepad2.dpad_left) {
			CServoIntSPosition -= CServoIntSDelta;
		}

		if (gamepad2.y) {
		   CServoIntSPosition = 0.5;
		}		
				
		CServoIntSPosition = Range.clip(CServoIntSPosition, .2, .8);

		CServoIntS.setPosition(CServoIntSPosition);
		
		if (gamepad2.a) {
			ServoRFPosition -= ServoRFDelta;
			ServoLFPosition += ServoLFDelta;			
		}
		
		if (gamepad2.b) {
			ServoRFPosition += ServoRFDelta;
			ServoLFPosition -= ServoLFDelta;			
		}		
			
		ServoRFPosition = Range.clip(ServoRFPosition, 0.0, 1.0);
		ServoLFPosition = Range.clip(ServoLFPosition, 0.0, 1.0);
		
		ServoRF.setPosition(ServoRFPosition);
		ServoLF.setPosition(ServoLFPosition);

		telemetry.addData("Text", "*** Robot Data***");
		telemetry.addData("leftstick", "PWR: " + String.format("%2f", leftstick));
		telemetry.addData("rightstick", "PWR: " + String.format("%2f", rightstick));
		telemetry.addData("LiftServo", "Postion:  " + String.format("%.2f", CServoLiftPosition));

	} 

	@Override
	public void stop() {

	}
			
	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

		int index = (int) (dVal * 16.0);

		if (index < 0) {
			index = -index;
		}

		if (index > 16) {
			index = 16;
		}

		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}

		return dScale;
	} 

 } 