package org.firstinspires.ftc.teamcode;

import android.text.method.Touch;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.TouchSensor;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@TeleOp(name = "meet 3 teleop 2 driver")
public class meet3teleop extends LinearOpMode{
    //motors
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorMiddle;
    private DcMotor intakeMotor;
    private DcMotor carouselMotor;
    private DcMotor slideMotor;
    //servos
    private Servo bucketServo;
    private Servo bucketServo2;
    private Servo unJamServo;
    private Servo cappingServo;
    //sensors
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

    public float tooCloseDistance = 3f;
    //intake stuff
    public float intakeMotorPower = 1f;
    //slide stuff
    private float slideSpeed = 500;
    private int slideTopPos = 1000;
    private int slideBottomPos = 0;
    //spinny thing stuff
    public float carouselMotorPower = 1;
    //servo positions
    public float servo1intakepos = 0.25f;
    public float servo2intakepos = 0.75f;
    public float servo1liftpos = 0.5f;
    public float servo2liftpos = 0.5f;
    public float servo1restpos = 0f;
    public float servo2restpos = 0f;
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
        boolean tooClose = false;
        currentDriveSpeed = 1f;
        driveSlow = false;

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            telemetry.addData("leftstick x input:", gamepad1.left_stick_x);
            telemetry.addData("leftstick y input:", gamepad1.left_stick_y);
            telemetry.addData("rightstick x input:", gamepad1.right_stick_x);
            telemetry.addData("rightstick y input:", gamepad1.right_stick_y);
            telemetry.addData("drive speed factor: ", currentDriveSpeed);
            telemetry.addData("back sensor distance: ", backDistanceSensor.getDistance(DistanceUnit.CM));

            //driving stuff
            if(backDistanceSensor.getDistance(DistanceUnit.CM) < tooCloseDistance){
                tooClose = true;
            }
            else{
                tooClose = false;
            }

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
            if (Math.abs(gamepad1.right_stick_x) > 0.1f && (gamepad1.left_stick_y) > 0.1f) {
                if(gamepad1.right_stick_x < -0.1f){
                    leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    rightMotorPower = 0f;
                }
                else if (gamepad1.right_stick_x > 0.1f){
                    rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    leftMotorPower = 0f;
                }
            }
            else if (Math.abs(gamepad1.right_stick_x) > 0.1f && (gamepad1.left_stick_y) < -0.1f) {
                if (gamepad1.right_stick_x < -0.1f) {
                    rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    leftMotorPower = 0f;
                } else if (gamepad1.right_stick_x > 0.1f) {
                    leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    rightMotorPower = 0f;
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
                if (gamepad1.left_stick_y > 0.1){
                    leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                }
                else if (gamepad1.left_stick_y < -0.1){
                    leftMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                    rightMotorPower = gamepad1.left_stick_y * currentDriveSpeed;
                }
                else{
                    leftMotorPower = 0f;
                    rightMotorPower = 0f;
                }
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
            else {
                slideMotor.setPower(0);
            }

            //tilt bucket when lifted
            if (bucketTouch.isPressed()){
                servo1restpos = servo1intakepos;
                servo2restpos = servo2intakepos;
                bucketServo.setPosition(servo1restpos);
                bucketServo2.setPosition(servo2restpos);
            }
            else{
                servo1restpos = servo1liftpos;
                servo2restpos = servo2liftpos;
                bucketServo.setPosition(servo1restpos);
                bucketServo2.setPosition(servo2restpos);
            }

            //dump the freight bucket thing
            if(gamepad2.y){
                if(bucketDump){
                    bucketServo.setPosition(servo1restpos);
                    bucketServo2.setPosition(servo2restpos);
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

            //reset
            if (gamepad2.left_bumper == true && bucketTouch.isPressed() == false){
                do {
                    bucketServo.setPosition(servo1restpos);
                    bucketServo2.setPosition(servo2restpos);
                    slideMotor.setPower(-1);
                } while (bucketTouch.isPressed() == false);
            }

            //capping stick thingy
            if (gamepad2.a){
                cappingServo.setPosition(0);
            }
            if (gamepad2.left_stick_y > 0){
                cappingServo.setPosition(cappingServo.getPosition() + gamepad2.left_stick_y/400);
            }
            else if (gamepad2.left_stick_y < 0){
                cappingServo.setPosition(cappingServo.getPosition() + gamepad2.left_stick_y/400);
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
