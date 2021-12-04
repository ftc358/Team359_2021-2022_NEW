package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class encoderTest extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;
    Servo cappingServo;

    public void runOpMode() throws  InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");
        cappingServo = hardwareMap.servo.get("cappingServo");

        Wheel.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            drive(2,3,0.6);
            turn(90,0.6);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", 1, 1);
            telemetry.update();
        }
    }

    final double DRIVE_FACTOR = 89.1267681315;
    final double TURN_FACTOR = 29.6048043436;
    final double LIFT_FACTOR = 30.5577;

    public void drive (float inchF, float inchLR, double power)
    {
        //Reset Encoders
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorMiddle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        motorLeft.setTargetPosition((int) (inchF * DRIVE_FACTOR));
        motorRight.setTargetPosition((int) (inchF * DRIVE_FACTOR));
        motorMiddle.setTargetPosition((int) (inchLR * DRIVE_FACTOR));

        //Set Drive Power
        motorLeft.setPower(power);
        motorRight.setPower(power);
        motorMiddle.setPower(power);

        //Set to RUN_TO_POSITION mode
        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorMiddle.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (motorLeft.isBusy() && motorRight.isBusy() && motorMiddle.isBusy()){
            //Wait Until Target Position is Reached
        }
    }

    public void turn (int degree, double power)
    {
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
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}