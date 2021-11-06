package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class motorpractice extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor Intake;


    float leftMotorPower = 0f;
    float rightMotorPower = 0f;
    float middleMotorPower = 0f;

    public void runOpMode() throws InterruptedException {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        Intake = hardwareMap.dcMotor.get("motorMiddle");

        waitForStart();

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            leftMotorPower = gamepad1.left_stick_y;
            rightMotorPower = gamepad1.right_stick_y;
            middleMotorPower = gamepad1.right_stick_x;

            if(gamepad1.x)
            {
                Intake.setPower(-1);
            }
            else
            {
                Intake.setPower(gamepad1.right_trigger);
            }
            // forward and side-to-side case
            // TODO deal with drift
            if (Math.abs(gamepad1.right_stick_x) > 0 && Math.abs(gamepad1.left_stick_y) > 0) {
                //to the right
                if(gamepad1.right_stick_x > 0){
                    leftMotorPower = 0;
                    rightMotorPower = gamepad1.right_stick_x;
                }
                //to the left
                else if (gamepad1.right_stick_x < 0){
                    rightMotorPower = 0;
                    leftMotorPower = gamepad1.right_stick_x;
                }
            }
            // turning side to side
            else if (Math.abs(gamepad1.right_stick_x) > 0){
                if(gamepad1.right_stick_x > 0){
                    leftMotorPower = -gamepad1.right_stick_x;
                    rightMotorPower = gamepad1.right_stick_x;
                }
                else if (gamepad1.right_stick_x < 0){
                    rightMotorPower = gamepad1.right_stick_x;
                    leftMotorPower = -gamepad1.right_stick_x;
                }
            }

            motorLeft.setPower(leftMotorPower);
            motorRight.setPower(rightMotorPower);
            motorMiddle.setPower(middleMotorPower);
        }
    }
}