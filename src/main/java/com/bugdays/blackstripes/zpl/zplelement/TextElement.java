package com.bugdays.blackstripes.zpl.zplelement;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.awt.*;

public class TextElement extends ZplElementBase {
    private String text;
    private String fontName = "Arial";
    private int fontHeight = 9;
    private int fontWidth = 0;
    private VirtualPrinter virtualPrinter;

    public TextElement(String text, Point position, VirtualPrinter virtualPrinter) {
        this.text = text;
        this.setX(position.x);
        this.setY(position.y);
        this.virtualPrinter = virtualPrinter;
        this.fontName = virtualPrinter.getFontName();
        this.fontHeight = virtualPrinter.getFontHeight();
        this.fontWidth = virtualPrinter.getFontWidth();
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(this.fontName, Font.PLAIN, this.fontHeight));
        graphics.drawString(text, this.getX(), this.getY());
    }
}