package ru.miet.pattern.lab78.flappybird;

import ru.miet.pattern.lab78.flappybird.factory.PipeFactory;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

public class GamePanel extends JPanel {

    private final Bird bird;

    private final List<Rectangle> rectangles;

    private final FlappyBird flappyBird;

    private final static Font SCORE_FONT = new Font("Comic Sans MS", Font.BOLD, 18);

    private final static Font PAUSE_FONT = new Font("Arial", Font.BOLD, 48);

    public final static Color BG_COLOR = new Color(0, 158, 158);

    private final PipeFactory pipeFactory;

    public GamePanel(Bird bird, List<Rectangle> rectangles, FlappyBird flappyBird, PipeFactory pipeFactory) {
        this.bird = bird;
        this.rectangles = rectangles;
        this.flappyBird = flappyBird;
        this.pipeFactory = pipeFactory;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        bird.update(g);
        g.setColor(Color.RED);
        for (Rectangle rectangle : rectangles) {
            try {
                pipeFactory.createPipes(g, rectangle);
            } catch (IOException e) {
                throw new RuntimeException("нет такого ресурса");
            }
        }

        g.setFont(SCORE_FONT);
        g.setColor(Color.BLACK);
        g.drawString(String.format("Score: %d", flappyBird.getScore()), 10, 20);

        if (flappyBird.isPaused()) {
            g.setFont(PAUSE_FONT);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString("PAUSED", FlappyBird.WIDTH / 2 - 100, FlappyBird.HEIGHT / 2 - 100);
            g.drawString("PRESS SPACE TO BEGIN", FlappyBird.WIDTH / 2 - 300, FlappyBird.HEIGHT / 2 + 50);
        }
    }
}
