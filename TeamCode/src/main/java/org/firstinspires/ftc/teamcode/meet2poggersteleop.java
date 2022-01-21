package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.TouchSensor;
import java.util.List;

@TeleOp(name = "meet 2 teleop 1 driver")
public class meet2poggersteleop extends LinearOpMode{
    private TouchSensor slideResetSensor;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorMiddle;
    private DcMotor intakeMotor;
    private DcMotor carouselMotor;
    private DcMotor slideMotor;
    private Servo bucketServo;
    private Servo bucketServo2;
    private Servo unJamServo;
    //capping
    private Servo cappingServo;

    private DistanceSensor backDistanceSensor;
    private TouchSensor bucketTouch;

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
    public float carouselMotorPower = 1;
    //servo positions
    public float servo1intakepos = 0.2f;
    public float servo2intakepos = 0.8f;
    public float servo1dumppos = 0.9f;
    public float servo2dumppos = 0.1f;

    public void runOpMode() throws InterruptedException {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        intakeMotor = hardwareMap.dcMotor.get("Intake");
        carouselMotor = hardwareMap.dcMotor.get("Wheel");
        slideMotor = hardwareMap.dcMotor.get("linearSlide");
        bucketServo = hardwareMap.servo.get("bucketServo");
        bucketServo2 = hardwareMap.servo.get("bucketServo2");
        cappingServo = hardwareMap.servo.get("cappingServo");
        unJamServo = hardwareMap.servo.get("unJamServo");
        backDistanceSensor = hardwareMap.get(DistanceSensor.class, "backDistanceSensor");
        bucketTouch = hardwareMap.get(TouchSensor.class, "bucketTouch");


        waitForStart();
        //init

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

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
            if (gamepad1.dpad_down){
                //spin in
                //take in freight
                intakeMotor.setPower(intakeMotorPower);
            }
            else if (gamepad1.dpad_up){
                //spin opposite direction
                //for jamming or to get rid of extra freight
                intakeMotor.setPower(-intakeMotorPower);
            }
            else{
                intakeMotor.setPower(0f);
            }


            //lifting the bucket thing on ikea drawer slide
            if (gamepad1.right_trigger > 0.1f){
                slideMotor.setPower(gamepad1.right_trigger);
            }
            else if (gamepad1.left_trigger > 0.1f){
                slideMotor.setPower(-gamepad1.left_trigger);
            }
            else {
                slideMotor.setPower(0);
            }


            //dump the freight bucket thing
            if(gamepad1.y){
                if(bucketDump){
                    bucketServo.setPosition(servo1intakepos);
                    bucketServo2.setPosition(servo2intakepos);
                    TimeUnit.MILLISECONDS.sleep(250);
                    unJamServo.setPosition(1);
                    bucketDump = false;
                }
                else{
                    bucketServo.setPosition(servo1dumppos);
                    bucketServo2.setPosition(servo2dumppos);
                    TimeUnit.MILLISECONDS.sleep(250);
                    unJamServo.setPosition(250);
                    unJamServo.setPosition(0.7);
                    bucketDump = true;
                }
                TimeUnit.MILLISECONDS.sleep(250);
            }


            //capping stick thingy
            if (gamepad1.a){
                cappingServo.setPosition(0);
            }
            if (gamepad1.right_stick_y > 0){
                cappingServo.setPosition(cappingServo.getPosition() + gamepad1.right_stick_y/200);
            }
            else if (gamepad1.right_stick_y < 0){
                cappingServo.setPosition(cappingServo.getPosition() + gamepad1.right_stick_y/200);
            }

            //carousel spinny thing code
            if (gamepad1.x){
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
            else if (gamepad1.b){
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
