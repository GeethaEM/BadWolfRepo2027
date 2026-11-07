package org.firstinspires.ftc.teamcode;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
public class MyTeamUtil {
    static public List<Double> rotate_coordinates(List<DcMotor> wheels, List<Double> target) {
        Pose robot_position = follower.pose();
        double x = (target.get(0) - robot_position.get(0)) * Math.cos(robot_position.get(2)) - (target.get(1) - robot_position.get(1)) * Math.sin(robot_position.get(2)) + robot_position.get(0);
        double y = (target.get(0) - robot_position.get(0)) * Math.sin(robot_position.get(2)) + (target.get(1) - robot_position.get(1)) * Math.cos(robot_position.get(2)) + robot_position.get(1);
        List<Double> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }


    static public void drive(double direction_x, double direction_y, double spin, List<DcMotor> wheels) {
        List<Double> powers = new ArrayList<>();
        powers.add(direction_y + direction_x + spin);
        powers.add(direction_y - direction_x + spin);
        powers.add(direction_y - direction_x - spin);
        powers.add(direction_y + direction_x - spin);
        double max = Math.max(Math.abs(powers.get(0)), Math.abs(powers.get(1)));
        max = Math.max(max, Math.abs(powers.get(2)));
        max = Math.max(max, Math.abs(powers.get(3)));
        if (max > 1.0) {
            for(int i = 0; i <= 3; i++) {
                powers.set(i, powers.get(i) / max);
            }
        }
        for (int i = 0; i <= 3; i++) {
            wheels.get(i).setPower(powers.get(i));
        }
    }
    static public void update_position(List <DcMotor> wheels, List<Double> robot) {
        List<Double> distance_list = new ArrayList<>();
        Pose robot_position = follower.pose();
        double diameter = 3.75;
        double gear_ratio = 13.0/7.0;
        for (int i = 0; i <= 3; i++) {
            wheels.get(i).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            wheels.get(i).setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            distance_list.add(wheels.get(i).getCurrentPosition() / wheels.get(i).getMotorType().getTicksPerRev() * gear_ratio * Math.PI * diameter);
        }
        double distance = (distance_list.get(0) + distance_list.get(1) + distance_list.get(2) + distance_list.get(3)) / 4;
        robot.set(0, robot.get(0) + Math.sin(Math.toRadians(robot_position.get(2))) * distance);
        robot.set(1, robot.get(1) + Math.cos(Math.toRadians(robot_position.get(2))) * distance);
    }

    static public void get_to_target (List<List<Double>> coordinate_list, List<DcMotor> wheels) {
        Pose robot_position = follower.pose();
        spin_to_face(coordinate_list.get(0), wheels);
        PedroPathing pathing = new PedroPathing();
        Point start = new Point(robot_position.get(0), robot_position.get(1));
        Point end = new Point(coordinate_list.get(0), coordinate_list.get(1));
        Bezierpath path = new BezierPath(start, end);
        pathing.addPath(path);
        pathing.follow(Paths);
        spin_to_face(coordinate_list.get(1), wheels);

    }
    static public void spin_to_face(List<Double> target, List<DcMotor> wheels) {
        double ready_target = rotate_coordinates(wheels, target).get(0);
        while (ready_target != 0) {
            if (ready_target > 0) {
                drive(0, 0, 1, wheels);
            } else if (ready_target < 0) {
                drive(0, 0, -1, wheels);
            }
            ready_target = rotate_coordinates(wheels, target).get(0);
        }
        drive(0, 0, 0, wheels);
    }
    static public void move_servos(List<Servo> flowers) {
        if (flowers.get(0).getPosition() == 1 && flowers.get(1).getPosition() == 1) {
            flowers.get(0).setPosition(0);
            flowers.get(1).setPosition(0);
        }
        else if (flowers.get(0).getPosition() == 1 && flowers.get(1).getPosition() == 0) {
            flowers.get(0).setPosition(1);
            flowers.get(1).setPosition(1);
        }
    }
}