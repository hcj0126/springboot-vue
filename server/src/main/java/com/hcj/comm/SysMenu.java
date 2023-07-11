package com.hcj.comm;

import java.util.List;

/**
 * SysMenu
 *
 * @author hcj
 * @date 2023-06-16
 */
public class SysMenu {
    private String title;
    private String icon;
    private String href;
    private String target;
    private List<SysMenu> child;

    public SysMenu() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<SysMenu> getChild() {
        return this.child;
    }

    public void setChild(List<SysMenu> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "SysMenu [title=" + this.title + ", icon=" + this.icon + ", href=" + this.href + ", target=" + this.target + ", child=" + this.child + "]";
    }
}
