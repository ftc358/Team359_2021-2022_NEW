package org.firstinspires.ftc.teamcode;

//1 rev : 1120

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class red1 extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor linearSlide;
    DcMotor middle;
    DcMotor actualWheel;
    DcMotor greenWheel;
    Servo flipBox;

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.dcMotor.get("motorLeft");
        rightDrive = hardwareMap.dcMotor.get("motorRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        middle = hardwareMap.dcMotor.get("motorMiddle");
        actualWheel = hardwareMap.dcMotor.get("Intake");
        greenWheel = hardwareMap.dcMotor.get("Wheel");
        flipBox = hardwareMap.servo.get("bucketServo");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;



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