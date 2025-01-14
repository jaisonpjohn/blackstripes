package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;

public class GraphicBoxElement extends ZplElementBase {
    private int width;
    private int height;
    private int thickness;

    public GraphicBoxElement(int width, int height, int thickness, Point position) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.setX(position.x);
        this.setY(position.y);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(thickness));
        graphics.drawRect(this.getX(), this.getY(), width, height);
    }
}