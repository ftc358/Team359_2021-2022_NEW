package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class blue1 extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor linearSlide;
    DcMotor middle;
    DcMotor actualWheel;
    DcMotor greenWheel;
    Servo flipBox;

    public void runOpMode() {

        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            leftDrive.setPower(-0.3);
            rightDrive.setPower(0.3);
            sleep(5000);
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            sleep(1000);

        }
    }
}