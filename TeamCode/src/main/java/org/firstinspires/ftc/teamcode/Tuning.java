package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.teamcode.pedro.procedures.TwoWheelTuner;


public class Tuning {
    @Tuner
    public static Procedure twoWheelTuner() {
        return new TwoWheelTuner();
    }
}