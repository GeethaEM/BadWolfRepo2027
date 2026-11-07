//This imports all the ftc commands and stuff
package org.firstinspires.ftc.teamcode.teleop;
//hello

//This is to import the teleop and opMode stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.MyTeamUtil.drive;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.move_servos;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;
import java.util.ArrayList;

@TeleOp
public class TeleOpGeetha extends LinearOpMode {
    private List<DcMotor> wheel_motors = new ArrayList<>();
    private List<Servo> flower_servos = new ArrayList<>();
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor intakeMotor;
    private Servo flowerServo1;
    private Servo flowerServo2;
    private DcMotor nectarFlywheelMotor;
    private DcMotor pollenFlywheelMotor;
    private Servo nectarGate;
    private Servo pollenGate;
    private Servo nectarHood;
    private Servo pollenHood;


    private boolean currentAButton = false;

    @Override
    public void runOpMode() throws InterruptedException {

        //All the variables I set aside for motors and made empty I am now actually assigning the motors from the hardware map (this stores all the actual motors of the robot)
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        flowerServo1 = hardwareMap.get(Servo.class, "flower_servo_1");
        flowerServo2 = hardwareMap.get(Servo.class, "flower_servo_2");


        nectarFlywheelMotor = hardwareMap.get(DcMotor.class, "flywheel_motor_nectar");
        pollenFlywheelMotor = hardwareMap.get(DcMotor.class, "flywheel_motor_pollen");

        nectarGate = hardwareMap.get(Servo.class, "gate_nectar");
        pollenGate = hardwareMap.get(Servo.class, "gate_pollen");

        nectarHood = hardwareMap.get(Servo.class, "nectarHood");
        pollenHood = hardwareMap.get(Servo.class, "nectarHood");

        //This is setting the directions for the spinning of the motors (the reverse is because of some weird meca wheel stuff)
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        wheel_motors.add(frontLeftMotor);
        wheel_motors.add(backLeftMotor);
        wheel_motors.add(frontRightMotor);
        wheel_motors.add(backRightMotor);

        flower_servos.add(flowerServo1);
        flower_servos.add(flowerServo2);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double vertical_value = -gamepad1.left_stick_y;
            double horizontal_value = gamepad1.left_stick_x;
            double spin_amount = gamepad1.right_stick_x;

            drive(horizontal_value, vertical_value, spin_amount, wheel_motors);

            boolean lastAButton = currentAButton;
            currentAButton = gamepad1.a;
            if (gamepad2.b) {
                intakeMotor.setPower(1);
            } else if (!gamepad2.b) {
                intakeMotor.setPower(0);
            }
            if (!currentAButton && lastAButton) {
                move_servos(flower_servos);
            }
            if (gamepad2.x) {
                nectarFlywheelMotor.setPower(1);
            } else if (!gamepad2.x) {
                nectarFlywheelMotor.setPower(0);
            }
            if (gamepad2.y) {
                pollenFlywheelMotor.setPower(1);
            } else if (!gamepad2.y) {
                pollenFlywheelMotor.setPower(0);
            }
            if (gamepad2.left_stick_y > 0 && gamepad2.left_stick_y < 1) {
                pollenHood.setPosition(pollenHood.getPosition() + 0.05);
            } else if (gamepad2.left_stick_y < 0 && gamepad2.left_stick_y > 0.5) {
                pollenHood.setPosition(pollenHood.getPosition() - 0.005);
            }
            if (gamepad2.right_stick_y > 0 && gamepad2.right_stick_y < 1) {
                nectarHood.setPosition(nectarHood.getPosition() + 0.05);
            } else if (gamepad2.left_stick_y < 0 && gamepad2.left_stick_y > 0.5) {
                nectarHood.setPosition(nectarHood.getPosition() - 0.005);
            }        }
    }
}
