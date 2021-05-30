package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sky extends JFrame {

    private int plane_AxisX = 0;
    private int plane_AxisY = 200;
    private final int cloudSpeed = 2;
    private int planeSpeed = 5;
    private final int cloudsCount = 6;
    private final int windowWidth = 1060;
    private final int windowHeight = 500;

    private static BufferedImage plane;
    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();

    private void configureComponents() throws IOException {
        plane = ImageIO.read(new File("./plane.png"));
        getContentPane().add(new ObjectPool());
    }

    private void configureWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setContentPane(new SkyBackground());
    }

    public Sky(String title) throws IOException {
        super(title);
        configureWindow();
        configureComponents();

        var plainThread = new Thread(new PlainThread());
        plainThread.start();
    }

    private BufferedImage flip(BufferedImage image){
        BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = image.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < image.getHeight();yy++){
                img.setRGB(image.getWidth()-xx, yy, image.getRGB(xx, yy));
            }
        }
        return img;
    }

    private class PlainThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                plane_AxisX += planeSpeed;
                if(plane_AxisX > windowWidth - 200 || plane_AxisX < 0) {
                    planeSpeed *= -1;
                    plane = flip(plane);
                }


                for(var i = 0; i < cloudsCount; i++) {
                    var cloud = clouds.get(i);
                    cloud.AddToX(cloudSpeed);
                    if(cloud.getX() > windowWidth) {
                        cloud.ResetX();
                    }
                }
                repaint();
                try {
                    Thread.sleep(30);
                } catch (Exception exc) {
                }
            }
        }
    }


    private class ObjectPool extends JPanel {

        public ObjectPool() {
            setOpaque(false);
            setPreferredSize(new Dimension(1000, 600));
            try {
                plane = ImageIO.read(new File("./plane.png"));

                for(var i = 0; i < cloudsCount; i++) {
                    var cloud = new Cloud(-100 + i * 180, 0, ImageIO.read(new File("./cloud.png")));
                    clouds.add(cloud);
                }

            } catch (IOException exc) {

            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.drawImage(plane, plane_AxisX, plane_AxisY, 120, 120, this);

            for(var i = 0; i < cloudsCount; i++) {
                var cloud = clouds.get(i);
                graphics2D.drawImage(cloud.getImage(), cloud.getX(), cloud.getY(), 150, 150, this);
            }
        }
    }
}
