package org.firstinspires.ftc.teamcode.autocodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@Autonomous
public class blue2 extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor middleMotor;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    public void runOpMode() throws InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        middleMotor = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");

        Intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            motorLeft.setTargetPosition(500);
            motorRight.setTargetPosition(-500);
            motorLeft.setPower(1);
            motorRight.setPower(-1);
            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            int position = motorLeft.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();





//
//            int position = leftMotor.getCurrentPosition();
//            telemetry.addData("Encoder Position", position);
//            telemetry.update();
//
//            motorLeft.setTargetPosition(1070);
//            motorLeft.setPower(1);
//            motorRight.setTargetPosition(1070);
//            motorRight.setPower(1);
//            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//            //1070 ticks
//
//            Wheel.setPower(0.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999);
//            sleep(4000);
//
//
        }
    }

    final double DRIVE_FACTOR = 89.1267681315;
    final double TURN_FACTOR = 29.6048043436;
    final double LIFT_FACTOR = 30.5577;

    public void drive (float inchF, float inchLR, double power){
        //Reset Encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motorLeft.setTargetPosition((int) (inchF * DRIVE_FACTOR));
        motorRight.setTargetPosition((int) (inchF * DRIVE_FACTOR));
        middleMotor.setTargetPosition((int) (inchLR * DRIVE_FACTOR));

        //Set Drive Power
        motorLeft.setPower(power);
        motorRight.setPower(power);
        middleMotor.setPower(power);

        //Set to RUN_TO_POSITION mode
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motorLeft.isBusy() && motorRight.isBusy() && middleMotor.isBusy()){
            //Wait Until Target Position is Reached
        }
    }

    public void turn (int degree, double power){
        //Reset Encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motorLeft.setTargetPosition((int) (degree * TURN_FACTOR));
        motorRight.setTargetPosition((int) (degree * TURN_FACTOR));

        //Set Drive Power
        motorLeft.setPower(power);
        motorRight.setPower(power);

        //Set to RUN_TO_POSITION mode
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motorLeft.isBusy() && motorRight.isBusy()){
            //Wait Until Target Position is Reached
        }
    }

    public void linSlide (float inch, double power)
    {
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setTargetPosition((int) (inch * LIFT_FACTOR));
        linearSlide.setPower(power);
    }

}
