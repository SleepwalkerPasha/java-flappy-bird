package ru.miet.pattern.lab78.flappybird.model.factory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static ru.miet.pattern.lab78.flappybird.utils.GraphicUtils.HEIGHT;
import static ru.miet.pattern.lab78.flappybird.utils.GraphicUtils.WIDTH;

public class RectangleFactory extends AbstractFactory {

    @Override
    public Rectangle createRectangle(boolean top) {
        if (top) {
            return new Rectangle(WIDTH, 0, PIPE_W,
                    (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT));
        } else {
            int h2 = (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT);
            return new Rectangle(WIDTH, HEIGHT - h2, PIPE_W, h2);
        }
    }

    @Override
    public Graphics2D createPipes(Graphics graphics, Rectangle rectangle) {
        return null;
    }
}
