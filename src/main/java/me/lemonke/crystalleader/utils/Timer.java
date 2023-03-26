package me.lemonke.crystalleader.utils;

public class Timer {
    private long timeStamp = System.currentTimeMillis();

    public void reset() {
        timeStamp = System.currentTimeMillis();
    }

    public long passedMs() {
        return System.currentTimeMillis() - timeStamp;
    }

    public boolean isPassedMs(long ms) {
        return System.currentTimeMillis() - timeStamp > ms;
    }

}
