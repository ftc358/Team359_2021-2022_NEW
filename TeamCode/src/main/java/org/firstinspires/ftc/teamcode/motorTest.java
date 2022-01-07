package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class motorTest extends LinearOpMode {

    private DcMotor motor1;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");

        // motor1.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            motor1.setPower(1);

            // motorLeft.setPower(-gamepad1.left_stick_y);
            // motorRight.setPower(-gamepad1.right_stick_y);
            // idle();

            telemetry.addData("motor1's power is set to 1", "running");
            telemetry.update();
        }

    }
}
