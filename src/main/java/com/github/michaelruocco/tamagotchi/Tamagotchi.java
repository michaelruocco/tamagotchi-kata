package com.github.michaelruocco.tamagotchi;

import java.util.Timer;
import java.util.TimerTask;

public class Tamagotchi {

    private static final int START_VALUE = 50;
    private static final int CHANGE_VALUE = 10;
    private static final String NEW_LINE = System.lineSeparator();

    private int fullness = START_VALUE;
    private int happiness = START_VALUE;
    private int tiredness = START_VALUE;
    private int hungriness = START_VALUE;

    private Timer timer;

    public void start(int delay) {
        timer = new Timer("TamagotchiTimer", true);
        timer.schedule(new TamagotchiTask(this), delay, delay);
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }

    public String getState() {
        return "fullness: " + fullness + NEW_LINE +
                "happiness: " + happiness + NEW_LINE +
                "tiredness: " + tiredness + NEW_LINE +
                "hungriness: " + hungriness;
    }

    public int getHungriness() {
        return hungriness;
    }

    public int getFullness() {
        return fullness;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getTiredness() {
        return tiredness;
    }

    public void changeNeeds() {
        tiredness = increase(tiredness);
        hungriness = increase(hungriness);
        happiness = decrease(happiness);
    }

    public void feed() {
        hungriness = decrease(hungriness);
        fullness = increase(fullness);
    }

    public void play() {
        happiness = increase(happiness);
        tiredness = increase(tiredness);
    }

    public void sleep() {
        tiredness = decrease(tiredness);
    }

    public void poop() {
        fullness = decrease(fullness);
    }

    private int decrease(int value) {
        return value - CHANGE_VALUE;
    }

    private int increase(int value) {
        return value + CHANGE_VALUE;
    }

    private static class TamagotchiTask extends TimerTask {

        private final Tamagotchi tamagotchi;

        public TamagotchiTask(Tamagotchi tamagotchi) {
            this.tamagotchi = tamagotchi;
        }

        @Override
        public void run() {
            tamagotchi.changeNeeds();
        }

    }

}
