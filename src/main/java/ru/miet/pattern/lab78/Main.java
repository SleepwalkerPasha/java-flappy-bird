package ru.miet.pattern.lab78;

import ru.miet.pattern.lab78.flappybird.FlappyBird;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FlappyBird.getInstance().start();
    }
}