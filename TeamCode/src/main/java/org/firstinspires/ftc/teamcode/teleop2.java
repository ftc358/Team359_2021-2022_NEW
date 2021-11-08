package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math.*;


@TeleOp

public class teleop2 extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor Intake;
    DcMotor linearSlide;
    Servo Wheel;
    Servo cube;

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.servo.get("Wheel");
        cube = hardwareMap.servo.get("cube");

        waitForStart();

        while (opModeIsActive()) {

            double power = gamepad1.left_stick_y;
            double exdrive = Math.pow(power, 2.12);
            double angle = Math.atan2(gamepad1.right_stick_y,gamepad1.right_stick_x);

            /*this might not work dayunnn plz fix it
            also it's blocky and annoying to debug so i'll make a function that'll be more concise*/
            if(gamepad1.left_trigger != 0) //for more exact positioning--reaally slow turning
            {
                rightDrive.setPower(0.1);
                leftDrive.setPower(-0.1);
            }
            if(gamepad1.right_trigger != 0) //for more exact positioning--reaally slow turning
            {
                rightDrive.setPower(-0.1);
                leftDrive.setPower(0.1);
            }
            else if(Math.PI/6.0 <= angle  && angle < Math.PI/2.0)
            {
                rightDrive.setPower(0);
                leftDrive.setPower(0.8);
            }
            else if(Math.PI/2.0 <= angle  && angle < 5*Math.PI/6.0)
            {
                rightDrive.setPower(0.8);
                leftDrive.setPower(0);
            }
            else if(5*Math.PI/6.0 <= angle || angle < -5*Math.PI/6.0)
            {
                rightDrive.setPower(1);
                leftDrive.setPower(-0.3);
            }
            else if(-5*Math.PI/6.0 <= angle  && angle < -Math.PI/2.0)
            {
                rightDrive.setPower(1);
                leftDrive.setPower(-1);
            }
            else if(-Math.PI/2.0 <= angle  && angle < Math.PI/6.0)
            {
                rightDrive.setPower(-1);
                leftDrive.setPower(1);
            }
            else if(-Math.PI/6.0 <= angle  && angle < Math.PI/6.0)
            {
                rightDrive.setPower(-0.3);
                leftDrive.setPower(1);
            }
            else
            {
                leftDrive.setPower(exdrive);
                rightDrive.setPower(exdrive);
            }

            if(gamepad2.a)
            {
                Intake.setPower(-1);
            }
            else
            {
                Intake.setPower(gamepad2.left_trigger);
            }

            linearSlide.setPower(gamepad2.right_stick_y);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", 1, 1);
            telemetry.update();
        }
    }
    //TODO create a function for the turns
    static void turns(double angle)
    {

    }
}

