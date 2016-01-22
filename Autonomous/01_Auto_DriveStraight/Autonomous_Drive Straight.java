/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Autonomous

 */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


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
