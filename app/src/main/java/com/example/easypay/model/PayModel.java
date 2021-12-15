package com.example.easypay.model;

import java.io.Serializable;

public class PayModel implements Serializable {
    private String description;
    private String payOption;

    public String getPayOption() {
        return payOption;
    }

    public void setPayOption(String payOption) {
        this.payOption = payOption;
    }

    private float payValue;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPayValue() {
        return payValue;
    }

    public void setPayValue(float payValue) {
        this.payValue = payValue;
    }

    public PayModel() {

    }

}
