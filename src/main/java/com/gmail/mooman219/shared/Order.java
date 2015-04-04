package com.gmail.mooman219.shared;

public enum Order {
    INITIAL(0, true),
    EARLIEST_IGNORE_CANCELLED(1, true),
    EARLIEST(2, false),
    EARLY_IGNORE_CANCELLED(3, true),
    EARLY(4, false),
    DEFAULT_IGNORE_CANCELLED(5, true),
    DEFAULT(6, false),
    LATE_IGNORE_CANCELLED(7, true),
    LATE(8, false),
    LATEST_IGNORE_CANCELLED(9, true),
    LATEST(10, false),
    MONITOR(11, true);

    public final int index;
    public final boolean ignoreCancelled;

    Order(int i, boolean a){
        index = i;
        ignoreCancelled = a;
    }

    public int getIndex() {
        return index;
    }

    public boolean ignoresCancelled() {
        return ignoreCancelled;
    }

    public String toString(){
        return index + "";
    }
}