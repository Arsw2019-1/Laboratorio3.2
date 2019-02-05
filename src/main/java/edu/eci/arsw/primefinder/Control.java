/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class Control extends Thread {

    int reloj;
    Timer timer;
    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;

    private PrimeFinderThread pft[];

    private Control() {
        super();
        this.pft = new PrimeFinderThread[NTHREADS];

        int i;
        for (i = 0; i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i * NDATA, (i + 1) * NDATA);
            pft[i] = elem;
        }
        
        pft[i] = new PrimeFinderThread(i * NDATA, MAXVALUE + 1);
        //time();
    }

    public static Control newControl() {
        return new Control();
    }

    public void time() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reloj += 1000;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }

    @Override
    public void run() {


        for (int i = 0; i < NTHREADS; i++) {

            pft[i].start();

        }
    }

}
