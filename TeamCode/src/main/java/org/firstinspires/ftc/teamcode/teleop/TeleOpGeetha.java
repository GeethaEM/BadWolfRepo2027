//This imports all the ftc commands and stuff
package org.firstinspires.ftc.teamcode.teleop;

//This is to import the teleop and opMode stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//this is stuff you need to import for the code to work with hardware and stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOpGeetha", group = "Linear OpMode")
public class TeleOpGeetha extends LinearOpMode {

    //I'm creating the variables that are going to be for the wheel motors and making them empty for now
    static DcMotor frontLeftMotor = null;
    static DcMotor backLeftMotor = null;
    static DcMotor frontRightMotor = null;
    static DcMotor backRightMotor = null;

    //I'm creating the variables that are going to be for some other motors here and making them empty for now as well
    static DcMotorEx motor = null;
    static Servo hood1Servo = null;

    //I'm not really sure how this works, but it's for opMode (based on the actual way to enter the opMode, I guess it overrides what's happening the class and interrupts it
    @Override
    public void runOpMode() throws InterruptedException {

        //All the variables I set aside for motors and made empty I am now actually assigning the motors from the hardware map (this stores all the actual motors of the robot)
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

        motor = hardwareMap.get(DcMotorEx.class, "intake");

        hood1Servo = hardwareMap.get(Servo.class, "hood_1_Servo");

        //This is setting the directions for the spinning of the motors (the reverse is because of some weird meca wheel stuff)
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.a){
                hood1Servo.setPosition(1);
                telemetry.addData("a", "pressed");
            }
            if (gamepad1.b){
                hood1Servo.setPosition(0.45);
                telemetry.addData("b", "pressed");
            }

            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

        }
    }
}