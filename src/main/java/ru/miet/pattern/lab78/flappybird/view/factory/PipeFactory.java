package ru.miet.pattern.lab78.flappybird.view.factory;

import ru.miet.pattern.lab78.flappybird.presenter.FlappyBird;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PipeFactory extends AbstractFactory {

    @Override
    public Rectangle createRectangle(boolean top) {
        return null;
    }

    @Override
    public Graphics2D createPipes(Graphics graphics, Rectangle rectangle) throws IOException {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.translate(rectangle.x + PIPE_W / 2, rectangle.y + PIPE_H / 2);
        if (rectangle.y < FlappyBird.HEIGHT / 2) {
            graphics2D.translate(0, rectangle.height);
            graphics2D.rotate(Math.PI);
        }
        Image pipeHead = ImageIO.read(new File("src/main/resources/78px-Pipe.png"));
        Image pipeLength = ImageIO.read(new File("src/main/resources/pipe_part.png"));
        graphics2D.drawImage(pipeHead, -PIPE_W / 2, -PIPE_H / 2, PIPE_W, PIPE_H, null);
        graphics2D.drawImage(pipeLength, -PIPE_W / 2, PIPE_H / 2, PIPE_W, rectangle.height, null);
        graphics2D.setTransform(affineTransform);
        return graphics2D;
    }
}
