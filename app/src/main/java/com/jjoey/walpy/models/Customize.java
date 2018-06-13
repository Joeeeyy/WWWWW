package com.jjoey.walpy.models;

public class Customize {

    public boolean isChecked;
    public int icon;

    public Customize() {  }

    public Customize(boolean isChecked, int icon) {
        this.isChecked = isChecked;
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
