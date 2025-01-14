package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TextElement extends ZplElementBase {
    private String text;
    private final String fontRotation;
    private String fontName = "Arial";
    private int fontHeight = 9;
    private int fontWidth = 0;

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public TextElement(String text, Point position, String fontName, int fontHeight, int fontWidth, String fontRotation) {
        this.text = text;
        this.fontRotation = fontRotation;
        this.setX(position.x);
        this.setY(position.y);
        this.fontName = fontName;
        this.fontHeight = fontHeight;
        this.fontWidth = fontWidth;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(this.fontName, Font.PLAIN, this.fontHeight));
        AffineTransform originalTransform = graphics.getTransform();
        graphics.translate(this.getX(), this.getY());

        if ("B".equals(fontRotation)) {
            graphics.rotate(Math.toRadians(270));
        } else if ("I".equals(fontRotation)) {
            graphics.rotate(Math.toRadians(180));
        } else if ("R".equals(fontRotation)) {
            graphics.rotate(Math.toRadians(90));
        }

        graphics.drawString(text, 0, 0);
        graphics.setTransform(originalTransform);
    }
}