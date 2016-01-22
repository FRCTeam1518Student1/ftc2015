/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Autonomous

 */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


/**
 * Autonomous Mode
 * Drive Straight
 */
public class Autonomous extends OpMode {

    //============================================= Drive Motors ===================================================
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorCL;
    public DcMotor motorCR;
    // ========== Define Servo Motors Variables ======================================

    // Servo Motor "Java" Names
    public Servo CServoLift;
    public Servo CServoMS;
    public Servo CServoIntS;
    public Servo ServoRF;
    public Servo ServoLF;

    // position of the lift servo.
    double CServoLiftPosition;
    // amount to change the lift servo position.
    // double CServoLiftDelta = 0.1;

    // position of the main sweeper servo.
    double CServoMSPosition;
    // amount to change the main sweeper servo position.
    // double CServoMSDelta = 0.1;

    // position of the internal sweeper servo.
    double CServoIntSPosition;
    // amount to change the internal sweeper servo position.
    // double CServoIntSDelta = 0.1;

    // position of the right flipper servo.
    double ServoRFPosition;
    // amount to change the right flipper servo position.
    // double ServoRFDelta = 0.5;

    // position of the left flipper servo.
    double ServoLFPosition;
    // amount to change the right flipper servo position.
    // double ServoLFDelta = 0.5;


    //============================== Define Limit Switch Variables ======================================

    // Limit Switch "Java" Names
    public TouchSensor LTLimit;
    public TouchSensor LBLimit;

    /**
     * Constructor
     */
    public Autonomous() {

    }

    /*
	 * Code to run when the op mode is first enabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
    @Override
    public void init() {
	
		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 *   There are four motors "motor_1", "motor_2", "motor_3", "motor_4"
		 *   "motor_1" is on the front right side of the bot.
		 *   "motor_2" is on the front left side of the bot.
		 *   "motor_3" is on the rear right side of the bot.
		 *   "motor_4" is on the rear left side of the bot.
		 *
		 *
		 *
		 * Motor Layout
		 *
		 *
		 *         Front of Robot
		 *           2--------1
		 *           |        |
		 *           |        |
		 *           6        5
		 *           |        |
		 *           |        |
		 *           4--------3
	     */

        motorFR = hardwareMap.dcMotor.get("motor_1");
        motorFL = hardwareMap.dcMotor.get("motor_2");
        motorBR = hardwareMap.dcMotor.get("motor_3");
        motorBL = hardwareMap.dcMotor.get("motor_4");
        motorCR = hardwareMap.dcMotor.get("motor_5");
        motorCL = hardwareMap.dcMotor.get("motor_6");

        motorFR.setDirection(DcMotor.Direction.FORWARD);
        motorBR.setDirection(DcMotor.Direction.FORWARD);
        motorCR.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);
        motorCR.setDirection(DcMotor.Direction.REVERSE);

        //=============== Match Servo Motors "Java" Names to Hardware Configuration Names ===================

        CServoLift = hardwareMap.servo.get("LiftMotor"); // channel 5
        CServoMS = hardwareMap.servo.get("MSMotor");     // channel 3
        CServoIntS = hardwareMap.servo.get("ISMotor");   // channel 2
        ServoRF = hardwareMap.servo.get("RFMotor");      // channel 1
        ServoLF = hardwareMap.servo.get("LFMotor");      // channel 4

        //======================= Set the Starting Positions for the Servo Motors ===========================

        // assign the starting position of the Lift Motor (CServoLift)
        CServoLiftPosition  = 0.5;  // 0.5 Stops the Motor

        // assign the starting position of the Main Sweeper Motor (CServoMS)
        CServoMSPosition = 0.5;  // 0.5 Stops the Motor

        // assign the starting position of the Internal Sweeper Motor (CServoIntS)
        CServoIntSPosition = 0.5;  // 0.5 Stops the Motor

        // assign the starting position of the Right Flipper Motor (ServoRF)
        ServoRFPosition = 1.0;  // 0 = 0 degree position and 1 = 180 degree position (reversed)

        // assign the starting position of the Left Flipper Motor (ServoLF)
        ServoLFPosition = 0;  // 0 = 0 degree position and 1 = 180 degree position

    }

    int step = 0;

    @Override
    public void loop() {


        try {

            switch (step) {

                case 0:
                    motorFR.setPower(0.25);
                    motorBR.setPower(0.25);
                    motorCR.setPower(0.25);
                    motorFL.setPower(0.25);
                    motorBL.setPower(0.25);
                    motorCL.setPower(0.25);
                    Thread.sleep(2000);
                    motorFR.setPower(0);
                    motorBR.setPower(0);
                    motorCR.setPower(0);
                    motorFL.setPower(0);
                    motorBL.setPower(0);
                    motorCL.setPower(0);
                    break;
                case 1:
                    break;
            }

            step++;


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
