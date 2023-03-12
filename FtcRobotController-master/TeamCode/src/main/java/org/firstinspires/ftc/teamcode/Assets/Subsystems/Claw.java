package org.firstinspires.ftc.teamcode.Assets.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Assets.Hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.Assets.Hardware.HardwareConstants;

public class Claw extends BaseHardware {
    private static Servo clawServo;

    private Gamepad gamepad2;



    private enum ClawState {
        OPEN,
        CLOSED
    }
    public ClawState clawState;


    public void init() {
        this.gamepad2 = opMode.gamepad2;

        clawServo = opMode.hardwareMap.get(Servo.class, "claw");

        clawServo.setDirection(Servo.Direction.FORWARD);

        clawServo.setPosition(HardwareConstants.CLAW_CLOSED_CONST);

        opMode.telemetry.addData("Claw Status: ", "Initalized");
        opMode.telemetry.update();
    }

    public void moveClaw() {
        if (gamepad2.left_trigger > 0.5f) {
            clawState = ClawState.OPEN;
            clawServo.setPosition(HardwareConstants.CLAW_OPEN_CONST);
        } else if (gamepad2.right_trigger > 0.5f) {
            clawState = ClawState.CLOSED;
            clawServo.setPosition(HardwareConstants.CLAW_CLOSED_CONST);
        }
    }


}
