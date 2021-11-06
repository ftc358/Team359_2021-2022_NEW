package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class testDrive extends LinearOpMode {

    DcMotor motorLeft1;
    DcMotor motorRight1;
    DcMotor motorLeft2;
    DcMotor motorRight2;
    //DcMotor Intake;

    public void runOpMode() throws InterruptedException {

        motorLeft1 = hardwareMap.dcMotor.get("motorLeft1");
        motorRight1 = hardwareMap.dcMotor.get("motorRight1");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        //Intake = hardwareMap.dcMotor.get("Intake");

        motorRight1.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            motorLeft1.setPower(1);
            motorLeft2.setPower(1);
            motorRight1.setPower(1);
            motorRight2.setPower(1);

            sleep(6000);

            motorLeft1.setPower(-1);
            motorLeft2.setPower(-1);
            motorRight1.setPower(-1);
            motorRight2.setPower(-1);

            sleep(6000);

            motorLeft1.setPower(-1);
            motorLeft2.setPower(1);
            motorRight1.setPower(-1);
            motorRight2.setPower(1);

            //Intake.setPower(0.8);

            sleep(1000);

            //idle();
        }
    }
}