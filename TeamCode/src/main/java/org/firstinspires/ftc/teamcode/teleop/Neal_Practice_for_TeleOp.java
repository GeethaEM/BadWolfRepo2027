package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.Flywheel_Controller;

@TeleOp
public class Neal_Practice_for_TeleOp extends OpMode {
    //Motor Variables
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor Flywheel;
    private boolean x_pressed_last = false;
    Flywheel_Controller Flywheel_Controller;
    /*Variable types:
    Double = can have decimal places
    Long = no decimal places
    Boolean = true or false
    String = words*/

    @Override
    public void init() {
        //Hardware ----> Motor Definitions
        frontRight = hardwareMap.get(DcMotor.class,"frontRight");
        frontLeft = hardwareMap.get(DcMotor.class,"frontLeft");
        backRight = hardwareMap.get(DcMotor.class,"backRight");
        backLeft = hardwareMap.get(DcMotor.class,"backLeft");
        Flywheel = hardwareMap.get(DcMotor.class,"Flywheel");
        Flywheel_Controller = new Flywheel_Controller(
                Flywheel
        );
    }

    @Override
    public void init_loop() {

    }
    @Override
    public void start() {

    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        double time = System.currentTimeMillis();
        boolean x_now = gamepad1.x  ||  gamepad2.x;
        // || = or
        if (x_now && !x_pressed_last) {
            Flywheel_Controller.onFor(3333);
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        Flywheel_Controller.update(time);
    }

    @Override
    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        super.stop();
    }
}
