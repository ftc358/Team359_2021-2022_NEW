package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class auto extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor middleMotor;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    public boolean done = true;

    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        middleMotor = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && done) {
            motorLeft.setPower(0.6);
            motorRight.setPower(-0.6);
            sleep(1500);
            motorRight.setPower(0);
            motorLeft.setPower(0);

            Wheel.setPower(-1);
            sleep(4000);
            Wheel.setPower(0);

            middleMotor.setPower(0.8);
            sleep(700);
            middleMotor.setPower(0);

            int position = motorLeft.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
            telemetry.update();

            done = false;

        }
    }

}
