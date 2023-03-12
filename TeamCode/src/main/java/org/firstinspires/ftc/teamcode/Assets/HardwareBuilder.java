package org.firstinspires.ftc.teamcode.Assets;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assets.Hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.Assets.Subsystems.*;

public class HardwareBuilder extends BaseHardware {
    public Claw claw;
    public Lift lift;
    public Drivebase drive;

    private boolean isAuto;

    public HardwareBuilder(LinearOpMode op, boolean isAuto) {
       opMode = op;
       this.isAuto = isAuto;
    }

    public void init() {
        //initialize subsystems
        claw = new Claw();
        lift = new Lift(isAuto);
        drive = new Drivebase();
    }
}