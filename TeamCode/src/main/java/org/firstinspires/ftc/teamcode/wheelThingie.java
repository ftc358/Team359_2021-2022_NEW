package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class wheelThingie extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor middleMotor;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        middleMotor = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");

        Intake.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            middleMotor.setPower(1);
            sleep(1000);

            middleMotor.setPower(0);

            Wheel.setPower(1);
            sleep(4000);
            Wheel.setPower(0);

            int position = motorLeft.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();


        }
    }

}
