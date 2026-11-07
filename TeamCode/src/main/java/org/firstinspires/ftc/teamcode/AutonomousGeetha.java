package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.rotate_coordinates;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.drive;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.get_to_target;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.spin_to_face;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.move_servos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;


import java.util.Base64;
import java.util.List;
import java.util.ArrayList;

@Autonomous(name = "Autonomous Geetha", group = "Robot")
public class AutonomousGeetha extends LinearOpMode {

    private List<DcMotor> wheel_motors = new ArrayList<>();
    private List<Servo> flower_servos = new ArrayList<>();
    private List<Double> robot_position = new ArrayList<>();
    private List<List<List<Double>>> targets_list = new ArrayList<>();
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private GyroSensor imu;
    private DcMotor intakeMotor;
    private Servo flowerServo1;
    private Servo flowerServo2;

    @Override
    public void runOpMode() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

        imu = hardwareMap.get(GyroSensor.class, "imu");

        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");

        flowerServo1 = hardwareMap.get(Servo.class, "flower_servo_1");
        flowerServo2 = hardwareMap.get(Servo.class, "flower_servo_2");

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

        robot_position.add(9.0);
        robot_position.add(104.0);

        // Wait for driver to press start
        telemetry.addData(">", "Touch Play to run Auto");
        telemetry.update();

        waitForStart();

        // Run Auto if stop was not pressed.
        if (opModeIsActive()) {
            intakeMotor.setPower(1);
        }
    }
}