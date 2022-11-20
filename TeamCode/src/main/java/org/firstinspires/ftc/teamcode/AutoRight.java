package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "AutoRight")
public class AutoRight extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    private ColorSensor ColorRangeSensor;
    private Servo left_hand;
    private Servo right_hand;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        int Colorseen;

        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        ColorRangeSensor = hardwareMap.get(ColorSensor.class, "ColorRangeSensor");
        left_hand = hardwareMap.get(Servo.class, "left_hand");
        right_hand = hardwareMap.get(Servo.class, "right_hand");

        // Reverse one of the drive motors.
        waitForStart();
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setTargetPosition(0);
        right.setTargetPosition(0);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (opModeIsActive()) {
            // Put run blocks here.
            left.setTargetPosition(290);
            right.setTargetPosition(290);
            while (left.isBusy()) {
                telemetry.update();
                left.setPower(0.1);
                right.setPower(0.1);
            }
            left.setPower(0);
            right.setPower(0);
            while (opModeIsActive()) {
                // Put loop blocks here.
                Colorseen = Color.argb(ColorRangeSensor.alpha(), ColorRangeSensor.red(), ColorRangeSensor.green(), ColorRangeSensor.blue());
                telemetry.addData("Hue", JavaUtil.colorToHue(Colorseen));
                if (190 >= JavaUtil.colorToHue(Colorseen) && JavaUtil.colorToHue(Colorseen) >= 120) {
                    dropoffcone();
                    telemetry.addData("Color is", "Blue");
                    telemetry.update();
                    left.setPower(0.6);
                    right.setPower(-0.6);
                    sleep(625);
                    left.setPower(0.3);
                    right.setPower(0.3);
                    sleep(400);
                    left.setPower(0);
                    right.setPower(0);
                    sleep(30000);
                } else if (0 <= JavaUtil.colorToHue(Colorseen) && JavaUtil.colorToHue(Colorseen) <= 70 || 270 <= JavaUtil.colorToHue(Colorseen) && JavaUtil.colorToHue(Colorseen) <= 360) {
                    telemetry.addData("Color is", "Red");
                    telemetry.update();
                    dropoffcone();
                    left.setPower(-0.6);
                    right.setPower(0.6);
                    sleep(500);
                    left.setPower(0);
                    right.setPower(0);
                    sleep(100);
                    left.setPower(-0.4);
                    right.setPower(-0.4);
                    sleep(1850);
                    left.setPower(0);
                    right.setPower(0);
                    sleep(30000);
                    sleep(30000);
                } else {
                    dropoffcone();
                    telemetry.addData("Color is", "Green");
                    telemetry.update();
                    left.setPower(-0.6);
                    right.setPower(0.6);
                    sleep(180);
                    left.setPower(0);
                    right.setPower(0);
                    sleep(100);
                    left.setPower(0.4);
                    right.setPower(0.4);
                    sleep(1800);
                    left.setPower(0);
                    right.setPower(0);
                    sleep(30000);
                }
            }
        }
    }

    /**
     * Describe this function...
     */
    private void dropoffcone() {
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setPower(0.25);
        right.setPower(0.25);
        sleep(3200);
        left.setPower(0);
        right.setPower(0);
        sleep(100);
        left.setPower(-0.4);
        right.setPower(-0.4);
        sleep(1300);
        left.setPower(0);
        right.setPower(0);
        sleep(100);
        left.setPower(-0.6);
        right.setPower(0.6);
        sleep(495);
        left.setPower(0);
        right.setPower(0);
        sleep(100);
        left.setPower(-0.4);
        right.setPower(-0.4);
        sleep(695);
        left.setPower(0);
        right.setPower(0);
        left_hand.setPosition(1);
        right_hand.setPosition(0.8);
        sleep(1000);
        left.setPower(0.5);
        right.setPower(0.5);
        sleep(500);
        left.setPower(0);
        right.setPower(0);
        sleep(100);
    }
}