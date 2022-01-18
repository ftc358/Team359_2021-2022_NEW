package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.*;

public abstract class RobotMain359 extends LinearOpMode {
    /* STATE TIMER, USED FOR PROCEDURAL ACTIONS */

    protected long STATE_TIMER = System.currentTimeMillis()+1000000;
    protected int STATE = 0;

    /* MOTION POWER VARS */

    protected double left_power = 1;
    protected int left_effective = 0;
    protected long left_acc_last = System.currentTimeMillis();

    protected double right_power = 1;
    protected int right_effective = 0;
    protected long right_acc_last = System.currentTimeMillis();

    protected double side_power = 1;

    protected double intake_power = 1;
    protected double spin_power = 1;
    protected double slide_power = 0.8;

    /* MOTION VARS */

    protected int LEFT_DRIVE = 0;
    protected long left_stopat = System.currentTimeMillis()+1000000;

    protected int RIGHT_DRIVE = 0;
    protected long right_stopat = System.currentTimeMillis()+1000000;

    protected boolean SIDE_DRIVE = false;
    protected long side_stopat = System.currentTimeMillis()+1000000;

    protected boolean INTAKE_DRIVE = false;
    protected long intake_stopat = System.currentTimeMillis()+1000000;

    protected boolean SPIN_TO_WIN = false;
    protected long spin_stopat = System.currentTimeMillis()+1000000;

    protected boolean LSLIDE_DRIVE = false;
    protected long slide_stopat = System.currentTimeMillis()+1000000;

    /* MOTORS */

    protected DcMotor leftDrive;
    protected DcMotor rightDrive;

    protected DcMotor linearSlide;
    protected DcMotor sideDrive;

    protected DcMotor intakeWheel;
    protected DcMotor carouselWheel;

    /* SERVOS */

    protected Servo flipBox1;
    protected Servo flipBox2;

    /* SENSORS */

    protected Rev2mDistanceSensor distance_sensor;
    protected DigitalChannel magnetic_switch;

    public void forward(long inches)
    {
        LEFT_DRIVE = 2;
        RIGHT_DRIVE = 2;

        left_stopat = System.currentTimeMillis()+(long)(30*inches*left_power);
        right_stopat = System.currentTimeMillis()+(long)(30*inches*right_power);
    }

    /* INIT() function configures motors and sets all sorts of default parameters. */

    public void INIT() throws InterruptedException
    {
        // Configures left, right, middle drive motors
        leftDrive = hardwareMap.dcMotor.get("motorLeft");

        rightDrive = hardwareMap.dcMotor.get("motorRight");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        sideDrive = hardwareMap.dcMotor.get("motorMiddle");
        // Configures intake motor, linear slide, carousel spin motor
        intakeWheel = hardwareMap.dcMotor.get("Intake");
        intakeWheel.setDirection(DcMotor.Direction.REVERSE);

        carouselWheel = hardwareMap.dcMotor.get("Wheel");
        linearSlide = hardwareMap.get(DcMotorEx.class, "linearSlide");

        // Configures bucket-drop servos
        //flipBox1 = hardwareMap.servo.get("bucketServo");
        //flipBox2 = hardwareMap.servo.get("bucketServo2");

        // Distance sensor, magnetic switch/motion sensor configuration
        /*distance_sensor = (Rev2mDistanceSensor)hardwareMap.get(DistanceSensor.class, "sensor_range");
        magnetic_switch = hardwareMap.get(DigitalChannel.class, "magnetic_switch");
        magnetic_switch.setMode(DigitalChannel.Mode.INPUT);*/
    }

    /* UPDATE() function updates the events and motion of robot based on constants and indicator variables. */

    public void UPDATE()
    {
        // Update state if state timer elapsed
        if((System.currentTimeMillis() - STATE_TIMER) > 0)
        {
            STATE++;
            STATE_TIMER += 1000000;
        }

        // Checks all stop times, and stops motors

        if((System.currentTimeMillis() - left_stopat) > 0) LEFT_DRIVE = 0;
        if((System.currentTimeMillis() - right_stopat) > 0) RIGHT_DRIVE = 0;
        if((System.currentTimeMillis() - side_stopat) > 0) SIDE_DRIVE = false;

        if((System.currentTimeMillis() - intake_stopat) > 0) INTAKE_DRIVE = false;
        if((System.currentTimeMillis() - spin_stopat) > 0) SPIN_TO_WIN = false;
        if((System.currentTimeMillis() - slide_stopat) > 0) LSLIDE_DRIVE = false;

        leftDrive.setPower(left_effective/10);
        rightDrive.setPower(right_effective/10);

        // Activates or deactivates left, right, and side drives based on state
        if(LEFT_DRIVE == 2)
        {
            if(Math.floor(left_effective+1) >= left_power) LEFT_DRIVE = 1;
            if((System.currentTimeMillis() - left_acc_last) > 50)
            {
                int increment = 1;
                left_effective += increment;

                left_acc_last = System.currentTimeMillis();
            }
        }
        else if(LEFT_DRIVE == 0)
        {
            if(left_effective <= 0) LEFT_DRIVE = 1;
            if((System.currentTimeMillis() - left_acc_last) > 50)
            {
                int increment = 1;
                left_effective += increment;

                left_acc_last = System.currentTimeMillis();
            }
        }

        if(RIGHT_DRIVE == 2)
        {
            if(Math.floor(left_effective+1) >= right_power) RIGHT_DRIVE = 1;
            if((System.currentTimeMillis() - right_acc_last) > 50)
            {
                right_acc_last = System.currentTimeMillis();
            }
        }
        else if(RIGHT_DRIVE == 0)
        {
            if(right_effective <= 0) RIGHT_DRIVE = 1;
            if((System.currentTimeMillis() - right_acc_last) > 50)
            {
                double increment = 1;
                right_effective -= increment;

                right_acc_last = System.currentTimeMillis();
            }
        }

        if(SIDE_DRIVE) { sideDrive.setPower(side_power); }
        else { sideDrive.setPower(0); }

        // Activates or deactivates intake, carousel, and linear slide wheel based on state
        if(INTAKE_DRIVE) { intakeWheel.setPower(intake_power); }
        else { intakeWheel.setPower(0); }

        if(SPIN_TO_WIN) { carouselWheel.setPower(spin_power); }
        else { carouselWheel.setPower(0); }

        if(LSLIDE_DRIVE) { linearSlide.setPower(slide_power); }
        else { linearSlide.setPower(0); }
    }
}