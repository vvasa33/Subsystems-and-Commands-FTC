package org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain extends SubsystemBase {
    public static MotorEx fl, fr, bl, br;
    public static MecanumDrive drive;

    public static GamepadEx gamepad1;

    public enum DriveState {
        REGULAR(1.0),
        TRIGGER_SLOW_MODE(0.35),
        LIFT_SLOW_MODE(0.35),
        VERY_SLOW_MODE(0.25);

        private double state;

        public double getStateValue() {
            return this.state;
        }

        private DriveState(double state) {
            this.state = state;
        }
    }
    public static DriveState driveState;

    //TODO update code to work with roadrunner (ask zach to push)
    public Drivetrain(HardwareMap hardwareMap, LinearOpMode opMode) {
        fl = new MotorEx(hardwareMap, "fl");
        fr = new MotorEx(hardwareMap, "fr");
        bl = new MotorEx(hardwareMap, "bl");
        br = new MotorEx(hardwareMap, "br");

        drive = new MecanumDrive(fl,fr,bl,br);

        driveState = DriveState.REGULAR;

        gamepad1 = new GamepadEx(opMode.gamepad1);
    }

    @Override
    public void periodic() {
        double val = driveState.getStateValue();
        drive.driveRobotCentric(gamepad1.getLeftX() * val, gamepad1.getLeftY() * val, gamepad1.getRightX() * val);
    }
}
