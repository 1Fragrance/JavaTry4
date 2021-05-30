package com.company;

import java.awt.*;

public class Cloud {

    private int x;
    private int y;
    private Image image;

    public Cloud (int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void AddToX(int value) {
        x += value;
    }

    public void ResetX() {
        x = -50;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}
