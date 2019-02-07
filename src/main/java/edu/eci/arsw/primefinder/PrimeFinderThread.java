package edu.eci.arsw.primefinder;

import com.sun.media.sound.SoftAbstractResampler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeFinderThread extends Thread {

    long tiempo;
    int a, b;
    int reloj = 0;
    Timer timer;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private List<Integer> primes;
    Scanner scanner = new Scanner(System.in);
    String entrada = "";

    public PrimeFinderThread(int a, int b) {
        super();
        this.primes = new LinkedList<>();
        this.a = a;
        this.b = b;
    }

    public void time() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reloj += 1000;
                System.out.println("que dice " + reloj);
                if (reloj > 8000) {
                    reloj = 0;

                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public synchronized void getListPrimes() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine() != null) {
            System.out.println("Presione enter para continuar");
            notify();

        }
        tiempo = System.currentTimeMillis() - Control.startTime;
        while (tiempo < Control.TMILISECONDS) {
            System.out.println("Puat");
          wait();
        }
        /**
        if (reloj <= Control.TMILISECONDS) {
            System.out.println("Llegamos ");
            wait();
        } else {
            System.out.println("Bjamos");
            reloj = 0;
        }*/

        System.out.println("lOs numeros primos que lleva recoletados son : " + this.getPrimes() + "El hilo :" + this);
        //Thread.sleep(1000);
        Control.startTime = System.currentTimeMillis();

    }

    @Override
    public void run() {
        //time();
        for (int i = a; i < b; i++) {
            System.out.println("Hola loco " + Control.startTime);
            tiempo = (System.currentTimeMillis()) - Control.startTime;
            System.out.println("tiempo...." + tiempo);
            if (true) {
                synchronized (this) {
                    System.out.println("Hola reloj " + tiempo);
                    //System.out.println("tiempo local"+Control.TMILISECONDS);
                    while (tiempo >= Control.TMILISECONDS) {
                    //while (reloj >= Control.TMILISECONDS) {
                        //reloj = 0;
                        primes.add(i);
                        System.out.println(i);
                        System.out.println("Esperando");
                        try {
                            //primes.add(i);
                            //System.out.println(i);
                            tiempo = (System.currentTimeMillis()) - Control.startTime;
                            this.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PrimeFinderThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                   
                    if (isPrime(i)) {
                        primes.add(i);
                        System.out.println(i);
                    }
                    //Thread.sleep(1000);
                    //time();
                    notify();
                }

            }
            //if (reloj > Control.TMILISECONDS) {
            //  reloj = 0;

            //}
        }

    }

    boolean isPrime(int n) {
        boolean ans;
        if (n > 2) {
            ans = n % 2 != 0;
            for (int i = 3; ans && i * i <= n; i += 2) {
                ans = n % i != 0;
            }
        } else {
            ans = n == 2;
        }
        return ans;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

}
