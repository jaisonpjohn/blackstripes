package com.bugdays.blackstripes.zpl.zplelement;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.awt.*;

public class TextElement extends ZplElementBase {
    private String text;
    private VirtualPrinter virtualPrinter;

    public TextElement(String text, Point position, VirtualPrinter virtualPrinter) {
        this.text = text;
        this.setX(position.x);
        this.setY(position.y);
        this.virtualPrinter = virtualPrinter;
    }

    @Override
    public void draw(Graphics2D graphics) {
        int fontSize = virtualPrinter.getFontHeight();
        graphics.setColor(Color.BLACK);
        System.out.println("Font name: " + virtualPrinter.getFontHeight());
        graphics.setFont(new Font(virtualPrinter.getFontName(), Font.PLAIN, fontSize));
        graphics.drawString(text, this.getX(), this.getY());
    }
}