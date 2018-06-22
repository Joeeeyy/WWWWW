package com.jjoey.walpy.models;

import android.widget.Button;

public class CustomizeFooter {

    private Button button;

    public CustomizeFooter() {
    }

    public CustomizeFooter(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
