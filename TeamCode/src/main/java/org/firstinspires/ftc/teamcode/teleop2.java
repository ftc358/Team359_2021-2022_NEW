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
    DcMotor Wheel;
    Servo cube;

    @Override
    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("cube");

        Intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            double power = gamepad1.left_stick_y;
            double exdrive = Math.pow(power, 2.12);
            double turn = gamepad1.right_stick_x;
            double turndrive = Math.pow(turn, 2.12);
            int servo = 0;

            if(gamepad1.right_stick_x>0)
            {
                motorRight.setPower(-turndrive);
                motorLeft.setPower(turndrive);
            }
            else if (gamepad1.right_stick_x<0)
            {
                motorRight.setPower(turndrive);
                motorLeft.setPower(-turndrive);
            }
            else
            {
                motorRight.setPower(exdrive);
                motorLeft.setPower(exdrive);
            }
            if (Math.abs(gamepad1.left_stick_x) > 0.1 )
            {
                   motorLeft.setPower(0);
                   motorRight.setPower(0);
                   motorMiddle.setPower(gamepad1.right_stick_x);
            }
            if(gamepad2.left_bumper)
            {
               ;

            }
            if(gamepad2.a)
            {
                Intake.setPower(-1);
            }
            else
            {
                Intake.setPower(gamepad2.left_trigger);
            }
            if(gamepad1.b)
            {
                Wheel.setPower(1);
            }

            linearSlide.setPower(gamepad2.right_stick_y);

            // Show the elapsed game time and wheel power.
            telemetry.addData("motorvaluer", motorRight.getPower());
            telemetry.addData("motorvaluel", motorLeft.getPower());
            telemetry.update();
        }
    }
}

