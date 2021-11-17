package org.firstinspires.ftc.teamcode;

//1 rev : 1120
//wheel diameter: 4"
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

    public double driveFactor = 0.7; //for TeleOp
    public long lastTime = System.currentTimeMillis();
    public int timeElapsed = 2000; // this is in milliseconds

    final double DRIVE_FACTOR = 1000;
    final double TURN_FACTOR = 200;

    //functions

    public void setup(){
        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");
    }

    public void forward (int inch, double power){
        //Reset Encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + (int) (inch * DRIVE_FACTOR));
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + (int) (inch * DRIVE_FACTOR));

        //Set Drive Power
        leftDrive.setPower(-power);
        rightDrive.setPower(-power);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() && rightDrive.isBusy()){
            telemetry.addData("leftDrive", leftDrive.getCurrentPosition());
            telemetry.addData("rightDrive", rightDrive.getCurrentPosition());
        }
    }

    public void turn (int degree, double power){
        //Reset Encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        leftDrive.setTargetPosition((int) (degree * TURN_FACTOR));
        rightDrive.setTargetPosition((int) (degree * TURN_FACTOR));

        //Set Drive Power
        leftDrive.setPower(power);
        rightDrive.setPower(-power);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() && rightDrive.isBusy()){
        }
    }

    public void sideDrive(int inch, double power){
        //Reset Encoders
        middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        middle.setTargetPosition((int) (inch * DRIVE_FACTOR));

        //Set Drive Power
        middle.setPower(power);

        //Set to RUN_TO_POSITION mode
        middle.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (middle.isBusy()){
        }
    }

    public boolean done = true;

    //real shit

    @Override
    public void runOpMode() throws InterruptedException {

        setup();
        actualWheel.setDirection(DcMotor.Direction.REVERSE);

        waitForStart(); //driver presses play
        while (opModeIsActive() && done) {

            //done = false;
            //do ^^ this after every function since it doesn't stop by itself
            //linearSlide.setPower(#);
            //flipBox.setPosition(#);

        }
    }
}