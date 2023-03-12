package org.firstinspires.ftc.teamcode.Assets.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Assets.Hardware.HardwareConstants;


import org.firstinspires.ftc.teamcode.Assets.Hardware.BaseHardware;

public class Lift extends BaseHardware {

    public static DcMotorEx lift, lift2;
    private Gamepad gamepad2;

    private enum LiftState {
        COLLECTION,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        STACK,
        MANUAL
    }
    public LiftState liftState;

    private int stackSize;

    private DcMotor.RunMode initRunmode;

    public Lift(boolean isAuto) {
        initRunmode = (isAuto) ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    }

    public void init() {
        this.gamepad2 = opMode.gamepad2;

        lift = opMode.hardwareMap.get(DcMotorEx.class, "lift");
        lift2 = opMode.hardwareMap.get(DcMotorEx.class, "lift2");

        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        lift2.setDirection(DcMotorSimple.Direction.FORWARD);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setPowers(0);

        setModes(initRunmode);

        liftState = LiftState.MANUAL;

        stackSize = 5 + 1;

        opMode.telemetry.addData("Lift status: ", "Initialized");
    }

    private void setPowers(double power) {
        lift.setPower(power);
        lift2.setPower(power);
    }

    private void setModes(DcMotorEx.RunMode runmode) {
        lift.setMode(runmode);
        lift2.setMode(runmode);
    }

    private void setTargetPositions(int target) {
        lift.setTargetPosition(target);
        lift2.setTargetPosition(target);
    }

    private void manualLift() {
        if (gamepad2.dpad_up || gamepad2.dpad_down) {
            liftState = LiftState.MANUAL;
            setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            setPowers(HardwareConstants.LIFT_SLOW_UP_CONST * (gamepad2.dpad_up ? 1 : -1));
        }
    }

    //all code is written by zach
    private void presetLift() {
        if (gamepad2.a && liftState != LiftState.COLLECTION) {
            liftState = LiftState.COLLECTION;
            setTargetPositions(HardwareConstants.COLLECTION_POS);
            setModes(DcMotor.RunMode.RUN_TO_POSITION);
            setPowers(HardwareConstants.LIFT_UP_CONST);

        } else if (gamepad2.b && liftState != LiftState.LEVEL_1) {
            liftState = LiftState.LEVEL_1;
            setTargetPositions(HardwareConstants.LEVEL_1_POS);
            setModes(DcMotor.RunMode.RUN_TO_POSITION);
            setPowers(HardwareConstants.LIFT_UP_CONST);

        } else if (gamepad2.x && liftState != LiftState.LEVEL_2) {
            liftState = LiftState.LEVEL_2;
            setTargetPositions(HardwareConstants.LEVEL_2_POS);
            setModes(DcMotor.RunMode.RUN_TO_POSITION);
            setPowers(HardwareConstants.LIFT_UP_CONST);

        } else if (gamepad2.y && liftState != LiftState.LEVEL_3) {
            liftState = LiftState.LEVEL_3;
            setTargetPositions(HardwareConstants.LEVEL_3_POS);
            setModes(DcMotor.RunMode.RUN_TO_POSITION);
            setPowers(HardwareConstants.LIFT_UP_CONST);

        } else if (gamepad2.right_bumper && liftState != LiftState.STACK) {
            stackSize--;
            if (stackSize < 0) stackSize = 0;
            liftState = LiftState.STACK;
            lift.setTargetPosition(stackSize * HardwareConstants.STACK_CONE_OFFSET);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(HardwareConstants.LIFT_UP_CONST);
            lift2.setTargetPosition(stackSize * HardwareConstants.STACK_CONE_OFFSET);
            lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift2.setPower(HardwareConstants.LIFT_UP_CONST);

            setTargetPositions(stackSize * HardwareConstants.STACK_CONE_OFFSET);
            setModes(DcMotor.RunMode.RUN_TO_POSITION);
            setPowers(HardwareConstants.LIFT_UP_CONST);
        } else if (gamepad2.left_bumper) {
            stackSize = 6;
        }
    }

    private void updateLift() {
        switch (liftState) {
            case COLLECTION:
                if (lift.isBusy()) {
                    if (Math.abs(HardwareConstants.COLLECTION_POS - lift.getCurrentPosition()) < HardwareConstants.LIFT_SLOW_THRESHOLD) {
                        setPowers(HardwareConstants.LIFT_SLOW_UP_CONST);
                    }
                } else {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                    setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
                break;
            case LEVEL_1:
                if (lift.isBusy()) {
                    if (Math.abs(HardwareConstants.LEVEL_1_POS - lift.getCurrentPosition()) < HardwareConstants.LIFT_SLOW_THRESHOLD) {
                        setPowers(HardwareConstants.LIFT_SLOW_UP_CONST);
                    }
                } else {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                    setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
                break;
            case LEVEL_2:
                if (lift.isBusy()) {
                    if (Math.abs(HardwareConstants.LEVEL_2_POS - lift.getCurrentPosition()) < HardwareConstants.LIFT_SLOW_THRESHOLD) {
                        setPowers(HardwareConstants.LIFT_SLOW_UP_CONST);
                    }
                } else {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                    setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
                break;
            case LEVEL_3:
                if (lift.isBusy()) {
                    if (Math.abs(HardwareConstants.LEVEL_3_POS - lift.getCurrentPosition()) < HardwareConstants.LIFT_SLOW_THRESHOLD) {
                        setPowers(HardwareConstants.LIFT_SLOW_UP_CONST);
                    }
                } else {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                    setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
                break;
            case STACK:
                if (lift.isBusy()) {
                    if (Math.abs((stackSize * HardwareConstants.STACK_CONE_OFFSET) - lift.getCurrentPosition()) < HardwareConstants.LIFT_SLOW_THRESHOLD) {
                        setPowers(HardwareConstants.LIFT_SLOW_UP_CONST);
                    }
                } else {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                    setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                }
                break;
            case MANUAL: default:
                if (!gamepad2.dpad_up && !gamepad2.dpad_down) {
                    setPowers(HardwareConstants.LIFT_NO_POWER_CONST);
                }
                break;
        }
    }

    public void moveLift() {
        manualLift();
        presetLift();
        updateLift();
    }
}
