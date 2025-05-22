/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Gantry;
import org.firstinspires.ftc.teamcode.Striker;

import java.util.concurrent.TimeUnit;


@TeleOp(name="Development OpMode", group="Development")
//@Disabled
public class DevOpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private double[][] sequence = {
            //  time(s) ,   position(index)
            {3.00,         8},
            {3.273,        8},
            {3.545,        8},
            {3.818,        5},
            {4.091,        5},
            {4.364,        4},
            {4.636,        4},
            {4.909,        1},
            {5.182,        1},
            {5.455,        1},
            {5.727,        1},
            {6.000,        8},
            {6.273,        8},
            {6.545,        8},
            {11.182,        8},
            {11.455,        5},
            {11.727,        5},
            {12.000,        4},
            {12.273,        4},
            {12.545,        1},
            {12.818,        1},
            {13.091,        1},
            {13.364,        1},
            {13.636,        1},
            {13.909,        1},
            {14.182,        1}
    } ;

/* Note     index   Ticks    This is for testing
   high A   8       160
        G   7       140
        F   6       120
        E   5       100
        D   4       80
        C   3       60
        B   2       40
    low A   1       20
*/

    private int[] ticks = {
            0,
            20,
            40,
            60,
            80,
            100,
            120,
            140,
            160
    } ;


    @Override
    public void runOpMode() {
        Gantry gantry1 = new Gantry(this, "gantry1") ;
        Gantry gantry2 = new Gantry(this, "gantry2") ;
        Striker striker1 = new Striker(this, "striker1") ;
        Striker striker2 = new Striker(this, "striker2") ;
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses START) =========================================
        waitForStart();
        runtime.reset();

        int sequenceCounter = 0 ;

        // run until the end of the match (driver presses STOP) =======================================
        while (opModeIsActive()) {
            // Move gantry into position
            gantry1.goToPosition(ticks[(int)sequence[sequenceCounter][1]]) ;
            // Wait for the assigned time
            while ((runtime.time(TimeUnit.SECONDS) < sequence[sequenceCounter][0])&&opModeIsActive()) ;
            striker1.ding();

            sequenceCounter +=1 ;
            if (sequenceCounter >= sequence.length) {
                sequenceCounter = 0 ;
                runtime.reset();
            }



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
