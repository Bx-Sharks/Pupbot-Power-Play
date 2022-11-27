package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Auto")
public class Auto extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "right");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        driveTicks(300, 0.1);
        turnTicks(-50, 0.1);
    }

    public void driveTicks(double driveTicks, double speed) {
        int targetTicks;

        if (opModeIsActive()) {
            targetTicks = (int) leftMotor.getCurrentPosition() + (int) driveTicks;

            leftMotor.setTargetPosition(targetTicks);
            rightMotor.setTargetPosition(targetTicks);

            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

            while (leftMotor.isBusy() || rightMotor.isBusy()) {
                telemetry.addData("Encoder Left", leftMotor.getCurrentPosition());
                telemetry.update();
            }

            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }

    public void turnTicks(double ticks, double speed) {

        if (opModeIsActive()) {
            int targetLeft = (int) leftMotor.getCurrentPosition() + (int) ticks;
            int targetRight = (int) rightMotor.getCurrentPosition() - (int) ticks;

            leftMotor.setTargetPosition(targetLeft);
            rightMotor.setTargetPosition(targetRight);

            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

            while (leftMotor.isBusy() || rightMotor.isBusy()) {
                telemetry.addData("Encoder Left", leftMotor.getCurrentPosition());
                telemetry.update();
            }

            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }
}
