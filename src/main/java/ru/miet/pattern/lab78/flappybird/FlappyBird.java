package ru.miet.pattern.lab78.flappybird;


import ru.miet.pattern.lab78.flappybird.factory.AbstractFactory;
import ru.miet.pattern.lab78.flappybird.factory.PipeFactory;
import ru.miet.pattern.lab78.flappybird.factory.RectangleFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlappyBird implements ActionListener, KeyListener {

    public final static int WIDTH = 640;

    public final static int HEIGHT = 480;

    public final static int FPS = 60;

    private final Bird bird;

    private final JFrame frame = new JFrame("java flappy bird");

    private final JPanel panel;

    private final List<Rectangle> rectangles = new ArrayList<>();

    private final Timer timer;

    private boolean paused;

    private int time;

    private int scroll;

    private static FlappyBird flappyBird;

    private final AbstractFactory rectangleFactory;

    private FlappyBird() throws IOException {
        bird = Bird.getInstance();
        panel = new GamePanel(bird, rectangles, this, new PipeFactory());
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        paused = true;
        timer = new Timer(1000 / FPS, this);
        time = 0;
        scroll = 0;
        rectangleFactory = new RectangleFactory();
    }

    public void start() {
        timer.start();
    }

    public static FlappyBird getInstance() throws IOException {
        if (flappyBird == null) {
            flappyBird = new FlappyBird();
        }
        return flappyBird;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        panel.repaint();
        if (!paused) {
            bird.move();
            if (scroll % 90 == 0) {
                Rectangle topRectangle = rectangleFactory.createRectangle(true);
                Rectangle bottomRectangle = rectangleFactory.createRectangle(false);
                rectangles.addAll(List.of(topRectangle, bottomRectangle));
            }
            List<Rectangle> toRemoveList = new ArrayList<>();
            boolean gameFlag = true;
            for (Rectangle rectangle : rectangles) {
                rectangle.x -= 3;
                if (rectangle.x + rectangle.width <= 0) {
                    toRemoveList.add(rectangle);
                }
                if (rectangle.contains(bird.getxCoord(), bird.getyCoord())) {
                    gameFlag = false;
                }
            }
            rectangles.removeAll(toRemoveList);
            time++;
            scroll++;

            if (bird.getyCoord() > HEIGHT || bird.getyCoord() + Bird.RAD < 0) {
                gameFlag = false;
            }

            if (!gameFlag) {
                rectangles.clear();
                bird.reset();
                JOptionPane.showMessageDialog(frame, String.format("YOU LOSE!%nYOUR SCORE: %d.", time));
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (Objects.equals(keyEvent.getKeyCode(), KeyEvent.VK_UP)) {
            bird.jump();
        } else if (Objects.equals(keyEvent.getKeyCode(), KeyEvent.VK_SPACE)) {
            paused = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public boolean isPaused() {
        return paused;
    }

    public int getScore() {
        return time;
    }
}
