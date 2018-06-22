package com.jjoey.walpy.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "customise_tabs")
public class CustomizeItems extends Model{

    public boolean isChecked;
    public int icon;

    @Column(name = "tabId")
    public String tabId;

    @Column(name = "tabName")
    public String tabName;

    @Column(name = "tabsCount")
    public int tabsCount;

    public CustomizeItems() {  }

    public CustomizeItems(boolean isChecked, int icon, String tabId, String tabName, int tabsCount) {
        this.isChecked = isChecked;
        this.icon = icon;
        this.tabId = tabId;
        this.tabName = tabName;
        this.tabsCount = tabsCount;
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

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabsCount() {
        return tabsCount;
    }

    public void setTabsCount(int tabsCount) {
        this.tabsCount = tabsCount;
    }
}
