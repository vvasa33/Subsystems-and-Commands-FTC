package org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Builder;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.lock.qual.GuardedBy;
import org.firstinspires.ftc.teamcode.tryingftclib.Hardware.Subsystems.*;

public class HardwareBuilder {
    //Initialize Subsystems
    public static Claw claw;
    public static Lift lift;
    public static Drivetrain drive;

    //idk if i need this
    private boolean isAuto;

    //cool new imu code (threaded) (cool)
    private final Object imuLock = new Object();
    @GuardedBy("imuLock")
    public final BNO055IMU imu;
    private double imuAngle;
    private Thread imuThread;



    public HardwareBuilder(HardwareMap hardwareMap, LinearOpMode opMode, boolean isAuto) {
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);
        drive = new Drivetrain(hardwareMap, opMode);
        this.isAuto = isAuto;

        //initialize imu
        synchronized (imuLock) {
            imu = hardwareMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            imu.initialize(parameters);
        }
    }

    public void startIMUThread(LinearOpMode opMode) {
        imuThread = new Thread(() -> {
            while (!opMode.isStopRequested() && opMode.opModeIsActive()) {
                synchronized (imuLock) {
                    imuAngle = -imu.getAngularOrientation().firstAngle;
                }
            }
        });
        imuThread.start();
    }

    public double getCurHeading() {
        return imuAngle;
    }
}
