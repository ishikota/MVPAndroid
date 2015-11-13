package com.ikota.reactivesample.ui.signal;

import java.util.ArrayList;

/* TODO generic type */
public class Signal {

    public interface OnValueChangeListener {
        void onReceiveChange(int before_val, int new_val);
    }

    private int mVal;
    private ArrayList<OnValueChangeListener> callbacks = new ArrayList<>();

    public Signal(int val) {
        this.mVal = val;
    }

    public void register(OnValueChangeListener callback) {
        callbacks.add(callback);
    }

    public void unregister() {
        callbacks.clear();
    }

    public void update(int new_val) {
        for(OnValueChangeListener callback : callbacks) {
            callback.onReceiveChange(mVal, new_val);
        }
        mVal = new_val;
    }

    public void increment() {
        int before_val = this.mVal++;
        for(OnValueChangeListener callback : callbacks) {
            callback.onReceiveChange(before_val, this.mVal);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.mVal);
    }
}
