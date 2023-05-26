package ru.miet.pattern.lab78.flappybird.view;

import ru.miet.pattern.lab78.flappybird.model.bird.Bird;
import ru.miet.pattern.lab78.flappybird.model.factory.PipeFactory;
import ru.miet.pattern.lab78.flappybird.utils.GraphicUtils;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import static ru.miet.pattern.lab78.flappybird.utils.GraphicUtils.*;

public class GamePanel extends JPanel {
    private final Bird bird;

    private final List<Rectangle> rectangles;

    private final PipeFactory pipeFactory;

    private boolean isPaused = true;

    private int time;

    public GamePanel(Bird bird, List<Rectangle> rectangles, PipeFactory pipeFactory) {
        this.bird = bird;
        this.rectangles = rectangles;
        this.pipeFactory = pipeFactory;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
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
        g.drawString(String.format("Score: %d", time), 10, 20);

        if (isPaused) {
            g.setFont(PAUSE_FONT);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString("PAUSED", GraphicUtils.WIDTH / 2 - 100, GraphicUtils.HEIGHT / 2 - 100);
            g.drawString("PRESS SPACE TO BEGIN", GraphicUtils.WIDTH / 2 - 300, GraphicUtils.HEIGHT / 2 + 50);
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

}
