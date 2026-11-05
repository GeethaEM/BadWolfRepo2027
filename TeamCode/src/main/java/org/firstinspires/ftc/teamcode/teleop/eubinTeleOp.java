package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="this is a name", group = "ur mom")
public class eubinTeleOp extends OpMode {
    int iteration = 0;
    private DcMotor theLeftMotor;
    private DcMotor theRightMotor;
    private Servo claws;

    public void init() {
        theLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        theRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        claws = hardwareMap.get(Servo.class, "claws");


        telemetry.addData("me init", null);
        telemetry.update();
    }

    public void init_loop() {
        iteration++;
        telemetry.addData("ts run ", iteration);
        telemetry.update();
    }

    public void start() {
        theRightMotor.setPower(1);
        theLeftMotor.setPower(-1); // spinnys counter clockwises :)
    }

    public void loop() {

    }

    public void stop() {
        theRightMotor.setPower(0);
        theLeftMotor.setPower(0);
        telemetry.addData("i am stoppings", 12341234);
        telemetry.update();
    }
}
