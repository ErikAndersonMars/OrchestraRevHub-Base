package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Gantry {
    OpMode opMode ;
    public DcMotorEx gantryMotor ;

    public Gantry(OpMode newOpMode, String configName) {
        opMode = newOpMode ;
        gantryMotor = opMode.hardwareMap.get(DcMotorEx.class, configName) ;
        gantryMotor.setPositionPIDFCoefficients(9);
        gantryMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        gantryMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        gantryMotor.setTargetPosition(0);
        gantryMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        gantryMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        gantryMotor.setPower(0);
    }

    public void goToPosition(int newPosition) {
        gantryMotor.setTargetPosition(newPosition);
        // Switch to Run to Position mode
        //gantryMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        gantryMotor.setPower(1);
        // Wait for it to get there
        while (abs(gantryMotor.getCurrentPosition() - gantryMotor.getTargetPosition()) > 2) ;
        // Switch back to no power to keep it from overheating
        gantryMotor.setPower(0);
        //gantryMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER) ;
    }

    public int getPosition() {
        return gantryMotor.getCurrentPosition() ;
    }

}
