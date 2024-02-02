package com.mnsfhxy.johnny_s_biological_notes.tool;

/**
 * 冷却监控
 */
public class CooldownMonitor {
    private int interval;
    private int counter;

    public CooldownMonitor(int interval) {
        this.interval = interval;
        this.counter = 0;
    }

    public boolean checkIsActive() {
        update();
        return this.counter <= 0;
    }

    private void update() {
        this.counter--;
    }

    public void reset() {
        this.counter = interval / 2;
    }
}
