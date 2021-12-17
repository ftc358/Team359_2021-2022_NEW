package org.firstinspires.ftc.teamcode;

//1 rev : 1600
//wheel diameter: 4"
//40cm =~ 16" to the tower

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@Autonomous
public class AUTO_START_RED_SEBAS extends LinearOpMode {

    //defining stuff

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor linearSlide;
    DcMotor middle;
    DcMotor actualWheel;
    DcMotor greenWheel;
    Servo flipBox;

//    public double driveFactor = 0.2; //for TeleOp
//    public long lastTime = System.currentTimeMillis();
//    public int timeElapsed = 1000; // this is in milliseconds

    final double DRIVE_FACTOR = 89.1267681315; //30 * (10/9.2);
    final double TURN_FACTOR = 29.6048043436; //5 * (predict/real)


    //functions

    public void setup() throws InterruptedException {
        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.get(DcMotorEx.class, "linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void forward(int inch, double power) {
        int ticks = (int) (inch * DRIVE_FACTOR);

        //Reset Encoders
        //leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        //leftDrive.setTargetPosition(leftDrive.getCurrentPosition() - ticks);
        //rightDrive.setTargetPosition(rightDrive.getCurrentPosition() - ticks);

        //Set Drive Power
        leftDrive.setPower(0.5 * power);
        rightDrive.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        //leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //while (leftDrive.isBusy() && rightDrive.isBusy()) {
        //    telemetry.addData("leftDrive", -leftDrive.getCurrentPosition());
        //    telemetry.addData("rightDrive", -rightDrive.getCurrentPosition());
        //    telemetry.update();
        //}
        sleep((long)(inch*power*200));
    }

    public void turn(int degree, double power) {
        int ticks = (int) (degree * TURN_FACTOR);

        //Reset Encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        leftDrive.setTargetPosition(leftDrive.getCurrentPosition() - ticks);
        rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + ticks);

        //Set Drive Power
        leftDrive.setPower(0.5 * power);
        rightDrive.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() && rightDrive.isBusy()) {
            telemetry.addData("leftDrive", -leftDrive.getCurrentPosition());
            telemetry.addData("rightDrive", -rightDrive.getCurrentPosition());
            telemetry.update();
        }
        sleep(500);
    }

    public void sideDrive(int inch, double power) {
        int ticks = (int) (inch * TURN_FACTOR);

        //Reset Encoders
        middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        middle.setTargetPosition(middle.getCurrentPosition() - ticks);

        //Set Drive Power
        middle.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        middle.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (middle.isBusy()) {
            telemetry.addData("middle", -middle.getCurrentPosition());
        }
    }

    private boolean done = false;

    //real shit

    @Override
    public void runOpMode() throws InterruptedException {

        setup();
        actualWheel.setDirection(DcMotor.Direction.REVERSE);

        waitForStart(); //driver presses play
        while (opModeIsActive() && !done) {

//            forward(20, 0.8);
            forward(10, 0.8);
            turn(90,0.8);
            forward(5, 0.8);
            done = true;


            //do ^^ this after every function since it doesn't stop by itself
            //linearSlide.setPower(#);
            //flipBox.setPosition(#);

        }
    }
}