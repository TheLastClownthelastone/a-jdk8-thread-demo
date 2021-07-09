package com.pt.jdk8.practice;

/**
 * @author nate-pt
 * @date 2021/7/9 14:16
 * @Since 1.8
 * @Description
 */
public class Arter {

    public Arter(String name, String gj) {
        this.name = name;
        this.gj = gj;
    }

    private String name;

    private String gj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }
}
