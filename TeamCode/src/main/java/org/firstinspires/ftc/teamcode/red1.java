package org.firstinspires.ftc.teamcode;

//1 rev : 1120

//leftMotor.setPower(0.0);
//rightMotor.setPower(0.0);
//^^ stop command

//circumference: 12.56...
//diameter: 4"
//40cm =~ 16" to the tower

//the bucket dump thing modify for autonomous
//if(gamepad2.y){
//  if(bucketDump){
//      bucketServo.setPosition(0f);
//      bucketDump = false;
//  }
//  else{
//      bucketServo.setPosition(0.9f);
//      bucketDump = true;
//  }
//}

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import java.lang.*;

@Autonomous
public class red1 extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor linearSlide;
    DcMotor middle;
    DcMotor actualWheel;
    DcMotor greenWheel;
    Servo flipBox;

    final double DRIVE_FACTOR = 1000;
    final double TURN_FACTOR = 200;

    //team 358 function need to modify
    public void forward (int inch, double power){
        //Reset Encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        rightDrive.setTargetPosition((int) (inch * DRIVE_FACTOR);
        rightDrive.setTargetPosition((int) (inch * DRIVE_FACTOR);

        //Set Drive Power
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        middle.setPower(0);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() && rightDrive.isBusy() && middle.isBusy()){
            //Wait Until Target Position is Reached
        }
    }

    //team 358 function need to modify
    public void turn (int degree, double power){
        //Reset Encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        leftDrive.setTargetPosition((int) (degree * TURN_FACTOR));
        rightDrive.setTargetPosition((int) (degree * TURN_FACTOR));
        middle.setTargetPosition((int) (degree * TURN_FACTOR));

        //Set Drive Power
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
        middle.setPower(power);

        //Set to RUN_TO_POSITION mode
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middle.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDrive.isBusy() && rightDrive.isBusy()){
            //Wait Until Target Position is Reached
        }
    }

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");

        waitForStart(); //driver presses play
        while (opModeIsActive()) {



            int position = leftDrive.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();

            leftDrive.setTargetPosition(1070);
            leftDrive.setPower(1);
            rightDrive.setTargetPosition(1070);
            rightDrive.setPower(1);
            //1070 ticks - what aiperi wrote hehe

            telemetry.addData("Mode", "running");
            telemetry.update();

            leftDrive.setPower(1);
            rightDrive.setPower(1);

            while (opModeIsActive() && getRuntime() < 5)
            {
                telemetry.addData("encoder-fwd-left-end", leftDrive.getCurrentPosition());
                telemetry.addData("encoder-fwd-right-end", rightDrive.getCurrentPosition());
                telemetry.update();
            }

            //go diagonally to the tower thing
            //work the box thing to tip the cube into the tower
            //park in the white box


            // Show the elapsed game time and wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", 1, 1);
            telemetry.update();



            // Send calculated power to wheels
            leftDrive.setPower(1);
            rightDrive.setPower(1);
            linearSlide.setPower(1);
            middle.setPower(1);

            sleep(3000);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", 1, 1);
            telemetry.update();
        }
    }
}