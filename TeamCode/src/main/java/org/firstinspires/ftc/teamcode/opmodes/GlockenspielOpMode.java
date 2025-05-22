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

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Gantry;
import org.firstinspires.ftc.teamcode.Striker;

import java.util.concurrent.TimeUnit;

@Config
@TeleOp(name="Glockenspiel OpMode", group="Production")
//@Disabled
public class GlockenspielOpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private double[][] sequence = {
            //  time(s) ,  index1   index2
            {3.00,         8,       1},
            {3.273,        0,       1},
            {3.545,        0,       1},
            {3.818,        5,       2},
            {4.091,        0,       2},
            {4.364,        4,       3},
            {4.636,        0,       3},
            {4.909,        1,       4},
            {5.182,        0,       4},
            {5.455,        0,       4},
            {5.727,        0,       4},
            {6.000,        8,       5},
            {6.273,        8,       5},
            {6.545,        8,       5},
            {11.182,       8,       5},
            {11.455,       5,       6},
            {11.727,       5,       6},
            {12.000,       4,       6},
            {12.273,       4,       6},
            {12.545,       1,       7},
            {12.818,       1,       7},
            {13.091,       5,       8},
            {13.364,       5,       8},
            {13.636,       5,       8},
            {13.909,       5,       8},
            {14.182,       5,       8}
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

    // These numbers need to be calibrated using Gantry Only Opmode
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

    public static double SPEED_FACTOR = 1 ;


    @Override
    public void runOpMode() {
        Gantry gantry1 = new Gantry(this, "gantry1") ;
        Gantry gantry2 = new Gantry(this, "gantry2") ;
        Striker striker1 = new Striker(this, "striker1") ;
        Striker striker2 = new Striker(this, "striker2") ;
        telemetry.addData("Status", "Initialized") ;
        telemetry.update() ;

        // Wait for the game to start (driver presses START) =========================================
        waitForStart();
        runtime.reset();

        int sequenceCounter = 0 ;
        // Move gantry into position
        gantry1.goToPosition(ticks[(int)sequence[sequenceCounter][1]]) ;
        gantry2.goToPosition(ticks[(int)sequence[sequenceCounter][2]]) ;

        // run until the end of the match (driver presses STOP) =======================================
        while (opModeIsActive()) {
            // Wait for the assigned time
            if (runtime.time(TimeUnit.SECONDS) >= sequence[sequenceCounter][0]/SPEED_FACTOR) {
                if (sequence[sequenceCounter][1] != 0) {
                    striker1.ding();
                }
                if (sequence[sequenceCounter][2] != 0) {
                    striker2.ding();
                }
                sequenceCounter +=1 ;
                if (sequenceCounter >= sequence.length) {
                    sequenceCounter = 0 ;
                    runtime.reset();
                }
                // Move gantry into position
                    gantry1.goToPosition(ticks[(int) sequence[sequenceCounter][1]]);
                    gantry2.goToPosition(ticks[(int) sequence[sequenceCounter][2]]);
            }




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
