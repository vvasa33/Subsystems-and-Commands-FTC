package org.firstinspires.ftc.teamcode.tryingftclib.Hardware;

import org.opencv.core.Point;
import org.opencv.core.Rect;

import static java.lang.Math.PI;

public final class HardwareConstants {
    private HardwareConstants() {}

    //constants
    public static final int NEVEREST_20_TICKS_PER_ROT = 538;

    public static final double DRIVE_SPEED_CONST = 0.6d;
    public static final double DRIVE_SLOW_MODE_CONST = 0.35d; //0.4d
    public static final double DRIVE_SLOW_LIFT_UP_CONST = 0.35d; //0.4d
    public static final double DRIVE_SLOW_MODE_AND_LIFT_UP_CONST = 0.25;

    public static final double LIFT_UP_CONST = 1.0d;
    public static final double LIFT_SLOW_UP_CONST = LIFT_UP_CONST * 0.2d;
    public static final double LIFT_NO_POWER_CONST = 0.0d;
    public static final double LIFT_MANUAL_UP_CONST = LIFT_UP_CONST * 0.7d;

    public static final double CLAW_OPEN_CONST = 0.35d + 0.2d;
    public static final double CLAW_CLOSED_CONST = 0.15d + 0.2d;
    public static final int COLLECTION_POS = 0;
    public static final int LEVEL_1_POS = 1900;
    public static final int LEVEL_2_POS = 3200;
    public static final int LEVEL_3_POS = 4300;
    public static final int LIFT_SLOW_THRESHOLD = NEVEREST_20_TICKS_PER_ROT;

    //cone stack values
    public static final int STACK_CONE_OFFSET = 147;

    //physical measurements
    public static final double LX = 7.5d;
    public static final double LY = 4.5d;
    public static final double LX_PLUS_LY = LX + LY;
    public static final double WHEEL_RAD = 2.0d;

    //turnToAngle error
    public static final double ANGLE_ERROR = PI/90;

    //ROI
    public static final Rect ROI = new Rect(
            new Point(180, 60),
            new Point(210, 120)
    );

    //program group strings
    public static final String autoGroup = "Auto";
    public static final String opModeGroup = "TeleOp";
    public static final String controlGroup = "Control";
}