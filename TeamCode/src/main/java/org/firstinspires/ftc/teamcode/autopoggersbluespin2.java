package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.TouchSensor;


@Autonomous(name = "blue carousel side spin poggers 2")
public class autopoggersbluespin2 extends LinearOpMode {

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
    //intake stuff
    public float intakeMotorPower = 0.5f;
    //spinny thing stuff
    public float carouselMotorPower = 0.9f;
    //servo positions

    private boolean done = false;

    public void runOpMode() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        slideMotor = hardwareMap.dcMotor.get("linearSlide");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        intakeMotor = hardwareMap.dcMotor.get("Intake");
        carouselMotor = hardwareMap.dcMotor.get("Wheel");
        bucketServo = hardwareMap.servo.get("bucketServo");
        bucketServo2 = hardwareMap.servo.get("bucketServo2");
        cappingServo = hardwareMap.servo.get("cappingServo");
        unJamServo = hardwareMap.servo.get("unJamServo");
        backDistanceSensor = hardwareMap.get(DistanceSensor.class, "backDistanceSensor");
        bucketTouch = hardwareMap.get(TouchSensor.class, "bucketTouch");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && !done) {

            //move sideways
            bucketServo.setPosition(0.25f);
            bucketServo2.setPosition(0.75f);
            motorMiddle.setPower(-1);
            sleep(2000);
            //move to shipping hub
            motorMiddle.setPower(0);
            slideMotor.setPower(1);
            sleep(2000);
            slideMotor.setPower(0);
            //dump bucket
            sleep(1000);
            bucketServo.setPosition(0.9f);
            bucketServo2.setPosition(0.1f);
            sleep(1000);
            //move to carousel
            motorRight.setPower(-0.5);
            motorLeft.setPower(- 0.5);
            sleep(4000);
            motorLeft.setPower(0);
            motorRight.setPower(0);
            motorMiddle.setPower(0.75);
            sleep(3000);
            motorMiddle.setPower(0.05);
            //spin carousel
            carouselMotor.setPower(-1);
            sleep(2500);
            carouselMotor.setPower(0);
            //park
            motorMiddle.setPower(-0.75);
            sleep(1500);
            motorMiddle.setPower(0);
            bucketServo.setPosition(0.25f);
            bucketServo2.setPosition(0.75f);
            slideMotor.setPower(-1);
            sleep(1750);
            slideMotor.setPower(0);
            motorRight.setPower(-0.25f);
            motorLeft.setPower(-0.25f);
            sleep(1500);
            motorRight.setPower(0);
            motorLeft.setPower(0);

            done = true;
        }
    }
}
