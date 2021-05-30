package com.company;

import java.io.IOException;

public class Main {

    private static final String WindowTitle = "FlyingPlane";

    public static  void main(String args[]) throws IOException {
        new Sky(WindowTitle).setVisible(true);
    }
}
