package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicImageElement extends ZplElementBase {
    private BufferedImage image;

    public GraphicImageElement(BufferedImage image, Point position) {
        this.image = image;
        this.setX(position.x);
        this.setY(position.y);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawImage(image, this.getX(), this.getY(), null);
    }
}