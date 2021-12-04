package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@Autonomous
public class dfskdls extends LinearOpMode {

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

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            motorMiddle.setPower(0.5);
            sleep(900);
            motorMiddle.setPower(0);

            motorLeft.setPower(0.2);
            motorRight.setPower(0.2);
            sleep(200);
            motorRight.setPower(0);
            motorLeft.setPower(0);

            cube.setPosition(cubes);
            cube.setPosition(reset);

            motorMiddle.setPower(-0.5);
            sleep(1400);
            motorMiddle.setPower(0);

            motorLeft.setPower(0.6);
            motorRight.setPower(0.6);
            sleep(1000);
            motorRight.setPower(0);
            motorLeft.setPower(0);

//            float position = motorLeft.getCurrentPosition();
//            telemetry.addData("Encoder Position", position);
//            telemetry.update();

        }
    }

}