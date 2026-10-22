package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.MyTeamUtil.drive;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.get_to_target;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.rotate_coordinates;
import static org.firstinspires.ftc.teamcode.MyTeamUtil.spin_to_face;

@Autonomous(name = "Autonomous Geetha", group = "Robot")
public class AutonomousGeetha extends LinearOpMode {

    @Override
    public void runOpMode() {


        // Wait for driver to press start
        telemetry.addData(">", "Touch Play to run Auto");
        telemetry.update();

        waitForStart();

        // Run Auto if stop was not pressed.
        if (opModeIsActive())
        {


        }
    }
}