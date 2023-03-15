package org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Subsystems;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw extends SubsystemBase {

    public enum clawStates {
        CLOSED,
        OPEN
    }
    public static clawStates clawState = clawStates.CLOSED;

    public static final double CLAW_OPEN_CONST = 0.35d + 0.2d;
    public static final double CLAW_CLOSED_CONST = 0.15d + 0.2d;

    public Servo claw;

    public Claw(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        update();
    }

    public void update() {
        switch (clawState) {
            case OPEN:
                claw.setPosition(CLAW_OPEN_CONST);
                break;
            case CLOSED:
                claw.setPosition(CLAW_CLOSED_CONST);
        }
    }
}
