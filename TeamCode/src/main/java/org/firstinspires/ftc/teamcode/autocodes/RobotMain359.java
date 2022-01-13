package org.firstinspires.ftc.teamcode.autocodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;

public abstract class RobotMain359 extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor linearSlide;
    DcMotor Intake;
    DcMotor Wheel;
    Servo cube;

    public double driveFactor = 0.5; //for TeleOp
    public long lastTime = System.currentTimeMillis();
    public int timeElapsed = 1000; // this is in milliseconds

    final double DRIVE_FACTOR = 30 * (10/9.2) * (10/10.2);
    final double TURN_FACTOR = 5 * (90.0/95);

    public void TEST_CHASSIS_INITIALIZE() throws InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("Intake");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        Wheel = hardwareMap.dcMotor.get("Wheel");
        cube = hardwareMap.servo.get("bucketServo");


        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Wheel = hardwareMap.dcMotor.get("crMotor");
        Wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorMiddle.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        linearSlide.setDirection(DcMotor.Direction.REVERSE);
    }

    // TeleOp Switch Drive
    public double switchDriveUp(double df){
        if (df == 0.2) {
            return 0.5;
        }
        else if (df == 0.5){
            return 0.8;
        }
        else if (df == 0.8){
            return 0.8;
        }
        return df;
    }

    public double switchDriveDown(double df){
        if (df == 0.2) {
            return 0.2;
        }
        else if (df == 0.5){
            return 0.2;
        }
        else if (df == 0.8){
            return 0.5;
        }
        return df;
    }

    public void forward (long time, float power){
        motorLeft.setPower(power);
        motorRight.setPower(power);
        sleep(time/1000);

    }

    public void turn (int degree, double power){



    }

    public void carousel(String state){

    }

    public void slide(int position){

    }

    public void intake (){

    }

}
