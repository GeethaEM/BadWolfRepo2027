package org.firstinspires.ftc.teamcode.FlywheelAdvanced;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/* What this testing process does is basically rather than getting until we have
initial starting point where we break over the amount of power needed, we're figuring
out what is the power needed to send to the motor to be able to get it to consistently
achieve 1500 RPM without proportionally controlling itself -> there's a min value
or normal value that we send to motor, a normal voltage that holds it at goal RPM
 */
public class kVTuner extends OpMode {
    Flywheel flywheel = new Flywheel();

    public static double kV = 0.01;
    public double kS = 0.17; //change to the tuned value
    public double goalRPM = 1500; //Find highest point to launch a ball in flywheel away and put it in

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};

    int incrementIdx = 4; //Start at 0.001

    @Override
    public void init() {
        flywheel.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.dpadRightWasPressed() && incrementIdx < 4) {
            incrementIdx++;
        } else if (gamepad1.dpadLeftWasPressed() && incrementIdx > 0) {
            incrementIdx--;
        }

        if (gamepad1.a) {
            goalRPM = 1500;
        } else if (gamepad1.b) {
            goalRPM = 800; //example to show that things can scale
        }

        double currentStep = increments[incrementIdx];

        //Change kV
        if (gamepad1.dpadUpWasPressed()) { kV += currentStep; }
        if (gamepad1.dpadDownWasPressed()) { kV -= currentStep; }

        double power = (kV * goalRPM) + kS;
        flywheel.setMotorPower(power);

        telemetry.addData("Step", "%.6f", currentStep);
        telemetry.addData("kV", "%.6f", kV);
        telemetry.addData("RPM", flywheel.getRPM());
        telemetry.addData("Ticks per Sec", flywheel.getTicksPerSec());

    }
}
