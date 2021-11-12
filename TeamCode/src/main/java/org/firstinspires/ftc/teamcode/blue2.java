

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class blue2 extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor middleMotor;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("motorLeft");
        rightMotor = hardwareMap.dcMotor.get("motorRight");
        middleMotor = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");

        Intake.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor.setTargetPosition(70);
            leftMotor.setPower(1);
            rightMotor.setTargetPosition(-70);
            rightMotor.setPower(1);


//            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            int position = leftMotor.getCurrentPosition();
//            telemetry.addData("Encoder Position", position);
//            telemetry.update();
//
//            leftMotor.setTargetPosition(1070);
//            leftMotor.setPower(1);
//            rightMotor.setTargetPosition(1070);
//            rightMotor.setPower(1);
//            //1070 ticks
//
//            Wheel.setPower(0.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999);
//            sleep(4000);
//
//
        }
    }

    void drive()
    {

    }

}
