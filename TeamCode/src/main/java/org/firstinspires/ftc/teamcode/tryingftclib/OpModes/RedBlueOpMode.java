package org.firstinspires.ftc.teamcode.tryingftclib.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Builder.HardwareBuilder;
import org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Subsystems.*;

@TeleOp (name="cool teleop")
public class RedBlueOpMode extends CommandOpMode {
    private HardwareBuilder robot;
    private ElapsedTime timer;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();
        robot = new HardwareBuilder(hardwareMap, this, false);
        robot.claw.update();
        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.OFF);
        PhotonCore.EXPANSION_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.OFF);
        PhotonCore.enable();
    }

    @Override
    public void run() {
        if (timer == null) {
            timer = new ElapsedTime();
        }

        if (gamepad2.left_trigger > 0.5f) {
            Claw.clawState = Claw.clawStates.OPEN;
        } else if (gamepad2.right_trigger > 0.5f) {
            Claw.clawState = Claw.clawStates.CLOSED;
        }

        if (gamepad2.a && Lift.liftState != Lift.LiftState.COLLECTION) {
            Lift.liftState = Lift.LiftState.COLLECTION;
            robot.lift.update();
        } else if (gamepad2.b && Lift.liftState != Lift.LiftState.LEVEL_1) {
            Lift.liftState = Lift.LiftState.LEVEL_1;
            robot.lift.update();
        } else if (gamepad2.x && Lift.liftState != Lift.LiftState.LEVEL_2) {
            Lift.liftState = Lift.LiftState.LEVEL_2;
            robot.lift.update();
        } else if (gamepad2.y && Lift.liftState != Lift.LiftState.LEVEL_3) {
            Lift.liftState = Lift.LiftState.LEVEL_3;
            robot.lift.update();
        }

        robot.claw.update();
        CommandScheduler.getInstance().run();
    }
}
