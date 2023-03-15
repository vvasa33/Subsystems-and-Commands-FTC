package org.firstinspires.ftc.teamcode.MyCode.Assets.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MyCode.Assets.Hardware.HardwareConstants;
import org.firstinspires.ftc.teamcode.MyCode.Assets.Hardware.BaseHardware;


public class Drivebase extends BaseHardware {
    private static DcMotorEx[] drivetrain;

    private Gamepad gamepad1;

    public void init() {
        this.gamepad1 = opMode.gamepad1;
        drivetrain  = new DcMotorEx[4];
        drivetrain[0] = opMode.hardwareMap.get(DcMotorEx.class, "fl");
        drivetrain[1] = opMode.hardwareMap.get(DcMotorEx.class, "fr");
        drivetrain[2] = opMode.hardwareMap.get(DcMotorEx.class, "bl");
        drivetrain[3] = opMode.hardwareMap.get(DcMotorEx.class, "br");

        drivetrain[0].setDirection(DcMotorSimple.Direction.FORWARD);
        drivetrain[1].setDirection(DcMotorSimple.Direction.REVERSE);
        drivetrain[2].setDirection(DcMotorSimple.Direction.FORWARD);
        drivetrain[3].setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotorEx motor : drivetrain) {
            motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            motor.setPower(0);
            motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }

        opMode.telemetry.addData("Drivetrain status: ", "Initialized");
    }

    public void translateRobot() {
        double flPower = +gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        double frPower = -gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;
        double blPower = -gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_x;
        double brPower = +gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_x;
        if (gamepad1.left_trigger > 0.5f && Lift.lift.getCurrentPosition() > 4 * HardwareConstants.LIFT_SLOW_THRESHOLD) {
            flPower *= HardwareConstants.DRIVE_SLOW_MODE_AND_LIFT_UP_CONST;
            frPower *= HardwareConstants.DRIVE_SLOW_MODE_AND_LIFT_UP_CONST;
            blPower *= HardwareConstants.DRIVE_SLOW_MODE_AND_LIFT_UP_CONST;
            brPower *= HardwareConstants.DRIVE_SLOW_MODE_AND_LIFT_UP_CONST;
        } else if (gamepad1.left_trigger > 0.5f) {
            flPower *= HardwareConstants.DRIVE_SLOW_MODE_CONST;
            frPower *= HardwareConstants.DRIVE_SLOW_MODE_CONST;
            blPower *= HardwareConstants.DRIVE_SLOW_MODE_CONST;
            brPower *= HardwareConstants.DRIVE_SLOW_MODE_CONST;
        } else if (Lift.lift.getCurrentPosition() > 4 * HardwareConstants.LIFT_SLOW_THRESHOLD) {
            flPower *= HardwareConstants.DRIVE_SLOW_LIFT_UP_CONST;
            frPower *= HardwareConstants.DRIVE_SLOW_LIFT_UP_CONST;
            blPower *= HardwareConstants.DRIVE_SLOW_LIFT_UP_CONST;
            brPower *= HardwareConstants.DRIVE_SLOW_LIFT_UP_CONST;
        }

        drivetrain[0].setPower(flPower);
        drivetrain[1].setPower(frPower);
        drivetrain[2].setPower(blPower);
        drivetrain[3].setPower(brPower);
    }


}
