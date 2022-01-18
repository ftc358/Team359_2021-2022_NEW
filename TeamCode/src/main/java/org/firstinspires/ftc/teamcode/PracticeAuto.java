package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.RobotMain359;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="PracticeAuto")
public class PracticeAuto extends RobotMain359
{
    // Constant event identifier
    public final int GO_AND_TURN = 0;

    /* event() executes procedural code within our opMode loop dependent on
    * EVENT var */
    public void event()
    {
        telemetry.addData("LEFT_DRIVE: ", LEFT_DRIVE);
        telemetry.addData("RIGHT_DRIVE: ", RIGHT_DRIVE);

        telemetry.addData("left_effective: ", left_effective);
        telemetry.addData("right_effective: ", right_effective);
        telemetry.update();

        if(STATE == GO_AND_TURN) {
            forward(10);
            STATE++;
        }
    }

    public void runOpMode() throws InterruptedException
    {
        INIT();
        waitForStart();

        while(opModeIsActive())
        {
            event();
            UPDATE();
        }
    }
}
