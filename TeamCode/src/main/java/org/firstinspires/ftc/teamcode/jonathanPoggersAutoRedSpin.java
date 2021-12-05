package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "red carousel side spin poggers")
public class jonathanPoggersAutoRedSpin extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorMiddle;
    private DcMotor intakeMotor;
    private DcMotor carouselMotor;
    private DcMotor slideMotor;
    private Servo bucketServo;
    private Servo bucketServo2;

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

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && !done) {

            //move forward
            motorLeft.setPower(0.5);
            motorRight.setPower(0.5);
            sleep(2000);
            motorLeft.setPower(0);
            motorRight.setPower(0);
            //move to shipping hub
            motorMiddle.setPower(-0.75);
            sleep(1000);
            motorMiddle.setPower(0);
            //dump bucket
            bucketServo.setPosition(0.9f);
            bucketServo2.setPosition(0.1f);
            sleep(1000);
            bucketServo.setPosition(0.25f);
            bucketServo2.setPosition(0.75f);
            sleep(1000);
            //move to carousel
            motorMiddle.setPower(1);
            sleep(4200);
            motorMiddle.setPower(0);
            //spin carousel
            motorRight.setPower(-0.7);
            motorLeft.setPower(-1);
            sleep(3000);
            motorLeft.setPower(0);
            motorRight.setPower(0);
            carouselMotor.setPower(-0.7);
            sleep(3500);
            carouselMotor.setPower(0);
            //park
            motorRight.setPower(-0.5);
            motorLeft.setPower(-0.5);
            sleep(2000);
            motorLeft.setPower(0);
            motorRight.setPower(0);
            done = true;
        }
    }
}
