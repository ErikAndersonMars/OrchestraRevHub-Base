package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;


public class Striker {

    DigitalChannel digitalChannel ;
    OpMode opMode ;


    public Striker(OpMode newOpMode, String configName) {
        opMode = newOpMode ;
        digitalChannel = opMode.hardwareMap.get(DigitalChannel.class, configName);
        digitalChannel.setMode(DigitalChannel.Mode.OUTPUT) ;
        digitalChannel.setState(false) ;
    }

    public void ding() {
        digitalChannel.setState(true) ;
        wait(0.01) ;
        digitalChannel.setState(false) ;
    }

    public void wait(double sec) {
        double stopTime = opMode.getRuntime() + sec ;
        while (opMode.getRuntime() < stopTime) ;
    }

    public void zero() {
        digitalChannel.setState(false) ;
    }

}
