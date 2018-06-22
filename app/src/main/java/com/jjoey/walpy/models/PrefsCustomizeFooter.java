package com.jjoey.walpy.models;

import android.widget.Button;

public class PrefsCustomizeFooter {

    public Button doneBtn;

    public PrefsCustomizeFooter() {
    }

    public PrefsCustomizeFooter(Button doneBtn) {
        this.doneBtn = doneBtn;
    }

    public Button getDoneBtn() {
        return doneBtn;
    }

    public void setDoneBtn(Button doneBtn) {
        this.doneBtn = doneBtn;
    }
}
