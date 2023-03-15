package org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends SubsystemBase {

    public static MotorEx lift1, lift2;
    public static MotorGroup lift;

    public enum LiftState {
        COLLECTION(0),
        LEVEL_1(1900),
        LEVEL_2(3200),
        LEVEL_3(4300),
        MANUAL(-1), //filler
        STACK(STACK_CONE_OFFSET * stackSize);

        private int state;

        public int getStateValue() {
            return this.state;
        }

        private LiftState(int state) {
            this.state = state;
        }
    }
    public static LiftState liftState;
    public static final double LIFT_UP_CONST = 1.0d;
    public static final double LIFT_SLOW_UP_CONST = LIFT_UP_CONST * 0.2d;
    public static final double LIFT_NO_POWER_CONST = 0.0d;
    public static final double LIFT_MANUAL_UP_CONST = LIFT_UP_CONST * 0.7d;
    public static final int LIFT_SLOW_THRESHOLD = 538;

    public static final int STACK_CONE_OFFSET = 147;
    public static int stackSize = 5 + 1;

    public Lift(HardwareMap hardwareMap) {
        lift1 = new MotorEx(hardwareMap, "lift");
        lift2 = new MotorEx(hardwareMap, "lift2");
        lift = new MotorGroup(lift1, lift2);

        //TODO ask zach to push code to repo
        lift1.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        lift2.motor.setDirection(DcMotorSimple.Direction.FORWARD);

        lift.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift.set(0);

        //lift.motor.setMode((isAuto) ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftState = LiftState.COLLECTION;
    }

    public void update() {
        lift.setTargetPosition(liftState.getStateValue());
        lift.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.set(LIFT_UP_CONST);
    }

    @Override
    public void periodic() {
        if (lift.motor.isBusy()) {
            if (Math.abs(liftState.getStateValue() - lift.getCurrentPosition()) < LIFT_SLOW_THRESHOLD) {
                lift.set(LIFT_SLOW_UP_CONST);
            } else {
                lift.set(LIFT_NO_POWER_CONST);
                lift.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }
    }
}
