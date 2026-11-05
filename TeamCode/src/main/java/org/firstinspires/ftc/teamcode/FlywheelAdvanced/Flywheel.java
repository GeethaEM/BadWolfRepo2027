package org.firstinspires.ftc.teamcode.FlywheelAdvanced;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Flywheel {
    private DcMotorEx motor_1;
    private DcMotorEx motor_2;

    //Clicks per rotation on encoder
    private double encoderCPM = 28; //change depending on motor later
    private double gearRatio = 3; //change depending on actual gearratio for badwolf

    //velocity feed forward constant, static friction, and proportional constant
    private double kV, kS, kP; //will tune later -> after tuning set each value equal to the tuned value



    //initialize motors again
    public void init(HardwareMap hwMap) {
        motor_1 = hardwareMap.get(DcMotorEx.class, "leftMotor");
        motor_1 = hardwareMap.get(DcMotorEx.class, "leftMotor");
        //Because we're building custom loop, we don't want encoder.
        //This allows us to still read the motor encoder, but no run at speed it wants
        motor_1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_2.setDirection(DcMotorSimple.Direction.REVERSE); //-> might have to reverse other motor
    }

    //tell our motors how fast to go:
    public void setMotorPower(double power) {
        motor_1.setPower(power);
        motor_2.setPower(power);
    }
9
    public void stopMotors() {
        motor_1.setPower(0);
        motor_2.setPower(0);
    }

    public void setMotorRPM(double targetRPM) {
        //should call this every loop
        double error = targetRPM - getRPM();
        double ff = (kV * targetRPM) + kS; // feedforward
        double fb = error * kP;
        double power = ff + fb;

        setMotorPower(power);

    }

    //how fast we going
    public double getTicksPerSec() {
        return motor_1.getVelocity();
    };

    //calculating total RPM then ticks per second
    public double getRPM() {
        return((getTicksPerSec()/encoderCPM) * 60) / gearRatio; //37:50 in video
    }

}
