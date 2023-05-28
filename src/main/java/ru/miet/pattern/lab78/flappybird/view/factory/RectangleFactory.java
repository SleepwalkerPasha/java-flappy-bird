package ru.miet.pattern.lab78.flappybird.view.factory;

import ru.miet.pattern.lab78.flappybird.presenter.FlappyBird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RectangleFactory extends AbstractFactory {

    @Override
    public Rectangle createRectangle(boolean top) {
        if (top) {
            return new Rectangle(FlappyBird.WIDTH, 0, PIPE_W,
                    (int) ((Math.random() * FlappyBird.HEIGHT) / 5f + (0.2f) * FlappyBird.HEIGHT));
        } else {
            int h2 = (int) ((Math.random() * FlappyBird.HEIGHT) / 5f + (0.2f) * FlappyBird.HEIGHT);
            return new Rectangle(FlappyBird.WIDTH, FlappyBird.HEIGHT - h2, PIPE_W, h2);
        }
    }

    @Override
    public Graphics2D createPipes(Graphics graphics, Rectangle rectangle) {
        return null;
    }
}
