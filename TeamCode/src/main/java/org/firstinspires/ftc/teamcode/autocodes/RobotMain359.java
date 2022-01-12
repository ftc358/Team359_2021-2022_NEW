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

    public void forward (double time, float power){
        motorLeft.setPower(power);
        motorRight.setPower(power);
        sleep((double) (time/1000.0);

    }

    public void turn (int degree, double power){
        int ticks = (int) (degree * TURN_FACTOR);

        //Reset Encoders
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        lf.setTargetPosition(lf.getCurrentPosition() - ticks);
        lb.setTargetPosition(lb.getCurrentPosition() - ticks);
        rf.setTargetPosition(rf.getCurrentPosition() + ticks);
        rb.setTargetPosition(rb.getCurrentPosition() + ticks);

        //Set Drive Power
        lf.setPower(0.5 * power);
        lb.setPower(0.5 * power);
        rf.setPower(0.5 * power);
        rb.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (lf.isBusy() && lb.isBusy() && rf.isBusy() && rb.isBusy()){
            telemetry.addData("lf", -lf.getCurrentPosition());
            telemetry.addData("rf", -rf.getCurrentPosition());
            telemetry.addData("lb", -lb.getCurrentPosition());
            telemetry.addData("rb", -rb.getCurrentPosition());
            telemetry.update();
            //Wait Until Target Position is Reached
        }
        sleep(500);
    }

    public void carousel(String state){

        //Reset Encoders
        crMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        crMotor.setTargetPosition(crMotor.getCurrentPosition() + 3000);

        //Set Drive Power
        if (state == "red") {
            crMotor.setPower(0.3);
        } else if (state == "blue") {
            crMotor.setPower(-0.3);
        }

        //Set to RUN_TO_POSITION mode
        crMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (crMotor.isBusy()){
        }
    }

    public void slide(int position){
        /**
         * LEVEL 1 = 500
         * LEVEL 2 = 1000
         * MAX / LEVEL 3 = 1700
         * */
        //Set Target Position
        slideMotor.setTargetPosition(slideMotor.getCurrentPosition() + position);

        //Set Drive Power
        if (position > slideMotor.getCurrentPosition()) {
            slideMotor.setPower(0.5);
        } else if (position < slideMotor.getCurrentPosition()){
            slideMotor.setPower(-0.3);
        }


        //Set to RUN_TO_POSITION mode
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (slideMotor.isBusy()){
        }
    }

    /* public void intake (){
        //Reset Encoders
        crMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        crMotor.setTargetPosition(crMotor.getCurrentPosition() + 2000);

        crMotor.setPower(0.5);

        //Set to RUN_TO_POSITION mode
        crMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (crMotor.isBusy()){
            intakeServo.setPower(0.3);
        }
    }
*/
}
