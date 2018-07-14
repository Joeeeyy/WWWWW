package com.jjoey.walpy.models;

import android.widget.Button;

public class CustomizeFooter {

    private Button button;
    private int numChecked;

    public CustomizeFooter() {
    }

    public CustomizeFooter(Button button, int numChecked) {
        this.button = button;
        this.numChecked = numChecked;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getNumChecked() {
        return numChecked;
    }

    public void setNumChecked(int numChecked) {
        this.numChecked = numChecked;
    }
}
