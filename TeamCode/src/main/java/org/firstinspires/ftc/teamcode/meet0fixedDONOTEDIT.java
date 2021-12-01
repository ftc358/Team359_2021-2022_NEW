package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.util.List;

@TeleOp(name = "meet 1 teleop")
public class meet0fixedDONOTEDIT extends LinearOpMode{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorMiddle;
    private DcMotor intakeMotor;
    private DcMotor carouselMotor;
    private DcMotor slideMotor;
    private Servo bucketServo;

    //settings not final!!!!!!
    //change settings here:

    //driving stuff
    private float leftMotorPower = 0f;
    private float rightMotorPower = 0f;
    private float middleMotorPower = 0f;
    //intake stuff
    public float intakeMotorPower = 0.5f;
    //spinny thing stuff
    public float carouselMotorPower = 0.9f;
    //servo positions

    public void runOpMode() throws InterruptedException {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        intakeMotor = hardwareMap.dcMotor.get("Intake");
        carouselMotor = hardwareMap.dcMotor.get("Wheel");
        slideMotor = hardwareMap.dcMotor.get("linearSlide");
        bucketServo = hardwareMap.servo.get("bucketServo");

        waitForStart();

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        boolean bucketDump = false;
        boolean carouselOn = false;


        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            //driving stuff

            //pivot turn
            if (Math.abs(gamepad1.right_stick_x) > 0.1f && Math.abs(gamepad1.left_stick_y) > 0.1f) {
                if(gamepad1.right_stick_x < -0.1f){
                    leftMotorPower = gamepad1.left_stick_y;
                    rightMotorPower = 0f;
                }
                else if (gamepad1.right_stick_x > 0.1f){
                    rightMotorPower = gamepad1.left_stick_y;
                    leftMotorPower = 0f;
                }
            }

            //in place turn
            //spin to win
            else if (Math.abs(gamepad1.right_stick_x) > 0.1f){
                if(gamepad1.right_stick_x < -0.1f){
                    leftMotorPower = gamepad1.right_stick_x;
                    rightMotorPower = -gamepad1.right_stick_x;
                }
                else if (gamepad1.right_stick_x > 0.1f){
                    rightMotorPower = -gamepad1.right_stick_x;
                    leftMotorPower = gamepad1.right_stick_x;
                }
            }

            //headless driving
            //robot faces the same direction when moving around
            else{
                leftMotorPower = gamepad1.left_stick_y;
                rightMotorPower = gamepad1.left_stick_y;
            }
            middleMotorPower = gamepad1.left_stick_x;

            //set power to motors
            motorLeft.setPower(leftMotorPower);
            motorRight.setPower(rightMotorPower);
            motorMiddle.setPower(middleMotorPower);

            //intake stuff
            if (gamepad2.dpad_down){
                //spin in
                //take in freight
                intakeMotor.setPower(intakeMotorPower);
            }
            else if (gamepad2.dpad_up){
                //spin opposite direction
                //for jamming or to get rid of extra freight
                intakeMotor.setPower(-intakeMotorPower);
            }
            else{
                intakeMotor.setPower(0f);
            }

            //lifting the bucket thing on ikea drawer slide
            if (gamepad2.right_trigger > 0.1f){
                slideMotor.setPower(gamepad2.right_trigger);
            }
            else if (gamepad2.left_trigger > 0.1f){
                slideMotor.setPower(-gamepad2.left_trigger);
            }
            else{
                slideMotor.setPower(0);
            }

            //dump the freight bucket thing
            if(gamepad2.y){
                if(bucketDump){
                    bucketServo.setPosition(0f);
                    bucketDump = false;
                }
                else{
                    bucketServo.setPosition(0.7f);
                    bucketDump = true;
                }
                TimeUnit.MILLISECONDS.sleep(250);
            }

            //carousel spinny thing code
            if (gamepad2.x){
                //counterclockwise
                if(carouselOn){
                    carouselMotor.setPower(0);
                    carouselOn = false;
                }
                else{
                    carouselMotor.setPower(-carouselMotorPower);
                    carouselOn = true;
                }
                TimeUnit.MILLISECONDS.sleep(250);
            }
            else if (gamepad2.b){
                if(carouselOn){
                    carouselMotor.setPower(0);
                    carouselOn = false;
                }
                else{
                    carouselMotor.setPower(carouselMotorPower);
                    carouselOn = true;
                }
                TimeUnit.MILLISECONDS.sleep(250);
            }
        }
    }
}
