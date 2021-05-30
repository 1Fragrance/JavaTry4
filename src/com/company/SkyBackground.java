package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SkyBackground extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Image image = null;
        try {
            image = ImageIO.read(new File("./skyBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawImage(image, 0, 0, null);
    }
}
