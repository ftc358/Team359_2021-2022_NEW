//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import java.util.List;
//
//@TeleOp(name = "jonathanTest")
//public class intakeAndDriveTest1 extends LinearOpMode{
//    private DcMotor motorLeft;
//    private DcMotor motorRight;
//    private DcMotor motorMiddle;
//    private DcMotor intakeMotor;
//
//
//    //settings not final!!!!!!
//    //change settings here:
//
//    //driving stuff
//    private float leftMotorPower = 0f;
//    private float rightMotorPower = 0f;
//    private float middleMotorPower = 0f;
//    //intake stuff
//    public float intakeMotorPower = 0.5f;
//
//    public void runOpMode() throws InterruptedException {
//
//        motorLeft = hardwareMap.dcMotor.get("motorLeft");
//        motorRight = hardwareMap.dcMotor.get("motorRight");
//        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
//        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
//
//
//        waitForStart();
//
//        motorLeft.setDirection(DcMotor.Direction.REVERSE);
//
//        while (opModeIsActive()) {
//            telemetry.addData("opModeIsActive", opModeIsActive());
//            telemetry.update();
//
//            leftMotorPower = gamepad1.left_stick_y;
//            rightMotorPower = gamepad1.right_stick_y;
//            middleMotorPower = gamepad1.right_stick_x;
//
//            //drive stuff
//
//            if (Math.abs(gamepad1.right_stick_x) > 0 && Math.abs(gamepad1.left_stick_y) > 0) {
//                if(gamepad1.right_stick_x > 0){
//                    leftMotorPower = 0;
//                    rightMotorPower = gamepad1.right_stick_x;
//                }
//                else if (gamepad1.right_stick_x < 0){
//                    rightMotorPower = 0;
//                    leftMotorPower = gamepad1.right_stick_x;
//                }
//            }
//            else if (Math.abs(gamepad1.right_stick_x) > 0){
//                if(gamepad1.right_stick_x > 0){
//                    leftMotorPower = -gamepad1.right_stick_x;
//                    rightMotorPower = gamepad1.right_stick_x;
//                }
//                else if (gamepad1.right_stick_x < 0){
//                    rightMotorPower = gamepad1.right_stick_x;
//                    leftMotorPower = -gamepad1.right_stick_x;
//                }
//            }
//
//            motorLeft.setPower(leftMotorPower);
//            motorRight.setPower(rightMotorPower);
//            motorMiddle.setPower(middleMotorPower);
//
//            //intake stuff
//
//            if (gamepad1.dpad_down){
//                //spin in
//                intakeMotor.setPower(intakeMotorPower);
//            }
//            else if (gamepad1.dpad_up){
//                //spin opposite direction
//                intakeMotor.setPower(-intakeMotorPower);
//            }
//
//        }
//    }
//}


