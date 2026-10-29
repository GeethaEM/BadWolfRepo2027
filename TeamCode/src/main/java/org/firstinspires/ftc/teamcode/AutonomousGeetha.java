package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.util.List;
import java.util.ArrayList;

@Autonomous(name = "Autonomous Geetha", group = "Robot")
public class AutonomousGeetha extends LinearOpMode {
    private DcMotor frontLeftMotor;
    private DcMotor backLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private GyroSensor imu;
    public List<Double> rotate_coordinates(double cor_x, double cor_y) {
        imu = hardwareMap.get(GyroSensor.class, "imu");
        double angle = Math.toRadians(imu.getHeading());
        double x = cor_x * Math.cos(angle) - cor_y * Math.sin(angle);
        double y = cor_x * Math.sin(angle) + cor_y * Math.cos(angle);
        List<Double> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }


    public void drive(double direction_x, double direction_y, int spin) {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRight");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor.setPower(direction_y + direction_x + spin);
        backLeftMotor.setPower(direction_y - direction_x + spin);
        frontRightMotor.setPower(direction_y - direction_x - spin);
        backRightMotor.setPower(direction_y + direction_x - spin);
    }

    public void get_to_target (double cor_x, double cor_y) {
        List<Double> directions = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            double ready_target = rotate_coordinates(cor_x, cor_y).get(i);
            directions.add(1 - i, 0.00);
            while (ready_target != 0) {
                if (ready_target > 0) {
                    directions.add(i, 1.00);
                } else if (ready_target < 0) {
                    directions.add(i, -1.00);
                }
                drive(directions.get(0), directions.get(1), 0);
                ready_target = rotate_coordinates(cor_x, cor_y).get(i);
            }
            drive(0, 0, 0);
        }
    }
    public void spin_to_face(double cor_x, double cor_y) {
        double ready_target = rotate_coordinates(cor_x, cor_y).get(0);
        while (ready_target != 0) {
            if (ready_target > 0) {
                drive(0, 0, 1);
            } else if (ready_target < 0) {
                drive(0, 0, -1);
            }
            ready_target = rotate_coordinates(cor_x, cor_y).get(0);
        }
        drive(0, 0, 0);
    }

    @Override
    public void runOpMode() {


        // Wait for driver to press start
        telemetry.addData(">", "Touch Play to run Auto");
        telemetry.update();

        waitForStart();

        // Run Auto if stop was not pressed.
        if (opModeIsActive()) {

        }
    }
}