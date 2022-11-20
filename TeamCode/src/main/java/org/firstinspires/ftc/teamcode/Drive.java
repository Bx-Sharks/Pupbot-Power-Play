package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Drive Op mode")
public class Drive extends LinearOpMode {

    private DcMotor right;
    private DcMotor left;
    private Servo right_hand;
    private Servo left_hand;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        right_hand = hardwareMap.get(Servo.class, "right_hand");
        left_hand = hardwareMap.get(Servo.class, "left_hand");

        // Reverse one of the drive motors.
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_hand.setDirection(Servo.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.
                left.setPower(-(0.7 * gamepad1.right_stick_y));
                right.setPower(-(0.7 * gamepad1.left_stick_y));
                if (gamepad2.a) {
                    left_hand.setPosition(0.7);
                    right_hand.setPosition(0);
                }
                if (gamepad2.b) {
                    left_hand.setPosition(0.9);
                    right_hand.setPosition(0.2);
                }
                if (gamepad1.left_bumper) {
                    // The Y axis of a joystick ranges from -1 in its topmost position
                    // to +1 in its bottommost position. We negate this value so that
                    // the topmost position corresponds to maximum forward power.
                    right.setPower(-(1 * gamepad1.left_stick_y));
                    left.setPower(-(1 * gamepad1.right_stick_y));
                }
                if (gamepad1.right_bumper) {
                    // The Y axis of a joystick ranges from -1 in its topmost position
                    // to +1 in its bottommost position. We negate this value so that
                    // the topmost position corresponds to maximum forward power.
                    right.setPower(-(0.4 * gamepad1.left_stick_y));
                    left.setPower(-(0.4 * gamepad1.right_stick_y));
                }
                telemetry.addData("Left Pow", left.getPower());
                telemetry.addData("Right Pow", right.getPower());
                telemetry.update();
            }
        }
    }
}