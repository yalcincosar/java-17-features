package com.yalcincosar.java17.features.JEP406;

public class Wolf extends Animal{
    private boolean belongToTheHerd = false;
    public Wolf(int age) {
        super(age);
    }

    public Wolf(int age, boolean belongToTheHerd) {
        super(age);
        this.belongToTheHerd = belongToTheHerd;

    }

    public boolean doesItBelongToTheHerd() {
        return belongToTheHerd;
    }

    public void setBelongToTheHerd(boolean belongToTheHerd) {
        this.belongToTheHerd = belongToTheHerd;
    }

    String joinTheHerd() {
        return "wolf joined the herd.";
    }
}
