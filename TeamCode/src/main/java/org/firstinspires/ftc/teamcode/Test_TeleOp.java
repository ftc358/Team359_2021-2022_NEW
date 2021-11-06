package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.lang.*;

// TODO: change to autonomous

@TeleOp
public class Test_TeleOp extends RobotMain358{

    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();
        while(opModeIsActive()){
//            telemetry.addData("millisecond", System.currentTimeMillis());
//            telemetry.addData("millisecond", lastTime);

            // define slow drive
            if (gamepad1.right_bumper){
                if (System.currentTimeMillis() - lastTime > timeElapsed){
                    driveFactor = switchDrive(driveFactor);
                    lastTime = System.currentTimeMillis();
                }
            }

            // add telemetry
            telemetry.addData("precision status", driveFactor);

            // define drive power
            double leftDrive = gamepad1.left_stick_y;
            double rightDrive = gamepad1.right_stick_y;
            double finalLeft = leftDrive * driveFactor;
            double finalRight = rightDrive * driveFactor;

            // set motors to drive power
            lf.setPower(finalLeft);
            lb.setPower(finalLeft);
            rf.setPower(finalRight);
            rb.setPower(finalRight);

            // add telemetry
            telemetry.addData("left power", finalLeft);
            telemetry.addData("right power", finalRight);
            telemetry.update();

        }
    }
}
