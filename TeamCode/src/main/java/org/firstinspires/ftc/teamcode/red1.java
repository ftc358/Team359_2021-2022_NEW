package org.firstinspires.ftc.teamcode;

//1 rev : 1120

//NEED TO MAKE A DIAGONAL FUNCTION

//circumference: 12.56...
//diameter: 4"
//40cm =~ 16" to the tower

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@Autonomous
public class red1 extends LinearOpMode {

    //defining stuff

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor linearSlide;
    DcMotor middle;
    DcMotor actualWheel;
    DcMotor greenWheel;
    Servo flipBox;

//    public double driveFactor = 0.7; //for TeleOp
//    public long lastTime = System.currentTimeMillis();
//    public int timeElapsed = 2000; // this is in milliseconds
//
//    final double DRIVE_FACTOR = 1000;
//    final double TURN_FACTOR = 200;

    //functions

    public void setup(){
        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        middle.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //****THE WHILE FUNCTION REPEATS SO SET TIME FOR IT TO STOP USING SLEEP****


//    public void forward (int inch, double power){
//        //Reset Encoders
//        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        leftDrive.setTargetPosition((int) (inch * DRIVE_FACTOR));
//        rightDrive.setTargetPosition((int) (inch * DRIVE_FACTOR));
//
//        //Set Drive Power
//        leftDrive.setPower(power);
//        rightDrive.setPower(power);
//
//        //Set to RUN_TO_POSITION mode
//        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (leftDrive.isBusy() && rightDrive.isBusy()){
//            //Wait Until Target Position is Reached
//        }
//    }
//
//    public void turn (int degree, double power){
//        //Reset Encoders
//        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        leftDrive.setTargetPosition((int) (degree * TURN_FACTOR));
//        rightDrive.setTargetPosition((int) (degree * TURN_FACTOR));
//
//        //Set Drive Power
//        leftDrive.setPower(power);
//        rightDrive.setPower(-power);
//
//        //Set to RUN_TO_POSITION mode
//        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (leftDrive.isBusy() && rightDrive.isBusy()){
//            //Wait Until Target Position is Reached
//        }
//    }
//
//    public void sideDrive(int inch, double power){
//        //Reset Encoders
//        middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set Target Position
//        middle.setTargetPosition((int) (inch * DRIVE_FACTOR));
//
//        //Set Drive Power
//        middle.setPower(power);
//
//        //Set to RUN_TO_POSITION mode
//        middle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        while (middle.isBusy()){
//            //Wait Until Target Position is Reached
//        }
//    }

//    public boolean done = true;

    //real shit

    @Override
    public void runOpMode() {

        setup();

        waitForStart(); //driver presses play
        while (opModeIsActive()) { // && done
            //put the robot in the direction of the tower

            leftDrive.setPower(0.4);
            rightDrive.setPower(-0.4);
            sleep (1000);

            leftDrive.setPower(0);
            rightDrive.setPower(0);
            sleep(5000);


//            leftDrive.setPower(0.7);
//            rightDrive.setPower(0.7);
//
//            sleep(5000);

//            done = false;
        }

//            //to the tower
//            forward(30, 1);
//            linearSlide.setPower(1);

//            flipBox.setPosition(0.9);
//            linearSlide.setPower(-1);
//
//            //back to starting place
//            turn(180, 0.5);
//            forward(30,1);
//
//            //aim
//            turn(45, 0.5);
//            sideDrive(-14, 0.3);
//
//            //go and park
//            forward(14,0.7);
//            sideDrive(10, 0.7);
        }
}