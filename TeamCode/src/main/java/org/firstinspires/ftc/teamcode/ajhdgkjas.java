package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@Autonomous
public class ajhdgkjas extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    final float cubes = 180;
    final float reset = 0;

    public void runOpMode() throws  InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");

//        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            motorLeft.setPower(0.8);
            motorRight.setPower(0.8);
            sleep(3000);
//            motorLeft.setPower(0.5);
//            motorRight.setPower(0.5);
//            sleep(1600);
//            motorRight.setPower(0);
//            motorLeft.setPower(0);
//
//            Wheel.setPower(-1);
//            sleep(3500);
//            Wheel.setPower(0);
//
//            motorMiddle.setPower(-0.5);
//            sleep(700);
//            motorMiddle.setPower(0);
//
//            motorLeft.setPower(0.2);
//            motorRight.setPower(0.2);
//            sleep(100);
//            motorLeft.setPower(0);
//            motorRight.setPower(0);
//
//            float position = motorLeft.getCurrentPosition();
//            telemetry.addData("Encoder Position", position);
//            telemetry.update();
//
//            // Show the elapsed game time and wheel power.
//            telemetry.addData("Motors", "left (%.2f), right (%.2f)", 1, 1);
//            telemetry.update();
        }
    }

}
