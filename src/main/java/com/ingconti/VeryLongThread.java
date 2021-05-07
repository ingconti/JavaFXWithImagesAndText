package com.ingconti;


import java.util.List;


public class VeryLongThread extends Thread {

    List<String> players;

    public VeryLongThread(List<String> players) {
        this.players = players;
    }

    public void run() {
        System.out.println("VeryLongThread is running...");

        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            int tot = players.size();
            System.out.println("players logged til now " + tot);
        } while (true);
    }
}

