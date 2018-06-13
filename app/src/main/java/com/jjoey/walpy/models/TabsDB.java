package com.jjoey.walpy.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "saved_tabs")
public class TabsDB extends Model {

    @Column(name = "tab_name")
    public String tabName;

    public TabsDB() { }

    public TabsDB(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
