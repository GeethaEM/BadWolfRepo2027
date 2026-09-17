package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name="Strafer Drive")
public class StraferDriveOpMode extends OpMode {

    final double strafeAdjust = 1.0;  //change this parameter to counteract imperfect strafing
    Drive motors= new Drive(strafeAdjust);

    @Override
    public void init(){
        motors.init(hardwareMap);
    }

    @Override
    public void loop(){
        double y= -gamepad1.left_stick_y;
        double x= gamepad1.left_stick_x;
        double rx= gamepad1.right_stick_x;

        motors.move(x,y,rx);

    }


}