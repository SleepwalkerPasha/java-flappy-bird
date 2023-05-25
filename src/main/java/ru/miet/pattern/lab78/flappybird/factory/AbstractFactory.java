package ru.miet.pattern.lab78.flappybird.factory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public abstract class AbstractFactory {

    protected final static int PIPE_W = 50;

    protected final static int PIPE_H = 30;

    public abstract Rectangle createRectangle(boolean top);

    public abstract Graphics2D createPipes(Graphics graphics, Rectangle rectangle) throws IOException;
}
