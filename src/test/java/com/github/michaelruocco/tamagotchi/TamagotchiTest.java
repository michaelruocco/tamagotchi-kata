package com.github.michaelruocco.tamagotchi;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TamagotchiTest {

    private static final String NEW_LINE = System.lineSeparator();

    private Tamagotchi tamagotchi = new Tamagotchi();

    @Test
    public void shouldFeed() {
        int hungriness = tamagotchi.getHungriness();
        int fullness = tamagotchi.getFullness();

        tamagotchi.feed();

        assertThat(tamagotchi.getHungriness()).isLessThan(hungriness);
        assertThat(tamagotchi.getFullness()).isGreaterThan(fullness);
    }

    @Test
    public void shouldPlay() {
        int happiness = tamagotchi.getHappiness();
        int tiredness = tamagotchi.getTiredness();

        tamagotchi.play();

        assertThat(tamagotchi.getHappiness()).isGreaterThan(happiness);
        assertThat(tamagotchi.getTiredness()).isGreaterThan(tiredness);
    }

    @Test
    public void shouldSleep() {
        int tiredness = tamagotchi.getTiredness();

        tamagotchi.sleep();

        assertThat(tamagotchi.getTiredness()).isLessThan(tiredness);
    }

    @Test
    public void shouldPoop() {
        int fullness = tamagotchi.getFullness();

        tamagotchi.poop();

        assertThat(tamagotchi.getFullness()).isLessThan(fullness);
    }

    @Test
    public void needsShouldChange() {
        int hungriness = tamagotchi.getHungriness();
        int happiness = tamagotchi.getHappiness();
        int tiredness = tamagotchi.getTiredness();

        tamagotchi.changeNeeds();

        assertThat(tamagotchi.getTiredness()).isGreaterThan(tiredness);
        assertThat(tamagotchi.getHungriness()).isGreaterThan(hungriness);
        assertThat(tamagotchi.getHappiness()).isLessThan(happiness);
    }

    @Test
    public void shouldReturnState() {
        String expected = "fullness: 50" + NEW_LINE +
                "happiness: 50" + NEW_LINE +
                "tiredness: 50" + NEW_LINE +
                "hungriness: 50";

        assertThat(tamagotchi.getState()).isEqualTo(expected);

        tamagotchi.changeNeeds();

        expected = "fullness: 50" + NEW_LINE +
                "happiness: 40" + NEW_LINE +
                "tiredness: 60" + NEW_LINE +
                "hungriness: 60";

        assertThat(tamagotchi.getState()).isEqualTo(expected);
    }

    @Test
    public void needsShouldChangeAfterElapsedTime() {
        int hungriness = tamagotchi.getHungriness();
        int happiness = tamagotchi.getHappiness();
        int tiredness = tamagotchi.getTiredness();

        tamagotchi.start(1000);

        assertThat(tamagotchi.getTiredness()).isEqualTo(tiredness);
        assertThat(tamagotchi.getHungriness()).isEqualTo(hungriness);
        assertThat(tamagotchi.getHappiness()).isEqualTo(happiness);

        wait(2000);

        assertThat(tamagotchi.getTiredness()).isGreaterThan(tiredness);
        assertThat(tamagotchi.getHungriness()).isGreaterThan(hungriness);
        assertThat(tamagotchi.getHappiness()).isLessThan(happiness);

        tamagotchi.stop();
    }

    private void wait(int seconds) {
        try {
            Thread.sleep(secondsToMilliseconds(seconds));
        } catch (InterruptedException e) {
            throw new WaitException(e);
        }
    }

    private int secondsToMilliseconds(int seconds) {
        return seconds + 1000;
    }

    private static class WaitException extends RuntimeException {

        public WaitException(Throwable cause) {
            super(cause);
        }

    }

}
