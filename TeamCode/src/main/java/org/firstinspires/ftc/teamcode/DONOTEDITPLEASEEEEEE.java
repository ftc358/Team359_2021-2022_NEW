package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.TouchSensor;
import java.util.List;

@TeleOp(name = "DO NOT RUN")
public class DONOTEDITPLEASEEEEEE extends LinearOpMode{
    private TouchSensor slideResetSensor;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorMiddle;
    private DcMotor intakeMotor;
    private DcMotor carouselMotor;
    private DcMotorEx slideMotor;
    private Servo bucketServo;
    private Servo bucketServo2;
    //intake servos are continuous
    private Servo intakeServo;
    private Servo intakeServo2;

    //settings not final!!!!!!
    //change settings here:

    //driving stuff
    private float leftMotorPower = 0f;
    private float rightMotorPower = 0f;
    private float middleMotorPower = 0f;

    private float currentDriveSpeed;
    public float normalDriveSpeed = 1f;
    public float slowDriveSpeed =  0.5f;
    //intake stuff
    public float intakeMotorPower = 1f;
    //slide stuff
    private float slideSpeed = 500;
    private int slideTopPos = 1000;
    private int slideBottomPos = 0;
    //spinny thing stuff
    public float carouselMotorPower = 0.9f;
    //servo positions
    public float servo1intakepos = 0.25f;
    public float servo2intakepos = 0.75f;
    public float servo1dumppos = 0.9f;
    public float servo2dumppos = 0.1f;

    public void runOpMode() throws InterruptedException {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        intakeMotor = hardwareMap.dcMotor.get("Intake");
        carouselMotor = hardwareMap.dcMotor.get("Wheel");
        slideMotor = hardwareMap.get(DcMotorEx.class, "linearSlide");
        bucketServo = hardwareMap.servo.get("bucketServo");
        bucketServo2 = hardwareMap.servo.get("bucketServo2");
        intakeServo = hardwareMap.servo.get("intakeServo");
        intakeServo2 = hardwareMap.servo.get("intakeServo2");
        slideResetSensor = hardwareMap.touchSensor.get("slideResetSensor");

        waitForStart();
        //init

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        boolean bucketDump = false;
        boolean carouselOn = false;
        boolean driveSlow = false;
        currentDriveSpeed = 1f;
        driveSlow = false;

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            telemetry.addData("leftstick x input:", gamepad1.left_stick_x);
            telemetry.addData("leftstick y input:", gamepad1.left_stick_y);
            telemetry.addData("rightstick x input:", gamepad1.right_stick_x);
            telemetry.addData("rightstick y input:", gamepad1.right_stick_y);
            telemetry.addData("drive speed: ", currentDriveSpeed);
            telemetry.addData("linear slide encoder value: ", slideMotor.getCurrentPosition());

            //driving stuff

            //speed control
            if (gamepad1.right_bumper){
                if(driveSlow == true){
                    currentDriveSpeed = normalDriveSpeed;
                    driveSlow = false;
                }
                else if(driveSlow == false){
                    currentDriveSpeed = slowDriveSpeed;
                    driveSlow = true;
                }
                TimeUnit.MILLISECONDS.sleep(250);
            }

            //pivot turn
            if (Math.abs(gamepad1.right_stick_x) > 0.1f && Math.abs(gamepad1.left_stick_y) > 0.1f) {
                if(gamepad1.right_stick_x < -0.1f){
                    leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    rightMotorPower = 0f;
                }
                else if (gamepad1.right_stick_x > 0.1f){
                    rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    leftMotorPower = 0f;
                }
            }

            //in place turn
            //spin to win
            else if (Math.abs(gamepad1.right_stick_x) > 0.1f){
                if(gamepad1.right_stick_x < -0.1f){
                    leftMotorPower = gamepad1.right_stick_x * currentDriveSpeed;
                    rightMotorPower = -gamepad1.right_stick_x * currentDriveSpeed;
                }
                else if (gamepad1.right_stick_x > 0.1f){
                    rightMotorPower = -gamepad1.right_stick_x * currentDriveSpeed;
                    leftMotorPower = gamepad1.right_stick_x * currentDriveSpeed;
                }
            }

            //headless driving
            //robot faces the same direction when moving around
            else{
                leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
            }
            if (Math.abs(gamepad1.left_stick_x) > 0.1){
                middleMotorPower = gamepad1.left_stick_x * currentDriveSpeed;
            }
            else{
                middleMotorPower = 0;
            }

            //set power to motors
            motorLeft.setPower(leftMotorPower);
            motorRight.setPower(rightMotorPower);
            motorMiddle.setPower(middleMotorPower);


            //intake stuff
            if (gamepad2.dpad_down){
                //spin in
                //take in freight
                intakeServo.setPosition(.45);
                intakeServo2.setPosition(.55);
                intakeMotor.setPower(intakeMotorPower);
            }
            else if (gamepad2.dpad_up){
                //spin opposite direction
                //for jamming or to get rid of extra freight
                intakeServo.setPosition(.55);
                intakeServo2.setPosition(.45);
                intakeMotor.setPower(-intakeMotorPower);
            }
            else{
                intakeMotor.setPower(0f);
                intakeServo2.setPosition(0.5);
                intakeServo.setPosition(0.5);
            }

            //reset lift encoder
            if (slideResetSensor.isPressed()){
                slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            //lifting the bucket thing on ikea drawer slide
            if (gamepad2.right_trigger > 0.1f){
                if (slideMotor.getCurrentPosition() < 1100) {
                    slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    slideMotor.setPower(gamepad2.right_trigger);
                }
            }
            else if (gamepad2.left_trigger > 0.1f){
                if (slideMotor.getCurrentPosition() > 0) {
                    slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    slideMotor.setPower(-gamepad2.left_trigger);
                }
            }
            else {
                slideMotor.setVelocity(0);
            }


            //dump the freight bucket thing
            if(gamepad2.y){
                if(bucketDump){
                    bucketServo.setPosition(servo1intakepos);
                    bucketServo2.setPosition(servo2intakepos);
                    bucketDump = false;
                }
                else{
                    bucketServo.setPosition(servo1dumppos);
                    bucketServo2.setPosition(servo2dumppos);
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
