package org.firstinspires.ftc.teamcode.Assets.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class BaseHardware {

    //name
    public static final String name = "Bare Bones";
    public static final String version = "v1.0.0";

    //LinearOpMode, ElapsedTime
    protected LinearOpMode opMode;
    public ElapsedTime elapsedTime;



    //init
    public abstract void init();
}