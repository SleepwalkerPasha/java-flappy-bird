package ru.miet.pattern.lab78;

import ru.miet.pattern.lab78.flappybird.controller.FlappyBirdController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FlappyBirdController controller = new FlappyBirdController();
        controller.start();
    }
}