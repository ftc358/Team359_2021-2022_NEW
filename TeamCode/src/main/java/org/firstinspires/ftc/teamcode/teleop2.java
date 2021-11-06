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
            double exdrive = Math.pow(power, 2);
            double angle = Math.atan2(gamepad1.right_stick_y,gamepad1.right_stick_x);

            //this might not work dayunnn plz fix it
            if(Math.PI/6.0 <= angle  && angle < Math.PI/2.0)
            {

            }
            else if(Math.PI/2.0 <= angle  && angle < 5*Math.PI/6.0)
            {

            }
            else if(5*Math.PI/6.0 <= angle || angle < -5*Math.PI/6.0)
            {

            }
            else if(-5*Math.PI/6.0 <= angle  && angle < -Math.PI/2.0)
            {

            }
            else if(-Math.PI/2.0 <= angle  && angle < Math.PI/6.0)
            {

            }
            else if(-Math.PI/6.0 <= angle  && angle < Math.PI/6.0)
            {

            }

            leftDrive.setPower(exdrive);
            rightDrive.setPower(exdrive);

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

