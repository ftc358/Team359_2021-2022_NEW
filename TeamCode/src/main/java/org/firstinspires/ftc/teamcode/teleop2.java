package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp

public class teleop2 extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor Intake;
    DcMotor linearSlide;
//    Servo Wheel;
//    Servo cube;

    @Override
    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
//        Wheel = hardwareMap.servo.get("Wheel");
//        cube = hardwareMap.servo.get("cube");

        waitForStart();

        while (opModeIsActive()) {

            double power = gamepad1.left_stick_y;
            double exdrive = Math.pow(power, 2.12);
            double angle = Math.atan2(gamepad1.right_stick_y,gamepad1.right_stick_x);

            /*this might not work dayunnn plz fix it
            also it's blocky and annoying to debug so i'll make a function that'll be more concise*/
            if(gamepad1.left_trigger != 0) //for more exact positioning--reaally slow turning
            {
                motorRight.setPower(0.1);
                motorLeft.setPower(-0.1);
            }
            if(gamepad1.right_trigger != 0) //for more exact positioning--reaally slow turning
            {
                motorRight.setPower(-0.1);
                motorLeft.setPower(0.1);
            }
            else if(Math.PI/6.0 <= angle  && angle < Math.PI/2.0)
            {
                motorRight.setPower(0);
                motorLeft.setPower(0.8);
            }
            else if(Math.PI/2.0 <= angle  && angle < Math.PI/(6.0/5))
            {
                motorRight.setPower(0.8);
                motorLeft.setPower(0);
            }
            else if(Math.PI/(6.0/5) <= angle || angle < Math.PI/(6.0/5))
            {
                motorRight.setPower(1);
                motorLeft.setPower(-0.3);
            }
            else if(Math.PI/(-6.0/5) <= angle  && angle < Math.PI/-2.0)
            {
                motorRight.setPower(1);
                motorLeft.setPower(-1);
            }
            else if(Math.PI/-2.0 <= angle  && angle < Math.PI/-6.0)
            {
                motorRight.setPower(-1);
                motorLeft.setPower(1);
            }
            else if(Math.PI/-6.0 <= angle  && angle < Math.PI/6.0)
            {
                motorRight.setPower(-0.3);
                motorLeft.setPower(1);
            }
            else
            {
                motorRight.setPower(exdrive);
                motorLeft.setPower(exdrive);
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
}

