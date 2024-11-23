package org.example.beer.model;

public class Chars {
    private float revolutions;
    private byte transparency;
    private String filtered;
    private byte nutritionalValue;
    private BottlingMethod bottlingMethod;

    public Chars() {
        this.revolutions = revolutions;
        this.transparency = transparency;
        this.filtered = filtered;
        this.nutritionalValue = nutritionalValue;
        this.bottlingMethod = bottlingMethod;
    }

    public float getRevolutions() {
        return revolutions;
    }

    public void setRevolutions(float revolutions) {
        this.revolutions = revolutions;
    }

    public byte getTransparency() {
        return transparency;
    }

    public void setTransparency(byte transparency) {
        this.transparency = transparency;
    }

    public String getFiltered() {
        return filtered;
    }

    public void setFiltered(String filtered) {
        this.filtered = filtered;
    }

    public byte getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(byte nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public BottlingMethod getBottlingMethod() {
        return bottlingMethod;
    }

    public void setBottlingMethod(BottlingMethod bottlingMethod) {
        this.bottlingMethod = bottlingMethod;
    }
}
