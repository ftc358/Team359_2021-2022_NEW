package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous
public class ServoTest extends LinearOpMode {
    public CRServo servo1;

    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.crservo.get("servo1");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            servo1.setDirection(CRServo.Direction.REVERSE);
            servo1.setPower(1);

            telemetry.addData("servo1's power is set to 1", "running");
            telemetry.update();
        }
    }
}

