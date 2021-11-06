package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class KevinTest_Autonomous extends LinearOpMode {
    public DcMotor motor1;

    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");

        waitForStart();

        while (opModeIsActive()) {
            int inches = 10;
            double FORWARD_DISTANCE = inches * 9; // inches * FORWARD_ADJUST

            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            //Reset Encoders
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Set Target Position
            motor1.setTargetPosition(motor1.getCurrentPosition() + (int) FORWARD_DISTANCE);

            //Set Drive Power
            motor1.setPower(1);

            //Set to RUN_TO_POSITION mode
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//            private String currentPosition = Integer.toString(motor1.getCurrentPosition());

            telemetry.addData("motor1's power is set to 1", "running");
            telemetry.addData("Current Position", motor1.getCurrentPosition());
            telemetry.update();

            while (motor1.isBusy()) {
                //Wait Until Target Position is Reached
            }
        }
    }
}
