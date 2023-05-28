package ru.miet.pattern.lab78.flappybird.model;

import ru.miet.pattern.lab78.flappybird.presenter.FlappyBird;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Bird implements BirdObserver {
    private double xCoord;

    private double yCoord;

    private double xSpeed;

    private double ySpeed;

    public static final int RAD = 25;

    private final Image img;

    private static Bird bird;

    private Bird() throws IOException {
        xCoord = FlappyBird.WIDTH / 2.0;
        yCoord = FlappyBird.HEIGHT / 2.0;
        img = ImageIO.read(new File("src/main/resources/sticker,375x360.u2.png"));
    }

    public static Bird getInstance() throws IOException {
        if (bird == null) {
            bird = new Bird();
        }
        return bird;
    }

    public void move() {
        xCoord += xSpeed;
        yCoord += ySpeed;
        ySpeed += 0.5f;
    }

    @Override
    public void update(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawImage(img, (int) Math.round(xCoord - RAD), (int) Math.round(yCoord - RAD), 2 * RAD, 2 * RAD, null);
    }

    public void jump() {
        ySpeed = -8;
    }

    public void reset() {
        xCoord = 640 / 2.0;
        yCoord = 640 / 2.0;
        xSpeed = ySpeed = 0.0;
    }

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }
}