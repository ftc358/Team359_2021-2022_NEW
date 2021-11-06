package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.List;

@TeleOp
public class KevinTest_TeleOp extends LinearOpMode {

    private DcMotor motor1;

    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("Motor1");

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

            telemetry.addData("motor1's power is set to 1", "running");
            telemetry.update();

            while (motor1.isBusy()) {
                //Wait Until Target Position is Reached
            }
        }
    }
}
