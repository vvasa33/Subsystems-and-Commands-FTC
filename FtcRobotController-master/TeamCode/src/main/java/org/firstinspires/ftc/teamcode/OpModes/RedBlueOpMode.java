package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Assets.HardwareBuilder;

public class RedBlueOpMode extends LinearOpMode {
    HardwareBuilder robot = new HardwareBuilder(this, false);

    public void runOpMode() throws RuntimeException{
        waitForStart();
        while (!opModeIsActive()) {
            robot.drive.translateRobot();
            robot.lift.moveLift();
            robot.claw.moveClaw();
        }
    }
}
